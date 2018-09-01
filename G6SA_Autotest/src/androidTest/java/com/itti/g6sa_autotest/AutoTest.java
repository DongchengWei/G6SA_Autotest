package com.itti.g6sa_autotest;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutoTest {
    public static final long EXISTS_WAIT_TIME = 1000;//等待存在的等待时长
    public static final String TAG = "Tonsen_Tag";

    private static Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public static UiDevice mUiDevice = UiDevice.getInstance(instrumentation);
    public static Context appContext = InstrumentationRegistry.getTargetContext();
    public static Bundle bundle = InstrumentationRegistry.getArguments();//获取参数


    /**
     * 时间制式检查，是否有12小时和24小时制
     * */
    @Test
    public void _0001_TC_SETUP_0001_timeModeCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.timeModeCheck();
    }

    /**
     * 切换时间模式，12小时制和24小时制切换
     * */
    @Test
    public void _0002_TC_SETUP_0002_timeModeSwitch() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.timeModeChange();
    }

    /**
     * 时间设置
     * */
    @Test
    public void _0003_TC_SETUP_0003_timeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.timeSettings();
    }

    /**
     * 时区设置
     * */
    @Test
    public void _0004_TC_SETUP_0004_timeZoneSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.timeZoneSettings();
    }
    /**
     * gps同步开关，检查打开后时间日期和时区不可设置
     * */
    @Test
    public void _0005_TC_SETUP_0005_gpsSync() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.TIME_TAB_SETTINGS);
        Actions.gpsSyncOn();
    }
    /**
     * 系统语言检查，检查是否有中英文切换开关
     * */
    @Test
    public void _0006_TC_SETUP_0006_systemLanguageCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.systemLanguageCheck();
    }
    /**
     * 系统语言切换，先切英文，后切中文
     * */
    @Test
    public void _0007_TC_SETUP_0007_systemLanguageSwitch() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.systemLanguageSwitch();
    }
    /**
     * 白天模式切换
     * */
    @Test
    public void _0008_TC_SETUP_0012_dayBrightnessModeCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.dayModeCheck();
    }

    /**
     * 夜间模式切换
     * */
    @Test
    public void _0009_TC_SETUP_0013_nightBrightnessModeCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.nightModeCheck();
    }
    /**
     * 自动模式切换
     * */
    @Test
    public void _0010_TC_SETUP_0009_autoBrightnessModeCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.autoModeCheck();
    }
    /**
     * 主题切换
     * */
    @Test
    public void _0011_TC_SETUP_0014_themeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.themeSettings();
    }
    /**
     * 皮肤切换
     * */
    @Test
    public void _0012_TC_SETUP_0015_colorSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.colorSettings();
    }
    /**
     * 行车锁屏
     * */
    @Test
    public void _0013_TC_SETUP_0016_drivingLockScreenCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.DISPLAY_TAB_SETTINGS);
        Actions.drivingLockScreenCheck();
    }
    /**
     * 打开DTS开关
     * */
    @Test
    public void _0014_TC_SETUP_0028_turnOnDTS() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.turnOnDTS();
    }

    /**
     * 关闭DTS开关
     * */
    @Test
    public void _0015_TC_SETUP_0029_turnOffDTS() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.turnOffDTS();
    }
    /**
     * 平衡音调节soundField
     * */
    @Test
    public void _0016_TC_SETUP_0030_soundFieldSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.soundFieldSettings();
    }
    /**
     * 低音调节soundField
     * */
    @Test
    public void _0017_TC_SETUP_0033_bassSoundSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.bassSoundSettings();
    }
    /**
     * 中音调节soundField
     * */
    @Test
    public void _0018_TC_SETUP_0032_middleSoundSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.middleSoundSettings();
    }

    /**
     * 高音调节soundField
     * */
    @Test
    public void _0019_TC_SETUP_0031_trebleSoundSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.trebleSoundSettings();
    }

    /**
     * 音质还原开关检查
     * */
    @Test
    public void _0020_TC_SETUP_00121_soundQualityReductionSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.soundQualityReductionSwitchCheck();
    }

    //开始修改
    /**
     * 主机音量调节
     * */
    @Test
    public void _0021_TC_SETUP_0036_mediaVolumeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.mediaVolumeSettings();
    }
    /**
     * VR音量调节
     * */
    @Test
    public void _0022_TC_SETUP_0040_vrVolumeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.vrVolumeSettings();
    }
    /**
     * 电话音量调节
     * */
    @Test
    public void _0023_TC_SETUP_0039_phoneVolumeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.phoneVolumeSettings();
    }
    /**
     * 导航音量调节
     * */
    @Test
    public void _0024_TC_SETUP_0039_navigationVolumeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.navigationVolumeSettings();
    }
    /**
     * 蓝牙来电铃声
     * */
    @Test
    public void _0025_TC_SETUP_0122_btRingVolumeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.btRingVolumeSettings();
    }
    /**
     * 报警音
     * */
    @Test
    public void _0026_TC_SETUP_0123_alarmVolumeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.alarmVolumeSettings();
    }

    /**
     * 智能语音开关
     * */
    @Test
    public void _0027_TC_SETUP_0124_vrSwitchCheckInVox() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.vrSwitchCheckInVox();
    }

    /**
     * 按键音开关
     * */
    @Test
    public void _0028_TC_SETUP_0125_touchSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.touchSwitchCheck();
    }

    /**
     * 消息提示音开关
     * */
    @Test
    public void _0029_TC_SETUP_0126_messageToneSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.messageToneSwitchCheck();
    }

    /**
     * 车辆警示音设置
     * */
    @Test
    public void _0030_TC_SETUP_0127_carWaringSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.carWaringSettings();
    }

    /**
     * SDVC设置
     * */
    @Test
    public void _0031_TC_SETUP_0041_sdvcVolumeSettings() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SOUND_TAB_SETTINGS);
        Actions.sdvcVolumeSettings();
    }
    /**
     * 获取系统信息
     * */
    @Test
    public void _0032_TC_SETUP_0042_systemVersionInformation() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SYSTEM_TAB_SETTINGS);
        Actions.systemVersionInformation();
    }
    /**
     * 蓝牙名称检查
     * */
    @Test
    public void _0033_TC_SETUP_0046_btNameCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.btNameCheck();
    }
    /**
     * 蓝牙名称检查
     * */
    @Test
    public void _0034_TC_SETUP_0047_btSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.btSwitchCheck();
    }
    /**
     * 蓝牙自动连接检查
     * */
    @Test
    public void _0035_TC_SETUP_0048_btAutoConnectSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.btAutoConnectSwitchCheck();
    }
    /**
     * 蓝牙铃声检查
     * */
    @Test
    public void _0036_TC_SETUP_0049_btRingtonesCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.btRingtonesCheck();
    }
    /**
     * 双蓝牙开关检查
     * */
    @Test
    public void _0037_TC_SETUP_0050_dualBtSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.dualBtSwitchCheck();
    }
    /**
     * 音频连接开关检查
     * */
    @Test
    public void _0038_TC_SETUP_0051_btAudioConnectionSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.btAudioConnectionSwitchCheck();
    }
    /**
     * 两路通话开关检查
     * */
    @Test
    public void _0039_TC_SETUP_0052_btTwoWayCallSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.BT_TAB_SETTINGS);
        Actions.btTwoWayCallSwitchCheck();
    }
    /**
     * 语音唤醒开关检查
     * */
    @Test
    public void _0040_TC_SETUP_0053_voiceWakeUpSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.voiceWakeUpSwitchCheck();
    }

    /**
     * 语音唤醒+识别开关检查
     * */
    @Test
    public void _0041_TC_SETUP_0054_voiceWakeRecongnitionSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.voiceWakeRecongnitionSwitchCheck();
    }

    /**
     * 语音唤醒+识别开关检查
     * */
    @Test
    public void _0042_TC_SETUP_0056_voiceMultipleRoundsOfInteractionSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.voiceMultipleRoundsOfInteractionSwitchCheck();
    }
    /**
     * 语音唤醒命令修改
     * */
    @Test
    public void _0043_TC_SETUP_0055_voiceWakeCommandCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.voiceWakeCommandCheck();
    }
    /**
     * 语音称呼修改
     * */
    @Test
    public void _0044_TC_SETUP_0119_voiceAppellationCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.voiceAppellationCheck();
    }
    /**
     * 发音人
     * */
    @Test
    public void _0045_TC_SETUP_0057_voicePronunciationPeopleCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.VOICE_TAB_SETTINGS);
        Actions.voicePronunciationPeopleCheck();
    }
    /**
     * 系统信息推送
     * */
    @Test
    public void _0046_TC_SETUP_0120_systemMessagePushSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.SYSTEM_TAB_SETTINGS);
        Actions.systemMessagePushSwitchCheck();
    }
    /**
     * 打开wifi开关测试
     * */
    @Test
    public void _0047_TC_SETUP_0059_turnOnWifi() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
    }
    /**
     * 关闭wifi开关测试
     * */
    @Test
    public void _0048_TC_SETUP_0060_turnOffWifi() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOffWifi();
    }
    /**
     * TBOX开关测试
     * */
    @Test
    public void _0049_TC_SETUP_0128_tboxSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.tboxSwitchCheck();
    }
    /**
     * AP开关测试
     * */
    @Test
    public void _0050_TC_SETUP_0129_wifiApSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.wifiApSwitchCheck();
    }
    /**
     * 手机互联切换
     * */
    @Test
    public void _0051_TC_SETUP_0130_mobileInternetCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.mobileInternetCheck();
    }
    /**
     * DLNA开关
     * */
    @Test
    public void _0052_TC_SETUP_0131_dlnaSwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.dlnaSwitchCheck();
    }
    /**
     * CarPlay开关
     * */
    @Test
    public void _0052_TC_SETUP_0132_carPlaySwitchCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.carPlaySwitchCheck();
    }

    /**
     * wifi连接加密wifi
     * */
    @Test
    public void _0053_TC_SETUP_0064_wifiConnectNeedPassword() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
        Actions.connectWifi("mobile", "sv2655888");
    }
    /**
     * wifi连接公共非加密wifi
     * */
    @Test
    public void _0054_TC_SETUP_0063_wifiConnectNeedPassword() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
        Actions.connectWifiNoPassword("guest");
    }
    /**
     * wifis输入密码位数检查
     * */
    @Test
    public void _0055_TC_SETUP_0065_wifiPasswordWidthCheck() throws Exception {
        Actions.navigateBarTo(Actions.SETTINGS_NAVBAR);
        Actions.intoSettingsTab(Actions.WIFI_TAB_SETTINGS);
        Actions.turnOnWifi(5);
        Actions.disconnectWifi();
        Actions.wifiPasswordWidthCheck("mobile");
    }

}
