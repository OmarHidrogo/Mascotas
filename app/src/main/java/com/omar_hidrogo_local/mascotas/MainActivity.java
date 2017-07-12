package com.omar_hidrogo_local.mascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

//importar el id de RECYCLERVIEW asignado en activity_main
import static com.omar_hidrogo_local.mascotas.R.id.rvMascotas;

public class MainActivity extends AppCompatActivity {

    //Lista de Mascotas
    ArrayList<Mascota> mascotas;

    //variable de lista Mascotas
    private RecyclerView listaMascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazar la variable de tipo RecyclerView al id del layout
        listaMascotas = (RecyclerView) findViewById(rvMascotas);

        //declarar el administrador del recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //la lista de mascotas ligarla al LinearLayoutManager
        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializaAdaptador();
    }





    //inicializar el adaptador
    public MascotaAdaptador adaptador;
    private  void inicializaAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }


    // inicializar  la lista de Mascotas mediante el constructor de Mascota
    public  void  inicializarListaMascotas(){

        mascotas = new ArrayList<Mascota>();

        //Se llena el arreglo por medio del constructor creado en la clase Mascota
        mascotas.add(new Mascota(R.drawable.p2, "SHIH TZU"));
        mascotas.add(new Mascota(R.drawable.p6, "CHIHUAHUEÑO"));
        mascotas.add(new Mascota(R.drawable.p9, "PUG"));
        mascotas.add(new Mascota(R.drawable.p8, "BOXER"));
        mascotas.add(new Mascota(R.drawable.p7, "LABRADOR RETRIEVER"));

    }
}
