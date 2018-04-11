package com.example.basto.moviesgo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   // String movieTitle;
   // String posterPath;

   // private RequestQueue mQueue;
  //  private List<Result> movieList= new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private viewpageradapter viewpageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tablayout_id);
        viewPager=findViewById(R.id.viewpager_id);
        TextView tabOne= (TextView) LayoutInflater.from(this).inflate(R.layout.customtab,null);

        viewpageradapter= new viewpageradapter(getSupportFragmentManager());

        //Add fragment here
        viewpageradapter.AddFragment(new fragment_trending(),"Trending");
        viewpageradapter.AddFragment(new fragment_movieOnTheatre(),"OnTheatre");
        viewpageradapter.AddFragment(new fragment_upcoming(),"Upcoming");


        viewPager.setAdapter(viewpageradapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_trending);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_theaters);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_keyboard_arrow);
      //  tabLayout.getTabAt(2).setIcon()
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));


      //  mQueue=Volley.newRequestQueue(this);
        //loadJson();

    }

    /*

    public void loadJson() {

        String url="https://api.themoviedb.org/3/discover/movie?api_key=156233448762466f099bd0f6e16893d0&sort_by=popularity.desc";
        final JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray= response.getJSONArray("results");

                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject results= jsonArray.getJSONObject(i);
                        Result result= new Result();

                        result.setTitle(results.getString("title"));
                        result.setPosterPath(results.getString("poster_path"));

                        movieList.add(result);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setRvadapter(movieList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);

    }

    public void setRvadapter (List<Result> lst) {

        RecycleViewAdapter myAdapter = new RecycleViewAdapter(this,lst) ;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdapter);




    }

    */
}