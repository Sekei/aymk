package com.live.tv.mvp.fragment.huanxin.runtimepermissions;

/**
 * Created by taoh on 2018/3/31.
 */

/**
 * Enum class to handle the different states
 * of permissions since the PackageManager only
 * has a granted and denied state.
 */
enum Permissions {
    GRANTED,
    DENIED,
    NOT_FOUND
}