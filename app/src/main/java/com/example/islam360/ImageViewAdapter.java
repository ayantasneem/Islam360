package com.example.islam360;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.MyViewHolder> {
    List<ImageApiModel.Post> list;
    String baseurl = "https://islam360.app/islam360/posts/image/urdu/";

    public ImageViewAdapter(List<ImageApiModel.Post> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageprototype, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Uri img_url = Uri.parse(baseurl+list.get(position).getPath());
        Glide.with(holder.img_ayat.getContext()).load(img_url).apply(new RequestOptions().override(1400, 1400)).into(holder.img_ayat);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_ayat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_ayat = (ImageView) itemView.findViewById(R.id.img_ayat);
        }
    }
}
