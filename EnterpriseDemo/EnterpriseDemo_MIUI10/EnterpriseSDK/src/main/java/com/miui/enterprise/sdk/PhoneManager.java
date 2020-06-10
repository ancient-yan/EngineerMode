package com.miui.enterprise.sdk;

import java.util.List;
import java.util.Map;

public class PhoneManager {

    /**
     * 号码隐藏,来电广播
     *
     * @see #setPhoneNumberHide(Map, String)
     */
    public static final String ACTION_INCOMING_CALL = "com.miui.enterprise.ACTION_INCOMING_CALL";

    /**
     * 号码隐藏,去电广播
     *
     * @see #setPhoneNumberHide(Map, String)
     */
    public static final String ACTION_OUTGOING_CALL = "com.miui.enterprise.ACTION_OUTGOING_CALL";

    /**
     * 号码隐藏,接通广播
     *
     * @see #setPhoneNumberHide(Map, String)
     */
    public static final String ACTION_CALL_CONNECTED = "com.miui.enterprise.ACTION_CALL_CONNECTED";

    /**
     * 号码隐藏,断开广播
     *
     * @see #setPhoneNumberHide(Map, String)
     */
    public static final String ACTION_CALL_DISCONNECTED = "com.miui.enterprise.ACTION_CALL_DISCONNECTED";

    /**
     * 号码隐藏,拒接广播
     *
     * @see #setPhoneNumberHide(Map, String)
     */
    public static final String ACTION_CALL_REJECT = "com.miui.enterprise.ACTION_CALL_REJECT";

    /**
     * 号码隐藏,通话录音完成广播，广播中不包含电话号码
     */
    public static final String ACTION_CALL_RECORD = "com.miui.enterprise.ACTION_CALL_RECORD";

    /**
     * sim卡加载完成广播
     */
    public static final String ACTION_SIM_LOADED = "com.miui.enterprise.ACTION_SIM_LOADED";

    /**
     * 拔出sim广播
     */
    public static final String ACTION_SIM_ABSENT = "com.miui.enterprise.ACTION_SIM_ABSENT";

    /**
     * 号码隐藏,广播extra字段
     *
     * @see #setPhoneNumberHide(Map, String)
     */
    public static final String EXTRA_PHONE_NUMBER = "extra_phone_number";

    /**
     * 号码隐藏,当前通话卡槽号码
     *
     * @see #setPhoneNumberHide(Map, String)
     */
    public static final String EXTRA_SLOT_ID = "extra_slot_id";

    /**
     * 号码隐藏,录音完成广播extra字段,录音存储路径
     */
    public static final String EXTRA_CALL_RECORD_FILE = "extra_call_record_file";

    /**
     * 功能禁用
     *
     * @see #controlCellular(int)
     */
    public static final int DISABLE = 0;
    /**
     * 功能启用（不管控）
     *
     * @see #controlCellular(int)
     */
    public static final int ENABLE = 1;
    /**
     * 在启用状态下，开启功能开关
     *
     * @see #controlCellular(int)
     */
    public static final int OPEN = 2;
    /**
     * 在启用状态下，关闭功能开关
     *
     * @see #controlCellular(int)
     */
    public static final int CLOSE = 3;
    /**
     * 强制开启功能开关，无法手动关闭
     *
     * @see #controlCellular(int)
     */
    public static final int FORCE_OPEN = 4;

    /**
     * 默认模式
     *
     * @see #controlPhoneCall(int)
     * @see #controlSMS(int)
     */
    public static final int FLAG_DEFAULT = 0;
    /**
     * 禁止通话呼出或者短信发送
     *
     * @see #controlPhoneCall(int)
     * @see #controlSMS(int)
     */
    public static final int FLAG_DISALLOW_IN = 1;
    /**
     * 拦截所有通话或者短信
     *
     * @see #controlPhoneCall(int)
     * @see #controlSMS(int)
     */
    public static final int FLAG_DISALLOW_OUT = 1 << 1;

    /**
     * 默认模式，黑白名单均不生效
     *
     * @see #setCallContactRestriction(int)
     * @see #setSMSContactRestriction(int)
     */
    public static final int RESTRICTION_MODE_DEFAULT = 0;
    /**
     * 白名单模式，白名单生效
     *
     * @see #setCallContactRestriction(int)
     * @see #setSMSContactRestriction(int)
     */
    public static final int RESTRICTION_MODE_WHITE_LIST = 1;
    /**
     * 黑名单模式，黑名单生效
     *
     * @see #setCallContactRestriction(int)
     * @see #setSMSContactRestriction(int)
     */
    public static final int RESTRICTION_MODE_BLACK_LIST = 2;

    public static synchronized PhoneManager getInstance() {
        throw new RuntimeException();
    }

