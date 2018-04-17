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
import com.indokoding.model.DataHotList;

import com.indokoding.talabia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 26/02/2018.
 */

public class HomeHotListAdapter extends RecyclerView.Adapter<HomeHotListAdapter.myViewHolder> {
    private Context context;
    private List<DataHotList> dataHotLists;


    public class myViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_titleHotList)
        public TextView tvTitleHotList;

        @BindView(R.id.tvPriceHotList)
        public TextView tvPriceHotList;

        @BindView(R.id.image_thumbnailHotList)
        public ImageView thumbnailHotlist;


        public myViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public HomeHotListAdapter(Context context, List<DataHotList> dataHotLists) {
        this.context = context;
        this.dataHotLists = dataHotLists;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_menu_hotlist, parent, false);

        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        DataHotList dataHotList = dataHotLists.get(position);
        holder.tvTitleHotList.setText(dataHotList.getTitleProduct());
        holder.tvPriceHotList.setText(dataHotList.getPrice() + " KWD");

        // loading album cover using Glide library
        Glide.with(context).load(dataHotList.getThumbnail()).into(holder.thumbnailHotlist);


    }

    @Override
    public int getItemCount() {
        return dataHotLists.size();
    }

}
