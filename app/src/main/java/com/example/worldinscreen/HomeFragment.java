package com.example.worldinscreen;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    String api="9fdab22f392d4707bd4d335273b16338";
    ArrayList<ModelClass> modelClassArrayList;
    ArrayList<ModelClass> p;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewofhome;

    String k="",news="",news1="",news2="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.homefragment,null);

        recyclerViewofhome=v.findViewById(R.id.recyclerviewofhome);
        modelClassArrayList=new ArrayList<>();
        recyclerViewofhome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modelClassArrayList);
        recyclerViewofhome.setAdapter(adapter);

        findNews();


        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getNews(country,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();

                    p = response.body().getArticles();

                    for (int i = 0; i < 9; i++) {
                        ModelClass aa = p.get(i);
                        news = news + "Title : " + String.valueOf(aa.getTitle()) + ". The description of the news is : " + String.valueOf(aa.getDescription()) + ". The news was published by : " + String.valueOf(aa.getAuthor()) + ". The news is published at " + String.valueOf(aa.getPublishedAt()) + ". ";
                        news = news + ". The next news is : ";
                    }

                    for (int i = 9; i < 18; i++) {
                        ModelClass aa = p.get(i);
                        news1 = news1 + "Title : " + String.valueOf(aa.getTitle()) + ". The description of the news is : " + String.valueOf(aa.getDescription()) + ". The news was published by : " + String.valueOf(aa.getAuthor()) + ". The news is published at " + String.valueOf(aa.getPublishedAt()) + ". ";
                        news1 = news1 + ". The next news is : ";
                    }

                    for (int i = 18; i <= 19; i++) {
                        ModelClass aa = p.get(i);
                        news2 = news2 + "Title : " + String.valueOf(aa.getTitle()) + ". The description of the news is : " + String.valueOf(aa.getDescription()) + ". The news was published by : " + String.valueOf(aa.getAuthor()) + ". The news is published at " + String.valueOf(aa.getPublishedAt()) + ". ";
                        if (i != 19)
                            news2 = news2 + ". The next news is : ";
                        else
                            news2 = news2 + ". The news is up to this. Please stay tuned for further updates. Thank You.";
                    }
                    MainActivity.get_texts(news, news1, news2);
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });


    }
}
