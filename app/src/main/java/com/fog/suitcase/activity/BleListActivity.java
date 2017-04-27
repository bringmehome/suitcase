package com.fog.suitcase.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fog.suitcase.R;
import com.fog.suitcase.adapter.CommonAdapter;
import com.fog.suitcase.adapter.ViewHolder;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by SIN on 2017/4/11.
 */

public class BleListActivity extends AppCompatActivity {
    private String TAG = "---bleact---";

    // view
    private ListView lstv_devList;
    private SwipeRefreshLayout mSwipeLayout;

    // bledevice
    private List<Map<String, Object>> deviceList;
    private CommonAdapter<Map<String, Object>> deviceAdapter;
    private List<String> serviceList;
    private ArrayAdapter<String> serviceAdapter;

    // 临时存储devices
    private List<BluetoothDevice> devices = new ArrayList<>();

    private BluetoothLeScanner scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        // 初始化界面
        initView();
        listenBLEchange();

        initAdapter();
        scanLeDevice();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        lstv_devList = (ListView) findViewById(R.id.index_devList);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            public void onRefresh() {

                stopScanLeDevice();
                deviceList.clear();
                devices.clear();
                deviceAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mSwipeLayout.setRefreshing(false);
                        scanLeDevice();
                    }
                }, 2000);
            }
        });

        mSwipeLayout.setColorSchemeResources(R.color.colorAccent);

        jumping();
    }

    // 扫描中动画
    private void jumping() {
        TextView scaning_txt = (TextView) findViewById(R.id.scaning_txt);
        JumpingBeans.with(scaning_txt)
                .appendJumpingDots()
                .build();
    }

    /**
     * 设备列表adapter
     */
    private void initAdapter() {
        deviceList = new ArrayList<>();
        deviceAdapter = new CommonAdapter<Map<String, Object>>(this, R.layout.item_device, deviceList) {
            @Override
            public void convert(ViewHolder holder, final Map<String, Object> deviceMap) {
                if (null != deviceMap.get("name")) {
                    holder.setText(R.id.txtv_name, deviceMap.get("name").toString());
                }
                holder.setText(R.id.txtv_address, deviceMap.get("address").toString());
                holder.setText(R.id.txtv_connState, ((boolean) deviceMap.get("isConnect")) ? getResources().getString(R.string.state_connected) : getResources().getString(R.string.state_disconnected));
                holder.setText(R.id.btn_connect, ((boolean) deviceMap.get("isConnect")) ? getResources().getString(R.string.disconnected) : getResources().getString(R.string.connected));
                holder.getView(R.id.btn_connect).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, CtrlSuitActivity.class);
//                        Intent intent = new Intent(context, BleInfoActivity.class);
                        intent.putExtra("mac", (String) deviceMap.get("address"));
                        startActivity(intent);
                    }
                });
            }
        };
        lstv_devList.setAdapter(deviceAdapter);
        serviceList = new ArrayList<>();
        serviceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviceList);
    }

    BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;

    private void scanLeDevice() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);//这里与标准蓝牙略有不同
        bluetoothAdapter = bluetoothManager.getAdapter();

        /**
         * 打开位置权限
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText(BleListActivity.this, "自Android 6.0开始需要打开位置权限才可以搜索到Ble设备", Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }
        }


        /*隐式打开蓝牙*/
        if (!bluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);  // 弹对话框的形式提示用户开启蓝牙
//            bluetoothAdapter.enable();// 强制开启，不推荐使用
        } else {
            scanner = bluetoothAdapter.getBluetoothLeScanner();
            scanner.startScan(leCallback);
        }
    }

    private void stopScanLeDevice() {
        scanner.stopScan(leCallback);
    }

    ScanCallback leCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                BluetoothDevice device = result.getDevice();
                if (!devices.contains(device)) {  //判断是否已经添加
                    devices.add(device);

                    if (device.getName() != null) {
                        String tmpDevName = device.getName();
//                        String tmpDevName = device.getName() != null ? device.getName() : "Unknow";
                        String tmpDevAddress = device.getAddress();
                        HashMap<String, Object> deviceMap = new HashMap<>();
                        deviceMap.put("name", tmpDevName);
                        deviceMap.put("address", tmpDevAddress);
                        deviceMap.put("isConnect", false);
                        deviceList.add(deviceMap);
                    }
                    deviceAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.e("搜索失败", "");
        }
    };

    /**
     * 监听蓝牙的变化
     */
    private void listenBLEchange() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) { // 蓝牙开关发生变化
                // 这里可以直接使用mBluetoothAdapter.isEnabled()来判断当前蓝牙状态
                scanner = bluetoothAdapter.getBluetoothLeScanner();
                try {
                    if (null != scanner)
                        scanner.startScan(leCallback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
