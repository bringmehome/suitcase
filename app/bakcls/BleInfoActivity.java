//package com.fog.suitcase.activity;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothGatt;
//import android.bluetooth.BluetoothGattCharacteristic;
//import android.bluetooth.BluetoothGattDescriptor;
//import android.bluetooth.BluetoothGattService;
//import android.bluetooth.BluetoothManager;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.fog.suitcase.MyGattAttributes;
//import com.fog.suitcase.R;
//import com.junkchen.blelib.BleService;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by SIN on 2017/4/11.
// */
//
//public class BleInfoActivity extends AppCompatActivity {
//    private String TAG = "---bleinfo---";
//    public static final int SERVICE_BIND = 1;
//    public static final int SERVICE_SHOW = 3;
//    public static final int _UPDATETV = 2;
//
//    private Context context;
//
//    private String mac;
//    private BleService mBleService;
//
//    private BluetoothManager bleMan;
//    private BluetoothAdapter bleAdapter;
//    private BluetoothGatt bleGatt;
//    private boolean mIsBind;//是否已经绑定
//
//    private TextView ble_status;
//    private ListView ble_showser;
//
//    /**
//     * 蓝牙服务的列表
//     */
//    private List<String> serviceList;
//    private ArrayAdapter<String> serviceAdapter;
//    private List<BluetoothGattService> gattServiceList;
//    private List<String[]> characteristicList;
//    // 服务列表
//    private BluetoothGatt mGatt;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ble_info);
//
//        context = BleInfoActivity.this;
//
//        initView();
//
//        Intent i = getIntent();
//        mac = i.getStringExtra("mac");
//
//        // 绑定服务
//        doBindService();
//    }
//
//    private void initView() {
//        ble_status = (TextView) findViewById(R.id.ble_status);
//        ble_showser = (ListView) findViewById(R.id.ble_showser);
//
//        ble_showser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, "position = " + position + ", id = " + characteristicList.get((int) id));
//
//                Log.d(TAG, gattServiceList.get(position).getUuid()+"");
//
////                Intent intent = new Intent(MainActivity.this, CharacteristicActivity.class);
////                intent.putExtra("characteristic", characteristicList.get((int) id));
////                startActivity(intent);
//
////                byte [] k = new byte[] {0x7e, 0x14, 0x00, 0x00,0x00,(byte) 0xaa};
////                byte [] k = new byte[] {0x01};
////                sendCommand(k, gattServiceList.get(position).getCharacteristics().get(0));
//                mGatt.readCharacteristic(gattServiceList.get(position).getCharacteristics().get(1));
//            }
//        });
//
//        serviceList = new ArrayList<>();
//        characteristicList = new ArrayList<>();
//        serviceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviceList);
//        ble_showser.setAdapter(serviceAdapter);
//    }
//
//    private void connectble() {
//        if (null != mBleService)
//            mBleService.connect(mac);
//    }
//
//    private void sendCommand(byte[] data, BluetoothGattCharacteristic characteristic){
//        //将指令放置进特征中
//        characteristic.setValue(data);
//        //设置回复形式
//        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
//        //开始写数据
//        mGatt.writeCharacteristic(characteristic);
//    }
//
//    private ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mBleService = ((BleService.LocalBinder) service).getService();
//            if (mBleService != null) mHandler.sendEmptyMessage(SERVICE_BIND);
//            if (mBleService.initialize()) {
//                if (mBleService.enableBluetooth(true)) {
//                    Toast.makeText(context, "Bluetooth was opened", Toast.LENGTH_SHORT).show();
//                    connectble();
//                }
//            } else {
//                Toast.makeText(context, "not support Bluetooth", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            mBleService = null;
//            mIsBind = false;
//        }
//    };
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SERVICE_BIND:
//                    ble_status.setText("当前状态：" + SERVICE_BIND);
//                    setBleServiceListener();
//                    break;
//                case _UPDATETV:
//                    ble_status.setText("当前状态：" + msg.obj.toString());
//                    if (msg.obj.toString().equals("0"))
//                        connectble();
//                    break;
//                case SERVICE_SHOW:
//                    serviceAdapter.notifyDataSetChanged();
//                    break;
//            }
//        }
//    };
//
//    private void setBleServiceListener() {
//        mBleService.setOnServicesDiscoveredListener(new BleService.OnServicesDiscoveredListener() {
//            @Override
//            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//                if (status == BluetoothGatt.GATT_SUCCESS) {
//
//                    mGatt = gatt;
//
////                    gatt.readCharacteristic(characteristics.get(i));
//
//                    gattServiceList = gatt.getServices();
//                    serviceList.clear();
//                    for (BluetoothGattService service : gattServiceList) {
//                        String serviceUuid = service.getUuid().toString();
//                        serviceList.add(MyGattAttributes.lookup(serviceUuid, "Unknown") + "\n" + serviceUuid);
//                        Log.d(TAG, MyGattAttributes.lookup(serviceUuid, "Unknown") + "\n" + serviceUuid);
//
//                        List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
//                        String[] charArra = new String[characteristics.size()];
//                        for (int i = 0; i < characteristics.size(); i++) {
//
//                            String charUuid = characteristics.get(i).getUuid().toString();
//                            // 获取数据
//                            // characteristics.get(i).getValue()
//                            charArra[i] = MyGattAttributes.lookup(charUuid, "Unknown") + "\n" + charUuid;
//                        }
//
//                        characteristicList.add(charArra);
//                    }
//                    mHandler.sendEmptyMessage(SERVICE_SHOW);
//                }
//            }
//        });
//
//        /**
//         * Ble连接回调
//         * newstate: 0 disconnect; 2 connected
//         */
//        mBleService.setOnConnectListener(new BleService.OnConnectionStateChangeListener() {
//            @Override
//            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//                Log.i(TAG, "onReadRemoteRssi: newState = " + newState);
//
//                Message msg = new Message();
//                msg.what = _UPDATETV;
//                msg.obj = newState;
//
//                mHandler.sendMessage(msg);
//            }
//        });
//
//        mBleService.setOnReadRemoteRssiListener(new BleService.OnReadRemoteRssiListener() {
//            @Override
//            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
//                Log.i(TAG, "onReadRemoteRssi: rssi = " + rssi);
//            }
//        });
//        mBleService.setOnDataAvailableListener(new BleService.OnDataAvailableListener(){
//
//            @Override
//            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//                Log.i(TAG, "onCharacteristicRead = " + characteristic.getValue());
//
//                try {
//                    String res = new String(characteristic.getValue(),"UTF-8");
//                    Log.i(TAG, res);
//                } catch (UnsupportedEncodingException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//                Log.i(TAG, "onCharacteristicChanged = " + characteristic.getValue());
//            }
//
//            @Override
//            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor characteristic, int status) {
//                Log.i(TAG, "onDescriptorRead = " + characteristic.getValue());
//            }
//        });
//        mBleService.setOnReadRemoteRssiListener(new BleService.OnReadRemoteRssiListener() {
//            @Override
//            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
//                Log.i(TAG, "onReadRemoteRssi: rssi = " + rssi);
//            }
//        });
//    }
//
//    /**
//     * 绑定服务
//     */
//    private void doBindService() {
//        Intent serviceIntent = new Intent(this, BleService.class);
//        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//    }
//
//    /**
//     * 解绑服务
//     */
//    private void doUnBindService() {
//        if (mIsBind) {
//            unbindService(serviceConnection);
//            mBleService = null;
//            mIsBind = false;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        doUnBindService();
//        mBleService.disconnect();
//    }
//}
