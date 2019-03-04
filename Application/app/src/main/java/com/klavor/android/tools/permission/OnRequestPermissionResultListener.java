package com.klavor.android.tools.permission;

/**
 * Created by 054478 on 2019/3/4.
 */
public interface OnRequestPermissionResultListener {
    void onRequestPermissionResult(String permission, int grantResult, boolean isDeniedAlway, boolean isLast);
}
