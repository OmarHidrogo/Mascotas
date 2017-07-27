package com.omar_hidrogo_local.mascotas.presentador;

import android.content.Context;

import com.omar_hidrogo_local.mascotas.db.ConstructorMascotas;
import com.omar_hidrogo_local.mascotas.fragment.IRecyclerViewFragmentView;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 24/07/2017.
 */

public class RecyclerViewFragmentPresent implements IRecyclerViewFragmentPresent {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;


    public RecyclerViewFragmentPresent(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }
    // en automatico  se ejecuta
    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        //se inicializa el adaptador  con un lista de mascotas
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinerLayoutVertical();
    }
}
