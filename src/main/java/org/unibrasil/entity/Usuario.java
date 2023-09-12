package org.unibrasil.entity;

import jakarta.persistence.*;
import org.unibrasil.entity.dto.UsuarioDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String login;
    private String senha;
    private String telefone;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_acessibilidade",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "acessibilidade_id"))
    private List<Acessibilidade> acessibilidades;
    private LocalDate dataNascimento;
    private Instant dataCriacao;
    private Instant dataEdicao;
    private Instant dataRemocao;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorito> favoritos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Curtida> curtidas = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(UsuarioDTO usuarioDTO) {
        this.login = usuarioDTO.getLogin();
        this.senha = usuarioDTO.getSenha();
        this.telefone = usuarioDTO.getTelefone();
        this.cpf = usuarioDTO.getCpf();
        this.acessibilidades = usuarioDTO.getAcessibilidade().stream().map(Acessibilidade::new).collect(Collectors.toList());
        this.dataNascimento = usuarioDTO.getDataNascimento();
        this.email = usuarioDTO.getEmail();
    }

    public Usuario(long id) {
        this.id = id;
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

    public List<Acessibilidade> getAcessibilidades() {
        return acessibilidades;
    }

    public void setAcessibilidades(List<Acessibilidade> acessibilidade) {
        this.acessibilidades = acessibilidade;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Curtida> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Curtida> curtidas) {
        this.curtidas = curtidas;
    }
}
