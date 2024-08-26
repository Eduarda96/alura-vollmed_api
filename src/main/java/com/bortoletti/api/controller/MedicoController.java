package com.bortoletti.api.controller;

import com.bortoletti.api.medico.DadosCadastroMedico;
import com.bortoletti.api.medico.DadosListagemMedico;
import com.bortoletti.api.medico.Medico;
import com.bortoletti.api.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar(){
       return repository.findAll().stream().map(DadosListagemMedico::new).toList(); //converte lista de medicos para DadosListamMedico
    }
}
