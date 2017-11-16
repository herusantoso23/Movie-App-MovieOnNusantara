package com.aragon.herusantoso.movieonnusantara;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by santoso on 11/14/17.
 */

public class DetailActivity extends AppCompatActivity {
    TextView txtNameOfMovie, txtPlotSynopsis, txtUserRating, txtReleaseDate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageView = (ImageView) findViewById(R.id.thumbail_image_header);
        txtNameOfMovie = (TextView) findViewById(R.id.title);
        txtPlotSynopsis = (TextView) findViewById(R.id.plotsynopsis);
        txtUserRating = (TextView) findViewById(R.id.userrating);
        txtReleaseDate = (TextView) findViewById(R.id.releasedate);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("original_title")){
            String thumbail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumbail)
                    .placeholder(R.drawable.load)
                    .into(imageView);

            txtNameOfMovie.setText(movieName);
            txtPlotSynopsis.setText(synopsis);
            txtUserRating.setText(rating);
            txtReleaseDate.setText(dateOfRelease);
        } else {
            Toast.makeText(this, "No, API Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
               if(scrollRange == -1){
                   scrollRange = appBarLayout.getTotalScrollRange();
               }

               if(scrollRange + verticalOffset == 0){
                   collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                   isShow = true;
               } else if (isShow) {
                   collapsingToolbarLayout.setTitle("");
                   isShow = false;
               }
            }
        });
    }
}
