package org.unibrasil.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.unibrasil.entity.Acessibilidade;
import org.unibrasil.repository.AcessibilidadeRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AcessibilidadeService {

    @Inject
    AcessibilidadeRepository acessibilidadeRepository;

    @Transactional
    public void gravarAcessibilidade(Acessibilidade acessibilidade) throws ValidationException {
        if (acessibilidade.getDescricao().isBlank()) {
            throw new ValidationException("Acessiblidade está vazia!");
        }

        var acessibilidadeBuscada = acessibilidadeRepository.buscarAcessiblidadePorDescricao(acessibilidade.getDescricao());

        if (acessibilidadeBuscada.isPresent()) {
            throw new ValidationException("Acessiblidade já cadastrada!");
        }

        acessibilidade.setDataCriacao(LocalDateTime.now());
        acessibilidadeRepository.persist(acessibilidade);
    }

    @Transactional
    public List<Acessibilidade> buscarTodasAcessibilidades() {
        return acessibilidadeRepository.buscarTodos();
    }

    @Transactional
    public Acessibilidade buscarPorId(long id) throws ValidationException {
        var usuario = acessibilidadeRepository.findById(id);

        if (usuario == null) {
            throw new ValidationException("Acessibilidade não encontrado");
        }

        return usuario;
    }

    @Transactional
    public Acessibilidade atualizar(long id, Acessibilidade acessibilidade) throws ValidationException {
        if (acessibilidade.getDescricao().isBlank()) {
            throw new ValidationException("Descrição está vazia");
        }

        var acessibilidadeBuscada = buscarPorId(id);

        acessibilidadeBuscada.setDescricao(acessibilidade.getDescricao());
        acessibilidadeBuscada.setDataEdicao(LocalDateTime.now());
        acessibilidadeRepository.persist(acessibilidadeBuscada);

        return buscarPorId(id);
    }

    @Transactional
    public void deletarPorId(long id) throws ValidationException {
        var acessibilidade = acessibilidadeRepository.findById(id);

        if (acessibilidade == null) {
            throw new ValidationException("Acessiblidade não encontrada para deleção");
        }

        acessibilidade.setDataRemocao(LocalDateTime.now());
        acessibilidadeRepository.persist(acessibilidade);
    }
}
