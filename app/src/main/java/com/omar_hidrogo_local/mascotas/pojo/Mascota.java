package com.omar_hidrogo_local.mascotas.pojo;

/**
 * Created by tmhidrooma on 10/07/2017.
 */

public class Mascota {
    //Se declara las variables
    private int id;
    private int foto;
    private String nombre;
    private int likes;

    //Se declara el constructor de las dos variables
    public Mascota(int foto, String nombre, int likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.likes=likes;
    }

    public Mascota (){

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

