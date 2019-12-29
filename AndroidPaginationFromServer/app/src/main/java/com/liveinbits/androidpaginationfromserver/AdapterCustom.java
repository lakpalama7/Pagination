package com.liveinbits.androidpaginationfromserver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterCustom extends RecyclerView.Adapter<AdapterCustom.MyViewHolder> {

    List<Images> list;
    Context context;
    public AdapterCustom(List<Images> list){
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view=LayoutInflater.from(context).inflate(viewType,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Images obj=list.get(position);
        Glide.with(context).load(obj.getImagepath()).into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.single_item;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.image);
        }
    }

    public void bindImage(List<Images> images){
        for(Images img:images){
            list.add(img);
        }
        notifyDataSetChanged();
    }
}
