package com.android.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Toast mToast;
    String mTag = "blueTooth";
    BluetoothAdapter mDefaultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDefaultAdapter = BluetoothAdapter.getDefaultAdapter();
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        registerReceiver(mReceiver, filter);
    }


    /**
     * @param view 开始搜索
     */
    public void searchDevices(View view) {
        if (mDefaultAdapter != null) {
            if (!mDefaultAdapter.isEnabled()) {
                mDefaultAdapter.enable();
            }
            mDefaultAdapter.startDiscovery();
            showMsg("正在搜索...");
        } else {
            showMsg("设备不支持蓝牙");
        }
    }


    /**
     * @param view 停止搜索
     */
    public void stopSearch(View view) {
        mDefaultAdapter.cancelDiscovery();
    }

    private void showMsg(String s) {

        if (mToast == null) {
            mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
        if (mDefaultAdapter != null && mDefaultAdapter.isEnabled()) {
            mDefaultAdapter.disable();
            mDefaultAdapter = null;
        }
    }


    /**
     * 蓝牙接收者
     */
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(mTag, action);
            showMsg(action);
            switch (action) {
                case BluetoothDevice.ACTION_FOUND://发现设备
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Log.d(mTag, "name==" + device.getName() + " address==" + device.getAddress());
                    showMsg(device.getName());
                    break;
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED:
                    break;
            }
        }
    };

}
