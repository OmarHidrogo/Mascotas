package com.omar_hidrogo_local.mascotas;

/**
 * Created by tmhidrooma on 10/07/2017.
 */

public class Mascota {
    //Se declara las variables
    private int foto;
    private String nombre;

    //Se declara el constructor de las dos variables
    public Mascota(int foto, String nombre) {
        this.foto = foto;
        this.nombre = nombre;
    }

    //Se declara los geter y seter
    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
