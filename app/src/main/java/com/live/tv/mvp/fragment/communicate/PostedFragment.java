package com.live.tv.mvp.fragment.communicate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.Group;
import com.live.tv.bean.Note;
import com.live.tv.dao.greendao.GroupDao;
import com.live.tv.dao.greendao.NoteDao;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.communicate.PostedFragmentPresenter;
import com.live.tv.mvp.view.communicate.IPostedFragmentView;
import com.live.tv.uditTextandImage.RichTextEditor;
import com.live.tv.util.ImageFactory;
import com.live.tv.util.editutils.CommonUtil;
import com.live.tv.util.editutils.ImageUtils;
import com.live.tv.util.editutils.SDCardUtil;
import com.live.tv.util.editutils.SoftKeyboard;
import com.live.tv.util.editutils.StringUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.live.tv.Constants.REQUEST_CODE_CHOOSE;
import static com.ysjk.health.iemk.R.id.et_new_content;
import static com.ysjk.health.iemk.R.id.et_new_title;
import static com.ysjk.health.iemk.R.id.tv_new_group;
import static com.ysjk.health.iemk.R.id.tv_new_time;

/**
 * @author Created by stone
 * @since 2018/7/31
 */

public class PostedFragment extends BaseFragment<IPostedFragmentView, PostedFragmentPresenter> implements IPostedFragmentView {
    @BindView(et_new_title)
    EditText etNewTitle;
    @BindView(tv_new_time)
    TextView tvNewTime;
    @BindView(tv_new_group)
    TextView tvNewGroup;
    @BindView(et_new_content)
    RichTextEditor etNewContent;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_textcolor)
    ImageView ivTextcolor;
    @BindView(R.id.iv_textsize)
    ImageView ivTextsize;
    @BindView(R.id.ll_bottom)
    ConstraintLayout llBottom;
    @BindView(R.id.ct_content)
    ConstraintLayout ctContent;
    @BindView(R.id.iv_tvbig)
    ImageView ivTvbig;
    @BindView(R.id.iv_tvnomal)
    ImageView ivTvnomal;
    @BindView(R.id.iv_tvsmall)
    ImageView ivTvsmall;
    @BindView(R.id.ll_textsize)
    ConstraintLayout llTextsize;
    @BindView(R.id.iv_black)
    ImageView ivBlack;
    @BindView(R.id.iv_grey)
    ImageView ivGrey;
    @BindView(R.id.iv_green)
    ImageView ivGreen;
    @BindView(R.id.iv_red)
    ImageView ivRed;
    @BindView(R.id.iv_bule)
    ImageView ivBule;
    @BindView(R.id.iv_yellow)
    ImageView ivYellow;
    @BindView(R.id.ll_textcolor)
    ConstraintLayout llTextcolor;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.et_zuozhe)
    EditText editText;
