package com.itti.g6p_autotest;

import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class G6P_UiAutomatorTest {
    public static final long EXISTS_WAIT_TIME = 1000;//等待存在的等待时长
    public static final String TAG = "Tonsen_Tag";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();//获取参数


    /**
     * wifi开关打开关闭
     * @throws Exception
     */
    @Test
    public void wifiSwitchTest() throws Exception {
        // Context of the app under test.

        mUiDevice.pressHome();
        UiObject settingIconObj = mUiDevice.findObject(new UiSelector().descriptionContains("Settings"));
        settingIconObj.waitForExists(3000);
        settingIconObj.click();

        UiObject networkOjb = mUiDevice.findObject(new UiSelector().textContains("Network & Internet"));
        networkOjb.waitForExists(3000);
        networkOjb.click();

        UiObject wifiSwitchObj = mUiDevice.findObject(new UiSelector().description("Wi‑Fi"));
        wifiSwitchObj.waitForExists(3000);
        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (wifiSwitchObj.isChecked()){//已打开
            Log.d(TAG, "wifiSwitchTest: wifi本身是打开的，正在关闭...");
            wifiSwitchObj.click();//点击关闭
            //指定时间
            long timeout = 10000;
            long startMills = SystemClock.uptimeMillis();
            long currentMills = 0;
            while(currentMills <= timeout){
                currentMills = SystemClock.uptimeMillis() - startMills;
                if(timeout > 0) {
                    SystemClock.sleep(100);
                }
                if (!wifiManager.isWifiEnabled()) {
                    Log.d(TAG, "wifiSwitchTest: wifi关闭成功");
                    currentMills = timeout + 1;
                }
            }
            wifiSwitchObj = mUiDevice.findObject(new UiSelector().description("Wi‑Fi"));
            assertEquals("trun off wifi fail:assert wifiSwitchObj", false, wifiSwitchObj.isChecked());
            assertEquals("trun off wifi fail:assert wifiManager", false, wifiManager.isWifiEnabled());
        } else {
            Log.d(TAG, "wifiSwitchTest: wifi本身是关闭的，正在打开...");
            wifiSwitchObj.click();//点击打开
            //指定时间
            long timeout = 10000;
            long startMills = SystemClock.uptimeMillis();
            long currentMills = 0;
            while(currentMills <= timeout){
                currentMills = SystemClock.uptimeMillis() - startMills;
                if(timeout > 0) {
                    SystemClock.sleep(100);
                }
                if (wifiManager.isWifiEnabled()) {
                    Log.d(TAG, "wifiSwitchTest: wifi打开成功");
                    currentMills = timeout + 1;
                }
            }
            wifiSwitchObj = mUiDevice.findObject(new UiSelector().description("Wi‑Fi"));
            assertEquals("trun on wifi fail:assert wifiSwitchObj", true, wifiSwitchObj.isChecked());
            assertEquals("turn on wifi fail", true, wifiManager.isWifiEnabled());
        }
    }

    @Test
    public void btSwitchTest() throws Exception {
        mUiDevice.pressHome();
        UiObject settingIconObj = mUiDevice.findObject(new UiSelector().descriptionContains("Settings"));
        settingIconObj.waitForExists(3000);
        settingIconObj.click();

        UiObject connectedDevicesOjb = mUiDevice.findObject(new UiSelector().textContains("Connected devices"));
        connectedDevicesOjb.waitForExists(3000);
        connectedDevicesOjb.click();

        UiObject btSwtichOjb = mUiDevice.findObject(new UiSelector().description("Bluetooth"));
        btSwtichOjb.waitForExists(3000);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btSwtichOjb.isChecked()) {
            Log.d(TAG, "btSwitchTest: bt was on, trun off...");
            btSwtichOjb.click();
            //指定时间
            long timeout = 10000;
            long startMills = SystemClock.uptimeMillis();
            long currentMills = 0;
            while(currentMills <= timeout){
                currentMills = SystemClock.uptimeMillis() - startMills;
                if(timeout > 0) {
                    SystemClock.sleep(100);
                }
                if (!bluetoothAdapter.isEnabled()) {
                    Log.d(TAG, "btSwitchTest: bt trun off OK");
                    currentMills = timeout + 1;
                }
            }
            btSwtichOjb = mUiDevice.findObject(new UiSelector().description("Bluetooth"));
            assertEquals("trun off bt fail:assert wifiSwitchObj", false, btSwtichOjb.isChecked());
            assertEquals("turn off bt fail", false, bluetoothAdapter.isEnabled());
        } else {
            Log.d(TAG, "btSwitchTest: bt was off, trun on...");
            btSwtichOjb.click();
            //指定时间
            long timeout = 10000;
            long startMills = SystemClock.uptimeMillis();
            long currentMills = 0;
            while(currentMills <= timeout){
                currentMills = SystemClock.uptimeMillis() - startMills;
                if(timeout > 0) {
                    SystemClock.sleep(100);
                }
                if (bluetoothAdapter.isEnabled()) {
                    Log.d(TAG, "btSwitchTest: bt trun on OK");
                    currentMills = timeout + 1;
                }
            }
            btSwtichOjb = mUiDevice.findObject(new UiSelector().description("Bluetooth"));
            assertEquals("trun on bt fail:assert btSwitchTest", true, btSwtichOjb.isChecked());
            assertEquals("turn on bt fail", true, bluetoothAdapter.isEnabled());
        }
    }


}
