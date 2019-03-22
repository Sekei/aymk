package com.live.tv.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.HtmlBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.SettingPresenter;
import com.live.tv.mvp.view.mine.ISettingView;
import com.live.tv.util.CustomDialog;
import com.live.tv.util.DataCleanManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class SettingFragment extends BaseFragment<ISettingView,SettingPresenter>implements ISettingView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.cache_size)
    TextView cacheSize;
    Unbinder unbinder;


    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initUI() {
        tvTitle.setText("设置");
        try {
            cacheSize.setText(DataCleanManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initData() {

    }

    private void exit() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否确认退出登录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

              map.clear();
                map.put("member_id",userBean.getMember_id());
                getPresenter().LogoutDeviceTokens(map);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }


    @OnClick({R.id.ivLeft, R.id.clean, R.id.logout, R.id.suggest, R.id.about_us,R.id.account,
            R.id.meaasge,R.id.address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.about_us:
               getPresenter().GetAboutUs(map);
                break;
            case R.id.clean:
                DataCleanManager.clearAllCache(context);
                try {
                    cacheSize.setText(DataCleanManager.getTotalCacheSize(getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.logout:
                exit();


                break;
            case R.id.suggest:
                startFanhuiFragment();
                break;

            case R.id.account:

                startAccountSecurityFragment();
                break;

            case R.id.meaasge:

                startMessageRemindFragment();
                break;

            case R.id.address:

                startAddressFragment("");
                 break;



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAboutUs(HtmlBean data) {

        if (data!=null){

            startWeb("关于我们", Constants.BASE_URL + data.getHtml_url(), "3");

        }

    }
//注销devicetoken
    @Override
    public void logoutDeviceTokens(String str) {
        EventBus.getDefault().post(new FirstEvent("logout"));
        exitLogin();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }
}
