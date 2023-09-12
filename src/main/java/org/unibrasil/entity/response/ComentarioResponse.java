package org.unibrasil.entity.response;

import org.unibrasil.entity.Acessibilidade;
import org.unibrasil.entity.Comentario;
import org.unibrasil.entity.TipoEstabelecimento;

import java.time.LocalDateTime;

public class ComentarioResponse {

    private long id;
    private Long idComentario;
    private TipoEstabelecimento estabelecimento;
    private long usuario;
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
    private Integer curtidas;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;
    private LocalDateTime dataRemocao;

    public ComentarioResponse() {
    }

    public ComentarioResponse(int id) {
        this.id = id;
    }

    public ComentarioResponse(Comentario comentario) {
            this.id = comentario.getId();
            this.idComentario = comentario.getIdComentario();
            this.estabelecimento = comentario.getEstabelecimento();
            this.complemento = comentario.getComplemento();
            this.rua = comentario.getRua();
            this.numero = comentario.getNumero();
            this.bairro = comentario.getBairro();
            this.estado = comentario.getEstado();
            this.cidade = comentario.getCidade();
            this.cep = comentario.getCep();
            this.nivelSatisfacao = comentario.getNivelSatisfacao();
            this.comentario = comentario.getComentario();
            this.usuario = comentario.getId();
            this.acessibilidade = comentario.getAcessibilidade();
            this.curtidas = comentario.getCurtidas().size();
            this.dataCriacao = comentario.getDataCriacao();
            this.dataEdicao = comentario.getDataEdicao();
            this.dataRemocao = comentario.getDataRemocao();
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

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public Acessibilidade getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(Acessibilidade acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public Integer getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Integer curtidas) {
        this.curtidas = curtidas;
    }
}
