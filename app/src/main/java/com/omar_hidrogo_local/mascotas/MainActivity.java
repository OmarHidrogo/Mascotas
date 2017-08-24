package com.omar_hidrogo_local.mascotas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.omar_hidrogo_local.mascotas.adaptador.PageAdapter;
import com.omar_hidrogo_local.mascotas.fragment.FragmentPerfilDog;
import com.omar_hidrogo_local.mascotas.fragment.Fragment_RecyclerView;
import com.omar_hidrogo_local.mascotas.pojo.Relationship;
import com.omar_hidrogo_local.mascotas.restApi.EndpointsApi;
import com.omar_hidrogo_local.mascotas.restApi.adaptador.RestApiAdapter;
import com.omar_hidrogo_local.mascotas.restApi.model.MascotaResponse;
import com.omar_hidrogo_local.mascotas.restApi.model.RelationshipResponse;
import com.omar_hidrogo_local.mascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

//importar el id de RECYCLERVIEW asignado en activity_main
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.omar_hidrogo_local.mascotas.R.id.list_item;
import static com.omar_hidrogo_local.mascotas.R.id.rvMascotas;
import static com.omar_hidrogo_local.mascotas.R.id.toolbar;
import static com.omar_hidrogo_local.mascotas.R.id.tabLayout;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private  String statusrelationship ="";
    private Menu menu;

    private ArrayList<Relationship> relationships;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

       setUpViewPager();

        //establecer informacion de accion bar
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayShowTitleEnabled(false);
        }


    }

    /*private void updateMenuTitles(Menu menu) {
        statusRelationship();
        menu.add(Menu.NONE, 105, Menu.NONE, "Seguir");
        MenuItem menuItem = menu.findItem(105);
        SharedPreferences miRelation = getSharedPreferences("relationshipstatus", Context.MODE_PRIVATE);
        if (miRelation.getString("outgoing_status", "")!= "none"){
            menuItem.setTitle("Dejar de Seguir");
            statusrelationship = "none";

        }else{
            menuItem.setTitle("Seguir");
            statusrelationship = "follow";
        }
      }*/


    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
          fragments.add(new Fragment_RecyclerView());
          fragments.add(new FragmentPerfilDog());
        return fragments;
    }

    private void setUpViewPager() {

       viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_dog_house_48);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_dog_48);
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
      // menu.add(Menu.NONE, 105, Menu.NONE, "Seguir");
       //updateMenuTitles();
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
            case R.id.acerca:
                Intent intent3 = new Intent(this, Acerca_de.class);
                this.startActivity(intent3);
                break;
            case R.id.settings:
                Intent intent4 = new Intent(this, Login.class);
                this.startActivity(intent4);
                break;
            case R.id.notification:
                //Intent intent4 = new Intent(this, Login.class);
                //this.startActivity(intent4);
                enviarTokenRegistro(FirebaseInstanceId.getInstance().getToken());
                break;
            case 105:
                changerelationship();
        }
      return super.onOptionsItemSelected(item);
    }



    public void enviarToken(View v) {

        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro (String token){
        Log.d("TOKEN", token);
        SharedPreferences miPreferencia = getSharedPreferences("instagram", Context.MODE_PRIVATE);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPI();
        //Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token);
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token, miPreferencia.getString("id",""));

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                Log.d("ID_INSTAGRAM_USUARIO", usuarioResponse.getUsuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    private  void changerelationship(){
        SharedPreferences miPreferenciausuarioreciente = getSharedPreferences("mascota", Context.MODE_PRIVATE);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiInstagramsinDeserializar();

        Call<MascotaResponse> mascotaResponseCall = endpoints.changeRelationship(miPreferenciausuarioreciente.getString("id", ""),statusrelationship);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

            }
        });
    }

   /* public void mostrarMascotasRV() {
        //se inicializa el adaptador  con un lista de mascotas
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarGridLayout();
    }*/

    public void statusRelationship(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        SharedPreferences miPreferenciausuarioreciente = getSharedPreferences("mascota", Context.MODE_PRIVATE);
        Gson gsonRelationshipStatus = restApiAdapter.construyeGsonDeserializadorStatusRelationship();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonRelationshipStatus);
        Call<RelationshipResponse> relationshipResponseCall = endpointsApi.statusRelationship(miPreferenciausuarioreciente.getString("id", ""));
        relationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
            @Override
            public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {
                RelationshipResponse relationshipResponse = response.body();
                relationships = relationshipResponse.getRelationships();
                SharedPreferences miRelationshipstatus = getSharedPreferences("relationshipstatus", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = miRelationshipstatus.edit();
                editor.putString("outgoing_status", relationships.get(0).getOutgoing_status());
                editor.putString("incoming_status", relationships.get(0).getIncoming_status());
                editor.commit();
            }

            @Override
            public void onFailure(Call<RelationshipResponse> call, Throwable t) {

            }
        });
    }




}
