package com.example.android.sunkentrello.Presenter;

import android.arch.core.util.Function;
import android.content.Context;

import com.example.android.sunkentrello.Model.Tarjeta;
import com.example.android.sunkentrello.View.Adapter.IToDoView;

import java.util.ArrayList;
import java.util.List;

public class TarjetaPresenter implements ITarjetaPresenter{

    private List<Tarjeta> listaTarjetas = new ArrayList<Tarjeta>();
    private IToDoView iToDoView;
    public TarjetaPresenter(IToDoView toDoView){
        iToDoView = toDoView;
    }

    public void obtenerTarjetas(){

//        List<Tarjeta> listaTarjetas = new ArrayList<Tarjeta>();
//        listaTarjetas.add(crearTarjetaFake());
//        listaTarjetas.add(crearTarjetaFake());
//        listaTarjetas.add(crearTarjetaFake());
//        listaTarjetas.add(crearTarjetaFake());

        final List<Tarjeta> listaTarjetas = new ArrayList<Tarjeta>();
        FirebaseManager.getInstance(iToDoView.getContext()).ObtenerListaTarjetasToDo(new Function<List<Tarjeta>, Boolean>() {
            @Override
            public Boolean apply(List<Tarjeta> input) {
                iToDoView.limpiar();
                System.out.println("INPUT INPUT INPUT INPUT");
                for(Tarjeta tarjeta: input){
                    System.out.println("TARJETA TARJETA");
                    listaTarjetas.add(tarjeta);
                }
                System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
                System.out.println("SIZE: "+listaTarjetas.size());
                iToDoView.añadirTarjetas(listaTarjetas);
                iToDoView.finalizarRefresh();
                return true;
            }
        });
    }

    public void añadirTarjeta(Context context){
        FirebaseManager.getInstance(context).AñadirTarjeta();
    }

    private Tarjeta crearTarjetaFake(){
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNombre("To Do Fragments");
        tarjeta.setDescripcion("La wea fragments");
        tarjeta.setFecha("02/02/02");
        return tarjeta;
    }

}
