package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/15
 */

public class MedicalListBean implements Parcelable {


    /**
     * medical_id : 1
     * medical_desc : 体检报告
     * create_time : 2018-03-02 10:02:00
     * update_time :
     * is_delete : 0
     * member_id : 28
     * medical_class_id : 3
     * medicalImgBeans : [{"medical_img_id":1,"medical_img":"/images/banner/banner1.jpg","create_time":"2018-01-02 10:02:00","update_time":"","is_delete":"0","medical_id":1},{"medical_img_id":2,"medical_img":"/images/banner/banner1.jpg","create_time":"2018-01-02 10:02:00","update_time":"","is_delete":"0","medical_id":1},{"medical_img_id":3,"medical_img":"/images/banner/banner1.jpg","create_time":"2018-01-02 10:02:00","update_time":"","is_delete":"0","medical_id":1},{"medical_img_id":4,"medical_img":"/images/banner/banner1.jpg","create_time":"2018-01-02 10:02:00","update_time":"","is_delete":"0","medical_id":1},{"medical_img_id":5,"medical_img":"/images/banner/banner1.jpg","create_time":"2018-01-02 10:02:00","update_time":"","is_delete":"0","medical_id":1},{"medical_img_id":6,"medical_img":"/images/banner/banner1.jpg","create_time":"2018-01-02 10:02:00","update_time":"","is_delete":"0","medical_id":1}]
     */

    private String medical_id;
    private String medical_desc;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String member_id;
    private String medical_class_id;
    private List<MedicalImgBeansBean> medicalImgBeans;

    public String getMedical_id() {
        return medical_id;
    }

    public void setMedical_id(String medical_id) {
        this.medical_id = medical_id;
    }

    public String getMedical_desc() {
        return medical_desc;
    }

    public void setMedical_desc(String medical_desc) {
        this.medical_desc = medical_desc;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMedical_class_id() {
        return medical_class_id;
    }

    public void setMedical_class_id(String medical_class_id) {
        this.medical_class_id = medical_class_id;
    }

    public List<MedicalImgBeansBean> getMedicalImgBeans() {
        return medicalImgBeans;
    }

    public void setMedicalImgBeans(List<MedicalImgBeansBean> medicalImgBeans) {
        this.medicalImgBeans = medicalImgBeans;
    }

    public static class MedicalImgBeansBean implements Parcelable {
        /**
         * medical_img_id : 1
         * medical_img : /images/banner/banner1.jpg
         * create_time : 2018-01-02 10:02:00
         * update_time :
         * is_delete : 0
         * medical_id : 1
         */

        private int medical_img_id;
        private String medical_img;
        private String create_time;
        private String update_time;
        private String is_delete;
        private int medical_id;

        public int getMedical_img_id() {
            return medical_img_id;
        }

        public void setMedical_img_id(int medical_img_id) {
            this.medical_img_id = medical_img_id;
        }

        public String getMedical_img() {
            return medical_img;
        }

        public void setMedical_img(String medical_img) {
            this.medical_img = medical_img;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public int getMedical_id() {
            return medical_id;
        }

        public void setMedical_id(int medical_id) {
            this.medical_id = medical_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.medical_img_id);
            dest.writeString(this.medical_img);
            dest.writeString(this.create_time);
            dest.writeString(this.update_time);
            dest.writeString(this.is_delete);
            dest.writeInt(this.medical_id);
        }

        public MedicalImgBeansBean() {
        }

        protected MedicalImgBeansBean(Parcel in) {
            this.medical_img_id = in.readInt();
            this.medical_img = in.readString();
            this.create_time = in.readString();
            this.update_time = in.readString();
            this.is_delete = in.readString();
            this.medical_id = in.readInt();
        }

        public static final Parcelable.Creator<MedicalImgBeansBean> CREATOR = new Parcelable.Creator<MedicalImgBeansBean>() {
            @Override
            public MedicalImgBeansBean createFromParcel(Parcel source) {
                return new MedicalImgBeansBean(source);
            }

            @Override
            public MedicalImgBeansBean[] newArray(int size) {
                return new MedicalImgBeansBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.medical_id);
        dest.writeString(this.medical_desc);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.is_delete);
        dest.writeString(this.member_id);
        dest.writeString(this.medical_class_id);
        dest.writeTypedList(this.medicalImgBeans);
    }

    public MedicalListBean() {
    }

    protected MedicalListBean(Parcel in) {
        this.medical_id = in.readString();
        this.medical_desc = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.is_delete = in.readString();
        this.member_id = in.readString();
        this.medical_class_id = in.readString();
        this.medicalImgBeans = in.createTypedArrayList(MedicalImgBeansBean.CREATOR);
    }

    public static final Parcelable.Creator<MedicalListBean> CREATOR = new Parcelable.Creator<MedicalListBean>() {
        @Override
        public MedicalListBean createFromParcel(Parcel source) {
            return new MedicalListBean(source);
        }

        @Override
        public MedicalListBean[] newArray(int size) {
            return new MedicalListBean[size];
        }
    };
}
