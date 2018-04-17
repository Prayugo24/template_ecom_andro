package com.indokoding.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.indokoding.adapeter.HomeFeedAdapter;
import com.indokoding.adapeter.HomeProductAdapter;
import com.indokoding.model.DataFeed;
import com.indokoding.model.DataHotList;
import com.indokoding.model.DataProduk;
import com.indokoding.talabia.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedFragment extends Fragment {
    @BindView(R.id.recycler_viewFeed)
    RecyclerView recyclerView;

    @BindView(R.id.image_backdropFeed)
    ImageView imageBackdrop;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.appbar)
    AppBarLayout appBarFeed;

    private HomeFeedAdapter homeFeedAdapter;
    private List<DataFeed> dataFeeds;

    private static final String TAG = "FeedFragment";

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, view);
        initCollapsingToolbar(view);


        dataFeeds = new ArrayList<>();
        homeFeedAdapter = new HomeFeedAdapter(getActivity(), dataFeeds);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeFeedAdapter);

        prepareProduct();

        try {
            Glide.with(this).load(R.drawable.cover).into(imageBackdrop);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar(View view) {
        collapsingToolbarLayout.setTitle(" ");
        appBarFeed.setExpanded(true);

// hiding & showing the title when toolbar expanded & collapsed
        appBarFeed.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareProduct() {
        int[] coverse = new int[]{
                R.drawable.product,
                R.drawable.product

        };
        DataFeed a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[0]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);
        a = new DataFeed("Fresh Meat Beaf Ground 2.5 KG", 8000, coverse[1]);
        dataFeeds.add(a);

        homeFeedAdapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
