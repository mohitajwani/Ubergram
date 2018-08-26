package com.mohitajwani.ubergram.network;

/**
 * Created by Mohit on 25/08/18.
 */
public interface AsyncTaskListener<T, P> {
    void onTaskCompleted(T... params);
    void onProgressUpdate(P... params);
}
