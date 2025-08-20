package com.example.demo.service;

import com.example.demo.dto.TurmaDto;
import com.example.demo.entity.TurmaEntity;
import com.example.demo.exception.TurmaNotFoundException;
import com.example.demo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    
    @Autowired
    private TurmaRepository turmaRepository;
    
    public TurmaDto createTurma(TurmaDto turmaDto) {
        TurmaEntity entity = new TurmaEntity();
        entity.setNome(turmaDto.getNome());
        entity.setPeriodo(turmaDto.getPeriodo());
        entity.setCurso(turmaDto.getCurso());
        
        TurmaEntity savedEntity = turmaRepository.save(entity);
        return toDto(savedEntity);
    }
    
    public TurmaDto getTurmaById(Long id) {
        TurmaEntity entity = turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada com ID: " + id));
        return toDto(entity);
    }
    
    public List<TurmaDto> getAllTurmas() {
        List<TurmaEntity> entities = turmaRepository.findAll();
        if (entities.isEmpty()) {
            throw new TurmaNotFoundException("Nenhuma turma cadastrada");
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public List<TurmaDto> getTurmasByNome(String nome) {
        List<TurmaEntity> entities = turmaRepository.findByNome(nome);
        if (entities.isEmpty()) {
            throw new TurmaNotFoundException("Nenhuma turma encontrada com o nome: " + nome);
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public List<TurmaDto> getTurmasByPeriodo(String periodo) {
        List<TurmaEntity> entities = turmaRepository.findByPeriodo(periodo);
        if (entities.isEmpty()) {
            throw new TurmaNotFoundException("Nenhuma turma encontrada para o período: " + periodo);
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public List<TurmaDto> getTurmasByCurso(String curso) {
        List<TurmaEntity> entities = turmaRepository.findByCurso(curso);
        if (entities.isEmpty()) {
            throw new TurmaNotFoundException("Nenhuma turma encontrada para o curso: " + curso);
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public void deleteTurma(Long id) {
        if (!turmaRepository.deleteById(id)) {
            throw new TurmaNotFoundException("Turma não encontrada com ID: " + id);
        }
    }
    
    private TurmaDto toDto(TurmaEntity entity) {
        return new TurmaDto(entity.getId(), entity.getNome(), entity.getPeriodo(), entity.getCurso());
    }
}
