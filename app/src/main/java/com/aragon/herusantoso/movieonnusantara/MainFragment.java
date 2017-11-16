package com.aragon.herusantoso.movieonnusantara;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
    private View myView;
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_main, container, false);

        mTabHost = (FragmentTabHost) myView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("popular")
                .setIndicator("Most Popular"), MainPopularMovieFragment.class, null );
        mTabHost.addTab(mTabHost.newTabSpec("rated")
                .setIndicator("Top Rated"), MainTopRatedFragment.class, null );



        return myView;
    }


}
