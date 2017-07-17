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
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

import static com.omar_hidrogo_local.mascotas.R.id.rvMascotas;

public class Fragment_RecyclerView extends Fragment {

    //Lista de Mascotas
    private ArrayList<Mascota> mascotas;

    //variable de lista Mascotas
    private RecyclerView listaMascotas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        //Asociar el fragment al recycler view
        View v = inflater.inflate(R.layout.activity_fragment__recycler_view, container, false);

        //enlazar la variable de tipo RecyclerView al id del layout
        listaMascotas = (RecyclerView) v.findViewById(rvMascotas);

        //declarar el administrador del recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //la lista de mascotas ligarla al LinearLayoutManager
        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializaAdaptador();

        return  v;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment__recycler_view);
    }*/

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
        mascotas.add(new Mascota(R.drawable.p2, "SHIH TZU", "29"));
        mascotas.add(new Mascota(R.drawable.p6, "CHIHUAHUEÑO", "15"));
        mascotas.add(new Mascota(R.drawable.p9, "PUG", "25"));
        mascotas.add(new Mascota(R.drawable.p8, "BOXER", "5"));
        mascotas.add(new Mascota(R.drawable.p13, "LABRADOR RETRIEVER", "7"));
        mascotas.add(new Mascota(R.drawable.p10, "BOXER", "8"));
        mascotas.add(new Mascota(R.drawable.p11, "PUG", "30"));
        mascotas.add(new Mascota(R.drawable.p12, "BREED COLLIE", "20"));
        mascotas.add(new Mascota(R.drawable.p7, "LABRADOR RETRIEVER", "8"));
    }
}