    /**
     * 控制短信发送与接收
     *
     * @param flags {@link #FLAG_DEFAULT}: 默认模式，不管控; {@link #FLAG_DISALLOW_IN}:禁止接收;
     *              {@link #FLAG_DISALLOW_OUT}:禁止发送
     */
    public void controlSMS(int flags) {
        throw new RuntimeException();
    }

    /**
     * 控制通话呼入与呼出
     *
     * @param flags {@link #FLAG_DEFAULT}: 默认模式，不管控; {@link #FLAG_DISALLOW_IN}:拦截呼入;
     *              {@link #FLAG_DISALLOW_OUT}:禁止呼出
     */
    public void controlPhoneCall(int flags) {
        throw new RuntimeException();
    }

    /**
     * 控制数据流量开关
     *
     * @param status {@link #DISABLE}:禁用; {@link #ENABLE}:启用; {@link #OPEN}:在启用状态下开启开关;
     *               {@link #CLOSE}:在启用状态下关闭开关; {@link #FORCE_OPEN}强制开启
     */
    public void controlCellular(int status) {
        throw new RuntimeException();
    }

    /**
     * 获取短信管控状态
     *
     * @return {@link #FLAG_DEFAULT}: 默认模式，不管控; {@link #FLAG_DISALLOW_IN}:禁止接收;
     * {@link #FLAG_DISALLOW_OUT}:禁止发送
     */
    public int getSMSStatus() {
        throw new RuntimeException();
    }

    /**
     * 获取通话管控状态
     *
     * @return {@link #FLAG_DEFAULT}: 默认模式，不管控; {@link #FLAG_DISALLOW_IN}:拦截呼入;
     * {@link #FLAG_DISALLOW_OUT}:禁止呼出
     */
    public int getPhoneCallStatus() {
        throw new RuntimeException();
    }

    /**
     * 获取数据流量管控状态
     *
     * @return {@link #DISABLE}:禁用; {@link #ENABLE}:启用; {@link #FORCE_OPEN}:强制开启
     */
    public int getCellularStatus() {
        throw new RuntimeException();
    }

    /**
     * 获取设备IMEI
     *
     * @param slotId 卡槽id(0或1)
     * @return 对应卡槽IMEI
     */
    public String getIMEI(int slotId) {
        throw new RuntimeException();
    }

    /**
     * 通话自动录音，开启后通话接通会自动录音
     *
     * @param isAutoRecord 是否开启自动录音
     */
    public void setPhoneCallAutoRecord(boolean isAutoRecord) {
        throw new RuntimeException();
    }

    /**
     * 自定义通话录音路径
     *
     * @param filePath 自定义录音路径
     */
    public void setPhoneCallAutoRecordDir(String filePath) {
        throw new RuntimeException();
    }

    /**
     * @return 通话自动录音功能是否开启
     */
    public boolean isAutoRecordPhoneCall() {
        throw new RuntimeException();
    }

    /**
     * 设置短信黑名单
     * <p>
     * 在调用{@link #setSMSContactRestriction(int)}设置{@link #RESTRICTION_MODE_BLACK_LIST}后，
     * 黑名单内的电话的短信将被拦截
     *
     * @param list 黑名单号码列表
     */
    public void setSMSBlackList(List<String> list) {
        throw new RuntimeException();
    }

    /**
     * 获取短信黑名单
     *
     * @return 黑名单号码列表
     */
    public List<String> getSMSBlackList() {
        throw new RuntimeException();
    }

    /**
     * 设置短信白名单
     * 在调用{@link #setSMSContactRestriction(int)}设置{@link #RESTRICTION_MODE_WHITE_LIST}后，
     * 不在白名单内的电话的短信将被拦截
     *
     * @param list 白名单号码列表
     */
    public void setSMSWhiteList(List<String> list) {
        throw new RuntimeException();
    }

    /**
     * 获取短信白名单
     *
     * @return 白名单号码列表
     */
    public List<String> getSMSWhiteList() {
        throw new RuntimeException();
    }

    /**
     * 设置短信黑白名单模式
     *
     * @param mode {@link #RESTRICTION_MODE_DEFAULT}:默认模式，黑白名单均不生效;
     *             {@link #RESTRICTION_MODE_WHITE_LIST}:白名单生效; {@link #RESTRICTION_MODE_BLACK_LIST}:黑名单生效
     */
    public void setSMSContactRestriction(int mode) {
        throw new RuntimeException();
    }

    /**
     * 获取短信黑白名单模式
     *
     * @return {@link #RESTRICTION_MODE_DEFAULT}:默认模式，黑白名单均不生效;
     * {@link #RESTRICTION_MODE_WHITE_LIST}:白名单生效; {@link #RESTRICTION_MODE_BLACK_LIST}:黑名单生效
     */
    public int getSMSContactRestriction() {
        throw new RuntimeException();
    }

