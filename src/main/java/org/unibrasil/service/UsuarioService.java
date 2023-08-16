package org.unibrasil.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.unibrasil.entity.Usuario;
import org.unibrasil.repository.UsuarioRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public void criarUsuario(Usuario usuario) {
        usuario.setDataCriacao(Instant.now());
        usuarioRepository.persist(usuario);
    }

    @Transactional
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll().stream().toList();
    }

    @Transactional
    public Usuario buscarPorId(long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario atualizarUsuario(long id, Usuario usuario) {
        var usuarioBuscado = usuarioRepository.findById(id);
        usuarioBuscado.setLogin(usuario.getLogin());
        usuarioBuscado.setSenha(usuario.getSenha());
        usuarioBuscado.setTelefone(usuario.getTelefone());
        usuarioBuscado.setAcessibilidade(usuario.getAcessibilidade());
        usuarioBuscado.setDataNascimento(usuario.getDataNascimento());
        usuarioBuscado.setDataEdicao(Instant.now());

        usuarioRepository.persist(usuarioBuscado);

        return buscarPorId(id);
    }

    @Transactional
    public boolean deletarPorId(long id) {
        return usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> realizarLogin(String login, String senha) {
        return usuarioRepository.buscarUsuario(login, senha);
    }
}
