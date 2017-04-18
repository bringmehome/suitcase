package com.fog.suitcase.activity;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fog.suitcase.R;
import com.fog.suitcase.helper.COMPARA;
import com.junkchen.blelib.BleService;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.bluetooth.BluetoothAdapter.STATE_CONNECTING;
import static android.bluetooth.BluetoothProfile.STATE_CONNECTED;
import static android.bluetooth.BluetoothProfile.STATE_DISCONNECTED;

/**
 * Created by SIN on 2017/4/13.
 */

public class CtrlSuitActivity extends AppCompatActivity {
    private String TAG = "---bleinfo---";
    public static final int SERVICE_BIND = 1;
    public static final int _UPDATETV = 2;

    // 更新经纬度
    public static final int _UP_LOCATION = 4;
    public static final int _UP_SWITCH = 5;
    public static final int _UP_ALERT = 6;
    public static final int _UP_DEVID = 7;

    private Context context;

    private String mac;
    private BleService mBleService;

    private boolean mIsBind;//是否已经绑定

    private TextView ble_status;
    private ImageView arrow_back;
//    private TextView update_loc;
//    private TextView longitude_tvid;
//    private TextView latitude_tvid;
    private Switch lock_switch;
    private Switch lock_alert;

    /**
     * 蓝牙服务的列表
     */
    private List<String[]> characteristicList;
    // 服务列表
    private BluetoothGatt mGatt;

