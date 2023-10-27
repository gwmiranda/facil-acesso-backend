package org.unibrasil.service;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.unibrasil.entity.Comentario;
import org.unibrasil.entity.response.ComentarioFavoritadoResponse;
import org.unibrasil.entity.response.ComentarioResponse;
import org.unibrasil.repository.AcessibilidadeRepository;
import org.unibrasil.repository.ComentarioRepository;
import org.unibrasil.repository.TipoEstabelecimentoRepository;
import org.unibrasil.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ComentarioService {

    @Inject
    ComentarioRepository comentarioRepository;

    @Inject
    TipoEstabelecimentoRepository tipoEstabelecimentoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    AcessibilidadeRepository acessibilidadeRepository;

    @Inject
    SecurityIdentity securityIdentity;

    @Transactional
    public void gravarComentario(Comentario comentario) throws ValidationException {
        if (comentario.getIdComentario() != null) {
            var comentarioId = comentarioRepository.findById(comentario.getIdComentario());

            if (comentarioId == null) {
                throw new ValidationException("Campo idComentario invalido");
            }
        }

        if (comentario.getUsuario() != null) {
            var usuarioId = usuarioRepository.findById(comentario.getUsuario().getId());

            if (usuarioId == null) {
                throw new ValidationException("Campo usuario invalido");
            }
        }

        validarComentario(comentario);

        comentario.setDataCriacao(LocalDateTime.now());
        comentarioRepository.persist(comentario);
    }

    @Transactional
    public Comentario atualizar(long id, Comentario comentario) throws ValidationException {
        validarComentario(comentario);

        var comentarioBuscado = comentarioRepository.findById(id);

        comentarioBuscado.setEstabelecimento(comentario.getEstabelecimento());
        comentarioBuscado.setAcessibilidades(comentario.getAcessibilidades());
        comentarioBuscado.setComplemento(comentario.getComplemento());
        comentarioBuscado.setRua(comentario.getRua());
        comentarioBuscado.setNumero(comentario.getNumero());
        comentarioBuscado.setBairro(comentario.getBairro());
        comentarioBuscado.setEstado(comentario.getEstado());
        comentarioBuscado.setCidade(comentario.getCidade());
        comentarioBuscado.setCep(comentario.getCep());
        comentarioBuscado.setNivelSatisfacao(comentario.getNivelSatisfacao());
        comentarioBuscado.setComentario(comentario.getComentario());
        comentarioBuscado.setDataEdicao(LocalDateTime.now());
        comentarioRepository.persist(comentarioBuscado);

        return comentarioRepository.findById(id);
    }

    @Transactional
    public List<ComentarioResponse> buscarPorId(long id) throws ValidationException {
        var comentario = comentarioRepository.findById(id);

        if (comentario == null) {
            throw new ValidationException("Comentario não encontrado");
        }

        return List.of(new ComentarioResponse(comentario, securityIdentity.getPrincipal().getName()));
    }

    @Transactional
    public void deletarPorId(long id) throws ValidationException {
        var comentario = comentarioRepository.findById(id);

        if (comentario == null) {
            throw new ValidationException("Comentario não encontrada para deleção");
        }

        comentario.setDataRemocao(LocalDateTime.now());
        comentarioRepository.persist(comentario);
    }

    @Transactional
    public List<ComentarioResponse> buscarComentariosPorUsuario(long id) throws ValidationException {
        var comentarios = comentarioRepository.buscarComentariosPorUsuario(id);

        if (comentarios == null) {
            throw new ValidationException("Comentario não encontrado");
        }

        return comentarios.stream().map(c -> new ComentarioResponse(c, securityIdentity.getPrincipal().getName())).collect(Collectors.toList());
    }

    @Transactional
    public List<ComentarioResponse> buscarTodosComentarios() {
        var comentarios = comentarioRepository.findAll();
        return comentarios.stream().map(c -> new ComentarioResponse(c, securityIdentity.getPrincipal().getName())).collect(Collectors.toList());
    }

    private void validarComentario(Comentario comentario) throws ValidationException {
        List<String> camposInvalidos = new ArrayList<>();

        if (comentario.getAcessibilidades() != null) {
            comentario.getAcessibilidades().stream().forEach(a -> {
                var acessibilidade = acessibilidadeRepository.findById(a.getId());

                if (acessibilidade == null) {
                    camposInvalidos.add("Acessibilidade");
                }
            });

        }

        var tipoEstabelecimentoId = tipoEstabelecimentoRepository.findById(comentario.getEstabelecimento().getId());

        if (tipoEstabelecimentoId == null) {
            camposInvalidos.add("estabelecimento");
        }

        if (comentario.getComplemento() == null || comentario.getComplemento().isEmpty()) {
            camposInvalidos.add("complemento");
        }

        if (comentario.getRua() == null || comentario.getRua().isEmpty()) {
            camposInvalidos.add("rua");
        }

        if (comentario.getNumero() <= 0) {
            camposInvalidos.add("numero");
        }

        if (comentario.getBairro() == null || comentario.getBairro().isEmpty()) {
            camposInvalidos.add("bairro");
        }

        if (comentario.getEstado() == null || comentario.getEstado().isEmpty()) {
            camposInvalidos.add("estado");
        }

        if (comentario.getCidade() == null || comentario.getCidade().isEmpty()) {
            camposInvalidos.add("cidade");
        }

        if (comentario.getCep() == null || comentario.getCep().isEmpty()) {
            camposInvalidos.add("cep");
        }

        if (comentario.getNivelSatisfacao() == null || comentario.getNivelSatisfacao() <= 0 || comentario.getNivelSatisfacao() > 5) {
            camposInvalidos.add("nivelSatisfacao");
        }

        if (comentario.getComentario() == null || comentario.getComentario().isEmpty()) {
            camposInvalidos.add("comentario");
        }

        if (!camposInvalidos.isEmpty()) {
            throw new ValidationException("Campos inválidos: " + String.join(", ", camposInvalidos));
        }
    }

    public List<ComentarioFavoritadoResponse> buscarComentariosFavoritos(long idUsuario) {
        var comentarios = comentarioRepository.buscarComentariosFavoritadosPorUsuario(idUsuario);
        return comentarios.stream()
                .map(ComentarioFavoritadoResponse::new)
                .collect(Collectors.toList());
    }
}