    /**
     * 设置通话黑名单
     * 在调用{@link #setCallContactRestriction(int)}设置{@link #RESTRICTION_MODE_BLACK_LIST}后，
     * 黑名单内号码来电默认拦截
     *
     * @param list 黑名单号码列表
     */
    public void setCallBlackList(List<String> list) {
        throw new RuntimeException();
    }

    /**
     * 获取通话黑名单
     *
     * @return 黑名单号码列表
     */
    public List<String> getCallBlackList() {
        throw new RuntimeException();
    }

    /**
     * 设置通话白名单
     * 在调用{@link #setCallContactRestriction(int)}设置{@link #RESTRICTION_MODE_WHITE_LIST}后，
     * 不在白名单内号码来电默认拦截
     *
     * @param list 白名单号码列表
     */
    public void setCallWhiteList(List<String> list) {
        throw new RuntimeException();
    }

    /**
     * 获取通话白名单
     *
     * @return 白名单号码列表
     */
    public List<String> getCallWhiteList() {
        throw new RuntimeException();
    }

    /**
     * 设置通话黑白名单模式
     *
     * @param mode {@link #RESTRICTION_MODE_DEFAULT}:默认模式，黑白名单均不生效;
     *             {@link #RESTRICTION_MODE_WHITE_LIST}:白名单生效; {@link #RESTRICTION_MODE_BLACK_LIST}:黑名单生效
     */
    public void setCallContactRestriction(int mode) {
        throw new RuntimeException();
    }

    /**
     * 获取通话黑白名单模式
     *
     * @return {@link #RESTRICTION_MODE_DEFAULT}:默认模式，黑白名单均不生效;
     * {@link #RESTRICTION_MODE_WHITE_LIST}:白名单生效; {@link #RESTRICTION_MODE_BLACK_LIST}:黑名单生效
     */
    public int getCallContactRestriction() {
        throw new RuntimeException();
    }

    /**
     * 挂断当前通话
     */
    public void endCall() {
        throw new RuntimeException();
    }

    /**
     * 管控呼叫转移
     *
     * @param disable 是否禁用
     */
    public void disableCallForward(boolean disable) {
        throw new RuntimeException();
    }

    /**
     * 禁用通话记录
     *
     * @param disable 是否禁用通话记录
     */
    public void disableCallLog(boolean disable) {
        throw new RuntimeException();
    }

    /**
     * 获取电话归属地
     *
     * @param phoneNumber 电话号码
     * @return 归属地代码
     */
    public String getAreaCode(String phoneNumber) {
        throw new RuntimeException();
    }

    /**
     * 获取设备MEID
     *
     * @param slotId sim卡ID(0或1)
     * @return 设备MEID
     */
    public String getMeid(int slotId) {
        throw new RuntimeException();
    }

    /**
     * 管控SIM卡卡槽
     *
     * @param slotId   卡槽ID(0或1)
     * @param isActive 是否启用，{@code true}启用. {@code false}禁用
     */
    public void setIccCardActivate(int slotId, boolean isActive) {
        throw new RuntimeException();
    }

    /**
     * Encode phone number
     *
     * @param format      隐号规则的map，key为号码的位数，value为对应长度号码的规则，规则之中"_"对应的位置不处理，其他字符对应的位置，号码则会被替换成规则之中对应位置的字符。
     *                    比如map.put(11, "___***#____")，那么11位长度的号码13377778888则会被显示为133***#8888
     * @param packageName 号码加密后，所有的通话广播都会被设置此packageName为target pacakage，即只有此package能够收到通话相关广播
     */
    public void setPhoneNumberHide(Map<Integer, String> format, String packageName) {
        throw new RuntimeException();
    }

    /**
     * 关闭隐号功能
     */
    public void disablePhoneNumberHide() {
        throw new RuntimeException();
    }

    /**
     * 从加密号码串之中解析出真实号码
     *
     * @param encodedNumber 加密号码
     * @return 返回未加密号码
     */
    public String decodePhoneNumber(String encodedNumber) {
        throw new RuntimeException();
    }

    /**
     * 待实现API
     * 获取明文号码对应的加密号码,需要隐号权限
     *
     * @param originNumber 明文号码
     * @return String 加密号码
     */
    public String getEncryptedNumber(String originNumber) {
        throw new RuntimeException();
    }

    /**
     * Active SIM card in specific slot, you can only get the phone number after activation
     *
     * @param slotId which slot
     */
    public void activeSim(int slotId) {
        throw new RuntimeException();
    }


    /**
     * Get the phone number for specific sim, you can only get the phone number after calling {@link #activeSim(int)}
     *
     * @param slotId which slot
     * @return phone number
     */
    public String getPhoneNumber(int slotId) {
        throw new RuntimeException();
    }

    /**
     * 开始录音
     *
     * @param filePath 录音文件路径
     */
    public void startRecord(String filePath) {
        throw new RuntimeException();
    }

    /**
     * 停止录音
     */
    public void stopRecord() {
        throw new RuntimeException();
    }

}
