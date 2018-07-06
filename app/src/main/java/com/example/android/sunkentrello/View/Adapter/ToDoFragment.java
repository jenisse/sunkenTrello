package com.example.android.sunkentrello.View.Adapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sunkentrello.Model.Tarjeta;
import com.example.android.sunkentrello.Presenter.ITarjetaPresenter;
import com.example.android.sunkentrello.Presenter.TarjetaPresenter;
import com.example.android.sunkentrello.R;

import java.util.ArrayList;
import java.util.List;

public class ToDoFragment extends Fragment implements IToDoView {
    private SwipeRefreshLayout swipeRefresh;
    private ITarjetaPresenter iTarjetaPresenter;
    private ToDoAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iTarjetaPresenter = new TarjetaPresenter(this);
        Bundle args = getArguments();
        //mUserId = args.getString("userId", "");
    }

    public static ToDoFragment getNewInstance() {
        ToDoFragment f = new ToDoFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("userId", "");
        f.setArguments(args);
        return f;
    }


    private void loadData(final RecyclerView rv, final ToDoAdapter adapter) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.card_list, container, false);

        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);




        iTarjetaPresenter.obtenerTarjetas();
        //iTarjetaPresenter.añadirTarjeta(getContext());

        adapter = new ToDoAdapter(new ArrayList<Tarjeta>(), getContext());
        rv.setAdapter(adapter);


        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeTarjeta);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iTarjetaPresenter.obtenerTarjetas();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                },1000);
            }



        });


        //adapter.setData(listaTarjetas);
        //this.loadData(rv,adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }


    @Override
    public void actualizarTarjetas() {
//        adapter.setData(listaTarjetas);
    }

    @Override
    public void limpiar() {
        adapter.setData(new ArrayList<Tarjeta>());
    }

    @Override
    public void añadirTarjetas(List<Tarjeta> listaTarjetas) {
        adapter.setData(listaTarjetas);
    }

    @Override
    public void finalizarRefresh() {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }
}
