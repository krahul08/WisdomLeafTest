package com.example.wisdomleaftest.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wisdomleaftest.R;
import com.example.wisdomleaftest.data.ImagesResponseData;

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
        Glide.with(context).load("https://www.google.co.in/url?sa=i&url=https%3A%2F%2Fin.pinterest.com%2Fpin%2F776237685759631394%2F&psig=AOvVaw3-iQftHhquR1RuribENkgG&ust=1600283583091000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKCG7ojv6-sCFQAAAAAdAAAAABAD").into(listHolder.imageView);
        ImagesResponseData bookmarkData = imagesResponseData.get(position);
        Toast.makeText(context, "" + bookmarkData.getUrl(), Toast.LENGTH_SHORT).show();
        listHolder.textView.setText(bookmarkData.getAuthor());

//        listHolder.imageView.getLayoutParams().height = bookmarkData.getHeight();
//        listHolder.imageView.getLayoutParams().width = bookmarkData.getWidth();
//        listHolder.imageView.requestLayout();


    }

    @Override
    public int getItemCount() {
        return imagesResponseData.size();
    }


    public class listHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;


        public listHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);

        }
    }


}
