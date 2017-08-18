package com.omar_hidrogo_local.mascotas.adaptador;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omar_hidrogo_local.mascotas.DetalleMascota;
import com.omar_hidrogo_local.mascotas.db.ConstructorMascotas;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;
import com.omar_hidrogo_local.mascotas.R;
import com.squareup.picasso.Picasso;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_mascota, parent, false);

        //Se envia el View al constructor  MascotasViewHolder
        return new MascotaViewHolder(v);
    }

    /*private  boolean esPar(int n){
        if ((n%2)==0){
            return true;
        }else {
            return false;
        }
    }*/

    //Se Asocia cada elemento de la lista con cada vista
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
    final Mascota mascota = mascotas.get(position);

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.p2)
                .into(mascotaViewHolder.imgFotop);

       /* mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvnombremascota.setText(mascota.getNombre());*/
        mascotaViewHolder.tvlikesp.setText(String.valueOf(mascota.getLikes()));

        /*if ((position%2)==0){
            mascotaViewHolder.imgFoto.setBackgroundColor(Color.parseColor("#fa689a"));
        }else {
            mascotaViewHolder.imgFoto.setBackgroundColor(Color.parseColor("#6379f9"));
        }*/

        mascotaViewHolder.imgFotop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetalleMascota.class);
                intent.putExtra("url", mascota.getUrlFoto());
                intent.putExtra("likes", mascota.getLikes());
                intent.putExtra("idmedia",mascota.getIdmedia());
                activity.startActivity(intent);
            }
        });

       /* mascotaViewHolder.btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity," Diste Like a "+mascota.getNombre(),Toast.LENGTH_SHORT).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                mascotaViewHolder.tvlikes.setText(String.valueOf(constructorMascotas.obtenerLikesMascotas(mascota)+ " Likes"));
            }
        });*/
    }

    //Se declara el tamano de la lista
    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class MascotaViewHolder extends RecyclerView.ViewHolder {

        //Declarar las variables de los views en este caso las imagenes de las mascotas y el nombre
        private ImageView imgFotop;
       // private TextView tvnombremascota;
        private TextView tvlikesp;
        //private ImageButton btnlike;


        //Constructor heredado de RecyclerView.ViewHolder
        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFotop         = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvlikesp         = (TextView)  itemView.findViewById(R.id.tvLikes1);
           /* tvnombremascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);*/
          //  btnlike         = (ImageButton) itemView.findViewById(R.id.btnlike);
        }
    }
}
