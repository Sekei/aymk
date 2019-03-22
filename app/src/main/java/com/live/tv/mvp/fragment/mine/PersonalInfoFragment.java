package com.live.tv.mvp.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.UserBean;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.fragment.dialog.PublicEditFragment;
import com.live.tv.mvp.presenter.mine.PersonInfoPresenter;
import com.live.tv.mvp.view.mine.IPersonInfoView;
import com.live.tv.util.ImageFactory;
import com.live.tv.util.LoadingUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * @author Created by stone
 * @since 2018/1/16
 */

public class PersonalInfoFragment extends BaseFragment<IPersonInfoView, PersonInfoPresenter> implements IPersonInfoView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.invitation_code)
    TextView invitation_code;
    @BindView(R.id.all_bg)
    ConstraintLayout allBg;
    Unbinder unbinder;

    private String path = "";

    public static PersonalInfoFragment newInstance() {
        Bundle args = new Bundle();
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_personal_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText("个人资料");
    }

    @Override
    public void initData() {

        if (userBean != null) {
            Map<String, String> map = new HashMap<>();
            map.put("member_id", userBean.getMember_id());
            map.put("member_token", userBean.getMember_token());
            getPresenter().getPersonInfo(map);
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
    public PersonInfoPresenter createPresenter() {
        return new PersonInfoPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLeft, R.id.nameTittle, R.id.ivAvatar,R.id.phone})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivAvatar:
                show_img();
                break;

            case R.id.nameTittle:
                startEditNameFragment(name.getText().toString(), context);
                break;

            case R.id.phone:

                startpublicEditFragment(phone.getText().toString(),"phone","输入11位手机号","手机号",context);
                break;
        }
    }

    protected void show_img() {
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.boxing_storage_deny);
            return;
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                .needCamera(R.drawable.ic_boxing_camera_white)
                .withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, REQUEST_CODE);

    }

    public void startEditNameFragment(String s, Context context) {
        final EditNameFragment fragment = EditNameFragment.newInstance(s, context);
        fragment.setOkClickListener(new EditNameFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                name.setText(state);
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("member_nick_name", state);
                getPresenter().UpdatePersonInfo(map);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }
    public void startpublicEditFragment(String s,String type,String hint,String title, Context context) {
        final PublicEditFragment fragment = PublicEditFragment.newInstance(s,type,hint,title, context);
        fragment.setOkClickListener(new PublicEditFragment.OKOnclickListener() {
            @Override
            public void onOk(String state) {
                phone.setText(state);
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("member_phone", state);
                getPresenter().UpdatePersonInfo(map);
                fragment.dismiss();
            }
        });
        fragment.show(getFragmentManager(), PersonalInfoFragment.class.getSimpleName());

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    if (medias.get(0) instanceof ImageMedia) {
                        path = ((ImageMedia) medias.get(0)).getThumbnailPath();
                    } else {
                        path = medias.get(0).getPath();
                    }

                    BoxingMediaLoader.getInstance().displayThumbnail(ivAvatar, path, 480, 480);
                    upimg();
                }
                break;


        }
    }

    private void upimg() {
        String outFilePath = context.getExternalCacheDir().getPath() + System.currentTimeMillis() + ".jpg";
        ImageFactory imageFactory = new ImageFactory();
        Bitmap ratio = imageFactory.ratio(path, 480, 480);
        try {
            imageFactory.storeImage(ratio, outFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ratio != null) {
                ratio.recycle();
                ratio = null;
            }
        }
        File _file = new File(outFilePath);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", _file.getName(), RequestBody.create(MediaType.parse("image/*"), _file))
                .build();
        getPresenter().uploadImg(requestBody);
        LoadingUtil.showLoading(context, getResources().getString(R.string.img_put));

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        errorHandle(e);
    }

    @Override
    public void PersonInfo(UserBean data) {

        if (data != null) {

            Glide.with(context).load(Constants.BASE_URL + data.getMember_head_image())
                    .error(R.drawable.pic_defaults)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatar);


            String name_str = !"".equals(data.getMember_nick_name())?data.getMember_nick_name() : "请填写你的昵称";
            String phone_str = !"".equals(data.getMember_account()) ? data.getMember_account() : "请填写你的手机号";
            invitation_code.setText(data.getInvitation_code());
            name.setText(name_str);
            phone.setText(phone_str);

        }
    }

    @Override
    public void UpdatePersonInfo(String data) {

        ToastUtils.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void uploadImg(String data) {
        LoadingUtil.hideLoading();
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("member_head_image", data);
        getPresenter().UpdatePersonInfo(map);

    }
}
