package com.miui.enterprise.sdk;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver2;

import java.util.List;

public class ApplicationManager {

    /**
     * 数据备份完成广播
     *
     * @see #backupData
     */
    public static final String ACTION_BACKUP_FINISH = "com.miui.enterprise.ACTION_BACKUP_FINISH";

    /**
     * 数据备份失败广播
     *
     * @see #backupData
     */
    public static final String ACTION_BACKUP_FAILED = "com.miui.enterprise.ACTION_BACKUP_FAILED";

    /**
     * 数据备份广播,extra字段 requestId
     *
     * @see #backupData
     */
    public static final String EXTRA_BACKUP_ID = "com.miui.enterprise.EXTRA_BACKUP_ID";

    /**
     * 默认权限标志位，清除已设置的其他标志
     *
     * @see #setApplicationSettings
     */
    public static final int FLAG_DEFAULT = 0;

    /**
     * 应用保活标志位，设置该标志的应用，进程不会被杀，联网不会被限制
     *
     * @see #setApplicationSettings 后台保活权限标志位
     */
    public static final int FLAG_KEEP_ALIVE = 1;

    /**
     * 应用防卸载标志位，设置该标志的应用无法被卸载
     *
     * @see #setApplicationSettings
     */
    public static final int FLAG_PREVENT_UNINSTALLATION = 1 << 2;

    /**
     * 应用后台自启动标志位，设置该标志的应用，能够后天自启动，不受MIUI自启动权限管理限制
     *
     * @see #setApplicationSettings
     */
    public static final int FLAG_ALLOW_AUTOSTART = 1 << 3;

    /**
     * 应用运行时权限静默授予标志位，设置该权限的应用，所有运行时权限默认授予，不受MIUI权限管理限制
     *
     * @see #setApplicationSettings
     */
    public static final int FLAG_GRANT_ALL_RUNTIME_PERMISSION = 1 << 4;

    /**
     * 黑白名单默认模式，此模式黑白名单均不生效
     *
     * @see #setApplicationRestriction(int)
     */
    public static final int RESTRICTION_MODE_DEFAULT = 0;

    /**
     * 白名单模式，此模式仅通过{@link #setApplicationWhiteList(List)}设置的白名单生效
     *
     * @see #setApplicationRestriction(int)
     */
    public static final int RESTRICTION_MODE_WHITE_LIST = 1;

    /**
     * 黑名单模式，此模式仅通过{@link #setApplicationBlackList(List)}设置的白名单生效
     *
     * @see #setApplicationRestriction(int)
     */
    public static final int RESTRICTION_MODE_BLACK_LIST = 2;

    /**
     * 企业模式激活权限，在AndroidManifest.xml配置
     */
    public static final String ENT_PERMISSION = "com.miui.enterprise.permission.ACTIVE_ENTERPRISE_MODE";

    public static synchronized ApplicationManager getInstance() {
        throw new RuntimeException();
    }

    /**
     * 静默安装apk
     *
     * @param path     apk路径,比如：/sdcard/test.apk
     * @param flag     参阅Android标准API android.content.pm.PackageManager#installPackage中参数的InstallFlags说明
     * @param observer 应用安装回调，参阅Demo工程app下aidl与ApplicationManagerTestActivity相关实现
     */
    public void installPackage(String path, int flag, IPackageInstallObserver2 observer) {
        throw new RuntimeException();
    }

    /**
     * @param path     apk路径,比如：/sdcard/test.apk
     * @param flag     参阅Android标准API android.content.pm.PackageManager#installPackage中参数的InstallFlags说明
     * @param observer 应用安装回调，参阅Demo工程app下aidl与ApplicationManagerTestActivity相关实现
     * @param userId   用户多用户，userId
     */
    public void installPackage(String path, int flag, IPackageInstallObserver2 observer, int userId) {
        throw new RuntimeException();
    }

