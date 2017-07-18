package com.omar_hidrogo_local.mascotas.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omar_hidrogo_local.mascotas.R;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 18/07/2017.
 */

public class MascotaPerfilAdaptador extends RecyclerView.Adapter<MascotaPerfilAdaptador.MascotaPerfilViewHolder> {

    ArrayList<Mascota> perfilmascotas;
    Activity activity;

    //
    public MascotaPerfilAdaptador(ArrayList<Mascota> perfilmascotas, Activity activity){
        this.perfilmascotas=perfilmascotas;
        this.activity=activity;

    }



    //inflar el layout y pasa a view hlder para que el obtenga los datos
    @Override
    public MascotaPerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Ligar el Layout cardview_mascotas al Adaptador
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfilmascota, parent, false);

        //Se envia el View al constructor  MascotasViewHolder
        return new MascotaPerfilViewHolder(v);
    }



    //Se Asocia cada elemento de la lista con cada vista
    @Override
    public void onBindViewHolder(MascotaPerfilViewHolder mascotaperfilViewHolder, int position) {
        final Mascota perfilmascota = perfilmascotas.get(position);

        mascotaperfilViewHolder.imgFoto.setImageResource(perfilmascota.getFoto());
        mascotaperfilViewHolder.tvlikes.setText(perfilmascota.getLikes());


    }

    //Se declara el tamano de la lista
    @Override
    public int getItemCount() {
        return perfilmascotas.size();
    }

    public class MascotaPerfilViewHolder extends RecyclerView.ViewHolder {

        //Declarar las variables de los views en este caso las imagenes de las mascotas y el nombre
        private ImageView imgFoto;
        private TextView tvlikes;


        //Constructor heredado de RecyclerView.ViewHolder
        public MascotaPerfilViewHolder(View itemView) {
            super(itemView);
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgperfilfoto);
            tvlikes         = (TextView) itemView.findViewById(R.id.tvperfillikes);
        }
    }
}
