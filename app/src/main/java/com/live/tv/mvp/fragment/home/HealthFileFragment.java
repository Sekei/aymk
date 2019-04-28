package com.live.tv.mvp.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.adapter.home.HealthFileListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.PublicEditFragment;
import com.live.tv.mvp.fragment.mine.PersonalInfoFragment;
import com.live.tv.mvp.presenter.home.HealthFilePresenter;
import com.live.tv.mvp.view.home.IHealthFileView;
import com.live.tv.util.LoadingUtil;
import com.live.tv.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Created by stone
 * @since 2018/1/11
 */

public class HealthFileFragment extends BaseFragment<IHealthFileView, HealthFilePresenter> implements IHealthFileView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.verified)
    TextView verified;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.oneBottom)
    View oneBottom;
    @BindView(R.id.twoBottom)
    View twoBottom;
    @BindView(R.id.bg_three)
    View bgThree;
    @BindView(R.id.heightTittle)
    TextView heightTittle;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.weightTittle)
    TextView weightTittle;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.isMarryTittle)
    TextView isMarryTittle;
    @BindView(R.id.isMarry)
    TextView isMarry;
    @BindView(R.id.isAllergyTittle)
    TextView isAllergyTittle;
    @BindView(R.id.isAllergy)
    TextView isAllergy;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.allergyContentTittle)
    TextView allergyContentTittle;
    Unbinder unbinder;
    @BindView(R.id.familiaydisease)
    TextView familiaydisease;
    @BindView(R.id.fapersondisease)
    TextView fapersondisease;
    @BindView(R.id.isfapersondisease)
    TextView isfapersondisease;
    @BindView(R.id.tv_sex)
    TextView tvsex;//性别
    @BindView(R.id.tv_birthday)
    TextView tvbitthday;//生日
    @BindView(R.id.tv_age)
    TextView tvage;//年龄
    @BindView(R.id.tv_bloodtype)
    TextView tvBloodtype;//血型
    @BindView(R.id.isfamiliaydisease)
    TextView tvfamiliaydisease;//是否家族遗传
    /**
     * ----------------------家族遗传病------------------------------------
     */
    @BindView(R.id.familiaydiseasename)
    TextView tvfamiliaydiseasename;//家族遗传病
    @BindView(R.id.familiaydiseasecontent)
    TextView tvfamiliaydiseasecontent;//家族遗传名称
    @BindView(R.id.familiayperson)
    TextView tvfamiliayperson;//患病的人
    @BindView(R.id.familiaypersoncontent)
    TextView tvfamiliaypersoncontent;//患病的对象
    @BindView(R.id.familiaythrowback)
    TextView tvfamiliaythrowback;//隔代遗传
    @BindView(R.id.isfamiliaythrowback)
    TextView tvisfamiliaythrowback;//是否隔代遗传


    /**
     * ----------------------个人疾病------------------------------------
     */
    @BindView(R.id.tv_profession)
    TextView tvProfession;//职业
    @BindView(R.id.fapersonname)
    TextView fapersonname;//个人疾病名称
    @BindView(R.id.fapersoncontent)
    TextView fapersoncontent;//具体名称
    @BindView(R.id.personheredity)
    TextView personheredity;//是否遗传
    @BindView(R.id.ispersonheredity)
    TextView ispersonheredity;
    @BindView(R.id.persontime)
    TextView persontime;//发现时间
    @BindView(R.id.persontimeshow)
    TextView persontimeshow;
    @BindView(R.id.pharmacyname)
    TextView pharmacyname;//用药名称
    @BindView(R.id.pharmacynameshow)
    TextView pharmacynameshow;
    private int nowyear;
    private int choseyear;




    /*-----------------------------------------------------*/

    private List<HealthRecordDetailBean.HealthPlanBeansBean> HealthPlanList;
    private HealthFileListAdapter adapter;

    private UserBean userBean;
    private HealthRecordDetailBean healthRecordDetailBean;
    private Map<String, String> map = new HashMap<>();

    private String health_record_id = "";

    public static HealthFileFragment newInstance() {

        Bundle args = new Bundle();

        HealthFileFragment fragment = new HealthFileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_health_file;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.health_file);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.more_file);

        HealthPlanList = new ArrayList<>();
        adapter = new HealthFileListAdapter(context, HealthPlanList);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HealthRecordDetailBean.HealthPlanBeansBean healthPlan = adapter.getItem(position);
                startHealthStepFragment(healthPlan.getHealth_record_id(), healthPlan.getDoctor_id());
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (null != userBean && null != userBean.getMember_id() && null != userBean.getMember_token()) {
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            map.put("health_record_id", userBean.getHealthRecordBean().getHealth_record_id());
            getPresenter().myHealthRecordDetail(map);
            LoadingUtil.showLoading(context);
        } else {
            startLogin();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public HealthFilePresenter createPresenter() {
        return new HealthFilePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.bg_one, R.id.ivLeft, R.id.tvRight, R.id.one, R.id.two, R.id.heightTittle,
            R.id.weightTittle, R.id.isMarryTittle, R.id.isAllergyTittle, R.id.verified,
            R.id.allergyContentTittle, R.id.nameTittle, R.id.sexTittle, R.id.birthdayTittle,
            R.id.ageTittle, R.id.bloodtypeTitle, R.id.familiaydisease, R.id.fapersondisease,
            R.id.professionTittle, R.id.familiaydiseasename, R.id.familiayperson, R.id.familiaythrowback,
            R.id.fapersonname, R.id.personheredity, R.id.persontime, R.id.pharmacyname
    })
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.verified:
                //startVerifiedFragment(health_record_id);//实名认证
                break;
            case R.id.tvRight:
                startFileMoreFragment("1");
                break;
            case R.id.bg_one:
                if (!"".equals(health_record_id)) {
                    startUpdateFileFragment(health_record_id);
                }

                break;
            case R.id.one:
                easyRecyclerView.setVisibility(View.INVISIBLE);
                one.setTextColor(getResources().getColor(R.color.colorPrimary));
                oneBottom.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                two.setTextColor(getResources().getColor(R.color.colorTextG3));
                twoBottom.setBackgroundColor(getResources().getColor(R.color.pure_white));
                bgThree.setVisibility(View.VISIBLE);
                height.setVisibility(View.VISIBLE);
                weight.setVisibility(View.VISIBLE);
                isMarry.setVisibility(View.VISIBLE);
                isAllergy.setVisibility(View.VISIBLE);
                break;
            case R.id.two:
                easyRecyclerView.setVisibility(View.VISIBLE);
                two.setTextColor(getResources().getColor(R.color.colorPrimary));
                twoBottom.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                one.setTextColor(getResources().getColor(R.color.colorTextG3));
                oneBottom.setBackgroundColor(getResources().getColor(R.color.pure_white));
                bgThree.setVisibility(View.GONE);
                height.setVisibility(View.GONE);
                weight.setVisibility(View.GONE);
                isMarry.setVisibility(View.GONE);
                isAllergy.setVisibility(View.GONE);

                break;
            case R.id.heightTittle:
                startpublicEditFragment("", "num", "单位：CM", "身高", context);
                break;
            case R.id.weightTittle:
                String mWeight = weight.getText().toString();
                startpublicWeightEditFragment("", "num", "单位：KG", "体重", context);

                break;


            case R.id.isMarryTittle:
                final String mIsMarry = isMarry.getText().toString();
                SinglePicker<String> singlePicker = new SinglePicker<>(getActivity(), new String[]{"已婚", "未婚", "其他"});
                singlePicker.setCanLoop(true);//不禁用循环
                singlePicker.setAnimationStyle(R.style.AnimBottom);
                singlePicker.setTopBackgroundColor(0xFFEEEEEE);
                singlePicker.setTopHeight(50);
                singlePicker.setTopLineHeight(1);
                singlePicker.setTitleText("请选择");
                singlePicker.setTitleTextColor(0xFF999999);
                singlePicker.setTitleTextSize(12);
                singlePicker.setCancelTextColor(0xFF999999);
                singlePicker.setCancelTextSize(13);
                singlePicker.setSubmitTextColor(0xFF6EB92B);
                singlePicker.setSubmitTextSize(13);
                singlePicker.setSelectedTextColor(0xFF6EB92B);
                singlePicker.setUnSelectedTextColor(0xFF999999);
                LineConfig config = new LineConfig();
                config.setColor(0xFF6EB92B);//线颜色
                config.setAlpha(140);//线透明度
                config.setRatio((float) (1.0 / 8.0));//线比率
                singlePicker.setLineConfig(config);
                singlePicker.setItemWidth(200);
                singlePicker.setBackgroundColor(0xFFE1E1E1);
                singlePicker.setSelectedItem(mIsMarry);
                singlePicker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        isMarry.setText(item);

                        Map<String, String> map = new HashMap<>();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("health_record_id", health_record_id);
                        map.put("is_marriage", isMarry.getText().toString());
                        getPresenter().UpdateRecord(map);


                    }
                });
                singlePicker.show();
                break;
            case R.id.isAllergyTittle:
                final String mIsAllergy = isAllergy.getText().toString();
                SinglePicker<String> singlePickerTwo = new SinglePicker<>(getActivity(), new String[]{"是", "否"});
                singlePickerTwo.setSelectedItem(mIsAllergy);
                singlePickerTwo.setCanLoop(true);//不禁用循环
                singlePickerTwo.setAnimationStyle(R.style.AnimBottom);
                singlePickerTwo.setTopBackgroundColor(0xFFEEEEEE);
                singlePickerTwo.setTopHeight(50);
                //singlePickerTwo.setTopLineColor(0xFF33B5E5);
                singlePickerTwo.setTopLineHeight(1);
                singlePickerTwo.setTitleText("请选择");
                singlePickerTwo.setTitleTextColor(0xFF999999);
                singlePickerTwo.setTitleTextSize(12);
                singlePickerTwo.setCancelTextColor(0xFF999999);
                singlePickerTwo.setCancelTextSize(13);
                singlePickerTwo.setSubmitTextColor(0xFF6EB92B);
                singlePickerTwo.setSubmitTextSize(13);
                singlePickerTwo.setSelectedTextColor(0xFFEE0000);
                singlePickerTwo.setUnSelectedTextColor(0xFF999999);
                LineConfig configTwo = new LineConfig();
                configTwo.setColor(0xFFEE0000);//线颜色
                configTwo.setAlpha(140);//线透明度
                configTwo.setRatio((float) (1.0 / 8.0));//线比率
                singlePickerTwo.setLineConfig(configTwo);
                singlePickerTwo.setItemWidth(200);
                singlePickerTwo.setBackgroundColor(0xFFE1E1E1);
                singlePickerTwo.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        isAllergy.setText(item);
                        if (item != null) {

                            if (item.equals("是")) {
                                allergyContentTittle.setVisibility(View.VISIBLE);
                            } else if (item.equals("否")) {

                                allergyContentTittle.setVisibility(View.GONE);

                            }

                        }
                        Map<String, String> map = new HashMap<>();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("health_record_id", health_record_id);
                        map.put("allergy", isAllergy.getText().toString());
                        getPresenter().UpdateRecord(map);

                    }
                });
                singlePickerTwo.show();
                break;


            case R.id.allergyContentTittle:
                //编辑过敏史

                startEditAllergyContentFragment(allergyContentTittle.getText().toString(), health_record_id);
                break;

            case R.id.nameTittle:

                startnameFragment("", "string", "请输入您的姓名", "姓名", context);
                break;
            case R.id.sexTittle:
                final String mIssex = tvsex.getText().toString();
                SinglePicker<String> singlePickersex = new SinglePicker<>(getActivity(), new String[]{"男", "女", "其他"});
                singlePickersex.setCanLoop(true);//不禁用循环
                singlePickersex.setAnimationStyle(R.style.AnimBottom);
                singlePickersex.setTopBackgroundColor(0xFFEEEEEE);
                singlePickersex.setTopHeight(50);
                singlePickersex.setTopLineHeight(1);
                singlePickersex.setTitleText("请选择");
                singlePickersex.setTitleTextColor(0xFF999999);
                singlePickersex.setTitleTextSize(12);
                singlePickersex.setCancelTextColor(0xFF999999);
                singlePickersex.setCancelTextSize(13);
                singlePickersex.setSubmitTextColor(0xFF6EB92B);
                singlePickersex.setSubmitTextSize(13);
                singlePickersex.setSelectedTextColor(0xFF6EB92B);
                singlePickersex.setUnSelectedTextColor(0xFF999999);
                LineConfig configsex = new LineConfig();
                configsex.setColor(0xFF6EB92B);//线颜色
                configsex.setAlpha(140);//线透明度
                configsex.setRatio((float) (1.0 / 8.0));//线比率
                singlePickersex.setLineConfig(configsex);
                singlePickersex.setItemWidth(200);
                singlePickersex.setBackgroundColor(0xFFE1E1E1);
                singlePickersex.setSelectedItem(mIssex);
                singlePickersex.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvsex.setText(item);

                        Map<String, String> map = new HashMap<>();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("health_record_id", health_record_id);
                        map.put("sex", tvsex.getText().toString());
                        getPresenter().UpdateRecord(map);


                    }
                });
                singlePickersex.show();

                break;

            case R.id.birthdayTittle:
                final DatePicker picker = new DatePicker(getActivity());
                picker.setCanLoop(false);
                picker.setWheelModeEnable(true);
                picker.setLineVisible(false);
                picker.setAnimationStyle(R.style.AnimBottom);
                picker.setSubmitTextColor(getResources().getColor(R.color.colorPrimary));
                picker.setSelectedTextColor(getResources().getColor(R.color.colorPrimary));
                picker.setTopPadding(15);
                picker.setRangeStart(1900, 8, 29);
                picker.setRangeEnd(3017, 1, 11);
                try {

                    if (!tvbitthday.getText().toString().equals("")) {
                        String[] strings = tvbitthday.getText().toString().split("-");
                        picker.setSelectedItem(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));

                    } else {
                        picker.setSelectedItem(1993, 7, 7);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    picker.setSelectedItem(1993, 7, 7);

                }
                picker.setWeightEnable(true);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        tvbitthday.setText(year + "-" + month + "-" + day);
                        Calendar c = Calendar.getInstance();//
                        nowyear = c.get(Calendar.YEAR);
                        if (year != null && !year.equals("")) {
                            choseyear = Integer.parseInt(year);
                            if ((nowyear - choseyear) > 0) {

                                tvage.setText((nowyear - choseyear) + " 岁");
                                age.setText((nowyear - choseyear) + " 岁");
                            } else {
                                tvage.setText(0 + " 岁");
                                age.setText("0 岁");
                            }
                            Map<String, String> map = new HashMap<>();
                            map.put("member_id", userBean.getMember_id());
                            map.put("member_token", userBean.getMember_token());
                            map.put("health_record_id", health_record_id);
                            map.put("birthday", tvbitthday.getText().toString());
//                            map.put("age",tvagee.getText().toString());
                            getPresenter().UpdateRecord(map);
                        }
                    }
                });
                picker.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());

                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
                    }
                });
                picker.show();


                break;
            case R.id.ageTittle:
                /***
                 * 年龄
                 */

                // argeFragment("", "num", "请输入您的年龄", "年龄", context);
                break;
            case R.id.bloodtypeTitle:


                final String mIsblood = tvBloodtype.getText().toString();
                SinglePicker<String> singlePickerblood = new SinglePicker<>(getActivity(), new String[]{"A型", "B型", "AB型", "O型", "RH阴性", "RH阳性"});
                singlePickerblood.setCanLoop(true);//不禁用循环
                singlePickerblood.setAnimationStyle(R.style.AnimBottom);
                singlePickerblood.setTopBackgroundColor(0xFFEEEEEE);
                singlePickerblood.setTopHeight(50);
                singlePickerblood.setTopLineHeight(1);
                singlePickerblood.setTitleText("请选择");
                singlePickerblood.setTitleTextColor(0xFF999999);
                singlePickerblood.setTitleTextSize(12);
                singlePickerblood.setCancelTextColor(0xFF999999);
                singlePickerblood.setCancelTextSize(13);
                singlePickerblood.setSubmitTextColor(0xFF6EB92B);
                singlePickerblood.setSubmitTextSize(13);
                singlePickerblood.setSelectedTextColor(0xFF6EB92B);
                singlePickerblood.setUnSelectedTextColor(0xFF999999);
                LineConfig configblood = new LineConfig();
                configblood.setColor(0xFF6EB92B);//线颜色
                configblood.setAlpha(140);//线透明度
                configblood.setRatio((float) (1.0 / 8.0));//线比率
                singlePickerblood.setLineConfig(configblood);
                singlePickerblood.setItemWidth(200);
                singlePickerblood.setBackgroundColor(0xFFE1E1E1);
                singlePickerblood.setSelectedItem(mIsblood);
                singlePickerblood.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvBloodtype.setText(item);
                        Map<String, String> map = new HashMap<>();
                        map.put("member_id", userBean.getMember_id());
                        map.put("member_token", userBean.getMember_token());
                        map.put("health_record_id", health_record_id);
                        map.put("blood", tvBloodtype.getText().toString());
                        getPresenter().UpdateRecord(map);

                    }
                });
                singlePickerblood.show();

                break;

            case R.id.familiaydisease:
