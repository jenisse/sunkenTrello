package com.example.android.sunkentrello.Presenter;

import android.arch.core.util.Function;
import android.content.Context;

import com.example.android.sunkentrello.Model.Tarjeta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseManager {

    public static final String TARJETA_REFERENCE = "tarjeta";

    private static FirebaseManager instance = new FirebaseManager();
    private Context context;
    private FirebaseManager(){}

    public static FirebaseManager getInstance(Context context) {
        if(instance.context == null) {
            instance.context = context;
        }
        return instance;
    }

    private Tarjeta crearTarjetaFake(){
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNombre("To Do Fragments");
        tarjeta.setDescripcion("La wea fragments");
        tarjeta.setFecha("02/02/02");
        return tarjeta;
    }

    public String AñadirTarjeta(){
        Tarjeta tarjeta = crearTarjetaFake();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String key = ref.child(TARJETA_REFERENCE).push().getKey();
        tarjeta.setId(key);
        ref.child(TARJETA_REFERENCE).child(key).setValue(tarjeta);
        return key;
    }

    public void ObtenerListaTarjetasToDo(final Function<List<Tarjeta>,Boolean> funcionLista){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child(TARJETA_REFERENCE).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    List<Tarjeta> listaTarjetas = new ArrayList<>();
                    for(DataSnapshot tarjetaSnapshot : dataSnapshot.getChildren()){
                        String key = tarjetaSnapshot.getKey();
                        System.out.println("KEY:"+key);
                        Tarjeta tarjeta = tarjetaSnapshot.getValue(Tarjeta.class);
                        tarjeta.setId(key);
                        listaTarjetas.add(tarjeta);
                    }
                    funcionLista.apply(listaTarjetas);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
