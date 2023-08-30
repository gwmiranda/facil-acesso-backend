package org.unibrasil.entity;

import jakarta.persistence.*;
import org.unibrasil.entity.dto.ComentarioDTO;

import java.time.LocalDateTime;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long idComentario;
    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private TipoEstabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "acessibilidade_id")
    private Acessibilidade acessibilidade;

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
            this.acessibilidade = new Acessibilidade(dto.getAcessibilidade());
    }

    public int getId() {
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

    public Acessibilidade getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(Acessibilidade acessibilidade) {
        this.acessibilidade = acessibilidade;
    }
}
