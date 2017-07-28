package com.omar_hidrogo_local.mascotas.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar_hidrogo_local.mascotas.R;
import com.omar_hidrogo_local.mascotas.adaptador.MascotaAdaptador;
import com.omar_hidrogo_local.mascotas.adaptador.MascotaTopLikeAdaptador;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;
import com.omar_hidrogo_local.mascotas.presentador.IRecyclerViewFragmentPresent;
import com.omar_hidrogo_local.mascotas.presentador.RecyclerViewFragmentPresent;
import com.omar_hidrogo_local.mascotas.presentador.RecyclerViewFragmentTopLikePresent;

import java.util.ArrayList;

public class Fragment_RecyclerViewMascotasTopLike extends Fragment implements IRecyclerViewFragmentMascotasTopLike {

    //Lista de Mascotas
    private ArrayList<Mascota> mascotas;

    //variable de lista Mascotas
    private RecyclerView listaMascotas;

    //Presentador
    private RecyclerViewFragmentTopLikePresent present;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        //Asociar el fragment al recycler view
        View v = inflater.inflate(R.layout.activity_fragment__recycler_view_mascotas_top_like, container, false);

        //enlazar la variable de tipo RecyclerView al id del layout
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotasLikes);
        present = new RecyclerViewFragmentTopLikePresent(this, getContext());

       /*

        inicializarListaMascotas();
        inicializaAdaptador();*/

        return  v;
    }


    @Override
    public void generarLinerLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaTopLikeAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaTopLikeAdaptador adaptador = new MascotaTopLikeAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaTopLikeAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
