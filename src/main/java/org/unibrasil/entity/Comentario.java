package org.unibrasil.entity;

import jakarta.persistence.*;
import org.unibrasil.entity.dto.ComentarioDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long idComentario;
    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private TipoEstabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "comentario_acessibilidade",
            joinColumns = @JoinColumn(name = "comentario_id"),
            inverseJoinColumns = @JoinColumn(name = "acessibilidade_id")
    )
    private List<Acessibilidade> acessibilidades = new ArrayList<>();

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorito> favoritos = new ArrayList<>();

    @OneToMany(mappedBy = "comentario")
    private List<Curtida> curtidas = new ArrayList<>();


    private String complemento;
    private String rua;
    private int numero;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;
    private Integer nivelSatisfacao;
    private String comentario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;
    private LocalDateTime dataRemocao;



    public Comentario() {
    }

    public Comentario(int id) {
        this.id = id;
    }

    public Comentario(ComentarioDTO dto) {
            this.idComentario = dto.getIdComentario();
            this.estabelecimento = new TipoEstabelecimento(dto.getEstabelecimentoId());
            this.complemento = dto.getComplemento();
            this.rua = dto.getRua();
            this.numero = dto.getNumero();
            this.bairro = dto.getBairro();
            this.estado = dto.getEstado();
            this.cidade = dto.getCidade();
            this.cep = dto.getCep();
            this.nivelSatisfacao = dto.getNivelSatisfacao();
            this.comentario = dto.getComentario();
            this.usuario = new Usuario(dto.getUsuario());
            this.acessibilidades = dto.getAcessibilidade().stream().map(Acessibilidade::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public TipoEstabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(TipoEstabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNivelSatisfacao() {
        return nivelSatisfacao;
    }

    public void setNivelSatisfacao(Integer nivelSatisfacao) {
        this.nivelSatisfacao = nivelSatisfacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(LocalDateTime dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public LocalDateTime getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(LocalDateTime dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Acessibilidade> getAcessibilidades() {
        return acessibilidades;
    }

    public void setAcessibilidades(List<Acessibilidade> acessibilidades) {
        this.acessibilidades = acessibilidades;
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
