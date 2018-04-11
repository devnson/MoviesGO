package com.example.basto.moviesgo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.basto.moviesgo.R;
import com.google.gson.JsonObject;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class fragment_movieOnTheatre extends android.support.v4.app.Fragment {

    View v;
    private RecyclerView firstrecyclerView;
    private List<Result> resultList= new ArrayList<>();
    private AVLoadingIndicatorView avi;


    private RequestQueue mQueue;

    public fragment_movieOnTheatre() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.movieontheatre, container,false);
        firstrecyclerView= v.findViewById(R.id.movieOnTheatreRecyclerVIew_id);
        RecycleViewAdapter recycleViewAdapter= new RecycleViewAdapter(getContext(),resultList);
        firstrecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        firstrecyclerView.setAdapter(recycleViewAdapter);
        avi=v.findViewById(R.id.avi);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);

        // firstrecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        mQueue= Volley.newRequestQueue(getContext());

        loadJson();


    }

    private void loadJson(){

        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=156233448762466f099bd0f6e16893d0";
        final JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray= response.getJSONArray("results");

                            for(int i=0; i<jsonArray.length();i++){

                                JSONObject results= jsonArray.getJSONObject(i);
                                Result result= new Result();

                                String title = results.getString("title");
                                String posterPath= results.getString("poster_path");
                                Double moviesRating= results.getDouble("vote_average");
                                String coverPath= results.getString("backdrop_path");
                                String overView= results.getString("overview");
                                String releaseDate= results.getString("release_date");

                                result.setTitle(title);
                                result.setPosterPath(posterPath);
                                result.setBackdropPath(coverPath);
                                result.setVoteAverage(moviesRating);
                                result.setOverview(overView);
                                result.setReleaseDate(releaseDate);

                                resultList.add(result);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mQueue.add(request);

    }
    public void showAnimation(){
        avi.show();
    }

    public void stopAnimation(){
        avi.hide();
    }


}