    /**
     * 静默安装apk
     *
     * @param path          apk路径
     * @param pendingIntent 安装成功后该PendingIntent会被执行
     */
    public void installPackageWithPendingIntent(String path, PendingIntent pendingIntent) {
        throw new RuntimeException();
    }

    public void installPackageWithPendingIntent(String path, PendingIntent pendingIntent, int userId) {
        throw new RuntimeException();
    }

    /**
     * 静默卸载
     *
     * @param packageName 应用包名
     * @param flag        参阅Android标准API android.content.pm.PackageManager#deletePackage中参数的DeleteFlags说明
     * @param observer    应用删除的回调，参阅Demo工程app下aidl与ApplicationManagerTestActivity相关实现
     */
    public void deletePackage(String packageName, int flag, IPackageDeleteObserver observer) {
        throw new RuntimeException();
    }

    public void deletePackage(String packageName, int flag, IPackageDeleteObserver observer, int userId) {
        throw new RuntimeException();
    }

    /**
     * 清除应用数据
     *
     * @param packageName 应用包名
     */
    public void clearApplicationUserData(String packageName) {
        throw new RuntimeException();
    }

    public void clearApplicationUserData(String packageName, int userId) {
        throw new RuntimeException();
    }

    /**
     * 清除应用缓存
     *
     * @param packageName 应用包名
     */
    public void clearApplicationCache(String packageName) {
        throw new RuntimeException();
    }

