package org.unibrasil.entity.dto;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDTO {

    private String login;
    private String senha;
    private String telefone;
    private String cpf;
    private List<Long> acessibilidade;
    private LocalDate dataNascimento;
    private String email;

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

    public List<Long> getAcessibilidade() {
        return acessibilidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }
}
