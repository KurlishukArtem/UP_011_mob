package com.example.up_011;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import WSRfood.up.Models.Dish;
import WSRfood.up.ui.home.HomeFragment;

public class DishsAdapter extends RecyclerView.Adapter<DishsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<Dish> dishs;
    private final Context context;
    private HomeFragment homeFragment;

    public DishsAdapter(Context context, ArrayList<Dish> states, HomeFragment homeFragment) {
        this.context = context;
        this.dishs = states;
        this.inflater = LayoutInflater.from(context);
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dish_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish dish = dishs.get(position);
        holder.name.setText(dish.name);
        holder.price.setText("N" + dish.price);
        Picasso.with(context)
                .load(dish.image)
                .resize(300,300)
                .into(holder.logo);
        holder.itemView.setOnClickListener(v -> {
            homeFragment.OnSelectDish(dish.name, String.valueOf(dish.price), dish.image, context);
        });
    }

    @Override
    public int getItemCount() {
        return dishs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, price;
        final ImageView logo;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            logo = view.findViewById(R.id.logo1);
        }
    }
}
