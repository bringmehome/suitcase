package com.junkchen.blelib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;

/**
 * Created by JunkChen on 2016/5/21 0021.
 */

interface BleListener {
    interface OnLeScanListener {
        /**
         * Callback reporting an LE device found during a device scan initiated
         * by the {@link BluetoothAdapter#startLeScan} function.
         *
         * @param device Identifies the remote device
         * @param rssi The RSSI value for the remote device as reported by the
         *             Bluetooth hardware. 0 if no RSSI value is available.
         * @param scanRecord The content of the advertisement record offered by
         *                   the remote device.
         */
        void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord);
    }

    interface OnConnectionStateChangeListener {
        /**
         * Callback indicating when GATT client has connected/disconnected to/from a remote
         * GATT server.
         *
         * @param gatt GATT client
         * @param status Status of the connect or disconnect operation.
         *               {@link BluetoothGatt#GATT_SUCCESS} if the operation succeeds.
         * @param newState Returns the new connection state. Can be one of
         *                  {@link BluetoothProfile#STATE_DISCONNECTED} or
         *                  {@link BluetoothProfile#STATE_CONNECTED}
         */
        void onConnectionStateChange(BluetoothGatt gatt, int status, int newState);
    }

    interface OnServicesDiscoveredListener {
        /**
         * Callback invoked when the list of remote services, characteristics and descriptors
         * for the remote device have been updated, ie new services have been discovered.
         *
         * @param gatt GATT client invoked {@link BluetoothGatt#discoverServices}
         * @param status {@link BluetoothGatt#GATT_SUCCESS} if the remote device
         *               has been explored successfully.
         */
        void onServicesDiscovered(BluetoothGatt gatt, int status);
    }

    interface OnDataAvailableListener {
        /**
         * Callback reporting the result of a characteristic read operation.
         *
         * @param gatt GATT client invoked {@link BluetoothGatt#readCharacteristic}
         * @param characteristic Characteristic that was read from the associated
         *                       remote device.
         * @param status {@link BluetoothGatt#GATT_SUCCESS} if the read operation
         *               was completed successfully.
         */
        void onCharacteristicRead(BluetoothGatt gatt,
                                  BluetoothGattCharacteristic characteristic, int status);

        /**
         * Callback triggered as a result of a remote characteristic notification.
         *
         * @param gatt GATT client the characteristic is associated with
         * @param characteristic Characteristic that has been updated as a result
         *                       of a remote notification event.
         */
        void onCharacteristicChanged(BluetoothGatt gatt,
                                     BluetoothGattCharacteristic characteristic);

        /**
         * Callback reporting the result of a descriptor read operation.
         *
         * @param gatt GATT client invoked {@link BluetoothGatt#readDescriptor}
         * @param descriptor Descriptor that was read from the associated
         *                   remote device.
         * @param status {@link BluetoothGatt#GATT_SUCCESS} if the read operation
         *               was completed successfully
         */
        void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status);
    }

    interface OnReadRemoteRssiListener {
        /**
         * Callback reporting the RSSI for a remote device connection.
         *
         * This callback is triggered in response to the
         * {@link BluetoothGatt#readRemoteRssi} function.
         *
         * @param gatt GATT client invoked {@link BluetoothGatt#readRemoteRssi}
         * @param rssi The RSSI value for the remote device
         * @param status {@link BluetoothGatt#GATT_SUCCESS} if the RSSI was read successfully
         */
        void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status);
    }

    interface OnMtuChangedListener {
        /**
         * Callback indicating the MTU for a given device connection has changed.
         *
         * This callback is triggered in response to the
         * {@link BluetoothGatt#requestMtu} function, or in response to a connection
         * event.
         *
         * @param gatt GATT client invoked {@link BluetoothGatt#requestMtu}
         * @param mtu The new MTU size
         * @param status {@link BluetoothGatt#GATT_SUCCESS} if the MTU has been changed successfully
         */
        void onMtuChanged(BluetoothGatt gatt, int mtu, int status);
    }
}
