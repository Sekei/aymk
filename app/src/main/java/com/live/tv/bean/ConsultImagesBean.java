package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by taoh on 2018/3/21.
 */

public class ConsultImagesBean implements Parcelable {


    /**
     * consult_image_id : 121
     * consult_image_url : /images/member/15247102295621163643365.jpg
     * consult_record_id : 274
     * create_time : 2018-04-26 10:37:24
     * is_delete : 0
     */

    private String consult_image_id;
    private String consult_image_url;
    private String consult_record_id;
    private String create_time;
    private String is_delete;

    public String getConsult_image_id() {
        return consult_image_id;
    }

    public void setConsult_image_id(String consult_image_id) {
        this.consult_image_id = consult_image_id;
    }

    public String getConsult_image_url() {
        return consult_image_url;
    }

    public void setConsult_image_url(String consult_image_url) {
        this.consult_image_url = consult_image_url;
    }

    public String getConsult_record_id() {
        return consult_record_id;
    }

    public void setConsult_record_id(String consult_record_id) {
        this.consult_record_id = consult_record_id;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.consult_image_id);
        dest.writeString(this.consult_image_url);
        dest.writeString(this.consult_record_id);
        dest.writeString(this.create_time);
        dest.writeString(this.is_delete);
    }

    public ConsultImagesBean() {
    }

    protected ConsultImagesBean(Parcel in) {
        this.consult_image_id = in.readString();
        this.consult_image_url = in.readString();
        this.consult_record_id = in.readString();
        this.create_time = in.readString();
        this.is_delete = in.readString();
    }

    public static final Parcelable.Creator<ConsultImagesBean> CREATOR = new Parcelable.Creator<ConsultImagesBean>() {
        @Override
        public ConsultImagesBean createFromParcel(Parcel source) {
            return new ConsultImagesBean(source);
        }

        @Override
        public ConsultImagesBean[] newArray(int size) {
            return new ConsultImagesBean[size];
        }
    };
}
