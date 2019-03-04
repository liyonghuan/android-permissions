package com.klavor.android.tools.permission;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Klavor on 2019/3/4.
 */
public class PermissionsHelper {
    private PermissionsHelper() {

    }

    public static void requestPermissions(FragmentActivity activity, @NonNull String[] permissions) {
        if (activity instanceof OnRequestPermissionResultListener) {
            requestPermissions(activity, permissions, (OnRequestPermissionResultListener) activity);
        } else {
            requestPermissions(activity, permissions, null);
        }
    }

    public static void requestPermissions(FragmentActivity activity, @NonNull String[] permissions, OnRequestPermissionResultListener l) {
        Permissions p = new Permissions(activity);
        p.requstPermissions(permissions, l);
    }

    public static void requestPermission(FragmentActivity activity, @NonNull String permission) {
        if (activity instanceof OnRequestPermissionResultListener) {
            requestPermission(activity, permission, (OnRequestPermissionResultListener) activity);
        } else {
            requestPermission(activity, permission, null);
        }
    }

    public static void requestPermission(FragmentActivity activity, @NonNull String permission, OnRequestPermissionResultListener l) {
        String[] permissions = new String[1];
        permissions[0] = permission;
        requestPermissions(activity, permissions, l);
    }

    public static void requestPermissions(Fragment fragment, @NonNull String[] permissions) {
        if (fragment instanceof OnRequestPermissionResultListener) {
            requestPermissions(fragment, permissions, (OnRequestPermissionResultListener) fragment);
        } else {
            requestPermissions(fragment, permissions, null);
        }
    }

    public static void requestPermissions(Fragment fragment, @NonNull String[] permissions, OnRequestPermissionResultListener l) {
        Permissions p = new Permissions(fragment);
        p.requstPermissions(permissions, l);
    }

    public static void requestPermission(Fragment fragment, @NonNull String permission) {
        if (fragment instanceof OnRequestPermissionResultListener) {
            requestPermission(fragment, permission, (OnRequestPermissionResultListener) fragment);
        } else {
            requestPermission(fragment, permission, null);
        }
    }

    public static void requestPermission(Fragment fragment, @NonNull String permission, OnRequestPermissionResultListener l) {
        String[] permissions = new String[1];
        permissions[0] = permission;
        requestPermissions(fragment, permissions, l);
    }

    public static int checkSelfPermission(Context context, @NonNull String permission) {
        return ActivityCompat.checkSelfPermission(context, permission);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, @NonNull String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }
}
