package com.klavor.android.tools.permission;

/**
 * Created by Klavor on 2019/3/4.
 */
public interface OnRequestPermissionResultListener {
    void onRequestPermissionResult(String permission, int grantResult, boolean isDeniedAlway, boolean isLast);
}
