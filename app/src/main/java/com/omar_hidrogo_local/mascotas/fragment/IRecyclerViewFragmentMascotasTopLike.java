package com.omar_hidrogo_local.mascotas.fragment;


import com.omar_hidrogo_local.mascotas.adaptador.MascotaTopLikeAdaptador;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 28/07/2017.
 */

public interface IRecyclerViewFragmentMascotasTopLike {

    //metodo para generar el liner layout  de forma vertical
    public void generarLinerLayoutVertical();

    //metodo para devolver mascotas Adaptador  genera un  adaptador
    public MascotaTopLikeAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    //inicializa el adaptador con el recyclerview
    public  void inicializarAdaptadorRV(MascotaTopLikeAdaptador adaptador);
}
