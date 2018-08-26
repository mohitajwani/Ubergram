package com.mohitajwani.ubergram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohitajwani.ubergram.R;
import com.mohitajwani.ubergram.models.FlickrPhoto;
import com.mohitajwani.ubergram.network.image.ImageLoader;

import java.util.List;

/**
 * Created by Mohit on 25/08/18.
 */
public class SearchImagesAdapter extends RecyclerView.Adapter<SearchImagesAdapter.ViewHolder> {

    public static final String TAG = SearchImagesAdapter.class.getSimpleName();

    private List<FlickrPhoto> flickrPhotoList;
    private ImageLoader imgLoader;

    public SearchImagesAdapter(Context context, List<FlickrPhoto> flickrPhotos) {
        super();
        flickrPhotoList = flickrPhotos;
        imgLoader = new ImageLoader(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_photo, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        FlickrPhoto flickrPhoto = flickrPhotoList.get(position);
        viewHolder.setData(flickrPhoto, position);
    }

    @Override
    public int getItemCount() {
        return flickrPhotoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mPhotoImageView;
        TextView mPhotoTextView;

        private FlickrPhoto flickrPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.ivPhotoItem);
            mPhotoTextView = itemView.findViewById(R.id.tvPhotoItem);
        }

        void setData(final FlickrPhoto photo, final int position) {
            flickrPhoto = photo;

            if (flickrPhoto.getImageURL() != null) {
                mPhotoImageView.setImageDrawable(null);
                imgLoader.displayImage(flickrPhoto.getImageURL(), R.mipmap.ic_launcher, mPhotoImageView);
            }
            mPhotoTextView.setText(flickrPhoto.getTitle());
        }
    }
}
