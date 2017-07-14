package com.omar_hidrogo_local.mascotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

//importar el id de RECYCLERVIEW asignado en activity_main
import static com.omar_hidrogo_local.mascotas.R.id.rvMascotas;

public class MainActivity extends AppCompatActivity {

    //Lista de Mascotas
    private ArrayList<Mascota> mascotas;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.top:
                Intent intent = new Intent(this, Mascotas_Likes.class);
                this.startActivity(intent);
                break;
            case R.id.mContact:
                Intent intent2 = new Intent(this, Contacto.class);
                this.startActivity(intent2);
                break;
            case R.id.mAbout:
                Intent intent3 = new Intent(this, Acerca_de.class);
                this.startActivity(intent3);

        }
      return super.onOptionsItemSelected(item);
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
