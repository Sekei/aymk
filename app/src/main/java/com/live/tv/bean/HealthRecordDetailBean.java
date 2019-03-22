package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public class HealthRecordDetailBean implements Parcelable {


    /**
     * health_record_id : 1
     * record_name : 刘小姐
     * height : 160
     * weight :
     * parent_id : 0
     * member_id : 1
     * is_marriage : 未婚
     * allergy : 无
     * allergy_desc : 无
     * real_name :
     * idcard :
     * legal_face_img :
     * legal_opposite_img :
     * legal_hand_img :
     * is_record_authentication : 0
     * sex :
     * relation : 亲人
     * head_image : /images/member/default.png
     * birthday : 1995-07-09
     * mobile_phone : 13011111111
     * apply_state : 0
     * is_delete : 0
     * doctor_id : 0
     * health_record_ids :
     * start_time :
     * end_time :
     * health_record_type : 0
     * is_due_to : 0
     * healthPlanBeans : [{"health_plan_id":1,"doctor_id":1,"doctor_name":"王医生","plan_name":"呼吸恢复第一阶段","plan_desc":"呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段","create_time":"2018-01-11 10:07:22","update_time":"2018-01-11 10:07:22","is_delete":"0","doctor_department":"心内科","start_time":"2017-12-27","end_time":"2018-01-10","done_num":0,"sum_num":14,"health_record_id":"1"}]
     * plan_state :
     * service_type :
     * latest :
     *
     *
     * blood
     * career
     *
     * career	String	N		职业
     is_inherited_disease	String	N		是否有遗传病
     inherited_disease_name	String	Y		遗传病名称
     patient	String	N		患病人
     is_hereditary	String	N		是否隔代遗传
     is_disease	String	N		是否患有重大疾病
     disease_name	String	N		重大疾病名称
     is_inherited	String	N		是否遗传
     diagnose_time	String	N		确诊时间
     pharmacy_name	String	N		用药名称
     */
    private String blood;
    private String career;
    private String is_inherited_disease;
    private String inherited_disease_name;
    private String patient;
    private String is_hereditary;
    private String is_disease;
    private String disease_name;
    private String is_inherited;
    private String diagnose_time;
    private String pharmacy_name;

    public String getBlood() {

        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getIs_inherited_disease() {
        return is_inherited_disease;
    }

    public void setIs_inherited_disease(String is_inherited_disease) {
        this.is_inherited_disease = is_inherited_disease;
    }

    public String getInherited_disease_name() {
        return inherited_disease_name;
    }

    public void setInherited_disease_name(String inherited_disease_name) {
        this.inherited_disease_name = inherited_disease_name;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getIs_hereditary() {
        return is_hereditary;
    }

    public void setIs_hereditary(String is_hereditary) {
        this.is_hereditary = is_hereditary;
    }

    public String getIs_disease() {
        return is_disease;
    }

    public void setIs_disease(String is_disease) {
        this.is_disease = is_disease;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getIs_inherited() {
        return is_inherited;
    }

    public void setIs_inherited(String is_inherited) {
        this.is_inherited = is_inherited;
    }

    public String getDiagnose_time() {
        return diagnose_time;
    }

    public void setDiagnose_time(String diagnose_time) {
        this.diagnose_time = diagnose_time;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }

    public static Creator<HealthRecordDetailBean> getCREATOR() {
        return CREATOR;
    }

    private String health_record_id;
    private String record_name;
    private String height;
    private String weight;
    private String parent_id;
    private String member_id;
    private String is_marriage;
    private String allergy;
    private String allergy_desc;
    private String real_name;
    private String idcard;
    private String legal_face_img;
    private String legal_opposite_img;
    private String legal_hand_img;
    private String is_record_authentication;
    private String sex;
    private String relation;
    private String head_image;
    private String birthday;
    private String mobile_phone;
    private String apply_state;
    private String is_delete;
    private String doctor_id;
    private String health_record_ids;
    private String start_time;
    private String end_time;
    private String health_record_type;
    private String is_due_to;
    private String plan_state;
    private String service_type;
    private String latest;
    private String age;
    private String service_type_show;
    private String authentication_show;
    private List<HealthPlanBeansBean> healthPlanBeans;

    public String getAuthentication_show() {
        return authentication_show;
    }

    public void setAuthentication_show(String authentication_show) {
        this.authentication_show = authentication_show;
    }

    public String getService_type_show() {
        return service_type_show;
    }

    public void setService_type_show(String service_type_show) {
        this.service_type_show = service_type_show;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(String health_record_id) {
        this.health_record_id = health_record_id;
    }

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getIs_marriage() {
        return is_marriage;
    }

    public void setIs_marriage(String is_marriage) {
        this.is_marriage = is_marriage;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getAllergy_desc() {
        return allergy_desc;
    }

    public void setAllergy_desc(String allergy_desc) {
        this.allergy_desc = allergy_desc;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
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

    public String getIs_record_authentication() {
        return is_record_authentication;
    }

    public void setIs_record_authentication(String is_record_authentication) {
        this.is_record_authentication = is_record_authentication;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getHealth_record_ids() {
        return health_record_ids;
    }

    public void setHealth_record_ids(String health_record_ids) {
        this.health_record_ids = health_record_ids;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getHealth_record_type() {
        return health_record_type;
    }

    public void setHealth_record_type(String health_record_type) {
        this.health_record_type = health_record_type;
    }

    public String getIs_due_to() {
        return is_due_to;
    }

    public void setIs_due_to(String is_due_to) {
        this.is_due_to = is_due_to;
    }

    public String getPlan_state() {
        return plan_state;
    }

    public void setPlan_state(String plan_state) {
        this.plan_state = plan_state;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public List<HealthPlanBeansBean> getHealthPlanBeans() {
        return healthPlanBeans;
    }

    public void setHealthPlanBeans(List<HealthPlanBeansBean> healthPlanBeans) {
        this.healthPlanBeans = healthPlanBeans;
    }

    public static class HealthPlanBeansBean implements Parcelable {
        /**
         * health_plan_id : 1
         * doctor_id : 1
         * doctor_name : 王医生
         * plan_name : 呼吸恢复第一阶段
         * plan_desc : 呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段呼吸恢复第一阶段
         * create_time : 2018-01-11 10:07:22
         * update_time : 2018-01-11 10:07:22
         * is_delete : 0
         * doctor_department : 心内科
         * start_time : 2017-12-27
         * end_time : 2018-01-10
         * done_num : 0
         * sum_num : 14
         * health_record_id : 1
         */

        private String health_plan_id;
        private String doctor_id;
        private String doctor_name;
        private String plan_name;
        private String plan_desc;
        private String create_time;
        private String update_time;
        private String is_delete;
        private String doctor_department;
        private String start_time;
        private String end_time;
        private String done_num;
        private String sum_num;
        private String health_record_id;

        public String getHealth_plan_id() {
            return health_plan_id;
        }

        public void setHealth_plan_id(String health_plan_id) {
            this.health_plan_id = health_plan_id;
        }

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getPlan_name() {
            return plan_name;
        }

        public void setPlan_name(String plan_name) {
            this.plan_name = plan_name;
        }

        public String getPlan_desc() {
            return plan_desc;
        }

        public void setPlan_desc(String plan_desc) {
            this.plan_desc = plan_desc;
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

        public String getDoctor_department() {
            return doctor_department;
        }

        public void setDoctor_department(String doctor_department) {
            this.doctor_department = doctor_department;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getDone_num() {
            return done_num;
        }

        public void setDone_num(String done_num) {
            this.done_num = done_num;
        }

        public String getSum_num() {
            return sum_num;
        }

        public void setSum_num(String sum_num) {
            this.sum_num = sum_num;
        }

        public String getHealth_record_id() {
            return health_record_id;
        }

        public void setHealth_record_id(String health_record_id) {
            this.health_record_id = health_record_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.health_plan_id);
            dest.writeString(this.doctor_id);
            dest.writeString(this.doctor_name);
            dest.writeString(this.plan_name);
            dest.writeString(this.plan_desc);
            dest.writeString(this.create_time);
            dest.writeString(this.update_time);
            dest.writeString(this.is_delete);
            dest.writeString(this.doctor_department);
            dest.writeString(this.start_time);
            dest.writeString(this.end_time);
            dest.writeString(this.done_num);
            dest.writeString(this.sum_num);
            dest.writeString(this.health_record_id);
        }

        public HealthPlanBeansBean() {
        }

        protected HealthPlanBeansBean(Parcel in) {
            this.health_plan_id = in.readString();
            this.doctor_id = in.readString();
            this.doctor_name = in.readString();
            this.plan_name = in.readString();
            this.plan_desc = in.readString();
            this.create_time = in.readString();
            this.update_time = in.readString();
            this.is_delete = in.readString();
            this.doctor_department = in.readString();
            this.start_time = in.readString();
            this.end_time = in.readString();
            this.done_num = in.readString();
            this.sum_num = in.readString();
            this.health_record_id = in.readString();
        }

        public static final Creator<HealthPlanBeansBean> CREATOR = new Creator<HealthPlanBeansBean>() {
            @Override
            public HealthPlanBeansBean createFromParcel(Parcel source) {
                return new HealthPlanBeansBean(source);
            }

            @Override
            public HealthPlanBeansBean[] newArray(int size) {
                return new HealthPlanBeansBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.health_record_id);
        dest.writeString(this.record_name);
        dest.writeString(this.height);
        dest.writeString(this.weight);
        dest.writeString(this.parent_id);
        dest.writeString(this.member_id);
        dest.writeString(this.is_marriage);
        dest.writeString(this.allergy);
        dest.writeString(this.allergy_desc);
        dest.writeString(this.real_name);
        dest.writeString(this.idcard);
        dest.writeString(this.legal_face_img);
        dest.writeString(this.legal_opposite_img);
        dest.writeString(this.legal_hand_img);
        dest.writeString(this.is_record_authentication);
        dest.writeString(this.sex);
        dest.writeString(this.relation);
        dest.writeString(this.head_image);
        dest.writeString(this.birthday);
        dest.writeString(this.mobile_phone);
        dest.writeString(this.apply_state);
        dest.writeString(this.is_delete);
        dest.writeString(this.doctor_id);
        dest.writeString(this.health_record_ids);
        dest.writeString(this.start_time);
        dest.writeString(this.end_time);
        dest.writeString(this.health_record_type);
        dest.writeString(this.is_due_to);
        dest.writeString(this.plan_state);
        dest.writeString(this.service_type);
        dest.writeString(this.latest);
        dest.writeString(this.age);
        dest.writeString(this.service_type_show);
        dest.writeString(this.authentication_show);
        dest.writeList(this.healthPlanBeans);
    }

    public HealthRecordDetailBean() {
    }

    protected HealthRecordDetailBean(Parcel in) {
        this.health_record_id = in.readString();
        this.record_name = in.readString();
        this.height = in.readString();
        this.weight = in.readString();
        this.parent_id = in.readString();
        this.member_id = in.readString();
        this.is_marriage = in.readString();
        this.allergy = in.readString();
        this.allergy_desc = in.readString();
        this.real_name = in.readString();
        this.idcard = in.readString();
        this.legal_face_img = in.readString();
        this.legal_opposite_img = in.readString();
        this.legal_hand_img = in.readString();
        this.is_record_authentication = in.readString();
        this.sex = in.readString();
        this.relation = in.readString();
        this.head_image = in.readString();
        this.birthday = in.readString();
        this.mobile_phone = in.readString();
        this.apply_state = in.readString();
        this.is_delete = in.readString();
        this.doctor_id = in.readString();
        this.health_record_ids = in.readString();
        this.start_time = in.readString();
        this.end_time = in.readString();
        this.health_record_type = in.readString();
        this.is_due_to = in.readString();
        this.plan_state = in.readString();
        this.service_type = in.readString();
        this.latest = in.readString();
        this.age = in.readString();
        this.service_type_show = in.readString();
        this.authentication_show = in.readString();
        this.healthPlanBeans = new ArrayList<HealthPlanBeansBean>();
        in.readList(this.healthPlanBeans, HealthPlanBeansBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HealthRecordDetailBean> CREATOR = new Parcelable.Creator<HealthRecordDetailBean>() {
        @Override
        public HealthRecordDetailBean createFromParcel(Parcel source) {
            return new HealthRecordDetailBean(source);
        }

        @Override
        public HealthRecordDetailBean[] newArray(int size) {
            return new HealthRecordDetailBean[size];
        }
    };
}
