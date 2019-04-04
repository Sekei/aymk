package com.live.tv.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.amap.api.services.core.PoiItem;
import com.google.gson.Gson;
import com.king.base.util.LogUtils;
import com.live.tv.Constants;
import com.live.tv.bean.AddressBean;
import com.live.tv.bean.AssociatorBean;
import com.live.tv.bean.CartBean;
import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.FirstEvent;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.bean.Note;
import com.live.tv.bean.OrderGoodsBeans;
import com.live.tv.mvp.activity.live.StartLiveFragment;
import com.live.tv.mvp.fragment.LoginFragment;
import com.live.tv.mvp.fragment.RegisteredFragment;
import com.live.tv.mvp.fragment.SearchCityFragment;
import com.live.tv.mvp.fragment.WebFragment;
import com.live.tv.mvp.fragment.communicate.CommunicateFragment;
import com.live.tv.mvp.fragment.communicate.CommunityDetailsFragment;
import com.live.tv.mvp.fragment.communicate.CommunityListFragment;
import com.live.tv.mvp.fragment.communicate.FragmentPostDetails;
import com.live.tv.mvp.fragment.communicate.PostedFragment;
import com.live.tv.mvp.fragment.home.AddFileFragment;
import com.live.tv.mvp.fragment.home.ChooseTimeFragment;
import com.live.tv.mvp.fragment.home.CommonTestFragment;
import com.live.tv.mvp.fragment.home.ComplaintFragment;
import com.live.tv.mvp.fragment.home.DoctorDetailFragment;
import com.live.tv.mvp.fragment.home.DoctorListFragment;
import com.live.tv.mvp.fragment.home.DoctorPersonalInfoFragment;
import com.live.tv.mvp.fragment.home.FileMoreFragment;
import com.live.tv.mvp.fragment.home.HealthDataDetailFragment;
import com.live.tv.mvp.fragment.home.HealthDataFragment;
import com.live.tv.mvp.fragment.home.HealthFileFragment;
import com.live.tv.mvp.fragment.home.HealthManageFragment;
import com.live.tv.mvp.fragment.home.HealthStepDetailFragment;
import com.live.tv.mvp.fragment.home.HealthStepFragment;
import com.live.tv.mvp.fragment.home.HistoryAdvisoryFragment;
import com.live.tv.mvp.fragment.home.HousekeepDetailFragment;
import com.live.tv.mvp.fragment.home.HousekeepingFragment;
import com.live.tv.mvp.fragment.home.MedicalRecordsDetailFragment;
import com.live.tv.mvp.fragment.home.MedicalRecordsDetailHxFragment;
import com.live.tv.mvp.fragment.home.MedicalRecordsFragment;
import com.live.tv.mvp.fragment.home.MedicalRecordsHxFragment;
import com.live.tv.mvp.fragment.home.MedicalRecordsMonthFragment;
import com.live.tv.mvp.fragment.home.MerchantMessageFragment;
import com.live.tv.mvp.fragment.home.MerchantMoreAddressFragment;
import com.live.tv.mvp.fragment.home.MerchantsMapFragment;
import com.live.tv.mvp.fragment.home.MoreConsultantFragment;
import com.live.tv.mvp.fragment.home.MoreNearbyShopListFragment;
import com.live.tv.mvp.fragment.home.MoreSearchDoctorFragment;
import com.live.tv.mvp.fragment.home.MoreSearchGoodFragment;
import com.live.tv.mvp.fragment.home.MoreSearchMerchantsFragment;
import com.live.tv.mvp.fragment.home.MsgFragment;
import com.live.tv.mvp.fragment.home.MyAdvisoryFragment;
import com.live.tv.mvp.fragment.home.PayServerFragment;
import com.live.tv.mvp.fragment.home.PaySuccessFragment;
import com.live.tv.mvp.fragment.home.ProfessionalTestFragment;
import com.live.tv.mvp.fragment.home.ReleaseHouseKeepFragment;
import com.live.tv.mvp.fragment.home.RequestOneFragment;
import com.live.tv.mvp.fragment.home.SearchFragment;
import com.live.tv.mvp.fragment.home.SearchHistoryFragment;
import com.live.tv.mvp.fragment.home.SendHeartFragment;
import com.live.tv.mvp.fragment.home.ServiceAddAddressFragment;
import com.live.tv.mvp.fragment.home.ServiceMoreAssessmentListFragment;
import com.live.tv.mvp.fragment.home.TestCommonResultFragment;
import com.live.tv.mvp.fragment.home.TestCompleteFragment;
import com.live.tv.mvp.fragment.home.TestDataHuanxinFragment;
import com.live.tv.mvp.fragment.home.TestDescriptionFragment;
import com.live.tv.mvp.fragment.home.TestDiagnoseReportFragment;
import com.live.tv.mvp.fragment.home.TestFragment;
import com.live.tv.mvp.fragment.home.TestHistoryFragment;
import com.live.tv.mvp.fragment.home.TestHomePageFragment;
import com.live.tv.mvp.fragment.home.TestPhysiqueFragment;
import com.live.tv.mvp.fragment.home.TestPlanProfessionFragment;
import com.live.tv.mvp.fragment.home.TestResultTabFragment;
import com.live.tv.mvp.fragment.home.TestResultTabNextFragment;
import com.live.tv.mvp.fragment.home.TestResultTabPlanFragment;
import com.live.tv.mvp.fragment.home.TestResultTabPlanProfessionFragment;
import com.live.tv.mvp.fragment.home.UpdateFileFragment;
import com.live.tv.mvp.fragment.home.UploadCaseFragment;
import com.live.tv.mvp.fragment.home.VerifiedFragment;
import com.live.tv.mvp.fragment.home.VerifiedImgFragment;
import com.live.tv.mvp.fragment.home.VerifiedSuccessFragment;
import com.live.tv.mvp.fragment.home.editAllergyContentFragment;
import com.live.tv.mvp.fragment.huanxin.HuaxinHealthStepFragment;
import com.live.tv.mvp.fragment.huanxin.SendHealthFileFragment;
import com.live.tv.mvp.fragment.huanxin.VoiceVIdeoCallFragment;
import com.live.tv.mvp.fragment.mine.AccountSecurityFragment;
import com.live.tv.mvp.fragment.mine.AddAddressFragment;
import com.live.tv.mvp.fragment.mine.AddHealthManageFragment;
import com.live.tv.mvp.fragment.mine.AddPlanFragment;
import com.live.tv.mvp.fragment.mine.AddressFragment;
import com.live.tv.mvp.fragment.mine.AssessmentDoctorFragment;
import com.live.tv.mvp.fragment.mine.BindAccountFragment;
import com.live.tv.mvp.fragment.mine.BindAliapayFragment;
import com.live.tv.mvp.fragment.mine.BindWxFragment;
import com.live.tv.mvp.fragment.mine.ChongzhiFragment;
import com.live.tv.mvp.fragment.mine.ConfiemOrderVerifyPhoneFragment;
import com.live.tv.mvp.fragment.mine.DetailFragment;
import com.live.tv.mvp.fragment.mine.DoctorConsultationFragment;
import com.live.tv.mvp.fragment.mine.DoctorFileFragment;
import com.live.tv.mvp.fragment.mine.DoctorInfoFragment;
import com.live.tv.mvp.fragment.mine.EditPersonalInfoFragment;
import com.live.tv.mvp.fragment.mine.EditServiceFragment;
import com.live.tv.mvp.fragment.mine.FamousDoctorLiveListFragment;
import com.live.tv.mvp.fragment.mine.FankuiFragment;
import com.live.tv.mvp.fragment.mine.FansFragment;
import com.live.tv.mvp.fragment.mine.FocusFragment;
import com.live.tv.mvp.fragment.mine.ForgetLoginPwdFragment;
import com.live.tv.mvp.fragment.mine.ForgetLoginPwdNextFragment;
import com.live.tv.mvp.fragment.mine.HealthUserDetailFragment;
import com.live.tv.mvp.fragment.mine.HealthUserListFragment;
import com.live.tv.mvp.fragment.mine.ImageEditFragment;
import com.live.tv.mvp.fragment.mine.JoinDoctorFragment;
import com.live.tv.mvp.fragment.mine.JoinFragment;
import com.live.tv.mvp.fragment.mine.JoinHouseFragment;
import com.live.tv.mvp.fragment.mine.JoinShopFragment;
import com.live.tv.mvp.fragment.mine.JoindoctorSuccessFragment;
import com.live.tv.mvp.fragment.mine.MerchantAboutFragment;
import com.live.tv.mvp.fragment.mine.MerchantFormFragment;
import com.live.tv.mvp.fragment.mine.MessageRemindFragment;
import com.live.tv.mvp.fragment.mine.MyAccountFragment;
import com.live.tv.mvp.fragment.mine.MyCollectionFragment;
import com.live.tv.mvp.fragment.mine.MyLiveListFragment;
import com.live.tv.mvp.fragment.mine.MyOrderFragment;
import com.live.tv.mvp.fragment.mine.MyProfitFragment;
import com.live.tv.mvp.fragment.mine.MyVipFragment;
import com.live.tv.mvp.fragment.mine.MydoctorFragment;
import com.live.tv.mvp.fragment.mine.NewMyPostedListFragment;
import com.live.tv.mvp.fragment.mine.PersonalHomePageFragment;
import com.live.tv.mvp.fragment.mine.PersonalInfoFragment;
import com.live.tv.mvp.fragment.mine.PhoneEditFragment;
import com.live.tv.mvp.fragment.mine.ServiceDetaliFragment;
import com.live.tv.mvp.fragment.mine.ServiceTypeFragment;
import com.live.tv.mvp.fragment.mine.ServiceTypeSettingFragment;
import com.live.tv.mvp.fragment.mine.ServicecopeFragment;
import com.live.tv.mvp.fragment.mine.SettingFragment;
import com.live.tv.mvp.fragment.mine.TimeSettingFragment;
import com.live.tv.mvp.fragment.mine.TimeSettingFragment2;
import com.live.tv.mvp.fragment.mine.TixianFragment;
import com.live.tv.mvp.fragment.mine.UpdateLoginPwdFragment;
import com.live.tv.mvp.fragment.mine.UpdatePayPwdFragment;
import com.live.tv.mvp.fragment.mine.VerifyPhoneFragment;
import com.live.tv.mvp.fragment.mine.VideoEditFragment;
import com.live.tv.mvp.fragment.mine.VipDetailFragment;
import com.live.tv.mvp.fragment.pay.BuyConsultHealthFragment;
import com.live.tv.mvp.fragment.pay.BuyConsultImgageFragment;
import com.live.tv.mvp.fragment.pay.BuyConsultVideoFragment;
import com.live.tv.mvp.fragment.pay.BuyGoodFragment;
import com.live.tv.mvp.fragment.pay.BuyPlanFragment;
import com.live.tv.mvp.fragment.pay.BuyVipFragment;
import com.live.tv.mvp.fragment.pay.GiveHeartFragment;
import com.live.tv.mvp.fragment.shop.AfterSaleOrderDetailFragment;
import com.live.tv.mvp.fragment.shop.AfterSaleOrderListFragment;
import com.live.tv.mvp.fragment.shop.AllClassFragment;
import com.live.tv.mvp.fragment.shop.ApplyAfterSaleFragment;
import com.live.tv.mvp.fragment.shop.AssessmentOrderFragment;
import com.live.tv.mvp.fragment.shop.CartFragment;
import com.live.tv.mvp.fragment.shop.ClassGoodsListFragment;
import com.live.tv.mvp.fragment.shop.ConfirmGoodOrderFragment;
import com.live.tv.mvp.fragment.shop.ConfirmServiceGoodOrderFragment;
import com.live.tv.mvp.fragment.shop.GoodDetailFragment;
import com.live.tv.mvp.fragment.shop.GoodOrderDetailFragment;
import com.live.tv.mvp.fragment.shop.MerchantsDetailFragment;
import com.live.tv.mvp.fragment.shop.MoreAssessmentListFragment;
import com.live.tv.mvp.fragment.shop.ServiceGoodDetailFragment;
import com.live.tv.mvp.fragment.shop.ServiceGoodOrderDetailFragment;
import com.live.tv.mvp.fragment.shop.ShopFragment;
import com.live.tv.mvp.fragment.shop.hotGoodsListFragment;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMShareAPI;
import com.ysjk.health.iemk.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.live.tv.Constants.HEALTH_RECORD_ID;
import static com.live.tv.Constants.KEY_TITLE;

