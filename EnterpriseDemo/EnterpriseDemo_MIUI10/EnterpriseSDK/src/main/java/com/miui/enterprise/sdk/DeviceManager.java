package com.miui.enterprise.sdk;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Map;

public class DeviceManager {

    /**
     * 默认模式，此模式黑白名单均不生效
     *
     * @see #setBrowserRestriction(int)
     */
    public static final int RESTRICTION_MODE_DEFAULT = 0;

    /**
     * 白名单模式，设置此模式仅白名单生效
     *
     * @see #setBrowserRestriction(int)
     */
    public static final int RESTRICTION_MODE_WHITE_LIST = 1;

    /**
     * 黑名单模式，设置此模式仅黑名单生效
     *
     * @see #setBrowserRestriction(int)
     */
    public static final int RESTRICTION_MODE_BLACK_LIST = 2;

    /**
     * 打上隐水印的截图保存完成后发送广播的ACTION
     */
    public static final String ACTION_HIDDEN_WATERMARK = "com.miui.enterprise.ACTION_WATERMARK";

    public static synchronized DeviceManager getInstance() {
        throw new RuntimeException();
    }

    /**
     * 获取SDK API版本
     *
     * @return 当前政企API版本
     */
    public String getAPIVersion() {
        throw new RuntimeException();
    }

    /**
     * @return 设备是否root
     */
    public boolean isDeviceRoot() {
        throw new RuntimeException();
    }

    /**
     * 关机
     */
    public void deviceShutDown() {
        throw new RuntimeException();
    }

    /**
     * 重启
     */
    public void deviceReboot() {
        throw new RuntimeException();
    }

    /**
     * 格式化sd卡
     */
    public void formatSdCard() {
        throw new RuntimeException();
    }

    /**
     * 域名白名单，目前仅针对系统浏览器生效，在通过{@link #setBrowserRestriction(int)}设置白名单模式后，
     * 非白名单内域名将无法访问
     *
     * @param urls 域名列表
     */
    public void setUrlWhiteList(List<String> urls) {
        throw new RuntimeException();
    }

    public void setUrlWhiteList(List<String> urls, int userId) {
        throw new RuntimeException();
    }

    /**
     * 域名黑名单，目前仅针对系统浏览器生效，在通过{@link #setBrowserRestriction(int)}设置黑名单模式后，
     * 黑名单内域名将无法访问
     *
     * @param urls 域名名单
     */
    public void setUrlBlackList(List<String> urls) {
        throw new RuntimeException();
    }

    public void setUrlBlackList(List<String> urls, int userId) {
        throw new RuntimeException();
    }

    /**
     * @return 域名白名单
     * @see #setUrlWhiteList(List)
     */
    public List<String> getUrlWhiteList() {
        throw new RuntimeException();
    }

    public List<String> getUrlWhiteList(int userId) {
        throw new RuntimeException();
    }

    /**
     * @return 域名黑名单
     * @see #setUrlBlackList(List)
     */
    public List<String> getUrlBlackList() {
        throw new RuntimeException();
    }

