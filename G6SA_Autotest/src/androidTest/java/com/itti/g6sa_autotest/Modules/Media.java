package com.itti.g6sa_autotest.Modules;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.itti.g6sa_autotest.ActionsPackages.MediaActions;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

/**
 * @创建者： WDC
 * @创建时间： 2018/9/5 18:37
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Media {
    public static final long EXISTS_WAIT_TIME = 1000;//等待存在的等待时长
    public static final String TAG = "Tonsen_Tag";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();//获取参数

    /**
     * 播放USB音乐
     * */
    @Test
    public void _0001_TC_MUSIC_0001_gotoUSB2AutoPlay() throws Exception {
        MediaActions.gotoSource(MediaActions.USB2_MUSIC);
        assertEquals("断言正在播放", true, MediaActions.judgeIsPlaying());
    }
    /**
     * 暂停
     * */
    @Test
    public void _0002_TC_MUSIC_0002_doPaushSong() throws Exception {
        MediaActions.gotoSource(MediaActions.USB2_MUSIC);
        assertEquals("断言暂停播放", true, MediaActions.doPaush());
    }
    /**
     * 播放
     * */
    @Test
    public void _0003_TC_MUSIC_0003_doPlaySong() throws Exception {
        MediaActions.gotoSource(MediaActions.USB2_MUSIC);
        assertEquals("断言暂停播放", true, MediaActions.doPaush());
        assertEquals("断言开始播放", true, MediaActions.doPlay());
    }
    /**
     * 下一曲
     * */
    @Test
    public void _0004_TC_MUSIC_0004_doNextSong() throws Exception {
        MediaActions.gotoSource(MediaActions.USB2_MUSIC);
        assertEquals("断言下一曲", true, MediaActions.doNextSong());
    }
    /**
     * 上一曲
     * */
    @Test
    public void _0005_TC_MUSIC_0005_doPreviousSong() throws Exception {
        MediaActions.gotoSource(MediaActions.USB2_MUSIC);
        assertEquals("断言上一曲", true, MediaActions.doPreviousSong());
    }
    /**
     * ID3检查
     * */
    @Test
    public void _0006_TC_MUSIC_0006_musicCheckID3() throws Exception {
        MediaActions.gotoSource(MediaActions.USB2_MUSIC);
        MediaActions.musicCheckID3();
    }
    /**
     * 选曲播放
     * */
    @Test
    public void _0007_TC_MUSIC_0007_musicSelectSong() throws Exception {
        MediaActions.gotoSource(MediaActions.USB2_MUSIC);
        MediaActions.musicSelectSong();
    }



}
