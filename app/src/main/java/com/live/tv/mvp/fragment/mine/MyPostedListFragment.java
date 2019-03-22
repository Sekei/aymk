package com.live.tv.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.Note;
import com.live.tv.bean.PlateListBean;
import com.live.tv.dao.greendao.NoteDao;
import com.live.tv.mvp.adapter.communicate.MyNoteListAdapter;
import com.live.tv.mvp.base.BaseFragment;
import com.live.tv.mvp.presenter.mine.PostedListFragmentPresenter;
import com.live.tv.mvp.view.mine.IPostedListView;
import com.live.tv.util.editutils.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/8/1
 */

public class MyPostedListFragment extends BaseFragment<IPostedListView, PostedListFragmentPresenter> implements IPostedListView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    private MyNoteListAdapter mNoteListAdapter;
    private List<Note> noteList;
    private NoteDao noteDao;
    private int groupId;//分类ID
    private String groupName;


    public static MyPostedListFragment newInstance() {

        MyPostedListFragment fragment = new MyPostedListFragment();


        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_postedlist;
    }

    @Override
    public void initUI() {

        tvTitle.setText("我的帖子");
        tvRight.setVisibility(View.GONE);
       // tvRight.setText("新建");
        noteDao = new NoteDao(getActivity());

        recyclerView.addItemDecoration(new SpacesItemDecoration(0));//设置item间距

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//竖向列表
        recyclerView.setLayoutManager(layoutManager);

        mNoteListAdapter = new MyNoteListAdapter();
        mNoteListAdapter.setmNotes(noteList);
        recyclerView.setAdapter(mNoteListAdapter);

        mNoteListAdapter.setOnItemClickListener(new MyNoteListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Note note) {
                //showToast(note.getTitle());

                startPostedFragment(note);
            }
        });
        mNoteListAdapter.setOnItemLongClickListener(new MyNoteListAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final Note note) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("确定删除笔记？");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int ret = noteDao.deleteNote(note.getId());
                        if (ret > 0) {
                            ToastUtils.showToast(getActivity(), "删除成功");                            //TODO 删除笔记成功后，记得删除图片（分为本地图片和网络图片）
                            //获取笔记中图片的列表 StringUtils.getTextFromHtml(note.getContent(), true);
                            refreshNoteList();
                        }
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public PostedListFragmentPresenter createPresenter() {
        return new PostedListFragmentPresenter(getApp());
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

    //刷新笔记列表
    private void refreshNoteList() {
        noteList = noteDao.queryNotesAll(groupId);
        mNoteListAdapter.setmNotes(noteList);
        mNoteListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshNoteList();
    }

    @OnClick({R.id.ivLeft})

    public void Oncliclk(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }


    }

    /***
     * 获取数据
     * @param plateListBeenlist
     */
    @Override
    public void onPostedList(List<PlateListBean> plateListBeenlist) {

    }
}
