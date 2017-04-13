package com.fog.suitcase.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fog.suitcase.R;
import com.fog.suitcase.adapter.CommonAdapter;
import com.fog.suitcase.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        // 初始化界面
        initView();
        initAdapter();
        scanLeDevice(true);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        lstv_devList = (ListView) findViewById(R.id.index_devList);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        mSwipeLayout.setColorSchemeResources(R.color.colorAccent);
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

    private void scanLeDevice(boolean paramBoolean) {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);//这里与标准蓝牙略有不同
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        /*隐式打开蓝牙*/
        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }
        BluetoothLeScanner scanner = bluetoothAdapter.getBluetoothLeScanner();
        scanner.startScan(leCallback);
    }

    ScanCallback leCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                BluetoothDevice device = result.getDevice();
                if (!devices.contains(device)) {  //判断是否已经添加
                    devices.add(device);

                    String tmpDevName = device.getName() != null ? device.getName() : "Unknow";
                    String tmpDevAddress = device.getAddress();
                    HashMap<String, Object> deviceMap = new HashMap<>();
                    deviceMap.put("name", tmpDevName);
                    deviceMap.put("address", tmpDevAddress);
                    deviceMap.put("isConnect", false);
                    deviceList.add(deviceMap);
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
}
