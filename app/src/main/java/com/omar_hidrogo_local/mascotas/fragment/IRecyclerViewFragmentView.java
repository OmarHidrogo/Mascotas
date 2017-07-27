package com.omar_hidrogo_local.mascotas.fragment;

import com.omar_hidrogo_local.mascotas.adaptador.MascotaAdaptador;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 26/07/2017.
 */

public interface IRecyclerViewFragmentView {

    //metodo para generar el liner layout  de forma vertical
    public void generarLinerLayoutVertical();

    //metodo para devolver mascotas Adaptador  genera un  adaptador
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    //inicializa el adaptador con el recyclerview
    public  void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
