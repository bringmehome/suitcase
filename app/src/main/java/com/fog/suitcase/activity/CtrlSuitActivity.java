package com.fog.suitcase.activity;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fog.suitcase.Beans.AppStateBean;
import com.fog.suitcase.R;
import com.fog.suitcase.helper.COMPARA;
import com.google.gson.Gson;
import com.junkchen.blelib.BleService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    public static final int _UP_LONG = 4;
    public static final int _UP_LATI = 8;
    public static final int _UP_SWITCH = 5;
    public static final int _UP_ALERT = 6;
    public static final int _UP_DEVID = 7;
    public static final int _GET_STATE = 9;

    private Context context;

    private String mac;
    private BleService mBleService;

    private boolean mIsBind;//是否已经绑定

    private TextView ble_status;
    private ImageView arrow_back;
    private ImageView suit_map;
    //    private TextView update_loc;
    private TextView longitude_tvid;
    private TextView latitude_tvid;
    private TextView devstate_tvid;
    private TextView deviceid_tvid;
    private Switch lock_switch;
    private Switch lock_alert;
    private LinearLayout lock_layout;

    /**
     * 蓝牙服务的列表
     */
    private List<String[]> characteristicList;
    // 服务列表
    private BluetoothGatt mGatt;

    // 各种服务的UUID
    private BluetoothGattCharacteristic bgc_longitude = null;
    private BluetoothGattCharacteristic bgc_latitude = null;
    private BluetoothGattCharacteristic bgc_switch_r = null;
    private BluetoothGattCharacteristic bgc_switch_w = null;
    private BluetoothGattCharacteristic bgc_alert = null;
    private BluetoothGattCharacteristic bgc_devid = null;

    private String _LONGITUDE_TMP = "";
    private String _LATITUDE_TMP = "";
    private String _LATCH_SWITCH_TMP = "false";
    private String _CASE_LOST_TMP = "false";
    private String _DEVICE_TMP = "047863A00214";

    // 蓝牙是否连接上
    private boolean isBLEConnect = false;

    // 是否布防
    private boolean _ISLOCK = false;

    //创建okHttpClient对象
    private OkHttpClient mOkHttpClient;
    private Handler getHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_suit);

        context = CtrlSuitActivity.this;

        initView();

        // 绑定服务
        doBindService();

        // 通过Handler启动线程
        getHandler.post(mRunnable);  //发送消息，启动线程运行
    }

    private void initView() {
//        update_loc = (TextView) findViewById(R.id.update_loc);
        ble_status = (TextView) findViewById(R.id.ble_status1);

        deviceid_tvid = (TextView) findViewById(R.id.deviceid_tvid);
        longitude_tvid = (TextView) findViewById(R.id.longitude_tvid);
        latitude_tvid = (TextView) findViewById(R.id.latitude_tvid);
        devstate_tvid = (TextView) findViewById(R.id.devstate_tvid);
        arrow_back = (ImageView) findViewById(R.id.arrow_back);
        suit_map = (ImageView) findViewById(R.id.suit_map);
        lock_switch = (Switch) findViewById(R.id.lock_switch);
        lock_alert = (Switch) findViewById(R.id.lock_alert);
        lock_layout = (LinearLayout) findViewById(R.id.lock_layout);

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

                if (!isBLEConnect) {
                    String k = "false";
                    if (isChecked) {
                        k = "true";
                    }
                    FormBody body = new FormBody.Builder()
                            .add("device", _DEVICE_TMP)
                            .add("latch_switch", k)
                            .build();
                    sendCloudCmd(body);
                } else {
                    byte[] k;
                    if (isChecked) {
                        k = new byte[]{0x01};
                    } else {
                        k = new byte[]{0x00};
                    }
                    if (null != bgc_switch_w)
                        sendCommand(k, bgc_switch_w);
                }
            }
        });

        lock_alert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                byte[] k;
                if (isChecked) {
                    k = new byte[]{0x01};

                    _ISLOCK = true;
                } else {
                    k = new byte[]{0x00};

                    _ISLOCK = false;
                }
                if (null != bgc_alert)
                    sendCommand(k, bgc_alert);
            }
        });

        Intent i = getIntent();
        mac = i.getStringExtra("mac");
        _DEVICE_TMP = mac.replace(":", "");
        deviceid_tvid.setText(_DEVICE_TMP);

        suit_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_LONGITUDE_TMP.equals("") && !_LATITUDE_TMP.equals("")) {
                    Log.d(TAG, _LONGITUDE_TMP + "  " + _LATITUDE_TMP);
                    Intent intent = new Intent(context, GeoCoderActivity.class);
                    intent.putExtra("longitude", _LONGITUDE_TMP);
                    intent.putExtra("latitude", _LATITUDE_TMP);
                    startActivity(intent);
                } else {
                    Log.d(TAG, "-----为空");
                }
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
                    if (msg.obj.toString().equals("0")){
                        connectble();
                        if (!isBLEConnect && _ISLOCK) {
                            _CASE_LOST_TMP = "true";

                            devstate_tvid.setText(R.string.cast_lost);
                            lock_layout.setVisibility(View.GONE);
                            playWarning();
                        }else {
                            _CASE_LOST_TMP = "false";

                            devstate_tvid.setText(R.string.cast_unlost);
                            lock_layout.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                case _UP_LONG:
                    longitude_tvid.setText(msg.obj.toString());
                    break;
                case _UP_LATI:
                    latitude_tvid.setText(msg.obj.toString());
                    break;
                case _GET_STATE:
                    if (!isBLEConnect && _ISLOCK) {
                        if (msg.obj.toString().equals("false")) {
                            devstate_tvid.setText(R.string.cast_unlost);
                            lock_layout.setVisibility(View.VISIBLE);
                        } else {
                            devstate_tvid.setText(R.string.cast_lost);
                            lock_layout.setVisibility(View.GONE);
                            playWarning();
                        }
                    } else {
                        devstate_tvid.setText(R.string.cast_unlost);
                    }
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
                isBLEConnect = false;
                break;
            case STATE_CONNECTED:
                status = R.string.state_connected;
                isBLEConnect = true;
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
//                                case COMPARA._LONGITUDE:
//                                    bgc_longitude = characteristics.get(i);
//                                    readCharacter();
//                                    break;
//                                case COMPARA._LATITUDE:
//                                    bgc_latitude = characteristics.get(i);
//                                    readCharacter();
//                                    break;
                                case COMPARA._SWITCH_W:
                                    bgc_switch_w = characteristics.get(i);
                                    break;
                                case COMPARA._SWITCH_R:
                                    bgc_switch_r = characteristics.get(i);
                                    readCharacter();
                                    break;
                                case COMPARA._ALERT:
                                    bgc_alert = characteristics.get(i);
                                    readCharacter();
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

                    Log.d(TAG, "onCharacteristicRead ---> " + res);

                    switch (characteristic.getUuid().toString()) {
//                        case COMPARA._LONGITUDE:
//                            send2Handler(_UP_LOCATION, res);
//                            break;
                        case COMPARA._SWITCH_R:
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
                try {
//                    Log.d(TAG, "onCharacteristicChanged ---> " + characteristic.getUuid().toString().equals(COMPARA._LONGITUDE) + " === ");
                    switch (characteristic.getUuid().toString()) {
//                        case COMPARA._LONGITUDE:
//                            _LONGITUDE_TMP = new String(characteristic.getValue(), "UTF-8");
//                            send2Handler(_UP_LONG, _LONGITUDE_TMP);
//                            break;
//                        case COMPARA._LATITUDE:
//                            _LATITUDE_TMP = new String(characteristic.getValue(), "UTF-8");
//                            send2Handler(_UP_LATI, _LATITUDE_TMP);
//                            break;
                        case COMPARA._SWITCH_R:
//                            _LATCH_SWITCH_TMP = new String(characteristic.getValue(), "UTF-8");
                            _LATCH_SWITCH_TMP = getSwitch(characteristic.getValue());
                            break;
                        case COMPARA._DEVID:
                            _DEVICE_TMP = new String(characteristic.getValue(), "UTF-8");
                            break;
                    }
                    upData();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor characteristic, int status) {
                try {
                    Log.d(TAG, "onDescriptorRead ---> " + new String(characteristic.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        mBleService.setOnReadRemoteRssiListener(new BleService.OnReadRemoteRssiListener() {
            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                Log.d(TAG, "onReadRemoteRssi ---> " + status);
            }
        });
    }

    /**
     * 开启订阅和改变的监听
     */
    private void readCharacter() {
        if (null != bgc_longitude) {
            mGatt.readCharacteristic(bgc_longitude);
            mGatt.setCharacteristicNotification(bgc_longitude, true);
        }
        if (null != bgc_latitude) {
            mGatt.readCharacteristic(bgc_latitude);
            mGatt.setCharacteristicNotification(bgc_latitude, true);
        }
        if (null != bgc_switch_r) {
            mGatt.readCharacteristic(bgc_switch_r);
            mGatt.setCharacteristicNotification(bgc_switch_r, true);
        }
        if (null != bgc_alert) {
            mGatt.readCharacteristic(bgc_alert);
            mGatt.setCharacteristicNotification(bgc_alert, true);
        }
    }

    /**
     * 上报数据
     */
    private void upData() {
        mOkHttpClient = new OkHttpClient();

        FormBody body = new FormBody.Builder()
                .add("device", _DEVICE_TMP)
                .add("longitude", _LONGITUDE_TMP)
                .add("latitude", _LATITUDE_TMP)
                .add("latch_switch", _LATCH_SWITCH_TMP)
                .add("case_lost", _CASE_LOST_TMP)
                .build();

        //创建一个Request
        Request request = new Request.Builder()
                .url(COMPARA._STATE_HOST)
                .post(body)
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);

        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.d(TAG, "gson -- htmlStr -- " + htmlStr);

                if (htmlStr.indexOf("code") > -1) {
                    if (0 == getResCode(htmlStr)) {
                        Log.d(TAG, "gson -- success");
                    }
                }
            }
        });
    }

    /**
     * 给云端发送指令
     *
     * @param body
     */
    private void sendCloudCmd(FormBody body) {
        //创建okHttpClient对象
        mOkHttpClient = new OkHttpClient();

        //创建一个Request
        Request request = new Request.Builder()
                .url(COMPARA._CMD_HOST)
                .post(body)
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);

        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.d(TAG, "gson -- htmlStr -- " + htmlStr);

                if (htmlStr.indexOf("code") > -1) {
                    if (0 == getResCode(htmlStr)) {
                        Log.d(TAG, "gson -- success");
                    }
                }
            }
        });
    }

    /**
     * 去云端获取设备状态
     */
    private void getStates() {
        //创建okHttpClient对象
        mOkHttpClient = new OkHttpClient();

        //创建一个Request
        Request request = new Request.Builder()
                .url(COMPARA._GET_STATE_HOST + "?device=" + _DEVICE_TMP)
                .get()
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);

        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.d(TAG, "get -- state -- " + htmlStr);
                if (htmlStr.indexOf("code") > -1) {
                    if (0 == getResCode(htmlStr)) {
                        updateDevInfo(htmlStr);
                    }
                }
            }
        });
    }

    /**
     * 解析云端返回的code
     *
     * @param message
     * @return
     */
    private int getResCode(String message) {

        Gson gson = new Gson();
        AppStateBean asb = null;
        try {
            asb = gson.fromJson(message, AppStateBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null == asb ? 1 : asb.getMeta().getCode();
    }

    /**
     * 将byte数组转成string
     * @param data
     * @return
     */
    private String getSwitch(byte[] data) {
        return data[0] == 1 ? "true" : "false";
    }

    /**
     * 更新设备的信息
     *
     * @param message
     */
    private void updateDevInfo(String message) {

        Gson gson = new Gson();
        AppStateBean asb = null;
        try {
            asb = gson.fromJson(message, AppStateBean.class);
            send2Handler(_GET_STATE, null == asb ? "false" : asb.getData().getCase_lost());

            _LONGITUDE_TMP = asb.getData().getLongitude();
            _LATITUDE_TMP = asb.getData().getLatitude();
            send2Handler(_UP_LONG, _LONGITUDE_TMP);
            send2Handler(_UP_LATI, _LATITUDE_TMP);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private MediaPlayer mp = null;

    /**
     * 播放警报
     */
    private void playWarning() {
        if (mp == null) {
            mp = createLocalMp3();
        }
        try {
            //在播放音频资源之前，必须调用Prepare方法完成些准备工作
            mp.prepare();
            //开始播放音频
            mp.start();

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mp.stop();//停止播放
                    mp.release();//释放资源
                    mp = null;
                }
            }, 5000);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建本地MP3
     *
     * @return
     */
    public MediaPlayer createLocalMp3() {
        /**
         * 创建音频文件的方法：
         * 1、播放资源目录的文件：MediaPlayer.create(MainActivity.this,R.raw.beatit);//播放res/raw 资源目录下的MP3文件
         * 2:播放sdcard卡的文件：mediaPlayer=new MediaPlayer();
         *   mediaPlayer.setDataSource("/sdcard/beatit.mp3");//前提是sdcard卡要先导入音频文件
         */
        MediaPlayer mp = MediaPlayer.create(this, R.raw.warning);
        mp.stop();
        return mp;
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
     * 5s执行一次获取设备的状态
     */
    private Runnable mRunnable = new Runnable() {
        public void run() {
            // 每3秒执行一次
            getStates();
            getHandler.postDelayed(mRunnable, 5000);  //给自己发送消息，自运行
        }
    };

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
        //将线程销毁掉
        getHandler.removeCallbacks(mRunnable);
    }
}
