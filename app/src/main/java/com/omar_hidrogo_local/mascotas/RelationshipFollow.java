package com.omar_hidrogo_local.mascotas;

/**
 * Created by tmhidrooma on 24/08/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.omar_hidrogo_local.mascotas.restApi.EndpointsApi;
import com.omar_hidrogo_local.mascotas.restApi.adaptador.RestApiAdapter;
import com.omar_hidrogo_local.mascotas.restApi.model.MascotaResponse;
import com.omar_hidrogo_local.mascotas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  RelationshipFollow extends BroadcastReceiver {

    private Context context;
    private static final String ANIMAL_RECEPTOR = "gato";
    private static final String ANIMAL_EMISOR = "perroCF";

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "FOLLOW";
        /*String ACCION_KEY_VER_PERFIL = "VER_PERFIL";
        String ACCION_KEY_VER_USUARIO = "VER_USUARIO";*/

        String actionfollow = intent.getAction();

        if (ACCION_KEY.equals(actionfollow)) {
            //changerelationship();
            enviarNotificacion();
            Toast.makeText(context, "Siguiendo "+ ANIMAL_RECEPTOR, Toast.LENGTH_SHORT).show();
        }
    }

    private  void changerelationship(){
        SharedPreferences miPreferenciausuarioreciente = context.getSharedPreferences("mascota", Context.MODE_PRIVATE);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiInstagramsinDeserializar();

        String statusrelationship = "follow";
        Call<MascotaResponse> mascotaResponseCall = endpoints.changeRelationship(miPreferenciausuarioreciente.getString("id", ""),statusrelationship);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                Toast.makeText(context, "Siguiendo "+ ANIMAL_RECEPTOR, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

            }
        });
    }


    public void enviarNotificacion(){
        Log.d("TOQUE_ANIMAL", "true");
        //final UsuarioResponse usuarioResponse = new UsuarioResponse("-KrpZtICwQAjRLrUBCof","123","5810080353","123");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.toquerelationship("-KsL0hOQ8LERawgjrUUP","jorge avila");/*usuarioResponse.getAnimal());*/
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 = response.body();
                Log.d("ID_FIREBASE", usuarioResponse1.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse1.getToken());
                Log.d("PHOTO_FIREBASE", usuarioResponse1.getUsuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
