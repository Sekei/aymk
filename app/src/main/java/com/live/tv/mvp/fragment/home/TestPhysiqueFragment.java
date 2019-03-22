package com.live.tv.mvp.fragment.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.CheckLeadFactorsBean;
import com.live.tv.bean.CheckQuestionBean;
import com.live.tv.bean.LeadFactorsBean;
import com.live.tv.bean.QuestionBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.TestPhysiquePresenter;
import com.live.tv.mvp.view.home.ITestPhysiqueView;
import com.live.tv.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Created by stone
 * @since 2018/1/22
 */

public class TestPhysiqueFragment extends BaseFragment<ITestPhysiqueView, TestPhysiquePresenter> implements ITestPhysiqueView {
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.no)
    TextView no;
    @BindView(R.id.yes)
    TextView yes;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String health_record_id;
    private String step;  //0体质测试  1体质测试完成
    private String result; //体质测试完成结论

    private int is_checked = -1; //是否选中答案  -1未选中 其他选中
    private String question_id;  //体质测试题id
    private String type; //体质测试题类型
    private String lead_factors_id; //导致因素题id
    private int testType = 0; //测试阶段  0体质测试  1导致因素测试

    private PopupWindow popupWindow;
    private boolean isNextAuto = false;//是否自动进入下一题


    public static TestPhysiqueFragment newInstance(String health_record_id, String step, String result) {
        Bundle args = new Bundle();
        TestPhysiqueFragment fragment = new TestPhysiqueFragment();
        fragment.health_record_id = health_record_id;
        fragment.step = step;
        fragment.result = result;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_test_physique;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.test_physique);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.test_profess_icon_more);
    }

    @Override
    public void initData() {
        initPopWindow();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(step)) {
            map.put("health_record_id", health_record_id);
            getPresenter().getQuestionBean(map);
        } else {
            ok.setVisibility(View.GONE);
            no.setText(R.string.test_again);
            yes.setText(R.string.result_ok);
            num.setText(R.string.physique_conclusion);
            tittle.setText(result);
            tvTitle.setText(R.string.physique_conclusion);
        }
    }

    //测试体质题目
    @Override
    public void onGetQuestionBean(QuestionBean data) {
        num.setText(data.getQuestion_number() + "/" + data.getSum_num());
        question_id = data.getQuestion_id();
        type = data.getType();
        tittle.setText(data.getQuestion_title());
        //初始化
        is_checked = -1;
        setOkG9();
        setNoPri();
        setYesPri();

    }

    //测试体质结论
    @Override
    public void onCheckQuestion(CheckQuestionBean data) {
        if ((data.getSum_ques_num() - data.getDone_ques_num()) == 1) {
            ok.setText(R.string.commit);
        }
        if (data.getDone_ques_num() == data.getSum_ques_num()) {
            ok.setVisibility(View.GONE);
            no.setText(R.string.test_again);
            yes.setText(R.string.result_ok);
            num.setText(R.string.physique_conclusion);
            tittle.setText(data.getTest_result());
            if (data.getTest_result()!=null&&!"".equals(data.getTest_result())){
                yes.setVisibility(View.VISIBLE);
            }else {
                yes.setVisibility(View.GONE);
                tittle.setText("没有测试结果");
            }

            tvTitle.setText(R.string.physique_conclusion);
            setNoPri();
            setYesPri();
            return;
        }
        map.clear();
        map.put("health_record_id", health_record_id);
        getPresenter().getQuestionBean(map);
    }

    //体质测试完成后确认结论
    @Override
    public void onCheckResult(String data) {
        yes.setText(R.string.yes);
        no.setText(R.string.no);
        ok.setVisibility(View.VISIBLE);
        ok.setText(R.string.next_question);
        tvTitle.setText(R.string.test_reason);
        map.clear();
        map.put("health_record_id", health_record_id);
        getPresenter().getLeadFactorsBean(map);

    }

    //导致原因题目
    @Override
    public void onGetLeadFactorsBean(LeadFactorsBean data) {
        testType = 1;
        num.setText(data.getLeadFactors_number() + "/" + data.getSum_num());
        lead_factors_id = data.getLead_factors_id();
        tittle.setText(data.getLead_factors_title());
        //初始化
        is_checked = -1;
        setOkG9();
        setNoPri();
        setYesPri();

    }

    //导致原因的结论
    @Override
    public void onCheckLeadFactors(CheckLeadFactorsBean data) {
        if ((data.getSum_factors_num() - data.getDone_factors_num()==1) ) {
            ok.setText(R.string.commit);
        }
        if (data.getDone_factors_num() == data.getSum_factors_num()) {
            map.clear();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("health_record_id", health_record_id);
            getPresenter().checkFactors(map);
            return;
        }
        map.clear();
        map.put("health_record_id", health_record_id);
        getPresenter().getLeadFactorsBean(map);
    }

    @Override
    public void onCheckFactors(String data) {
        startTestCompleteFragment(health_record_id);
        finish();
    }

    @Override
    public void onCheckQuestionAgain(String data) {
        finish();
    }


    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.yes, R.id.no, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                popupWindow.showAsDropDown(ivRight);
                break;
            case R.id.yes:
                if ("确认结论".equals(yes.getText().toString())) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("health_record_id", health_record_id);
                    getPresenter().checkResult(map);
                    return;
                }
                is_checked = 1;
                if (isNextAuto) {
                    check();
                }
                setOkPri();
                setYesWhite();
                setNoPri();
                break;
            case R.id.no:
                if ("重新测试".equals(no.getText().toString())) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("health_record_id", health_record_id);
                    getPresenter().checkQuestionAgain(map);
                }
                is_checked = 0;
                if (isNextAuto) {
                    check();
                }
                setOkPri();
                setYesPri();
                setNoWhite();
                break;
            case R.id.ok:
                if (-1 == is_checked) {
                    ToastUtils.showToast(context.getApplicationContext(), getResources().getString(R.string.config_answer));
                    return;
                }
                check();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public TestPhysiquePresenter createPresenter() {
        return new TestPhysiquePresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(context.getApplicationContext(),e.getMessage());

    }


    private void setOkG9() {
        ok.setTextColor(getResources().getColor(R.color.colorTextG9));
        ok.setBackgroundResource(R.drawable.setbar_seach_gray);
    }

    private void setOkPri() {
        ok.setTextColor(getResources().getColor(R.color.colorPrimary));
        ok.setBackgroundResource(R.drawable.setbar_seach);
    }

    private void setYesPri() {
        yes.setTextColor(getResources().getColor(R.color.colorPrimary));
        yes.setBackgroundResource(R.drawable.setbar_mn);
    }

    private void setYesWhite() {
        yes.setTextColor(getResources().getColor(R.color.pure_white));
        yes.setBackgroundResource(R.drawable.setbar_mm);
    }

    private void setNoPri() {
        no.setTextColor(getResources().getColor(R.color.colorPrimary));
        no.setBackgroundResource(R.drawable.setbar_mn);
    }

    private void setNoWhite() {
        no.setTextColor(getResources().getColor(R.color.pure_white));
        no.setBackgroundResource(R.drawable.setbar_mm);
    }

    private void check() {
        if (0 == testType) {
            map.clear();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("health_record_id", health_record_id);
            map.put("question_id", question_id);
            map.put("type", type);
            map.put("is_checked", is_checked + "");
            getPresenter().checkQuestion(map);
        } else {
            map.clear();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("health_record_id", health_record_id);
            map.put("lead_factors_id", lead_factors_id);
            map.put("is_checked", is_checked + "");
            getPresenter().checkLeadFactors(map);
        }
    }

    //初始化popwindow
    private void initPopWindow() {
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        isNextAuto = sharedPre.getBoolean(Constants.ISNEXTAUTO, false);
        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(context).inflate(R.layout.test_popupwindow, null);
        TextView isNext = (TextView) view.findViewById(R.id.isNext);
        TextView quit = (TextView) view.findViewById(R.id.quit);
        final ImageView check = (ImageView) view.findViewById(R.id.check);
        if (isNextAuto) {
            check.setVisibility(View.VISIBLE);
        } else {
            check.setVisibility(View.GONE);
        }
        isNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNextAuto) {
                    check.setVisibility(View.GONE);
                } else {
                    check.setVisibility(View.VISIBLE);
                }
                isNextAuto = !isNextAuto;
                saveIsNext(context, isNextAuto);
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestPhysiqueFragment.this.finish();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x50000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

    }

    //保存是否自动进入下一题的标识
    public static void saveIsNext(Context context, Boolean isNext) {
        //获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        //设置参数
        editor.putBoolean(Constants.ISNEXTAUTO, isNext);
        //提交
        editor.commit();
    }
}
