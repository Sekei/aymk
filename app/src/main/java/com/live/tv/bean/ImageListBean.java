package com.live.tv.bean;

/**
 * @author Created by stone
 * @since 2018/7/18
 */

public class ImageListBean {

    /**
     * post_image_id : 5
     * post_id : 6
     * post_image : /images/20180627//1530083095025.jpg
     * is_delete : 0
     * create_time : 2018-07-02 10:13:35
     * update_time :
     */

    private int post_image_id;
    private String post_id;
    private String post_image;
    private String is_delete;
    private String create_time;
    private String update_time;

    public int getPost_image_id() {
        return post_image_id;
    }

    public void setPost_image_id(int post_image_id) {
        this.post_image_id = post_image_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
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
