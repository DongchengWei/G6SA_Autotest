package com.itti.g6sa_autotest.ActionsPackages;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.itti.g6sa_autotest.Actions;
import com.itti.g6sa_autotest.Modules.Media;
import com.itti.g6sa_autotest.UiAutoLibs;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @创建者： WDC
 * @创建时间： 2018/9/5 18:47
 */
public class MediaActions {

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

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();

    public static void gotoSource(String sourceStr) throws UiObjectNotFoundException{
        mUiDevice.pressHome();
        Actions.navigateBarTo(Actions.MEDIA_NAVBAR);
        UiObject sourceFlagObj;
        switch (sourceStr){
            case FM:
                break;
            case AM:
                break;
            case NET_FM:
                break;
            case USB1_MUSIC:
                break;
            case USB2_MUSIC:
                sourceFlagObj = mUiDevice.findObject(new UiSelector()
                        .resourceId("com.desaysv_automotive.svmedia:id/tv_music_hall"));
                sourceFlagObj.click();
                UiAutoLibs.clickById("com.desaysv_automotive.svmedia:id/usb2_music");
                break;
            case BT_MUSIC:
                break;
            case IPOD_MUSIC:
                break;
            case ONLINE_MUSIC:
                break;
            case USB1_VIDEO:
                break;
            case USB2_VIDEO:
                break;
            case USB1_PICTURE:
                break;
            case USB2_PICTURE:
                break;
            default:
                assertEquals("没有该source失败，", true, false);
                break;
        }

    }

    public static boolean judgeIsPlaying() throws UiObjectNotFoundException{
        UiObject startTimeOjb = UiAutoLibs.getUiobjectById("com.desaysv_automotive.svmedia:id/tv_time_start");
        UiObject endTimeOjb = UiAutoLibs.getUiobjectById("com.desaysv_automotive.svmedia:id/tv_time_end");
        String startCheckStr = startTimeOjb.getText();
        SystemClock.sleep(2000);
        String endCheckStr = startTimeOjb.getText();
        Log.d(TAG, "judgeIsPlaying: 前=" + startCheckStr + ", 后=" + endCheckStr);
        boolean isOk = false;
        if (startCheckStr.equals(endCheckStr)){
            isOk = false;
        } else {
            isOk = true;
        }
        return isOk;
    }

    public static boolean doPaush() throws UiObjectNotFoundException{
        boolean isOk = true;
        if (judgeIsPlaying()){
            Log.d(TAG, "doPaush: 当前不是暂停，点击暂停");
            UiAutoLibs.clickById("com.desaysv_automotive.svmedia:id/iv_play");
        } else {
            Log.d(TAG, "doPaush: 当前已是暂停");
        }
        SystemClock.sleep(1000);
        if (judgeIsPlaying()){
            isOk = false;
        } else {
            isOk = true;
        }
        return isOk;
    }

    public static boolean doPlay() throws UiObjectNotFoundException{
        boolean isOk = true;
        if (judgeIsPlaying()){
            Log.d(TAG, "doPaush: 当前已是播放状态");
        } else {
            Log.d(TAG, "doPaush: 当前已是暂停");
            UiAutoLibs.clickById("com.desaysv_automotive.svmedia:id/iv_play");
        }
        return judgeIsPlaying();
    }
    public static boolean doNextSong() throws UiObjectNotFoundException{
        boolean isOk = true;
        UiAutoLibs.clickSeekBar(11, 1, UiAutoLibs.getUiobjectById("com.desaysv_automotive.svmedia:id/musicseekBar"));
        UiObject musicTitleObj = UiAutoLibs.getUiobjectById("com.desaysv_automotive.svmedia:id/tv_title");
        String beforeNextName = musicTitleObj.getText();
        UiAutoLibs.clickById("com.desaysv_automotive.svmedia:id/iv_play_next");
        String afterNextSongName = musicTitleObj.getText();
        
        if (beforeNextName.equals(afterNextSongName)){
            Log.d(TAG, "doNext: 下一曲后歌名相同");
            isOk = false;
        } else {
            Log.d(TAG, "doPaush: 下一曲后歌名不相同");
            isOk = true;
        }

        return isOk;
    }

