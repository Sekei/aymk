package com.live.tv.mvp.fragment.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.base.util.ToastUtils;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.GoodsSpecificationBeans;
import com.live.tv.mvp.adapter.dialogadapter.DialogAddCartAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by taoh on 2018/5/8.
 */

public class DialogAddCartFragment extends DialogFragment {


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.stock)
    TextView stock;
    @BindView(R.id.select)
    TextView select;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.jia)
    ImageView jia;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.jian)
    ImageView jian;
    @BindView(R.id.btnAction)
    TextView btnAction;
    @BindView(R.id.addCar)
    TextView addCar;
    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private DialogAddCartAdapter adapter;
    private GoodsBean  goodsBean;

    private int count = 1;
    private int cks;
    private int addOrBuy;
    private Map<String, String> kindsId = new HashMap<>();//map可自动排序
    private String ids = "";
    private String specification_id = "";


    public SelectListener selectListener;

    public interface SelectListener {
        void OnSelectListener(String ids, int type);

        void OnPay(String goods_num, String specification_id, int type);
    }

    public void setOnSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_add_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        EventBus.getDefault().register(this);
        goodsBean = getArguments().getParcelable("goodsBean");
        addOrBuy = getArguments().getInt("addOrBuy");
        if (addOrBuy == 1) {
            btnAction.setText("加入购物车");
        } else if (addOrBuy == 2) {
            btnAction.setText("立即购买");
        } else if (addOrBuy == 3) {
            addCar = (TextView) view.findViewById(R.id.addCar);
            addCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPay(1);

                }
            });
            addCar.setVisibility(View.VISIBLE);
            pay = (TextView) view.findViewById(R.id.pay);
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPay(2);

                }
            });
            pay.setVisibility(View.VISIBLE);
            btnAction.setVisibility(View.GONE);
        }

      if (goodsBean.getSpecificationBeans()!=null){

          adapter = new DialogAddCartAdapter(getContext(), goodsBean.getSpecificationBeans());
          recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
          recyclerView.setAdapter(adapter);
          kindsId.clear();
          for (int i = 0; i < adapter.getAllData().size(); i++) {
              String s = goodsBean.getSpecificationBeans().get(i).getSpecificationBeans().get(0).getSpecification_id() + "";
              //adapter.notifyDataSetChanged();
              kindsId.put(i + "", s);
              if (isAll()) {
                  if (kindsId.size() > 1) {
                      ids = kindsId.get(0 + "");
                      for (int j = 1; j < kindsId.size(); j++) {
                          ids = ids + "," + kindsId.get(j + "");
                      }
                      selectListener.OnSelectListener(ids, addOrBuy);
                  } else {
                      ids = kindsId.get(0 + "");
                      selectListener.OnSelectListener(ids, addOrBuy);
                  }

              }
          }
          adapter.select_id = ids;
          adapter.notifyDataSetChanged();

          adapter.setSelectListener(new DialogAddCartAdapter.SelectListener() {
              @Override
              public void OnSelectListener(String id, int position) {
                  adapter.select_id = id;
                  adapter.notifyItemChanged(position);
                  kindsId.put(position + "", id);
                  if (isAll()) {
                      if (kindsId.size() > 1) {
                          ids = kindsId.get(0 + "");
                          for (int i = 1; i < kindsId.size(); i++) {
                              ids = ids + "," + kindsId.get(i + "");
                          }
                          selectListener.OnSelectListener(ids, addOrBuy);
                      } else {
                          ids = kindsId.get(0 + "");
                          selectListener.OnSelectListener(ids, addOrBuy);
                      }

                  }

              }
          });
      }



        Glide.with(getContext())
                .load(Constants.BASE_URL + goodsBean.getGoods_img())
                .placeholder(R.drawable.pic_defaults)
                .into(img);

        price = (TextView) view.findViewById(R.id.price);
        price.setText("¥" + goodsBean.getGoods_now_price());
        stock = (TextView) view.findViewById(R.id.stock);
        stock.setText("库存：" + goodsBean.getGoods_stock());

        try {
            cks = Integer.parseInt(goodsBean.getGoods_stock());

        }catch (Exception e){
            e.printStackTrace();

        }
        select = (TextView) view.findViewById(R.id.select);

        if (goodsBean.getGoodsSpecificationBeans()!=null&&goodsBean.getGoodsSpecificationBeans().size() > 0) {
            select.setText("请选择：规格");
        }
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(num.getText().toString().trim());
                count++;
                if (count > cks) {
                    count--;
                    ToastUtils.showToast(getActivity().getApplicationContext(), "不能大于库存");
                }
                num.setText(String.valueOf(count));
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(num.getText().toString().trim());
                count--;
                if (count < 1) {
                    count = 1;
                    ToastUtils.showToast(getActivity().getApplicationContext(), "数量不能小于1");
                }
                num.setText(String.valueOf(count));
            }
        });

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPay(addOrBuy);

            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startDownAnimation(view);
            }
        });
        startUpAnimation(view);
        return view;

    }

    private void startUpAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);
    }

    private void startDownAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(slide);

}


    private void onPay(int type) {
        if (cks == 0) {
            ToastUtils.showToast(getActivity().getApplicationContext(), "库存不足");
            return;
        }
        if (isAll()) {
            // selectListener.OnPay(num.getText().toString(), specification_id,type);
            selectListener.OnPay(num.getText().toString(), specification_id, type);
            dismiss();
        } else {
            ToastUtils.showToast(getContext().getApplicationContext(), "请选择规格");
        }
    }

    private synchronized boolean isAll() {
        if (goodsBean.getSpecificationBeans().size() > 0) {
            if (goodsBean.getSpecificationBeans().size() == kindsId.size()) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM; // 显示在底部
            params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度填充满屏
            window.setAttributes(params);
            window.setBackgroundDrawable( new ColorDrawable( 0x00000000 ) );
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
            dialog.getWindow().setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );


        }

    }
GoodsSpecificationBeans  goodsSpecificationBeans;
    @Subscribe
    public void onEventMainThread(GoodsSpecificationBeans event) {
        Glide.with(getContext())
                .load(Constants.BASE_URL + event.getSpecification_img())
                .placeholder(R.drawable.pic_defaults)
                .into(img);

        cks = Integer.parseInt(event.getSpecification_stock());
        if (cks > 0) {
            num.setText("1");
            jian.setEnabled(true);
        } else {
            num.setText("0");
            jian.setEnabled(false);
        }
        specification_id = event.getSpecification_id() + "";
        price.setText("¥" + event.getSpecification_price());
        stock.setText("库存：" + event.getSpecification_stock() + "件");
        select.setText("已选择：" + event.getSpecification_names());
        goodsSpecificationBeans=event;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.jia, R.id.jian, R.id.btnAction, R.id.addCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jia:
                break;
            case R.id.jian:
                break;
            case R.id.btnAction:
                break;
            case R.id.addCar:
                break;
        }
    }


}
