package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/24
 */

public class TextConsultBean implements Parcelable {


    /**
     * consult_record_desc : 啦啦啦啦啦，我是张大龙，我是一个卖串的
     * doctor_id : 1
     * member_id : 1
     * consultImages : [{"consult_image_url":"/images/banner/banner1.jpg"},{"consult_image_url":"/images/banner/banner1.jpg"},{"consult_image_url":"/images/banner/banner1.jpg"}]
     */

    private String consult_record_desc;
    private String doctor_id;
    private String member_id;
    private String health_record_id;
    private List<ConsultImagesBean> consultImages;

    public String getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(String health_record_id) {
        this.health_record_id = health_record_id;
    }

    public String getConsult_record_desc() {
        return consult_record_desc;
    }

    public void setConsult_record_desc(String consult_record_desc) {
        this.consult_record_desc = consult_record_desc;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public List<ConsultImagesBean> getConsultImages() {
        return consultImages;
    }

    public void setConsultImages(List<ConsultImagesBean> consultImages) {
        this.consultImages = consultImages;
    }

    public static class ConsultImagesBean implements Parcelable {
        /**
         * consult_image_url : /images/banner/banner1.jpg
         */

        private String consult_image_url;

        public String getConsult_image_url() {
            return consult_image_url;
        }

        public void setConsult_image_url(String consult_image_url) {
            this.consult_image_url = consult_image_url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.consult_image_url);
        }

        public ConsultImagesBean() {
        }

        protected ConsultImagesBean(Parcel in) {
            this.consult_image_url = in.readString();
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.consult_record_desc);
        dest.writeString(this.doctor_id);
        dest.writeString(this.member_id);
        dest.writeString(this.health_record_id);
        dest.writeTypedList(this.consultImages);
    }

    public TextConsultBean() {
    }

    protected TextConsultBean(Parcel in) {
        this.consult_record_desc = in.readString();
        this.doctor_id = in.readString();
        this.member_id = in.readString();
        this.health_record_id = in.readString();
        this.consultImages = in.createTypedArrayList(ConsultImagesBean.CREATOR);
    }

    public static final Parcelable.Creator<TextConsultBean> CREATOR = new Parcelable.Creator<TextConsultBean>() {
        @Override
        public TextConsultBean createFromParcel(Parcel source) {
            return new TextConsultBean(source);
        }

        @Override
        public TextConsultBean[] newArray(int size) {
            return new TextConsultBean[size];
        }
    };
}
