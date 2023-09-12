package org.unibrasil.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.unibrasil.entity.Favorito;
import org.unibrasil.entity.response.ComentarioFavoritadoResponse;
import org.unibrasil.repository.ComentarioRepository;
import org.unibrasil.repository.FavoritoRepository;
import org.unibrasil.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class FavoritoService {

    @Inject
    FavoritoRepository favoritoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    ComentarioRepository comentarioRepository;

    @Transactional
    public void gravarFavorito(Favorito favorito) throws ValidationException {
        var usuario = usuarioRepository.findById(favorito.getUsuario().getId());

        if (usuario == null) {
            throw new ValidationException("Usuário não encontrado!");
        }

        var comentario = comentarioRepository.findById(favorito.getComentario().getId());

        if (comentario == null) {
            throw new ValidationException("Comentário não encontrado!");
        }

        favorito.setDataCriacao(LocalDateTime.now());
        favoritoRepository.persist(favorito);
    }

    @Transactional
    public List<Favorito> buscarFavoritoPorUsuario(long idUsuario) {
        return favoritoRepository.buscarFavoritoPorUsuario(idUsuario);
    }

    @Transactional
    public ComentarioFavoritadoResponse buscarPorId(long id) throws ValidationException {
        var favorito = favoritoRepository.findById(id);

        if (favorito == null) {
            throw new ValidationException("Comentário favoritado não encontrado");
        }

        return new ComentarioFavoritadoResponse(
                favorito.getComentario().getId(),
                favorito.getComentario().getComentario(),
                favorito.getComentario().getAcessibilidade(),
                favorito.getComentario().getDataCriacao(),
                favorito.getComentario().getDataRemocao(),
                favorito.getComentario().getCurtidas().size());
    }

    @Transactional
    public void deletarPorId(long id) throws ValidationException {
        var acessibilidade = favoritoRepository.findById(id);

        if (acessibilidade == null) {
            throw new ValidationException("Favorito não encontrada para deleção");
        }

        acessibilidade.setDataRemocao(LocalDateTime.now());
        favoritoRepository.persist(acessibilidade);
    }
}
