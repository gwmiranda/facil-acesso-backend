package org.unibrasil.entity;

public class AlterarSenhaRequest {
    private long id;
    private String senhaAtual;
    private String senhaNova;

    public long getId() {
        return id;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public String getSenhaNova() {
        return senhaNova;
    }
}