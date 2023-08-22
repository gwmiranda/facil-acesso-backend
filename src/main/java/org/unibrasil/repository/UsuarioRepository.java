package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Usuario;

import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Optional<Usuario> buscarUsuario(String login, String senha) {
        var sql = "SELECT u FROM Usuario u WHERE u.login = ?1 AND u.senha = ?2";
        return find(sql, login, senha).singleResultOptional();
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        var sql = "SELECT u FROM Usuario u WHERE u.email = ?1";
        return find(sql, email).singleResultOptional();
    }

    public Optional<Usuario> buscarUsuarioPorCpf(String cpf) {
        var sql = "SELECT u FROM Usuario u WHERE u.cpf = ?1";
        return find(sql, cpf).singleResultOptional();
    }

    public Optional<Usuario> buscarUsuarioPorLogin(String login) {
        var sql = "SELECT u FROM Usuario u WHERE u.login = ?1";
        return find(sql, login).singleResultOptional();
    }
}
