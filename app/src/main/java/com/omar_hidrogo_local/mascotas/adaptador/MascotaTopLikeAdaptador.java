package com.omar_hidrogo_local.mascotas.adaptador;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.omar_hidrogo_local.mascotas.R;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 28/07/2017.
 */

public class MascotaTopLikeAdaptador extends RecyclerView.Adapter<MascotaTopLikeAdaptador.MascotaTopLikeViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaTopLikeAdaptador(ArrayList<Mascota> mascotas, FragmentActivity activity) {
        this.mascotas=mascotas;
        this.activity=activity;
    }

    @Override
    public MascotaTopLikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Ligar el Layout cardview_mascotas al Adaptador
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        //Se envia el View al constructor  MascotasViewHolder
        return new MascotaTopLikeViewHolder(v);
    }

    private  boolean esPar(int n){
        if ((n%2)==0){
            return true;
        }else {
            return false;
        }
    }

    //Se Asocia cada elemento de la lista con cada vista
    @Override
    public void onBindViewHolder(MascotaTopLikeViewHolder mascotaTopLikeViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        mascotaTopLikeViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaTopLikeViewHolder.tvnombremascota.setText(mascota.getNombre());
        mascotaTopLikeViewHolder.tvlikes.setText(String.valueOf(mascota.getLikes())+" Likes");

        if ((position%2)==0){
            mascotaTopLikeViewHolder.imgFoto.setBackgroundColor(Color.parseColor("#fa689a"));
        }else {
            mascotaTopLikeViewHolder.imgFoto.setBackgroundColor(Color.parseColor("#6379f9"));
        }
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class MascotaTopLikeViewHolder extends RecyclerView.ViewHolder {

        //Declarar las variables de los views en este caso las imagenes de las mascotas y el nombre
        private ImageView imgFoto;
        private TextView tvnombremascota;
        private TextView tvlikes;
        private ImageButton btnlike;

        //Constructor heredado de RecyclerView.ViewHolder
        public MascotaTopLikeViewHolder(View itemView) {
            super(itemView);
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvnombremascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);
            tvlikes         = (TextView) itemView.findViewById(R.id.tvlikes);
            btnlike         = (ImageButton) itemView.findViewById(R.id.btnlike);
        }
    }
}
