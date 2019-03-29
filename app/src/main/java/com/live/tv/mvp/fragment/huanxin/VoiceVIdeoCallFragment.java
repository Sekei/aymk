package com.live.tv.mvp.fragment.huanxin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.GlideCircleTransform;
import com.king.base.util.LogUtils;
import com.king.base.util.ToastUtils;
import com.live.tv.App;
import com.live.tv.Constants;
import com.live.tv.http.HttpResult;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.ConsultBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.VoiceVideoPresenter;
import com.live.tv.mvp.view.mine.IVoiceVideoView;
import com.live.tv.util.TimeUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 语音和视频的拨打界面
 * Created by sh-lx on 2017/7/12.
 */

public class VoiceVIdeoCallFragment extends BaseFragment<IVoiceVideoView, VoiceVideoPresenter> implements IVoiceVideoView {

    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.img_face)
    ImageView imgFace;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_cancel)
    TextView tvTime;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;

    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.textView45)
    TextView textView45;
    Unbinder unbinder;
    private ConsultBean consultBean;
    private String consult_type;
    private String role;
    private Map<String, String> pushmap = new HashMap<>();

    public static VoiceVIdeoCallFragment newInstance(ConsultBean consultBean, String role) {

        Bundle args = new Bundle();
        VoiceVIdeoCallFragment fragment = new VoiceVIdeoCallFragment();
        fragment.consultBean = consultBean;
        fragment.role = role;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(R.color.bg_3);
            tintManager.setStatusBarTintResource(R.color.bg_3);//状态栏所需颜色
            // tintManager.setTintColor(R.color.pure_white);//文字颜色
        }
        App.getInstance().mCallback = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvCall.setVisibility(App.getInstance().mCallback ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_voice_video_call;
    }

    @Override
    public void initUI() {

        if (consultBean != null) {
            consult_type = consultBean.getConsult_type();
            if ("video".equals(consultBean.getConsult_type())) {

                tvType.setText("视频咨询");
                tvCall.setText("立即开始");

            } else if ("phone".equals(consultBean.getConsult_type())) {

                tvType.setText("语音咨询");
                tvCall.setText("立即拨号");

            }


        }


    }

    Date start_date = null;
    Date end_date = null;
    String sex_str = "";

    @Override
    public void initData() {
        if ("doctor".equals(role)) {
            Glide.with(context).load(Constants.BASE_URL + consultBean.getMember_head_image())
                    .transform(new GlideCircleTransform(context))
                    .placeholder(R.drawable.ava_defaultx)
                    .error(R.drawable.ava_defaultx)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(imgFace);
            tvName.setText(consultBean.getMember_nick_name());

            if ("m".equals(consultBean.getMember_sex())) {
                sex_str = "男";
            } else {
                sex_str = "女";
            }

            textView45.setText(sex_str + "  " + consultBean.getMember_age());
        } else {

            Glide.with(context).load(Constants.BASE_URL + consultBean.getDoctor_head_image())
                    .transform(new GlideCircleTransform(context))
                    .placeholder(R.drawable.ava_defaultx)
                    .error(R.drawable.ava_defaultx)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(imgFace);
            tvName.setText(consultBean.getDoctor_name());
            textView45.setText(consultBean.getDoctor_sex() + "  " + consultBean.getDoctor_age());
        }
        tvTime.setText(consultBean.getConsult_start_time());
        tv_end_time.setText(consultBean.getConsult_end_time());

        start_date = TimeUtils.StringToDate(consultBean.getConsult_start_time());
        end_date = TimeUtils.StringToDate(consultBean.getConsult_end_time());

        if (userBean != null && userBean.getDoctor_type() != null) {
            if ("1".equals(userBean.getDoctor_type())) {
                tvCall.setText("立即拨号");
            } else {
                tvCall.setText("等待回复");
            }
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
    public VoiceVideoPresenter createPresenter() {
        return new VoiceVideoPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_call, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_call:
                if ("doctor".equals(role)) {
                    //判断是否在预约的时间内
                    Date curDate = new Date(System.currentTimeMillis());
                    boolean isOvertime = TimeUtils.isEffectiveDate(curDate, start_date, end_date);

                    if (isOvertime) {


                        String ext_content1 = "";
                        String ext_content2 = "";

                        if (consultBean.getDoctor_head_image() != null && !consultBean.getDoctor_head_image().equals("")) {
                            ext_content1 = consultBean.getDoctor_head_image();
                        } else {
                            ext_content1 = "0";
                        }
                        try {
                            pushmap.put("hx_account", consultBean.getHx_account());
                            Umengpush(pushmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        if ("phone".equals(consult_type)) {
                            Intent intent = new Intent(getActivity(), VoiceCallActivity.class);
                            intent.putExtra("username", consultBean.getHx_account());
                            intent.putExtra("doctor_name", consultBean.getMember_nick_name());
                            intent.putExtra("doctor_img", consultBean.getMember_head_image());
                            intent.putExtra("ext_content", ext_content1 + "," + consultBean.getDoctor_name());
                            intent.putExtra("consult_record_id", consultBean.getConsult_record_id());
                            intent.putExtra("isComingCall", false);
                            startActivity(intent);
                        } else if ("video".equals(consult_type)) {
                            startActivity(new Intent(getActivity(), VideoCallActivity.class)
                                    .putExtra("username", consultBean.getHx_account())
                                    .putExtra("doctor_name", consultBean.getDoctor_name())
                                    .putExtra("doctor_name", consultBean.getMember_nick_name())
                                    .putExtra("doctor_img", consultBean.getMember_head_image())
                                    .putExtra("consult_record_id", consultBean.getConsult_record_id())
                                    .putExtra("ext_content", ext_content1 + "," + consultBean.getDoctor_name())
                                    .putExtra("isComingCall", false));

                        }
                    } else {

                        ToastUtils.showToast(context.getApplicationContext(), "不在服务时间内");
                    }


                } else {

                    ToastUtils.showToast(context.getApplicationContext(), "请等待医生");

                }


                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }


    /**
     * make a voice call
     */
    protected void startVoiceCall() {
        if (!EMClient.getInstance().isConnected()) {
            Toast.makeText(getActivity(), R.string.not_connect_to_server, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(getActivity(), VoiceCallActivity.class).putExtra("username", "用户名")
                    .putExtra("isComingCall", false));
            // voiceCallBtn.setEnabled(false);

        }
    }

    @Override
    public void test(String data) {

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

    private void Umengpush(Map<String, String> parmer) {

        ((App) getActivity().getApplication()).getAppCommponent().getAPIService()
                .DeviceTokensHuanXin(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {


                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("Response:" + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        LogUtils.e("Response:" + userBeanHttpResult);

                    }
                });


    }
}
