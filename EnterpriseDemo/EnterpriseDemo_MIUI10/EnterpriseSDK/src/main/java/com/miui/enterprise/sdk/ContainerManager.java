package com.miui.enterprise.sdk;

public class ContainerManager {

    public static final String ENABLE_SWITCH_USER = "enable_switch_user";

    public synchronized static ContainerManager getInstance() {
        throw new RuntimeException();
    }

    /**
     * 根据userId切换到对应域
     *
     * @param userId 空间ID
     * @return 是否切换成功
     */
    public boolean switchUser(int userId) {
        throw new RuntimeException();
    }

    /**
     * 获取第二域(工作域)的userId
     *
     * @return 返回空间ID
     */
    public int getSecuritySpaceId() {
        throw new RuntimeException();
    }

    /**
     * 不允许域切换
     */
    public void disableSwitching() {
        throw new RuntimeException();
    }

    /**
     * 允许域切换
     */
    public void enableSwitching() {
        throw new RuntimeException();
    }

    /**
     * 是否允许域切换
     *
     * @return boolean 是否允许域切换
     */
    public boolean isEnableSwitching() {
        throw new RuntimeException();
    }

    /**
     * 强制域切换
     *
     * @param userId        切换空间ID
     * @param isForceSwitch 是否强制域切换
     */
    public void forceSpaceSwitch(int userId, boolean isForceSwitch) {
        throw new RuntimeException();
    }

    /**
     * @return boolean
     */
    public boolean isInFgContainer() {
        throw new RuntimeException();
    }

    /**
     * 支持的域个数，目前支持2个域
     *
     * @return int
     */
    public int getContainerNumber() {
        throw new RuntimeException();
    }
}