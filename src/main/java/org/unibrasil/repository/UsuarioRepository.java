package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Usuario;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
}
