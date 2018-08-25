package com.mohitajwani.ubergram.network.asyncTask;

import android.os.AsyncTask;

import com.mohitajwani.ubergram.Ubergram;
import com.mohitajwani.ubergram.models.SearchPhotosResponse;
import com.mohitajwani.ubergram.network.AsyncTaskListener;
import com.mohitajwani.ubergram.network.api.GetSearchImagesApi;
import com.mohitajwani.ubergram.network.parser.SearchImagesApiJSONParser;

import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mohit on 25/08/18.
 */
public class GetSearchImagesAsyncTask extends AsyncTask<Void, SearchPhotosResponse, SearchPhotosResponse> {
    private static final String TAG = GetSearchImagesAsyncTask.class.getSimpleName();

    private Ubergram ubergram;
    private AsyncTaskListener asyncTaskListener;
    private String searchText;
    private int pageNo;

    public GetSearchImagesAsyncTask(Ubergram ubergram, AsyncTaskListener asyncTaskListener,
                                    String searchText, int pageNo) {
        this.ubergram = ubergram;
        this.asyncTaskListener = asyncTaskListener;
        this.searchText = searchText;
        this.pageNo = pageNo;
    }

    @Override
    protected SearchPhotosResponse doInBackground(Void... params) {
        GetSearchImagesApi getSearchImagesApi = new GetSearchImagesApi(ubergram);
        SearchPhotosResponse searchPhotosResponse = null;
        try {
            JSONObject jsonObject = getSearchImagesApi.getImages(searchText, pageNo);
            SearchImagesApiJSONParser jsonParser = new SearchImagesApiJSONParser();
            searchPhotosResponse = jsonParser.parseSearchListResponse(jsonObject);

        } catch (ClassCastException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return searchPhotosResponse;
    }

    @Override
    protected void onPostExecute(SearchPhotosResponse searchPhotosResponse) {
        super.onPostExecute(searchPhotosResponse);
        asyncTaskListener.onTaskCompleted(searchPhotosResponse, TAG);
    }
}
