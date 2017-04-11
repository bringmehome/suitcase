package com.fog.suitcase.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fog.suitcase.MyGattAttributes;
import com.fog.suitcase.R;
import com.fog.suitcase.adapter.CommonAdapter;
import com.fog.suitcase.adapter.ViewHolder;
import com.junkchen.blelib.BleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SIN on 2017/4/11.
 */

public class IndexActivity extends AppCompatActivity{
    private static final String TAG = "---index---";
    public static final int SERVICE_BIND = 1;
    public static final int SERVICE_SHOW = 2;
    public static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;

    private ListView lstv_devList;
    private SwipeRefreshLayout mSwipeLayout;
    // 服务列表
    private CommonAdapter<Map<String, Object>> deviceAdapter;
    private BleService mBleService;

    private String connDeviceName;
    private String connDeviceAddress;

    private List<Map<String, Object>> deviceList;
    private List<String> serviceList;
    private List<String[]> characteristicList;
    private ArrayAdapter<String> serviceAdapter;
    private List<BluetoothGattService> gattServiceList;

    /**
     * dialog
     */
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        // 初始化界面
        initView();
        // 设备列表adapter
        initAdapter();
        // 注册广播
        registerReceiver(bleReceiver, makeIntentFilter());
        // 绑定服务
        doBindService();
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
                if(null != deviceMap.get("name")){
                    holder.setText(R.id.txtv_name, deviceMap.get("name").toString());
                }
                holder.setText(R.id.txtv_address, deviceMap.get("address").toString());
                holder.setText(R.id.txtv_connState, ((boolean) deviceMap.get("isConnect")) ? getResources().getString(R.string.state_connected) : getResources().getString(R.string.state_disconnected));
                holder.setText(R.id.btn_connect, ((boolean) deviceMap.get("isConnect")) ? getResources().getString(R.string.disconnected) : getResources().getString(R.string.connected));
                holder.getView(R.id.btn_connect).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, BleInfoActivity.class);
                        intent.putExtra("mac", (String) deviceMap.get("address"));
                        startActivity(intent);

//                        if ((boolean) deviceMap.get("isConnect")) {
//                            mBleService.disconnect();
//                            showDialog(getString(R.string.disconnecting));
//                        } else {
//                            connDeviceAddress = (String) deviceMap.get("address");
//                            connDeviceName = (String) deviceMap.get("name");
//                            HashMap<String, Object> connDevMap = new HashMap<String, Object>();
//                            connDevMap.put("name", connDeviceName);
//                            connDevMap.put("address", connDeviceAddress);
//                            connDevMap.put("isConnect", false);
//                            deviceList.clear();
//                            deviceList.add(connDevMap);
//                            deviceAdapter.notifyDataSetChanged();
//                            mBleService.connect(connDeviceAddress);
//                            showDialog(getString(R.string.connecting));
//                        }
                    }
                });
            }
        };
        lstv_devList.setAdapter(deviceAdapter);
        serviceList = new ArrayList<>();
        characteristicList = new ArrayList<>();
        serviceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviceList);
    }

    /**
     * 绑定服务
     */
    private void doBindService() {
        Intent serviceIntent = new Intent(this, BleService.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void verifyIfRequestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.i(TAG, "onCreate: checkSelfPermission");
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onCreate: Android 6.0 动态申请权限");

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                    Log.i(TAG, "*********onCreate: shouldShowRequestPermissionRationale**********");
                    Toast.makeText(this, "只有允许访问位置才能搜索到蓝牙设备", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_ACCESS_COARSE_LOCATION);
                }
            } else {
                showDialog(getResources().getString(R.string.scanning));
                mBleService.scanLeDevice(true);
            }
        } else {
            showDialog(getResources().getString(R.string.scanning));
            mBleService.scanLeDevice(true);
        }
    }

    private static IntentFilter makeIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BleService.ACTION_BLUETOOTH_DEVICE);
        intentFilter.addAction(BleService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BleService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BleService.ACTION_SCAN_FINISHED);
        return intentFilter;
    }

    private void setBleServiceListener() {
        mBleService.setOnServicesDiscoveredListener(new BleService.OnServicesDiscoveredListener() {
            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                if (status == BluetoothGatt.GATT_SUCCESS) {

                    gattServiceList = gatt.getServices();
                    serviceList.clear();
                    for (BluetoothGattService service : gattServiceList) {
                        String serviceUuid = service.getUuid().toString();
                        serviceList.add(MyGattAttributes.lookup(serviceUuid, "Unknown") + "\n" + serviceUuid);
                        Log.d(TAG, MyGattAttributes.lookup(serviceUuid, "Unknown") + "\n" + serviceUuid);

                        List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
                        String[] charArra = new String[characteristics.size()];
                        for (int i = 0; i < characteristics.size(); i++) {

                            String charUuid = characteristics.get(i).getUuid().toString();
                            // 获取数据
                            // characteristics.get(i).getValue()
                            charArra[i] = MyGattAttributes.lookup(charUuid, "Unknown") + "\n" + charUuid;
                        }

                        characteristicList.add(charArra);
                    }
                    mHandler.sendEmptyMessage(SERVICE_SHOW);
                }
            }
        });
