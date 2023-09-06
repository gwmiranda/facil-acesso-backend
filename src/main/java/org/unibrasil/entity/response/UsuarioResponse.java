package org.unibrasil.entity.response;

import org.unibrasil.entity.Acessibilidade;
import org.unibrasil.entity.Usuario;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class UsuarioResponse {

    private long id;
    private String login;
    private String senha;
    private String telefone;
    private String email;
    private String cpf;
    private List<Acessibilidade> acessibilidades;
    private LocalDate dataNascimento;
    private Instant dataCriacao;
    private Instant dataEdicao;
    private Instant dataRemocao;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.cpf = usuario.getCpf();
        this.acessibilidades = usuario.getAcessibilidades();
        this.dataNascimento = usuario.getDataNascimento();
        this.dataCriacao = usuario.getDataCriacao();
        this.dataEdicao = usuario.getDataEdicao();
        this.dataRemocao = usuario.getDataRemocao();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Acessibilidade> getAcessibilidades() {
        return acessibilidades;
    }

    public void setAcessibilidades(List<Acessibilidade> acessibilidades) {
        this.acessibilidades = acessibilidades;
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