//* 家族重大疾病*/

                showlistdialog(tvfamiliaydisease, 1, new String[]{"否", "是"});
                break;
            case R.id.fapersondisease:
//* 个人重大疾病*/
                showlistdialog(isfapersondisease, 2, new String[]{"否", "是"});
                break;
            case R.id.professionTittle:
                /***
                 * 职业
                 */
                // startnameFragment("", "string", "请输入您的姓名", "姓名", context);

                alleditdialog("", "string", "请输入您的职业", "职业", context, "", 1, tvProfession);
                break;
            case R.id.familiaydiseasename:
                /* 家族疾病的名称*/
                alleditdialog("", "string", "请输入疾病名称", "疾病名称", context, "", 2, tvfamiliaydiseasecontent);

                break;

            case R.id.familiayperson:
                /***
                 * 患病人
                 */

                showlistdialog(tvfamiliaypersoncontent, 3, new String[]{"父", "母", "其他"});

                break;

            case R.id.familiaythrowback:
                /***
                 * 家族是否隔代遗传
                 */
                showlistdialog(tvisfamiliaythrowback, 4, new String[]{"是", "否"});


                break;
            case R.id.fapersonname:
                /***
                 * 个人疾病名称
                 */
                alleditdialog("", "string", "请输入疾病名称", "疾病名称", context, "", 3, fapersoncontent);


                break;
            case R.id.personheredity:
                /**
                 * 是否遗传
                 */
                showlistdialog(ispersonheredity, 5, new String[]{"是", "否"});

                break;

            case R.id.persontime:
                /***
                 * 确诊时间
                 */
                // alleditdialog("","string","请输入确诊时间","确证时间",context,"",4,persontimeshow);
                showlistdialog(persontimeshow, 6, new String[]{"1月以内", "1-3月", "1年-2年", "2年以上"});


                break;
            case R.id.pharmacyname:
                /**用药名称*/

                alleditdialog("", "string", "请输入用药名称", "用药名称", context, "", 4, pharmacynameshow);
                break;
        }
    }


    /***
     * 所有输入文字的dialog
     *
     * @param s
     * @param type
     * @param hint
     * @param title
     * @param context
     * @param unit
     * @param tag
     * @param tv
     */
    public void alleditdialog(String s, String type, String hint, String title, Context context, final String unit, final int tag, final TextView tv) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {


                tv.setText(state + unit);

                switch (tag) {
                    case 1:
                        //职业
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("member_id", userBean.getMember_id());
                        map1.put("member_token", userBean.getMember_token());
                        map1.put("health_record_id", health_record_id);
                        map1.put("career", tv.getText().toString());
                        getPresenter().UpdateRecord(map1);
                        break;
                    case 2:
                        //家族疾病的名称
                        Map<String, String> map2 = new HashMap<>();
                        map2.put("member_id", userBean.getMember_id());
                        map2.put("member_token", userBean.getMember_token());
                        map2.put("health_record_id", health_record_id);
                        map2.put("inherited_disease_name", tv.getText().toString());
                        getPresenter().UpdateRecord(map2);
                        break;
                    case 3:
                        //个人患病名称
                        Map<String, String> map3 = new HashMap<>();
                        map3.put("member_id", userBean.getMember_id());
                        map3.put("member_token", userBean.getMember_token());
                        map3.put("health_record_id", health_record_id);
                        map3.put("disease_name", tv.getText().toString());
                        getPresenter().UpdateRecord(map3);
                        break;
                    case 4:
                        //用药名称
                        Map<String, String> map4 = new HashMap<>();
                        map4.put("member_id", userBean.getMember_id());
                        map4.put("member_token", userBean.getMember_token());
                        map4.put("health_record_id", health_record_id);
                        map4.put("pharmacy_name", tv.getText().toString());
                        getPresenter().UpdateRecord(map4);
                        break;


                }


                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }

    /***
     * 列表弹窗
     * @param tv
     * @param type
     * @param strs
     */
    private void showlistdialog(final TextView tv, final int type, String[] strs) {

        final String mIsPicker = tv.getText().toString();
        SinglePicker<String> singlePicker = new SinglePicker<>(getActivity(), strs);
        singlePicker.setCanLoop(true);//不禁用循环
        singlePicker.setAnimationStyle(R.style.AnimBottom);
        singlePicker.setTopBackgroundColor(0xFFEEEEEE);
        singlePicker.setTopHeight(50);
        singlePicker.setTopLineHeight(1);
        singlePicker.setTitleText("请选择");
        singlePicker.setTitleTextColor(0xFF999999);
        singlePicker.setTitleTextSize(12);
        singlePicker.setCancelTextColor(0xFF999999);
        singlePicker.setCancelTextSize(13);
        singlePicker.setSubmitTextColor(0xFF6EB92B);
        singlePicker.setSubmitTextSize(13);
        singlePicker.setSelectedTextColor(0xFF6EB92B);
        singlePicker.setUnSelectedTextColor(0xFF999999);
        LineConfig config = new LineConfig();
        config.setColor(0xFF6EB92B);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        singlePicker.setLineConfig(config);
        singlePicker.setItemWidth(200);
        singlePicker.setBackgroundColor(0xFFE1E1E1);
        singlePicker.setSelectedItem(mIsPicker);
        singlePicker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                tv.setText(item);

                switch (type) {
                    case 1:
                        if (item != null) {
                            /***
                             * 家族疾病
                             */
                            if (item.equals("是")) {
                                tvfamiliaydiseasename.setVisibility(View.VISIBLE);
                                tvfamiliaydiseasecontent.setVisibility(View.VISIBLE);
                                tvfamiliayperson.setVisibility(View.VISIBLE);
                                tvfamiliaypersoncontent.setVisibility(View.VISIBLE);
                                tvfamiliaythrowback.setVisibility(View.VISIBLE);
                                tvisfamiliaythrowback.setVisibility(View.VISIBLE);
                            } else if (item.equals("否")) {
                                tvfamiliaydiseasename.setVisibility(View.GONE);
                                tvfamiliaydiseasecontent.setVisibility(View.GONE);
                                tvfamiliayperson.setVisibility(View.GONE);
                                tvfamiliaypersoncontent.setVisibility(View.GONE);
                                tvfamiliaythrowback.setVisibility(View.GONE);
                                tvisfamiliaythrowback.setVisibility(View.GONE);

                            }
                            Map<String, String> map1 = new HashMap<>();
                            map1.put("member_id", userBean.getMember_id());
                            map1.put("member_token", userBean.getMember_token());
                            map1.put("health_record_id", health_record_id);
                            map1.put("is_inherited_disease", tv.getText().toString());
                            getPresenter().UpdateRecord(map1);

                        }


                        break;
                    case 2:
                        //* 个人重大疾病*/

                        if (item != null) {

                            if (item.equals("是")) {
                                fapersonname.setVisibility(View.VISIBLE);
                                fapersoncontent.setVisibility(View.VISIBLE);
                                personheredity.setVisibility(View.VISIBLE);
                                ispersonheredity.setVisibility(View.VISIBLE);
                                persontime.setVisibility(View.VISIBLE);
                                persontimeshow.setVisibility(View.VISIBLE);
                                pharmacyname.setVisibility(View.VISIBLE);
                                pharmacynameshow.setVisibility(View.VISIBLE);

                            } else if (item.equals("否")) {
                                fapersonname.setVisibility(View.GONE);
                                fapersoncontent.setVisibility(View.GONE);
                                personheredity.setVisibility(View.GONE);
                                ispersonheredity.setVisibility(View.GONE);
                                persontime.setVisibility(View.GONE);
                                persontimeshow.setVisibility(View.GONE);
                                pharmacyname.setVisibility(View.GONE);
                                pharmacynameshow.setVisibility(View.GONE);

                            }
                            Map<String, String> map2 = new HashMap<>();
                            map2.put("member_id", userBean.getMember_id());
                            map2.put("member_token", userBean.getMember_token());
                            map2.put("health_record_id", health_record_id);
                            map2.put("is_disease", tv.getText().toString());
                            getPresenter().UpdateRecord(map2);

                        }

                        break;
                    case 3:
                        // 患病人

                        Map<String, String> map3 = new HashMap<>();
                        map3.put("member_id", userBean.getMember_id());
                        map3.put("member_token", userBean.getMember_token());
                        map3.put("health_record_id", health_record_id);
                        map3.put("patient", tv.getText().toString());
                        getPresenter().UpdateRecord(map3);
                        break;
                    case 4:

                        //家族是否隔代遗传

                        Map<String, String> map4 = new HashMap<>();
                        map4.put("member_id", userBean.getMember_id());
                        map4.put("member_token", userBean.getMember_token());
                        map4.put("health_record_id", health_record_id);
                        map4.put("is_hereditary", tv.getText().toString());
                        getPresenter().UpdateRecord(map4);
                        break;
                    case 5:
                        //个人是否遗传
                        Map<String, String> map5 = new HashMap<>();
                        map5.put("member_id", userBean.getMember_id());
                        map5.put("member_token", userBean.getMember_token());
                        map5.put("health_record_id", health_record_id);
                        map5.put("is_inherited", tv.getText().toString());
                        getPresenter().UpdateRecord(map5);
                        break;
                    case 6:
                        //确诊时间
                        Map<String, String> map6 = new HashMap<>();
                        map6.put("member_id", userBean.getMember_id());
                        map6.put("member_token", userBean.getMember_token());
                        map6.put("health_record_id", health_record_id);
                        map6.put("diagnose_time", tv.getText().toString());
                        getPresenter().UpdateRecord(map6);
                        break;


                }

            }
        });
        singlePicker.show();


    }

    public void startpublicEditFragment(String s, String type, String hint, String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                height.setText(state + " CM");
                Map<String, String> map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("health_record_id", health_record_id);
                map.put("height", height.getText().toString());
                getPresenter().UpdateRecord(map);

                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }

    /***
     * 年龄
     * @param s
     * @param type
     * @param hint
     * @param title
     * @param context
     */
    public void argeFragment(String s, String type, String hint, String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                tvage.setText(state + " 岁");
              /*  Map<String, String> map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("health_record_id", health_record_id);
                map.put("height", height.getText().toString());
                getPresenter().UpdateRecord(map);*/

                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }

    /***
     * 修改名称
     * @param s
     * @param type
     * @param hint
     * @param title
     * @param context
     */
    public void startnameFragment(String s, String type, String hint, String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                tv_name.setText(state);
                Map<String, String> map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("health_record_id", health_record_id);
                map.put("record_name", tv_name.getText().toString());
                getPresenter().UpdateRecord(map);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }


    public void startpublicWeightEditFragment(String s, String type, String hint, String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s, type, hint, title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                weight.setText(state + " KG");
                Map<String, String> map = new HashMap<>();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("health_record_id", health_record_id);
                map.put("weight", weight.getText().toString());
                getPresenter().UpdateRecord(map);

                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LoadingUtil.hideLoading();
        //ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Subscribe
    public void onEventMainThread(String health_record_id) {

        this.health_record_id = health_record_id;
        map.put("health_record_id", health_record_id);
        getPresenter().healthRecordDetail(map);


        // LoadingUtil.showLoading(context);
    }


    /**
     * 通过档案id  来查询档案详情
     *
     * @param data
     */

    @Override
    public void onHealthRecordDetail(HealthRecordDetailBean data) {
        LoadingUtil.hideLoading();

        healthRecordDetailBean = data;
        health_record_id = data.getHealth_record_id();
        Glide.with(context).load(Constants.BASE_URL + data.getHead_image())
                .error(R.drawable.ava_defaultx)
                .into(avatar);


        tv_name.setText(data.getRecord_name() + "(" + data.getRelation() + ")");
        tvsex.setText(data.getSex());
        tvage.setText(data.getAge());
        tvbitthday.setText(data.getBirthday());
        name.setText(data.getRecord_name() + "(" + data.getRelation() + ")");
        sex.setText(data.getSex());
        age.setText(data.getAge());
        tvBloodtype.setText(data.getBlood());
        tvProfession.setText(data.getCareer());
        tvfamiliaydisease.setText(data.getIs_inherited_disease());//家族遗传病
        tvfamiliaydiseasecontent.setText(data.getInherited_disease_name());//疾病名称
        tvfamiliaypersoncontent.setText(data.getPatient());//huan bing ren
        tvisfamiliaythrowback.setText(data.getIs_hereditary());//shi fou ge dai
        isfapersondisease.setText(data.getIs_disease()); //ge ren shi fou zhong da ji bing
        fapersoncontent.setText(data.getDisease_name());//mingcheng
        ispersonheredity.setText(data.getIs_inherited());
        persontimeshow.setText(data.getDiagnose_time());
        pharmacynameshow.setText(data.getPharmacy_name());
        height.setText(data.getHeight().equals("") ? "0cm" : data.getHeight());
        weight.setText(data.getWeight().equals("") ? "0kg" : data.getWeight());
        isMarry.setText(data.getIs_marriage().equals("") ? "未知" : data.getIs_marriage());
        isAllergy.setText(data.getAllergy().equals("") ? "未知" : data.getAllergy());
        if (!data.getAllergy().equals("")) {
            if (data.getAllergy().equals("是")) {

                allergyContentTittle.setVisibility(View.VISIBLE);
            } else {
                allergyContentTittle.setVisibility(View.GONE);
            }
        }
        allergyContentTittle.setText(data.getAllergy_desc().equals("") ? "在这里编辑过敏内容" : data.getAllergy_desc());

        verified.setText(data.getAuthentication_show());
        if (data.getIs_inherited_disease() != null && !data.getIs_inherited_disease().equals("")) {
            if (data.getIs_inherited_disease().equals("是")) {
                tvfamiliaydiseasename.setVisibility(View.VISIBLE);
                tvfamiliaydiseasecontent.setVisibility(View.VISIBLE);
                tvfamiliayperson.setVisibility(View.VISIBLE);
                tvfamiliaypersoncontent.setVisibility(View.VISIBLE);
                tvfamiliaythrowback.setVisibility(View.VISIBLE);
                tvisfamiliaythrowback.setVisibility(View.VISIBLE);
            } else {
                tvfamiliaydiseasename.setVisibility(View.GONE);
                tvfamiliaydiseasecontent.setVisibility(View.GONE);
                tvfamiliayperson.setVisibility(View.GONE);
                tvfamiliaypersoncontent.setVisibility(View.GONE);
                tvfamiliaythrowback.setVisibility(View.GONE);
                tvisfamiliaythrowback.setVisibility(View.GONE);
            }
        }

        if (data.getIs_disease() != null && !data.getIs_disease().equals("")) {
            if (data.getIs_disease().equals("是")) {

                fapersonname.setVisibility(View.VISIBLE);
                fapersoncontent.setVisibility(View.VISIBLE);
                personheredity.setVisibility(View.VISIBLE);
                ispersonheredity.setVisibility(View.VISIBLE);
                persontime.setVisibility(View.VISIBLE);
                persontimeshow.setVisibility(View.VISIBLE);
                pharmacyname.setVisibility(View.VISIBLE);
                pharmacynameshow.setVisibility(View.VISIBLE);

            } else {
                fapersonname.setVisibility(View.GONE);
                fapersoncontent.setVisibility(View.GONE);
                personheredity.setVisibility(View.GONE);
                ispersonheredity.setVisibility(View.GONE);
                persontime.setVisibility(View.GONE);
                persontimeshow.setVisibility(View.GONE);
                pharmacyname.setVisibility(View.GONE);
                pharmacynameshow.setVisibility(View.GONE);
            }


        }


        if (data.getHealthPlanBeans() != null && data.getHealthPlanBeans().size() > 0) {
            adapter.clear();
            adapter.addAll(data.getHealthPlanBeans());
            adapter.notifyDataSetChanged();
        }


    }

    /**
     * 通过member_id   查询本人的健康档案详情
     *
     * @param data
     */

    @Override
    public void onmyHealthRecordDetail(HealthRecordDetailBean data) {

        LoadingUtil.hideLoading();
        if (data != null) {
            healthRecordDetailBean = data;
            health_record_id = data.getHealth_record_id();
            Glide.with(context).load(Constants.BASE_URL + data.getHead_image())
                    .placeholder(R.drawable.ava_defaultx)
                    .error(R.drawable.ava_defaultx)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avatar);
            name.setText(data.getRecord_name() + "(" + data.getRelation() + ")");
            sex.setText(data.getSex());
            age.setText(data.getAge());
            tv_name.setText(data.getRecord_name());
            tvsex.setText(data.getSex());
            tvage.setText(data.getAge());
            tvbitthday.setText(data.getBirthday());
            tvBloodtype.setText(data.getBlood());
            tvProfession.setText(data.getCareer());
            tvfamiliaydisease.setText(data.getIs_inherited_disease());//家族遗传病
            tvfamiliaydiseasecontent.setText(data.getInherited_disease_name());//疾病名称
            tvfamiliaypersoncontent.setText(data.getPatient());//huan bing ren
            tvisfamiliaythrowback.setText(data.getIs_hereditary());//shi fou ge dai
            isfapersondisease.setText(data.getIs_disease()); //ge ren shi fou zhong da ji bing
            fapersoncontent.setText(data.getDisease_name());//mingcheng
            ispersonheredity.setText(data.getIs_inherited());
            persontimeshow.setText(data.getDiagnose_time());
            pharmacynameshow.setText(data.getPharmacy_name());
            height.setText("".equals(data.getHeight()) ? "0cm" : data.getHeight());
            weight.setText("".equals(data.getWeight()) ? "0kg" : data.getWeight());
            isMarry.setText("".equals(data.getIs_marriage()) ? "未知" : data.getIs_marriage());
            isAllergy.setText("".equals(data.getAllergy()) ? "未知" : data.getAllergy());
            if (!"".equals(data.getAllergy())) {
                if ("是".equals(data.getAllergy())) {

                    allergyContentTittle.setVisibility(View.VISIBLE);
                } else {
                    allergyContentTittle.setVisibility(View.GONE);
                }
            }

            if (null != data.getIs_inherited_disease() && !"".equals(data.getIs_inherited_disease())) {
                if ("是".equals(data.getIs_inherited_disease())) {
                    tvfamiliaydiseasename.setVisibility(View.VISIBLE);
                    tvfamiliaydiseasecontent.setVisibility(View.VISIBLE);
                    tvfamiliayperson.setVisibility(View.VISIBLE);
                    tvfamiliaypersoncontent.setVisibility(View.VISIBLE);
                    tvfamiliaythrowback.setVisibility(View.VISIBLE);
                    tvisfamiliaythrowback.setVisibility(View.VISIBLE);
                } else {
                    tvfamiliaydiseasename.setVisibility(View.GONE);
                    tvfamiliaydiseasecontent.setVisibility(View.GONE);
                    tvfamiliayperson.setVisibility(View.GONE);
                    tvfamiliaypersoncontent.setVisibility(View.GONE);
                    tvfamiliaythrowback.setVisibility(View.GONE);
                    tvisfamiliaythrowback.setVisibility(View.GONE);
                }
            }

            if (null != data.getIs_disease() && !"".equals(data.getIs_disease())) {
                if ("是".equals(data.getIs_disease())) {
                    fapersonname.setVisibility(View.VISIBLE);
                    fapersoncontent.setVisibility(View.VISIBLE);
                    personheredity.setVisibility(View.VISIBLE);
                    ispersonheredity.setVisibility(View.VISIBLE);
                    persontime.setVisibility(View.VISIBLE);
                    persontimeshow.setVisibility(View.VISIBLE);
                    pharmacyname.setVisibility(View.VISIBLE);
                    pharmacynameshow.setVisibility(View.VISIBLE);

                } else {
                    fapersonname.setVisibility(View.GONE);
                    fapersoncontent.setVisibility(View.GONE);
                    personheredity.setVisibility(View.GONE);
                    ispersonheredity.setVisibility(View.GONE);
                    persontime.setVisibility(View.GONE);
                    persontimeshow.setVisibility(View.GONE);
                    pharmacyname.setVisibility(View.GONE);
                    pharmacynameshow.setVisibility(View.GONE);
                }
            }
            allergyContentTittle.setText("".equals(data.getAllergy_desc()) ? "在这里编辑过敏内容" : data.getAllergy_desc());
            verified.setText(data.getAuthentication_show());


            adapter.clear();
            adapter.addAll(data.getHealthPlanBeans());
            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onUpdateRecord(String data) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {

            initData();
        }
    }
}