package com.example.wisdomleaftest.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.example.wisdomleaftest.utils.BaseViewHolder;

import java.util.List;

import butterknife.ButterKnife;

public class ImagesListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ImagesResponseData> imagesResponseData;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;


    public ImagesListAdapter(Context context, List<ImagesResponseData> imagesResponseData) {
        this.context = context;
        this.imagesResponseData = imagesResponseData;
        if (context != null) {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new listHolder(layoutInflater.inflate(R.layout.imageslistitem, viewGroup, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(layoutInflater.inflate(R.layout.items_loading, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemCount() {
        return imagesResponseData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == imagesResponseData.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    public void addItems(List<ImagesResponseData> dataList) {
        imagesResponseData.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        imagesResponseData.add(new ImagesResponseData());
        notifyItemInserted(imagesResponseData.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = imagesResponseData.size() - 1;
        ImagesResponseData item = getItem(position);
        if (item != null) {
            imagesResponseData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        imagesResponseData.clear();
        notifyDataSetChanged();
    }

    ImagesResponseData getItem(int position) {
        return imagesResponseData.get(position);
    }


    public class listHolder extends BaseViewHolder {

        AppCompatImageView imageView;
        TextView textView;


        public listHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            ImagesResponseData responseData = imagesResponseData.get(position);

            textView.setText(responseData.getAuthor());
            imageView.getLayoutParams().width = responseData.getWidth();
            imageView.getLayoutParams().height = responseData.getHeight();
            imageView.requestLayout();
            Glide.with(context).load(responseData.getDownload_url())
                    .thumbnail(0.5f)
                    .crossFade()
                    .placeholder(new ColorDrawable(Color.DKGRAY))
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
                    .into(imageView);
        }
    }


    private class ProgressHolder extends BaseViewHolder {

        public ProgressHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemview);
        }

        @Override
        protected void clear() {
        }
    }
}
