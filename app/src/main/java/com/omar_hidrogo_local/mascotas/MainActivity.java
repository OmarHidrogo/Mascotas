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
import com.omar_hidrogo_local.mascotas.adaptador.PageAdapter;
import com.omar_hidrogo_local.mascotas.fragment.FragmentPerfilDog;
import com.omar_hidrogo_local.mascotas.fragment.Fragment_RecyclerView;
import com.omar_hidrogo_local.mascotas.restApi.EndpointsApi;
import com.omar_hidrogo_local.mascotas.restApi.adaptador.RestApiAdapter;
import com.omar_hidrogo_local.mascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

//importar el id de RECYCLERVIEW asignado en activity_main
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.omar_hidrogo_local.mascotas.R.id.rvMascotas;
import static com.omar_hidrogo_local.mascotas.R.id.toolbar;
import static com.omar_hidrogo_local.mascotas.R.id.tabLayout;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


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



}
