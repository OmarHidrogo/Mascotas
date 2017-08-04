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
 * Created by tmhidrooma on 19/07/2017.
 */

public class PerfilAdaptador  extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;


    public PerfilAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas=mascotas;
        this.activity=activity;

    }

    //inflar el layout y pasa a view hlder para que el obtenga los datos
    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Ligar el Layout cardview_mascotas al Adaptador
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfilmascota, parent,false);

        //Se envia el View al constructor  MascotasViewHolder
        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PerfilViewHolder perfilViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

      //  perfilViewHolder.imgFoto.setImageResource(mascota.getFoto());
        perfilViewHolder.tvlikes.setText(mascota.getLikes());

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class PerfilViewHolder extends RecyclerView.ViewHolder {

        //Declarar las variables de los views en este caso las imagenes de las mascotas y el nombre
        private ImageView imgFoto;
        private TextView tvlikes;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgperfilFoto);
            tvlikes         = (TextView) itemView.findViewById(R.id.tvperfillikes);
        }
    }
}
