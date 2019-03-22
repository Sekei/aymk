package com.live.tv.mvp.valid;

import android.app.Activity;
import android.content.Context;

import com.live.tv.Constants;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.ContentActivity;
import com.live.tv.util.SpSingleInstance;
import com.toptechs.libaction.action.Valid;


/**
 * Created by jinyabo on 8/12/2017.
 */

public class LoginValid implements Valid {
    private Context context;
    private UserBean userBean;

    public LoginValid(Context context) {
        this.context = context;
    }

    /**
     * check whether it login in or not
     *
     * @return
     */
    @Override
    public boolean check() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (userBean != null) {
            return true;
        }
        return false;

    }


    /**
     * if check() return false. then doValid was called
     */
    @Override
    public void doValid() {
        ContentActivity.start((Activity) context, Constants.LOGIN_FRAGMENT);
    }


}
