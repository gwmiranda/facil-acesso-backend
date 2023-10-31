package org.unibrasil.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.unibrasil.entity.Curtida;
import org.unibrasil.repository.ComentarioRepository;
import org.unibrasil.repository.CurtidaRepository;
import org.unibrasil.repository.UsuarioRepository;

import java.time.LocalDateTime;

@ApplicationScoped
public class CurtidaService {

    @Inject
    CurtidaRepository curtidaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    ComentarioRepository comentarioRepository;

    @Transactional
    public void gravarCurtida(Curtida curtida) throws ValidationException {
        var usuario = usuarioRepository.findById(curtida.getUsuario().getId());

        if (usuario == null) {
            throw new ValidationException("Usuário não encontrado!");
        }

        var comentario = comentarioRepository.findById(curtida.getComentario().getId());

        if (comentario == null) {
            throw new ValidationException("Comentário não encontrado!");
        }

        var curtidaBuscada = curtidaRepository.buscarPorUsuarioEComentario(curtida);

        if (curtidaBuscada != null) {
            throw new ValidationException("Curtida já realizada!");
        }

        curtida.setDataCriacao(LocalDateTime.now());
        curtidaRepository.persist(curtida);
    }

    @Transactional
    public void deletarPorId(Curtida curtida) throws ValidationException {
        var curtidaBuscada = curtidaRepository.buscarPorUsuarioEComentario(curtida);

        if (curtidaBuscada == null) {
            throw new ValidationException("Curtida não encontrada para deleção");
        }

        if (curtidaBuscada.getDataRemocao() != null) {
            throw new ValidationException("Curtida já deletada");
        }

        curtidaRepository.delete(curtidaBuscada);
    }
}
