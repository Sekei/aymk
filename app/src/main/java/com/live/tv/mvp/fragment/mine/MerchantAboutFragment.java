package com.live.tv.mvp.fragment.mine;

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
import com.live.tv.mvp.presenter.mine.MerchantAboutPresenter;
import com.live.tv.mvp.view.mine.IMerchantAboutView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mac1010 on 2018/8/28.
 * 商家简介
 */

public class MerchantAboutFragment extends BaseFragment<IMerchantAboutView, MerchantAboutPresenter> implements IMerchantAboutView {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private Map<String,String> map=new HashMap<>();
    private int num=500;
    Unbinder unbinder;

    public static MerchantAboutFragment newInstance() {

        MerchantAboutFragment fragment = new MerchantAboutFragment();


        return fragment;

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_merchantabout;
    }

    @Override
    public void initUI() {
     tvTitle.setText("商家简介");
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

                tvNum.setText("限定字数(" + s.length() + "/500)");
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
    public MerchantAboutPresenter createPresenter() {
        return new MerchantAboutPresenter(getApp());
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

    @OnClick({R.id.ivLeft,R.id.tv_ok})
    public void OnClick(View veiw){
        switch (veiw.getId()){
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_ok:
                if (editText.getText().toString().equals("")){
                    ToastUtils.showToast(getActivity(),"请填写商家简介");
                    return;
                }
                map.put("member_id",userBean.getMember_id());
                map.put("member_token",userBean.getMember_token());
                map.put("house_service_desc",editText.getText().toString());
                map.put("house_service_id",userBean.getHouse_service_id());
                getPresenter().getCHangemessage(map);
                break;
        }
    }

    @Override
    public void onChangeSuccess(String str) {
        ToastUtils.showToast(getActivity(),str);
        finish();
    }
}
