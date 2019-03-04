package com.klavor.android.tools.permission;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by 054478 on 2019/3/4.
 */
public class PermissionsFragment extends Fragment implements OnRequestPermissionResultListener {

    protected static final int REQUEST_CODE_PERMISSION = 2048;

    private OnRequestPermissionResultListener mOnRequestPermissionResultListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            int length = permissions.length;
            int i = 0;
            for (String permission : permissions) {
                int grantResult = grantResults[i++];
                length--;
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    onRequestPermissionResult(permission, grantResult, false, length == 0);
                } else {
                    boolean shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale(permission);
                    onRequestPermissionResult(permission, grantResult, !shouldShowRequestPermissionRationale, length == 0);
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void requestPermissions(String[] permissions, OnRequestPermissionResultListener l) {
        mOnRequestPermissionResultListener = l;
        requestPermissions(permissions, REQUEST_CODE_PERMISSION);
    }

    @Override
    public void onRequestPermissionResult(String permission, int grantResult, boolean isDeniedAlway, boolean isLast) {
        if (mOnRequestPermissionResultListener != null) {
            mOnRequestPermissionResultListener.onRequestPermissionResult(permission, grantResult, isDeniedAlway, isLast);
        }
        if (isLast) {
            mOnRequestPermissionResultListener = null;
        }
    }
}
