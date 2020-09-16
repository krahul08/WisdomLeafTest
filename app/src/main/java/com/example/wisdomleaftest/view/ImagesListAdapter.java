package com.example.wisdomleaftest.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.wisdomleaftest.R;
import com.example.wisdomleaftest.data.ImagesResponseData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ImagesResponseData> imagesResponseData;


    public ImagesListAdapter(Context context) {
        this.context = context;
        if (context != null) {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    public void setData(List<ImagesResponseData> imagesResponseData) {
        this.imagesResponseData = imagesResponseData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.imageslistitem, viewGroup, false);
        return new listHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ImagesListAdapter.listHolder listHolder = (ImagesListAdapter.listHolder) holder;
        ImagesResponseData bookmarkData = imagesResponseData.get(position);
        listHolder.textView.setText(bookmarkData.getAuthor());
        listHolder.imageView.getLayoutParams().height = bookmarkData.getHeight();
        listHolder.imageView.getLayoutParams().width = bookmarkData.getWidth();
        listHolder.imageView.requestLayout();
        Glide.with(context).load(bookmarkData.getDownload_url())
                .thumbnail(0.5f)
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(listHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesResponseData.size();
    }


    public class listHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imageView;
        TextView textView;


        public listHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);

        }
    }


}
