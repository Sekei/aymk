package com.live.tv.bean;

/**
 * Created by taoh on 2018/5/9.
 */

public class InsertOrderGoodBean {


    /**
     * goods_id : 1
     * goods_mark : 0
     * goods_num : 1
     * specification_id : 1
     */

    private String goods_id;
    private String goods_mark;
    private String goods_num;
    private String specification_id;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_mark() {
        return goods_mark;
    }

    public void setGoods_mark(String goods_mark) {
        this.goods_mark = goods_mark;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }
}
