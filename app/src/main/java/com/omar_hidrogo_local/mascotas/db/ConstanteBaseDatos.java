package com.omar_hidrogo_local.mascotas.db;

/**
 * Created by tmhidrooma on 24/07/2017.
 */

public class ConstanteBaseDatos {

    //Constantes que se utilizaran para el uso de querys en la base de datos

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_MASCOTAS = "mascotas";
    public static final String TABLE_MASCOTAS_ID = "id";
    public static final String TABLE_MASCOTAS_NOMBRE = "nombre";
    public static final String TABLE_MASCOTAS_FOTO = "foto";



    public static final String TABLE_LIKES_MASCOTAS = "mascota_likes";
    public static final String TABLE_LIKES_MASCOTAS_ID = "id";
    public static final String TABLE_LIKES_MASCOTAS_ID_MASCOTAS = "id_mascotas";
    public static final String TABLE_LIKES_MASCOTAS_NUMERO_LIKES = "numero_likes";

}
