package com.live.tv.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.amap.api.services.core.PoiItem;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hyphenate.chat.EMClient;
import com.king.base.util.LogUtils;
import com.king.base.util.ToastUtils;
import com.live.tv.App;
import com.live.tv.Constants;
import com.live.tv.bean.AddressBean;
import com.live.tv.bean.AssociatorBean;
import com.live.tv.bean.CartBean;
import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.bean.Note;
import com.live.tv.bean.OrderGoodsBeans;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.activity.ContentActivity;
import com.live.tv.util.NetWorkUtils;
import com.live.tv.util.SPUtils;
import com.live.tv.util.SpSingleInstance;

import org.apache.http.conn.ConnectTimeoutException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.HttpException;

import static android.app.Activity.RESULT_OK;
import static com.live.tv.Constants.HEALTH_RECORD_ID;
import static com.live.tv.Constants.PRICE;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends MvpFragment<V, P> {

    protected Context context;

    private View rootView;

    private Unbinder mUnbinder;
    public UserBean userBean;
    public Map<String, String> map = new HashMap<>();

    public <T extends View> T findView(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        rootView = inflater.inflate(getRootViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        LogUtils.d("onCreateView");
        initUI();


        return rootView;
    }

    /**
     * 处理错误信息
     *
     * @param e
     */

    public void errorHandle(Throwable e) {

        String mess = e.getMessage();

        try {
            if (!NetWorkUtils.isNetConnected(context.getApplicationContext())) {
                //无网络状态
                mess = "网络错误";
            } else if (e instanceof HttpException) {
                //服务器
                mess = "服务器错误";
            } else if (e instanceof IllegalArgumentException) {
                //网络错误
                mess = "服务器异常";
            } else if (e instanceof NullPointerException) {
                mess = "服务器异常";
            } else if (e instanceof SocketTimeoutException) {
                mess = "服务器响应超时";
            } else if (e instanceof ConnectTimeoutException) {
                mess = "与服务器连接超时";
            } else if (e.getMessage().startsWith("Unable to resolve host")) {
                return;
            }

            if (mess.equals("token failed")) {
                ToastUtils.showToast(context.getApplicationContext(), "账号过期，请重新登录");
                startLogin();
            } else {
                ToastUtils.showToast(context.getApplicationContext(), mess);
            }
        }catch (Exception ee){
            ToastUtils.showToast(context.getApplicationContext(), mess);

        }



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        initData();
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public App getApp() {
        return (App) getActivity().getApplication();
    }

    public <T> void toSetList(List<T> list, List<T> newList, boolean isMore) {

        if (list != null && newList != null) {
            synchronized (BaseFragment.class) {
                if (!isMore) {
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }


    public static void registerEvent(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public static void unregisterEvent(Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public static void sendEvent(Object obj) {
        EventBus.getDefault().post(obj);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj) {

    }

    //--------------------------------

    protected Intent getIntent() {
        return getActivity().getIntent();
    }


    protected Intent getFragmentIntent(int fragmentKey) {
        Intent intent = getContentActivityIntent();
        intent.putExtra(Constants.KEY_FRAGMENT, fragmentKey);
        return intent;
    }

    protected Intent getContentActivityIntent() {
        return new Intent(context, ContentActivity.class);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(context, cls));
    }


    protected void finish() {
        getActivity().finish();
    }


    protected void startWeb(String title, String url) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        startActivity(intent);
    }
//PostedFragment


    protected void startWeb(String title, String url, String type) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        intent.putExtra(Constants.KEY_SLUG, type);
        startActivity(intent);
    }

    //登录
    protected void startLogin() {
        Intent intent = getFragmentIntent(Constants.LOGIN_FRAGMENT);
        startActivity(intent);
    }
    //投诉
    protected void startComplantFragment(String houseid) {
        Intent intent = getFragmentIntent(Constants.COMPLAINTFRAGMENT);
        intent.putExtra(Constants.TYPE,houseid);
        startActivity(intent);
    }
    //商家简介
    protected void startermerchantaboutFragment() {
        Intent intent = getFragmentIntent(Constants.MERCHANTABOUT);
        startActivity(intent);
    }
    //商家更多地址
    protected void merchantamoreaddreFragment(String serviceId) {
        Intent intent = getFragmentIntent(Constants.MERCHANTMOREADDRESS);
        intent.putExtra("house_service_id",serviceId);
        startActivity(intent);
    }
    //注册
    protected void statrRegistered(String type,String tag,String code) {
        Intent intent = getFragmentIntent(Constants.REGISTER);
        intent.putExtra(Constants.TYPE,type);
        intent.putExtra(Constants.TAG,tag);
        intent.putExtra(Constants.OPENID,code);
        startActivity(intent);
    }

    //选择医生
    protected void startDoctorListFragment() {
        Intent intent = getFragmentIntent(Constants.DOCTOR_LIST);
        startActivity(intent);
    }
    //编辑服务
    protected void startEditserviceFragment() {
        Intent intent = getFragmentIntent(Constants.EDITSERVICE);
        startActivity(intent);
    }
    //编辑服务
    protected void startEditserviceFragment(ServiceCopeBean serviceCopeBean) {
        Intent intent = getFragmentIntent(Constants.EDITSERVICE);
       Gson gson= new Gson();
        intent.putExtra(Constants.TAG,gson.toJson(serviceCopeBean));
        startActivity(intent);
    }
    //服务范围
    protected void startservicecopeFragment() {
        Intent intent = getFragmentIntent(Constants.SERVICECOPE);
        startActivity(intent);
    }
    //商家消息
    protected void startMerchantmessagefragment() {
        Intent intent = getFragmentIntent(Constants.MERCHANTMESSAGE);
        startActivity(intent);
    }
    //服务详情
    protected void startServiceDetailFragment() {
        Intent intent = getFragmentIntent(Constants.SERVICEDETILS);
        startActivity(intent);
    }

    //服务更多评论
    protected void startServiceMoreFragment(String serviceId) {
        Intent intent = getFragmentIntent(Constants.SERVICEMOREASSESSMENT);
        intent.putExtra(Constants.TYPE,serviceId);
        startActivity(intent);
    }
    //发帖
    protected void startPostedFragment(Note note) {
        Intent intent = getFragmentIntent(Constants.POSTEDFRGAMENT);
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        intent.putExtra("data", bundle);
        intent.putExtra("flag", 1);//编辑笔记
        startActivity(intent);
    }
    //发帖
    protected void startPostedFragment(String mPlateid) {
        Intent intent = getFragmentIntent(Constants.POSTEDFRGAMENT);
        intent.putExtra("flag", 0);//编辑笔记
        intent.putExtra(Constants.PLATE_ID,mPlateid);
        startActivity(intent);
    }


    protected void startPostedListFragment() {
        Intent intent = getFragmentIntent(Constants.POSTEDListFRGAMENT);
        startActivity(intent);
    }

    /***
     *商家订单
     */
    protected void startformtFragment(String s) {
        Intent intent = getFragmentIntent(Constants.MERCHANTFORMFRAGMENT);
        intent.putExtra(Constants.TYPE,s);
        startActivity(intent);
    }
    /***
     *商家订单
     */
    protected void addhealthmanagefragment(String s) {
        Intent intent = getFragmentIntent(Constants.ADDHEALTHMANAGE);
        intent.putExtra(Constants.TYPE,s);
        startActivity(intent);
    }/***
     *商家订单
     */
    protected void addhealthmanagefragment(HealthManagerBeans bean) {
        Intent intent = getFragmentIntent(Constants.ADDHEALTHMANAGE);
        intent.putExtra(Constants.HEALTH_RECORD_ID,bean);
        startActivity(intent);
    }
    //医生详情
    protected void startDoctorDetailFragment(String doctor_id) {
        Intent intent = getFragmentIntent(Constants.DOCTOR_DETAIL);
        intent.putExtra(Constants.DOCTOR_ID, doctor_id);
        startActivity(intent);
    }

    //健康测试
    protected void startTestFragment() {
        Intent intent = getFragmentIntent(Constants.TEST);
        startActivity(intent);
    }

    //图文咨询
    protected void startRequestOneFragment(String doctor_id, String doctor_name, String price, String Hx_account) {
        Intent intent = getFragmentIntent(Constants.REQUEST_ONE);
        intent.putExtra(Constants.DOCTOR_ID, doctor_id);
        intent.putExtra(Constants.PRICE, price);
        intent.putExtra("doctorName", doctor_name);
        intent.putExtra("Hx_account", Hx_account);
        startActivity(intent);
    }

    //预约时间
    protected void startChooseTimeFragment(String doctor_id, String type, String price) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_TIME);
        intent.putExtra(Constants.DOCTOR_ID, doctor_id);
        intent.putExtra(Constants.TYPE, type);
        intent.putExtra(Constants.PRICE, price);
        startActivity(intent);
    }

    //普通测试
    protected void startCommonTestFragment() {
        Intent intent = getFragmentIntent(Constants.COMMON_TEST);
        startActivity(intent);
    }

    //专业测试
    protected void startProfessionalTestFragment() {
        Intent intent = getFragmentIntent(Constants.PEROFESSIONAL_TEST);
        startActivity(intent);
    }

    //商城界面
    protected void startshopfragment() {
        Intent intent = getFragmentIntent(Constants.SHOPFRAGMENT);
        startActivity(intent);
    }

    //送心意
    protected void startSendHeartFragment(String doctor_id, String sendDoctorName, String member_head_image) {
        Intent intent = getFragmentIntent(Constants.SEND_HEART);
        intent.putExtra(Constants.DOCTOR_ID, doctor_id);
        intent.putExtra(Constants.SEND_DOCTOR_NAME, sendDoctorName);
        intent.putExtra("member_head_image", member_head_image);
        startActivity(intent);
    }


    //健康档案
    protected void startHeartFileFragment() {
        Intent intent = getFragmentIntent(Constants.HEALTH_FILE);
        startActivity(intent);
    }

    //更多档案
    protected void startFileMoreFragment(String type) {
        Intent intent = getFragmentIntent(Constants.MORE_FILE);
        intent.putExtra(Constants.TYPE, type);
        startActivityForResult(intent, Constants.REQUESTCODE);
    }

    //修改咨询人
    protected void startMoreConsultantFragment() {
        Intent intent = getFragmentIntent(Constants.MORECONSULTANT_FRAGMENT);
        startActivityForResult(intent, Constants.REQUESTCODE);
    }


    //购买服务
    protected void startPayServerFragment(String payType, String pirce, String three, String four, String five) {
        Intent intent = getFragmentIntent(Constants.PAY_SERVER);
        intent.putExtra(Constants.PAY_TYPE, payType);
        intent.putExtra(PRICE, pirce);
        intent.putExtra(Constants.THREE, three);
        intent.putExtra(Constants.FOUR, four);
        intent.putExtra(Constants.FIVE, five);
        startActivity(intent);
    }


    //支付成功
    protected void startPaySuccessFragment() {
        Intent intent = getFragmentIntent(Constants.PAY_SUCCESS);
        startActivity(intent);
    }

    //健康管理
    protected void startHealthManageFragment(String doctor_id, String doctor_name) {
        Intent intent = getFragmentIntent(Constants.HEALTH_MANAGE);
        intent.putExtra(Constants.DOCTOR_NAME, doctor_name);
        intent.putExtra(Constants.DOCTOR_ID, doctor_id);
        startActivity(intent);
    }

    //测试首页
    protected void startTestHomePageFragment() {
        Intent intent = getFragmentIntent(Constants.TEST_HOMEPAGE);
        startActivity(intent);
    }

    //普通测试结果
    protected void startTestCommonResultFragment() {
        Intent intent = getFragmentIntent(Constants.TEST_COMMON_RESULT);
        startActivity(intent);
    }

    //测试历史
    protected void startTestHistoryFragment() {
        Intent intent = getFragmentIntent(Constants.TEST_HISTORY);
        startActivityForResult(intent, Constants.INTENT_TEST_DATA);
    }

    //测试说明
    protected void startTestDescriptionFragment() {
        Intent intent = getFragmentIntent(Constants.TEST_DESCRIPTION);
        startActivity(intent);
    }

    //健康详情界面
    protected void startPostDetailsFragment(String post) {
        Intent intent = getFragmentIntent(Constants.POSTEDETAILS);
        intent.putExtra(Constants.PLATELISTBEAN,post);
        startActivity(intent);
    }
    //测试完成
    protected void startTestCompleteFragment(String health_record_id) {
        Intent intent = getFragmentIntent(Constants.TEST_COMPLETE);
        intent.putExtra(HEALTH_RECORD_ID, health_record_id);
        startActivity(intent);
    }
 //交流社区
    protected void startCommunicateFragment(String str) {
        Intent intent = getFragmentIntent(Constants.COMMUNICATEFRAGMENT);
        intent.putExtra(Constants.TYPE,str);
        startActivity(intent);
    }

    //诊断报告
    protected void startTestDiagnoseReportFragment(String health_record_id) {
        Intent intent = getFragmentIntent(Constants.TEST_DIAGNOSE_REPORT);
        intent.putExtra(HEALTH_RECORD_ID, health_record_id);
        startActivity(intent);
    }

    //诊断结果目录
    protected void startTestResultTabFragment(String test_id) {
        Intent intent = getFragmentIntent(Constants.TEST_RESULT_TAB);
        intent.putExtra(Constants.TEST_ID, test_id);
        startActivity(intent);
    }


    //综合辨析 体制成因 体制问题
    protected void startTestResultTabNextFragment(String health_record_id, String type) {
        Intent intent = getFragmentIntent(Constants.TEST_RESULT_TAB_NEXT);
        intent.putExtra(HEALTH_RECORD_ID, health_record_id);
        intent.putExtra(Constants.TYPE, type);
        startActivity(intent);
    }

    //专业调理方案详情
    protected void startTestPlanProfessionFragment(String test_id, String type) {
        Intent intent = getFragmentIntent(Constants.TEST_PLAN_PROFESSION);
        intent.putExtra(Constants.TEST_ID, test_id);
        intent.putExtra(Constants.TYPE, type);
        startActivity(intent);
    }

    //调理方案
    protected void startTestResultTabPlanFragment(String test_id) {
        Intent intent = getFragmentIntent(Constants.TEST_RESULT_TAB_PLAN);
        intent.putExtra(Constants.TEST_ID, test_id);
        startActivity(intent);
    }

    //调理方案_专业
    protected void startTestResultTabPlanProfessionFragment(String test_id) {
        Intent intent = getFragmentIntent(Constants.TEST_RESULT_TAB_PLAN_PROFESSION);
        intent.putExtra(Constants.TEST_ID, test_id);
        startActivity(intent);
    }


    //电子病历_所有的
    protected void startMedicalRecordsFragment() {
        Intent intent = getFragmentIntent(Constants.MEDICAL_RECORD);
        startActivity(intent);
    }

    //电子病历列表  环信
    protected void startMedicalRecordsHxFragment() {
        Intent intent = getFragmentIntent(Constants.MEDICAL_RECORDS_HX_FRAGMENT);
        startActivity(intent);
    }


    //电子病历_每月的
    protected void startMedicalRecordsMonthFragment() {
        Intent intent = getFragmentIntent(Constants.MEDICAL_RECORD_MONTH);
        startActivity(intent);
    }

    //电子病历详情
    protected void startMedicalRecordsDetailFragment(MedicalListBean medicalListBean, int position) {
        Intent intent = getFragmentIntent(Constants.MEDICAL_RECORD_DETAIL);
        intent.putExtra("medicalListBean", medicalListBean);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    //健康数据
    protected void startHealthDataFragment() {
        Intent intent = getFragmentIntent(Constants.HEALTH_DATA);
        startActivity(intent);
    }


    //健康数据详情
    protected void startHealthDataDetailFragment() {
        Intent intent = getFragmentIntent(Constants.HEALTH_DATA_DETAIL);
        startActivity(intent);
    }

    //搜索定位城市
    protected void startSearchCityFragment() {
        Intent intent = getFragmentIntent(Constants.SEARCH_CITY);
        startActivityForResult(intent, Constants.SEARCH_CITY);
    }

    //返回选择城市
    protected void BlackCity(String city) {
        Intent intent = new Intent();
        intent.putExtra(Constants.CITY, city);
        getActivity().setResult(RESULT_OK, intent);
    }


    //社区
    protected void showCommunityFragment() {
        Intent intent = getFragmentIntent(Constants.COMMUNITY);
        startActivity(intent);
    }

    //群聊
    protected void showGroupFragment() {
        Intent intent = getFragmentIntent(Constants.GROUP);
        startActivity(intent);
    }

    //设置
    protected void startSettingFragment() {
        Intent intent = getFragmentIntent(Constants.SZ);
        startActivity(intent);
    }

    //个人资料
    protected void startPersonalnfoFragment() {
        Intent intent = getFragmentIntent(Constants.PERSONAL_INFO);
        startActivity(intent);
    }

    //关注
    protected void startFocusFragment() {
        Intent intent = getFragmentIntent(Constants.FOCUS);
        startActivity(intent);
    }

    //粉丝
    protected void startFansFragment() {
        Intent intent = getFragmentIntent(Constants.FANS);
        startActivity(intent);
    }

    //收藏
    protected void startMyCollectionFragment() {
        Intent intent = getFragmentIntent(Constants.MY_COLLECTION);
        startActivity(intent);
    }

    //健康阶段
    protected void startHealthStepFragment(String health_record_id, String doctor_id) {
        Intent intent = getFragmentIntent(Constants.HEALTH_STEP);
        intent.putExtra("health_record_id", health_record_id);
        intent.putExtra("doctor_id", doctor_id);
        startActivity(intent);
    }

    //环信健康阶段
    protected void startHuanxinHealthStepFragment(String health_record_id, String doctor_id) {
        Intent intent = getFragmentIntent(Constants.HUANXIN_HEALTH_STEP_FRAGMENT);
        intent.putExtra("health_record_id", health_record_id);
        intent.putExtra("doctor_id", doctor_id);
        startActivity(intent);
    }

    //健康阶段详情
    protected void startHealthStepDetailFragment(String health_plan_id) {
        Intent intent = getFragmentIntent(Constants.HEALTH_STEP_DETAIL);
        intent.putExtra("health_plan_id", health_plan_id);
        startActivity(intent);
    }

    //新建档案
    protected void startAddFileFragment() {
        Intent intent = getFragmentIntent(Constants.ADD_FILE);
        startActivity(intent);
    }

    //修改档案
    protected void startUpdateFileFragment(String health_record_id) {
        Intent intent = getFragmentIntent(Constants.UPDATE_FILE_FRAGMENT);
        intent.putExtra("health_record_id", health_record_id);
        startActivity(intent);
    }

    //我的咨询
    protected void startMyAdvisoryFragment() {
        Intent intent = getFragmentIntent(Constants.MY_ACDVISORY);
        startActivity(intent);
    }

    //历史咨询
    protected void startHistoryAdvisoryFragment() {
        Intent intent = getFragmentIntent(Constants.History_Advisory_Fragment);
        startActivity(intent);
    }


    //帖子列表
    protected void startCommunityListFragment() {
        Intent intent = getFragmentIntent(Constants.COMMUNTITY_LIST);
        startActivity(intent);
    }

    //帖子列表
    protected void startCommunityListFragment(String plateid,String title) {
        Intent intent = getFragmentIntent(Constants.COMMUNTITY_LIST);
        intent.putExtra(Constants.PLATE_ID, plateid);
        intent.putExtra("name",title);
        startActivity(intent);
    }

    //体质诊断
    protected void startTestPhysiqueFragment(String health_record_id, String step, String result) {
        Intent intent = getFragmentIntent(Constants.TEST_PHYSIQUE);
        intent.putExtra(HEALTH_RECORD_ID, health_record_id);
        intent.putExtra(Constants.STEP, step);
        intent.putExtra(Constants.RESULT, result);
        startActivity(intent);
    }


    //通知
    protected void startMsgFragment() {
        Intent intent = getFragmentIntent(Constants.MSG);
        startActivity(intent);
    }

    //主页
    protected void startPersonalHomePageFragment() {
        Intent intent = getFragmentIntent(Constants.PERSONAL_HOME_PAGE);
        startActivity(intent);
    }

    //实名认证支付宝
    protected void startVerifiedFragment(String health_record_id) {
        Intent intent = getFragmentIntent(Constants.VERIFIED);
        intent.putExtra("health_record_id", health_record_id);
        startActivity(intent);
    }

    //实名认证成功
    protected void startVerifiedSuccessFragment() {
        Intent intent = getFragmentIntent(Constants.VERIFIED_SUCCESS);
        startActivity(intent);
    }

    //实名认证传照片
    protected void startVerifiedImgsFragment(String health_record_id) {
        Intent intent = getFragmentIntent(Constants.VERIFIED_IMG);
        intent.putExtra("health_record_id", health_record_id);
        startActivity(intent);
    }

    //合作加盟
    protected void startJoinFragment() {
        Intent intent = getFragmentIntent(Constants.JOIN);
        startActivity(intent);
    }

    protected void startJoinShopFragment(String htmlurl) {
        Intent intent = getFragmentIntent(Constants.JOIN_SHOP);
        intent.putExtra(Constants.TYPE,htmlurl);
        startActivity(intent);
    }

    protected void startJoinDoctorFragment(String htmlurl) {
        Intent intent = getFragmentIntent(Constants.JOIN_DOCTOR);
        intent.putExtra(Constants.TYPE,htmlurl);
        startActivity(intent);
    }

    protected void startJoinHouseFragment(String htmlurl) {
        Intent intent = getFragmentIntent(Constants.JOIN_HOUSE);
        intent.putExtra(Constants.TYPE,htmlurl);
        startActivity(intent);
    }

    //医生档案
    protected void startDoctorFileFragment() {
        Intent intent = getFragmentIntent(Constants.DOCTORFILE_FRAGMENT);
        startActivity(intent);
    }

    //图问咨询服务设置
    protected void startImageEditFragment(String service_price, String doctor_id) {
        Intent intent = getFragmentIntent(Constants.IMGAGEEDIT_FRAGMENT);
        intent.putExtra(Constants.STATE, doctor_id);
        intent.putExtra(Constants.STATE2, service_price);

        startActivity(intent);
    }

    //语音咨询服务设置
    protected void startPhoneEditFragment(String service_price, String doctor_id) {
        Intent intent = getFragmentIntent(Constants.PHONEEEDIT_FRAGMENT);
        intent.putExtra(Constants.STATE, doctor_id);
        intent.putExtra(Constants.STATE2, service_price);
        startActivity(intent);
    }

    //视频咨询服务设置
    protected void startVideoEditFragment(String service_price, String doctor_id) {
        Intent intent = getFragmentIntent(Constants.VIDEOEEDIT_FRAGMENT);
        intent.putExtra(Constants.STATE, doctor_id);
        intent.putExtra(Constants.STATE2, service_price);
        startActivity(intent);
    }


    //时段设置
    protected void startTimeSettingFragment(String state, String state2) {
        Intent intent = getFragmentIntent(Constants.TIMESETTING_FRAGMENT);
        intent.putExtra(Constants.STATE, state);
        intent.putExtra(Constants.STATE2, state2);
        startActivity(intent);
    }

    //时段设置
    protected void startTimeSetting2Fragment(String state, String state2) {
        Intent intent = getFragmentIntent(Constants.TIMESETTING_FRAGMENT2);
        intent.putExtra(Constants.STATE, state);
        intent.putExtra(Constants.STATE2, state2);
        startActivity(intent);
    }

    //服务类型
    protected void startServiceTypeFragment(String state, String state2) {
        Intent intent = getFragmentIntent(Constants.SERVICETYPE_FRAGMENT);
        intent.putExtra(Constants.STATE, state);
        intent.putExtra(Constants.STATE2, state2);
        startActivity(intent);
    }

    //类型设置
    protected void startServiceTypeSettingFragment(HealthManagerBeans state, String state2) {
        Intent intent = getFragmentIntent(Constants.SERVICETYPESETTING_FRAGMENT);
        intent.putExtra(Constants.STATE, state);
        intent.putExtra(Constants.STATE2, state2);
        startActivity(intent);
    }

    //医生个人资料
    protected void startDoctorInfoFragment() {
        Intent intent = getFragmentIntent(Constants.DOCTORINFO_FRAGMENT);
        startActivity(intent);
    }

    //健康用户
    protected void startHealthUserFragment() {
        Intent intent = getFragmentIntent(Constants.HEALTHUSERLIST_FRAGMENT);
        startActivity(intent);
    }

    //健康用户详情
    protected void startHealthUserDetailFragment(String state) {
        Intent intent = getFragmentIntent(Constants.HEALTHUSERDETAIL_FRAGMENT);
        intent.putExtra(Constants.STATE, state);
        startActivity(intent);
    }

    //健康用户详情
    protected void startDoctorConsultationFragment() {
        Intent intent = getFragmentIntent(Constants.DOCTORCONSULTATION_FRAGMENT);
        startActivity(intent);
    }

    //医生入驻成功
    protected void startJoindoctorSuccessFragment(String type) {
        Intent intent = getFragmentIntent(Constants.JOINDOCTORSUCCESS_FRAGMENT);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    //购买语音／视频
    protected void startBuyConsultVideoFragment(String Type, String Price, String doctorName, ConsultTimesBean consultTimesBean) {
        Intent intent = getFragmentIntent(Constants.BUYCONSULTVIDEO_FRAGMENT);
        intent.putExtra("Type", Type);
        intent.putExtra("Price", Price);
        intent.putExtra("doctorName", doctorName);
        intent.putExtra("consultTimesBean", consultTimesBean);

        startActivity(intent);
    }

    //购买图文咨询
    protected void startBuyConsultImageFragment(String orderid, String doctorName, String Hx_account,String price,List<String> list,String desc) {
        Intent intent = getFragmentIntent(Constants.BUYCONSULTIMAGE_FRAGMENT);
        intent.putExtra("doctorName", doctorName);
        intent.putExtra("Hx_account", Hx_account);
        intent.putExtra("order_id",orderid);
        intent.putExtra("price",price);
        intent.putExtra("list", (Serializable) list);
        intent.putExtra("desc",desc);
        startActivity(intent);
    }

    //购买健康管理
    protected void startBuyConsultHealthFragment(String health_record_id, HealthManagerBeans healthManagerBeans) {
        Intent intent = getFragmentIntent(Constants.BUYCONSULTHEALTH_FRAGMENT);
        intent.putExtra(Constants.HEALTH_RECORD_ID, health_record_id);
        intent.putExtra("healthManagerBeans", healthManagerBeans);
        startActivity(intent);
    }

    //送心意
    protected void startGiveHeartFragment(String doctor_name, String doctor_id, String record_desc, String order_actual_price) {
        Intent intent = getFragmentIntent(Constants.GIVE_HEART_FRAGMENT);
        intent.putExtra("doctor_name", doctor_name);
        intent.putExtra("doctor_id", doctor_id);
        intent.putExtra("record_desc", record_desc);
        intent.putExtra("order_actual_price", order_actual_price);

        startActivity(intent);
    }


    //购买调理方案
    protected void startBuyPlanFragment(String health_record_id, String test_id, String order_actual_price) {
        Intent intent = getFragmentIntent(Constants.BUY_PLAN_FRAGMENT);
        intent.putExtra("health_record_id", health_record_id);
        intent.putExtra("test_id", test_id);
        intent.putExtra("order_actual_price", order_actual_price);

        startActivityForResult(intent, Constants.INTENT_PAY_SUCCESS);
    }

    //购买会员
    protected void startBuyVipFragment(String associator_id, String associator_price, String associator_title) {
        Intent intent = getFragmentIntent(Constants.BUY_VIP_FRAGMENT);
        intent.putExtra("associator_id", associator_id);
        intent.putExtra("associator_price", associator_price);
        intent.putExtra("associator_title", associator_title);
        startActivity(intent);
    }


    //修改登录密码
    protected void startUpdateLoginPwdFragment() {
        Intent intent = getFragmentIntent(Constants.UPDATELOGINPWD_FRAGMENT);
        startActivity(intent);
    }

    //修改支付密码
    protected void startUpdatePayPwdFragment(String type) {
        Intent intent = getFragmentIntent(Constants.UPDATEPAYPWD_FRAGMENT);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    //账号安全
    protected void startAccountSecurityFragment() {
        Intent intent = getFragmentIntent(Constants.ACCOUNT_SECURITY_FRAGMENT);
        startActivity(intent);
    }

    //上传病历
    protected void startUploadCaseFragment() {
        Intent intent = getFragmentIntent(Constants.UPLOAD_CASE_FRAGMENT);
        startActivity(intent);
    }

    //我的账户
    protected void startMyAccountFragment() {
        Intent intent = getFragmentIntent(Constants.MY_ACCOUNT_FRAGMENT);
        startActivity(intent);
    }

    //充值
    protected void startChongzhiFragment() {
        Intent intent = getFragmentIntent(Constants.CHONGZHI_FRAGMENT);
        startActivity(intent);
    }

    //明细
    protected void startDetailFragment() {
        Intent intent = getFragmentIntent(Constants.DETAIL_FRAGMENT);
        startActivity(intent);
    }

    //我的收益
    protected void startMyProfitFragment() {
        Intent intent = getFragmentIntent(Constants.MY_PROFIT_FRAGMENT);
        startActivity(intent);
    }

    //我的订单
    protected void startMyOrderFragment(String type, String page) {
        Intent intent = getFragmentIntent(Constants.MY_ORDER_FRAGMENT);
        intent.putExtra("type", type);
        intent.putExtra("page", page);
        startActivity(intent);
    }


    //我的医生
    protected void startMyDoctorFragment() {
        Intent intent = getFragmentIntent(Constants.MY_DOCTOR_FRAGMENT);
        startActivity(intent);
    }

    //我的会员
    protected void startMyVipragment() {
        Intent intent = getFragmentIntent(Constants.MY_VIP_FRAGMENT);
        startActivity(intent);
    }


    //语音和视频的拨打界面
    protected void startVoiceVideoCallFragment(ConsultBean consultBean, String role) {
        Intent intent = getFragmentIntent(Constants.VOICE_VIDEO_CALLFRAGMENT);
        intent.putExtra("ConsultBean", consultBean);
        intent.putExtra("role", role);
        startActivity(intent);
    }


    //消息提醒
    protected void startMessageRemindFragment() {
        Intent intent = getFragmentIntent(Constants.Message_Remind_Fragment);
        startActivity(intent);
    }


    //地址管理
    protected void startAddressFragment(String type) {
        Intent intent = getFragmentIntent(Constants.ADDRESS_FRAGMENT);
        intent.putExtra("type", type);
        startActivityForResult(intent, Constants.REQUESTCODE);
    }


    //会员详情
    protected void startVipDatailFragment(AssociatorBean associatorBean) {
        Intent intent = getFragmentIntent(Constants.VIP_DETAIL_FRAGMENT);
        intent.putExtra("AssociatorBean", associatorBean);
        startActivity(intent);
    }


    //编辑个人介绍
    protected void startEditPersonalInfoFragment(String doctor_id, String doctor_introduce, String doctor_background, String doctor_winning, String doctor_remarks) {
        Intent intent = getFragmentIntent(Constants.EDIT_PERSONALINFO_FRAGMENT);
        intent.putExtra("doctor_id", doctor_id);
        intent.putExtra("doctor_introduce", doctor_introduce);
        intent.putExtra("doctor_background", doctor_background);
        intent.putExtra("doctor_winning", doctor_winning);
        intent.putExtra("doctor_remarks", doctor_remarks);
        startActivity(intent);
    }


    //分类商品列表
    protected void startClassGoosListFragment(String class_uuid) {
        Intent intent = getFragmentIntent(Constants.CLASS_GOODS_LIST_FRAGMENT);
        intent.putExtra("class_uuid", class_uuid);
        startActivity(intent);
    }


    //所有分类
    protected void startAllClassFragment() {
        Intent intent = getFragmentIntent(Constants.ALL_CLASS_FRAGMENT);
        startActivity(intent);
    }

    //忘记密码
    protected void startForgetLoginPwdFragment() {
        Intent intent = getFragmentIntent(Constants.FORGETLOGINPWD_FRAGMENT);
        startActivity(intent);
    }

    //忘记密码下一步
    protected void startForgetLoginPwdNextFragment(String phone, String code) {
        Intent intent = getFragmentIntent(Constants.FORGETLOGINPWD_NEXT_FRAGMENT);
        intent.putExtra("phone", phone);
        intent.putExtra("code", code);
        startActivity(intent);
    }

    //医生个人介绍
    protected void startDoctorPersonalInfoFragment(DoctorDetailBean doctorDetailBean) {
        Intent intent = getFragmentIntent(Constants.DOCTOR_PERSONAL_INFO_FRAGMENT);
        intent.putExtra("doctorDetailBean", doctorDetailBean);

        startActivity(intent);
    }


    //提现
    protected void startTixianFragment(String type) {
        Intent intent = getFragmentIntent(Constants.TIXIAN_FRAGMENT);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    //绑定账户列表
    protected void startBindAccountFragment() {
        Intent intent = getFragmentIntent(Constants.BIND_ACCOUNT_FRAGMENT);
        startActivity(intent);
    }

    //绑定支付宝
    protected void startBindAlipayFragment() {
        Intent intent = getFragmentIntent(Constants.BIND_ALIPAY_FRAGMENT);
        startActivity(intent);
    }

    //绑定微信
    protected void startBindWxFragment() {
        Intent intent = getFragmentIntent(Constants.BIND_WX_FRAGMENT);
        startActivity(intent);
    }

    //验证手机号
    protected void startVerifyPhoneFragment(String bind_type) {
        Intent intent = getFragmentIntent(Constants.VERIFY_PHONE_FRAGMENT);
        intent.putExtra("bind_type", bind_type);
        startActivity(intent);
    }

    //提交订单验证手机号
    protected void startConfirmOrderVerifyPhoneFragment(String phone) {
        Intent intent = getFragmentIntent(Constants.CONFIEM_ORDER_VERIFYPHONE_FRAGMENT);
        intent.putExtra("phone", phone);
        startActivityForResult(intent, Constants.REQUESTCODE);
    }


    //商品详情
    protected void startGoodDetailFragment(String goods_id) {
        Intent intent = getFragmentIntent(Constants.GOOD_DETAIL_FRAGMENT);
        intent.putExtra("goods_id", goods_id);
        startActivity(intent);
    }
    //帖子详情
    protected void startPlateDetailFragment(String plate) {
        Intent intent = getFragmentIntent(Constants.COMMUNITYDETAILS);
        intent.putExtra(Constants.PLATE_ID, plate);
        startActivity(intent);
    }
    //服务商品详情
    protected void startServiceGoodDetailFragment(String goods_id) {
        Intent intent = getFragmentIntent(Constants.SERVICE_GOOD_DETAIL_FRAGMENT);
        intent.putExtra("goods_id", goods_id);
        startActivity(intent);
    }

    //提交服务订单
    protected void startConfirmServiceGoodOrderFragment(GoodsBean goodsBean) {
        Gson gson=new Gson();
        Intent intent = getFragmentIntent(Constants.CONFIRM_SERVICEGOODORDER_FRAGMENT);
        intent.putExtra("goodsBean", gson.toJson(goodsBean));
        startActivity(intent);
    }


    //购物车
    protected void startCartFragment() {
        Intent intent = getFragmentIntent(Constants.CART_FRAGMENT);
        startActivity(intent);
    }

    //商品订单详情
    protected void startGoodOrderDetailFragment(String order_id) {
        Intent intent = getFragmentIntent(Constants.GOOD_ORDER_DETAIL_FRAGMENT);
        intent.putExtra("order_id", order_id);
        startActivity(intent);
    }

    //服务商品订单详情
    protected void startServiceGoodOrderDetailFragment(String order_id) {
        Intent intent = getFragmentIntent(Constants.SERVICE_GOODORDER_DETAILFRAGMENT);
        intent.putExtra("order_id", order_id);
        startActivity(intent);
    }


    //编辑过敏史
    protected void startEditAllergyContentFragment(String content, String health_record_id) {
        Intent intent = getFragmentIntent(Constants.EDIT_ALLERGY_CONTENT_FRAGMENT);
        intent.putExtra("content", content);
        intent.putExtra("health_record_id", health_record_id);
        startActivityForResult(intent, 1);
    }

    //添加方案
    protected void startAddPlanFragment(String health_record_id) {
        Intent intent = getFragmentIntent(Constants.ADD_PLAN_FRAGMENT);
        intent.putExtra("health_record_id", health_record_id);
//        intent.putExtra("healthPlanBeansBean",healthPlanBeansBean);
        startActivityForResult(intent, 1);
    }

    //添加方案
    protected void startSendHealthFileFragment(String health_record_id) {
        Intent intent = getFragmentIntent(Constants.SEND_HEALTH_FILE_FRAGMENT);
        intent.putExtra("health_record_id", health_record_id);
        startActivityForResult(intent, Constants.INTENT_HEALTH_FILE);
    }


    //意见反馈
    protected void startFanhuiFragment() {
        Intent intent = getFragmentIntent(Constants.FANKUI_FRAGMENT);
        startActivity(intent);
    }

    //评价医生
    protected void startAssessmentDoctorFragment(String doctor_id) {
        Intent intent = getFragmentIntent(Constants.ASSESSMENT_DOCTOR_FRAGMENT);
        intent.putExtra("doctor_id", doctor_id);
        startActivity(intent);
    }

    //提交订单
    protected void startConfirmGoodOrderFragment(String type, List<CartBean> cartBeanList) {
        Intent intent = getFragmentIntent(Constants.CONFIRM_GOODORDER_FRAGMENT);
        intent.putExtra("type", type);
        intent.putParcelableArrayListExtra("cartBeanList", (ArrayList<? extends Parcelable>) cartBeanList);
        startActivity(intent);
    }

    //添加地址
    protected void startAddAddressFragment(String type, AddressBean addressBean) {
        Intent intent = getFragmentIntent(Constants.ADD_ADDRESS_FRAGMENT);
        intent.putExtra("type", type);
        intent.putExtra("addressBean", addressBean);
        startActivityForResult(intent, Constants.REQUESTCODE);
    }
    //添加地址(家政)
    protected void  ServiceAddAddressFragment(String serviceId) {
        Intent intent = getFragmentIntent(Constants.ADDADDRESS);
        intent.putExtra("house_service_id",serviceId);
        startActivity(intent);
    }

    //支付商品和家政服务
    protected void startBuyGoodFragment(String order_ids, String order_price) {
        Intent intent = getFragmentIntent(Constants.BUY_GOOD_FRAGMENT);
        intent.putExtra("order_ids", order_ids);
        intent.putExtra("order_price", order_price);
        startActivity(intent);
    }
    //支付商品和家政服务
    protected void startBuyGoodFragment(String order_ids, String order_price,String type) {
        Intent intent = getFragmentIntent(Constants.BUY_GOOD_FRAGMENT);
        intent.putExtra("order_ids", order_ids);
        intent.putExtra("order_price", order_price);
        intent.putExtra(Constants.TYPE,type);
        startActivity(intent);
    }

    //我的直播列表
    protected void startMyLiveListFragment() {
        Intent intent = getFragmentIntent(Constants.MY_LIVELIST_FRAGMENT);
        startActivity(intent);
    }

    //评价
    protected void startAssessmentOrderFragment(List<OrderGoodsBeans> orderGoodsBeanses, String order_id) {
        Intent intent = getFragmentIntent(Constants.ASSESSMENT_ORDER_FRAGMENT);
        intent.putExtra("order_id", order_id);
        intent.putParcelableArrayListExtra("orderGoodsBeanses", (ArrayList<? extends Parcelable>) orderGoodsBeanses);
        startActivity(intent);
    }


    //售后
    protected void startApplyAfterSaleFragment(OrderGoodsBeans orderGoodsBeans) {
        Intent intent = getFragmentIntent(Constants.APPLY_AFTER_SALE_FRAGMENT);
        intent.putExtra("orderGoodsBeans", orderGoodsBeans);
        startActivity(intent);
    }

    //售后列表
    protected void startAfterSaleOrderListFragment() {
        Intent intent = getFragmentIntent(Constants.AFTER_SALE_ORDER_LIST_FRAGMENT);
        startActivity(intent);
    }


    //售后列表
    protected void startAfterSaleOrderDetailFragment(String refund_id) {
        Intent intent = getFragmentIntent(Constants.AFTER_SALE_ORDER_DETAIL_FRAGMENT);
        intent.putExtra("refund_id", refund_id);
        startActivity(intent);
    }

    //更多评价
    protected void startMoreAssessmentListFragment(String goods_id) {
        Intent intent = getFragmentIntent(Constants.MORE_ASSESSMENT_LIST_FRAGMENT);
        intent.putExtra("goods_id", goods_id);
        startActivity(intent);
    }

    //商家详情
    protected void startMerchantsDetailFragment(String merchants_id) {
        Intent intent = getFragmentIntent(Constants.Merchants_Detail_Fragment);
        intent.putExtra("merchants_id", merchants_id);
        startActivity(intent);
    }

    //名医直播列表
    protected void startFamousDoctorLiveListFragment() {
        Intent intent = getFragmentIntent(Constants.Famous_Doctor_LiveList_Fragment);
        startActivity(intent);
    }

    //更多附近门店
    protected void startMoreNearbyShopListFragment(String city,double lat,double log) {
        Intent intent = getFragmentIntent(Constants.MORE_NEARBY_SHOPLIST_FRAGMENT);
        intent.putExtra(Constants.LOCALITY,city);
        intent.putExtra(Constants.LATITUDE,lat);
        intent.putExtra(Constants.LONGITUDE,log);
        startActivity(intent);
    }

    //更多热卖商品
    protected void startHotGoodsListFragment() {
        Intent intent = getFragmentIntent(Constants.HOT_GOODSLIST_FRAGMENT);
        startActivity(intent);
    }

    //开始直播
    protected void startStartLiveFragment() {
        Intent intent = getFragmentIntent(Constants.Start_Live_Fragment);
        startActivity(intent);
    }

    //门店详情
    protected void startMerchantsMapFragment(PoiItem poiItem) {


        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.POIITEM, poiItem);
        Intent intent = getFragmentIntent(Constants.MERCHANTS_MAP_FRAGMENT);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //搜索
    protected void startSearchFragment(String search_content) {
        Intent intent = getFragmentIntent(Constants.SEARCH_FRAGMENT);
        intent.putExtra("search_content", search_content);
        startActivity(intent);
    }

    //搜索历史
    protected void startSearchHistoryFragment() {
        Intent intent = getFragmentIntent(Constants.SEARCH_HISTORY_FRAGMENT);
        startActivity(intent);
    }

    //搜索更多医生
    protected void startMoreSearchDoctorFragment(String keyWord) {
        Intent intent = getFragmentIntent(Constants.MORESEARCH_DOCTOR_FRAGMENT);
        intent.putExtra("keyWord", keyWord);
        startActivity(intent);
    }

    //搜索更多商家
    protected void startMoreSearchMerchantsFragment(String keyWord) {
        Intent intent = getFragmentIntent(Constants.MORESEARCH_MERCHANTS_FRAGMENT);
        intent.putExtra("keyWord", keyWord);
        startActivity(intent);
    }

    //搜索更多医生
    protected void startMoreSearchGoodFragment(String keyWord) {
        Intent intent = getFragmentIntent(Constants.MORESEARCH_GOOD_FRAGMENT);
        intent.putExtra("keyWord", keyWord);
        startActivity(intent);
    }

    //家政服务
    protected void startHouseKeepingFragment() {
        Intent intent = getFragmentIntent(Constants.HOUSEKEEPING_FRAGMENT);
        startActivity(intent);
    }

    //家政服务详情
    protected void startHouseKeepingDetailFragment(String housekeep_id) {
        Intent intent = getFragmentIntent(Constants.HOUSEKEEPING_DETAIL_FRAGMENT);
        intent.putExtra("housekeep_id", housekeep_id);
        startActivity(intent);
    }

    //环信 电子病历
    protected void startMedicalRecordsDetailHxFragment(String medical_id, int position) {
        Intent intent = getFragmentIntent(Constants.MEDICAL_RECORDS_DETAIL_HX_FRAGMENT);
        intent.putExtra("medical_id", medical_id);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    //发布家政信息
    protected void startReleaseHouseKeepFragment() {
        Intent intent = getFragmentIntent(Constants.RELEASE_HOUSEKEEP_FRAGMENT);
        startActivity(intent);
    }


    //--------------------------------


    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {

        InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        v.clearFocus();
    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    public abstract int getRootViewId();

    public abstract void initUI();

    public abstract void initData();


    //保存个人信息
    protected void saveUser(UserBean userBean) {
        // 登录成功
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, userBean);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
        SPUtils.saveString(getActivity(), "phone", userBean.getMember_account());
        SPUtils.saveString(getActivity(), "head_image", userBean.getMember_head_image());
        SPUtils.saveString(getActivity(), "nick_name", userBean.getMember_nick_name());
        //CallUnit.reCall();
    }

    //退出登陆返回登陆页面
    protected void exitLogin() {

        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, null);
        UserBean userBean = SPUtils.getObj1(getContext(), Constants.USER_BEAN);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
        EMClient.getInstance().logout(true);

        startLogin();
        // startActivity(new Intent(getActivity(), MainActivity.class));
        finish();

    }

}
