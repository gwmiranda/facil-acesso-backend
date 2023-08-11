package org.unibrasil.entity.dto;

import java.time.LocalDate;

public class UsuarioDTO {

    private String login;
    private String senha;
    private String telefone;
    private String cpf;
    private String acessibilidade;
    private LocalDate dataNascimento;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getAcessibilidade() {
        return acessibilidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
