package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/7/18
 */

public class PlateListBean implements Parcelable {


    /**
     * post_id : 15
     * plate_id : 1
     * member_id : 4
     * member_nick_name : 8767
     * member_head_image : /images/20180627//1530083095025.jpg
     * forward_member_id : 4
     * forward_post_id : 12
     * post_parent_id : 6
     * post_title : 我也转发一次试试怎么样
     * post_desc :
     * comment_num : 0
     * praise_num : 0
     * forward_num : 0
     * posting_date : 2018-07-02
     * is_praise :
     * is_delete : 0
     * create_time : 2018-07-02 16:43:32
     * update_time :
     * postBean : {"post_id":6,"plate_id":"1","member_id":"28","member_nick_name":"神医","member_head_image":"/images/20180627//1530083095025.jpg","forward_member_id":"","forward_post_id":"","post_parent_id":"","post_title":"1","post_desc":"1","comment_num":"0","praise_num":"0","forward_num":"0","posting_date":"2018-07-02","is_praise":"","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":"","postBean":{},"postImageBeans":[{"post_image_id":5,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""},{"post_image_id":6,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""},{"post_image_id":7,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""}],"commentPostBeans":[{"comment_id":2,"member_id":"4","post_id":"6","comment_desc":"哎呦，不错哦","member_nick_name":"8767","member_head_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-18 10:26:36","update_time":""}]}
     * postImageBeans : [{"post_image_id":5,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""},{"post_image_id":6,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""},{"post_image_id":7,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""}]
     * commentPostBeans : [{"comment_id":2,"member_id":"4","post_id":"6","comment_desc":"哎呦，不错哦","member_nick_name":"8767","member_head_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-18 10:26:36","update_time":""}]
     */

    private int post_id;
    private String plate_id;
    private String member_id;
    private String member_nick_name;
    private String member_head_image;
    private String forward_member_id;
    private String forward_post_id;
    private String post_parent_id;
    private String post_title;
    private String post_desc;
    private String comment_num;
    private String praise_num;
    private String forward_num;
    private String posting_date;
    private String is_praise;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String read_num;
    private String post_image;

    public PlateListBean() {
    }
    protected PlateListBean(Parcel in) {
        post_id = in.readInt();
        plate_id = in.readString();
        member_id = in.readString();
        member_nick_name = in.readString();
        member_head_image = in.readString();
        forward_member_id = in.readString();
        forward_post_id = in.readString();
        post_parent_id = in.readString();
        post_title = in.readString();
        post_desc = in.readString();
        comment_num = in.readString();
        praise_num = in.readString();
        forward_num = in.readString();
        posting_date = in.readString();
        is_praise = in.readString();
        is_delete = in.readString();
        create_time = in.readString();
        update_time = in.readString();
        post_html_desc = in.readString();
    }


    public static final Creator<PlateListBean> CREATOR = new Creator<PlateListBean>() {
        @Override
        public PlateListBean createFromParcel(Parcel in) {
            return new PlateListBean(in);
        }

        @Override
        public PlateListBean[] newArray(int size) {
            return new PlateListBean[size];
        }
    };

    public String getPost_html_desc() {
        return post_html_desc;
    }

    public void setPost_html_desc(String post_html_desc) {
        this.post_html_desc = post_html_desc;
    }

