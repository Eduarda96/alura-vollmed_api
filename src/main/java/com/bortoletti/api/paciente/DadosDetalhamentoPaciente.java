package com.bortoletti.api.paciente;

import com.bortoletti.api.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String telefone, Endereco endereco) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
    }
}
