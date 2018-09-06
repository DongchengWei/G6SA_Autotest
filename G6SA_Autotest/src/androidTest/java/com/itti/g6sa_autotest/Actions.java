package com.itti.g6sa_autotest;

import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.IOException;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by uidq0460 on 2018/5/30.
 */

public class Actions {
    public static final String TAG = "Tonsen_Tag";


    //source
    public static  final String FM = "FM";
    public static  final String AM = "AM";
    public static  final String NET_FM = "NET_FM";
    public static  final String USB1_MUSIC = "USB1_MUSIC";
    public static  final String USB2_MUSIC = "USB2_MUSIC";
    public static  final String BT_MUSIC = "BT_MUSIC";
    public static  final String IPOD_MUSIC = "IPOD_MUSIC";
    public static  final String ONLINE_MUSIC = "ONLINE_MUSIC";
    public static  final String USB1_VIDEO = "USB1_VIDEO";
    public static  final String USB2_VIDEO = "USB2_VIDEO";
    public static  final String USB1_PICTURE = "USB1_PICTURE";
    public static  final String USB2_PICTURE = "USB2_PICTURE";


    //navabar
    public static final String HOME_NAVBAR = "Home";
    public static final String RADIO_NAVBAR = "Radio";
    public static final String MEDIA_NAVBAR = "Media";
    public static final String PHONE_NAVBAR = "Phone";
    public static final String HVAC_NAVBAR = "Hvac";
    public static final String NAVIGATION_NAVBAR = "Navigation";
    public static final String APPLIST_NAVBAR = "AppList";
    public static final String SETTINGS_NAVBAR = "Settings";


    //settings tap
    public static final String WIFI_TAB_SETTINGS = "WIFI";
    public static final String BT_TAB_SETTINGS = "Bluetooth";
    public static final String TIME_TAB_SETTINGS = "Time";
    public static final String DISPLAY_TAB_SETTINGS = "Display";
    public static final String SOUND_TAB_SETTINGS = "Sound";
    public static final String VOICE_TAB_SETTINGS = "Voice";
    public static final String SYSTEM_TAB_SETTINGS = "System";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();


    /**
     * 点击导航栏到指定页面
     * @param nav
     * @return
     */
    public static String navToLocationString(String nav) {
        String locationStr = "";
        if (nav.equals(HOME_NAVBAR)) {
            locationStr = "input tap 60 660";
        } else if (nav.equals(RADIO_NAVBAR)) {
            locationStr = "input tap 220 660";
        } else if (nav.equals(MEDIA_NAVBAR)) {
            locationStr = "input tap 380 660";
        } else if (nav.equals(PHONE_NAVBAR)) {
            locationStr = "input tap 540 660";
        } else if (nav.equals(HVAC_NAVBAR)) {
            locationStr = "input tap 700 660";
        } else if (nav.equals(NAVIGATION_NAVBAR)) {
            locationStr = "input tap 860 660";
        } else if (nav.equals(APPLIST_NAVBAR)) {
            locationStr = "input tap 1020 660";
        } else if (nav.equals(SETTINGS_NAVBAR)) {
            locationStr = "input tap 1180 660";
        }
        return locationStr;
    }
    /**
     * 导航栏点击到系统某应用
     * @throws Exception
     */
    public static void navigateBarTo(String navTo) throws UiObjectNotFoundException{

//        String navTo = bundle.getString("NavTo");
        mUiDevice.pressHome();
        SystemClock.sleep(1000);
        UiObject targetObj;
        if (navTo.equals("Home")){
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/os_number"));
            targetObj.getText();
            assertEquals("assert go back home", true, targetObj.waitForExists(3000));
        } else if (navTo.equals("Radio")){
            amActivity("com.desay_svautomotive.svradio/.RadioMainActivity");
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.desay_svautomotive.svradio:id/radio_type_fm"));
            targetObj.getText();
            assertEquals("assert go to Radiot", true, targetObj.waitForExists(3000));
        } else if (navTo.equals("Media")){
            amActivity("com.desaysv_automotive.svmedia/.MainActivity");
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/tv_music_hall"));
            targetObj.getText();
            assertEquals("assert go to Media", true, targetObj.waitForExists(3000));
        } else if (navTo.equals("Phone")){
//            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.desay_svautomotive.svradio:id/radio_number"));
//            assertEquals(true, targetObj.waitForExists(3000));
        } else if (navTo.equals("Hvac")){
//            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.desay_svautomotive.svradio:id/radio_number"));
//            assertEquals(true, targetObj.waitForExists(3000));
        } else if (navTo.equals("Navigation")){
//            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.desay_svautomotive.svradio:id/radio_number"));
//            assertEquals(true, targetObj.waitForExists(3000));
        } else if (navTo.equals("AppList")){
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.launcher:id/app_icon"));
            assertEquals("assert go to AppList", true, targetObj.waitForExists(3000));
        } else if (navTo.equals("Settings")){//1030,670    220,670
            amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab"));
            targetObj.getText();
            assertEquals("assert goto Settings", true, targetObj.waitForExists(3000));
        }
    }

