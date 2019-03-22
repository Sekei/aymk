package com.live.tv.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/9
 */

public class DepartmentLevelTwoBean {

    /**
     * department_id : 4
     * department_name : 心脏科二级
     * create_time : 2018-01-22 14:37:25
     * update_time :
     * is_delete : 0
     * parent_id : 1
     * departmentBeans : []
     */

    private int department_id;
    private String department_name;
    private String create_time;
    private String update_time;
    private String is_delete;
    private int parent_id;
    private List<?> departmentBeans;

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
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

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<?> getDepartmentBeans() {
        return departmentBeans;
    }

    public void setDepartmentBeans(List<?> departmentBeans) {
        this.departmentBeans = departmentBeans;
    }
}
