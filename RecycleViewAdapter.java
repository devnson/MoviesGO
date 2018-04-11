package com.example.basto.moviesgo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>{

    private Context context;
    private List<Result> results;

    public RecycleViewAdapter(Context context, List lst){
        this.context= context;
        this.results=lst;
    }




    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.cardview,parent,false);
        final RecycleViewHolder recycleViewHolder= new RecycleViewHolder(view);

        recycleViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(context,DetailsActivity.class);
                i.putExtra("title",results.get(recycleViewHolder.getAdapterPosition()).getTitle());
                i.putExtra("moviecover",results.get(recycleViewHolder.getAdapterPosition()).getPosterPath());
                i.putExtra("rating",results.get(recycleViewHolder.getAdapterPosition()).getVoteAverage());
                i.putExtra("releasedate",results.get(recycleViewHolder.getAdapterPosition()).getReleaseDate());
                i.putExtra("overview",results.get(recycleViewHolder.getAdapterPosition()).getOverview());
                context.startActivity(i);


            }
        });

        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder holder, int position) {

        holder.movieTitle.setText(results.get(position).getTitle());
        Picasso.get().load(results.get(position).getPosterPath()).into(holder.movieCover);


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder{

        ImageView movieCover;
        TextView movieTitle;
        CardView cardView;



        public RecycleViewHolder(final View itemView) {
            super(itemView);
            movieCover=itemView.findViewById(R.id.moviecover_id);
            movieTitle=itemView.findViewById(R.id.movietitle_id);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }


}
