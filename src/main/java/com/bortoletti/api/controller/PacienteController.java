package com.bortoletti.api.controller;

import com.bortoletti.api.paciente.DadosListagemPaciente;
import com.bortoletti.api.paciente.DadosPaciente;
import com.bortoletti.api.paciente.Paciente;
import com.bortoletti.api.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public List<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"})  Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPaciente::new).toList();
    }
}