//import com.umeng.message.PushAgent;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public class ContentActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);             //给状态栏设置颜色。我设置透明。            window.setStatusBarColor(Color.TRANSPARENT);            window.setNavigationBarColor(Color.TRANSPARENT);        }

        }
        setContentView(R.layout.content);
        swichFragment(getIntent());
        EventBus.getDefault().register(this);
        PushAgent.getInstance(this).onAppStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventExit(Boolean isBool) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("1")) {

        } else if (event.getMsg().equals("2")) {

        } else if (event.getMsg().equals("3")) {

        } else if (event.getMsg().equals("4")) {
            finish();
        } else if (event.getMsg().equals("5")) {
            finish();
        }
    }

    public void swichFragment(Intent intent) {

        int fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT, 0);
        String state;
        String state2;
        String doctor_id;
        String doctor_name;
        String health_record_id;
        String keyWord;
        switch (fragmentKey) {

            case Constants.WEB_FRAGMENT:
                String title = getIntent().getStringExtra(KEY_TITLE);
                String url = getIntent().getStringExtra(Constants.KEY_URL);
                String web_type = getIntent().getStringExtra(Constants.KEY_SLUG);
                replaceFragment(WebFragment.newInstance(url, title, web_type));
                break;
            case Constants.DOCTOR_LIST:
                replaceFragment(DoctorListFragment.newInstance());
                break;
            case Constants.LOGIN_FRAGMENT:
                //登录
                replaceFragment(LoginFragment.newInstance());
                break;
            case Constants.REGISTER:
                //注册
                String mtype = getIntent().getStringExtra(Constants.TYPE);
                String mtag = getIntent().getStringExtra(Constants.TAG);
                String mcode = getIntent().getStringExtra(Constants.OPENID);
                replaceFragment(RegisteredFragment.newInstance(mtype, mtag, mcode));
                break;
            case Constants.DOCTOR_DETAIL:
                doctor_id = intent.getStringExtra(Constants.DOCTOR_ID);
                replaceFragment(DoctorDetailFragment.newInstance(doctor_id));
                break;
            case Constants.TEST:
                replaceFragment(TestFragment.newInstance());
                break;
            case Constants.REQUEST_ONE:
                String doctor_id1 = intent.getStringExtra(Constants.DOCTOR_ID);
                String text_price = intent.getStringExtra(Constants.PRICE);
                doctor_name = intent.getStringExtra("doctorName");
                String Hx_account = intent.getStringExtra("Hx_account");
                replaceFragment(RequestOneFragment.newInstance(doctor_id1, doctor_name, text_price, Hx_account));
                break;
            case Constants.CHOOSE_TIME:
                String type2 = intent.getStringExtra(Constants.TYPE);
                String doctor_id2 = intent.getStringExtra(Constants.DOCTOR_ID);
                String service_price = intent.getStringExtra(Constants.PRICE);
                replaceFragment(ChooseTimeFragment.newInstance(doctor_id2, type2, service_price));
                break;
            case Constants.COMMON_TEST:
                replaceFragment(CommonTestFragment.newInstance());
                break;
            case Constants.PEROFESSIONAL_TEST:
                replaceFragment(ProfessionalTestFragment.newInstance());
                break;
            case Constants.SEND_HEART:
                String doctor_id3 = intent.getStringExtra(Constants.DOCTOR_ID);
                String sendDoctorName = intent.getStringExtra(Constants.SEND_DOCTOR_NAME);
                String member_head_image = intent.getStringExtra("member_head_image");
                replaceFragment(SendHeartFragment.newInstance(doctor_id3, sendDoctorName, member_head_image));
                break;
            case Constants.HEALTH_FILE://健康档案
                replaceFragment(HealthFileFragment.newInstance());
                break;
            case Constants.MORE_FILE:
                String type = getIntent().getStringExtra(Constants.TYPE);
                replaceFragment(FileMoreFragment.newInstance(type));
                break;
            case Constants.MORECONSULTANT_FRAGMENT://修改咨询人
                replaceFragment(MoreConsultantFragment.newInstance(""));
                break;
            case Constants.PAY_SERVER:
                String payType = getIntent().getStringExtra(Constants.PAY_TYPE);
                String price = getIntent().getStringExtra(Constants.PRICE);
                String three = getIntent().getStringExtra(Constants.THREE);
                String four = getIntent().getStringExtra(Constants.FOUR);
                String five = getIntent().getStringExtra(Constants.FIVE);
                replaceFragment(PayServerFragment.newInstance(payType, price, three, four, five));
                break;
            case Constants.PAY_SUCCESS:
                replaceFragment(PaySuccessFragment.newInstance());
                break;
            case Constants.HEALTH_MANAGE:
                doctor_id = intent.getStringExtra(Constants.DOCTOR_ID);
                doctor_name = intent.getStringExtra(Constants.DOCTOR_NAME);
                replaceFragment(HealthManageFragment.newInstance(doctor_id, doctor_name));
                break;
            case Constants.TEST_HOMEPAGE:
                replaceFragment(TestHomePageFragment.newInstance());
                break;
            case Constants.TEST_HISTORY://历史测试
                String click_type = getIntent().getStringExtra("click_type");
                replaceFragment(TestHistoryFragment.newInstance(click_type));
                break;
            case Constants.TEST_DESCRIPTION://测试说明
                replaceFragment(TestDescriptionFragment.newInstance());
                break;
            case Constants.TEST_COMPLETE:
                String health_record_id1 = getIntent().getStringExtra(HEALTH_RECORD_ID);
                replaceFragment(TestCompleteFragment.newInstance(health_record_id1));
                break;
            case Constants.TEST_DIAGNOSE_REPORT://诊断报告封面
                String health_record_id2 = getIntent().getStringExtra(HEALTH_RECORD_ID);
                replaceFragment(TestDiagnoseReportFragment.newInstance(health_record_id2));
                break;
            case Constants.TEST_RESULT_TAB:
                String test_id = getIntent().getStringExtra(Constants.TEST_ID);
                replaceFragment(TestResultTabFragment.newInstance(test_id));
                break;
            case Constants.TEST_RESULT_TAB_NEXT:
                String health_record_id4 = getIntent().getStringExtra(HEALTH_RECORD_ID);
                String type1 = getIntent().getStringExtra(Constants.TYPE);
                replaceFragment(TestResultTabNextFragment.newInstance(health_record_id4, type1));
                break;
            case Constants.TEST_RESULT_TAB_PLAN:
                String test_id1 = getIntent().getStringExtra(Constants.TEST_ID);
                replaceFragment(TestResultTabPlanFragment.newInstance(test_id1));
                break;
            case Constants.TEST_RESULT_TAB_PLAN_PROFESSION:
                String test_id2 = getIntent().getStringExtra(Constants.TEST_ID);
                replaceFragment(TestResultTabPlanProfessionFragment.newInstance(test_id2));
                break;
            case Constants.TEST_COMMON_RESULT:
                replaceFragment(TestCommonResultFragment.newInstance());
                break;
            case Constants.MEDICAL_RECORD:
                String click_type1 = getIntent().getStringExtra("click_type");
                replaceFragment(MedicalRecordsFragment.newInstance(click_type1));
                break;

            case Constants.MEDICAL_RECORDS_HX_FRAGMENT:
                replaceFragment(MedicalRecordsHxFragment.newInstance());
                break;


            case Constants.MEDICAL_RECORD_MONTH:
                replaceFragment(MedicalRecordsMonthFragment.newInstance());
                break;
            case Constants.MEDICAL_RECORD_DETAIL://病历详情

                MedicalListBean medicalListBean = getIntent().getParcelableExtra("medicalListBean");
                int position = getIntent().getIntExtra("position", 0);
                replaceFragment(MedicalRecordsDetailFragment.newInstance(medicalListBean, position));
                break;
            case Constants.HEALTH_DATA:
                replaceFragment(HealthDataFragment.newInstance());
                break;

            case Constants.HEALTH_DATA_DETAIL:
                replaceFragment(HealthDataDetailFragment.newInstance());
                break;
            case Constants.SEARCH_CITY:
                //搜索城市
                replaceFragment(SearchCityFragment.newInstance());
                break;
            case Constants.SZ:
                //设置
                replaceFragment(SettingFragment.newInstance());
                break;
            case Constants.PERSONAL_INFO:
                //个人资料
                replaceFragment(PersonalInfoFragment.newInstance());
                break;
            case Constants.FOCUS:
                //关注的人
                replaceFragment(FocusFragment.newInstance());
                break;
            case Constants.FANS:
                //个人资料
                replaceFragment(FansFragment.newInstance());
                break;
            case Constants.MY_COLLECTION:
                //收藏
                replaceFragment(MyCollectionFragment.newInstance());
                break;
            case Constants.HEALTH_STEP:
                //String health_record_id,String doctor_id
                health_record_id = getIntent().getStringExtra("health_record_id");
                doctor_id = getIntent().getStringExtra("doctor_id");
                replaceFragment(HealthStepFragment.newInstance(health_record_id, doctor_id));
                break;
            case Constants.HUANXIN_HEALTH_STEP_FRAGMENT:
                //String health_record_id,String doctor_id
                health_record_id = getIntent().getStringExtra("health_record_id");
                doctor_id = getIntent().getStringExtra("doctor_id");
                replaceFragment(HuaxinHealthStepFragment.newInstance(health_record_id, doctor_id));
                break;
            case Constants.HEALTH_STEP_DETAIL:
                String health_plan_id = getIntent().getStringExtra("health_plan_id");
                replaceFragment(HealthStepDetailFragment.newInstance(health_plan_id));
                break;
            case Constants.ADD_FILE://档案信息
                replaceFragment(AddFileFragment.newInstance());
                break;
            case Constants.UPDATE_FILE_FRAGMENT://修改档案信息

                health_record_id = getIntent().getStringExtra("health_record_id");
                replaceFragment(UpdateFileFragment.newInstance(health_record_id));
                break;
            case Constants.MY_ACDVISORY://我的咨询
                replaceFragment(MyAdvisoryFragment.newInstance());
                break;
            case Constants.History_Advisory_Fragment://历史咨询
                replaceFragment(HistoryAdvisoryFragment.newInstance());
                break;

            case Constants.COMMUNTITY_LIST:
                String plateid = getIntent().getStringExtra(Constants.PLATE_ID);
                String name = getIntent().getStringExtra("name");
                replaceFragment(CommunityListFragment.newInstance(plateid, name));
                break;
            case Constants.TEST_PHYSIQUE:
                health_record_id = getIntent().getStringExtra(HEALTH_RECORD_ID);
                String result = getIntent().getStringExtra(Constants.RESULT);
                String step = getIntent().getStringExtra(Constants.STEP);
                replaceFragment(TestPhysiqueFragment.newInstance(health_record_id, step, result));
                break;
            case Constants.TEST_PLAN_PROFESSION:
                String test_id3 = getIntent().getStringExtra(Constants.TEST_ID);
                String type3 = getIntent().getStringExtra(Constants.TYPE);
                replaceFragment(TestPlanProfessionFragment.newInstance(test_id3, type3));
                break;
            case Constants.MSG:
                replaceFragment(MsgFragment.newInstance());
                break;
            case Constants.PERSONAL_HOME_PAGE:
                replaceFragment(PersonalHomePageFragment.newInstance());
                break;

            case Constants.VERIFIED:
                health_record_id = getIntent().getStringExtra("health_record_id");
                replaceFragment(VerifiedFragment.newInstance(health_record_id));
                break;

            case Constants.VERIFIED_IMG:
                health_record_id = getIntent().getStringExtra("health_record_id");
                replaceFragment(VerifiedImgFragment.newInstance(health_record_id));
                break;

            case Constants.VERIFIED_SUCCESS:
                replaceFragment(VerifiedSuccessFragment.newInstance());
                break;
            case Constants.JOIN:
                //合作加盟
                replaceFragment(JoinFragment.newInstance());
                break;
            case Constants.JOIN_DOCTOR:
                String doctorhtmlurl = intent.getStringExtra(Constants.TYPE);
                replaceFragment(JoinDoctorFragment.newInstance(doctorhtmlurl));
                break;

            case Constants.JOIN_HOUSE:
                String househtmlurl = intent.getStringExtra(Constants.TYPE);
                replaceFragment(JoinHouseFragment.newInstance(househtmlurl));
                break;

            case Constants.JOIN_SHOP:
                String shophtmlurl = intent.getStringExtra(Constants.TYPE);
                replaceFragment(JoinShopFragment.newInstance(shophtmlurl));
                break;
            case Constants.DOCTORFILE_FRAGMENT: //医生档案
                replaceFragment(DoctorFileFragment.newInstance());
                break;
            case Constants.IMGAGEEDIT_FRAGMENT://图问咨询服务设置

                state = intent.getStringExtra(Constants.STATE);
                String servcie_price = intent.getStringExtra(Constants.STATE2);
                replaceFragment(ImageEditFragment.newInstance(servcie_price, state));
                break;
            case Constants.PHONEEEDIT_FRAGMENT://语音咨询服务设置

                state = intent.getStringExtra(Constants.STATE);
                String phone_servcie_price = intent.getStringExtra(Constants.STATE2);
                replaceFragment(PhoneEditFragment.newInstance(phone_servcie_price, state));
                break;
            case Constants.VIDEOEEDIT_FRAGMENT://视频咨询服务设置

                state = intent.getStringExtra(Constants.STATE);
                String video_servcie_price = intent.getStringExtra(Constants.STATE2);
                replaceFragment(VideoEditFragment.newInstance(video_servcie_price, state));
                break;


            case Constants.TIMESETTING_FRAGMENT://时段设置

                state = intent.getStringExtra(Constants.STATE);
                state2 = intent.getStringExtra(Constants.STATE2);
                replaceFragment(TimeSettingFragment.newInstance(state, state2));
                break;

            case Constants.TIMESETTING_FRAGMENT2://时段设置

                state = intent.getStringExtra(Constants.STATE);
                state2 = intent.getStringExtra(Constants.STATE2);
                replaceFragment(TimeSettingFragment2.newInstance(state, state2));
                break;
            case Constants.SERVICETYPE_FRAGMENT://服务类型设置

                state = intent.getStringExtra(Constants.STATE);
                state2 = intent.getStringExtra(Constants.STATE2);
                replaceFragment(ServiceTypeFragment.newInstance(state, state2));
                break;
            case Constants.SERVICETYPESETTING_FRAGMENT://服务类型设置  编辑具体内容

                HealthManagerBeans healthManagerBeans = intent.getParcelableExtra(Constants.STATE);
                state2 = intent.getStringExtra(Constants.STATE2);
                replaceFragment(ServiceTypeSettingFragment.newInstance(healthManagerBeans, state2));
                break;
            case Constants.DOCTORINFO_FRAGMENT://医生个人资料
                replaceFragment(DoctorInfoFragment.newInstance());
                break;

            case Constants.HEALTHUSERLIST_FRAGMENT://健康用户
                replaceFragment(HealthUserListFragment.newInstance());
                break;

            case Constants.HEALTHUSERDETAIL_FRAGMENT://健康用户详情
                state = intent.getStringExtra(Constants.STATE);
                replaceFragment(HealthUserDetailFragment.newInstance(state));
                break;
            case Constants.DOCTORCONSULTATION_FRAGMENT://医生 咨询列表

                replaceFragment(DoctorConsultationFragment.newInstance());
                break;
            case Constants.JOINDOCTORSUCCESS_FRAGMENT://医生 入驻成功

                String join_type = getIntent().getStringExtra("type");
                replaceFragment(JoindoctorSuccessFragment.newInstance(join_type));
                break;

            case Constants.BUYCONSULTVIDEO_FRAGMENT://购买语音／视频
                //String Type, String Price,String doctorName, ConsultTimesBean consultTimesBean
                String Type = intent.getStringExtra("Type");
                String Price = intent.getStringExtra("Price");
                String doctorName = intent.getStringExtra("doctorName");
                ConsultTimesBean consultTimesBean = intent.getParcelableExtra("consultTimesBean");
                replaceFragment(BuyConsultVideoFragment.newInstance(Type, Price, doctorName, consultTimesBean));
                break;

            case Constants.BUYCONSULTIMAGE_FRAGMENT://图文咨询
                //String Type, String Price,String doctorName, ConsultTimesBean consultTimesBean
                String orderid = intent.getStringExtra("order_id");
                String img_doctorName = intent.getStringExtra("doctorName");
                String Hx_account_buy = intent.getStringExtra("Hx_account");
                List<String> files = (List<String>) intent.getSerializableExtra("list");
                String img_Price = intent.getStringExtra("price");
                String desc = intent.getStringExtra("desc");
                replaceFragment(BuyConsultImgageFragment.newInstance(orderid, img_doctorName, Hx_account_buy, img_Price, files, desc));
                break;
            case Constants.BUYCONSULTHEALTH_FRAGMENT://购买健康管理
                health_record_id = intent.getStringExtra(Constants.HEALTH_RECORD_ID);
                HealthManagerBeans healthManager = intent.getParcelableExtra("healthManagerBeans");
                replaceFragment(BuyConsultHealthFragment.newInstance(health_record_id, healthManager));
                break;

            case Constants.BUY_PLAN_FRAGMENT://购买调理方案String health_record_id, String test_id, String order_actual_price
                health_record_id = intent.getStringExtra(Constants.HEALTH_RECORD_ID);
                test_id = intent.getStringExtra("test_id");
                String order_actual_price1 = intent.getStringExtra("order_actual_price");
                replaceFragment(BuyPlanFragment.newInstance(health_record_id, test_id, order_actual_price1));
                break;

            case Constants.BUY_VIP_FRAGMENT://购买会员String associator_id, String associator_price, String associator_title
                String associator_id = intent.getStringExtra("associator_id");
                String associator_price = intent.getStringExtra("associator_price");
                String associator_title = intent.getStringExtra("associator_title");
                replaceFragment(BuyVipFragment.newInstance(associator_id, associator_price, associator_title));
                break;


            case Constants.GIVE_HEART_FRAGMENT://支付送心意
                //String doctor_name, String doctor_id,String record_desc,String order_actual_price)
                doctor_name = intent.getStringExtra("doctor_name");
                doctor_id = intent.getStringExtra("doctor_id");
                String record_desc = intent.getStringExtra("record_desc");
                String order_actual_price = intent.getStringExtra("order_actual_price");

                replaceFragment(GiveHeartFragment.newInstance(doctor_name, doctor_id, record_desc, order_actual_price));
                break;

            case Constants.UPDATELOGINPWD_FRAGMENT://修改登录密码
                replaceFragment(UpdateLoginPwdFragment.newInstance());
                break;
            case Constants.UPDATEPAYPWD_FRAGMENT://修改支付密码

                String setting_type = getIntent().getStringExtra("type");
                replaceFragment(UpdatePayPwdFragment.newInstance(setting_type));
                break;
            case Constants.ACCOUNT_SECURITY_FRAGMENT://账号安全
                replaceFragment(AccountSecurityFragment.newInstance());
                break;
            case Constants.UPLOAD_CASE_FRAGMENT://上传病历
                replaceFragment(UploadCaseFragment.newInstance());
                break;

            case Constants.MY_ACCOUNT_FRAGMENT://我的账户
                replaceFragment(MyAccountFragment.newInstance());
                break;
            case Constants.CHONGZHI_FRAGMENT://充值
                replaceFragment(ChongzhiFragment.newInstance());
                break;
            case Constants.DETAIL_FRAGMENT://明细
                replaceFragment(DetailFragment.newInstance());
                break;
            case Constants.MY_PROFIT_FRAGMENT://我的收益
                replaceFragment(MyProfitFragment.newInstance());
                break;
            case Constants.MY_ORDER_FRAGMENT://我的订单
                type = getIntent().getStringExtra("type");
                String page = getIntent().getStringExtra("page");
                replaceFragment(MyOrderFragment.newInstance(type, page));
                break;
            case Constants.MY_DOCTOR_FRAGMENT://我的医生
                replaceFragment(MydoctorFragment.newInstance());
                break;
            case Constants.MY_VIP_FRAGMENT://我的会员
                replaceFragment(MyVipFragment.newInstance());
                break;

            case Constants.VOICE_VIDEO_CALLFRAGMENT://语音和视频的拨打界面

                ConsultBean consultBean = intent.getParcelableExtra("ConsultBean");

                String role = intent.getStringExtra("role");
                replaceFragment(VoiceVIdeoCallFragment.newInstance(consultBean, role));
                break;


            case Constants.Message_Remind_Fragment://消息提醒
                replaceFragment(MessageRemindFragment.newInstance());
                break;
            case Constants.ADDRESS_FRAGMENT://地址管理
                type = getIntent().getStringExtra("type");
                replaceFragment(AddressFragment.newInstance(type));
                break;
            case Constants.VIP_DETAIL_FRAGMENT://会员详情
                AssociatorBean associatorBean = intent.getParcelableExtra("AssociatorBean");
                replaceFragment(VipDetailFragment.newInstance(associatorBean));
                break;
            case Constants.EDIT_PERSONALINFO_FRAGMENT://编辑个人介绍
                String doctor_ids = getIntent().getStringExtra("doctor_id");
                String doctor_introduce = getIntent().getStringExtra("doctor_introduce");
                String doctor_background = getIntent().getStringExtra("doctor_background");
                String doctor_winning = getIntent().getStringExtra("doctor_winning");
                String doctor_remarks = getIntent().getStringExtra("doctor_remarks");
                replaceFragment(EditPersonalInfoFragment.newInstance(doctor_ids, doctor_introduce, doctor_background, doctor_winning, doctor_remarks));
                break;

            case Constants.CLASS_GOODS_LIST_FRAGMENT://分类商品列表
                String class_uuid = intent.getStringExtra("class_uuid");
                replaceFragment(ClassGoodsListFragment.newInstance(class_uuid));
                break;
            case Constants.ALL_CLASS_FRAGMENT://所有分类
                replaceFragment(AllClassFragment.newInstance());
                break;

            case Constants.FORGETLOGINPWD_FRAGMENT://忘记密码
                replaceFragment(ForgetLoginPwdFragment.newInstance());
                break;
            case Constants.FORGETLOGINPWD_NEXT_FRAGMENT://忘记密码下一步
                String phone = getIntent().getStringExtra("phone");
                String code = getIntent().getStringExtra("code");
                replaceFragment(ForgetLoginPwdNextFragment.newInstance(phone, code));
                break;

            case Constants.DOCTOR_PERSONAL_INFO_FRAGMENT://医生个人介绍

                DoctorDetailBean doctorDetailBean = getIntent().getParcelableExtra("doctorDetailBean");
                replaceFragment(DoctorPersonalInfoFragment.newInstance(doctorDetailBean));
                break;
            case Constants.TIXIAN_FRAGMENT://提现
                String tixian_type = getIntent().getStringExtra("type");
                replaceFragment(TixianFragment.newInstance(tixian_type));
                break;
            case Constants.BIND_ACCOUNT_FRAGMENT://绑定账户列表
                replaceFragment(BindAccountFragment.newInstance());
                break;

            case Constants.BIND_ALIPAY_FRAGMENT://绑定支付宝
                replaceFragment(BindAliapayFragment.newInstance());
                break;

            case Constants.BIND_WX_FRAGMENT://绑定微信
                replaceFragment(BindWxFragment.newInstance());
                break;
            case Constants.VERIFY_PHONE_FRAGMENT://验证手机号

                String bind_type = getIntent().getStringExtra("bind_type");
                replaceFragment(VerifyPhoneFragment.newInstance(bind_type));
                break;

            case Constants.CONFIEM_ORDER_VERIFYPHONE_FRAGMENT://提交订单验证手机号

                phone = getIntent().getStringExtra("phone");
                replaceFragment(ConfiemOrderVerifyPhoneFragment.newInstance(phone));
                break;


            case Constants.GOOD_DETAIL_FRAGMENT://商品详情

                String goods_id = getIntent().getStringExtra("goods_id");
                replaceFragment(GoodDetailFragment.newInstance(goods_id));
                break;
            case Constants.SERVICE_GOOD_DETAIL_FRAGMENT://服务商品详情
                String service_goods_id = getIntent().getStringExtra("goods_id");
                replaceFragment(ServiceGoodDetailFragment.newInstance(service_goods_id));
                break;


            case Constants.CART_FRAGMENT://购物车

                replaceFragment(CartFragment.newInstance());
                break;

            case Constants.GOOD_ORDER_DETAIL_FRAGMENT://商品订单详情

                String order_id = getIntent().getStringExtra("order_id");
                replaceFragment(GoodOrderDetailFragment.newInstance(order_id));
                break;

            case Constants.SERVICE_GOODORDER_DETAILFRAGMENT://服务商品订单详情
                String service_order_id = getIntent().getStringExtra("order_id");
                replaceFragment(ServiceGoodOrderDetailFragment.newInstance(service_order_id));
                break;


            case Constants.CONFIRM_SERVICEGOODORDER_FRAGMENT://提交服务商品订单
                String goodbeanstr = getIntent().getStringExtra("goodsBean");
                GoodsBean goodsBean = new Gson().fromJson(goodbeanstr, GoodsBean.class);
                replaceFragment(ConfirmServiceGoodOrderFragment.newInstance(goodsBean));
                break;


            case Constants.EDIT_ALLERGY_CONTENT_FRAGMENT://编辑过敏史

                String content = getIntent().getStringExtra("content");
                health_record_id = getIntent().getStringExtra("health_record_id");
                replaceFragment(editAllergyContentFragment.newInstance(content, health_record_id));
                break;
            case Constants.TESTDATA_HUANXIN_FRAGMENT://聊天中查看测试数据
                String huanxin_test_id = getIntent().getStringExtra("test_id");
                replaceFragment(TestDataHuanxinFragment.newInstance(huanxin_test_id));
                break;
            case Constants.ADD_PLAN_FRAGMENT://添加方案
                health_record_id = getIntent().getStringExtra("health_record_id");
                replaceFragment(AddPlanFragment.newInstance(health_record_id));
                break;
            case Constants.SEND_HEALTH_FILE_FRAGMENT://发送健康档案
                health_record_id = getIntent().getStringExtra("health_record_id");
                replaceFragment(SendHealthFileFragment.newInstance(health_record_id));
                break;
            case Constants.FANKUI_FRAGMENT://意见反馈
                replaceFragment(FankuiFragment.newInstance());
                break;
            case Constants.ASSESSMENT_DOCTOR_FRAGMENT://评价医生

                doctor_id = getIntent().getStringExtra("doctor_id");
                replaceFragment(AssessmentDoctorFragment.newInstance(doctor_id));
                break;

            case Constants.CONFIRM_GOODORDER_FRAGMENT://提交订单
                type = getIntent().getStringExtra("type");
                List<CartBean> cartBeanList = getIntent().getParcelableArrayListExtra("cartBeanList");
                replaceFragment(ConfirmGoodOrderFragment.newInstance(type, cartBeanList));
                break;

            case Constants.ADD_ADDRESS_FRAGMENT://添加地址
                type = getIntent().getStringExtra("type");
                AddressBean addressBean = getIntent().getParcelableExtra("addressBean");
                replaceFragment(AddAddressFragment.newInstance(type, addressBean));
                break;
            case Constants.BUY_GOOD_FRAGMENT://支付订单 购买商品
                String order_price = getIntent().getStringExtra("order_price");
                String order_ids = getIntent().getStringExtra("order_ids");
                String order_type = "";

                try {
                    order_type = getIntent().getStringExtra(Constants.TYPE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                replaceFragment(BuyGoodFragment.newInstance(order_ids, order_price, order_type));
                break;
            case Constants.MY_LIVELIST_FRAGMENT://我的直播列表
                replaceFragment(MyLiveListFragment.newInstance());
                break;
            case Constants.ASSESSMENT_ORDER_FRAGMENT:

                List<OrderGoodsBeans> orderGoodsBeanses = getIntent().getParcelableArrayListExtra("orderGoodsBeanses");
                order_id = getIntent().getStringExtra("order_id");
                replaceFragment(AssessmentOrderFragment.newInstance(orderGoodsBeanses, order_id));
                break;

            case Constants.APPLY_AFTER_SALE_FRAGMENT://申请售后

                OrderGoodsBeans orderGoodsBeans = getIntent().getParcelableExtra("orderGoodsBeans");
                replaceFragment(ApplyAfterSaleFragment.newInstance(orderGoodsBeans));
                break;

            case Constants.AFTER_SALE_ORDER_LIST_FRAGMENT://售后列表

                replaceFragment(AfterSaleOrderListFragment.newInstance());
                break;
            case Constants.AFTER_SALE_ORDER_DETAIL_FRAGMENT://售后订单详情

                String refund_id = getIntent().getStringExtra("refund_id");
                replaceFragment(AfterSaleOrderDetailFragment.newInstance(refund_id));
                break;
            case Constants.MORE_ASSESSMENT_LIST_FRAGMENT://更多评价

                goods_id = getIntent().getStringExtra("goods_id");
                replaceFragment(MoreAssessmentListFragment.newInstance(goods_id));
                break;
            case Constants.Merchants_Detail_Fragment://商家详情
                goods_id = getIntent().getStringExtra("merchants_id");
                replaceFragment(MerchantsDetailFragment.newInstance(goods_id));
                break;

            case Constants.Famous_Doctor_LiveList_Fragment://名医直播列表
                replaceFragment(FamousDoctorLiveListFragment.newInstance());
                break;
            case Constants.MORE_NEARBY_SHOPLIST_FRAGMENT://更多附近门店

                String nearcity = getIntent().getStringExtra(Constants.LOCALITY);
                double nearLat = getIntent().getDoubleExtra(Constants.LATITUDE, 0);
                double nearLag = getIntent().getDoubleExtra(Constants.LONGITUDE, 0);
                replaceFragment(MoreNearbyShopListFragment.newInstance(nearcity, nearLat, nearLag));
                break;
            case Constants.HOT_GOODSLIST_FRAGMENT://更多热卖商品
                replaceFragment(hotGoodsListFragment.newInstance(""));
                break;
            case Constants.Start_Live_Fragment://开始直播
                replaceFragment(StartLiveFragment.newInstance());
                break;

            case Constants.MERCHANTS_MAP_FRAGMENT://门店详情
                Bundle bundle = getIntent().getExtras();

                replaceFragment(MerchantsMapFragment.newInstance((PoiItem) bundle.get(Constants.POIITEM)));
                break;

            case Constants.SEARCH_HISTORY_FRAGMENT://搜索历史
                replaceFragment(SearchHistoryFragment.newInstance());
                break;

            case Constants.SEARCH_FRAGMENT://搜索
                String search_content = getIntent().getStringExtra("search_content");
                replaceFragment(SearchFragment.newInstance(search_content));
                break;
            case Constants.MORESEARCH_DOCTOR_FRAGMENT://搜索更多医生
                keyWord = getIntent().getStringExtra("keyWord");
                replaceFragment(MoreSearchDoctorFragment.newInstance(keyWord));
                break;
            case Constants.MORESEARCH_MERCHANTS_FRAGMENT://搜索更多商家
                keyWord = getIntent().getStringExtra("keyWord");
                replaceFragment(MoreSearchMerchantsFragment.newInstance(keyWord));
                break;
            case Constants.MORESEARCH_GOOD_FRAGMENT://搜索更多商品
                keyWord = getIntent().getStringExtra("keyWord");
                replaceFragment(MoreSearchGoodFragment.newInstance(keyWord));
                break;

            case Constants.HOUSEKEEPING_FRAGMENT://家政服务
                replaceFragment(HousekeepingFragment.newInstance());
                break;

            case Constants.HOUSEKEEPING_DETAIL_FRAGMENT://家政服务详情
                String housekeep_id = getIntent().getStringExtra("housekeep_id");
                replaceFragment(HousekeepDetailFragment.newInstance(housekeep_id));
                break;

            case Constants.MEDICAL_RECORDS_DETAIL_HX_FRAGMENT://环信电子病历详情
                String medical_id = getIntent().getStringExtra("medical_id");
                int positions = getIntent().getIntExtra("position", 0);
                replaceFragment(MedicalRecordsDetailHxFragment.newInstance(medical_id, positions));
                break;
            case Constants.RELEASE_HOUSEKEEP_FRAGMENT://发布家政信息
                replaceFragment(ReleaseHouseKeepFragment.newInstance());
                break;
            case Constants.COMMUNITYDETAILS:

                String plate = getIntent().getStringExtra(Constants.PLATE_ID);

                replaceFragment(CommunityDetailsFragment.newInstance(plate));
                break;
            case Constants.POSTEDFRGAMENT:
                int flag = getIntent().getIntExtra("flag", 0);
                if (flag == 1) {

                    Bundle bundle1 = intent.getBundleExtra("data");
                    Note note = (Note) bundle1.getSerializable("note");
                    replaceFragment(PostedFragment.newInstance(note, flag));
                } else {

                    replaceFragment(PostedFragment.newInstance(flag, getIntent().getStringExtra(Constants.PLATE_ID)));

                }


                break;
            case Constants.POSTEDListFRGAMENT:

                replaceFragment(NewMyPostedListFragment.newInstance());
                break;
            case Constants.POSTEDETAILS:
/***
 * 健康详情
 */String postId = intent.getStringExtra(Constants.PLATELISTBEAN);
                replaceFragment(FragmentPostDetails.newInstance(postId));

                break;
            case Constants.MERCHANTFORMFRAGMENT:
                String mtyp = intent.getStringExtra(Constants.TYPE);
                replaceFragment(MerchantFormFragment.newInstance(mtyp));
                break;
            case Constants.SERVICEDETILS:


                replaceFragment(ServiceDetaliFragment.newInstance());

                break;
            case Constants.COMPLAINTFRAGMENT:
                String housid = intent.getStringExtra(Constants.TYPE);
                replaceFragment(ComplaintFragment.newInstance(housid));

                break;
            case Constants.MERCHANTMESSAGE:
                replaceFragment(MerchantMessageFragment.newInstance());
                break;
            case Constants.MERCHANTABOUT:


                replaceFragment(MerchantAboutFragment.newInstance());

                break;
            case Constants.SERVICECOPE:
                replaceFragment(ServicecopeFragment.newInstance());

                break;
            case Constants.EDITSERVICE:
                String serviceCopeBean = null;
                try {
                    serviceCopeBean = getIntent().getStringExtra(Constants.TAG);

                } catch (Exception e) {
                    e.printStackTrace();

                }
                if (serviceCopeBean != null) {
                    replaceFragment(EditServiceFragment.newInstance(serviceCopeBean));

                } else {
                    replaceFragment(EditServiceFragment.newInstance());

                }

                break;
            case Constants.MERCHANTMOREADDRESS:
                //商家更多地址
                String serviceId = getIntent().getStringExtra("house_service_id");
                replaceFragment(MerchantMoreAddressFragment.newInstence(serviceId));
                break;
            case Constants.ADDADDRESS:
                String addserviceId = getIntent().getStringExtra("house_service_id");
                replaceFragment(ServiceAddAddressFragment.newInstence(addserviceId));
                break;
            case Constants.SERVICEMOREASSESSMENT:
                String typeservice = getIntent().getStringExtra(Constants.TYPE);
                replaceFragment(ServiceMoreAssessmentListFragment.newInstance(typeservice));
                break;
            case Constants.SHOPFRAGMENT:
                //打开商城界面
                replaceFragment(ShopFragment.newInstance());
                break;
            case Constants.ADDHEALTHMANAGE:
                //添加健康管理
                // String typeid=intent.getStringExtra(Constants.TYPE);

                HealthManagerBeans beans = null;


                try {
                    beans = intent.getParcelableExtra(Constants.HEALTH_RECORD_ID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                replaceFragment(AddHealthManageFragment.newInstance(beans));

                break;
            case Constants.COMMUNICATEFRAGMENT:

                String str = intent.getStringExtra(Constants.TYPE);
                replaceFragment(CommunicateFragment.newInstance(str));
                break;


            default:
                LogUtils.d("Not found fragment:" + Integer.toHexString(fragmentKey));
                break;

        }
    }


    public void replaceFragment(Fragment fragmnet) {
        replaceFragment(R.id.fragmentContent, fragmnet);
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }


    public static void start(Activity activity, int KEY) {
        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, KEY);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        // Log.e(TAG, "onActivityResult: " + requestCode + "\n" + resultCode + "\n" + data);
    }
}
