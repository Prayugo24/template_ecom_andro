package com.indokoding.adapeter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.indokoding.model.DataFeed;
import com.indokoding.model.DataProduk;
import com.indokoding.talabia.DetailProductActivity;
import com.indokoding.talabia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 26/02/2018.
 */

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.myViewHolder> {
    private Context context;
    private List<DataFeed> dataFeeds;

    public class myViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardViewFeed)
        CardView cardViewFeed;

        @BindView(R.id.tv_titleFeed)
        public TextView tvTitleFeed;

        @BindView(R.id.tvPriceFeed)
        public TextView tvPrice;

        @BindView(R.id.image_thumbnailFeed)
        public ImageView thumbnailFeed;


        public myViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

    public HomeFeedAdapter(Context context, List<DataFeed> dataFeed) {
        this.context = context;
        this.dataFeeds = dataFeed;
    }


    @Override
    public HomeFeedAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_menu_feed, parent, false);

        return new HomeFeedAdapter.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HomeFeedAdapter.myViewHolder holder, int position) {
        DataFeed dataFeed = dataFeeds.get(position);
        holder.tvTitleFeed.setText(dataFeed.getTitleFeed());
        holder.tvPrice.setText(dataFeed.getPrice() + " KWD");

        // loading album cover using Glide library
        Glide.with(context).load(dataFeed.getThumbnail()).into(holder.thumbnailFeed);
        holder.cardViewFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailProductActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        holder.thumbnailFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailProductActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });


    }


    @Override
    public int getItemCount() {
        return dataFeeds.size();
    }
}
