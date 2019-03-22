package com.live.tv.util;


import com.live.tv.bean.UserBean;


/**
 * Created by lenove on 2016/9/1.
 */
public class SpSingleInstance {
    private static SpSingleInstance sp;
    public UserBean userBean;
    private SpSingleInstance(){};
    public static synchronized SpSingleInstance  getSpSingleInstance(){
        if(sp==null){
            sp=new SpSingleInstance();
            return sp;
        }
        return  sp;
    }


    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    public UserBean getUserBean(){
        if (userBean == null) {
            userBean = new UserBean();
        }
        return userBean;
    }

}
