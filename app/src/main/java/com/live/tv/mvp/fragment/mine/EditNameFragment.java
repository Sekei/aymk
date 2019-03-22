package com.live.tv.mvp.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2017/11/29
 */

public class EditNameFragment extends BottomSheetDialogFragment {

    @BindView(R.id.tittle)
    TextView tittle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.hint)
    TextView hint;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.ok)
    TextView ok;


    public interface OKOnclickListener {
        void onOk(String state);
    }
    private OKOnclickListener onclickListener;
    public void setOkClickListener(OKOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

    private String name;
    Unbinder unbinder;
    Context context;

    public static EditNameFragment newInstance(String name, Context context) {
        Bundle args = new Bundle();
        EditNameFragment fragment = new EditNameFragment();
        fragment.name = name;
        fragment.context = context;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        etName.setText(name);
    }

    @OnClick({R.id.cancel, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.ok:
                String s = etName.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入用户名");
                    return;
                }
                onclickListener.onOk(s);
                break;

        }
    }


}
