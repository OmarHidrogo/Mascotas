package com.omar_hidrogo_local.mascotas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.omar_hidrogo_local.mascotas.restApi.EndpointsApi;
import com.omar_hidrogo_local.mascotas.restApi.adaptador.RestApiAdapter;
import com.omar_hidrogo_local.mascotas.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tmhidrooma on 24/08/2017.
 */

public class RelationshipUnfollow extends BroadcastReceiver {

    private Context context;
    private static final String ANIMAL_RECEPTOR = "gato";
    private static final String ANIMAL_EMISOR = "perroCF";

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "UNFOLLOW";
        /*String ACCION_KEY_VER_PERFIL = "VER_PERFIL";
        String ACCION_KEY_VER_USUARIO = "VER_USUARIO";*/

        String actionunfollow = intent.getAction();

        if (ACCION_KEY.equals(actionunfollow)) {
            changerelationship();
            Toast.makeText(context, "Siguiendo "+ ANIMAL_RECEPTOR, Toast.LENGTH_SHORT).show();
        }
    }

    private  void changerelationship(){
        SharedPreferences miPreferenciausuarioreciente = context.getSharedPreferences("mascota", Context.MODE_PRIVATE);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiInstagramsinDeserializar();

        String statusrelationship = "unfollow";
        Call<MascotaResponse> mascotaResponseCall = endpoints.changeRelationship(miPreferenciausuarioreciente.getString("id", ""),statusrelationship);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                Toast.makeText(context, "Se dejo de seguir a  "+ ANIMAL_RECEPTOR, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

            }
        });
    }
}
