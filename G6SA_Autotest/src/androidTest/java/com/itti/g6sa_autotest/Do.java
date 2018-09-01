package com.itti.g6sa_autotest;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Do {
    public static final String TAG = "Tonsen_Tag";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();//获取参数

    /**
     * 不起作用了2018-08-13
     * */
    @Test
    public void _0001_longPressPowerButton() throws Exception {
        UiAutoLibs.longPressKeyCode(26, 5000);
    }
}
