package com.live.tv.util;

import android.content.Context;

import com.android.tu.loadingdialog.LoadingDailog;

/**
 * @author daiyunchao
 * @date 2017/11/12.
 */

public class LoadingUtil {

    private static LoadingDailog dialog = null;

    public static void showLoading(Context mContext, String msg) {
        if (dialog != null) {
            dialog.dismiss();
        }
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setMessage(msg)
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();
        if (dialog != null)
            dialog.show();
    }

    public static void showLoading(Context mContext) {
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setCancelable(true)
                .setMessage("")
                .setCancelOutside(true);
        dialog = loadBuilder.create();
        dialog.show();
    }

    public static void showLoadingNew(Context mContext, String msg) {
        if (dialog != null){
            dialog.show();
        }else{
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                    .setCancelable(false)
                    .setMessage(msg)
                    .setCancelOutside(false);
            dialog = loadBuilder.create();
            dialog.show();
        }

    }


    public static void hideLoading() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }

    }
}
