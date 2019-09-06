package org.devlang.android.permission;

import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by Klavor on 2019/3/4.
 */
public class Permissions implements OnRequestPermissionResultListener {
    private static final String TAG = Permissions.class.getSimpleName();

    private Fragment mPermissionsFragment;
    private OnRequestPermissionResultListener mOnRequestPermissionResultListener;
    private String[] mPermissions;
    private boolean mHasCalling = false;

    public Permissions(FragmentActivity activity) {
        newInstancePermissionsFragment(activity.getSupportFragmentManager());
    }

    public Permissions(Fragment fragment) {
        newInstancePermissionsFragment(fragment.getChildFragmentManager());
    }

    private synchronized void newInstancePermissionsFragment(FragmentManager fragmentManager) {
        if (mPermissionsFragment == null) {
            Fragment fragment = fragmentManager.findFragmentByTag(TAG);
            if (fragment == null) {
                fragment = new PermissionsFragment();
                fragmentManager.beginTransaction()
                        .add(fragment, TAG)
                        .commitNow();
            }
            mPermissionsFragment = fragment;
        }
    }

    public synchronized void requstPermissions(@NonNull String[] permissions, OnRequestPermissionResultListener l) {
        if (permissions.length == 0) {
            return;
        }
        if (!mHasCalling) {
            mHasCalling = true;
            mPermissions = permissions;
            mOnRequestPermissionResultListener = l;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                doUpperPermissions();
            } else {
                doLowerPermissions();
            }
        }
    }

    private void doLowerPermissions() {
        String[] permissions = mPermissions;
        FragmentActivity activity = mPermissionsFragment.getActivity();
        if (activity != null) {
            int length = permissions.length;
            for (String permission : permissions) {
                int grantResult = ActivityCompat.checkSelfPermission(activity, permission);
                length--;
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    onRequestPermissionResult(permission, grantResult, false, length == 0);
                } else {
                    onRequestPermissionResult(permission, grantResult, true, length == 0);
                }
            }
        }
        mHasCalling = false;
    }

    private void doUpperPermissions() {
        String[] permissions = mPermissions;
        FragmentActivity activity = mPermissionsFragment.getActivity();
        ArrayList<String> requestPermiisions = new ArrayList<>();
        if (activity != null) {
            int length = permissions.length;
            for (String permission : permissions) {
                int grantResult = ActivityCompat.checkSelfPermission(activity, permission);
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    length--;
                    onRequestPermissionResult(permission, grantResult, false, length == 0);
                } else {
                    requestPermiisions.add(permission);
                }
            }
        }
        int size = requestPermiisions.size();
        if (size > 0) {
            String[] requestPermissions = new String[size];
            requestPermiisions.toArray(requestPermissions);
            ((PermissionsFragment) mPermissionsFragment).requestPermissions(requestPermissions, this);
        } else {
            mHasCalling = false;
        }
    }

    @Override
    public void onRequestPermissionResult(String permission, int grantResult, boolean isDeniedAlway, boolean isLast) {
        if (mOnRequestPermissionResultListener != null) {
            mOnRequestPermissionResultListener.onRequestPermissionResult(permission, grantResult, isDeniedAlway, isLast);
        }
        if (isLast) {
            mHasCalling = false;
            mOnRequestPermissionResultListener = null;
            mPermissions = null;
        }
    }
}
