package com.example.demo.repository;

import com.example.demo.entity.TurmaEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TurmaRepository {
    
    private final Map<Long, TurmaEntity> turmas = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public TurmaEntity save(TurmaEntity turma) {
        if (turma.getId() == null) {
            turma.setId(idGenerator.getAndIncrement());
        }
        turmas.put(turma.getId(), turma);
        return turma;
    }
    
    public Optional<TurmaEntity> findById(Long id) {
        return Optional.ofNullable(turmas.get(id));
    }
    
    public List<TurmaEntity> findAll() {
        return new ArrayList<>(turmas.values());
    }
    
    public List<TurmaEntity> findByNome(String nome) {
        return turmas.values().stream()
                .filter(turma -> turma.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }
    
    public List<TurmaEntity> findByPeriodo(String periodo) {
        return turmas.values().stream()
                .filter(turma -> turma.getPeriodo().equalsIgnoreCase(periodo))
                .collect(Collectors.toList());
    }
    
    public List<TurmaEntity> findByCurso(String curso) {
        return turmas.values().stream()
                .filter(turma -> turma.getCurso().equalsIgnoreCase(curso))
                .collect(Collectors.toList());
    }
    
    public boolean deleteById(Long id) {
        return turmas.remove(id) != null;
    }
}
