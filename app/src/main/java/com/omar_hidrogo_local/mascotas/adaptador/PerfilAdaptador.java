package com.omar_hidrogo_local.mascotas.adaptador;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omar_hidrogo_local.mascotas.R;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 19/07/2017.
 */

public class PerfilAdaptador  extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder>{

    ArrayList<Mascota> masc;
    Activity activity;


    public PerfilAdaptador(ArrayList<Mascota> masc, Activity activity){
        this.masc=masc;
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
        final Mascota mascota = masc.get(position);

        //perfilViewHolder.imgFoto.setImageResource(mascota.getFoto());
        perfilViewHolder.tvlikespm.setText(String.valueOf(mascota.getLikes()));
       // perfilViewHolder.tvlikes.setText(mascota.getLikes());

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.p2)
                .into(perfilViewHolder.imgFotopm);

        perfilViewHolder.btnwithlikeperfilpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Like", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return masc.size();
    }

    public class PerfilViewHolder extends RecyclerView.ViewHolder {

        //Declarar las variables de los views en este caso las imagenes de las mascotas y el nombre
        private ImageView imgFotopm;
        private TextView tvlikespm;
        private ImageButton btnwithlikeperfilpm;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            imgFotopm                   = (ImageView) itemView.findViewById(R.id.imgperfilFotopm);
            tvlikespm                   = (TextView) itemView.findViewById(R.id.tvperfillikespm);
            btnwithlikeperfilpm         = (ImageButton) itemView.findViewById(R.id.btnwithlikeperfilpm);
        }
    }
}
