package com.live.tv.mvp.fragment.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.home.ComplaintPresenter;
import com.live.tv.mvp.view.home.IComplaintView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/28.
 */

public class ComplaintFragment extends BaseFragment<IComplaintView, ComplaintPresenter> implements IComplaintView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tvname)
    EditText tvname;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tvnumber)
    EditText tvnumber;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.tv_shownum)
    TextView tvShownum;
    @BindView(R.id.tv_complant)
    TextView tvComplant;
    private int num=500;
    private Map<String,String> map=new HashMap<>();
    private String houseId;
    Unbinder unbinder;
public static ComplaintFragment newInstance(String houseserviceid){

    Bundle args = new Bundle();

    ComplaintFragment fragment = new ComplaintFragment();
    fragment.houseId=houseserviceid;
    fragment.setArguments(args);
    return fragment;
}
    @Override
    public int getRootViewId() {
        return R.layout.fragment_complaint_detials;
    }
    @Override
    public void initUI() {
        ivLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("投诉");

        editText.addTextChangedListener(new TextWatcher() {
            private CharSequence wordNum;//记录输入的字数
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wordNum = s;//实时记录输入的字数
            }

            @Override
            public void afterTextChanged(Editable s) {

                tvShownum.setText("限定字数(" + s.length() + "/500)");
                selectionStart = editText.getSelectionStart();
                selectionEnd = editText.getSelectionEnd();
                if (wordNum.length() > num) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    editText.setText(s);
                    editText.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public ComplaintPresenter createPresenter() {
        return new ComplaintPresenter(getApp());
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

    @OnClick({R.id.ivLeft,R.id.tv_complant})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:

                finish();

                break;
            case R.id.tv_complant:

                if (editText.getText().toString().equals("")){
                    ToastUtils.showToast(getActivity(),"投诉内容不能为空");
                    return;
                }
                if (tvnumber.getText().toString().equals("")){
                    ToastUtils.showToast(getActivity(),"请输入您的手机号");
                    return;
                }
                if (tvname.getText().toString().equals("")){
                    ToastUtils.showToast(getActivity(),"请输入您的昵称");
                    return;
                }


                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                map.put("house_service_id",houseId);
                map.put("complain_desc",editText.getText().toString());
                map.put("complain_mobile",tvnumber.getText().toString());
                map.put("member_nick_name",tvname.getText().toString());
                getPresenter().getinsertComplains(map);
                break;


        }
    }

    @Override
    public void oninsertComplains(String str) {
        ToastUtils.showToast(getActivity(),str);
        finish();
    }
}
