package com.example.basto.moviesgo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class fragment_upcoming extends android.support.v4.app.Fragment {

    View v;
    RecyclerView thirdRecyclerView;
    private List<Result> resultList= new ArrayList<>();

    private RequestQueue mQueue;


    public fragment_upcoming() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_upcoming,container,false);
        thirdRecyclerView= v.findViewById(R.id.thirdRecyclerView_id);
        RecycleViewAdapter recycleViewAdapter= new RecycleViewAdapter(getContext(),resultList);
        thirdRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        thirdRecyclerView.setAdapter(recycleViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mQueue= Volley.newRequestQueue(getContext());
        getJson();

    }

    public void getJson(){

        String url="https://api.themoviedb.org/3/movie/upcoming?api_key=156233448762466f099bd0f6e16893d0&language=en-US&page=1";
        final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray= response.getJSONArray("results");

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject results= jsonArray.getJSONObject(i);
                                Result result= new Result();
                                String posterpath;

                                String title = results.getString("title");
                                String posterPath= results.getString("poster_path");

                                result.setTitle(title);
                                result.setPosterPath(posterPath);

                                Double moviesRating= results.getDouble("vote_average");
                                String coverPath= results.getString("backdrop_path");
                                String overView= results.getString("overview");
                                String releaseDate= results.getString("release_date");

                                Intent intent = new Intent(getContext(),DetailsActivity.class);
                                intent.putExtra("title",title);
                                intent.putExtra("coverPath",coverPath);
                                intent.putExtra("vote",moviesRating);
                                intent.putExtra("overview",overView);
                                intent.putExtra("releaseDate",releaseDate);

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
        mQueue.add(jsonObjectRequest);


    }
}
