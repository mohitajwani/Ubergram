package com.mohitajwani.ubergram.network.parser;

import com.mohitajwani.ubergram.models.FlickrPhoto;
import com.mohitajwani.ubergram.models.SearchPhotosResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohit on 25/08/18.
 */
public class SearchImagesApiJSONParser {
    private static final String TAG = SearchImagesApiJSONParser.class.getSimpleName();

    private static final String KEY_PHOTOS_JSON = "photos";
    private static final String KEY_STAT = "stat";
    private static final String KEY_OK = "ok";
    private static final String KEY_PAGES = "pages";
    private static final String KEY_PHOTOS_ARRAY = "photo";
    private static final String KEY_PHOTO_ID = "id";
    private static final String KEY_OWNER = "owner";
    private static final String KEY_SECRET = "secret";
    private static final String KEY_SERVER = "server";
    private static final String KEY_FARM = "farm";
    private static final String KEY_TITLE = "title";

    public SearchPhotosResponse parseSearchListResponse(JSONObject jsonObject) {
        SearchPhotosResponse searchPhotosResponse = null;
        try {
            if (jsonObject.has(KEY_STAT)) {
                if (jsonObject.getString(KEY_STAT).contentEquals(KEY_OK)) {
                    JSONObject resultObject = jsonObject.getJSONObject(KEY_PHOTOS_JSON);
                    JSONArray jsonArray = resultObject.getJSONArray(KEY_PHOTOS_ARRAY);
                    searchPhotosResponse = new SearchPhotosResponse(
                            resultObject.getInt(KEY_PAGES),
                            parseSearchArray(jsonArray)
                    );
                }
            } else {
                return searchPhotosResponse;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return searchPhotosResponse;
    }

    private List<FlickrPhoto> parseSearchArray(JSONArray jsonArray) {
        List<FlickrPhoto> flickrPhotoList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                FlickrPhoto flickrPhoto = new FlickrPhoto(
                        jsonObj.getString(KEY_PHOTO_ID),
                        jsonObj.getString(KEY_OWNER),
                        jsonObj.getString(KEY_SECRET),
                        jsonObj.getString(KEY_SERVER),
                        jsonObj.getInt(KEY_FARM),
                        jsonObj.getString(KEY_TITLE));
                flickrPhotoList.add(flickrPhoto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return flickrPhotoList;
    }
}
