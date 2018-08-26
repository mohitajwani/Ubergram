package com.mohitajwani.ubergram.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Mohit on 25/08/18.
 */
public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getSimpleName();

    public static boolean getConnectivityStatus(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }
}