//    @BindView(R.id.content_new)
//    LinearLayout contentNew;

    private GroupDao groupDao;
    private NoteDao noteDao;
    private Note note;//笔记对象
    private String myTitle;
    private String myContent;
    private String myGroupName;
    private String myNoteTime;
    private int flag;//区分是新建笔记还是编辑笔记
    private static final int cutTitleLength = 20;//截取的标题长度
    private ProgressDialog loadingDialog;
    private ProgressDialog insertDialog;
    private int screenWidth;
    private int screenHeight;
    private Subscription subsLoading;
    private Subscription subsInsert;
    private InputMethodManager imm;
    private SoftKeyboard softKeyboard;
    private static final int IMAGE = 0X02;
    private String title;
    private String plateId;


    Unbinder unbinder;
    private ArrayList<File> files;
    private String titlename;

    public static PostedFragment newInstance(Note note, int flage) {

        PostedFragment fragment = new PostedFragment();
        fragment.note = note;
        fragment.flag = flage;

        return fragment;


    }

    public static PostedFragment newInstance(int flag,String plateId) {

        PostedFragment fragment = new PostedFragment();
        fragment.note = new Note();
        fragment.flag = flag;
        fragment.plateId=plateId;
        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getRootViewId() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return R.layout.fragment_posted;
    }

    @Override
    public void initUI() {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("发送");
        tvRight.setTextColor(getResources().getColor(R.color.tx_4));
        initView();

    }

    @Override
    public void initData() {

    }


    private void initView() {

        groupDao = new GroupDao(getActivity());
        noteDao = new NoteDao(getActivity());
        // note = new Note();

        screenWidth = CommonUtil.getScreenWidth(getActivity());
        screenHeight = CommonUtil.getScreenHeight(getActivity());

        insertDialog = new ProgressDialog(getActivity());
        insertDialog.setMessage("正在插入图片...");
        insertDialog.setCanceledOnTouchOutside(false);

        // 图片删除事件
        etNewContent.setOnRtImageDeleteListener(new RichTextEditor.OnRtImageDeleteListener() {

            @Override
            public void onRtImageDelete(String imagePath) {
                if (!TextUtils.isEmpty(imagePath)) {
                    boolean isOK = SDCardUtil.deleteFile(imagePath);
                    if (isOK) {
                        showToast("删除成功：" + imagePath);
                    }
                }
            }
        });
        // 图片点击事件
        etNewContent.setOnRtImageClickListener(new RichTextEditor.OnRtImageClickListener() {
            @Override
            public void onRtImageClick(String imagePath) {
                myContent = getEditData();
                if (!TextUtils.isEmpty(myContent)) {
                    List<String> imageList = StringUtils.getTextFromHtml(myContent, true);
                    if (!TextUtils.isEmpty(imagePath)) {
                        int currentPosition = imageList.indexOf(imagePath);
                        showToast("点击图片：" + currentPosition + "：" + imagePath);
                    }
                }
            }
        });

        openSoftKeyInput();//打开软键盘显示

        //0新建，1编辑
        if (flag == 1) {//编辑
            myTitle = note.getTitle();
            myContent = note.getContent();
            myNoteTime = note.getCreateTime();
            Group group = groupDao.queryGroupById(note.getGroupId());
            myGroupName = group.getName();

            loadingDialog = new ProgressDialog(getActivity());
            loadingDialog.setMessage("数据加载中...");
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.show();

            tvTitle.setText("编辑帖子");
            tvNewTime.setText(note.getCreateTime());
            tvNewGroup.setText(myGroupName);
            etNewTitle.setText(note.getTitle());
            plateId=note.getPlateId();
            etNewContent.post(new Runnable() {
                @Override
                public void run() {
                    //showEditData(note.getContent());
                    etNewContent.clearAllLayout();
                    showDataSync(note.getContent());
                }
            });
        } else {
            tvTitle.setText("新建帖子");
            if (myGroupName == null || "全部帖子".equals(myGroupName)) {
                myGroupName = "默认笔记";
            }
            tvNewGroup.setText(myGroupName);
            myNoteTime = CommonUtil.date2string(new Date());
            tvNewTime.setText(myNoteTime);
        }

    }


    @Override
    public PostedFragmentPresenter createPresenter() {
        return new PostedFragmentPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    /**
     * 打开软键盘
     */
    private void openSoftKeyInput() {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        //boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        if (!imm.isActive()) {
            etNewContent.requestFocus();
            //第二个参数可设置为0
            //imm.showSoftInput(et_content, InputMethodManager.SHOW_FORCED);//强制显示
            imm.showSoftInputFromInputMethod(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.SHOW_FORCED);
        }

        /*getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (llBottom.getVisibility() == View.GONE) {
                    llBottom.setVisibility(View.VISIBLE);

                }

            }
        });*/
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
        //  softKeyboard.unRegisterSoftKeyboardCallback();

    }

    /**
     * 退出处理
     */
    private void dealwithExit() {
        String noteTitle = etNewTitle.getText().toString();
        String noteContent = getEditData();
        String groupName = tvNewGroup.getText().toString();
        String noteTime = tvNewTime.getText().toString();
        if (flag == 0) {//新建笔记
            if (noteTitle.length() > 0 || noteContent.length() > 0) {
                saveNoteData(false);
            }
        } else if (flag == 1) {//编辑笔记
            if (!noteTitle.equals(myTitle) || !noteContent.equals(myContent)
                    || !groupName.equals(myGroupName) || !noteTime.equals(myNoteTime)) {
                saveNoteData(false);
            }
        }
        finish();
    }


    /**
     * 保存数据,=0销毁当前界面，=1不销毁界面，为了防止在后台时保存笔记并销毁，应该只保存笔记
     */
    private void saveNoteData(boolean isBackground) {
        String noteTitle = etNewTitle.getText().toString();
        String noteContent = getEditData();
        String groupName = tvNewGroup.getText().toString();
        String noteTime = tvNewTime.getText().toString();
        String author="";//作者
        String title;
        Group group = groupDao.queryGroupByName(myGroupName);
        if (group != null) {
            if (noteTitle.length() == 0) {//如果标题为空，则截取内容为标题
                if (noteContent.length() > cutTitleLength) {
                    noteTitle = noteContent.substring(0, cutTitleLength);
                } else if (noteContent.length() > 0 && noteContent.length() <= cutTitleLength) {
                    noteTitle = noteContent;
                }
            }
            int groupId = group.getId();
            titlename=noteTitle;
            note.setPlateId(plateId);
            note.setTitle(noteTitle);
            note.setContent(noteContent);
            note.setGroupId(groupId);
            note.setGroupName(groupName);
            note.setType(2);
            note.setBgColor("#FFFFFF");
            note.setIsEncrypt(0);
            note.setCreateTime(CommonUtil.date2string(new Date()));
            if (flag == 0) {//新建笔记
                if (noteTitle.length() == 0 && noteContent.length() == 0) {
                    if (!isBackground) {
                        Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    long noteId = noteDao.insertNote(note);
                    //Log.i("", "noteId: "+noteId);
                    //查询新建笔记id，防止重复插入
                    note.setId((int) noteId);
                    flag = 1;//插入以后只能是编辑
                    if (!isBackground) {
                        Intent intent = new Intent();
                        getActivity().setResult(RESULT_OK, intent);
                        //finish();
                        author=editText.getText().toString();
                        if (author!=null&&!author.equals("")){

                          title="<h3>"+noteTitle+"</h3><h5>"+CommonUtil.date2string(new Date())+"</h5><h5>"+author+"</h5><br>";

                        }else {
                            title="<h3>"+noteTitle+"</h3><h5>"+CommonUtil.date2string(new Date())+"</h5><h5>未知</h5><br>";

                        }
                        setupData(title+noteContent);
                    }
                }
            } else if (flag == 1) {//编辑笔记
                if (!noteTitle.equals(myTitle) || !noteContent.equals(myContent)
                        || !groupName.equals(myGroupName) || !noteTime.equals(myNoteTime)) {
                    noteDao.updateNote(note);
                }
                if (!isBackground) {
                    if (author!=null&&!author.equals("")){

                        title="<h3>"+noteTitle+"</h3><h5>"+CommonUtil.date2string(new Date())+"</h5><h5>"+author+"</h5><br>";

                    }else {
                        title="<h3>"+noteTitle+"</h3><h5>"+CommonUtil.date2string(new Date())+"</h5><h5>未知</h5><br>";

                    }
                    setupData(title+noteContent);
                }
            }
        }
    }

    /**
     * 负责处理编辑数据提交等事宜，请自行实现
     */
    private String getEditData() {
        List<RichTextEditor.EditData> editList = etNewContent.buildEditData();
        StringBuffer content = new StringBuffer();
        for (RichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {
                content.append(itemData.inputStr);
            } else if (itemData.imagePath != null) {
                content.append("<img height=\"auto\" ;width=\"100%\"  src=\"").append(itemData.imagePath).append("\"/>");
            }
        }
        return content.toString();
    }

    @OnClick({R.id.tvRight, R.id.ivLeft, R.id.iv_black,
            R.id.iv_grey, R.id.iv_bule, R.id.iv_green, R.id.iv_red, R.id.iv_yellow, R.id.iv_tvbig,
            R.id.iv_tvnomal, R.id.iv_tvsmall, R.id.iv_image, R.id.iv_textcolor, R.id.iv_textsize})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tvRight:
                saveNoteData(false);
                break;
            case R.id.ivLeft:
                closeSoftKeyInput();//关闭软键盘
                dealwithExit();
                break;
            case R.id.iv_image:
                closeSoftKeyInput();//关闭软键盘
                callGallery();
                // show_img(IMAGE);
                if (llTextcolor.getVisibility() == View.VISIBLE) {
                    llTextcolor.setVisibility(View.GONE);
                }
                if (llTextsize.getVisibility() == View.VISIBLE) {
                    llTextsize.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_textsize:
                if (llTextcolor.getVisibility() == View.VISIBLE) {
                    llTextcolor.setVisibility(View.GONE);
                }
                llTextsize.setVisibility(View.VISIBLE);

                break;
            case R.id.iv_textcolor:
                if (llTextsize.getVisibility() == View.VISIBLE) {
                    llTextsize.setVisibility(View.GONE);
                }
                llTextcolor.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_tvbig:

                etNewContent.setRtTextSize(Constants.SIZE_18);
                llTextsize.setVisibility(View.GONE);
                break;
            case R.id.iv_tvsmall:

                etNewContent.setRtTextSize(Constants.SIZE_14);
                llTextsize.setVisibility(View.GONE);

                break;
            case R.id.iv_tvnomal:

                etNewContent.setRtTextSize(Constants.SIZE_16);
                llTextsize.setVisibility(View.GONE);

                break;
            case R.id.iv_black:
                etNewContent.setRtTextColor(Constants.COLOR_BLACK);
                llTextcolor.setVisibility(View.GONE);

                break;
            case R.id.iv_green:
                etNewContent.setRtTextColor(Constants.COLOR_GREEN);
                llTextcolor.setVisibility(View.GONE);

                break;
            case R.id.iv_grey:
                etNewContent.setRtTextColor(Constants.COLOR_GRAY);
                llTextcolor.setVisibility(View.GONE);

                break;
            case R.id.iv_red:
                etNewContent.setRtTextColor(Constants.COLOR_RED);
                llTextcolor.setVisibility(View.GONE);

                break;
            case R.id.iv_yellow:
                etNewContent.setRtTextColor(Constants.COLOR_YELLOW);
                llTextcolor.setVisibility(View.GONE);
                break;
            case R.id.iv_bule:
                etNewContent.setRtTextColor(Constants.COLOR_BLUE);
                llTextcolor.setVisibility(View.GONE);
                break;

        }


    }

    /**
     * 关闭软键盘
     */
    private void closeSoftKeyInput() {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        //boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            //imm.hideSoftInputFromInputMethod();//据说无效
            //imm.hideSoftInputFromWindow(et_content.getWindowToken(), 0); //强制隐藏键盘
            //如果输入法在窗口上已经显示，则隐藏，反之则显示
            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 调用图库选择
     */
    private void callGallery() {
        Matisse.from(this)
                .choose(MimeType.allOf())//图片类型
                .countable(true)//true:选中后显示数字;false:选中后显示对号
                .maxSelectable(3)//可选的最大数
                .capture(true)//选择照片时，是否显示拍照
                .thumbnailScale(0.85f)//缩放比例
                .captureStrategy(new CaptureStrategy(true, "com.ysjk.health.iemk.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .imageEngine(new GlideEngine())//图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE);//
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == 1) {
                    //处理调用系统图库
                } else if (requestCode == REQUEST_CODE_CHOOSE) {
                    //异步方式插入图片
                    insertImagesSync(data);
                }
            }
        }
    }


    /**
     * 异步方式插入图片
     *
     * @param data
     */

    private void insertImagesSync(final Intent data) {
        insertDialog.show();
        //  subsInsert=
        upImage(data);
    }


    /**
     * 显示吐司
     **/
    public void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


    /**
     * 异步方式显示数据
     *
     * @param html
     */
    private void showDataSync(final String html) {


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> ob) throws Exception {

                showEditData(ob, html);


            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(String text) {

                        if (text.contains("<img") && text.contains("src=")) {
                            //imagePath可能是本地路径，也可能是网络地址
                            String imagePath = StringUtils.getImgSrc(text);
                            //插入空的EditText，以便在图片前后插入文字
                            etNewContent.addEditTextAtIndex(etNewContent.getLastIndex(), "");
                            etNewContent.addImageViewAtIndex(etNewContent.getLastIndex(), imagePath);
                        } else {
                            etNewContent.addEditTextAtIndex(etNewContent.getLastIndex(), text);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                        showToast("解析错误：图片不存在或已损坏");
                    }

                    @Override
                    public void onComplete() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                        }
                        //在图片全部插入完毕后，再插入一个EditText，防止最后一张图片后无法插入文字
                        etNewContent.addEditTextAtIndex(etNewContent.getLastIndex(), "");
                    }
                });

    }

    /**
     * 显示数据
     */
    protected void showEditData(ObservableEmitter<? super String> subscriber, String html) {
        try {
            List<String> textList = StringUtils.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                subscriber.onNext(text);
            }
            subscriber.onComplete();
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }

    @Override
    public void onUploadImgs(final String[] data) {
        Log.e("-------------------", "onUploadImgs>>>" + data.length);

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> sub) throws Exception {
                try {
                    etNewContent.measure(0, 0);
                    // 可以同时插入多张图片
                    for (int i = 0; i < data.length; i++) {

                        sub.onNext(Constants.BASE_URL + data[i]);
                    }
                    sub.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    sub.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String imagePath) {
                        etNewContent.insertImage(imagePath, etNewContent.getMeasuredWidth());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (insertDialog != null && insertDialog.isShowing()) {
                            insertDialog.dismiss();
                        }
                        showToast("图片插入失败:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (insertDialog != null && insertDialog.isShowing()) {
                            insertDialog.dismiss();
                        }
                        showToast("图片插入成功");
                    }
                });


    }

    /***
     * 上传成功的回调
     * @param data
     */
    @Override
    public void onpostsuccess(String data) {
        ToastUtils.showToast(getActivity(),"发帖成功");
        finish();
    }


    //上传图片
    private void upImage(final Intent data) {
        new Thread() {
            @Override
            public void run() {
                files = new ArrayList<>();
                try {
                    etNewContent.measure(0, 0);
                    List<Uri> mSelected = Matisse.obtainResult(data);

                    // 可以同时插入多张图片
                    for (Uri imageUri : mSelected) {
                        String imagePath = SDCardUtil.getFilePathFromUri(getActivity(), imageUri);
                        Log.e("image", "###path1=" + imagePath);
                        Bitmap bitmap = ImageUtils.getSmallBitmap(imagePath, screenWidth, screenHeight);//压缩图片
                        //bitmap = BitmapFactory.decodeFile(imagePath);
                        imagePath = SDCardUtil.saveToSdCard(bitmap);
                        //Log.e(TAG, "###imagePath="+imagePath);
                        Log.e("image", "###path2=" + imagePath);
                        File _file = new File(imagePath);

                        files.add(_file);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                //构建body
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("path", "/images/member");
                for (int i = 0; i < files.size(); i++) {
                    builder.addFormDataPart("file", files.get(i).getName(), RequestBody.create(MediaType.parse("image/jpeg"), files.get(i)));
                }
                RequestBody requestBody = builder.build();
                getPresenter().uploadImgs(requestBody);


            }
        }.start();


    }

    private void setupData(String content) {
        Map<String, String> map = new HashMap<>();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("post_url", content);
        map.put("plate_id",plateId);
        if (titlename!=null&&!titlename.equals("")){
            map.put("post_title",titlename);
        }else {
            map.put("post_title","爱医美康");
        }
        try {
        map.put("doctor_id", userBean.getDoctorBean().getDoctor_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
        getPresenter().getpost(map);


    }

}
