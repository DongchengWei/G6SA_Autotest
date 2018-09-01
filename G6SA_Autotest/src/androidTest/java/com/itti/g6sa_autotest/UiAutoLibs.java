package com.itti.g6sa_autotest;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by uidq0460 on 2018/5/31.
 */

public class UiAutoLibs {
    public static final String TAG = "Tonsen_Tag";
    private static final long delayTime = 500;//点击动作后延迟

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();


    public static void scrollToBeginByClassName(String str) throws UiObjectNotFoundException {//android.widget.ScrollView
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().className(str));
        uiScrollable.scrollToBeginning(50);
        SystemClock.sleep(delayTime);
    }
    public static void scrollIdFindObjectById(String strId, String findObjIdStr) throws UiObjectNotFoundException {//android.widget.ScrollView
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().resourceId(strId));
        uiScrollable.setMaxSearchSwipes(50);
        uiScrollable.scrollIntoView(new UiSelector().resourceId(findObjIdStr));
        SystemClock.sleep(delayTime);
    }
    public static void scrollClassFindObjectById(String classNameStr, String findObjIdStr) throws UiObjectNotFoundException {//android.widget.ScrollView
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().className(classNameStr));
        uiScrollable.setMaxSearchSwipes(50);
        uiScrollable.scrollIntoView(new UiSelector().resourceId(findObjIdStr));
        SystemClock.sleep(delayTime);
    }
    public static void scrollClassFindObjectByText(String classNameStr, String findObjTextStr) throws UiObjectNotFoundException {//android.widget.ScrollView
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().className(classNameStr));
        uiScrollable.setMaxSearchSwipes(50);
        uiScrollable.scrollIntoView(new UiSelector().textContains(findObjTextStr));
        SystemClock.sleep(delayTime);
    }
    public static void scrollToBeginById(String str) throws UiObjectNotFoundException {//android.widget.ScrollView
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().resourceId(str));
        uiScrollable.scrollToBeginning(50);
        SystemClock.sleep(delayTime);
    }
    public static void scrollToEndByClassName(String str) throws UiObjectNotFoundException {//android.widget.ScrollView
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().className(str));
        uiScrollable.scrollToEnd(50);
        SystemClock.sleep(delayTime);
    }

    public static void clickById(String idStr) throws UiObjectNotFoundException {
        mUiDevice.findObject(new UiSelector().resourceId(idStr)).click();
        SystemClock.sleep(delayTime);
    }
    public static void clickByText(String textStr) throws UiObjectNotFoundException {
        mUiDevice.findObject(new UiSelector().text(textStr)).click();
        SystemClock.sleep(delayTime);
    }
    public static void clickObject(UiObject obj) throws UiObjectNotFoundException {
        obj.click();
        SystemClock.sleep(delayTime);
    }
    public static boolean isCheckById(String idStr) throws UiObjectNotFoundException {
       return mUiDevice.findObject(new UiSelector().resourceId(idStr)).isChecked();
    }

    // 长按物理键
    public static boolean longPressKeyCode(int keyCode,long PressTime) {
        try {

            Field mUiAutomationBridge = Class.forName("android.support.test.uiautomator.UiDevice").getDeclaredField("mUiAutomationBridge");
            mUiAutomationBridge.setAccessible(true);

            Object bridgeObj = mUiAutomationBridge.get(mUiDevice);
            Method injectInputEvent = Class.forName("android.support.test.uiautomator.UiAutomatorBridge")
                    .getDeclaredMethod("injectInputEvent",new Class[]{android.view.InputEvent.class,boolean.class});


            final long eventTime = SystemClock.uptimeMillis();
            KeyEvent downEvent = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN,
                    keyCode, 0, 0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                    InputDevice.SOURCE_KEYBOARD);


            if ((Boolean) injectInputEvent.invoke(bridgeObj, new Object[]{downEvent, true})) {

                Log.d(TAG, "longPressKeyCode: press 26");
                SystemClock.sleep(PressTime);

                KeyEvent upEvent = new KeyEvent(eventTime, eventTime,
                        KeyEvent.ACTION_UP, keyCode, 0, 0,
                        KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                        InputDevice.SOURCE_KEYBOARD);
                if ((Boolean) injectInputEvent.invoke(bridgeObj, new Object[]{upEvent, true})) {
                    return true;
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * 点击开关
     * @param itemsCounter 该switch控件按键数量,最小为1
     * @param witchBtn 需要点击该控件哪个按键，最小为1
     * @param object 控件对象
     * @throws UiObjectNotFoundException
     */
    public static boolean clickSwitchMultiBtn(int itemsCounter, int witchBtn, UiObject object) throws UiObjectNotFoundException{
        int xLeft = object.getBounds().left;
        int xWidth = object.getBounds().width();
        int yCenter = object.getBounds().centerY();
        int btnWidth = xWidth/itemsCounter;//控件上每个按键的宽度
        int btnHalfWidth = (xWidth/itemsCounter)/2;//控件上每个按键一半的宽度
        int targetX = xLeft + btnHalfWidth + (witchBtn-1)*btnWidth;
        Log.d(TAG, "clickSwitchMultiBtn: " + targetX + ", " + yCenter);
        return mUiDevice.click(targetX, yCenter);
    }

    /**
     * 点击进度条
     * @param splitCounter 进度条格数
     * @param seekNum 点击第几格(1开始)
     * @param object 进度条对象
     * @return
     */
    public static boolean clickSeekBar(int splitCounter, int seekNum, UiObject object) throws UiObjectNotFoundException{
        int xLeft = object.getBounds().left;
        int xWidth = object.getBounds().width();
        int yCenter = object.getBounds().centerY();

        int btnWidth = xWidth/(splitCounter);//控件上每格的宽度

        int targetX = xLeft + btnWidth * (seekNum - 1) + btnWidth/2;

        Log.d(TAG, "clickSeekBar: " + targetX + ", " + yCenter);
        return mUiDevice.click(targetX, yCenter);
    }

    public static UiObject getUiobjectById(String idStr) throws UiObjectNotFoundException {
       return mUiDevice.findObject(new UiSelector().resourceId(idStr));
    }
}
