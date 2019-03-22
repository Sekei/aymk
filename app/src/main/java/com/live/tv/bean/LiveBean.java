package com.live.tv.bean;

/**
 * Created by taoh on 2018/5/12.
 */

public class LiveBean {


    /**
     * live_id : 6
     * member_id : 28
     * room_id : 1891153116695364616
     * stream_id : 2002606965527675911
     * live_title :
     * live_img : http://pili-live-snapshot.tstmobile.com/vxiu1/2002606965527675911.jpg
     * live_push_url : rtmp://qntest.tstmobile.com/vxiu1/2002606965527675911?e=1527567928&token=pR_CsEkFcTn1Kgf8ZNIh2zUB_w8bzaeLYEgjBItT:OHB9GZLqwQ3AJmHVTVimMLF49Xs=
     * live_play_rtmp_url : rtmp://pili-live-rtmp.tstmobile.com/vxiu1/2002606965527675911
     * live_play_flv_url : http://pili-live-hdl.tstmobile.com/vxiu1/2002606965527675911.flv
     * live_play_hls_url : http://pili-live-hls.tstmobile.com/vxiu1/2002606965527675911.m3u8
     * live_longitude :
     * live_latitude :
     * live_state :
     * create_time :
     * is_delete :
     * live_start_time : 2018-05-29 11:25:28
     * live_end_time : 2018-05-30 11:25:28
     * goods_id :
     * goods_ids :
     * collection_id :
     */

    private String live_id;
    private String member_id;
    private String room_id;
    private String stream_id;
    private String live_title;
    private String live_img;
    private String live_push_url;
    private String live_play_rtmp_url;
    private String live_play_flv_url;
    private String live_play_hls_url;
    private String live_longitude;
    private String live_latitude;
    private String live_state;
    private String create_time;
    private String is_delete;
    private String live_start_time;
    private String live_end_time;
    private String goods_id;
    private String goods_ids;
    private String collection_id;
    private String viewing_num;
    private String live_playback_url;

    private DoctorDetailBean doctorBean;

    public String getLive_playback_url() {
        return live_playback_url;
    }

    public void setLive_playback_url(String live_playback_url) {
        this.live_playback_url = live_playback_url;
    }

    public String getViewing_num() {
        return viewing_num;
    }

    public void setViewing_num(String viewing_num) {
        this.viewing_num = viewing_num;
    }

    public DoctorDetailBean getDoctorBean() {
        return doctorBean;
    }

    public void setDoctorBean(DoctorDetailBean doctorBean) {
        this.doctorBean = doctorBean;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getStream_id() {
        return stream_id;
    }

    public void setStream_id(String stream_id) {
        this.stream_id = stream_id;
    }

    public String getLive_title() {
        return live_title;
    }

    public void setLive_title(String live_title) {
        this.live_title = live_title;
    }

    public String getLive_img() {
        return live_img;
    }

    public void setLive_img(String live_img) {
        this.live_img = live_img;
    }

    public String getLive_push_url() {
        return live_push_url;
    }

    public void setLive_push_url(String live_push_url) {
        this.live_push_url = live_push_url;
    }

    public String getLive_play_rtmp_url() {
        return live_play_rtmp_url;
    }

    public void setLive_play_rtmp_url(String live_play_rtmp_url) {
        this.live_play_rtmp_url = live_play_rtmp_url;
    }

    public String getLive_play_flv_url() {
        return live_play_flv_url;
    }

    public void setLive_play_flv_url(String live_play_flv_url) {
        this.live_play_flv_url = live_play_flv_url;
    }

    public String getLive_play_hls_url() {
        return live_play_hls_url;
    }

    public void setLive_play_hls_url(String live_play_hls_url) {
        this.live_play_hls_url = live_play_hls_url;
    }

    public String getLive_longitude() {
        return live_longitude;
    }

    public void setLive_longitude(String live_longitude) {
        this.live_longitude = live_longitude;
    }

    public String getLive_latitude() {
        return live_latitude;
    }

    public void setLive_latitude(String live_latitude) {
        this.live_latitude = live_latitude;
    }

    public String getLive_state() {
        return live_state;
    }

    public void setLive_state(String live_state) {
        this.live_state = live_state;
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

    public String getLive_start_time() {
        return live_start_time;
    }

    public void setLive_start_time(String live_start_time) {
        this.live_start_time = live_start_time;
    }

    public String getLive_end_time() {
        return live_end_time;
    }

    public void setLive_end_time(String live_end_time) {
        this.live_end_time = live_end_time;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_ids() {
        return goods_ids;
    }

    public void setGoods_ids(String goods_ids) {
        this.goods_ids = goods_ids;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }
}
