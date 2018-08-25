package com.mohitajwani.ubergram;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.mohitajwani.ubergram.activity.BaseActivity;
import com.mohitajwani.ubergram.interfaces.ActivityDestroyedListener;
import com.mohitajwani.ubergram.interfaces.CurrentActivityListener;
import com.mohitajwani.ubergram.utils.NetworkUtil;

/**
 * Created by Mohit on 25/08/18.
 */
public class Ubergram extends Application {
    private static final String TAG = Ubergram.class.getSimpleName();

    private CurrentActivityListener currentActivityListener;
    private ActivityDestroyedListener activityDestroyedListener;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
    }

    public void onActivityDestroyed(BaseActivity baseActivity) {
        if (activityDestroyedListener != null) {
            /*
             * If activityDestroyedListener is same as the activity which got destroyed now (due to which this function
             * onActivityDestroyed() got called), then we need to reset activityDestroyedListener to null; otherwise
             * it keeps reference of this destroyed activity blocking lot of memory unnecessarily
             * e.g. - We launch home screen behind splash resulting in initially 'activityDestroyedListener = HomeActivity' followed by
             * 'activityDestroyedListener = SplashActivity'. Now when SplashActivity is destroyed HomeActivity is the only activity required
             * but since last set value for activityDestroyedListener was SplashActivity, its memory could not be released. Hence the
             * following reset is necessary.
             */
            if (activityDestroyedListener == baseActivity) {
                activityDestroyedListener = null;

            } else {
                /*
                 * We call onOtherActivityDestroyed() on activityDestroyedListener because without this sometimes
                 * back arrow is set instead of drawer indicator due to more activities found in back stack. This
                 * happens because onStart() of new activity executes first where we set drawer indicator/back arrow
                 * based on isTaskRoot() call and onDestroy() of previous activity gets called up in the end after
                 * which it's possible that isTaskRoot() call for new activity might return true if that is the only
                 * activity in stack. In this case we should update drawer indicator icon from back arrow to hamburger.
                 *
                 * We use handler here to post in the message queue so that onDestroy() which has called this
                 * function completes first & then only we check for isTaskRoot() from onOtherActivityDestroyed().
                 */
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        if(activityDestroyedListener!=null) {
                            activityDestroyedListener.onOtherActivityDestroyed();
                        }
                    }
                });
            }
        }
    }

    public void setActivityDestroyedListener(ActivityDestroyedListener activityDestroyedListener) {
        this.activityDestroyedListener = activityDestroyedListener;
    }

    public CurrentActivityListener getCurrentActivityListener() {
        //Log.d(TAG, "Databasehelper ChillX getCurrentActivityListener");
        return currentActivityListener;
    }

    public boolean setCurrentActivityListener(CurrentActivityListener currentActivityListener) {
        //Log.d(TAG, "Databasehelper ChillX setCurrentActivityListener");
        this.currentActivityListener = currentActivityListener;
        return NetworkUtil.getConnectivityStatus(this);
    }
}
