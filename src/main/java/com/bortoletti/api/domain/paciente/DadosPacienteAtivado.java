package com.bortoletti.api.domain.paciente;

public record DadosPacienteAtivado(Long id, String nome, String email, boolean ativo) {
    public DadosPacienteAtivado(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getNome(), paciente.isAtivo());
    }
}