    private String post_html_desc;
    private PostBeanBeanX postBean;
    private List<PostImageBeansBeanX> postImageBeans;
    private List<CommentPostBeansBeanX> commentPostBeans;

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(String plate_id) {
        this.plate_id = plate_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_nick_name() {
        return member_nick_name;
    }

    public void setMember_nick_name(String member_nick_name) {
        this.member_nick_name = member_nick_name;
    }

    public String getMember_head_image() {
        return member_head_image;
    }

    public void setMember_head_image(String member_head_image) {
        this.member_head_image = member_head_image;
    }

    public String getForward_member_id() {
        return forward_member_id;
    }

    public void setForward_member_id(String forward_member_id) {
        this.forward_member_id = forward_member_id;
    }

    public String getForward_post_id() {
        return forward_post_id;
    }

    public void setForward_post_id(String forward_post_id) {
        this.forward_post_id = forward_post_id;
    }

    public String getPost_parent_id() {
        return post_parent_id;
    }

    public void setPost_parent_id(String post_parent_id) {
        this.post_parent_id = post_parent_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getPraise_num() {
        return praise_num;
    }

    public String getRead_num() {
        return read_num;
    }

    public void setRead_num(String read_num) {
        this.read_num = read_num;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public String getForward_num() {
        return forward_num;
    }

    public void setForward_num(String forward_num) {
        this.forward_num = forward_num;
    }

    public String getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(String posting_date) {
        this.posting_date = posting_date;
    }

    public String getIs_praise() {
        return is_praise;
    }

    public void setIs_praise(String is_praise) {
        this.is_praise = is_praise;
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

    public PostBeanBeanX getPostBean() {
        return postBean;
    }

    public void setPostBean(PostBeanBeanX postBean) {
        this.postBean = postBean;
    }

    public List<PostImageBeansBeanX> getPostImageBeans() {
        return postImageBeans;
    }

    public void setPostImageBeans(List<PostImageBeansBeanX> postImageBeans) {
        this.postImageBeans = postImageBeans;
    }

    public List<CommentPostBeansBeanX> getCommentPostBeans() {
        return commentPostBeans;
    }

    public void setCommentPostBeans(List<CommentPostBeansBeanX> commentPostBeans) {
        this.commentPostBeans = commentPostBeans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(post_id);
        dest.writeString(plate_id);
        dest.writeString(member_id);
        dest.writeString(member_nick_name);
        dest.writeString(member_head_image);
        dest.writeString(forward_member_id);
        dest.writeString(forward_post_id);
        dest.writeString(post_parent_id);
        dest.writeString(post_title);
        dest.writeString(post_desc);
        dest.writeString(comment_num);
        dest.writeString(praise_num);
        dest.writeString(forward_num);
        dest.writeString(posting_date);
        dest.writeString(is_praise);
        dest.writeString(is_delete);
        dest.writeString(create_time);
        dest.writeString(update_time);
        dest.writeString(post_html_desc);
    }


    public static class PostBeanBeanX {
        /**
         * post_id : 6
         * plate_id : 1
         * member_id : 28
         * member_nick_name : 神医
         * member_head_image : /images/20180627//1530083095025.jpg
         * forward_member_id :
         * forward_post_id :
         * post_parent_id :
         * post_title : 1
         * post_desc : 1
         * comment_num : 0
         * praise_num : 0
         * forward_num : 0
         * posting_date : 2018-07-02
         * is_praise :
         * is_delete : 0
         * create_time : 2018-07-02 10:13:35
         * update_time :
         * postBean : {}
         * postImageBeans : [{"post_image_id":5,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""},{"post_image_id":6,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""},{"post_image_id":7,"post_id":"6","post_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-02 10:13:35","update_time":""}]
         * commentPostBeans : [{"comment_id":2,"member_id":"4","post_id":"6","comment_desc":"哎呦，不错哦","member_nick_name":"8767","member_head_image":"/images/20180627//1530083095025.jpg","is_delete":"0","create_time":"2018-07-18 10:26:36","update_time":""}]
         */

        private int post_id;
        private String plate_id;
        private String member_id;
        private String member_nick_name;
        private String member_head_image;
        private String forward_member_id;
        private String forward_post_id;
        private String post_parent_id;
        private String post_title;
        private String post_desc;
        private String comment_num;
        private String praise_num;
        private String forward_num;
        private String posting_date;
        private String is_praise;
        private String is_delete;
        private String create_time;
        private String update_time;

        public String getPost_html_desc() {
            return post_html_desc;
        }

        public void setPost_html_desc(String post_html_desc) {
            this.post_html_desc = post_html_desc;
        }

        private String post_html_desc;
        private PostBeanBean postBean;
        private List<PostImageBeansBean> postImageBeans;
        private List<CommentPostBeansBean> commentPostBeans;

        public int getPost_id() {
            return post_id;
        }

        public void setPost_id(int post_id) {
            this.post_id = post_id;
        }

        public String getPlate_id() {
            return plate_id;
        }

        public void setPlate_id(String plate_id) {
            this.plate_id = plate_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_nick_name() {
            return member_nick_name;
        }

        public void setMember_nick_name(String member_nick_name) {
            this.member_nick_name = member_nick_name;
        }

        public String getMember_head_image() {
            return member_head_image;
        }

        public void setMember_head_image(String member_head_image) {
            this.member_head_image = member_head_image;
        }

        public String getForward_member_id() {
            return forward_member_id;
        }

        public void setForward_member_id(String forward_member_id) {
            this.forward_member_id = forward_member_id;
        }

        public String getForward_post_id() {
            return forward_post_id;
        }

        public void setForward_post_id(String forward_post_id) {
            this.forward_post_id = forward_post_id;
        }

        public String getPost_parent_id() {
            return post_parent_id;
        }

        public void setPost_parent_id(String post_parent_id) {
            this.post_parent_id = post_parent_id;
        }

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public String getPost_desc() {
            return post_desc;
        }

        public void setPost_desc(String post_desc) {
            this.post_desc = post_desc;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getPraise_num() {
            return praise_num;
        }

        public void setPraise_num(String praise_num) {
            this.praise_num = praise_num;
        }

        public String getForward_num() {
            return forward_num;
        }

        public void setForward_num(String forward_num) {
            this.forward_num = forward_num;
        }

        public String getPosting_date() {
            return posting_date;
        }

        public void setPosting_date(String posting_date) {
            this.posting_date = posting_date;
        }

        public String getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(String is_praise) {
            this.is_praise = is_praise;
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

        public PostBeanBean getPostBean() {
            return postBean;
        }

        public void setPostBean(PostBeanBean postBean) {
            this.postBean = postBean;
        }

        public List<PostImageBeansBean> getPostImageBeans() {
            return postImageBeans;
        }

        public void setPostImageBeans(List<PostImageBeansBean> postImageBeans) {
            this.postImageBeans = postImageBeans;
        }

        public List<CommentPostBeansBean> getCommentPostBeans() {
            return commentPostBeans;
        }

        public void setCommentPostBeans(List<CommentPostBeansBean> commentPostBeans) {
            this.commentPostBeans = commentPostBeans;
        }

        public static class PostBeanBean {
        }

        public static class PostImageBeansBean {
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

        public static class CommentPostBeansBean {
            /**
             * comment_id : 2
             * member_id : 4
             * post_id : 6
             * comment_desc : 哎呦，不错哦
             * member_nick_name : 8767
             * member_head_image : /images/20180627//1530083095025.jpg
             * is_delete : 0
             * create_time : 2018-07-18 10:26:36
             * update_time :
             */

            private int comment_id;
            private String member_id;
            private String post_id;
            private String comment_desc;
            private String member_nick_name;
            private String member_head_image;
            private String is_delete;
            private String create_time;
            private String update_time;

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getPost_id() {
                return post_id;
            }

            public void setPost_id(String post_id) {
                this.post_id = post_id;
            }

            public String getComment_desc() {
                return comment_desc;
            }

            public void setComment_desc(String comment_desc) {
                this.comment_desc = comment_desc;
            }

            public String getMember_nick_name() {
                return member_nick_name;
            }

            public void setMember_nick_name(String member_nick_name) {
                this.member_nick_name = member_nick_name;
            }

            public String getMember_head_image() {
                return member_head_image;
            }

            public void setMember_head_image(String member_head_image) {
                this.member_head_image = member_head_image;
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

    public static class PostImageBeansBeanX {
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

    public static class CommentPostBeansBeanX {
        /**
         * comment_id : 2
         * member_id : 4
         * post_id : 6
         * comment_desc : 哎呦，不错哦
         * member_nick_name : 8767
         * member_head_image : /images/20180627//1530083095025.jpg
         * is_delete : 0
         * create_time : 2018-07-18 10:26:36
         * update_time :
         */

        private int comment_id;
        private String member_id;
        private String post_id;
        private String comment_desc;
        private String member_nick_name;
        private String member_head_image;
        private String is_delete;
        private String create_time;
        private String update_time;

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getComment_desc() {
            return comment_desc;
        }

        public void setComment_desc(String comment_desc) {
            this.comment_desc = comment_desc;
        }

        public String getMember_nick_name() {
            return member_nick_name;
        }

        public void setMember_nick_name(String member_nick_name) {
            this.member_nick_name = member_nick_name;
        }

        public String getMember_head_image() {
            return member_head_image;
        }

        public void setMember_head_image(String member_head_image) {
            this.member_head_image = member_head_image;
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
