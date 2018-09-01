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
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class G6SA_Pressure {
    public static final String TAG = "Tonsen_Tag";
    public static final String SEPERATE_LINE = "---------------------------------------------------";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();//获取参数

    @Before
    public void setUp(){
        mUiDevice.registerWatcher("crashHomeWatch", crashHomeWatch);
        Log.d(TAG, "setUp: registerWatcher");
    }

    @After
    public void tearDown() {
        mUiDevice.removeWatcher("crashHomeWatch");
        Log.d(TAG, "tearDown: removeWatcher");
    }

    private final UiWatcher crashHomeWatch = new UiWatcher() {
        public boolean checkForCondition() {
//            UiObject2 alarmWindows = mUiDevice.findObject(By
//                  .text(Pattern.compile(".*闹钟.*", Pattern.DOTALL)));
            //Apps,应用
            UiObject appsObj = mUiDevice.findObject(new UiSelector().description("Apps"));
            UiObject appsChObj = mUiDevice.findObject(new UiSelector().description("应用"));
            if (appsObj.exists() || appsChObj.exists()) {
                Log.d(TAG, "checkForCondition: Crash to home");
                Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
                try {
                    Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                return false;
            }
        }
    };

    @Test
    public void _001_btSwitchTest() throws Exception {
        long startTestTime = System.currentTimeMillis();
        //获取参数
        String testTimesStr = bundle.getString("TestTimes");
        Log.d(TAG, "_001_btSwitchTest: testTiems = " + testTimesStr);
        long testTimes = 1;
        if (testTimesStr != null && !testTimesStr.equals("")) {
            try {
                 testTimes = Long.parseLong(testTimesStr);
            } catch (NumberFormatException e) {
                Log.e(TAG, "_001_btSwitchTest: TestTimes input error, must be number");
                e.printStackTrace();
            }
        }
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        boolean keepTest = true;
//        long testCounter = 0;
        long testPassCounter = 0;
//        long testFailCounter = 0;
        while(keepTest) {
            Actions.turnOnBT(120);
            Actions.turnOffBT();
            testPassCounter ++;
            Log.d(TAG, "_001_btSwitchTest: " + SEPERATE_LINE);
            Log.d(TAG, "_001_btSwitchTest: 已测试：" + testPassCounter
                    + "次， PASS："
                    + testPassCounter
                    + "次， 测试目标：" + testTimes + "次");
            Log.d(TAG, "_002_wifiSwitchTest: CostTime:"
                    + Utils.millisToTime(System.currentTimeMillis() - startTestTime));
            Log.d(TAG, "_001_btSwitchTest: " + SEPERATE_LINE);
            if (testPassCounter >= testTimes) {
                keepTest = false;
            }
        }

    }

    @Test
    public void _002_wifiSwitchTest() throws Exception {
        long startTestTime = System.currentTimeMillis();
        //获取参数
        String testTimesStr = bundle.getString("TestTimes");
        Log.d(TAG, "_001_btSwitchTest: testTiems = " + testTimesStr);
        long testTimes = 1;
        if (testTimesStr != null && !testTimesStr.equals("")) {
            try {
                 testTimes = Long.parseLong(testTimesStr);
            } catch (NumberFormatException e) {
                Log.e(TAG, "_002_wifiSwitchTest: TestTimes input error, must be number");
                e.printStackTrace();
            }
        }
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        boolean keepTest = true;
//        long testCounter = 0;
        long testPassCounter = 0;
//        long testFailCounter = 0;
        while(keepTest) {
            Actions.turnOnWifi(5);
            Actions.turnOffWifi();
            testPassCounter ++;
            Log.d(TAG, "_002_wifiSwitchTest: " + SEPERATE_LINE);
            Log.d(TAG, "_002_wifiSwitchTest: 已测试：" + testPassCounter
                    + "次， PASS："
                    + testPassCounter
                    + "次， 测试目标：" + testTimes + "次");
            Log.d(TAG, "_002_wifiSwitchTest: CostTime:"
                    + Utils.millisToTime(System.currentTimeMillis() - startTestTime));
            Log.d(TAG, "_002_wifiSwitchTest: " + SEPERATE_LINE);
            if (testPassCounter >= testTimes) {
                keepTest = false;
            }
        }
    }

    @Test
    public void _003_btOnAutoConnect() throws Exception {
        long startTestTime = System.currentTimeMillis();
        //获取参数
        String testTimesStr = bundle.getString("TestTimes");
        Log.d(TAG, "_003_btOnAutoConnect: testTiems = " + testTimesStr);
        long testTimes = 1;
        if (testTimesStr != null && !testTimesStr.equals("")) {
            try {
                testTimes = Long.parseLong(testTimesStr);
            } catch (NumberFormatException e) {
                Log.e(TAG, "_003_btOnAutoConnect: TestTimes input error, must be number");
                e.printStackTrace();
            }
        }
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        boolean keepTest = true;
        long testPassCounter = 0;
        while(keepTest) {
            UiScrollable scrollable = new UiScrollable(new UiSelector().className("android.widget.ScrollView"));
            scrollable.flingToBeginning(5);
            Actions.turnOffBT();
            Actions.turnOnBT(120);
            Actions.turnBTAutoConnect(true, 3000);
            Actions.judgeBtConnected(20000);
            testPassCounter ++;
            Log.d(TAG, "_002_wifiSwitchTest: " + SEPERATE_LINE);
            Log.d(TAG, "_002_wifiSwitchTest: 已测试：" + testPassCounter
                    + "次， PASS："
                    + testPassCounter
                    + "次， 测试目标：" + testTimes + "次");
            Log.d(TAG, "_002_wifiSwitchTest: CostTime:"
                    + Utils.millisToTime(System.currentTimeMillis() - startTestTime));
            Log.d(TAG, "_002_wifiSwitchTest: " + SEPERATE_LINE);
            if (testPassCounter >= testTimes) {
                keepTest = false;
            }
        }
    }

    @Test
    public void _004_wifiOnAutoConnect() throws Exception {
        long startTestTime = System.currentTimeMillis();
        //获取参数
        String testTimesStr = bundle.getString("TestTimes");
        Log.d(TAG, "_004_wifiOnAutoConnect: testTiems = " + testTimesStr);
        long testTimes = 1;
        if (testTimesStr != null && !testTimesStr.equals("")) {
            try {
                testTimes = Long.parseLong(testTimesStr);
            } catch (NumberFormatException e) {
                Log.e(TAG, "_004_wifiOnAutoConnect: TestTimes input error, must be number");
                e.printStackTrace();
            }
        }
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
        Actions.connectWifi("mobile", "sv2655888");
        boolean keepTest = true;
        long testPassCounter = 0;
        while(keepTest) {
            Actions.turnOffWifi();
            Actions.turnOnWifi(5);
            Actions.judgeWifiConnect(20000);
            testPassCounter ++;
            Log.d(TAG, "_004_wifiOnAutoConnect: " + SEPERATE_LINE);
            Log.d(TAG, "_004_wifiOnAutoConnect: 已测试：" + testPassCounter
                    + "次， PASS："
                    + testPassCounter
                    + "次， 测试目标：" + testTimes + "次");
            Log.d(TAG, "_004_wifiOnAutoConnect: CostTime:"
                    + Utils.millisToTime(System.currentTimeMillis() - startTestTime));
            Log.d(TAG, "_004_wifiOnAutoConnect: " + SEPERATE_LINE);
            if (testPassCounter >= testTimes) {
                keepTest = false;
            }
        }
    }

    @Test
    public void _005_wifiKeepConnected() throws Exception {
        long startTestTime = System.currentTimeMillis();
        //获取参数
        String testTimesStr = bundle.getString("TestTimes");
        Log.d(TAG, "_005_wifiKeepConnected: testTiems = " + testTimesStr + "分钟");
        long testTimes = 1;
        if (testTimesStr != null && !testTimesStr.equals("")) {
            try {
                testTimes = Long.parseLong(testTimesStr);
            } catch (NumberFormatException e) {
                Log.e(TAG, "_005_wifiKeepConnected: TestTimes input error, must be number");
                e.printStackTrace();
            }
        }
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
        Actions.connectWifi("mobile", "sv2655888");
        boolean keepTest = true;
        long testPassCounter = 0;
        while(keepTest) {
            Log.d(TAG, "_005_wifiKeepConnected: 等待5秒");
            SystemClock.sleep(5000);
            Actions.judgeWifiConnect(20000);
            testPassCounter ++;
            Log.d(TAG, "_005_wifiKeepConnected: " + SEPERATE_LINE);
            Log.d(TAG, "_005_wifiKeepConnected: 已测试：" + testPassCounter
                    + "次， PASS："
                    + testPassCounter
                    + "次， 测试目标：" + testTimes + "分钟");
            Log.d(TAG, "_005_wifiKeepConnected: CostTime:"
                    + Utils.millisToTime(System.currentTimeMillis() - startTestTime));
            Log.d(TAG, "_005_wifiKeepConnected: " + SEPERATE_LINE);
            if ((System.currentTimeMillis() - startTestTime) >= (testTimes * 60 * 1000)) {
                keepTest = false;
            }
        }
    }

    /**
     * 进入退出TOD检查wifi是否保持连接
     * */
    @Test
    public void _006_TodOnOffCheckWifi() throws Exception {
        long startTestTime = System.currentTimeMillis();
        //获取参数
        String testTimesStr = bundle.getString("TestTimes");
        Log.d(TAG, "_006_TodOnOffCheckWifi: testTiems = " + testTimesStr);
        long testTimes = 1;
        if (testTimesStr != null && !testTimesStr.equals("")) {
            try {
                testTimes = Long.parseLong(testTimesStr);
            } catch (NumberFormatException e) {
                Log.e(TAG, "_006_TodOnOffCheckWifi: TestTimes input error, must be number");
                e.printStackTrace();
            }
        }
        Actions.amActivity("com.android.settings/com.desaysv.hmi.SvSettings");
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
        Actions.connectWifi("mobile", "sv2655888");
        boolean keepTest = true;
        long testPassCounter = 0;
        while(keepTest) {

            mUiDevice.pressKeyCode(26);//进入TOD
            Actions.judgeIsTodPage(true, 5000);
            SystemClock.sleep(10000);
            mUiDevice.pressKeyCode(26);//退出TOD
            Actions.judgeIsTodPage(false, 5000);
            SystemClock.sleep(10000);
            Actions.judgeWifiConnect(5000);
            testPassCounter ++;
            Log.d(TAG, "_006_TodOnOffCheckWifi: " + SEPERATE_LINE);
            Log.d(TAG, "_006_TodOnOffCheckWifi: 已测试：" + testPassCounter
                    + "次， PASS："
                    + testPassCounter
                    + "次， 测试目标：" + testTimes + "次");
            Log.d(TAG, "_006_TodOnOffCheckWifi: CostTime:"
                    + Utils.millisToTime(System.currentTimeMillis() - startTestTime));
            Log.d(TAG, "_006_TodOnOffCheckWifi: " + SEPERATE_LINE);
            if (testPassCounter >= testTimes) {
                keepTest = false;
            }
        }
    }

    @Test
    public void _0007_keepBtOnPhone() throws UiObjectNotFoundException {
        //获取参数
        String testTimesStr = bundle.getString("TestTime");
        Log.d(TAG, "_0004_judgeBtOnPhone: CheckTime = " + testTimesStr);
        long testTimes = 1;//单位分钟
        if (testTimesStr != null && !testTimesStr.equals("")) {
            try {
                testTimes = Long.parseLong(testTimesStr);
            } catch (NumberFormatException e) {
                Log.e(TAG, "_0004_judgeBtOnPhone: TestTimes input error, must be number");
                e.printStackTrace();
            }
        }


        UiObject callNameObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.desay_svautomotive.svbtphone:id/tvCallName"));
        UiObject callNumObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.desay_svautomotive.svbtphone:id/tvCallNumber"));
        UiObject callStatusObj = mUiDevice.findObject(new UiSelector()
                .resourceId("com.desay_svautomotive.svbtphone:id/tvCallState"));

        String firstCallName = callNameObj.getText();
        String firstCallNum = callNumObj.getText();
        String firstCallStatus = callStatusObj.getText();


        String secondCallName = "";
        String secondCallNum = "";
        String secondCallStatus = "";

        boolean isOk = true;
        long timeout = testTimes * 60 *1000;
        Bundle bundle = new Bundle();
        //指定时间
        long startMills = SystemClock.uptimeMillis();
        long currentMills = 0;
        long testCounter = 0;
        while(currentMills <= timeout){
            testCounter ++;
            currentMills = SystemClock.uptimeMillis() - startMills;
            if(timeout > 0) {
                SystemClock.sleep(3000);
            }
            secondCallName = callNameObj.getText();
            secondCallNum = callNumObj.getText();
            secondCallStatus = callStatusObj.getText();
            Log.d(TAG, "_0004_judgeBtOnPhone: real CallName=" + secondCallName
                    + ", real CallNum=" + secondCallNum
                    + ", real CallStatus=" + secondCallStatus);

            if (secondCallName.equals(firstCallName) && secondCallNum.equals(firstCallNum) && !secondCallStatus.equals(firstCallStatus)) {
                firstCallName = secondCallName;
                firstCallNum = secondCallNum;
                firstCallStatus = secondCallStatus;
                Log.d(TAG, "_0004_judgeBtOnPhone: normal ");
                bundle.putString("CostTime", "CostTime="
                        + Utils.millisToTime(SystemClock.uptimeMillis() - startMills)
                        + ", TestedCounter=" + testCounter);
                instrumentation.sendStatus(815, bundle);
            } else {
                isOk = false;
                currentMills = timeout + 1;
            }
        }
        assertEquals("OnPhone abnormal:", true, isOk);

    }

}
