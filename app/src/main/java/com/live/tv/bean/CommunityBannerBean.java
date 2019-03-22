package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/7/17
 */

public class CommunityBannerBean implements Parcelable {


    /**
     * plate_class_id : 1
     * class_name : 饮食
     * is_delete : 0
     * create_time : 2018-06-20 16:35:45
     * update_time :
     * communityPlateBeans : [{"plate_id":1,"plate_name":"饮食","plate_image":"/images/member/default.png","topic_num":"0","is_hot":"1","plate_class_id":1,"is_home_show":"1","is_delete":"0","create_time":"2018-06-20 16:35:45","update_time":"2018-06-20 16:35:45","postBeans":[]},{"plate_id":3,"plate_name":"健康养生","plate_image":"/images/member/default.png","topic_num":"0","is_hot":"1","plate_class_id":1,"is_home_show":"0","is_delete":"0","create_time":"2018-06-20 16:35:45","update_time":"2018-06-20 16:35:45","postBeans":[]}]
     */

    private String plate_class_id;
    private String class_name;
    private String is_delete;
    private String create_time;
    private String update_time;
    private List<CommunityPlateBeansBean> communityPlateBeans;

    protected CommunityBannerBean(Parcel in) {
        plate_class_id = in.readString();
        class_name = in.readString();
        is_delete = in.readString();
        create_time = in.readString();
        update_time = in.readString();
    }

   private CommunityBannerBean(){
   }

    public static final Creator<CommunityBannerBean> CREATOR = new Creator<CommunityBannerBean>() {
        @Override
        public CommunityBannerBean createFromParcel(Parcel in) {
            return new CommunityBannerBean(in);
        }

        @Override
        public CommunityBannerBean[] newArray(int size) {
            return new CommunityBannerBean[size];
        }
    };

    public String getPlate_class_id() {
        return plate_class_id;
    }

    public void setPlate_class_id(String plate_class_id) {
        this.plate_class_id = plate_class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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

    public List<CommunityPlateBeansBean> getCommunityPlateBeans() {
        return communityPlateBeans;
    }

    public void setCommunityPlateBeans(List<CommunityPlateBeansBean> communityPlateBeans) {
        this.communityPlateBeans = communityPlateBeans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(plate_class_id);
        dest.writeString(class_name);
        dest.writeString(is_delete);
        dest.writeString(create_time);
        dest.writeString(update_time);
    }

    public static class CommunityPlateBeansBean {
        /**
         * plate_id : 1
         * plate_name : 饮食
         * plate_image : /images/member/default.png
         * topic_num : 0
         * is_hot : 1
         * plate_class_id : 1
         * is_home_show : 1
         * is_delete : 0
         * create_time : 2018-06-20 16:35:45
         * update_time : 2018-06-20 16:35:45
         * postBeans : []
         */

        private int plate_id;
        private String plate_name;
        private String plate_image;
        private String topic_num;
        private String is_hot;
        private int plate_class_id;
        private String is_home_show;
        private String is_delete;
        private String create_time;
        private String update_time;
        private List<?> postBeans;

        public int getPlate_id() {
            return plate_id;
        }

        public void setPlate_id(int plate_id) {
            this.plate_id = plate_id;
        }

        public String getPlate_name() {
            return plate_name;
        }

        public void setPlate_name(String plate_name) {
            this.plate_name = plate_name;
        }

        public String getPlate_image() {
            return plate_image;
        }

        public void setPlate_image(String plate_image) {
            this.plate_image = plate_image;
        }

        public String getTopic_num() {
            return topic_num;
        }

        public void setTopic_num(String topic_num) {
            this.topic_num = topic_num;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public int getPlate_class_id() {
            return plate_class_id;
        }

        public void setPlate_class_id(int plate_class_id) {
            this.plate_class_id = plate_class_id;
        }

        public String getIs_home_show() {
            return is_home_show;
        }

        public void setIs_home_show(String is_home_show) {
            this.is_home_show = is_home_show;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
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

        public List<?> getPostBeans() {
            return postBeans;
        }

        public void setPostBeans(List<?> postBeans) {
            this.postBeans = postBeans;
        }
    }
}
