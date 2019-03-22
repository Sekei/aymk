package com.live.tv.mvp.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.tv.Constants;
import com.ysjk.health.iemk.R;
import com.live.tv.bean.HealthFileBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Created by stone
 * @since 2018/1/12
 */

public class FileMoreAdapter extends RecyclerArrayAdapter<HealthFileBean> {
    public FileMoreAdapter(Context context, List<HealthFileBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    private int type = -1;

    public void setType(int type) {
        this.type = type;
    }

    public class ViewHolder extends BaseViewHolder<HealthFileBean> {
        private ImageView right;
        private CircleImageView avatar;
        private CheckBox check;
        private TextView name, sex, verified, age;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_file_more);
            right = $(R.id.right);
            check = $(R.id.check);
            avatar = $(R.id.avatar);
            name = $(R.id.name);
            sex = $(R.id.sex);
            verified = $(R.id.verified);
            age = $(R.id.age);

        }

        @Override
        public void setData(final HealthFileBean data) {
            super.setData(data);
            if (-1 == type) {
                right.setVisibility(View.VISIBLE);
                check.setVisibility(View.GONE);
            } else {

                right.setVisibility(View.GONE);
//                check.setVisibility(View.VISIBLE);

                if (data.getRelation().equals("自己")){
                    check.setVisibility(View.GONE);

                }else {
                    check.setVisibility(View.VISIBLE);
                }
            }
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onOpen();
                }
            });
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.setSelect(isChecked);

                }
            });


            Glide.with(getContext()).load(Constants.BASE_URL + data.getHead_image())
                    .error(R.drawable.home_doctor_ava)
                    .into(avatar);
            name.setText(data.getRecord_name());
            sex.setText(data.getSex());
            age.setText(data.getAge());

            if ("0".equals(data.getIs_record_authentication())) {
                verified.setText(R.string.not_certified);
                verified.setBackgroundResource(R.drawable.setbar_fh_gray);

            } else {
                verified.setText(R.string.certification);
                verified.setBackgroundResource(R.drawable.setbar_fh_two);
            }



            //age.setText(data.get);
        }
    }

    public void setOnClickListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private onClickListener onClickListener;

    public interface onClickListener {

        void onOpen();
    }

}
