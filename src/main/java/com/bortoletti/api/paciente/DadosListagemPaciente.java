package com.bortoletti.api.paciente;

public record DadosListagemPaciente(String nome, String email) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail());
    }
}
