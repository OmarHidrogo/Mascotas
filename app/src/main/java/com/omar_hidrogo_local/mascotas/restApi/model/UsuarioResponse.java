package com.omar_hidrogo_local.mascotas.restApi.model;

/**
 * Created by tmhidrooma on 11/08/2017.
 */

public class UsuarioResponse {
    private String id;
    private String token;
    private String usuario_instagram;
    private String foto_instagram;

    public UsuarioResponse(String id, String token, String usuario_instagram, String foto_instagram) {
        this.id = id;
        this.token = token;
        this.usuario_instagram = usuario_instagram;
        this.foto_instagram = foto_instagram;
    }

    public UsuarioResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario_instagram() {
        return usuario_instagram;
    }

    public void setUsuario_instagram(String usuario_instagram) {
        this.usuario_instagram = usuario_instagram;
    }

    public String getFoto_instagram() {
        return foto_instagram;
    }

    public void setFoto_instagram(String foto_instagram) {
        this.foto_instagram = foto_instagram;
    }
}
