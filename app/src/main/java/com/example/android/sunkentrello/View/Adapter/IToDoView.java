package com.example.android.sunkentrello.View.Adapter;

import android.content.Context;

import com.example.android.sunkentrello.Model.Tarjeta;

import java.util.List;

public interface IToDoView {

    void actualizarTarjetas();
    void limpiar();
    void añadirTarjetas(List<Tarjeta> listaTarjetas);
    void finalizarRefresh();
    Context getContext();
}
