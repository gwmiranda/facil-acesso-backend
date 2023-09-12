package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Curtida;

@ApplicationScoped
public class CurtidaRepository implements PanacheRepository<Curtida> {

}
