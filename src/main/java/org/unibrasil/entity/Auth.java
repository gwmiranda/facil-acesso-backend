package org.unibrasil.entity;

public class Auth {

    private String nomeUsuario;
    private String token;
    private String tipoAutenticacao = "Bearer";

    public Auth() {
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public String getToken() {
        return this.token;
    }

    public String getTipoAutenticacao() {
        return this.tipoAutenticacao;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return tipoAutenticacao + token;
    }
}