    public static void amActivity(String activityName) {
        try {
            mUiDevice.executeShellCommand("am start -n " + activityName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void intoSettingsTab(String witchTab) throws UiObjectNotFoundException{
        UiObject targetObj;
        if (witchTab.equals(WIFI_TAB_SETTINGS)) {
            mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_wifi_id")).click();
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_content"));
            assertEquals("assert goto wifiSetting", true, targetObj.waitForExists(3000));
        } else if (witchTab.equals(BT_TAB_SETTINGS)){
            mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_bluetooth_id")).click();
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/bt_power_switch"));
            assertEquals("assert goto btSetting", true, targetObj.waitForExists(3000));
        } else if (witchTab.equals(TIME_TAB_SETTINGS)){
            mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_time_id")).click();
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_12"));
            assertEquals("assert goto timeSetting",true, targetObj.waitForExists(3000));
        } else if (witchTab.equals(DISPLAY_TAB_SETTINGS)){
            mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_display_id")).click();
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_raw_driveclock"));
            assertEquals("assert goto displaySetting",true, targetObj.waitForExists(3000));
        } else if (witchTab.equals(SOUND_TAB_SETTINGS)){
            mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_sound_id")).click();
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sound_effects"));
            assertEquals("assert goto soundSetting",true, targetObj.waitForExists(3000));
        } else if (witchTab.equals(VOICE_TAB_SETTINGS)){
            mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_voice_id")).click();
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/wakeup_switch"));
            assertEquals("assert goto voiceSetting",true, targetObj.waitForExists(3000));
        }else if (witchTab.equals(SYSTEM_TAB_SETTINGS)){
            mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/sv_tab_system_id")).click();
            targetObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/layout_system_message"));
            assertEquals("assert goto systemSetting",true, targetObj.waitForExists(3000));
        }
    }

    /**
     * @throws Exception
     */
    public static void disconnectWifi() throws UiObjectNotFoundException {
        UiScrollable settingsItem = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));

        settingsItem.scrollToBeginning(50);
        UiObject statusObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/wifi_list_item_desc"));
        if (statusObj.waitForExists(5000)) {
            statusObj.click();
            UiObject unsaveBtn = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/dialog_forget"));
            if (unsaveBtn.waitForExists(3000) && unsaveBtn.isEnabled()) {
                unsaveBtn.click();
            }
            Log.d(TAG, "disconnectWifi: wifi是连接的，正在断开。。。");
        } else {
            Log.d(TAG, "disconnectWifi: wifi本身是断开的！");
        }

        UiScrollable scrollViewScr = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));
        UiObject wifiObj = scrollViewScr.getChildByText(new UiSelector()
                .className("android.widget.TextView"), "WIFI");
        UiObject connectObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wifi_list_item_desc"));
        assertEquals(false, connectObj.exists());
    }

    /**
     * @throws Exception
     */
    public static void disconnectWifiAll() throws UiObjectNotFoundException {
        UiScrollable settingsItem = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));

        settingsItem.scrollToBeginning(50);
        UiObject statusObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/wifi_list_item_desc"));
        while (statusObj.waitForExists(5000)){
            statusObj.click();
            UiObject unsaveBtn = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/dialog_forget"));
            if (unsaveBtn.waitForExists(3000) && unsaveBtn.isEnabled()) {
                unsaveBtn.click();
            }
            Log.d(TAG, "disconnectWifi: wifi是连接的，正在断开。。。");
        }

        UiScrollable scrollViewScr = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));
        UiObject wifiObj = scrollViewScr.getChildByText(new UiSelector()
                .className("android.widget.TextView"), "WIFI");
        UiObject connectObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wifi_list_item_desc"));
        assertEquals(false, connectObj.exists());
    }

    /**
     * 连接某个wifi热点
     * @param wifiNameStr     wifi名称
     * @param wifiPasswordStr wifi密码
     * @throws Exception
     */
    public static void connectWifi(String wifiNameStr, String wifiPasswordStr) throws UiObjectNotFoundException {
//        UiAutoLibs.scrollClassFindObjectByText("android.widget.ScrollView", wifiNameStr);

        UiScrollable settingsItem = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));
        UiObject about = settingsItem.getChildByText(new UiSelector()
                .className("android.widget.TextView"), wifiNameStr);
        about.click();
        mUiDevice.findObject(By.res("com.android.settings:id/wifi_con_dialog_password"))
                .setText(wifiPasswordStr);
        mUiDevice.pressBack();
        UiObject okBtn = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wifi_con_dialog_confirm"));
        if (okBtn.waitForExists(3000) && okBtn.isEnabled()) {
            okBtn.click();
        }
        settingsItem.scrollToBeginning(5);
        UiObject connectStatusObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wifi_list_item_desc"));
        assertEquals("assert wifi connect ok ",true, connectStatusObj.waitForExists(5000));
    }
    public static void connectWifiNoPassword(String wifiNameStr) throws UiObjectNotFoundException {
//        UiAutoLibs.scrollClassFindObjectByText("android.widget.ScrollView", wifiNameStr);
        boolean isOk = false;

        UiScrollable settingsItem = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));
        UiObject about = settingsItem.getChildByText(new UiSelector()
                .className("android.widget.TextView"), wifiNameStr);
        about.click();//点击该wifi名称连接
        Log.d(TAG, "connectWifiNoPassword: child = " + about.getFromParent(new UiSelector().resourceId("com.android.settings:id/wifi_list_item_desc")).getText());

        settingsItem.scrollToBeginning(5);
        UiObject connectStatusObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wifi_list_item_desc"));
        assertEquals(true, connectStatusObj.waitForExists(5000));

        UiObject itemNameObj = connectStatusObj.getFromParent(new UiSelector().resourceId("com.android.settings:id/wifi_list_item_name"));
        Log.d(TAG, "connectWifiNoPassword: connecded name = " + itemNameObj.getText());
        assertEquals("assert connected is " + wifiNameStr, wifiNameStr, itemNameObj.getText());
    }

    public static void wifiOffAndOnAutoConnect(long testTimes, long timeout) throws Exception {

        //获取参数
//        String wifiNameStr = bundle.getString("WifiName");
//        String wifiPasswordStr = bundle.getString("WifiPassword");
//        Log.d(TAG, "receive WifiName: " + wifiNameStr);
//        Log.d(TAG, "receive WifiPassword: " + wifiPasswordStr);
        boolean autoConnectOk = false;
        boolean keepTest = true;
        long testCounter = 0;
        while(keepTest){
            testCounter ++;
            Log.d(TAG, "wifiOffAndOnAutoConnect: 正在进行第" + testCounter + "次测试...");
            turnOffWifi();
            turnOnWifi(5);
            //指定时间
            long startMills = SystemClock.uptimeMillis();
            long currentMills = 0;
            while(currentMills <= timeout){
                currentMills = SystemClock.uptimeMillis() - startMills;
                if(timeout > 0) {
                    SystemClock.sleep(100);
                }
                UiObject connectStatusObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.android.settings:id/wifi_list_item_desc"));
                if (connectStatusObj.getText().contains("已连接")) {
                    autoConnectOk = true;
                    Log.d(TAG, "wifiOffAndOnAutoConnect: 自动连接成功");
                    currentMills = timeout + 1;
                }
            }
            assertEquals("自动连接断言", true, autoConnectOk);
            autoConnectOk = false;
            if (testCounter == testTimes){
                keepTest = false;
            }
        }
    }
    /**
     * 判断wifi连接，并且wifi开关为打开
     * */
    public static void judgeWifiConnect( long timeout) throws Exception {

        //获取参数
//        String wifiNameStr = bundle.getString("WifiName");
//        String wifiPasswordStr = bundle.getString("WifiPassword");
//        Log.d(TAG, "receive WifiName: " + wifiNameStr);
//        Log.d(TAG, "receive WifiPassword: " + wifiPasswordStr);
        boolean autoConnectOk = false;
        //指定时间
        long startMills = SystemClock.uptimeMillis();
        long currentMills = 0;
        while(currentMills <= timeout){
            currentMills = SystemClock.uptimeMillis() - startMills;
            if(timeout > 0) {
                SystemClock.sleep(100);
            }
            UiObject connectStatusObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/wifi_list_item_desc"));
            UiObject connectSwitch = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/wifi_switch"));
            if (connectStatusObj.getText().contains("已连接") && connectSwitch.isChecked()) {
                autoConnectOk = true;
                Log.d(TAG, "judgeWifiConnect: wifi当前是连接着的");
                currentMills = timeout + 1;
            }
        }
        assertEquals("是否连接断言", true, autoConnectOk);
        autoConnectOk = false;
    }

    /**
     * 打开wifi
     * @throws Exception
     */
    public static void turnOnWifi(long delaySecond) throws Exception {
        UiScrollable scrollViewScr = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));
        UiObject wifiObj = scrollViewScr.getChildByText(new UiSelector()
                .className("android.widget.TextView"), "WIFI");

        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getContext()
                .getSystemService(Context.WIFI_SERVICE);
        UiObject wifiSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wifi_switch"));
        Log.d(TAG, "turnOnWifi: default wifi switch = " + wifiManager.isWifiEnabled());
        if (!wifiManager.isWifiEnabled()) {
            Log.d(TAG, "turnOnWifi: wifi为关闭,正在打开...");
            if (UiAutoLibs.clickSwitchMultiBtn(2, 2, wifiSwitchObj)) {
                Log.d(TAG, "click: ok");
            } else {
                Log.d(TAG, "click: fail");
            }
        } else {
            Log.d(TAG, "turnOnWifi: wifi已为打开状态！");
        }

        boolean isOk = false;
        //指定时间
        long startMills = SystemClock.uptimeMillis();
        long currentMills = 0;
        long timeout = 1000 * delaySecond;
        while(currentMills <= timeout){
            currentMills = SystemClock.uptimeMillis() - startMills;
            if(timeout > 0) {
                SystemClock.sleep(300);
            }
            if (wifiManager.isWifiEnabled()) {
                isOk = true;
                Log.d(TAG, "turnOnWifi: 打开成功");
                currentMills = timeout + 1;
                Log.d(TAG, "turnOnWifi: 打开到打开成功耗时="
                        + Utils.millisToTime(SystemClock.uptimeMillis() - startMills));
            }
        }
        assertEquals(true, wifiManager.isWifiEnabled());
    }

    /**
     * 关闭wifi
     * @throws Exception
     */
    public static void turnOffWifi() throws Exception {
        UiScrollable scrollViewScr = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));
        UiObject wifiObj = scrollViewScr.getChildByText(new UiSelector()
                .className("android.widget.TextView"), "WIFI");

        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getContext()
                .getSystemService(Context.WIFI_SERVICE);
        UiObject wifiSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wifi_switch"));
        if (wifiManager.isWifiEnabled()) {
            Log.d(TAG, "turnOnWifi: wifi开关为打开状态，正在关闭...");
            if (UiAutoLibs.clickSwitchMultiBtn(2, 1, wifiSwitchObj)) {
                Log.d(TAG, "click: ok");
            } else {
                Log.d(TAG, "click: fail");
            }
        } else {
            Log.d(TAG, "turnOnWifi: wifi已为关闭状态！");
        }
        SystemClock.sleep(5000);
        assertEquals(false, wifiManager.isWifiEnabled());
    }

    /**
     * @throws UiObjectNotFoundException
     */
    public static void turnOnBT(long delaySecond) throws UiObjectNotFoundException {
        //com.android.settings:id/bt_power_switch
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        UiObject btSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/bt_power_switch"));
        if (bluetoothAdapter != null){
            Log.d(TAG, "turnOnBT: bluetoothAdapter is Enable = " + bluetoothAdapter.isEnabled());
            if (bluetoothAdapter.isEnabled()){
                Log.d(TAG, "turnOnBT: 蓝牙本身是打开的");
            } else {
                Log.d(TAG, "turnOnBT: 蓝牙未打开，正在打开...");
                UiAutoLibs.clickSwitchMultiBtn(2,2, btSwitchObj);
            }
            boolean isOk = false;
            //指定时间
            long startMills = SystemClock.uptimeMillis();
            long currentMills = 0;
            long timeout = 1000 * delaySecond;
            BluetoothAdapter newAdapter = BluetoothAdapter.getDefaultAdapter();
            while(currentMills <= timeout){
                currentMills = SystemClock.uptimeMillis() - startMills;
                if(timeout > 0) {
                    SystemClock.sleep(300);
                }
                if (newAdapter.isEnabled()) {
                    isOk = true;
                    Log.d(TAG, "turnBTAutoConnect: 打开成功");
                    currentMills = timeout + 1;
                    Log.d(TAG, "turnOnBT: 打开蓝牙开关到打开成功耗时="
                            + Utils.millisToTime(SystemClock.uptimeMillis() - startMills));
                }
            }
            assertEquals("打开蓝牙结果", true, newAdapter.isEnabled());
        } else {
            throw new UiObjectNotFoundException("蓝牙适配器为空");
        }
    }

    public static void turnOffBT() throws UiObjectNotFoundException {
        //com.android.settings:id/bt_power_switch
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        UiObject btSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/bt_power_switch"));
        if (bluetoothAdapter != null){
            if (!bluetoothAdapter.isEnabled()){
                Log.d(TAG, "turnOnBT: 蓝牙本身是关闭的");
            } else {
                Log.d(TAG, "turnOnBT: 蓝牙未关闭，正在关闭...");
                UiAutoLibs.clickSwitchMultiBtn(2,1, btSwitchObj);
            }
            UiObject autoConnectObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/bt_auto_con_switch"));
            SystemClock.sleep(3000);
            BluetoothAdapter newAdapter = BluetoothAdapter.getDefaultAdapter();
            assertEquals(false, newAdapter.isEnabled());
        } else {
            throw new UiObjectNotFoundException("蓝牙适配器为空");
        }
    }

    public static void turnBTAutoConnect(boolean onOff, long timeout) throws UiObjectNotFoundException {
        UiObject btAutoSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/bt_auto_con_switch"));
        if (onOff){ //true为打开
            if (btAutoSwitchObj.isChecked()){
                Log.d(TAG, "turnBTAutoConnect: 本身是打开的，不做操作");
            } else {
                Log.d(TAG, "turnBTAutoConnect: 本身是关闭的，正在打开");
                btAutoSwitchObj.click();
            }
        } else {
            if (btAutoSwitchObj.isChecked()){
                Log.d(TAG, "turnBTAutoConnect: 本身是打开的，正在关闭");
                btAutoSwitchObj.click();
            } else {
                Log.d(TAG, "turnBTAutoConnect: 本身是关闭的，不做操作");
            }
        }
        boolean isOk = false;
        //指定时间
        long startMills = SystemClock.uptimeMillis();
        long currentMills = 0;
        while(currentMills <= timeout){
            currentMills = SystemClock.uptimeMillis() - startMills;
            if(timeout > 0) {
                SystemClock.sleep(100);
            }
            btAutoSwitchObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/bt_auto_con_switch"));
            if ((onOff == true && btAutoSwitchObj.isChecked() == true) || (onOff == false && btAutoSwitchObj.isChecked() == false)) {
                isOk = true;
                Log.d(TAG, "turnBTAutoConnect: 打开成功");
                currentMills = timeout + 1;
            }
        }

        assertEquals("打开结果：", onOff, btAutoSwitchObj.isCheckable());

    }

    public static void judgeBtConnected(long timeout) throws UiObjectNotFoundException {
        UiScrollable scrollable = new UiScrollable(new UiSelector().className("android.widget.ScrollView"));
        scrollable.flingToEnd(5);
//        UiObject showStatusObj = mUiDevice.findObject(new UiSelector()
//                .resourceId("com.android.settings:id/img_bt_devices"));
//        showStatusObj.waitForExists(3000);
//        showStatusObj.click();
        boolean isOk = false;
        //指定时间
        long startMills = SystemClock.uptimeMillis();
        long currentMills = 0;
        while(currentMills <= timeout){
            currentMills = SystemClock.uptimeMillis() - startMills;
            if(timeout > 0) {
                SystemClock.sleep(100);
            }
            UiObject targetObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/tv_discon"));
            if (targetObj.getText().equals("已连接") || targetObj.getText().equals("Connected")) {
                isOk = true;
                Log.d(TAG, "judgeBtConnected: 连接成功");
                currentMills = timeout + 1;
            }
        }
        assertEquals("连接结果：", true, isOk);
    }

    public static void turnOffBWhite() throws UiObjectNotFoundException {
        //com.android.settings:id/bt_power_switch
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null){
            bluetoothAdapter.disable();
            SystemClock.sleep(5000);
            BluetoothAdapter newAdapter = BluetoothAdapter.getDefaultAdapter();
            assertEquals(false, newAdapter.isEnabled());
        } else {
            throw new UiObjectNotFoundException("蓝牙适配器为空");
        }
    }

    public static void switchTimeTo_12H() throws UiObjectNotFoundException{
        boolean is24HourMode = DateFormat.is24HourFormat(appContext);
        if (is24HourMode) {
            Log.d(TAG, "switchTimeTo_12H: 当前是24小时制，正转为12小时制...");
            UiObject targetObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/hour_switch"));
            UiAutoLibs.clickSwitchMultiBtn(2,1, targetObj);
            SystemClock.sleep(5000);
            assertEquals(false, DateFormat.is24HourFormat(appContext));
        } else {
            Log.d(TAG, "switchTimeTo_12H: 本身是12小时制");
        }
    }
    public static void switchTimeTo_24H() throws UiObjectNotFoundException{
        boolean is24HourMode = DateFormat.is24HourFormat(appContext);
        if (!is24HourMode) {
            Log.d(TAG, "switchTimeTo_12H: 当前是12小时制，正转为24小时制...");
            UiObject targetObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/hour_switch"));
            UiAutoLibs.clickSwitchMultiBtn(2,2, targetObj);
            SystemClock.sleep(5000);
            assertEquals(true, DateFormat.is24HourFormat(appContext));
        } else {
            Log.d(TAG, "switchTimeTo_12H: 本身是24小时制");
        }
    }

    public static void switchLanguageToChinese() throws UiObjectNotFoundException{
        String locale = Locale.getDefault().getLanguage();//英文en ，中文zh
        Log.d(TAG, "switchLanguageToChinese: " + locale);
        if (locale.equals("zh")) {
            Log.d(TAG, "switchLanguageToChinese: 本身已是中文语言");
        } else if (locale.equals("en")) {
            Log.d(TAG, "switchLanguageToChinese: 本身是英文，正切换到中文...");
            UiObject targetObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/language_switch"));
            UiAutoLibs.clickSwitchMultiBtn(2,1, targetObj);
            SystemClock.sleep(2000);
            assertEquals("zh", Locale.getDefault().getLanguage());
        }
    }

    public static void switchLanguageToEnglish() throws UiObjectNotFoundException{
        String locale = Locale.getDefault().getLanguage();//英文en ，中文zh
        Log.d(TAG, "switchLanguageToChinese: " + locale);
        if (locale.equals("en")) {
            Log.d(TAG, "switchLanguageToChinese: 本身已是英文语言");
        } else if (locale.equals("zh")) {
            Log.d(TAG, "switchLanguageToChinese: 本身是中文，正切换到英文...");
            UiObject targetObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/language_switch"));
            UiAutoLibs.clickSwitchMultiBtn(2,2, targetObj);
            SystemClock.sleep(2000);
            assertEquals("en", Locale.getDefault().getLanguage());
        }
    }

    public static void turnVoiceAllSwitchOn() throws UiObjectNotFoundException{
        UiObject voiceWakeupObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wakeup_switch"));
        UiObject recognitionObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/recognition_switch"));
        UiObject commandObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/command_switch"));
        UiObject moreObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/more_switch"));
        UiObject voiceLauncherObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/voice_launcher_switch"));
        if (!voiceWakeupObj.isChecked()){
            voiceWakeupObj.click();
            SystemClock.sleep(1000);
        }
        if (!recognitionObj.isChecked()){
            recognitionObj.click();
        }
        if (!commandObj.isChecked()){
            commandObj.click();
        }
        if (!moreObj.isChecked()){
            moreObj.click();
        }
        if (!voiceLauncherObj.isChecked()){
            voiceLauncherObj.click();
        }

        assertEquals("voiceWakeupObj.isChecked()", true, voiceWakeupObj.isChecked());
        assertEquals("recognitionObj.isChecked()",true, recognitionObj.isChecked());
        assertEquals("commandObj.isChecked()",true, commandObj.isChecked());
        assertEquals("moreObj.isChecked()",true, moreObj.isChecked());
        assertEquals("voiceLauncherObj.isChecked()",true, voiceLauncherObj.isChecked());
    }

    public static void turnVoiceAllSwitchOff() throws UiObjectNotFoundException{
        UiObject voiceWakeupObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wakeup_switch"));
        UiObject recognitionObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/recognition_switch"));
        UiObject commandObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/command_switch"));
        UiObject moreObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/more_switch"));
        UiObject voiceLauncherObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/voice_launcher_switch"));
        if (voiceWakeupObj.isChecked()){
            voiceWakeupObj.click();
            SystemClock.sleep(1000);
        }
        if (recognitionObj.isChecked()){
            recognitionObj.click();
        }
        if (commandObj.isChecked()){
            commandObj.click();
        }
        if (moreObj.isChecked()){
            moreObj.click();
        }
        if (voiceLauncherObj.isChecked()){
            voiceLauncherObj.click();
        }
        assertEquals("voiceWakeupObj.isChecked()", false, voiceWakeupObj.isChecked());
        assertEquals("recognitionObj.isChecked()",false, recognitionObj.isChecked());
        assertEquals("commandObj.isChecked()",false, commandObj.isChecked());
        assertEquals("moreObj.isChecked()",false, moreObj.isChecked());
        assertEquals("voiceLauncherObj.isChecked()",false, voiceLauncherObj.isChecked());
    }

    public static void btAutoAcceptCall(boolean isAuto) throws UiObjectNotFoundException{
        if (isAuto) {
            UiObject autoBtnObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/bt_auto_answer_switch"));
            UiAutoLibs.clickSwitchMultiBtn(2, 2, autoBtnObj);
        } else {
            UiObject autoBtnObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/bt_auto_answer_switch"));
            UiAutoLibs.clickSwitchMultiBtn(2, 1, autoBtnObj);
        }
    }

    public static void btRingSelect(int ringType) throws UiObjectNotFoundException {
        UiObject ringTypeObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/bt_bell_switch"));
        switch (ringType) {
            case 1:
                Log.d(TAG, "btRingSelect: 1");
                UiAutoLibs.clickSwitchMultiBtn(4, 1, ringTypeObj);
                break;
            case 2:
                Log.d(TAG, "btRingSelect: 2");
                UiAutoLibs.clickSwitchMultiBtn(4, 2, ringTypeObj);
                break;
            case 3:
                Log.d(TAG, "btRingSelect: 3");
                UiAutoLibs.clickSwitchMultiBtn(4, 3, ringTypeObj);
                break;
            case 4:
                Log.d(TAG, "btRingSelect: 4");
                UiAutoLibs.clickSwitchMultiBtn(4, 4, ringTypeObj);
                break;
            default:
                Log.d(TAG, "btRingSelect: 输入了错误的铃声（1-4）");
                break;
        }
    }

    public static void timeTabGPS_Sync(boolean onOff) throws UiObjectNotFoundException{
        UiObject gpsBtnOjb = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/gps_switch"));
        if (onOff) {
            if (gpsBtnOjb.isChecked()) {
                Log.d(TAG, "timeTabGPS_Sync: 本身是打开的！");
            } else {
                gpsBtnOjb.click();
                SystemClock.sleep(500);
                gpsBtnOjb = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.android.settings:id/gps_switch"));
                assertEquals("timeTabGPS_Sync On reault:", true, gpsBtnOjb.isChecked());
            }
        } else {//关闭
            if (!gpsBtnOjb.isChecked()) {
                Log.d(TAG, "timeTabGPS_Sync: 本身是关闭的！");
            } else {
                gpsBtnOjb.click();
                SystemClock.sleep(500);
                gpsBtnOjb = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.android.settings:id/gps_switch"));
                assertEquals("timeTabGPS_Sync On reault:", false, gpsBtnOjb.isChecked());
            }
        }
    }

    public static void displayLockScreenType(int type) throws UiObjectNotFoundException{
        UiObject lockType = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/lock_switch"));
        switch (type) {
            case 1:
                Log.d(TAG, "displayLockScreenType: 1");
                UiAutoLibs.clickSwitchMultiBtn(3, 1, lockType);
                break;
            case 2:
                Log.d(TAG, "displayLockScreenType: 2");
                UiAutoLibs.clickSwitchMultiBtn(3, 2, lockType);
                break;
            case 3:
                Log.d(TAG, "displayLockScreenType: 3");
                UiAutoLibs.clickSwitchMultiBtn(3, 3, lockType);
                break;
            default:
                Log.d(TAG, "displayLockScreenType: 输入了错误行车锁屏类型（1-3）");
                break;
        }
    }

    public static void displayDayNightMode(int type) throws UiObjectNotFoundException{
        UiObject lockType = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/light_model"));
        switch (type) {
            case 1:
                Log.d(TAG, "displayDayNightMode: 1");
                UiAutoLibs.clickSwitchMultiBtn(3, 1, lockType);
                break;
            case 2:
                Log.d(TAG, "displayDayNightMode: 2");
                UiAutoLibs.clickSwitchMultiBtn(3, 2, lockType);
                break;
            case 3:
                Log.d(TAG, "displayDayNightMode: 3");
                UiAutoLibs.clickSwitchMultiBtn(3, 3, lockType);
                break;
            default:
                Log.d(TAG, "displayDayNightMode: 输入了错误昼夜模式（1-3）");
                break;
        }
    }

    public static void displaySeekLightTo(int type) throws UiObjectNotFoundException{
        UiObject lockType = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/light_seekBar"));
        Log.d(TAG, "displaySeekLightTo: seek to " + type);
        UiAutoLibs.clickSeekBar(10, type, lockType);
    }

    public static void displayThemeSelect(int select) throws UiObjectNotFoundException{
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/theme"));
        switch (select) {
            case 1:
                Log.d(TAG, "displayThemeSelect: 1");
                UiAutoLibs.clickSwitchMultiBtn(2, 1, targetObj);
                break;
            case 2:
                Log.d(TAG, "displayThemeSelect: 2");
                UiAutoLibs.clickSwitchMultiBtn(2, 2, targetObj);
                break;
            default:
                Log.d(TAG, "displayThemeSelect: 输入了错误主题模式（1-2）");
                break;
        }
    }

    public static void displayThreamColorsSelect(int select) throws UiObjectNotFoundException{
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/color"));
        switch (select) {
            case 1:
                Log.d(TAG, "displayThreamColorsSelect: 1");
                UiAutoLibs.clickSwitchMultiBtn(3, 1, targetObj);
                break;
            case 2:
                Log.d(TAG, "displayThreamColorsSelect: 2");
                UiAutoLibs.clickSwitchMultiBtn(3, 2, targetObj);
                break;
            case 3:
                Log.d(TAG, "displayThreamColorsSelect: 3");
                UiAutoLibs.clickSwitchMultiBtn(3, 3, targetObj);
                break;
            default:
                Log.d(TAG, "displayThreamColorsSelect: 输入了错误肤色（1-3）");
                break;
        }
    }

    public static void soundShowEffects() throws UiObjectNotFoundException {
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/low_seekbar"));
        if (!targetObj.exists()){
            mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/sound_effects")).click();
        }
    }

    public static void soundHideEffects() throws UiObjectNotFoundException {
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/low_seekbar"));
        if (targetObj.exists()){
            mUiDevice.findObject(new UiSelector()
                    .resourceId("com.android.settings:id/sound_effects")).click();
        }
    }
    /**
     * 低音调节
     * @param seekTo 范围（-6 ， 6）
     * @throws UiObjectNotFoundException
     */
    public static void soundLowSeekBar(int seekTo) throws UiObjectNotFoundException{
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/low_seekbar"));
        UiObject dtcsBtnObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/DTCS_sound"));
        if (targetObj.exists()){
            if (dtcsBtnObj.isChecked()){
                dtcsBtnObj.click();
                SystemClock.sleep(500);
                assertEquals("dtcs button is checked!", false, dtcsBtnObj.isChecked());
            }
            UiAutoLibs.clickSeekBar(13, (seekTo+7), targetObj);
        }
    }

    /**
     * 中音调节
     * @param seekTo 范围（-6 ， 6）
     * @throws UiObjectNotFoundException
     */
    public static void soundMiddleSeekBar(int seekTo) throws UiObjectNotFoundException{
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/middle_seekbar"));
        UiObject dtcsBtnObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/DTCS_sound"));
        if (targetObj.exists()){
            if (dtcsBtnObj.isChecked()){
                dtcsBtnObj.click();
                SystemClock.sleep(500);
                assertEquals("dtcs button is checked!", false, dtcsBtnObj.isChecked());
            }
            UiAutoLibs.clickSeekBar(13, (seekTo+7), targetObj);
        }
    }

    /**
     * 高音调节
     * @param seekTo 范围（-6 ， 6）
     * @throws UiObjectNotFoundException
     */
    public static void soundHighSeekBar(int seekTo) throws UiObjectNotFoundException{
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/hight_seekbar"));
        UiObject dtcsBtnObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/DTCS_sound"));
        if (targetObj.exists()){
            if (dtcsBtnObj.isChecked()){
                dtcsBtnObj.click();
                SystemClock.sleep(500);
                assertEquals("dtcs button is checked!", false, dtcsBtnObj.isChecked());
            }
            UiAutoLibs.clickSeekBar(13, (seekTo+7), targetObj);
        }
    }

    public static void soundVolumFollowSpeed(int select) throws UiObjectNotFoundException{
        UiObject targetObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sound_speed"));
        switch (select) {
            case 1:
                Log.d(TAG, "soundVolumFollowSpeed: 1");
                UiAutoLibs.clickSwitchMultiBtn(4, 1, targetObj);
                break;
            case 2:
                Log.d(TAG, "soundVolumFollowSpeed: 2");
                UiAutoLibs.clickSwitchMultiBtn(4, 2, targetObj);
                break;
            case 3:
                Log.d(TAG, "soundVolumFollowSpeed: 3");
                UiAutoLibs.clickSwitchMultiBtn(4, 3, targetObj);
                break;
            case 4:
                Log.d(TAG, "soundVolumFollowSpeed: 3");
                UiAutoLibs.clickSwitchMultiBtn(4, 4, targetObj);
                break;
            default:
                Log.d(TAG, "soundVolumFollowSpeed: 输入了错误音量随速（1-4：关闭，高，中，低）");
                break;
        }
    }

    /**
     * 判断是否是TOD界面
     * */
    public static void judgeIsTodPage(boolean isTod, long timeout) throws UiObjectNotFoundException {
        //dsv.carstate.service:id/circle_mode
        boolean isOk = false;
        //指定时间
        long startMills = SystemClock.uptimeMillis();
        long currentMills = 0;
        while(currentMills <= timeout){
            currentMills = SystemClock.uptimeMillis() - startMills;
            if(timeout > 0) {
                SystemClock.sleep(100);
            }
            UiObject targetObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("dsv.carstate.service:id/circle_mode"));
            if (isTod) {//如果判断的是TOD存在
                if (targetObj.exists()) {
                    isOk = true;
                    Log.d(TAG, "judgeIsTodPage: circle_mode存在");
                    currentMills = timeout + 1;
                }
            } else {
                if (!targetObj.exists()) {
                    isOk = true;
                    Log.d(TAG, "judgeIsTodPage: circle_mode不存在");
                    currentMills = timeout + 1;
                }
            }
        }
        assertEquals("判断是否是TOD界面结果(" + isTod  + "):", true, isOk);
    }

    public static void judgeSource(String checkSource) throws UiObjectNotFoundException {
        Log.d(TAG, "judgeSource: judgeSource = " + checkSource);
        String sourceFlagStr = "";
        UiObject sourceFlagObj;
        boolean isOk = false;
        switch (checkSource) {
            case FM:
                Log.d(TAG, "judgeSource: 开始判断FM");
                sourceFlagStr = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desay_svautomotive.svradio:id/radio_band_channel")).getText();
                assertEquals("断言FM失败，", true, sourceFlagStr.startsWith("M"));
                break;
            case AM:
                Log.d(TAG, "judgeSource: 开始判断AM");
                sourceFlagStr = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desay_svautomotive.svradio:id/radio_band_channel")).getText();
                assertEquals("断言AM失败，", true, sourceFlagStr.startsWith("k"));
                break;
            case NET_FM:
                sourceFlagStr = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desay_svautomotive.svradio:id/radio_band_channel")).getText();
                break;
            case USB1_MUSIC:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_music_hall"));
                sourceFlagStr = sourceFlagObj.getText();
                assertEquals("断言USB1音乐被选中失败，", true, sourceFlagStr.contains(USB1_MUSIC));
                break;
            case USB2_MUSIC:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_music_hall"));
                sourceFlagStr = sourceFlagObj.getText();
                assertEquals("断言USB2音乐被选中失败，", true, sourceFlagStr.contains(USB2_MUSIC));
                break;
            case BT_MUSIC:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_music_hall"));
                sourceFlagStr = sourceFlagObj.getText();
                isOk = false;
                if (sourceFlagStr.contains("BT") || sourceFlagStr.contains("蓝牙")) {
                    isOk = true;
                }
                assertEquals("断言蓝牙音乐被选中失败，", true, isOk);
                break;
            case IPOD_MUSIC:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_music_hall"));
                assertEquals("断言音乐被选中失败，", true, sourceFlagObj.isSelected());
                sourceFlagStr = sourceFlagObj.getText();
                isOk = false;
                if (sourceFlagStr.contains("IPOD")) {
                    isOk = true;
                }
                assertEquals("断言IPOD音乐被选中失败，", true, isOk);
                break;
            case ONLINE_MUSIC:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_music_hall"));
                sourceFlagStr = sourceFlagObj.getText();
                Log.d(TAG, "judgeSource: sourceFlagStr = " + sourceFlagStr);
                isOk = false;
                if (sourceFlagStr.contains("OnlineMusic") || sourceFlagStr.contains("在线音乐")) {
                    isOk = true;
                }
                assertEquals("断言在线音乐被选中失败，", true, isOk);
                break;
            case USB1_VIDEO:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_vedio"));
                sourceFlagStr = sourceFlagObj.getText();
                Log.d(TAG, "judgeSource: sourceFlagStr = " + sourceFlagStr);
                isOk = false;
                if (sourceFlagStr.contains("USB1")) {
                    isOk = true;
                }
                assertEquals("断言USB1视频被选中失败，", true, isOk);
                break;
            case USB2_VIDEO:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_vedio"));
                sourceFlagStr = sourceFlagObj.getText();
                Log.d(TAG, "judgeSource: sourceFlagStr = " + sourceFlagStr);
                isOk = false;
                if (sourceFlagStr.contains("USB2")) {
                    isOk = true;
                }
                assertEquals("断言USB2视频被选中失败，", true, isOk);
                break;
            case USB1_PICTURE:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_picture"));
                sourceFlagStr = sourceFlagObj.getText();
                Log.d(TAG, "judgeSource: sourceFlagStr = " + sourceFlagStr);
                isOk = false;
                if (sourceFlagStr.contains("USB1")) {
                    isOk = true;
                }
                assertEquals("断言USB1图片被选中失败，", true, isOk);
                break;
            case USB2_PICTURE:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_picture"));
                sourceFlagStr = sourceFlagObj.getText();
                Log.d(TAG, "judgeSource: sourceFlagStr = " + sourceFlagStr);
                isOk = false;
                if (sourceFlagStr.contains("USB2")) {
                    isOk = true;
                }
                assertEquals("断言USB2图片被选中失败，", true, isOk);
                break;

            default:
                assertEquals("没有该source失败，", true, false);
                break;

        }
    }

    public static void timeModeCheck() throws UiObjectNotFoundException{
        boolean isOk = false;
        UiObject timeMode12Btn = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_12"));
        UiObject timeMode24Btn = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_24"));
        if ((timeMode12Btn.isChecked() || timeMode24Btn.isChecked()) && (timeMode12Btn.isChecked() && timeMode24Btn.isChecked()) == false){
            isOk = true;
            Log.d(TAG, "timeModeCheck: one of mode is selected");
        } else {
            isOk = false;
            Log.d(TAG, "timeModeCheck: no time  mode is selected");
        }
        assertEquals("CheckTimeModeAssert", true, isOk);
        assertEquals("Contains 12", true, timeMode12Btn.getText().contains("12"));
        assertEquals("Contains 24", true, timeMode24Btn.getText().contains("24"));

    }

    public static void timeModeChange() throws UiObjectNotFoundException{
        boolean isOk = false;
        UiObject timeMode12Btn = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_12"));
        UiObject timeMode24Btn = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_24"));
        timeMode12Btn.click();
        UiObject timeDisplayObj =  mUiDevice .findObject(new UiSelector()
                        .resourceId("com.android.settings:id/time_display"));
        if (timeDisplayObj.getText().contains("AM") || timeDisplayObj.getText().contains("PM")){
            isOk = true;
        } else if (timeDisplayObj.getText().contains("上午") || timeDisplayObj.getText().contains("下午")){
            isOk = true;
        } else {
            Log.d(TAG, "timeModeChange: Change to 12H mode fail");
            isOk = false;
        }
        assertEquals("Change 12H mode assert", true, isOk);

        //界面切换
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        if (timeDisplayObj.getText().contains("AM") || timeDisplayObj.getText().contains("PM")){
            isOk = true;
        } else if (timeDisplayObj.getText().contains("上午") || timeDisplayObj.getText().contains("下午")){
            isOk = true;
        } else {
            Log.d(TAG, "timeModeChange: Change to 12H mode fail");
            isOk = false;
        }
        assertEquals("assert 12H mode after switch page", true, isOk);


        timeMode24Btn.click();
        timeDisplayObj =  mUiDevice .findObject(new UiSelector()
                .resourceId("com.android.settings:id/time_display"));
        if (!timeDisplayObj.getText().contains("AM") && !timeDisplayObj.getText().contains("PM")
                && !timeDisplayObj.getText().contains("上午")
                && !timeDisplayObj.getText().contains("下午")){
            isOk = true;
        } else {
            Log.d(TAG, "timeModeChange: Change to 24H mode fail");
            isOk = false;
        }
        assertEquals("Change 24H mode assert", true, isOk);

        //界面切换
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        if (!timeDisplayObj.getText().contains("AM") && !timeDisplayObj.getText().contains("PM")
                && !timeDisplayObj.getText().contains("上午")
                && !timeDisplayObj.getText().contains("下午")){
            isOk = true;
        } else {
            Log.d(TAG, "timeModeChange: Change to 24H mode fail");
            isOk = false;
        }
        assertEquals("assert 24H mode after switch page", true, isOk);

    }

    public static void timeSettings() throws UiObjectNotFoundException {
        //在24小时制下调节
        mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_24")).click();

        UiObject gpsSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/gps_switch"));
        if (gpsSwitchObj.isChecked()) {
            Log.d(TAG, "timeSettings: gps sync had on, turn off");
            gpsSwitchObj.click();
        } else {
            Log.d(TAG, "timeSettings: gps sync had off");
        }
        assertEquals("gps off assert", false, gpsSwitchObj.isChecked() );
        String beforeSettingDate = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/date_display")).getText();
        String beforeSettingTime = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/time_display")).getText();
        Log.d(TAG, "timeSettings: beforeSettingTime = " + beforeSettingDate + " " + beforeSettingTime);

        UiObject timeSettingsOjb = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/time_settings"));
        timeSettingsOjb.click();

        //时间
        String yearStr = "";
        String monthStr = "";
        String dayStr = "";
        String hourStr = "";
        String minStr = "";
        //年
        //com.android.settings:id/datePicker
        UiCollection datePickColl = new UiCollection(new UiSelector()
                .resourceId("com.android.settings:id/datePicker"));
        UiObject yearOjb = datePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 0);
        Log.d(TAG, "timeSettings: yearOjb = " + yearOjb.getText());

        UiObject monthOjb = datePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 1);
        Log.d(TAG, "timeSettings: monthOjb = " + monthOjb.getText());

        UiObject dayOjb = datePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 2);
        Log.d(TAG, "timeSettings: dateOjb = " + dayOjb.getText());


        UiCollection timePickColl = new UiCollection(new UiSelector()
                .resourceId("com.android.settings:id/timePicker"));
        UiObject hourOjb = timePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 0);
        Log.d(TAG, "timeSettings: hourOjb = " + hourOjb.getText());
        UiObject minOjb = timePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 1);
        Log.d(TAG, "timeSettings: minOjb = " + minOjb.getText());

        //滚动年
        UiScrollable yearScrollable = new UiScrollable(new UiSelector()
                .className("android.widget.NumberPicker").instance(0));
        yearScrollable.setAsVerticalList();
        yearScrollable.scrollBackward();

        //滚动月
        UiScrollable monthScrollable = new UiScrollable(new UiSelector()
                .className("android.widget.NumberPicker").instance(1));
        monthScrollable.setAsVerticalList();
        monthScrollable.scrollBackward();

        //滚动日
        UiScrollable dayScrollable = new UiScrollable(new UiSelector()
                .className("android.widget.NumberPicker").instance(2));
        dayScrollable.setAsVerticalList();
        dayScrollable.scrollBackward();

        //滚动时
        UiScrollable hourScrollable = new UiScrollable(new UiSelector()
                .className("android.widget.NumberPicker").instance(3));
        hourScrollable.setAsVerticalList();
        hourScrollable.scrollBackward();

        //滚动分
        UiScrollable minScrollable = new UiScrollable(new UiSelector()
                .className("android.widget.NumberPicker").instance(4));
        minScrollable.setAsVerticalList();
        minScrollable.scrollBackward();

        //年
        yearOjb = datePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 0);
        Log.d(TAG, "timeSettings: New-yearOjb = " + yearOjb.getText());
        assertEquals("yearPick", false, yearOjb.getText().equals(yearStr));

        monthOjb = datePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 1);
        Log.d(TAG, "timeSettings: New-monthOjb = " + monthOjb.getText());
        assertEquals("monthPick", false, monthOjb.getText().equals(monthStr));

        dayOjb = datePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 2);
        Log.d(TAG, "timeSettings: New-dateOjb = " + dayOjb.getText());
        assertEquals("dayPick", false, dayOjb.getText().equals(dayStr));


        hourOjb = timePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 0);
        Log.d(TAG, "timeSettings: New-hourOjb = " + hourOjb.getText());
        assertEquals("hourPick", false, hourOjb.getText().equals(hourStr));
        minOjb = timePickColl.getChildByInstance(new UiSelector()
                .resourceId("android:id/numberpicker_input"), 1);
        Log.d(TAG, "timeSettings: New-minOjb = " + minOjb.getText());
        assertEquals("minPick", false, minOjb.getText().equals(minStr));

        //确定
        mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/time_confirm")).click();

        String afterSettingDate = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/date_display")).getText();
        String afterSettingTime = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/time_display")).getText();
        Log.d(TAG, "timeSettings: afterSettingTime = " + afterSettingDate + " " + afterSettingTime);
        assertEquals("DateSetAssert", false, afterSettingDate.equals(beforeSettingDate));
        assertEquals("TimeSetAssert", false, afterSettingTime.equals(beforeSettingTime));

        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);

        String switchPageSettingDate = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/date_display")).getText();
        String switchPageSettingTime = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/time_display")).getText();
        Log.d(TAG, "timeSettings: SwitchPageSettinTime = "
                + switchPageSettingDate + " " + switchPageSettingTime);
        assertEquals("SwitchDateSetAssert", true, afterSettingDate.equals(switchPageSettingDate));
        assertEquals("SwitchTimeSetAssert", true, afterSettingTime.equals(switchPageSettingTime));

    }

    public static void timeZoneSettings() throws UiObjectNotFoundException {
        UiObject gpsSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/gps_switch"));
        if (gpsSwitchObj.isChecked()) {
            Log.d(TAG, "timeZoneSettings: gps sync had on, turn off");
            gpsSwitchObj.click();
        } else {
            Log.d(TAG, "timeZoneSettings: gps sync had off");
        }
        assertEquals("gps off assert", false, gpsSwitchObj.isChecked() );

        String zoneNameStr = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/zone_name")).getText();
        Log.d(TAG, "timeZoneSettings: zoneNameStr = " + zoneNameStr);

        UiObject timeZoneSettingsOjb = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/zone_settings"));
        timeZoneSettingsOjb.click();//点击时区

        //滚动
        UiScrollable zoneList = new UiScrollable(new UiSelector()
                .resourceId("com.android.settings:id/wallpaper_wheelview"));
        zoneList.setAsVerticalList();
        zoneList.scrollForward();

        //获取中的时区名称
        UiObject zoneObj = new UiCollection(new UiSelector()
                .resourceId("com.android.settings:id/wallpaper_wheelview"))
                .getChildByInstance(new UiSelector()
                .resourceId("com.android.settings:id/item_name"), 2);
        Log.d(TAG, "timeZoneSettings: pickZon = " + zoneObj.getText());

        zoneObj.click();
        String afterNameStr = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/zone_name")).getText();
        Log.d(TAG, "timeZoneSettings: afterNameStr = " + zoneNameStr);
        assertEquals("assert after set Zone", false, afterNameStr.equals(zoneNameStr));

        //界面切换
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);

        //切换界面后时区不变
        String switchNameStr = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/zone_name")).getText();
        Log.d(TAG, "timeZoneSettings: switchNameStr = " + zoneNameStr);
        assertEquals("assert switchPage set Zone", afterNameStr, switchNameStr);

    }

    public static void gpsSyncOn() throws UiObjectNotFoundException {
        UiObject gpsSwitchObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/gps_switch"));
        if (gpsSwitchObj.isChecked()) {
            Log.d(TAG, "timeZoneSettings: gps sync had on");
        } else {
            Log.d(TAG, "timeZoneSettings: gps sync is off, turn on...");
            gpsSwitchObj.click();
        }
        assertEquals("gps on assert", true, gpsSwitchObj.isChecked());

        //界面切换
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        assertEquals("gps on assert", true, gpsSwitchObj.isChecked());

        //检查时间可否调节
        UiObject timeSettingsOjb = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/time_settings"));
        timeSettingsOjb.click();
        UiObject timePicker = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/time_confirm"));
        timePicker.waitForExists(3000);
        assertEquals("Assert can not seting time", false, timePicker.exists());

        //检查时区可否调节
        UiObject timeZoneSettingsOjb = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/zone_settings"));
        timeZoneSettingsOjb.click();
        UiObject zonePicker = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/wallpaper_wheelview"));
        zonePicker.waitForExists(3000);
        assertEquals("Assert can not seting zone", false, zonePicker.exists());
    }

    public static void systemLanguageCheck() throws UiObjectNotFoundException {
        boolean isOk = false;
        UiAutoLibs.scrollToBeginByClassName("android.widget.ScrollView");

        UiObject chineseObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_chinese"));
        UiObject englishObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_english"));
        Log.d(TAG, "systemLanguageCheck: " + chineseObj.getText() + ", " + englishObj.getText());

        if (chineseObj.isChecked()) {
            Log.d(TAG, "systemLanguageCheck: " + chineseObj.getText() + "is Selected");

        } else if (englishObj.isChecked()){
            Log.d(TAG, "systemLanguageCheck: " + englishObj.getText() + "is Selected");
        }
        if ((chineseObj.isChecked() || englishObj.isChecked())
                && (chineseObj.isChecked() && englishObj.isChecked()) == false){
            isOk = true;
            Log.d(TAG, "systemLanguageCheck: one of Language is selected");
        } else {
            isOk = false;
            Log.d(TAG, "systemLanguageCheck: no time  Language is selected");
        }
        assertEquals("systemLanguageCheck", true, isOk);
        assertEquals("Assert Chinese", true, chineseObj.getText().contains("简体中文"));
        assertEquals("Assert English", true, englishObj.getText().contains("English"));
    }

    public static void systemLanguageSwitch() throws UiObjectNotFoundException {
        UiAutoLibs.scrollToBeginByClassName("android.widget.ScrollView");

        UiObject chineseObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_chinese"));
        UiObject englishObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_english"));
        englishObj.click();//切换为英文
        SystemClock.sleep(3000);
        assertEquals("Assert Chinese not Select", false, chineseObj.isChecked());
        assertEquals("Assert English Select", true, englishObj.isChecked());

        UiObject netObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_wifi_id"));
        UiObject btObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.android.settings:id/sv_tab_bluetooth_id"));
        assertEquals("Assert Network English", "Network", netObj.getText());
        assertEquals("Assert BT English", "BT", btObj.getText());

        //切换界面
        mUiDevice.pressHome();
        navigateBarTo(SETTINGS_NAVBAR);
        intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);

        //检查是否仍是英文
        assertEquals("Assert Network English", "Network", netObj.getText());
        assertEquals("Assert BT English", "BT", btObj.getText());

        chineseObj.click();//切换为中文
        SystemClock.sleep(3000);
        assertEquals("Assert Chinese Select", true, chineseObj.isChecked());
        assertEquals("Assert English not Select", false, englishObj.isChecked());

        assertEquals("Assert Network Chinese", "网络", netObj.getText());
        assertEquals("Assert BT Chinese", "蓝牙", btObj.getText());

        //切换界面
        mUiDevice.pressHome();
        navigateBarTo(SETTINGS_NAVBAR);
        intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);

        //检查是否仍是中文
        assertEquals("Assert Network Chinese", "网络", netObj.getText());
        assertEquals("Assert BT Chinese", "蓝牙", btObj.getText());

    }

    public static void dayModeCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollToBeginByClassName("android.widget.ScrollView");
        //com.android.settings:id/sv_tab_day
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_day");
        assertEquals("assert Day mode is Selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_day"));

        UiObject brightnessValueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/light_number");
        Log.d(TAG, "dayModeCheck: Day default brightnessValue = " + brightnessValueObj.getText());

        mUiDevice.click(588,461);
        Log.d(TAG, "dayModeCheck: Day min brightnessValue = " + brightnessValueObj.getText());
        assertEquals("Assert Minimum" , "1", brightnessValueObj.getText());

        mUiDevice.click(1110,461);
        Log.d(TAG, "dayModeCheck: Day max brightnessValue = " + brightnessValueObj.getText());
        assertEquals("Assert Minimum" , "10", brightnessValueObj.getText());

        mUiDevice.click(936,461);
        Log.d(TAG, "dayModeCheck: Day 7 brightnessValue = " + brightnessValueObj.getText());
        assertEquals("Assert 7" , "7", brightnessValueObj.getText());

        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        assertEquals("assert Day mode is Selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_day"));
        assertEquals("Assert 7 after switch pages" , "7", brightnessValueObj.getText());
    }

    public static void nightModeCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollToBeginByClassName("android.widget.ScrollView");
        //com.android.settings:id/sv_tab_day
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_night");
        assertEquals("assert Night mode is Selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_night"));

        UiObject brightnessValueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/light_number");
        Log.d(TAG, "nightModeCheck: Night default brightnessValue = " + brightnessValueObj.getText());

        //596,461
        mUiDevice.click(588,461);
        Log.d(TAG, "nightModeCheck: Night min brightnessValue = " + brightnessValueObj.getText());
        assertEquals("Assert Minimum" , "1", brightnessValueObj.getText());

        mUiDevice.click(1110,461);
        Log.d(TAG, "nightModeCheck: Night max brightnessValue = " + brightnessValueObj.getText());
        assertEquals("Assert Minimum" , "10", brightnessValueObj.getText());

        mUiDevice.click(703,461);
        Log.d(TAG, "nightModeCheck: Night 3 brightnessValue = " + brightnessValueObj.getText());
        assertEquals("Assert 3" , "3", brightnessValueObj.getText());
        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        assertEquals("assert Night mode is Selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_night"));
        assertEquals("Assert 3 after switch pages" , "3", brightnessValueObj.getText());

    }
    public static void autoModeCheck() throws UiObjectNotFoundException {
        boolean isOK = false;

        UiAutoLibs.scrollToBeginByClassName("android.widget.ScrollView");
        //com.android.settings:id/sv_tab_day
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_auto");
        assertEquals("assert Night mode is Selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_auto"));

        UiObject brightnessValueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/light_number");
        String autoValueStr = brightnessValueObj.getText();
        Log.d(TAG, "autoModeCheck: Auto brightnessValue = " +autoValueStr );


        UiAutoLibs.clickById("com.android.settings:id/sv_tab_day");
        String dayValueStr = brightnessValueObj.getText();
        Log.d(TAG, "Day value = " +dayValueStr);


        UiAutoLibs.clickById("com.android.settings:id/sv_tab_night");
        String nightValueStr = brightnessValueObj.getText();
        Log.d(TAG, "Night value = " + nightValueStr);

        if (autoValueStr.equals(dayValueStr) || autoValueStr.equals(nightValueStr)) {
            isOK = true;
        } else {
            Log.d(TAG, "autoModeCheck: 自动亮度值与白天和黑夜都不一样");
        }
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_auto");
        assertEquals("assert auto", true, isOK);

        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        assertEquals("assert Night mode is Selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_auto"));
        assertEquals("Assert 3 after switch pages" , true, brightnessValueObj.getText().equals(autoValueStr));
    }

    public static void themeSettings() throws UiObjectNotFoundException {
        //滚动到底部
        UiAutoLibs.scrollToEndByClassName("android.widget.ScrollView");

        //选择魔幻
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_magic");
        //断言5秒内界面仍保持在显示设置不闪退，且魔幻被选中
        UiObject magicObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_magic");
        assertEquals("assert magic not gone", false,
                magicObj.waitUntilGone(5000));
        assertEquals("assert magic selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_magic"));

        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        UiAutoLibs.scrollToEndByClassName("android.widget.ScrollView");
        assertEquals("assert magic selected after switch pages", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_magic"));

        //选择经典
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_classic");
        //断言5秒内界面仍保持在显示设置不闪退，且经典被选中
        UiObject classicObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_classic");
        assertEquals("assert classic not gone", false,
                classicObj.waitUntilGone(5000));
        assertEquals("assert classic selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_classic"));
        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        UiAutoLibs.scrollToEndByClassName("android.widget.ScrollView");
        assertEquals("assert classic selected after switch pages", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_classic"));
    }

    public static void colorSettings() throws UiObjectNotFoundException {
        //滚动到底部
        UiAutoLibs.scrollToEndByClassName("android.widget.ScrollView");

        //选择魔幻
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_magic");
        //断言5秒内界面仍保持在显示设置不闪退，且魔幻被选中
        UiObject magicObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_magic");
        magicObj.waitUntilGone(3000);

        //选择金色五秒不闪退且保持选中
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_gold");
        UiObject goldObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_gold");
        assertEquals("assert gold not gone", false,
                goldObj.waitUntilGone(5000));
        assertEquals("assert gold selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_gold"));

        //选择红色五秒不闪退且保持选中
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_red");
        UiObject redObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_red");
        assertEquals("assert red not gone", false,
                redObj.waitUntilGone(5000));
        assertEquals("assert red selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_red"));

        //选择蓝色五秒不闪退且保持选中
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_blue");
        UiObject blueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_blue");
        assertEquals("assert blue not gone", false,
                blueObj.waitUntilGone(5000));
        assertEquals("assert blue selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_blue"));

        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        UiAutoLibs.scrollToEndByClassName("android.widget.ScrollView");
        assertEquals("assert classic selected after switch pages", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_magic"));
        assertEquals("assert blue selected after switch pages", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_blue"));

        //选择经典
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_classic");
        //断言5秒内界面仍保持在显示设置不闪退，且经典被选中
        UiObject classicObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_classic");
        classicObj.waitUntilGone(5000);

        //选择金色五秒不闪退且保持选中
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_gold");
        assertEquals("assert gold not gone", false,
                goldObj.waitUntilGone(5000));
        assertEquals("assert gold selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_gold"));

        //选择红色五秒不闪退且保持选中
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_red");
        assertEquals("assert red not gone", false,
                redObj.waitUntilGone(5000));
        assertEquals("assert red selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_red"));

        //选择蓝色五秒不闪退且保持选中
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_blue");
        assertEquals("assert blue not gone", false,
                blueObj.waitUntilGone(5000));
        assertEquals("assert blue selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_blue"));

    }

    public static void drivingLockScreenCheck() throws UiObjectNotFoundException {
        //滚动到顶部
        UiAutoLibs.scrollToBeginByClassName("android.widget.ScrollView");

        //关闭
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_off");
        assertEquals("assert 15km selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_off"));
        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        assertEquals("assert 15km selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_off"));

        //选择0km
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_0km");
        assertEquals("assert okm selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_0km"));
        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        assertEquals("assert 15km selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_0km"));

        //15km
        UiAutoLibs.clickById("com.android.settings:id/sv_tab_15km");
        assertEquals("assert 15km selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/sv_tab_15km"));

    }

    public static void turnOnDTS() throws UiObjectNotFoundException {
        boolean isOk = false;
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        //滚动到顶部
        UiAutoLibs.scrollToBeginById("com.android.settings:id/id_scrollView");

        //打开DTS
        UiObject dtsSwtichObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DTCS_sound");
        if (!dtsSwtichObj.isChecked()) {
            UiAutoLibs.clickById("com.android.settings:id/DTCS_sound");
        }
        assertEquals("assert 15km selected", true,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        //滚动到顶部
        UiAutoLibs.scrollToBeginById("com.android.settings:id/id_scrollView");
        assertEquals("assert DTS keep ON", true,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //打开DTS后高中低音应被禁用
        UiObject lowSeekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/low_seekbar");
        UiObject middleSeekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/middle_seekbar");
        UiObject hightSeekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/hight_seekbar");
        if (lowSeekOjb.isEnabled() == false && middleSeekOjb.isEnabled() == false && hightSeekOjb.isEnabled() == false) {
            isOk = true;
        } else {
            isOk = false;
            Log.d(TAG, "turnOnDTS: seekbar is enable after DTS On, test fail");
        }
        assertEquals("assert seekbar disable", true, isOk);

        //调节前排后排整车可用
        UiObject frontOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_front");
        UiObject behindOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_behind");
        UiObject allOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_all");
        behindOjb.click();
        assertEquals("assert behind" , true, behindOjb.isChecked());

        allOjb.click();
        assertEquals("assert all" , true, allOjb.isChecked());

        frontOjb.click();
        assertEquals("assert front" , true, frontOjb.isChecked());

        //声场被禁用
        UiObject leftOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/left_img");
        assertEquals("assert left disable" , false, leftOjb.isEnabled());

        //关闭DTS
        if (dtsSwtichObj.isChecked()) {
            UiAutoLibs.clickById("com.android.settings:id/DTCS_sound");
        }
        assertEquals("assert 15km selected", false,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

    }

    public static void turnOffDTS() throws UiObjectNotFoundException {
        boolean isOk = false;
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        //滚动到顶部
        UiAutoLibs.scrollToBeginById("com.android.settings:id/id_scrollView");

        //关闭DTS
        UiObject dtsSwtichObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DTCS_sound");
        if (dtsSwtichObj.isChecked()) {
            UiAutoLibs.clickById("com.android.settings:id/DTCS_sound");
        }
        assertEquals("assert 15km selected", false,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //界面切换
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        //滚动到顶部
        UiAutoLibs.scrollToBeginById("com.android.settings:id/id_scrollView");
        assertEquals("assert DTS keep OFF", false,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //打开DTS后高中低音应被禁用
        UiObject lowSeekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/low_seekbar");
        UiObject middleSeekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/middle_seekbar");
        UiObject hightSeekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/hight_seekbar");
        if (lowSeekOjb.isEnabled() == true && middleSeekOjb.isEnabled() == true && hightSeekOjb.isEnabled() == true) {
            isOk = true;
        } else {
            isOk = false;
            Log.d(TAG, "turnOffDTS: seekbar is disable after DTS Off, test fail");
        }
        assertEquals("assert seekbar enabled", true, isOk);

        //调节前排后排整车可用
        UiObject frontOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_front");
        UiObject behindOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_behind");
        UiObject allOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_all");
        assertEquals("assert behind disable" , false, behindOjb.isEnabled());
        assertEquals("assert all disable" , false, allOjb.isEnabled());
        assertEquals("assert front disable" , false, frontOjb.isEnabled());

        //声场可用
        UiObject leftOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/left_img");
        assertEquals("assert left enable" , true, leftOjb.isEnabled());
    }

    public static void soundFieldSettings() throws UiObjectNotFoundException {
        boolean isOk = false;
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        //滚动到顶部
        UiAutoLibs.scrollToBeginById("com.android.settings:id/id_scrollView");

        //关闭DTS
        UiObject dtsSwtichObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DTCS_sound");
        if (dtsSwtichObj.isChecked()) {
            UiAutoLibs.clickById("com.android.settings:id/DTCS_sound");
        }
        assertEquals("assert DTCS is ON", false,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //声场默认值
        UiObject upDownObj = UiAutoLibs.getUiobjectById("com.android.settings:id/updown_number");
        UiObject leftRightObj = UiAutoLibs.getUiobjectById("com.android.settings:id/leftright_number");
        Log.d(TAG, "soundFieldSettings: Default SoundFileld Value, upDown=" + upDownObj.getText() + ", leftRight=" + leftRightObj );

        //调节声场
        UiObject leftObj = UiAutoLibs.getUiobjectById("com.android.settings:id/left_img");
        UiObject bottomObj = UiAutoLibs.getUiobjectById("com.android.settings:id/down_img");
        UiObject upObj = UiAutoLibs.getUiobjectById("com.android.settings:id/up_img");
        UiObject rightObj = UiAutoLibs.getUiobjectById("com.android.settings:id/right_img");

        boolean setOk = false;
        for(int i = 0; i< 22 && setOk == false; i++) {
            if (upDownObj.getText().equals("-10") && leftRightObj.getText().equals("-10")) {
                setOk = true;
            }
        }
        if (upDownObj.getText().equals("-10") && leftRightObj.getText().equals("-10")) {
            isOk = true;
        } else {
            isOk = false;
            Log.d(TAG, "soundFieldSettings: not -10 fail");
        }
        Log.d(TAG, "soundFieldSettings: Set -10 Value, upDown=" + upDownObj.getText() + ", leftRight=" + leftRightObj );
        assertEquals("Assert upDown(-10,-10)", true, isOk);

        setOk = false;
        for(int i = 0; i< 22 && setOk == false; i++) {
            UiAutoLibs.clickObject(upObj);
            UiAutoLibs.clickObject(rightObj);
            if (upDownObj.getText().equals("10") && leftRightObj.getText().equals("10")) {
                setOk = true;
            }
        }
        if (upDownObj.getText().equals("10") && leftRightObj.getText().equals("10")) {
            isOk = true;
        } else {
            isOk = false;
            Log.d(TAG, "soundFieldSettings: not 10 fail");
        }
        Log.d(TAG, "soundFieldSettings: Set 10 Value, upDown=" + upDownObj.getText() + ", leftRight=" + leftRightObj );
        assertEquals("Assert LeftBehid(10,10)", true, isOk);

        //com.android.settings:id/reset
        UiAutoLibs.clickById("com.android.settings:id/reset");
        if (upDownObj.getText().equals("0") && leftRightObj.getText().equals("0")) {
            isOk = true;
        } else {
            isOk = false;
            Log.d(TAG, "soundFieldSettings: not 0 fail");
        }
        Log.d(TAG, "soundFieldSettings: Set 0 Value, upDown=" + upDownObj.getText() + ", leftRight=" + leftRightObj );
        assertEquals("Assert LeftBehid(0,0)", true, isOk);
    }

    public static void bassSoundSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/DTCS_sound");

        //关闭DTS
        UiObject dtsSwtichObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DTCS_sound");
        if (dtsSwtichObj.isChecked()) {
            UiAutoLibs.clickById("com.android.settings:id/DTCS_sound");
        }
        assertEquals("assert 15km selected", false,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //默认值
        UiObject lowSeekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/low_seekbar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/low_number");
        Log.d(TAG, "bassSoundSettings: default lowValue=" + valueObj.getText());

        //恢复默认
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sound_effect_reset");
        UiAutoLibs.clickById("com.android.settings:id/sound_effect_reset");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/low_number");
        String lowResetValue = valueObj.getText();
        Log.d(TAG, "bassSoundSettings: reset value = " + lowResetValue);

        UiAutoLibs.clickSeekBar(15, 1, lowSeekOjb);
        assertEquals("assert low value after set -7", "-7", valueObj.getText());

        UiAutoLibs.clickSeekBar(15, 15, lowSeekOjb);
        assertEquals("assert low value after set 7", "7", valueObj.getText());

        //恢复默认
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sound_effect_reset");
        UiAutoLibs.clickById("com.android.settings:id/sound_effect_reset");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/low_number");
        String afterResetValue = valueObj.getText();
        Log.d(TAG, "bassSoundSettings: reset value after two times = " + afterResetValue);
        assertEquals("assert reset two times for the same", afterResetValue, lowResetValue);
    }

    public static void middleSoundSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/DTCS_sound");

        //关闭DTS
        UiObject dtsSwtichObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DTCS_sound");
        if (dtsSwtichObj.isChecked()) {
            UiAutoLibs.clickById("com.android.settings:id/DTCS_sound");
        }
        assertEquals("assert DTS OFF", false,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/middle_seekbar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/middle_number");
        Log.d(TAG, "middleSoundSettings: default middleValue=" + valueObj.getText());

        //恢复默认
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sound_effect_reset");
        UiAutoLibs.clickById("com.android.settings:id/sound_effect_reset");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/middle_number");
        String lowResetValue = valueObj.getText();
        Log.d(TAG, "middleSoundSettings: reset value = " + lowResetValue);

        UiAutoLibs.clickSeekBar(15, 1, seekOjb);
        assertEquals("assert middle value after set -7", "-7", valueObj.getText());

        UiAutoLibs.clickSeekBar(15, 15, seekOjb);
        assertEquals("assert middle value after set 7", "7", valueObj.getText());

        //恢复默认
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sound_effect_reset");
        UiAutoLibs.clickById("com.android.settings:id/sound_effect_reset");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/middle_number");
        String afterResetValue = valueObj.getText();
        Log.d(TAG, "middleSoundSettings: reset value after two times = " + afterResetValue);
        assertEquals("assert reset two times for the same", afterResetValue, lowResetValue);
    }

    public static void trebleSoundSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/DTCS_sound");

        //关闭DTS
        UiObject dtsSwtichObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DTCS_sound");
        if (dtsSwtichObj.isChecked()) {
            UiAutoLibs.clickById("com.android.settings:id/DTCS_sound");
        }
        assertEquals("assert DTS OFF", false,
                UiAutoLibs.isCheckById("com.android.settings:id/DTCS_sound"));

        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/hight_seekbar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/hight_number");
        Log.d(TAG, "middleSoundSettings: default middleValue=" + valueObj.getText());

        //恢复默认
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sound_effect_reset");
        UiAutoLibs.clickById("com.android.settings:id/sound_effect_reset");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/hight_number");
        String lowResetValue = valueObj.getText();
        Log.d(TAG, "trebleSoundSettings: reset value = " + lowResetValue);

        UiAutoLibs.clickSeekBar(15, 1, seekOjb);
        assertEquals("assert treble value after set -7", "-7", valueObj.getText());

        UiAutoLibs.clickSeekBar(15, 15, seekOjb);
        assertEquals("assert treble value after set 7", "7", valueObj.getText());

        //恢复默认
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sound_effect_reset");
        UiAutoLibs.clickById("com.android.settings:id/sound_effect_reset");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/hight_number");
        String afterResetValue = valueObj.getText();
        Log.d(TAG, "trebleSoundSettings: reset value after two times = " + afterResetValue);
        assertEquals("assert reset two times for the same", afterResetValue, lowResetValue);
    }

    public static void soundQualityReductionSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_effects");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sound_quality");

        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sound_quality");

        Log.d(TAG, "soundQualityReductionSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert soundQuality On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert soundQuality Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert soundQuality second On", true, targetObj.isChecked());
    }

    public static void mediaVolumeSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_volume");
        //滚动到media
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/media_seekBar");
        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/media_seekBar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/media_number");
        Log.d(TAG, "mediaVolumeSettings: default mediaVolumeValue=" + valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 1, seekOjb);
        assertEquals("assert mediaVolume after set 0", "0", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 39, seekOjb);
        assertEquals("assert mediaVolume after set 39", "39", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 11, seekOjb);
        assertEquals("assert mediaVolume after set 10", "10", valueObj.getText());

    }

    public static void vrVolumeSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_volume");
        //滚动到media
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/vr_seekBar");
        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/vr_seekBar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/vr_number");
        Log.d(TAG, "vrVolumeSettings: default Value=" + valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 1, seekOjb);
        assertEquals("assert vrVolume after set 0", "0", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 39, seekOjb);
        assertEquals("assert vrVolume after set 39", "39", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 11, seekOjb);
        assertEquals("assert vrVolume after set 10", "10", valueObj.getText());
    }

    public static void phoneVolumeSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_volume");
        //滚动到media
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/phone_seekBar");
        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/phone_seekBar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/phone_number");
        Log.d(TAG, "phoneVolumeSettings: default Value=" + valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 1, seekOjb);
        Log.d(TAG, "phoneVolumeSettings: set phoneVolume 0 = " + seekOjb.getText());
        assertEquals("assert phoneVolume = 5 after set 1", "5", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 39, seekOjb);
        Log.d(TAG, "phoneVolumeSettings: set phoneVolume 39 = " + seekOjb.getText());
        assertEquals("assert phoneVolume after set 39", "39", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 11, seekOjb);
        Log.d(TAG, "phoneVolumeSettings: set phoneVolume 10 = " + seekOjb.getText());
        assertEquals("assert phoneVolume after set 10", "10", valueObj.getText());
    }

    public static void navigationVolumeSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_volume");
        //滚动到media
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/navi_seekBar");
        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/navi_seekBar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/navi_number");
        Log.d(TAG, "navigationVolumeSettings: default Value=" + valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 1, seekOjb);
        assertEquals("assert navigationVolume after set 0", "0", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 39, seekOjb);
        assertEquals("assert navigationVolume after set 39", "39", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 11, seekOjb);
        assertEquals("assert navigationVolume after set 10", "10", valueObj.getText());
    }

    public static void btRingVolumeSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_volume");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/bt_ring_seekBar");
        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_ring_seekBar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_ring_number");
        Log.d(TAG, "btRingVolumeSettings: default Value=" + valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 1, seekOjb);
        assertEquals("assert btRingVolume after set 0", "0", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 39, seekOjb);
        assertEquals("assert btRingVolume after set 39", "39", valueObj.getText());

        UiAutoLibs.clickSeekBar(39, 11, seekOjb);
        assertEquals("assert btRingVolume after set 10", "10", valueObj.getText());
    }

    public static void alarmVolumeSettings() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/sound_volume");
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/alarm_seekBar");
        //默认值
        UiObject seekOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/alarm_seekBar");
        UiObject valueObj = UiAutoLibs.getUiobjectById("com.android.settings:id/alarm_number");
        Log.d(TAG, "alarmVolumeSettings: default Value=" + valueObj.getText());

        UiAutoLibs.clickSeekBar(15, 1, seekOjb);
        assertEquals("assert alarmVolume after set 5", "5", valueObj.getText());

        UiAutoLibs.clickSeekBar(15, 15, seekOjb);
        assertEquals("assert alarmVolume after set 15", "15", valueObj.getText());

        UiAutoLibs.clickSeekBar(15, 10, seekOjb);
        assertEquals("assert alarmVolume after set 10", "10", valueObj.getText());
    }

    public static void vrSwitchCheckInVox() throws UiObjectNotFoundException {
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/vr_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/vr_switch");

        Log.d(TAG, "vrSwitchCheckInVox: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert vrSwitch On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert vrSwitch Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert vrSwitch second On", true, targetObj.isChecked());
    }

    public static void touchSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/touch_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/touch_switch");

        Log.d(TAG, "touchSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert touchSwitch On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert touchSwitch Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert touchSwitch second On", true, targetObj.isChecked());
    }

    public static void messageToneSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/notify_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/notify_switch");

        Log.d(TAG, "messageToneSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert messageToneSwitch On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert messageToneSwitch Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert messageToneSwitch second On", true, targetObj.isChecked());
    }

    public static void carWaringSettings() throws UiObjectNotFoundException {
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/sv_tab_waring1");
        //默认值
        UiObject lowSdvcjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_waring1");
        UiObject middleSdvcjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_waring2");
        UiObject highSdvcjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_waring3");

        Log.d(TAG, "carWaringSettings: default checked sound1 = " + lowSdvcjb.isChecked()
                + ", sound2 = " + middleSdvcjb.isChecked()
                + ", sound3 = " + highSdvcjb.isChecked());

        int counter = 0;
        if (lowSdvcjb.isChecked()==true){
            counter ++;
        }
        if (middleSdvcjb.isChecked()==true){
            counter ++;
        }
        if (highSdvcjb.isChecked()==true){
            counter ++;
        }
        assertEquals("assert only one checke", 1, 1);

        boolean isOk = false;
        middleSdvcjb.click();
        if (lowSdvcjb.isChecked()==false && middleSdvcjb.isChecked()==true && highSdvcjb.isChecked()==false){
            isOk = true;
        }
        assertEquals("assert sound2 selected", true, isOk);

        isOk = false;
        highSdvcjb.click();
        if (lowSdvcjb.isChecked()==false && middleSdvcjb.isChecked()==false && highSdvcjb.isChecked()==true){
            isOk = true;
        }
        assertEquals("assert sound3 selected", true, isOk);

        isOk = false;
        lowSdvcjb.click();
        if (lowSdvcjb.isChecked()==true && middleSdvcjb.isChecked()==false && highSdvcjb.isChecked()==false){
            isOk = true;
        }
        assertEquals("assert sound1 selected", true, isOk);
    }

    public static void sdvcVolumeSettings() throws UiObjectNotFoundException {
        //滚动到音量随速
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/id_scrollView", "com.android.settings:id/volume_speed_switch");
        //默认值
        UiObject lowSdvcjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_low");
        UiObject middleSdvcjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_middle");
        UiObject highSdvcjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_high");
        UiObject offSdvcjb = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_off");

        Log.d(TAG, "sdvcVolumeSettings: default checked lowSdvc = " + lowSdvcjb.isChecked()
                + ", middleSdvc = " + middleSdvcjb.isChecked()
                + ", highSdvc = " + highSdvcjb.isChecked()
                + ", offStatus = " + offSdvcjb.isChecked());

        lowSdvcjb.click();
        assertEquals("assert sdvc low is disable", true, lowSdvcjb.isChecked());
        assertEquals("assert sdvc middle is disable", false, middleSdvcjb.isChecked());
        assertEquals("assert sdvc high is disable", false, highSdvcjb.isChecked());
        assertEquals("assert sdvc is off", false, offSdvcjb.isChecked());

        highSdvcjb.click();
        assertEquals("assert sdvc low is disable", false, lowSdvcjb.isChecked());
        assertEquals("assert sdvc middle is disable", false, middleSdvcjb.isChecked());
        assertEquals("assert sdvc high is disable", true, highSdvcjb.isChecked());
        assertEquals("assert sdvc is off", false, offSdvcjb.isChecked());

        offSdvcjb.click();
        assertEquals("assert sdvc low is disable", false, lowSdvcjb.isChecked());
        assertEquals("assert sdvc middle is disable", false, middleSdvcjb.isChecked());
        assertEquals("assert sdvc high is disable", false, highSdvcjb.isChecked());
        assertEquals("assert sdvc is off", true, offSdvcjb.isChecked());

        middleSdvcjb.click();
        assertEquals("assert sdvc low is disable", false, lowSdvcjb.isChecked());
        assertEquals("assert sdvc middle is disable", true, middleSdvcjb.isChecked());
        assertEquals("assert sdvc high is disable", false, highSdvcjb.isChecked());
        assertEquals("assert sdvc is off", false, offSdvcjb.isChecked());

    }

    public static void systemVersionInformation() throws UiObjectNotFoundException {
        UiAutoLibs.clickById("com.android.settings:id/layout_system_message");
        String mcuStr = UiAutoLibs.getUiobjectById("com.android.settings:id/mcu_number").getText();
        String osStr = UiAutoLibs.getUiobjectById("com.android.settings:id/os_number").getText();

        Bundle bundle = new Bundle();
        bundle.putString("MCU", mcuStr);
        bundle.putString("OS", osStr);
        instrumentation.sendStatus(815, bundle);

//        String hardwareStr = UiAutoLibs.getUiobjectById("com.android.settings:id/hardware_number").getText();
//        String machineStr = UiAutoLibs.getUiobjectById("com.android.settings:id/machine_number").getText();



        Log.d(TAG, "systemVersionInformation: MCU = " + mcuStr);
        Log.d(TAG, "systemVersionInformation: OS = " + osStr);
//        Log.d(TAG, "systemVersionInformation: Hardware = " + hardwareStr);
//        Log.d(TAG, "systemVersionInformation: Machine = " + machineStr);
        assertEquals("assert MCU contains NV2969", true, mcuStr.contains("NV2969"));
        assertEquals("assert OS start contains G6SA", true, osStr.contains("G6SA"));
//        assertEquals("assert Hardware contains HW000", true, hardwareStr.contains("HW000"));
//        assertEquals("assert Machine is not null", true, !machineStr.equals(""));
    }

    public static void btNameCheck() throws UiObjectNotFoundException {
        String renameNameStr = "你Sin不Sin我似一过单纯靓丽滴会写点CODE哒风一样哒美男子";
        turnOnBT(20);
        UiObject btNameObj = UiAutoLibs.getUiobjectById("com.android.settings:id/txt_bt_name");
        UiObject renameBtnObj = UiAutoLibs.getUiobjectById("com.android.settings:id/img_bt_rename");
        UiObject renameEditObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_dialog_et_rename");

        String defaultNameStr = btNameObj.getText();
        Log.d(TAG, "btNameCheck: default BT name = " + defaultNameStr);

        //修改蓝牙名称
        renameBtnObj.click();
        renameEditObj.waitForExists(5000);
        renameEditObj.setText(renameNameStr);
        UiAutoLibs.clickById("com.android.settings:id/bt_rename_dialog_confirm");
        //断言是否修改成功
        assertEquals("断言是否修改", renameNameStr, btNameObj.getText());
        SystemClock.sleep(5000);//等待5秒再检查一次
        assertEquals("断言是否修改", renameNameStr, btNameObj.getText());

        //修改为默认
        renameBtnObj.click();
        renameEditObj.waitForExists(5000);
        renameEditObj.setText(defaultNameStr);
        UiAutoLibs.clickById("com.android.settings:id/bt_rename_dialog_confirm");
        //断言是否修改成功
        assertEquals("断言是否修改为默认", defaultNameStr, btNameObj.getText());
    }

    public static void btSwitchCheck() throws UiObjectNotFoundException {
        Actions.turnOffBT();
        UiObject renameBtnOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/img_bt_rename");
        UiObject autoConnectBtnOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_auto_con_switch");
        UiObject ringBellBtnOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/rb_bell_1");
        UiObject doubleBtBtnOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_double_bt_switch");
        UiObject a2dpBtnOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_a2dp_switch");
        UiObject doubleHfpBtnOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_double_hfp_switch");
        assertEquals("assert rename btn", false, renameBtnOjb.isEnabled());
        assertEquals("assert autoConnect btn", false, autoConnectBtnOjb.isEnabled());
        assertEquals("assert ringBell btn", false, ringBellBtnOjb.isEnabled());
        assertEquals("assert doubleBt btn", false, doubleBtBtnOjb.isEnabled());

        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/bt_a2dp_switch");
        assertEquals("assert a2dpBtn btn", false, a2dpBtnOjb.isEnabled());
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/rl_double_hfp_con");
        assertEquals("assert doubleHfp btn", false, doubleHfpBtnOjb.isEnabled());

        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/bt_power_switch");

        Actions.turnOnBT(20);
        assertEquals("assert rename btn", true, renameBtnOjb.isEnabled());
        assertEquals("assert autoConnect btn", true, autoConnectBtnOjb.isEnabled());
        assertEquals("assert ringBell btn", true, ringBellBtnOjb.isEnabled());
        assertEquals("assert doubleBt btn", true, doubleBtBtnOjb.isEnabled());

        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/bt_a2dp_switch");
        assertEquals("assert a2dpBtn btn", true, a2dpBtnOjb.isEnabled());
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/rl_double_hfp_con");
        assertEquals("assert doubleHfp btn", true, doubleHfpBtnOjb.isEnabled());
    }

    public static void btAutoConnectSwitchCheck() throws UiObjectNotFoundException {
        Actions.turnOnBT(20);
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/bt_auto_con_switch");
        UiObject autoConnectObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_auto_con_switch");

        Log.d(TAG, "btAutoConnectSwitchCheck: default value = " + autoConnectObj.isChecked());

        if (!autoConnectObj.isChecked()) {
            autoConnectObj.click();//打开自动连接
        }
        assertEquals("assert autoConnect On", true, autoConnectObj.isChecked());

        if (autoConnectObj.isChecked()) {
            autoConnectObj.click();//关闭自动连接
        }
        assertEquals("assert autoConnect Off", false, autoConnectObj.isChecked());

        if (!autoConnectObj.isChecked()) {
            autoConnectObj.click();//打开自动连接
        }
        assertEquals("assert autoConnect second On", true, autoConnectObj.isChecked());
    }

    public static void btRingtonesCheck() throws UiObjectNotFoundException {
        Actions.turnOnBT(20);
        //com.android.settings:id/rg_bell
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/tv_ringtone");
        UiObject ringOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/tv_ringtone");

        Log.d(TAG, "btRingtonesCheck: default Ringtone = " + ringOjb.getText());

        ringOjb.click();//点击弹出选择对话框
        UiObject selectDialogOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/wallpaper_wheelview");
        selectDialogOjb.waitForExists(3000);
        Log.d(TAG, "btRingtonesCheck: seleceDialog exit = " + selectDialogOjb.exists());
        UiAutoLibs.scrollToEndById("com.android.settings:id/wallpaper_wheelview");
        UiAutoLibs.clickObject(selectDialogOjb);
        Log.d(TAG, "btRingtonesCheck: 调到最后一个铃声 = " + ringOjb.getText());

        assertEquals("assert 调到最后一个铃声", "Ursaminor", ringOjb.getText());


        ringOjb.click();//点击弹出选择对话框
        selectDialogOjb = UiAutoLibs.getUiobjectById("com.android.settings:id/wallpaper_wheelview");
        selectDialogOjb.waitForExists(3000);
        Log.d(TAG, "btRingtonesCheck: seleceDialog exit = " + selectDialogOjb.exists());
        UiAutoLibs.scrollToBeginById("com.android.settings:id/wallpaper_wheelview");
        UiAutoLibs.clickObject(selectDialogOjb);
        Log.d(TAG, "btRingtonesCheck: 调到第一个铃声 = " + ringOjb.getText());
        assertEquals("assert 调到第一个铃声", "Orion", ringOjb.getText());
    }

    public static void dualBtSwitchCheck() throws UiObjectNotFoundException {
        Actions.turnOnBT(20);
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/bt_double_bt_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_double_bt_switch");

        Log.d(TAG, "dualBtSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开自动连接
        }
        assertEquals("assert dual Bt On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭自动连接
        }
        assertEquals("assert dual Bt Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开自动连接
        }
        assertEquals("assert dual Bt second On", true, targetObj.isChecked());
    }

    public static void btAudioConnectionSwitchCheck() throws UiObjectNotFoundException {
        Actions.turnOnBT(20);
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/bt_a2dp_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_a2dp_switch");

        Log.d(TAG, "btAudioConnectionSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert btAudioConnection On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert btAudioConnection Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert btAudioConnection second On", true, targetObj.isChecked());
    }

    public static void btTwoWayCallSwitchCheck() throws UiObjectNotFoundException {
        Actions.turnOnBT(20);
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/bt_double_hfp_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_double_hfp_switch");

        Log.d(TAG, "btTwoWayCallSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert btTwoWayCall On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert btTwoWayCall Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert btTwoWayCall second On", true, targetObj.isChecked());
    }

    public static void voiceWakeUpSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/wakeup_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/wakeup_switch");

        Log.d(TAG, "voiceWakeUpSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert voiceWakeUp On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert voiceWakeUp Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert voiceWakeUp second On", true, targetObj.isChecked());
    }

    public static void voiceWakeRecongnitionSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/recognition_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/recognition_switch");

        Log.d(TAG, "voiceWakeRecongnitionSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert voiceWakeRecongnition On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert voiceWakeRecongnition Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert voiceWakeRecongnition second On", true, targetObj.isChecked());
    }

    public static void voiceMultipleRoundsOfInteractionSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/more_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/more_switch");

        Log.d(TAG, "voiceMultipleRoundsOfInteractionSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert voiceMultipleRounds On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert voiceMultipleRounds Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert voiceMultipleRounds second On", true, targetObj.isChecked());
    }

    public static void voiceWakeCommandCheck() throws UiObjectNotFoundException {
        String renameNameStr = "语音唤醒命令你Sin不Sin我似一过单纯靓丽滴会写点CODE哒风一样哒美男子";
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/wakeup_str");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/wakeup_str");

        UiObject nameObj = UiAutoLibs.getUiobjectById("com.android.settings:id/wakeup_str");
        UiObject renameBtnObj = UiAutoLibs.getUiobjectById("com.android.settings:id/modify1");
        UiObject renameEditObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_dialog_et_rename");//com.android.settings:id/bt_dialog_et_rename

        String defaultNameStr = nameObj.getText();
        Log.d(TAG, "voiceWakeCommandCheck: default wake command = " + defaultNameStr);

        //修改名称
        renameBtnObj.click();
        renameEditObj.waitForExists(5000);
        renameEditObj.setText(renameNameStr);
        UiAutoLibs.clickById("com.android.settings:id/bt_rename_dialog_confirm");
        //断言是否修改成功
        assertEquals("断言是否修改", renameNameStr, nameObj.getText());

        //修改为默认
        renameBtnObj.click();
        renameEditObj.waitForExists(5000);
        renameEditObj.setText(defaultNameStr);
        UiAutoLibs.clickById("com.android.settings:id/bt_rename_dialog_confirm");//com.android.settings:id/bt_rename_dialog_confirm
        //断言是否修改成功
        assertEquals("断言是否修改为默认", defaultNameStr, nameObj.getText());
    }

    public static void voiceAppellationCheck() throws UiObjectNotFoundException {
        String renameNameStr = "语音称呼语你Sin不Sin我似一过单纯靓丽滴会写点CODE哒风一样哒美男子";
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/call_str");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/call_str");

        UiObject nameObj = UiAutoLibs.getUiobjectById("com.android.settings:id/call_str");
        UiObject renameBtnObj = UiAutoLibs.getUiobjectById("com.android.settings:id/modify2");
        UiObject renameEditObj = UiAutoLibs.getUiobjectById("com.android.settings:id/bt_dialog_et_rename");//com.android.settings:id/bt_dialog_et_rename

        String defaultNameStr = nameObj.getText();
        Log.d(TAG, "voiceAppellationCheck: default Appellation = " + defaultNameStr);

        //修改名称
        renameBtnObj.click();
        renameEditObj.waitForExists(5000);
        renameEditObj.setText(renameNameStr);
        UiAutoLibs.clickById("com.android.settings:id/bt_rename_dialog_confirm");
        //断言是否修改成功
        assertEquals("断言是否修改", renameNameStr, nameObj.getText());

        //修改为默认
        renameBtnObj.click();
        renameEditObj.waitForExists(5000);
        renameEditObj.setText(defaultNameStr);
        UiAutoLibs.clickById("com.android.settings:id/bt_rename_dialog_confirm");//com.android.settings:id/bt_rename_dialog_confirm
        //断言是否修改成功
        assertEquals("断言是否修改为默认", defaultNameStr, nameObj.getText());
    }

    public static void voicePronunciationPeopleCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollIdFindObjectById("com.android.settings:id/scrollview", "com.android.settings:id/speaker_name");
        UiObject peopleSelectedObj = UiAutoLibs.getUiobjectById("com.android.settings:id/speaker_name");
        String defaultPeopleStr = peopleSelectedObj.getText();
        Log.d(TAG, "voicePronunciationPeopleCheck: default people默认人名 = " + defaultPeopleStr);

        peopleSelectedObj.click();
        UiObject peopleObj = UiAutoLibs.getUiobjectById("com.android.settings:id/wallpaper_wheelview");
        peopleObj.waitForExists(3000);
        UiAutoLibs.scrollToEndById("com.android.settings:id/wallpaper_wheelview");
        peopleObj.click();
        Log.d(TAG, "voicePronunciationPeopleCheck: 最后一个人名 = " + peopleSelectedObj.getText());
        String lastObjStr = peopleSelectedObj.getText();;

        peopleSelectedObj.click();
        peopleObj = UiAutoLibs.getUiobjectById("com.android.settings:id/wallpaper_wheelview");
        peopleObj.waitForExists(3000);
        UiAutoLibs.scrollToBeginById("com.android.settings:id/wallpaper_wheelview");
        peopleObj.click();
        Log.d(TAG, "voicePronunciationPeopleCheck: 第一个人名 = " + peopleSelectedObj.getText());
        assertEquals("断言最后一个人名和第一个人名不一样", false, peopleSelectedObj.getText().equals(lastObjStr));
    }

    public static void systemMessagePushSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/push_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/push_switch");

        Log.d(TAG, "systemMessagePushSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert systemMessagePushSwitch On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert systemMessagePushSwitch Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert systemMessagePushSwitch second On", true, targetObj.isChecked());
    }

    public static void tboxSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/tbox_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/tbox_switch");
        UiObject wifiApSwitctObj = UiAutoLibs.getUiobjectById("com.android.settings:id/ap_switch");

        Log.d(TAG, "tboxSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert tboxSwitch On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert tboxSwitch Off", false, targetObj.isChecked());
        UiAutoLibs.clickObject(wifiApSwitctObj);//TOBX关闭情况下热点开关无法打开
        assertEquals("assert wifiApSwitch off", false, wifiApSwitctObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert tboxSwitch second On", true, targetObj.isChecked());

        UiAutoLibs.clickObject(wifiApSwitctObj);//打开热点
        assertEquals("assert wifi ap  On", true, wifiApSwitctObj.isChecked());

        UiAutoLibs.clickObject(targetObj);//关闭TBOX，热点也要关闭
        assertEquals("assert tboxSwitch Off", false, targetObj.isChecked());
        assertEquals("assert wifi ap off with tbox", false, wifiApSwitctObj.isChecked());


    }

    public static void wifiApSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/ap_switch");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/ap_switch");
        UiObject tBoxSwitctObj = UiAutoLibs.getUiobjectById("com.android.settings:id/tbox_switch");

        Log.d(TAG, "wifiApSwitchCheck: default value = " + targetObj.isChecked());

        if (!tBoxSwitctObj.isChecked()){//TOBX打开热点才可用
            UiAutoLibs.clickObject(tBoxSwitctObj);
        }

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert wifiAp On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert wifiAp Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert tboxSwitch second On", true, targetObj.isChecked());
    }

    public static void mobileInternetCheck() throws UiObjectNotFoundException{
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/sv_tab_usb");
        UiObject usbObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_usb");
        UiObject wifiObj = UiAutoLibs.getUiobjectById("com.android.settings:id/sv_tab_usb");

        Log.d(TAG, "mobileInternetCheck: default mobile internet selected = usb " + usbObj.isChecked() + ", WIFI " + wifiObj.isChecked());
        boolean isOk = false;
        if ((usbObj.isChecked()==true && wifiObj.isChecked()==true) || (wifiObj.isChecked()==false && usbObj.isChecked()==false)) {
            isOk = true;
        }
        assertEquals("assert mobile internet only on selected", true, isOk);

        UiAutoLibs.clickObject(wifiObj);
        assertEquals("assert WIFI", true, wifiObj.isChecked());

        UiAutoLibs.clickObject(usbObj);
        assertEquals("assert USB", true, usbObj.isChecked());
    }

    public static void dlnaSwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/DLNA_connect");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DLNA_connect");

        Log.d(TAG, "dlnaSwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert dlnaSwitch On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert dlnaSwitch Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert dlnaSwitch second On", true, targetObj.isChecked());
    }

    public static void carPlaySwitchCheck() throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectById("android.widget.ScrollView", "com.android.settings:id/DLNA_connect");
        UiObject targetObj = UiAutoLibs.getUiobjectById("com.android.settings:id/DLNA_connect");

        Log.d(TAG, "carPlaySwitchCheck: default value = " + targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert CarPlay On", true, targetObj.isChecked());

        if (targetObj.isChecked()) {
            targetObj.click();//关闭
        }
        assertEquals("assert CarPlay Off", false, targetObj.isChecked());

        if (!targetObj.isChecked()) {
            targetObj.click();//打开
        }
        assertEquals("assert CarPlay second On", true, targetObj.isChecked());
    }

    public static void wifiPasswordWidthCheck(String nameStr) throws UiObjectNotFoundException {
        UiAutoLibs.scrollClassFindObjectByText("android.widget.ScrollView", nameStr);
        UiAutoLibs.clickByText(nameStr);
        //密码框
        UiObject passwordEditObj = UiAutoLibs.getUiobjectById("com.android.settings:id/wifi_con_dialog_password");
        passwordEditObj.waitForExists(3000);
        //确定按钮出现
        UiObject confirmBtnObj = UiAutoLibs.getUiobjectById("com.android.settings:id/wifi_con_dialog_confirm");
        boolean isOk = false;
        Log.d(TAG, "wifiPasswordWidthCheck: null = " + passwordEditObj.getText());
        passwordEditObj.setText(passwordEditObj.getText());
        if (!confirmBtnObj.isEnabled()){
            isOk = true;
        }
        assertEquals("assert password null but " + passwordEditObj.getText(), true, isOk);

        passwordEditObj.setText("1234567");
        isOk = false;
        Log.d(TAG, "wifiPasswordWidthCheck: width 7 = " + passwordEditObj.getText().length());
        if (passwordEditObj.getText().length()==7 && !confirmBtnObj.isEnabled()){
            isOk = true;
        }
        assertEquals("assert password 1234567<8", true, isOk);

        passwordEditObj.setText("12345678");
        isOk = false;
        Log.d(TAG, "wifiPasswordWidthCheck: width 8 = " + passwordEditObj.getText().length());
        Log.d(TAG, "wifiPasswordWidthCheck: password = " + passwordEditObj.getText());
        if (passwordEditObj.getText().length()==8 && confirmBtnObj.isEnabled()){
            isOk = true;
        }
        assertEquals("assert password 12345678=8", true, isOk);

        passwordEditObj.setText("123456789");
        isOk = false;
        Log.d(TAG, "wifiPasswordWidthCheck: width 9 = " + passwordEditObj.getText().length());
        if (passwordEditObj.getText().length()==9 && confirmBtnObj.isEnabled()){
            isOk = true;
        }
        assertEquals("assert password 123456789>8", true, isOk);

        try {
            mUiDevice.executeShellCommand("input keyevent KEYCODE_DEL");
            mUiDevice.executeShellCommand("input keyevent KEYCODE_DEL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        isOk = false;
        Log.d(TAG, "wifiPasswordWidthCheck: width 7 after del 2 bit= " + passwordEditObj.getText().length());
        if (passwordEditObj.getText().length()==7 && !confirmBtnObj.isEnabled()){
            isOk = true;
        }
        assertEquals("assert password del < 8", true, isOk);

        UiAutoLibs.clickById("com.android.settings:id/wifi_con_dialog_concel");
        assertEquals("assert password dialog dismiss", true, confirmBtnObj.waitUntilGone(3000));
    }

    public static void wifiConnectMemorySSID(String saveSsid, String switchSsid) throws UiObjectNotFoundException {
        connectWifi("mobile", "sv2655888");
        connectWifiNoPassword("guest");
        connectWifiNoPassword("mobile");
    }
}


