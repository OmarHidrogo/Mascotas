package com.omar_hidrogo_local.mascotas.restApi.model;

/**
 * Created by tmhidrooma on 11/08/2017.
 */

public class UsuarioResponse {
    private String id;
    private String token;
    private String id_usuario_instagram;

    public UsuarioResponse(String id, String token, String id_usuario_instagram) {
        this.id = id;
        this.token = token;
        this.id_usuario_instagram = id_usuario_instagram;
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

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
}
