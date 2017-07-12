package com.omar_hidrogo_local.mascotas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 12/07/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    //
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas=mascotas;
        this.activity=activity;

    }



    //inflar el layout y pasa a view hlder para que el obtenga los datos
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Ligar el Layout cardview_mascotas al Adaptador
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        //Se envia el View al constructor  MascotasViewHolder
        return new MascotaViewHolder(v);
    }

    //Se Asocia cada elemento de la lista con cada vista
    @Override
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolder, int position) {
    final Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvnombremascota.setText(mascota.getNombre());

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, likes_mascotas.class);
                activity.startActivity(intent);
            }
        });

    }

    //Se declara el tamano de la lista
    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class MascotaViewHolder extends RecyclerView.ViewHolder {

        //Declarar las variables de los views en este caso las imagenes de las mascotas y el nombre
        private ImageView imgFoto;
        private TextView tvnombremascota;


        //Constructor heredado de RecyclerView.ViewHolder
        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvnombremascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);
        }
    }
}
