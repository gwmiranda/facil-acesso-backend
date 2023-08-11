package org.unibrasil.entity;

import jakarta.persistence.*;
import org.unibrasil.entity.dto.UsuarioDTO;

import java.time.Instant;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String senha;
    private String telefone;
    private String cpf;
    private String acessibilidade;
    private LocalDate dataNascimento;
    private Instant dataCriacao;
    private Instant dataEdicao;
    private Instant dataRemocao;

    public Usuario() {
    }

    public Usuario(UsuarioDTO usuarioDTO) {
        this.login = usuarioDTO.getLogin();
        this.senha = usuarioDTO.getSenha();
        this.telefone = usuarioDTO.getTelefone();
        this.cpf = usuarioDTO.getCpf();
        this.acessibilidade = usuarioDTO.getAcessibilidade();
        this.dataNascimento = usuarioDTO.getDataNascimento();
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(String acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(Instant dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public Instant getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(Instant dataRemocao) {
        this.dataRemocao = dataRemocao;
    }
}
