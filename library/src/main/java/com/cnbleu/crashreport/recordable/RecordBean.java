package com.cnbleu.crashreport.recordable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 异常信息封装类
 * <br>
 */
public class RecordBean implements Parcelable {

    /** 异常发生时间 */
    public long time;
    /** 设备信息 */
    public String deviceInfo;
    /** 异常堆栈信息 */
    public String stackTrace;

    public RecordBean() {}

    protected RecordBean(Parcel in) {
        this.time = in.readLong();
        this.deviceInfo = in.readString();
        this.stackTrace = in.readString();
    }

    @Override
    public String toString() {
        return "RecordBean{" +
               "deviceInfo='" + deviceInfo + '\'' +
               ", time=" + time +
               ", stackTrace='" + stackTrace + '\'' +
               '}';
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.time);
        dest.writeString(this.deviceInfo);
        dest.writeString(this.stackTrace);
    }


    public static final Creator<RecordBean> CREATOR = new Creator<RecordBean>() {
        public RecordBean createFromParcel(Parcel source) {return new RecordBean(source);}

        public RecordBean[] newArray(int size) {return new RecordBean[size];}
    };
}
