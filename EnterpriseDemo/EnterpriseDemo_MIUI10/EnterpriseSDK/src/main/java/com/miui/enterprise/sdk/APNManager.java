package com.miui.enterprise.sdk;

import java.util.List;

public class APNManager {

    /**
     * 获取APNManager实例
     *
     * @return APNManager实例
     */
    public static synchronized APNManager getInstance() {
        throw new RuntimeException();
    }

    /**
     * 默认状态, 运行用户手动切换APN配置
     *
     * @see #setAPNActiveMode(int)
     */
    public static final int MODE_DEFAULT = 0;

    /**
     * APN配置只能通过API切换，API见 {@link #activeAPN(String, String)}
     */
    public static final int MODE_RESTRICTED = 1;

    /**
     * 获取指定运营商的APN列表
     *
     * @param numeric 运营商numeric
     * @return 指定运营商的APN列表，参阅{@link APNConfig}
     */
    public List<APNConfig> getAPNList(String numeric) {
        throw new RuntimeException();
    }

    /**
     * 获取当前默认卡的APN列表
     *
     * @return APN配置，参阅{@link APNConfig}
     */
    public List<APNConfig> getAPNList() {
        throw new RuntimeException();
    }

    /**
     * 为指定运营商添加APN配置
     *
     * @param numeric   运营商numeric
     * @param apnConfig APN配置，参阅{@link APNConfig}
     */
    public void addAPN(String numeric, APNConfig apnConfig) {
        throw new RuntimeException();
    }

    /**
     * 查询APN配置
     *
     * @param name APN配置名称
     * @return APN配置，参阅{@link APNConfig}
     */
    public APNConfig getAPN(String name) {
        throw new RuntimeException();
    }

    /**
     * 查询指定运营商配置
     *
     * @param numeric 运营商numeric
     * @param name    APN配置名称
     * @return APN配置，参阅{@link APNConfig}
     */
    public APNConfig getAPN(String numeric, String name) {
        throw new RuntimeException();
    }

    /**
     * 为当前默认卡运营商添加APN配置
     *
     * @param apnConfig APN配置，参阅{@link APNConfig}
     */
    public void addAPN(APNConfig apnConfig) {
        throw new RuntimeException();
    }

    /**
     * 删除指定运营商APN配置
     *
     * @param numeric 运营商numeric
     * @param name    APN配置name，参阅{@link APNConfig}
     */
    public void deleteAPN(String numeric, String name) {
        throw new RuntimeException();
    }

    /**
     * 删除当前默认卡运营商APN配置
     *
     * @param name APN配置name
     * @return 操作是否成功
     */
    public boolean deleteAPN(String name) {
        throw new RuntimeException();
    }

    /**
     * 更新指定运营商APN配置
     *
     * @param numeric 运营商numeric
     * @param name    APN配置name
     * @param config  新的APN配置，参阅{@link APNConfig}
     */
    public void editAPN(String numeric, String name, APNConfig config) {
        throw new RuntimeException();
    }

    /**
     * 更新默认卡运营商的APN配置
     *
     * @param name   APN配置name，参阅{@link APNConfig}
     * @param config 参阅{@link APNConfig}
     * @return 操作是否成功
     */
    public boolean editAPN(String name, APNConfig config) {
        throw new RuntimeException();
    }

    /**
     * 激活指定运营商APN配置
     *
     * @param numeric 运营商numeric
     * @param name    APN配置name，参阅{@link APNConfig}
     */
    public void activeAPN(String numeric, String name) {
        throw new RuntimeException();
    }

    /**
     * 激活默认卡运营商APN配置
     *
     * @param name 参阅{@link APNConfig}
     * @return 操作是否成功
     */
    public boolean activeAPN(String name) {
        throw new RuntimeException();
    }

    /**
     * 重启系统APN配置
     *
     * @return 操作是否成功
     */
    public boolean resetAPN() {
        throw new RuntimeException();
    }

    /**
     * 配置APN激活模式
     *
     * @param mode {@link #MODE_DEFAULT}: 非管控模式，用户可手动切换APN配置；{@link #MODE_RESTRICTED}: 用户无法手动切换
     */
    public void setAPNActiveMode(int mode) {
        throw new RuntimeException();
    }

    /**
     * 获取当前APN切换模式
     *
     * @return {@link #MODE_DEFAULT}: 非管控模式，用户可手动切换APN配置；{@link #MODE_RESTRICTED}: 用户无法手动切换
     */
    public int getAPNActiveMode() {
        throw new RuntimeException();
    }

}
