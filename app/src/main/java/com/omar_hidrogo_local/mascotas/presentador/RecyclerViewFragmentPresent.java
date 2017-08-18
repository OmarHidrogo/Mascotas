package com.omar_hidrogo_local.mascotas.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.omar_hidrogo_local.mascotas.db.ConstructorMascotas;
import com.omar_hidrogo_local.mascotas.fragment.IRecyclerViewFragmentView;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;
import com.omar_hidrogo_local.mascotas.restApi.EndpointsApi;
import com.omar_hidrogo_local.mascotas.restApi.adaptador.RestApiAdapter;
import com.omar_hidrogo_local.mascotas.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tmhidrooma on 24/07/2017.
 */

public class RecyclerViewFragmentPresent implements IRecyclerViewFragmentPresent {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;


    public RecyclerViewFragmentPresent(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        //obtenerMascotasBaseDatos();
        obtenerMediosRecientes();
    }
    // en automatico  se ejecuta
    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();                                           //conexion a web services
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();                 //se prepara objeto Gson  realizando una deserializadorpersonalizado
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);//Establecemos la conexion y pasamos el objeto gsonMediaRecent

       /* SharedPreferences miPreferencia = context.getSharedPreferences("instagram", context.MODE_PRIVATE);
        String idInstagram = miPreferencia.getString("id","");*/



        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();


        /*Call<MascotaResponse> mascotaResponseCall;
        if(idInstagram == "")
            mascotaResponseCall = endpointsApi.getRecentMedia();
        else
            mascotaResponseCall = endpointsApi.getMediaLiked();*/

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            //eventos de la peticion
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                SharedPreferences miPreferenciaUser = context.getSharedPreferences("mascota", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = miPreferenciaUser.edit();
                editor.putString("id", mascotas.get(0).getId());
                editor.commit();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso en la conexion intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        //se inicializa el adaptador  con un lista de mascotas
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarGridLayout();
    }
}
