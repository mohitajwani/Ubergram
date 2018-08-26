package com.mohitajwani.ubergram.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.mohitajwani.ubergram.R;
import com.mohitajwani.ubergram.fragment.HomeFragment;
import com.mohitajwani.ubergram.service.PermissionService;
import com.mohitajwani.ubergram.utils.FragmentUtil;

/**
 * Created by Mohit on 25/08/18.
 */
public class HomeActivity extends BaseActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    private PermissionService mStoragePermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkStoragePermissions();
    }

    private void initializeFragment() {
        if (getFragmentByTag(FragmentUtil.getTag(HomeFragment.class)) == null) {
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(getIntent().getExtras());
            addFragment(R.id.fragmentContainer, homeFragment, FragmentUtil.getTag(homeFragment), false);
        }
    }
    private void checkStoragePermissions() {
        if (mStoragePermissions == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mStoragePermissions = new PermissionService(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 165, new PermissionService.IPermissionsService() {
                    @Override
                    public void onPermissionGranted() {
                        initializeFragment();
                    }

                    @Override
                    public void onPermissionDenied() {
                        finish();
                    }
                });
            } else {
                initializeFragment();
            }
        }

        if (mStoragePermissions.hasPermissionsGranted()) {
            initializeFragment();
        } else {
            mStoragePermissions.requestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mStoragePermissions != null) {
            mStoragePermissions.processResult(requestCode, permissions, grantResults);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