    public List<String> getUrlBlackList(int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置域名黑白名单模式
     *
     * @param mode 参阅{@link #RESTRICTION_MODE_DEFAULT}, {@link #RESTRICTION_MODE_BLACK_LIST}. {@link #RESTRICTION_MODE_WHITE_LIST}
     * @see #setUrlWhiteList(List)
     * @see #setUrlBlackList(List)
     */
    public void setBrowserRestriction(int mode) {
        throw new RuntimeException();
    }

    public void setBrowserRestriction(int mode, int userId) {
        throw new RuntimeException();
    }

    /**
     * 截屏
     *
     * @return Bitmap
     */
    public Bitmap captureScreen() {
        throw new RuntimeException();
    }

    /**
     * 重置手机
     *
     * @param formatSdcard 是否格式化外置sd卡
     */
    public void recoveryFactory(boolean formatSdcard) {
        throw new RuntimeException();
    }

    /**
     * 设置开机动画
     *
     * @param path 开机动画在sdcard上的路径
     * @return success 是否设置成功
     */
    public boolean setBootAnimation(String path) {
        throw new RuntimeException();
    }

    /**
     * @param mode   参阅{@link #RESTRICTION_MODE_DEFAULT}, {@link #RESTRICTION_MODE_WHITE_LIST},
     *               {@link #RESTRICTION_MODE_BLACK_LIST}
     * @param userId UserId in a multi-user android system
     */
    public void setWifiConnRestriction(int mode, int userId) {
        throw new RuntimeException();
    }

    public int getWifiConnRestriction(int userId) {
        throw new RuntimeException();
    }

    /**
     * Add ssid of wifi-AP to whitelist which allow to connect
     *
     * @param ssids  White list of ssid
     * @param userId UserId in a multi-user android system
     */
    public void setWifiApSsidWhiteList(List<String> ssids, int userId) {
        throw new RuntimeException();
    }

    /**
     * Add bssid(mac-address) of wifi-AP to whitelist which allow to connect
     *
     * @param bssid  White list of ssid
     * @param userId UserId in a multi-user android system
     */
    public void setWifiApBssidWhiteList(List<String> bssid, int userId) {
        throw new RuntimeException();
    }

    /**
     * Add ssid of wifi-AP to whitelist which allow to connect
     *
     * @param ssids  White list of ssid
     * @param userId UserId in a multi-user android system
     */
    public void setWifiApSsidBlackList(List<String> ssids, int userId) {
        throw new RuntimeException();
    }

    /**
     * Add bssid(mac-address) of wifi-AP to blacklist which allow to connect
     *
     * @param bssid  Black list of ssid
     * @param userId UserId in a multi-user android system
     */
    public void setWifiApBssidBlackList(List<String> bssid, int userId) {
        throw new RuntimeException();
    }

    /**
     * @param userId UserId in a multi-user android system
     * @return White list of Wifi-Ap ssid
     * @see #setWifiApSsidWhiteList(List, int)
     */
    public List<String> getWifiApSsidWhiteList(int userId) {
        throw new RuntimeException();
    }

    /**
     * @param userId UserId in a multi-user android system
     * @return White list of Wifi-Ap Bssid(mac)
     * @see #setWifiApBssidWhiteList(List, int)
     */
    public List<String> getWifiApBssidWhiteList(int userId) {
        throw new RuntimeException();
    }

    /**
     * @param userId UserId in a multi-user android system
     * @return Black list of Wifi-Ap ssid
     * @see #setWifiApSsidBlackList(List, int)
     */
    public List<String> getWifiApSsidBlackList(int userId) {
        throw new RuntimeException();
    }

    /**
     * @param userId UserId in a multi-user android system
     * @return Black list of Wifi-Ap Bssid(mac)
     * @see #setWifiApBssidBlackList(List, int)
     */
    public List<String> getWifiApBssidBlackList(int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置壁纸
     *
     * @param bitmap 壁纸bitmap
     */
    public void setWallPaper(Bitmap bitmap) {
        throw new RuntimeException();
    }

    /**
     * 设置锁屏壁纸
     *
     * @param bitmap 锁屏壁纸bitmap
     */
    public void setLockWallPaper(Bitmap bitmap) {
        throw new RuntimeException();
    }

    /**
     * 设置IP限制模式
     *
     * @param mode   参阅{@link #RESTRICTION_MODE_DEFAULT}, {@link #RESTRICTION_MODE_WHITE_LIST},
     *               {@link #RESTRICTION_MODE_BLACK_LIST}
     * @param userId 用户空间 ID
     */
    public void setIpRestriction(int mode, int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置IP白名单
     *
     * @param list   IP 白名单列表
     * @param userId 用户ID
     */
    public void setIpWhiteList(List<String> list, int userId) {
        throw new RuntimeException();
    }

    public List<String> getIpWhiteList(int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置IP黑名单
     *
     * @param list   IP黑名单列表
     * @param userId 用户空间ID
     */
    public void setIpBlackList(List<String> list, int userId) {
        throw new RuntimeException();
    }

    public List<String> getIpBlackList(int userId) {
        throw new RuntimeException();
    }

    /**
     * The ringer mode, one of AudioManager.RINGER_MODE_NORMAL,
     * AudioManager.RINGER_MODE_SILENT, or AudioManager.RINGER_MODE_VIBRATE.
     *
     * @param ringerMode ringerMode
     */
    public void setRingerMode(int ringerMode) {
        throw new RuntimeException();
    }

    public void enableUsbDebug(boolean enable) {
        throw new RuntimeException();
    }

    /**
     * 设置为true 则截图保存时自动附加隐藏水印
     * 设置为false 截图不隐藏任何信息
     *
     * @param status status of HiddenWatermark
     * @param userId UserId in a multi-user android system
     */
    public void setHiddenWatermark(boolean status, int userId) {
        throw new RuntimeException();
    }

    /**
     * 返回当前触发截图是否附加隐藏信息
     *
     * @param userId UserId in a multi-user android system
     * @return status of HiddenWatermark
     * @see #setHiddenWatermark(boolean, int)
     */
    public boolean getHiddenWatermark(int userId) {
        throw new RuntimeException();
    }

    /**
     * 抓取系统上层日志，不包含 modem 日志
     */
    public void bugReport() {
        throw new RuntimeException();
    }

}
