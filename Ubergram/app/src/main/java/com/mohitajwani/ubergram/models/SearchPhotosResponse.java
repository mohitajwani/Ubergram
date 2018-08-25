package com.mohitajwani.ubergram.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mohit on 25/08/18.
 */
public class SearchPhotosResponse implements Serializable {
    private static final String TAG = SearchPhotosResponse.class.getSimpleName();

    private int pages;
    private List<FlickrPhoto> flickrPhotoList;

    public SearchPhotosResponse(int pages, List<FlickrPhoto> flickrPhotoList) {
        this.pages = pages;
        this.flickrPhotoList = flickrPhotoList;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<FlickrPhoto> getFlickrPhotoList() {
        return flickrPhotoList;
    }

    public void setFlickrPhotoList(List<FlickrPhoto> flickrPhotoList) {
        this.flickrPhotoList = flickrPhotoList;
    }
}
