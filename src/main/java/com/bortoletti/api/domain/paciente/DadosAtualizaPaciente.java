package com.bortoletti.api.domain.paciente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPaciente(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone) {
}
