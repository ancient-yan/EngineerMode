package com.miui.enterprise.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Java bean for {@link APNManager}
 */
public class APNConfig implements Parcelable {

    public String mName;
    public String mApn;
    public String mUser;
    public String mPassword;
    public int mAuthType = 1;
    public String mDialNumber;
    public int mBearer = 0;
    //EP-cm-add START:
    public int mCarrier_enabled = -1;
    public int mCurrent = -1;
    public String mMcc = null;
    public String mMmsc = null;
    public String mMmsport = null;
    public String mMmsproxy = null;
    public String mMnc = null;
    public String mMvno_match_data = null;
    public String mMvno_type = null;
    public String mNumeric = null;
    public String mPort = null;
    public String mProtocol = null;
    public String mProxy = null;
    public String mRoaming_protocol = null;
    public String mServer = null;
    public String mSub_id = null;
    public String mType = null;
    //END

    public APNConfig(Parcel in) {
        readFromParcel(in);
    }

    public APNConfig() {

    }

    public APNConfig(String name, String apn, String user,
                     String password, int authType, String dialNumber, int bearer) {
        mName = name;
        mApn = apn;
        mUser = user;
        mPassword = password;
        mAuthType = authType;
        mDialNumber = dialNumber;
        mBearer = bearer;
    }

    public boolean isValidate() {
        return !TextUtils.isEmpty(mName) && !TextUtils.isEmpty(mApn) && !TextUtils.isEmpty(mUser)
                && !TextUtils.isEmpty(mPassword);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mApn);
        dest.writeString(mUser);
        dest.writeString(mPassword);
        dest.writeInt(mAuthType);
        dest.writeString(mDialNumber);
        dest.writeInt(mBearer);
        dest.writeInt(mCarrier_enabled);
        dest.writeInt(mCurrent);
        dest.writeString(mMcc);
        dest.writeString(mMmsc);
        dest.writeString(mMmsport);
        dest.writeString(mMmsproxy);
        dest.writeString(mMnc);
        dest.writeString(mMvno_match_data);
        dest.writeString(mMvno_type);
        dest.writeString(mNumeric);
        dest.writeString(mPort);
        dest.writeString(mProtocol);
        dest.writeString(mProxy);
        dest.writeString(mRoaming_protocol);
        dest.writeString(mServer);
        dest.writeString(mSub_id);
        dest.writeString(mType);


    }

    public void readFromParcel(Parcel in) {
        mName = in.readString();
        mApn = in.readString();
        mUser = in.readString();
        mPassword = in.readString();
        mAuthType = in.readInt();
        mDialNumber = in.readString();
        mBearer = in.readInt();
        mCarrier_enabled = in.readInt();
        mCurrent = in.readInt();
        mMcc = in.readString();
        mMmsc = in.readString();
        mMmsport = in.readString();
        mMmsproxy = in.readString();
        mMnc = in.readString();
        mMvno_match_data = in.readString();
        mMvno_type = in.readString();
        mNumeric = in.readString();
        mPort = in.readString();
        mProtocol = in.readString();
        mProxy = in.readString();
        mRoaming_protocol = in.readString();
        mServer = in.readString();
        mSub_id = in.readString();
        mType = in.readString();
    }

    public static final Creator<APNConfig> CREATOR =
            new Creator<APNConfig>() {
                @Override
                public APNConfig createFromParcel(Parcel source) {
                    return new APNConfig(source);
                }

                @Override
                public APNConfig[] newArray(int size) {
                    return new APNConfig[size];
                }
            };

    @Override
    public String toString() {
        return "APNConfig{" +
                "mName='" + mName + '\'' +
                ", mApn='" + mApn + '\'' +
                ", mUser='" + mUser + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mAuthType=" + mAuthType +
                ", mDialNumber='" + mDialNumber + '\'' +
                ", mBearer=" + mBearer +
                '}';
    }
}

