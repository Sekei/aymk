package com.live.tv.bean;

/**
 * Created by mac1010 on 2018/8/7.
 * 服务类型
 */

public class TypeServiceBean {


    /**
     * service_type_id : 1
     * service_type : 保洁
     * create_time : 2018-06-26 17:11:37
     * update_time :
     * is_delete : 0
     */

    private int service_type_id;
    private String service_type;
    private String create_time;
    private String update_time;
    private String is_delete;

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
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
}
