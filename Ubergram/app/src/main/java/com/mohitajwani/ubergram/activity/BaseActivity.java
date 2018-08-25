package com.mohitajwani.ubergram.activity;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mohitajwani.ubergram.R;
import com.mohitajwani.ubergram.fragment.BaseFragment;

/**
 * Created by Mohit on 25/08/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    protected String currentContentFragmentTag;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().executePendingTransactions();
    }

    public void addFragment(int containerViewId, BaseFragment baseFragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, baseFragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
        currentContentFragmentTag = tag;
    }

    public void replaceFragment(int containerViewId, BaseFragment baseFragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, baseFragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
        currentContentFragmentTag = tag;
    }

    public void removeFragment(BaseFragment baseFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(baseFragment);
        fragmentTransaction.commitAllowingStateLoss();
        //getSupportFragmentManager().executePendingTransactions();
    }

    protected BaseFragment getFragmentByTag(String fragmentTag) {
        return (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mProgressDialog.setContentView(R.layout.layout_progress_dialog);
        }
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
