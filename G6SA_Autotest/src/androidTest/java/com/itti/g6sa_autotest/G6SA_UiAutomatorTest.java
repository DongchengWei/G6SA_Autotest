package com.itti.g6sa_autotest;

import android.app.Instrumentation;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.util.Log;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class G6SA_UiAutomatorTest {

    public static final long EXISTS_WAIT_TIME = 1000;//等待存在的等待时长
    public static final String TAG = "Tonsen_Tag";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();//获取参数



//    @Before
//    public void setUp(){
//        mUiDevice.registerWatcher("alarmWatcher", alarmWatcher);
//        Log.d(TAG, "setUp: registerWatcher");
//    }
//
//    @After
//    public void tearDown() {
//        mUiDevice.removeWatcher("alarmWatcher");
//        Log.d(TAG, "tearDown: removeWatcher");
//    }


    @Test
    public void _0001_turnOnWifi() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOffWifi();
        Actions.turnOnWifi(5);
    }
    @Test
    public void _0002_turnOffWifi() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.turnOffWifi();
        Actions.turnOnWifi(5);
    }
    @Test
    public void _0003_disconnectWifi() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
    }

    @Test
    public void _0004_connectWifi() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
        Actions.connectWifi("mobile", "sv2655888");
        Actions.turnOnWifi(5);
    }
    @Test
    public void _0005_turnOnBT() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.turnOnBT(120);
    }
    @Test
    public void _0006_turnOffBT() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.turnOffBT();
    }
    @Test
    public void _0007_switchTimeTo_12H() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.switchTimeTo_12H();
    }
    @Test
    public void _0008_switchTimeTo_24H() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.switchTimeTo_12H();
    }
    @Test
    public void _0009_switchLanguageToChinese() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.switchLanguageToChinese();
    }
    @Test
    public void _0010_switchLanguageToEnglish() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.switchLanguageToEnglish();
    }
    @Test
    public void _0011_voiceAllSwitchOn() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.turnVoiceAllSwitchOn();
    }
    @Test
    public void _0012_voiceAllSwitchOff() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.turnVoiceAllSwitchOff();
    }

    @Test
    public void _0013_wifiOffThenOnCheckAutoConnect() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.wifiOffAndOnAutoConnect(100, 10000);
    }




    @Test
    public void _0014_judgeWifiConnectInTab() throws Exception {
        Actions.judgeWifiConnect(10000);
    }

    @Test
    public void _0015_longPressPowerButton() throws Exception {
        UiAutoLibs.longPressKeyCode(26, 3000);
    }

    @Test
    public void _0016_launchActivity() throws Exception {
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
    }

    @Test
    public void _0017_musicTitle() throws Exception {
        //com.desaysv_automotive.svmedia:id/tv_title
        UiObject musicTitleobj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.desaysv_automotive.svmedia:id/tv_title"));
        String titleStr = musicTitleobj.getText();
        Log.d(TAG, "_0017_musicTitle: music title = " + titleStr);
    }

    @Test
    public void _0018_videoTitle() throws Exception {
        //com.desaysv_automotive.svmedia:id/tv_title
        UiObject videoTitleobj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.desaysv_automotive.svmedia:id/vedio_type_name"));
        String titleStr = videoTitleobj.getText();
        Log.d(TAG, "_0018_videoTitle: music title = " + titleStr);
    }

    @Test
    public void update() throws Exception {
        //com.dsv.update/.activity.RadioUpdateActivity
        Actions.amActivity("com.dsv.update/.activity.FotaUpdateActivity");
        mUiDevice.findObject(new UiSelector().resourceId("com.dsv.update:id/btn_normal")).click();
        UiObject statusObj = mUiDevice.findObject(new UiSelector().resourceId("com.dsv.update:id/tv_status"));
        statusObj.waitForExists(30000);
        //指定时间
        long startMills = SystemClock.uptimeMillis();
        long currentMills = 0;
        long timeout = 1000 * 60 * 30;
        boolean isOk = false;
        while(currentMills <= timeout){
            currentMills = SystemClock.uptimeMillis() - startMills;
            if(timeout > 0) {
                SystemClock.sleep(100);
            }
            UiObject connectStatusObj = mUiDevice.findObject(new UiSelector()
                    .resourceId("com.dsv.update:id/tv_status"));
            if (connectStatusObj.getText().contains("升级完成")) {
                isOk = true;
                Log.d(TAG, "update: 升级成功");
                currentMills = timeout + 1;
            }
        }
        assertEquals("升级结果断言", true, isOk);
    }

    @Test
    public void _1000_ToggleSettings() throws Exception {
        //wifi
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);

        //BT
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.turnOnBT(120);
        Actions.btAutoAcceptCall(true);
        Actions.btRingSelect(2);
        Actions.btRingSelect(3);
        Actions.btRingSelect(4);

        //Time
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.switchTimeTo_24H();
        Actions.timeTabGPS_Sync(true);

        //Display
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.displayLockScreenType(2);
        Actions.displayLockScreenType(3);
        Actions.switchLanguageToEnglish();
        Actions.displayDayNightMode(2);
        Actions.displayDayNightMode(3);
        Actions.displaySeekLightTo(5);
        Actions.displayThemeSelect(2);
        Actions.displayThreamColorsSelect(2);
        Actions.displayThreamColorsSelect(3);

        //Sound
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.soundShowEffects();
        Actions.soundLowSeekBar(6);
        Actions.soundMiddleSeekBar(0);
        Actions.soundHighSeekBar(-6);
        Actions.soundHideEffects();
        Actions.soundVolumFollowSpeed(2);
        Actions.soundVolumFollowSpeed(3);
        Actions.soundVolumFollowSpeed(4);
    }
    @Test
    public void _1000_ToggleSettingsToDefault() throws Exception {
        //wifi
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOffWifi();

        //BT
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.turnOnBT(120);
        Actions.btAutoAcceptCall(false);
        Actions.btRingSelect(3);
        Actions.btRingSelect(2);
        Actions.btRingSelect(1);

        //Time
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.switchTimeTo_12H();
        Actions.timeTabGPS_Sync(false);

        //Display
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.displayLockScreenType(2);
        Actions.displayLockScreenType(1);
        Actions.switchLanguageToChinese();
        Actions.displayDayNightMode(2);
        Actions.displayDayNightMode(1);
        Actions.displaySeekLightTo(0);
        Actions.displayThemeSelect(1);
        Actions.displayThreamColorsSelect(2);
        Actions.displayThreamColorsSelect(1);

        //Sound
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.soundShowEffects();
        Actions.soundLowSeekBar(-3);
        Actions.soundMiddleSeekBar(-2);
        Actions.soundHighSeekBar(-4);
        Actions.soundHideEffects();
        Actions.soundVolumFollowSpeed(3);
        Actions.soundVolumFollowSpeed(2);
        Actions.soundVolumFollowSpeed(1);
    }

    @Test
    public void wifiSwitchToggle() throws Exception{
//        mUiDevice.pressHome();
//        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
//        Actions.navToLocationString(Actions.WIFI_TAB_SETTINGS);

        UiObject wifiSwitchObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/wifi_switch"));
        wifiSwitchObj.waitForExists(3000);
        WifiManager wifiManager = (WifiManager) InstrumentationRegistry.getContext()
                .getSystemService(Context.WIFI_SERVICE);
        long testCounter = 0;
        while (testCounter < 10000) {
            testCounter ++;
            if (wifiManager.isWifiEnabled()){//已打开
                Log.d(TAG, "wifiSwitchTest: wifi was on，trunning off...");
                UiAutoLibs.clickSwitchMultiBtn(2, 1, wifiSwitchObj);
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
                        Log.d(TAG, "wifiSwitchTest: wifi trun off ok");
                        currentMills = timeout + 1;
                    }
                }
                assertEquals("trun off wifi fail:assert wifiManager", false, wifiManager.isWifiEnabled());
            } else {
                Log.d(TAG, "wifiSwitchTest: wifi was off , trun on...");
                UiAutoLibs.clickSwitchMultiBtn(2, 2, wifiSwitchObj);
                //指定时间
                long timeout = 10000;
                long startMills = SystemClock.uptimeMillis();
                long currentMills = 0;
                while(currentMills <= timeout){
                    currentMills = SystemClock.uptimeMillis() - startMills;
                    if(timeout > 0) {
                        SystemClock.sleep(100);
                    }
                    UiObject wifiListItemObj = mUiDevice.findObject(new UiSelector().resourceId("com.android.settings:id/wifi_list_item"));
                    if (wifiManager.isWifiEnabled() && wifiListItemObj.exists()) {
                        Log.d(TAG, "wifiSwitchTest: wifi turn on ok");
                        currentMills = timeout + 1;
                    }
                }
                assertEquals("turn on wifi fail", true, wifiManager.isWifiEnabled());
            }
            Log.d(TAG, "wifiSwitchToggle: test Counter = " + testCounter);
        }
    }

    private final UiWatcher alarmWatcher = new UiWatcher() {
        public boolean checkForCondition() {
//            UiObject2 alarmWindows = mUiDevice.findObject(By
//                  .text(Pattern.compile(".*闹钟.*", Pattern.DOTALL)));
            UiObject2 alarmWindows = mUiDevice.findObject(By
                    .res("android:id/alwaysUse"));//wifi连接弹出的对此网络不再询问
            if (alarmWindows != null) {
                try {
                    mUiDevice.findObject(new UiSelector().resourceId("android:id/button1")).click();
                } catch (UiObjectNotFoundException e) {
                    Log.d(TAG, "checkForCondition: 找不到button1");
                }
                return true;
            } else {
                return false;
            }
        }
    };
}