//        //Ble扫描回调
//        mBleService.setOnLeScanListener(new BleService.OnLeScanListener() {
//            @Override
//            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
//                //每当扫描到一个Ble设备时就会返回，（扫描结果重复的库中已处理）
//            }
//        });
//        //Ble连接回调
//        mBleService.setOnConnectListener(new BleService.OnConnectListener() {
//            @Override
//            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//                if (newState == BluetoothProfile.STATE_DISCONNECTED) {
//                    //Ble连接已断开
//                } else if (newState == BluetoothProfile.STATE_CONNECTING) {
//                    //Ble正在连接
//                } else if (newState == BluetoothProfile.STATE_CONNECTED) {
//                    //Ble已连接
//                } else if (newState == BluetoothProfile.STATE_DISCONNECTING) {
//                    //Ble正在断开连接
//                }
//            }
//        });
//        //Ble服务发现回调
//        mBleService.setOnServicesDiscoveredListener(new BleService.OnServicesDiscoveredListener() {
//            @Override
//            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//
//            }
//        });
//        //Ble数据回调
//        mBleService.setOnDataAvailableListener(new BleService.OnDataAvailableListener() {
//            @Override
//            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//                //处理特性读取返回的数据
//            }
//
//            @Override
//            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//                //处理通知返回的数据
//            }
//        @Override
//        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
//
//        }
//        });

        mBleService.setOnReadRemoteRssiListener(new BleService.OnReadRemoteRssiListener() {
            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                Log.i(TAG, "onReadRemoteRssi: rssi = " + rssi);
            }
        });
    }

    /**
     * Show dialog
     */
    private void showDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private void dismissDialog() {
        if (progressDialog == null) return;
        progressDialog.dismiss();
        progressDialog = null;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBleService = ((BleService.LocalBinder) service).getService();
            if (mBleService != null) mHandler.sendEmptyMessage(SERVICE_BIND);
            if (mBleService.initialize()) {
                if (mBleService.enableBluetooth(true)) {
                    verifyIfRequestPermission();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBleService = null;
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SERVICE_BIND:
                    setBleServiceListener();
                    break;
                case SERVICE_SHOW:
                    serviceAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    /**
     * 广播
     */
    private BroadcastReceiver bleReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BleService.ACTION_BLUETOOTH_DEVICE)) {
                String tmpDevName = intent.getStringExtra("name");
                String tmpDevAddress = intent.getStringExtra("address");
                Log.i(TAG, "name: " + tmpDevName + ", address: " + tmpDevAddress);
                HashMap<String, Object> deviceMap = new HashMap<>();
                deviceMap.put("name", tmpDevName);
                deviceMap.put("address", tmpDevAddress);
                deviceMap.put("isConnect", false);
                deviceList.add(deviceMap);
                deviceAdapter.notifyDataSetChanged();
            } else if (intent.getAction().equals(BleService.ACTION_GATT_CONNECTED)) {
                deviceList.get(0).put("isConnect", true);
                deviceAdapter.notifyDataSetChanged();
                dismissDialog();
            } else if (intent.getAction().equals(BleService.ACTION_GATT_DISCONNECTED)) {
                deviceList.get(0).put("isConnect", false);
                serviceList.clear();
                characteristicList.clear();
                deviceAdapter.notifyDataSetChanged();
                serviceAdapter.notifyDataSetChanged();
                dismissDialog();
            } else if (intent.getAction().equals(BleService.ACTION_SCAN_FINISHED)) {
                dismissDialog();
            }
        }
    };
}
