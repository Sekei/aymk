package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/1/27
 */

public class MemberMsgsBean {

    /**
     * msg_id : 1
     * member_id : 7
     * msg_title : 待发货
     * msg_desc : 您已付款成功，等待商家发货
     * msg_type : order
     * order_id : 1
     * order_no : 1888071442201838594
     * create_time : 2017-12-22 10:43:59.0
     * update_time :
     * is_delete : 0
     * is_read : 0
     */

    private int msg_id;
    private String member_id;
    private String msg_title;
    private String msg_desc;
    private String msg_type;
    private String order_id;
    private String order_no;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String is_read;

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
    }

    public String getMsg_desc() {
        return msg_desc;
    }

    public void setMsg_desc(String msg_desc) {
        this.msg_desc = msg_desc;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getCreate_time() {
        return create_time.substring(0,11);
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

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }
}
