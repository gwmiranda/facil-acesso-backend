package org.unibrasil.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.unibrasil.entity.Usuario;
import org.unibrasil.entity.response.UsuarioResponse;
import org.unibrasil.repository.UsuarioRepository;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public void criarUsuario(Usuario usuario) throws ValidationException {
        validarUsuario(usuario);

        usuario.setDataCriacao(Instant.now());
        usuarioRepository.persist(usuario);
    }

    @Transactional
    public List<UsuarioResponse> buscarTodosUsuarios() {
        var todosUsuario = usuarioRepository.buscarTodos();
        return todosUsuario.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponse buscarPorId(long id) throws ValidationException {
        var usuario = usuarioRepository.findById(id);

        if (usuario == null) {
            throw new ValidationException("Usuário não encontrado");
        }

        return new UsuarioResponse(usuario);
    }

    @Transactional
    public void atualizarUsuario(long id, Usuario usuario) throws ValidationException {
        var usuarioBuscado = usuarioRepository.findById(id);

        usuarioBuscado.setLogin(usuario.getLogin());
        usuarioBuscado.setSenha(usuario.getSenha());
        usuarioBuscado.setTelefone(usuario.getTelefone());
        usuarioBuscado.setAcessibilidades(usuario.getAcessibilidades());
        usuarioBuscado.setDataNascimento(usuario.getDataNascimento());
        usuarioBuscado.setDataEdicao(Instant.now());
        usuarioBuscado.setEmail(usuario.getEmail());

        usuarioRepository.persistAndFlush(usuarioBuscado);
    }

    @Transactional
    public void deletarPorId(long id) throws ValidationException {
        var usuario = usuarioRepository.findById(id);

        if (usuario == null) {
            throw new ValidationException("Usuario não encontrado para deleção");
        }

        usuario.setDataRemocao(Instant.now());
        usuarioRepository.persist(usuario);
    }

    @Transactional
    public Usuario realizarLogin(String login, String senha) throws ValidationException {
        var usuario = usuarioRepository.buscarUsuario(login, senha);

        if (usuario.isEmpty()) {
            throw new ValidationException("Usuário e/ou senha incorretos");
        }

        return usuario.get();
    }

    private void validarUsuario(Usuario usuario) throws ValidationException {
        var usuarioLogin = usuarioRepository.buscarUsuarioPorLogin(usuario.getLogin());

        if (usuarioLogin.isPresent()) {
            throw new ValidationException("Login já cadastrado.");
        }

        var usuarioEmail = usuarioRepository.buscarUsuarioPorEmail(usuario.getEmail());

        if (usuarioEmail.isPresent()) {
            throw new ValidationException("Email já cadastrado.");
        }

        var usuarioCpf = usuarioRepository.buscarUsuarioPorCpf(usuario.getCpf());

        if (usuarioCpf.isPresent()) {
            throw new ValidationException("CPF já cadastrado.");
        }
    }

    @Transactional
    public void alterarSenha(long id, String senhaAtual, String senhaNova) throws ValidationException {
        var usuario = usuarioRepository.findById(id);

        if (usuario == null) {
            throw new ValidationException("Usuário não encontrado");

        }

        if (!Objects.equals(senhaAtual, usuario.getSenha())) {
            throw new ValidationException("Senha antiga inválida");
        }

        usuario.setSenha(senhaNova);
        usuarioRepository.persist(usuario);
    }

    @Transactional
    public String recuperarSenha(String email) throws ValidationException {
        var usuario = usuarioRepository.buscarUsuarioPorEmail(email);

        if (usuario.isEmpty()) {
            throw new ValidationException("Email não encontrado");
        }

        usuario.get().setSenha("123");
        usuarioRepository.persist(usuario.get());

        return  usuario.get().getSenha();
    }
}
