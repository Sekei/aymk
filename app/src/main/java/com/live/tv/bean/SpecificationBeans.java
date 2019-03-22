package com.live.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taoh on 2018/5/7.
 */

public class SpecificationBeans implements Parcelable {

    private String specification_id;
    private String specification_value;
    private String is_delete;
    private String sort;
    private String create_time;
    private String update_time;
    private String parent_id;
    private String is_select;
    private List<SpecificationBeans> specificationBeans;

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }

    public String getSpecification_value() {
        return specification_value;
    }

    public void setSpecification_value(String specification_value) {
        this.specification_value = specification_value;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getIs_select() {
        return is_select;
    }

    public void setIs_select(String is_select) {
        this.is_select = is_select;
    }

    public List<SpecificationBeans> getSpecificationBeans() {
        return specificationBeans;
    }

    public void setSpecificationBeans(List<SpecificationBeans> specificationBeans) {
        this.specificationBeans = specificationBeans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.specification_id);
        dest.writeString(this.specification_value);
        dest.writeString(this.is_delete);
        dest.writeString(this.sort);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
        dest.writeString(this.parent_id);
        dest.writeString(this.is_select);
        dest.writeList(this.specificationBeans);
    }

    public SpecificationBeans() {
    }

    protected SpecificationBeans(Parcel in) {
        this.specification_id = in.readString();
        this.specification_value = in.readString();
        this.is_delete = in.readString();
        this.sort = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
        this.parent_id = in.readString();
        this.is_select = in.readString();
        this.specificationBeans = new ArrayList<SpecificationBeans>();
        in.readList(this.specificationBeans, SpecificationBeans.class.getClassLoader());
    }

    public static final Parcelable.Creator<SpecificationBeans> CREATOR = new Parcelable.Creator<SpecificationBeans>() {
        @Override
        public SpecificationBeans createFromParcel(Parcel source) {
            return new SpecificationBeans(source);
        }

        @Override
        public SpecificationBeans[] newArray(int size) {
            return new SpecificationBeans[size];
        }
    };
}
