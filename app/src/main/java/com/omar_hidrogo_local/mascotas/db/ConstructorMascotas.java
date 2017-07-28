package com.omar_hidrogo_local.mascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.omar_hidrogo_local.mascotas.R;
import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 24/07/2017.
 */

// clase donde interactua con la fuente de datos   BASE DE DATOS O WEB SERVICES
public class ConstructorMascotas {

    public static final int Like = 1;
    private Context context;

    public ConstructorMascotas(Context context){
        this.context = context;

    }

    //se declara la coleccion de datos  donde devuelve los contactos
    public ArrayList<Mascota> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        insertarDiezMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public ArrayList<Mascota>obtenerTopLike(){

        BaseDatos db = new BaseDatos(context);
        return db.obtenerTopMascotas();
    }

    public void insertarDiezMascotas (BaseDatos db){


        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "PEPITO");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p2);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "PONCHITO");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p6);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "MACKI");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p7);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "TOBI");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p8);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "COQUI");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p9);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "COCO");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p10);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "FELIPITO");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p11);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "CAPUSHI");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p12);

        db.insertarMascotas(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE, "QUIQUI");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.p13);

        db.insertarMascotas(contentValues);
    }

    public void darLikeMascota (Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTAS, mascota.getId());
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, Like);
        db.insertarLikeMascota(contentValues);
    }

    public  int obtenerLikesMascotas(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerLikesMascotas(mascota);
    }

}
