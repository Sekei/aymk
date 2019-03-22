package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by taoh on 2018/5/15.
 */

public class RefundsReasons implements Parcelable {


    /**
     * refund_reason_id : 6
     * reason_name : 协商一致退款
     * create_time : 2016-12-28 13:15:25.0
     * is_delete : 0
     * sort : 6
     */

    private String refund_reason_id;
    private String reason_name;
    private String create_time;
    private String is_delete;
    private String sort;

    public String getRefund_reason_id() {
        return refund_reason_id;
    }

    public void setRefund_reason_id(String refund_reason_id) {
        this.refund_reason_id = refund_reason_id;
    }

    public String getReason_name() {
        return reason_name;
    }

    public void setReason_name(String reason_name) {
        this.reason_name = reason_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.refund_reason_id);
        dest.writeString(this.reason_name);
        dest.writeString(this.create_time);
        dest.writeString(this.is_delete);
        dest.writeString(this.sort);
    }

    public RefundsReasons() {
    }

    protected RefundsReasons(Parcel in) {
        this.refund_reason_id = in.readString();
        this.reason_name = in.readString();
        this.create_time = in.readString();
        this.is_delete = in.readString();
        this.sort = in.readString();
    }

    public static final Parcelable.Creator<RefundsReasons> CREATOR = new Parcelable.Creator<RefundsReasons>() {
        @Override
        public RefundsReasons createFromParcel(Parcel source) {
            return new RefundsReasons(source);
        }

        @Override
        public RefundsReasons[] newArray(int size) {
            return new RefundsReasons[size];
        }
    };
}
