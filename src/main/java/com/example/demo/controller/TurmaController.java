package com.example.demo.controller;

import com.example.demo.dto.TurmaDto;
import com.example.demo.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    
    @Autowired
    private TurmaService turmaService;
    
    @PostMapping
    public ResponseEntity<TurmaDto> createTurma(@Valid @RequestBody TurmaDto turmaDto) {
        TurmaDto createdTurma = turmaService.createTurma(turmaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTurma);
    }
    
    @GetMapping("/{turmaId}")
    public ResponseEntity<TurmaDto> getTurmaById(@PathVariable Long turmaId) {
        TurmaDto turma = turmaService.getTurmaById(turmaId);
        return ResponseEntity.ok(turma);
    }
    
    @GetMapping
    public ResponseEntity<List<TurmaDto>> getAllTurmas() {
        List<TurmaDto> turmas = turmaService.getAllTurmas();
        return ResponseEntity.ok(turmas);
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<TurmaDto>> getTurmasByNome(@PathVariable String nome) {
        List<TurmaDto> turmas = turmaService.getTurmasByNome(nome);
        return ResponseEntity.ok(turmas);
    }
    
    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<List<TurmaDto>> getTurmasByPeriodo(@PathVariable String periodo) {
        List<TurmaDto> turmas = turmaService.getTurmasByPeriodo(periodo);
        return ResponseEntity.ok(turmas);
    }
    
    @GetMapping("/curso/{curso}")
    public ResponseEntity<List<TurmaDto>> getTurmasByCurso(@PathVariable String curso) {
        List<TurmaDto> turmas = turmaService.getTurmasByCurso(curso);
        return ResponseEntity.ok(turmas);
    }
    
    @DeleteMapping("/{turmaId}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long turmaId) {
        turmaService.deleteTurma(turmaId);
        return ResponseEntity.noContent().build();
    }
}
