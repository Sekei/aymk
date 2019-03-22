package com.live.tv.mvp.activity;

import android.content.Intent;
import android.view.animation.Animation;

import com.king.base.SplashActivity;
import com.live.tv.Constants;
import com.live.tv.MainActivity;
import com.ysjk.health.iemk.R;

import static com.live.tv.Constants.LOGIN_FRAGMENT;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class WelcomeActivity extends SplashActivity {
    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                /*UserBean userBean = SPUtils.getObj1(getApplication(), Constants.USER_BEAN);
                if (userBean == null) {
                    startLogin();
                    finish();
                } else {
                    startActivityFinish(MainActivity.class);
                }*/
            }

            @Override
            public void onAnimationEnd(Animation animation) {
               startActivityFinish(MainActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    protected void startLogin() {
        Intent intent = new Intent(context, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, LOGIN_FRAGMENT);
        startActivity(intent);
    }
}
