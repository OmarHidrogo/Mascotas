package com.omar_hidrogo_local.mascotas.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.omar_hidrogo_local.mascotas.db.ConstructorMascotas;
import com.omar_hidrogo_local.mascotas.fragment.IRecyclerViewFragmentView;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;
import com.omar_hidrogo_local.mascotas.pojo.Relationship;
import com.omar_hidrogo_local.mascotas.restApi.EndpointsApi;
import com.omar_hidrogo_local.mascotas.restApi.adaptador.RestApiAdapter;
import com.omar_hidrogo_local.mascotas.restApi.model.MascotaResponse;
import com.omar_hidrogo_local.mascotas.restApi.model.RelationshipResponse;

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
    private ArrayList<Relationship> relationships;

    private  String statusrelationship ="";
    public Menu menu;


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
                statusRelationship();
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

   public void statusRelationship(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        SharedPreferences miPreferenciausuarioreciente = context.getSharedPreferences("mascota", Context.MODE_PRIVATE);
        Gson gsonRelationshipStatus = restApiAdapter.construyeGsonDeserializadorStatusRelationship();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonRelationshipStatus);
        Call<RelationshipResponse> relationshipResponseCall = endpointsApi.statusRelationship(miPreferenciausuarioreciente.getString("id", ""));
        relationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
            @Override
            public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {
                RelationshipResponse relationshipResponse = response.body();
                relationships = relationshipResponse.getRelationships();
                SharedPreferences miRelationshipstatus = context.getSharedPreferences("relationshipstatus", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = miRelationshipstatus.edit();
                editor.putString("outgoing_status", relationships.get(0).getOutgoing_status());
                editor.putString("incoming_status", relationships.get(0).getIncoming_status());
                editor.commit();
                menu.add(Menu.NONE, 105, Menu.NONE, "Seguir");
                MenuItem menuItem = menu.findItem(105);
                SharedPreferences miRelation = context.getSharedPreferences("relationshipstatus", Context.MODE_PRIVATE);
                if (miRelation.getString("outgoing_status", "")!= "none"){
                    menuItem.setTitle("Dejar de Seguir");
                    statusrelationship = "none";

                }else{
                    menuItem.setTitle("Seguir");
                    statusrelationship = "follow";
                }
            }

            @Override
            public void onFailure(Call<RelationshipResponse> call, Throwable t) {
                Log.e("ER. STATUS RELATIONSHIP", t.toString());
            }
        });
    }
}
