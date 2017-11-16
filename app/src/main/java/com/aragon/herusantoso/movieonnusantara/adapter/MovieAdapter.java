package com.aragon.herusantoso.movieonnusantara.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aragon.herusantoso.movieonnusantara.DetailActivity;
import com.aragon.herusantoso.movieonnusantara.R;
import com.aragon.herusantoso.movieonnusantara.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by santoso on 11/14/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context mContext;
    private List<Movie> movieList;

    public MovieAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.MyViewHolder holder, int position) {
        holder.txtTitle.setText(movieList.get(position).getOriginalTitle());
        String vote = Double.toString(movieList.get(position).getVoteAverage());
        holder.txtUserRating.setText(vote);

        Glide.with(mContext)
                .load(movieList.get(position).getPosterPath())
                .placeholder(R.drawable.load)
                .into(holder.thumbail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtUserRating;
        public ImageView thumbail;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
            txtUserRating = (TextView) itemView.findViewById(R.id.userrating);
            thumbail = (ImageView) itemView.findViewById(R.id.thumbail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Movie clickDataItem = movieList.get(pos);
                        Intent inten = new Intent(mContext, DetailActivity.class);
                        inten.putExtra("original_title", movieList.get(pos).getOriginalTitle());
                        inten.putExtra("poster_path", movieList.get(pos).getPosterPath());
                        inten.putExtra("overview", movieList.get(pos).getOverview());
                        inten.putExtra("vote_average", Double.toString(movieList.get(pos).getVoteAverage()));
                        inten.putExtra("release_date", movieList.get(pos).getReleaseDate());
                        inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(inten);

                        Toast.makeText(view.getContext(),
                                "You Clicked" + clickDataItem.getOriginalTitle(),
                                Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
