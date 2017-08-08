package com.omar_hidrogo_local.mascotas.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.omar_hidrogo_local.mascotas.R;
import com.omar_hidrogo_local.mascotas.adaptador.MascotaAdaptador;
import com.omar_hidrogo_local.mascotas.adaptador.PerfilAdaptador;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;
import com.omar_hidrogo_local.mascotas.presentador.IRecyclerViewFragmentPresent;
import com.omar_hidrogo_local.mascotas.presentador.RecyclerViewFragmentPresent;
import com.omar_hidrogo_local.mascotas.restApi.EndpointsApi;
import com.omar_hidrogo_local.mascotas.restApi.adaptador.RestApiAdapter;
import com.omar_hidrogo_local.mascotas.restApi.model.MascotaResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPerfilDog extends Fragment{

    //Lista de Mascotas
    private ArrayList<Mascota> mascotas;

    //variable de lista Mascotas
    private RecyclerView rvPerfilMascotas;
    CircularImageView imagep;
    TextView  tvMascotaPerfil;

    public FragmentPerfilDog() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        //Asociar el fragment al recycler view
        View v = inflater.inflate(R.layout.activity_fragment_perfil_dog, container, false);

        //enlazar la variable de tipo RecyclerView al id del layout
        rvPerfilMascotas = (RecyclerView) v.findViewById(R.id.rvPerfilMascotas);
        imagep = (CircularImageView) v.findViewById(R.id.imagep);
        tvMascotaPerfil = (TextView) v.findViewById(R.id.tvMascotaPerfil);

        //declarar el administrador del recyclerview
       /* LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);*/

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);

        //la lista de mascotas ligarla al LinearLayoutManager
        rvPerfilMascotas.setLayoutManager(glm);
        inicializarListaMascotas();
        //inicializaAdaptador();

        return  v;
    }


    //inicializar el adaptador
    public PerfilAdaptador adaptador;
    private  void inicializaAdaptador(){
        adaptador = new PerfilAdaptador(mascotas, getActivity());
        rvPerfilMascotas.setAdapter(adaptador);
    }


    // inicializar  la lista de Mascotas mediante el constructor de Mascota
    public  void  iinicializarListaMascotas(){

        mascotas = new ArrayList<Mascota>();

        //Se llena el arreglo por medio del constructor creado en la clase Mascota
       /* mascotas.add(new Mascota(R.drawable.pp2, "29"));
        mascotas.add(new Mascota(R.drawable.pp2, "15"));
        mascotas.add(new Mascota(R.drawable.pp2, "25"));
        mascotas.add(new Mascota(R.drawable.pp2, "5"));
        mascotas.add(new Mascota(R.drawable.pp2, "7"));
        mascotas.add(new Mascota(R.drawable.pp2, "8"));
        mascotas.add(new Mascota(R.drawable.pp2, "30"));
        mascotas.add(new Mascota(R.drawable.pp2, "20"));
        mascotas.add(new Mascota(R.drawable.pp2, "8"));*/
    }

    public void inicializarAdaptador(){
        PerfilAdaptador adaptador = new PerfilAdaptador(mascotas, getActivity());
        rvPerfilMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<>();
        mascotas = new ArrayList<>();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        SharedPreferences miPreferencia = this.getContext().getSharedPreferences("instagram", Context.MODE_PRIVATE);
        String idInstagram = miPreferencia.getString("id", "");
        String urlInstagram = miPreferencia.getString("url","" /*"http://epaper2.mid-day.com/images/no_image_thumb.gif"*/);
        String fullName = miPreferencia.getString("fullName", " ");
        tvMascotaPerfil.setText(fullName);
        Call<MascotaResponse> mascotaResponseCall;
        if (idInstagram == "")
            mascotaResponseCall = endpointsApi.getRecentMedia();
        else{
            mascotaResponseCall = endpointsApi.getMediaById(idInstagram);
            Picasso.with(this.getActivity())
                    .load(urlInstagram)
                    .placeholder(R.drawable.p2)
                    .into(imagep);

        }

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {

            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                inicializarAdaptador();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error en la conexion", Toast.LENGTH_SHORT).show();
                Log.e("Error en la conexion", t.toString());
            }
        });
    }



}
