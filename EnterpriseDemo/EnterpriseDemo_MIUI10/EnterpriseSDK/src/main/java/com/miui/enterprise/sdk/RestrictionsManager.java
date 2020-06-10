package com.miui.enterprise.sdk;

public class RestrictionsManager {

    /**
     * 功能禁用
     *
     * @see #setControlStatus(String, int)
     */
    public static final int DISABLE = 0;
    /**
     * 功能启用（不管控）
     *
     * @see #setControlStatus(String, int)
     */
    public static final int ENABLE = 1;
    /**
     * 在启用状态下，开启功能开关
     *
     * @see #setControlStatus(String, int)
     */
    public static final int OPEN = 2;
    /**
     * 在启用状态下，关闭功能开关
     *
     * @see #setControlStatus(String, int)
     */
    public static final int CLOSE = 3;
    /**
     * 强制开启功能开关，无法手动关闭
     *
     * @see #setControlStatus(String, int)
     */
    public static final int FORCE_OPEN = 4;

    /**
     * 管控系统升级
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_SYSTEM_UPDATE = "system_update";

    /**
     * <p>管控WiFi功能</p>
     * <p>管控状态参阅: {@link #DISABLE}, {@link #ENABLE}, {@link #OPEN}, {@link #CLOSE}, {@link #FORCE_OPEN}</P>
     *
     * @see #setControlStatus(String, int)
     * @see #getControlStatus(String)
     */
    public static final String WIFI_STATE = "wifi_state";

    /**
     * 管控VPN功能，设置该项管控时，VPN无法开启
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_VPN = "disallow_vpn";

    /**
     * 管控热点功能，设置该项管控时，热点无法开启
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_TETHER = "disallow_tether";

    /**
     * <p>管控蓝牙功能</p>
     * <p>管控状态参阅: {@link #DISABLE}, {@link #ENABLE}, {@link #OPEN}, {@link #CLOSE}, {@link #FORCE_OPEN}</p>
     *
     * @see #setControlStatus(String, int)
     * @see #getControlStatus(String)
     */
    public static final String BLUETOOTH_STATE = "bluetooth_state";

    /**
     * <p>管控GPS功能</p>
     * <p>管控状态参阅: {@link #DISABLE}, {@link #ENABLE}, {@link #OPEN}, {@link #CLOSE}, {@link #FORCE_OPEN}</p>
     *
     * @see #setControlStatus(String, int)
     * @see #getControlStatus(String)
     */
    public static final String GPS_STATE = "gps_state";

    /**
     * <p>管控飞行模式</p>
     * <p>管控状态参阅: {@code int}, one of {@link #DISABLE}, {@link #ENABLE}, {@link #OPEN}, {@link #CLOSE}, {@link #FORCE_OPEN}</p>
     *
     * @see #setControlStatus(String, int)
     * @see #getControlStatus(String)
     */
    public static final String AIRPLANE_STATE = "airplane_state";

    /**
     * 管控相机，设置该项管控时，系统相机无法使用
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_CAMERA = "disallow_camera";

    /**
     * 管控录音功能，设置该项管控时，系统无法录音
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_MICROPHONE = "disallow_microphone";

    /**
     * 管控系统截屏，设置该项管控时，系统截屏无法使用
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_SCREENCAPTURE = "disallow_screencapture";

    /**
     * 管控SD卡挂载，设置该项管控时，SD卡无法挂载
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_SDCARD = "disallow_sdcard";

    /**
     * 管控MTP功能，设置该项管控时，MTP无法开启
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_MTP = "disallow_mtp";

    /**
     * <p>管控NFC功能</p>
     * <p>管控状态参阅: {@code int}, one of {@link #DISABLE}, {@link #ENABLE}, {@link #OPEN}, {@link #CLOSE}, {@link #FORCE_OPEN}</p>
     *
     * @see #setControlStatus(String, int)
     * @see #getControlStatus(String)
     */
    public static final String NFC_STATE = "nfc_state";

    /**
     * 管控OTG功能，设置该项管控时OTG设备无法挂载
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_OTG = "disallow_otg";

    /**
     * 管控USB外设
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_USB_DEVICE = "disable_usb_device";

    /**
     * 管控指纹功能，设置该项管控时，指纹功能无法使用
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_FINGERPRINT = "disallow_fingerprint";

    /**
     * USB调试功能，设置该项管控时，USB调试功能无法开启
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_USBDEBUG = "disallow_usbdebug";

    /**
     * 管控系统重置功能，设置该项管控时，系统重置功能无法使用，
     * {@link com.miui.enterprise.sdk.DeviceManager#recoveryFactory(boolean)}不受限制
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_FACTORYRESET = "disallow_factoryreset";

    /**
     * 管控时间修改功能，设置该项管控时，系统时间无法被手动更改
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_TIMESET = "disallow_timeset";

    /**
     * 管控IMEI读取，设置该项管控时，所有电话相关信息无法通过Android标准API读取
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_IMEIREAD = "disallow_imeiread";

    /**
     * 管控加速度传感器，设置该项管控时，加速度传感器无法使用
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISABLE_ACCELEROMETER = "disable_accelerometer";

    /**
     * 管控系统备份功能，设置该项管控时，系统备份功能无法使用
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_BACKUP = "disallow_backup";

    /**
     * 管控系统同步功能，设置该项管控时，无法进行云同步
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_AUTO_SYNC = "disallow_auto_sync";

    /**
     * 管控安全模式，设置该项管控时，安全模式无法开启
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_SAFE_MODE = "disallow_safe_mode";

    /**
     * 管控语言修改，设置该项管控时，无法更改系统语言
     *
     * @see #setRestriction(String, boolean)
     * @see #hasRestriction(String)
     */
    public static final String DISALLOW_CHANGE_LANGUAGE = "disallow_change_language";

    public static synchronized RestrictionsManager getInstance() {
        throw new RuntimeException();
    }

    /**
     * 管控系统功能
     *
     * @param key   参阅类常量
     * @param value 参阅{@link #ENABLE}, {@link #DISABLE}, {@link #OPEN}, {@link #CLOSE}, {@link #FORCE_OPEN}
     */
    public void setControlStatus(String key, int value) {
        throw new RuntimeException();
    }

    public void setControlStatus(String key, int value, int userId) {
        throw new RuntimeException();
    }

    /**
     * 管控系统功能
     *
     * @param key   参阅常量列表
     * @param value 为true时功能为管控状态，无法使用
     */
    public void setRestriction(String key, boolean value) {
        throw new RuntimeException();
    }

    public void setRestriction(String key, boolean value, int userId) {
        throw new RuntimeException();
    }

    /**
     * @param key 参阅常量列表
     * @return 参阅{@link #ENABLE}, {@link #DISABLE}, {@link #OPEN}, {@link #CLOSE}, {@link #FORCE_OPEN}
     * @see #setControlStatus(String, int)
     */
    public int getControlStatus(String key) {
        throw new RuntimeException();
    }

    public int getControlStatus(String key, int userId) {
        throw new RuntimeException();
    }

    /**
     * @param key 参阅常量列表
     * @return 为true时功能为管控状态，无法使用
     * @see #setRestriction(String, boolean)
     */
    public boolean hasRestriction(String key) {
        throw new RuntimeException();
    }

    public boolean hasRestriction(String key, int userId) {
        throw new RuntimeException();
    }
}
