package com.indokoding.adapeter;

import android.content.Context;
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
import com.indokoding.model.DataFavorit;
import com.indokoding.model.DataHotList;
import com.indokoding.talabia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 26/02/2018.
 */

public class HomeFavoritAdapter extends RecyclerView.Adapter<HomeFavoritAdapter.myViewHolder> {
    private Context context;
    private List<DataFavorit> dataFavorits;


    public class myViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_titleFavorit)
        public TextView tvTitleFavorit;

        @BindView(R.id.tvPriceFavorit)
        public TextView tvPriceFavorit;

        @BindView(R.id.image_thumbnailfavorit)
        public ImageView thumbnailHotlist;


        public myViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public HomeFavoritAdapter(Context context, List<DataFavorit> dataFavorits) {
        this.context = context;
        this.dataFavorits = dataFavorits;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_menu_favorit, parent, false);

        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        DataFavorit dataFavorit = dataFavorits.get(position);
        holder.tvTitleFavorit.setText(dataFavorit.getTitleFavorit());
        holder.tvPriceFavorit.setText(dataFavorit.getPrice() + " KWD");

        // loading album cover using Glide library
        Glide.with(context).load(dataFavorit.getThumbnail()).into(holder.thumbnailHotlist);

    }

    @Override
    public int getItemCount() {
        return dataFavorits.size();
    }

}
