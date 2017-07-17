package com.omar_hidrogo_local.mascotas.pojo;

/**
 * Created by tmhidrooma on 10/07/2017.
 */

public class Mascota {
    //Se declara las variables
    private int foto;
    private String nombre;
    private String likes;

    //Se declara el constructor de las dos variables
    public Mascota(int foto, String nombre, String likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.likes=likes;
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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}

