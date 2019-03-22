package com.live.tv.bean;

import java.util.List;

/**
 * Created by taoh on 2018/6/27.
 */

public class HousekeepBean {

    /**
     * house_service_id : 2
     * member_id : 28
     * member_nick_name :
     * member_real_name :
     * age : 22
     * contact_name : 111
     * sex : 男
     * experience : 哈哈哈
     * service_type : 服务类型
     * contact_phone : 1111
     * email_address : 1212@qq.com
     * prove_img1 : /images/others/1529741660703979752010.jpg
     * prove_img2 : /images/others/1529741660703979752010.jpg
     * legal_face_img : /images/others/1529741660703979752010.jpg
     * legal_opposite_img : /images/others/1529741660703979752010.jpg
     * legal_hand_img : /images/others/1529741660703979752010.jpg
     * apply_state : 1
     * apply_state_show :
     * is_delete : 0
     * create_time : 2018-06-26 17:30:04
     * update_time : 2018-08-20 17:07:43
     * is_collection : 0
     * house_province : 上海市
     * house_city : 上海市
     * house_country : 闵行区
     * house_address : 新骏环路788号
     * house_service_image :
     * house_service_name : superman
     * house_service_desc :
     * hx_account
     * service_range : 精品服务1,精品服务2,精品服务3,精品服务4
     * houseAddressBeans : [{"address_id":5,"house_service_id":2,"house_province":"上海","house_city":"上海市","house_country":"闵行区","house_address":"新骏环路588号","is_delete":"0","create_time":"2018-08-27 16:47:48","update_time":""},{"address_id":6,"house_service_id":2,"house_province":"上海","house_city":"上海市","house_country":"静安区","house_address":"外滩18号","is_delete":"0","create_time":"2018-08-27 16:47:48","update_time":""},{"address_id":7,"house_service_id":2,"house_province":"河南省","house_city":"郑州市","house_country":"高新区","house_address":"科学大道166号","is_delete":"0","create_time":"2018-08-27 16:47:48","update_time":"2018-08-27 16:57:12"}]
     */

    private int house_service_id;
    private int member_id;
    private String member_nick_name;
    private String member_real_name;
    private int age;
    private String contact_name;
    private String sex;
    private String experience;
    private String service_type;
    private String contact_phone;
    private String email_address;
    private String prove_img1;
    private String prove_img2;
    private String legal_face_img;
    private String legal_opposite_img;
    private String legal_hand_img;
    private String apply_state;
    private String apply_state_show;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String is_collection;
    private String house_province;
    private String house_city;
    private String house_country;
    private String house_address;
    private String house_service_image;
    private String house_service_name;
    private String house_service_desc;
    private String service_range;

    public String getHx_account() {
        return hx_account;
    }

    public void setHx_account(String hx_account) {
        this.hx_account = hx_account;
    }

    private String hx_account;
    private List<HouseAddressBeansBean> houseAddressBeans;

    public int getHouse_service_id() {
        return house_service_id;
    }

    public void setHouse_service_id(int house_service_id) {
        this.house_service_id = house_service_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public String getMember_real_name() {
        return member_real_name;
    }

    public void setMember_real_name(String member_real_name) {
        this.member_real_name = member_real_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getProve_img1() {
        return prove_img1;
    }

    public void setProve_img1(String prove_img1) {
        this.prove_img1 = prove_img1;
    }

    public String getProve_img2() {
        return prove_img2;
    }

    public void setProve_img2(String prove_img2) {
        this.prove_img2 = prove_img2;
    }

    public String getLegal_face_img() {
        return legal_face_img;
    }

    public void setLegal_face_img(String legal_face_img) {
        this.legal_face_img = legal_face_img;
    }

    public String getLegal_opposite_img() {
        return legal_opposite_img;
    }

    public void setLegal_opposite_img(String legal_opposite_img) {
        this.legal_opposite_img = legal_opposite_img;
    }

    public String getLegal_hand_img() {
        return legal_hand_img;
    }

    public void setLegal_hand_img(String legal_hand_img) {
        this.legal_hand_img = legal_hand_img;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getApply_state_show() {
        return apply_state_show;
    }

    public void setApply_state_show(String apply_state_show) {
        this.apply_state_show = apply_state_show;
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

    public String getIs_collection() {
        return is_collection;
    }

    public void setIs_collection(String is_collection) {
        this.is_collection = is_collection;
    }

    public String getHouse_province() {
        return house_province;
    }

    public void setHouse_province(String house_province) {
        this.house_province = house_province;
    }

    public String getHouse_city() {
        return house_city;
    }

    public void setHouse_city(String house_city) {
        this.house_city = house_city;
    }

    public String getHouse_country() {
        return house_country;
    }

    public void setHouse_country(String house_country) {
        this.house_country = house_country;
    }

    public String getHouse_address() {
        return house_address;
    }

    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public String getHouse_service_image() {
        return house_service_image;
    }

    public void setHouse_service_image(String house_service_image) {
        this.house_service_image = house_service_image;
    }

    public String getHouse_service_name() {
        return house_service_name;
    }

    public void setHouse_service_name(String house_service_name) {
        this.house_service_name = house_service_name;
    }

    public String getHouse_service_desc() {
        return house_service_desc;
    }

    public void setHouse_service_desc(String house_service_desc) {
        this.house_service_desc = house_service_desc;
    }

    public String getService_range() {
        return service_range;
    }

    public void setService_range(String service_range) {
        this.service_range = service_range;
    }

    public List<HouseAddressBeansBean> getHouseAddressBeans() {
        return houseAddressBeans;
    }

    public void setHouseAddressBeans(List<HouseAddressBeansBean> houseAddressBeans) {
        this.houseAddressBeans = houseAddressBeans;
    }

    public static class HouseAddressBeansBean {
        /**
         * address_id : 5
         * house_service_id : 2
         * house_province : 上海
         * house_city : 上海市
         * house_country : 闵行区
         * house_address : 新骏环路588号
         * is_delete : 0
         * create_time : 2018-08-27 16:47:48
         * update_time :
         */

        private int address_id;
        private int house_service_id;
        private String house_province;
        private String house_city;
        private String house_country;
        private String house_address;
        private String is_delete;
        private String create_time;
        private String update_time;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getHouse_service_id() {
            return house_service_id;
        }

        public void setHouse_service_id(int house_service_id) {
            this.house_service_id = house_service_id;
        }

        public String getHouse_province() {
            return house_province;
        }

        public void setHouse_province(String house_province) {
            this.house_province = house_province;
        }

        public String getHouse_city() {
            return house_city;
        }

        public void setHouse_city(String house_city) {
            this.house_city = house_city;
        }

        public String getHouse_country() {
            return house_country;
        }

        public void setHouse_country(String house_country) {
            this.house_country = house_country;
        }

        public String getHouse_address() {
            return house_address;
        }

        public void setHouse_address(String house_address) {
            this.house_address = house_address;
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
    }
}
