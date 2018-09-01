package com.itti.g6sa_autotest;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JudgeResult {
    public static final String TAG = "Tonsen_Tag";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();//获取参数


    /**
     * 断电重启后进入设置检查wifi是否连接
     * */
    @Test
    public void _0001_judgeWifiConnected() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.judgeWifiConnect(20000);
    }

    /**
     * 断电重启后进入设置检查蓝牙是否连接
     * */
    @Test
    public void _0002_judgeBtConnected() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.judgeBtConnected(20000);
    }
    /**
     * 断言为FM界面，且FM频率不为空
     * */
    @Test
    public void _0003_judgeFmPage() throws UiObjectNotFoundException {
        boolean isOk = false;
        UiObject fmFrqObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.desay_svautomotive.svradio:id/radio_number_channel"));
        if (fmFrqObj.exists() && !fmFrqObj.getText().equals("")){
            Log.d(TAG, "_0003_judgeFmPage: FM界面存在且频率不为空");
            isOk = true;
        } else {
            if (!fmFrqObj.exists()) {
                Log.d(TAG, "_0003_judgeFmPage: FM界面不存在");
            } else {
                Log.d(TAG, "_0003_judgeFmPage: FM频率为空");
            }
        }
        assertEquals("断电FM是否存在结果=",true, fmFrqObj.exists());
    }

    /**
     * 断言音源界面
     * */
    @Test
    public void _0004_judgeSource() throws UiObjectNotFoundException {
        //获取参数
        String checkSource = "";
        String sourceStr = bundle.getString("SOURCE");
        Log.d(TAG, "_0004_judgeSource: SOURCE = " + sourceStr);
        if (sourceStr == null || sourceStr.equals("")) {
            checkSource = Actions.FM;
        } else {
            checkSource = sourceStr;
        }
        Actions.judgeSource(checkSource);
    }

    /**
     * 检查系统信息
     * */
    @Test
    public void _1000_CheckSysInfo() throws UiObjectNotFoundException {
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SYSTEM_TAB_SETTINGS);
        //点击通用
        UiObject sysInfoObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/layout_system_message"));
        sysInfoObj.click();
        //获取信息
        String mcuVersion = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/mcu_number")).getText();
        Log.d(TAG, "_1000_CheckSysInfo: mcuVersion = " + mcuVersion);
        String osVersion = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/os_number")).getText();
        Log.d(TAG, "_1000_CheckSysInfo: osVersion = " + osVersion);
        Bundle bundle = new Bundle();
        bundle.putString("McuVer", mcuVersion);
        bundle.putString("OSVer", osVersion);
        instrumentation.sendStatus(1000, bundle);
    }

}































