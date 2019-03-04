package com.klavor.android.tools;

import android.Manifest;
import android.content.pm.PermissionInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;

import com.klavor.android.tools.permission.OnRequestPermissionResultListener;
import com.klavor.android.tools.permission.Permissions;
import com.klavor.android.tools.permission.PermissionsHelper;

import java.security.Permission;

public class MainActivity extends AppCompatActivity implements OnRequestPermissionResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestPermissions(View view) {
        String[] permissions = new String[3];
        permissions[0] = Manifest.permission.CAMERA;
        permissions[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        permissions[2] = Manifest.permission.CALL_PHONE;
        PermissionsHelper.requestPermissions(this, permissions);
    }

    @Override
    public void onRequestPermissionResult(String permission, int grantResult, boolean isDeniedAlway, boolean isLast) {
        Log.d("cmf", permission + " : " + grantResult + " : " + isDeniedAlway + " : " + isLast);
    }
}
