package com.example.basto.moviesgo;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    List<Result> resultList;

    private ImageView movieCover;
    private TextView movieTitle;
    private TextView movieRating;
    private TextView movieReleaseDate;
    private TextView movieOverview;

    private String movieCoverPath;
    private String movieTitleHolder;
    private Double movieRatingHolder;
    private String movieReleaseHolder;
    private String movieOverViewHolder;
    String coverPath="http://image.tmdb.org/t/p/w300";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        movieCover=findViewById(R.id.details_moviecover);
        movieTitle=findViewById(R.id.details_movietitle);
        movieRating=findViewById(R.id.details_rating);
        movieReleaseDate=findViewById(R.id.details_releasedate);
        movieOverview=findViewById(R.id.details_overview);



        movieTitleHolder= getIntent().getExtras().getString("title");
        movieCoverPath= getIntent().getExtras().getString("moviecover");
        movieOverViewHolder= getIntent().getExtras().getString("overview");
        movieRatingHolder= getIntent().getExtras().getDouble("rating");
        movieReleaseHolder=getIntent().getExtras().getString("releasedate");

        //int views
        CollapsingToolbarLayout collapsingToolbarLayout= findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(movieTitleHolder);


        movieTitle.setText(movieTitleHolder);
        movieRating.setText(movieRatingHolder.toString());
        movieReleaseDate.setText(movieReleaseHolder);
        movieOverview.setText(movieOverViewHolder);

        Picasso.get().load(movieCoverPath).into(movieCover);




    }
}
