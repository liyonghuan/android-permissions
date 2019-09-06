package org.devlang.android;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.devlang.android.permission.OnRequestPermissionResultListener;
import org.devlang.android.permission.PermissionsHelper;

public class MainActivity extends AppCompatActivity implements OnRequestPermissionResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestPermissions(View view) {
        String[] permissions = new String[4];
        permissions[0] = Manifest.permission.CAMERA;
        permissions[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        permissions[2] = Manifest.permission.READ_EXTERNAL_STORAGE;
        permissions[3] = Manifest.permission.CALL_PHONE;
        PermissionsHelper.requestPermissions(this, permissions);
    }

    @Override
    public void onRequestPermissionResult(String permission, int grantResult, boolean isDeniedAlway, boolean isLast) {
        Log.d("cmf", permission + " : " + grantResult + " : " + isDeniedAlway + " : " + isLast);
    }
}
