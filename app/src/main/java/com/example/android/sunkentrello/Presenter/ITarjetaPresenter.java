package com.example.android.sunkentrello.Presenter;

import android.content.Context;

import com.example.android.sunkentrello.Model.Tarjeta;
import com.example.android.sunkentrello.View.Adapter.ToDoAdapter;

import java.util.List;

public interface ITarjetaPresenter {

    void obtenerTarjetas();
    void añadirTarjeta(Context context);
}
