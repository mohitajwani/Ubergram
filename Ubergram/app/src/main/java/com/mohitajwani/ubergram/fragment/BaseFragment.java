package com.mohitajwani.ubergram.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mohitajwani.ubergram.network.AsyncTaskListener;

/**
 * Created by Mohit on 25/08/18.
 */
public class BaseFragment extends Fragment implements AsyncTaskListener {

    private final String TAG = BaseFragment.class.getSimpleName();

    private Context context;
    protected boolean isOnCreateViewCalled;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() == null) {
            setRetainInstance(true);
        }
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onTaskCompleted(Object[] params) {

    }

    @Override
    public void onProgressUpdate(Object[] params) {

    }
}