    public void clearApplicationCache(String packageName, int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置应用权限配置
     * <p>
     * 需要申请多个权限使用 “|”，例如 ： flags = FLAG_KEEP_ALIVE | FLAG_ALLOW_AUTOSTART .
     * </p>
     *
     * @param packageName 应用包名
     * @param flags       参阅{@link #FLAG_DEFAULT}, {@link #FLAG_KEEP_ALIVE},
     *                    {@link #FLAG_PREVENT_UNINSTALLATION}, {@link #FLAG_ALLOW_AUTOSTART}
     *                    {@link #FLAG_GRANT_ALL_RUNTIME_PERMISSION}
     */
    public void setApplicationSettings(String packageName, int flags) {
        throw new RuntimeException();
    }

    public void setApplicationSettings(String packageName, int flags, int userId) {
        throw new RuntimeException();
    }

    /**
     * 获取应用权限配置
     * <p>
     * 如果申请了多个权限，获取到的权限是多个权限的 "|".
     * </p>
     *
     * @param packageName 应用包名
     * @return 参阅{@link #FLAG_DEFAULT}, {@link #FLAG_KEEP_ALIVE},
     * {@link #FLAG_PREVENT_UNINSTALLATION}, {@link #FLAG_ALLOW_AUTOSTART}
     * {@link #FLAG_GRANT_ALL_RUNTIME_PERMISSION}
     */
    public int getApplicationSettings(String packageName) {
        throw new RuntimeException();
    }

    public int getApplicationSettings(String packageName, int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置应用安装黑名单，黑名单内的应用在通过{@link #setApplicationRestriction(int)}设置为
     * {@link #RESTRICTION_MODE_BLACK_LIST}之后，将无法被安装到系统
     *
     * @param packages 黑名单应用包名列表
     */
    public void setApplicationBlackList(List<String> packages) {
        throw new RuntimeException();
    }

    public void setApplicationBlackList(List<String> packages, int userId) {
        throw new RuntimeException();
    }

    /**
     * 获取包名黑名单列表
     *
     * @return 包名黑名单列表
     * @see #setApplicationBlackList(List)
     */
    public List<String> getApplicationBlackList() {
        throw new RuntimeException();
    }

    public List<String> getApplicationBlackList(int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置应用安装白名单，不在白名单内的应用在通过{@link #setApplicationRestriction(int)}设置为
     * {@link #RESTRICTION_MODE_WHITE_LIST}之后，将无法被安装到系统
     *
     * @param packages 包名白名单列表
     */
    public void setApplicationWhiteList(List<String> packages) {
        throw new RuntimeException();
    }

    public void setApplicationWhiteList(List<String> packages, int userId) {
        throw new RuntimeException();
    }

    /**
     * 获取包名白名单列表
     *
     * @return 包名白名单
     * @see #setApplicationWhiteList(List)
     */
    public List<String> getApplicationWhiteList() {
        throw new RuntimeException();
    }

    public List<String> getApplicationWhiteList(int userId) {
        throw new RuntimeException();
    }

    /**
     * 设置应用安装黑白名单模式
     *
     * @param mode 参阅{@link #RESTRICTION_MODE_DEFAULT}, {@link #RESTRICTION_MODE_WHITE_LIST}, {@link #RESTRICTION_MODE_BLACK_LIST}
     * @see #setApplicationBlackList(List)
     * @see #setApplicationWhiteList(List)
     * 设置应用安装黑白名单模式
     */
    public void setApplicationRestriction(int mode) {
        throw new RuntimeException();
    }

    public void setApplicationRestriction(int mode, int userId) {
        throw new RuntimeException();
    }

    /**
     * @return 参阅{@link #RESTRICTION_MODE_DEFAULT}, {@link #RESTRICTION_MODE_WHITE_LIST}, {@link #RESTRICTION_MODE_BLACK_LIST}
     * @see #setApplicationRestriction(int)
     */
    public int getApplicationRestriction() {
        throw new RuntimeException();
    }

    public int getApplicationRestriction(int userId) {
        throw new RuntimeException();
    }

    /**
     * 静默激活设备管理器
     *
     * @param component 设备管理器组件
     * @return 操作是否成功
     */
    public boolean setDeviceAdmin(ComponentName component) {
        throw new RuntimeException();
    }

    public boolean setDeviceAdmin(ComponentName component, int userId) {
        throw new RuntimeException();
    }

    /**
     * 静默移除设备管理器
     *
     * @param component 设备管理器组件
     * @return 操作是否成功
     */
    public boolean removeDeviceAdmin(ComponentName component) {
        throw new RuntimeException();
    }

    public boolean removeDeviceAdmin(ComponentName component, int userId) {
        throw new RuntimeException();
    }

    /**
     * 杀掉应用进程
     *
     * @param packageName 应用包名
     */
    public void killProcess(String packageName) {
        throw new RuntimeException();
    }

    public void killProcess(String packageName, int userId) {
        throw new RuntimeException();
    }

    /**
     * 静默激活/注销辅助服务功能
     *
     * @param componentName 辅助服务功能组件
     * @param enable        是否开启
     */
    public void enableAccessibilityService(ComponentName componentName, boolean enable) {
        throw new RuntimeException();
    }

    /**
     * 禁止启动应用名单，名单内应用无法运行
     *
     * @param packages 包名列表
     */
    public void setDisallowedRunningAppList(List<String> packages) {
        throw new RuntimeException();
    }

    public void setDisallowedRunningAppList(List<String> packages, int userId) {
        throw new RuntimeException();
    }

    /**
     * 获取禁止运行包名列表
     *
     * @return 禁止运行包名列表
     * @see #setDisallowedRunningAppList(List)
     */
    public List<String> getDisallowedRunningAppList() {
        throw new RuntimeException();
    }

    public List<String> getDisallowedRunningAppList(int userId) {
        throw new RuntimeException();
    }

    /**
     * 添加应用安装来源白名单，在通过{@link #enableTrustedAppStore(boolean)}开启应用安装来源白名单后，
     * 只有白名单内应用能够请求系统安装apk
     * 此API不限制{@link #installPackage(String, int, IPackageInstallObserver2)}与{@link #installPackageWithPendingIntent(String, PendingIntent)}
     *
     * @param packages 包名列表
     */
    public void addTrustedAppStore(List<String> packages) {
        throw new RuntimeException();
    }

    public void addTrustedAppStore(List<String> packages, int userId) {
        throw new RuntimeException();
    }

    /**
     * @return 应用安装来源白名单列表
     * @see #addTrustedAppStore(List)
     */
    public List<String> getTrustedAppStore() {
        throw new RuntimeException();
    }

    public List<String> getTrustedAppStore(int userId) {
        throw new RuntimeException();
    }

    /**
     * 开启应用来源白名单
     *
     * @param enabld 是否开启，开启应用安装来源白名单后，只有白名单内应用能够请求系统安装apk
     */
    public void enableTrustedAppStore(boolean enabld) {
        throw new RuntimeException();
    }

    public void enableTrustedAppStore(boolean enabld, int userId) {
        throw new RuntimeException();
    }

    /**
     * @return 应用来源白名单是否开启
     */
    public boolean isTrustedAppStoreEnabled() {
        throw new RuntimeException();
    }

    public boolean isTrustedAppStoreEnabled(int userId) {
        throw new RuntimeException();
    }

    /**
     * 禁用/启用应用，禁用后应用处于安装状态，但是桌面图标消失，切无法启动
     *
     * @param packageName 应用包名
     * @param enable      应用是否启用
     */
    public void setApplicationEnabled(String packageName, boolean enable) {
        throw new RuntimeException();
    }

    public void setApplicationEnabled(String packageName, boolean enable, int userId) {
        throw new RuntimeException();
    }

    /**
     * 清除最近任务中的应用
     */
    public void clearAllTask() {
        throw new RuntimeException();
    }

    /**
     * 判断应用是否被双开
     *
     * @param pkgName 应用包名
     * @return 是否双开
     */
    public boolean isAppInXSpace(String pkgName) {
        throw new RuntimeException();
    }

    /**
     * 备份应用数据
     *
     * @param src     目标app data/data/{packageName}/目录下的文件，
     *                写“/”时备份整个目录，写“/files/”则备份app的files目录。
     * @param out     备份输出目录，例如“目录设置在sd卡的ent-backup目录
     * @param pkgName 需要备份的应用的包名
     */
    public void backupData(String src, String out, String pkgName) {
        throw new RuntimeException();
    }

    /**
     * @param src       目标app data/data/{packageName}/目录下的文件。
     *                  写“/”时备份整个目录，写“/files/”则备份app的files目录。
     * @param out       备份输出目录，例如“目录设置在sd卡的ent-backup目录
     * @param pkgName   需要备份的应用的包名
     * @param requestId 根据requestId进行备份
     */
    public void backupData(String src, String out, String pkgName, int requestId) {
        throw new RuntimeException();
    }

    /**
     * 判断当前应用是否正在运行
     *
     * @param pkgName 应用包名
     * @return 是否正在运行
     */
    public boolean isPackageAlive(String pkgName) {
        throw new RuntimeException();
    }

    /**
     * 设置应用成 Device Owner
     *
     * @param component 对应组件
     * @param userId    用户空间ID
     * @return 是否设置成功
     */
    public boolean setDeviceOwner(ComponentName component, int userId) {
        throw new RuntimeException();
    }

    public boolean removeDeviceOwner(String packageName) {
        throw new RuntimeException();
    }

    public boolean isDeviceOwner(String packageName) {
        throw new RuntimeException();
    }

    /**
     * 应用在最近任务中不显示
     *
     * @param packages 不显示应用包名列表
     */
    public void setIgnoreApplicationList(List<String> packages) {
        throw new RuntimeException();
    }

    /**
     * @return 返回设置的隐藏最近任务包名列表
     */
    public List<String> getIgnoreApplicationList() {
        throw new RuntimeException();
    }

    /**
     * 查找应用是否设置隐藏最近任务
     *
     * @param packageName 包名
     * @return 是否设置隐藏
     */
    public boolean isIgnoreApplication(String packageName) {
        throw new RuntimeException();
    }

}
