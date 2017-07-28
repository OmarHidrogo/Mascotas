package com.omar_hidrogo_local.mascotas.presentador;

import android.content.Context;

import com.omar_hidrogo_local.mascotas.db.ConstructorMascotas;
import com.omar_hidrogo_local.mascotas.fragment.IRecyclerViewFragmentMascotasTopLike;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 28/07/2017.
 */

public class RecyclerViewFragmentTopLikePresent implements IRecyclerViewFragmentPresent {

    private IRecyclerViewFragmentMascotasTopLike iRecyclerViewFragmentMascotasTopLike;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentTopLikePresent(IRecyclerViewFragmentMascotasTopLike iRecyclerViewFragmentMascotasTopLike, Context context){

        this.iRecyclerViewFragmentMascotasTopLike = iRecyclerViewFragmentMascotasTopLike;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerTopLike();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        //se inicializa el adaptador  con un lista de mascotas
        iRecyclerViewFragmentMascotasTopLike.inicializarAdaptadorRV(iRecyclerViewFragmentMascotasTopLike.crearAdaptador(mascotas));
        iRecyclerViewFragmentMascotasTopLike.generarLinerLayoutVertical();
    }


}