    public static boolean doPreviousSong() throws UiObjectNotFoundException{
        boolean isOk = true;
        mUiDevice.click(271, 436);
//        UiAutoLibs.clickSeekBar(11, 1, UiAutoLibs.getUiobjectById("com.desaysv_automotive.svmedia:id/musicseekBar"));
        UiObject musicTitleObj = UiAutoLibs.getUiobjectById("com.desaysv_automotive.svmedia:id/tv_title");
//        UiObject musicEndTimeObj = UiAutoLibs.getUiobjectById("com.desaysv_automotive.svmedia:id/tv_time_end");
        String beforeNextName = musicTitleObj.getText();

//        UiObject preBtnObj = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/iv_play_pre"));
//        preBtnObj.click();
//        preBtnObj.click();
        try {
            mUiDevice.executeShellCommand("input tap 433 524");
            mUiDevice.executeShellCommand("input tap 433 524");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String afterNextSongName = musicTitleObj.getText();

        Log.d(TAG, "doPreviousSong: before name = " + beforeNextName + ", after name = " + afterNextSongName);
        if (beforeNextName.equals(afterNextSongName)){
            Log.d(TAG, "doNext: 上一曲后歌曲名称相同");
            isOk = false;
        } else {
            Log.d(TAG, "doPaush: 上一曲后歌曲名称不相同");
            isOk = true;
        }

        return isOk;
    }

    public static void musicCheckID3() throws UiObjectNotFoundException {
        String musicTitle = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/tv_title")).getText();
        String musicCdName = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/tv_cd_name")).getText();
        String musicArtistName = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/tv_artist_name")).getText();

        String resultStr = "title="+ musicTitle + ", cdName=" + musicCdName + ", artistName=" + musicArtistName;
        Log.d(TAG, "musicCheckID3: " + resultStr);

        assertEquals("断言歌名不为空:" + resultStr, false, musicTitle.equals(""));
        assertEquals("断言专辑不为空:"+ resultStr, false, musicCdName.equals(""));
        assertEquals("断言艺术家不为空:"+ resultStr, false, musicArtistName.equals(""));
    }

    public static void musicSelectSong() throws UiObjectNotFoundException {

        mUiDevice.click(271, 436);
        UiObject musicTitle = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/tv_title"));
        mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/iv_list_show")).click();
        UiObject theSong = mUiDevice.findObject(new UiSelector().text(musicTitle.getText()));
        assertEquals("断言该歌曲被选中", true, theSong.isSelected());

        UiCollection collList = new UiCollection(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/vedio_list_all"));
        UiObject firstOjb = collList.getChildByInstance(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/music_type_name"), 0);
        Log.d(TAG, "musicSelectSong: firstSong = " + firstOjb.getText());
        if (firstOjb.isSelected()){
            UiObject secondOjb = collList.getChildByInstance(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/music_type_name"), 1);
            String selectSongStr = secondOjb.getText();
            Log.d(TAG, "musicSelectSong: secondSong = " + secondOjb.getText());
            secondOjb.click();
            assertEquals("断言选中歌曲后播放的是选中的歌曲", selectSongStr, musicTitle.getText());
        } else {
            String firstStr = firstOjb.getText();
            firstOjb.click();
            assertEquals("断言选中歌曲后播放的是选中的歌曲", firstStr, musicTitle.getText());
        }

        String musicCdName = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/tv_cd_name")).getText();
        String musicArtistName = mUiDevice.findObject(new UiSelector().resourceId("com.desaysv_automotive.svmedia:id/tv_artist_name")).getText();

        String resultStr = "cdName=" + musicCdName + ", artistName=" + musicArtistName;
        Log.d(TAG, "musicCheckID3: " + resultStr);

        assertEquals("断言专辑不为空:"+ resultStr, false, musicCdName.equals(""));
        assertEquals("断言艺术家不为空:"+ resultStr, false, musicArtistName.equals(""));
    }
}
