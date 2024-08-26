package com.bortoletti.api.controller;

import com.bortoletti.api.paciente.DadosPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("paciente")
public class pacienteController {
    @PostMapping
    public void cadastrar(@RequestBody DadosPaciente dados){
        System.out.println(dados);
    }
}
