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
import com.indokoding.model.DataProduk;
import com.indokoding.talabia.AboutActivity;
import com.indokoding.talabia.DetailProductActivity;
import com.indokoding.talabia.HomeActivity;
import com.indokoding.talabia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 26/02/2018.
 */

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.myViewHolder> {


    private Context context;
    private List<DataProduk> dataProduks;

    public class myViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardViewHome)
        CardView cardViewHome;

        @BindView(R.id.tv_titleProduct)
        public TextView tvTitleProduct;

        @BindView(R.id.tvPrice)
        public TextView tvPrice;

        @BindView(R.id.image_thumbnailProduct)
        public ImageView thumbnailProduct;


        public myViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }

    }


    public HomeProductAdapter(Context context, List<DataProduk> dataProduks) {
        this.context = context;
        this.dataProduks = dataProduks;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_menu_home, parent, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        DataProduk dataProduk = dataProduks.get(position);
        holder.tvTitleProduct.setText(dataProduk.getTitleProduct());
        holder.tvPrice.setText(dataProduk.getPrice() + " KWD");

        // loading album cover using Glide library
        Glide.with(context).load(dataProduk.getThumbnail()).into(holder.thumbnailProduct);
        holder.cardViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailProductActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        holder.thumbnailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailProductActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });


    }


    @Override
    public int getItemCount() {
        return dataProduks.size();
    }

}
