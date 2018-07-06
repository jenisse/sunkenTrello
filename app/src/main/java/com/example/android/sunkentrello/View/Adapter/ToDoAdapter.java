package com.example.android.sunkentrello.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunkentrello.Model.Tarjeta;
import com.example.android.sunkentrello.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder>{
    private List<Tarjeta> listaTarjetas;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textViewNombreTarjeta;
        public TextView textViewDescripcionTarjeta;
        public TextView textViewFechaTarjeta;

        public MyViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            textViewNombreTarjeta = (TextView) v.findViewById(R.id.card_name);
            textViewDescripcionTarjeta = (TextView) v.findViewById(R.id.card_description);
            textViewFechaTarjeta = (TextView) v.findViewById(R.id.card_date);
        }
    }

    public ToDoAdapter(List<Tarjeta> listaTarjetas, Context context) {
        this.listaTarjetas = listaTarjetas;
        this.context = context;
    }


    public void setData(List<Tarjeta> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ToDoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.MyViewHolder holder, int position) {
        final Tarjeta tarjeta = listaTarjetas.get(position);
        holder.textViewNombreTarjeta.setText(tarjeta.getNombre());
        holder.textViewDescripcionTarjeta.setText(tarjeta.getDescripcion());
        holder.textViewFechaTarjeta.setText(tarjeta.getFecha());
    }

    @Override
    public int getItemCount() {
        return listaTarjetas.size();
    }
}
