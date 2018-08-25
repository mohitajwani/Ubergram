package com.mohitajwani.ubergram.utils;

import android.os.AsyncTask;

/**
 * Created by Mohit on 25/08/18.
 */
public class AsyncTaskUtil {
    private static final String TAG = AsyncTaskUtil.class.getSimpleName();

    public static <Params, T extends AsyncTask<Params, ?, ?>> boolean executeAsyncTask(T asyncTask,
                                                                                       boolean runParallel, Params... params) {
        if (params.length == 0) {
            params = null;
        }

        if (runParallel) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);

        } else {
            asyncTask.execute(params);
        }
        return true;
    }
}
