package com.omar_hidrogo_local.mascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.omar_hidrogo_local.mascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by tmhidrooma on 24/07/2017.
 */

//extportar la libreria de base de datos SQLIteOpenHelper
public class BaseDatos  extends SQLiteOpenHelper{

    //se declara variable tipo Context
    private  Context context;

    private int campos = 0;

    //se crea el constructor
    public BaseDatos(Context context) {

        //SE CREA BASE DE DATOS
        super(context, ConstanteBaseDatos.DATABASE_NAME, null, ConstanteBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //SE CREA QUERY PARA CREAR LA TABLA MASCOTAS
        String queryCrearTablaMascotas = "CREATE TABLE "+ConstanteBaseDatos.TABLE_MASCOTAS + "("+
                ConstanteBaseDatos.TABLE_MASCOTAS_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteBaseDatos.TABLE_MASCOTAS_NOMBRE    + " TEXT, "+
                ConstanteBaseDatos.TABLE_MASCOTAS_FOTO      + " INTEGER"+
                ")";


        //SE CREA QUERY PARA CREAR LA TABLE MASCOTAS LIKE
        String queryCrearTablaLikesMascotas = "CREATE TABLE "+ ConstanteBaseDatos.TABLE_LIKES_MASCOTAS + "("+
                ConstanteBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTAS + " INTEGER, "+
                ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " INTEGER, "+
                "FOREIGN KEY (" + ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTAS + ") "+
                "REFERENCES "+ConstanteBaseDatos.TABLE_MASCOTAS + "("+ConstanteBaseDatos.TABLE_MASCOTAS_ID+")"+
                ")";

        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaLikesMascotas);

    }

    //solo se ejecuta si solo se necesita restructurar  la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST "+ ConstanteBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST" + ConstanteBaseDatos.TABLE_LIKES_MASCOTAS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        //SE DECLARA LA QUERY DE SELECCIONAR TODA LA BASE DE DATOS
        String query = " SELECT * FROM "+ ConstanteBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
          /*  Mascota mascotaActual = new Mascota();
            //INDICE DE LA COLUMNA  DE LA TABLA
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes ="SELECT COUNT("+ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES
                    +") as likes "+" FROM "+ ConstanteBaseDatos.TABLE_LIKES_MASCOTAS +
                    " WHERE "+ ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTAS +
                    " = "+ mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setLikes(registrosLikes.getInt(0));
            }else{
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);*/
        }
        db.close();
        return  mascotas;
    }

    public void insertarMascotas(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor numT = db.rawQuery("SELECT * FROM "+ConstanteBaseDatos.TABLE_MASCOTAS, null);
        int n = numT.getCount();
        if(n<= 4){
            db.insert(ConstanteBaseDatos.TABLE_MASCOTAS, null, contentValues);
            db.close();
        }

    }


    public void insertarLikeMascota(ContentValues contentValues){
        //SE PONE LA BASE DE DATOS DE MODO ESCRITURA
        SQLiteDatabase db = this.getWritableDatabase();

        //USAR LA TABLA MASCOTA LIKES
        db.insert(ConstanteBaseDatos.TABLE_LIKES_MASCOTAS, null, contentValues );
        db.close();
    }


    public int obtenerLikesMascotas (Mascota mascota){
        int likes = 0;
        String query = "SELECT COUNT("+ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES
                +") FROM " + ConstanteBaseDatos.TABLE_LIKES_MASCOTAS +
                " WHERE " + ConstanteBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTAS +
                " = " + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registro = db.rawQuery(query, null);

        if(registro.moveToNext()){
            likes = registro.getInt(0);
        }
        db.close();

        return likes;
    }


   /* public void isTableExist(String mascotas){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT mascotas from mascotas WHERE mascotas = '"+ mascotas + "'", null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                campos = cursor.getCount();
        }
        }
    }*/

}
