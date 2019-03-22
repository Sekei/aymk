package com.live.tv.util.city;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/19
 */

public class OfficeBean {
    private String officeName;

    List<HospitalLevelBean> list;

    public String getOfficeName() {
        return officeName;
    }
    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public List<HospitalLevelBean> getList() {
        return list;
    }

    public void setList(List<HospitalLevelBean> list) {
        this.list = list;
    }
}