    // 各种服务的UUID
    private BluetoothGattCharacteristic bgc_location = null;
    private BluetoothGattCharacteristic bgc_switch = null;
    private BluetoothGattCharacteristic bgc_alert = null;
    private BluetoothGattCharacteristic bgc_devid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_suit);

        context = CtrlSuitActivity.this;

        initView();

        Intent i = getIntent();
        mac = i.getStringExtra("mac");

        // 绑定服务
        doBindService();
    }

    private void initView() {
//        update_loc = (TextView) findViewById(R.id.update_loc);
        ble_status = (TextView) findViewById(R.id.ble_status1);

//        longitude_tvid = (TextView) findViewById(R.id.longitude_tvid);
//        latitude_tvid = (TextView) findViewById(R.id.latitude_tvid);
        arrow_back = (ImageView) findViewById(R.id.arrow_back);
        lock_switch = (Switch) findViewById(R.id.lock_switch);
        lock_alert = (Switch) findViewById(R.id.lock_alert);


        characteristicList = new ArrayList<>();

//        update_loc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != bgc_location)
//                    mGatt.readCharacteristic(bgc_location);
//            }
//        });

        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lock_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                byte[] k;
                if (isChecked) {
                    k = new byte[]{0x01};
                } else {
                    k = new byte[]{0x00};
                }
                if (null != bgc_switch)
                    sendCommand(k, bgc_switch);
            }
        });

        lock_alert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                byte[] k;
                if (isChecked) {
                    k = new byte[]{0x01};
                } else {
                    k = new byte[]{0x00};
                }
                if (null != bgc_alert)
                    sendCommand(k, bgc_alert);
            }
        });

    }

    private void connectble() {
        if (null != mBleService)
            mBleService.connect(mac);
    }

    private void sendCommand(byte[] data, BluetoothGattCharacteristic characteristic) {
        //将指令放置进特征中
        characteristic.setValue(data);
        //设置回复形式
        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
        //开始写数据
        mGatt.writeCharacteristic(characteristic);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBleService = ((BleService.LocalBinder) service).getService();
            if (mBleService != null) mHandler.sendEmptyMessage(SERVICE_BIND);
            if (mBleService.initialize()) {
                if (mBleService.enableBluetooth(true)) {
                    connectble();
                }
            } else {
                Toast.makeText(context, "Not support Bluetooth", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBleService = null;
            mIsBind = false;
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SERVICE_BIND:
                    ble_status.setText(getStatus(SERVICE_BIND));
                    setBleServiceListener();
                    break;
                case _UPDATETV:
                    ble_status.setText(getStatus(Integer.parseInt(msg.obj.toString())));
                    if (msg.obj.toString().equals("0"))
                        connectble();
                    break;
                case _UP_LOCATION:
//                    longitude_tvid.setText(msg.obj.toString());
                    break;
            }
        }
    };

    /**
     * 获取显示的状态
     *
     * @param code
     * @return
     */
    private int getStatus(int code) {
        int status = R.string.connecting;
        switch (code) {
            case STATE_DISCONNECTED:
                status = R.string.tryagain;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ble_status.setText(R.string.connecting);
                    }
                }, 2000);
                break;
            case STATE_CONNECTED:
                status = R.string.state_connected;
                break;
            case STATE_CONNECTING:
                status = R.string.connecting;
                break;
        }
        return status;
    }

    private void setBleServiceListener() {
        mBleService.setOnServicesDiscoveredListener(new BleService.OnServicesDiscoveredListener() {
            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                if (status == BluetoothGatt.GATT_SUCCESS) {

                    mGatt = gatt;
                    for (BluetoothGattService service : gatt.getServices()) {

                        List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
                        for (int i = 0; i < characteristics.size(); i++) {

                            String charUuid = characteristics.get(i).getUuid().toString();

                            switch (charUuid) {
                                case COMPARA._LOCATION:
                                    bgc_location = characteristics.get(i);
                                    break;
                                case COMPARA._SWITCH:
                                    bgc_switch = characteristics.get(i);
                                    break;
                                case COMPARA._ALERT:
                                    bgc_alert = characteristics.get(i);
                                    break;
                                case COMPARA._DEVID:
                                    bgc_devid = characteristics.get(i);
                                    break;
                            }
                        }
                    }
                }
            }
        });

        /**
         * Ble连接回调
         * newstate: 0 disconnect; 2 connected
         */
        mBleService.setOnConnectListener(new BleService.OnConnectionStateChangeListener() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                Message msg = new Message();
                msg.what = _UPDATETV;
                msg.obj = newState;

                mHandler.sendMessage(msg);
            }
        });

        mBleService.setOnReadRemoteRssiListener(new BleService.OnReadRemoteRssiListener() {
            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
//                Log.i(TAG, "onReadRemoteRssi: rssi = " + rssi);
            }
        });
        mBleService.setOnDataAvailableListener(new BleService.OnDataAvailableListener() {

            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                try {
                    String res = new String(characteristic.getValue(), "UTF-8");

                    switch (characteristic.getUuid().toString()) {
                        case COMPARA._LOCATION:
                            send2Handler(_UP_LOCATION, res);
                            break;
                        case COMPARA._SWITCH:
                            send2Handler(_UP_SWITCH, res);
                            break;
                        case COMPARA._ALERT:
                            send2Handler(_UP_ALERT, res);
                            break;
                        case COMPARA._DEVID:
                            send2Handler(_UP_DEVID, res);
                            break;
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//                Log.i(TAG, "onCharacteristicChanged = " + characteristic.getValue());
            }

            @Override
            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor characteristic, int status) {
//                Log.i(TAG, "onDescriptorRead = " + characteristic.getValue());
            }
        });
        mBleService.setOnReadRemoteRssiListener(new BleService.OnReadRemoteRssiListener() {
            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
//                Log.i(TAG, "onReadRemoteRssi: rssi = " + rssi);
            }
        });
    }

    /**
     * 通知handler来更新页面
     *
     * @param tag
     * @param message
     */
    private void send2Handler(int tag, String message) {
        Message msg = new Message();
        msg.what = tag;
        msg.obj = message;
        mHandler.sendMessage(msg);
    }

    /**
     * 绑定服务
     */
    private void doBindService() {
        Intent serviceIntent = new Intent(this, BleService.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        mIsBind = true;
    }

    /**
     * 解绑服务
     */
    private void doUnBindService() {
        if (mIsBind) {
            unbindService(serviceConnection);
            mBleService.disconnect();

            mBleService = null;
            mIsBind = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnBindService();
    }
}
