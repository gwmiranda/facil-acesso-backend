package org.unibrasil.entity;

import org.unibrasil.entity.response.UsuarioResponse;

public class Auth {

    private UsuarioResponse usuario;
    private String token;
    private String tipoAutenticacao = "Bearer";

    public Auth() {
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return this.token;
    }

    public String getTipoAutenticacao() {
        return this.tipoAutenticacao;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return tipoAutenticacao + token;
    }
}
