package com.example.worldinscreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{


    Context context;
    ArrayList<ModelClass> modelClassArrayList;
    private int position;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position ) {
        this.position =holder.getAdapterPosition();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,webView.class);
                intent.putExtra("url",modelClassArrayList.get(holder.getAdapterPosition()).getUrl());
                context.startActivity(intent);
            }
        });


        holder.ntime.setText("Published At :- "+modelClassArrayList.get(position).getPublishedAt());

        holder.nauthor.setText(modelClassArrayList.get(position).getAuthor());
        holder.nheading.setText(modelClassArrayList.get(position).getTitle());
        //holder.ntime.setText(modelClassArrayList.get(position).getPublishedAt());
        holder.ncontent.setText(modelClassArrayList.get(position).getDescription());
        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nheading,nauthor,ncontent,ntime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nheading=itemView.findViewById(R.id.mainheading);
            ncontent=itemView.findViewById(R.id.contentt);
            nauthor=itemView.findViewById(R.id.author);
            ntime=itemView.findViewById(R.id.time);
            imageView=itemView.findViewById(R.id.imageview);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }
}

