package com.omar_hidrogo_local.mascotas.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar_hidrogo_local.mascotas.R;
import com.omar_hidrogo_local.mascotas.adaptador.MascotaAdaptador;
import com.omar_hidrogo_local.mascotas.adaptador.MascotaPerfilAdaptador;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class FragmentPerfilDog extends Fragment {

    //Lista de Mascotas
    private ArrayList<Mascota> mascotas;

    //variable de lista Mascotas
    private RecyclerView listaMascotas;



    /* public FragmentPerfilDog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_perfil_dog, container, false);
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        //Asociar el fragment al recycler view
        View v = inflater.inflate(R.layout.activity_fragment_perfil_dog, container, false);

        //enlazar la variable de tipo RecyclerView al id del layout
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvPerfilMascotas);

        //declarar el administrador del recyclerview
       /* LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);*/

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);

        //la lista de mascotas ligarla al LinearLayoutManager
        listaMascotas.setLayoutManager(glm);
        inicializarListaMascotas();
        inicializaAdaptador();

        return  v;
    }


    //inicializar el adaptador
    public MascotaAdaptador adaptador;
    private  void inicializaAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }


    // inicializar  la lista de Mascotas mediante el constructor de Mascota
    public  void  inicializarListaMascotas(){

        mascotas = new ArrayList<Mascota>();

        //Se llena el arreglo por medio del constructor creado en la clase Mascota
        mascotas.add(new Mascota(R.drawable.pp2, "29"));
        mascotas.add(new Mascota(R.drawable.pp2, "15"));
        mascotas.add(new Mascota(R.drawable.pp2, "25"));
        mascotas.add(new Mascota(R.drawable.pp2, "5"));
        mascotas.add(new Mascota(R.drawable.pp2, "7"));
        mascotas.add(new Mascota(R.drawable.pp2, "8"));
        mascotas.add(new Mascota(R.drawable.pp2, "30"));
        mascotas.add(new Mascota(R.drawable.pp2, "20"));
        mascotas.add(new Mascota(R.drawable.pp2, "8"));
    }


}
