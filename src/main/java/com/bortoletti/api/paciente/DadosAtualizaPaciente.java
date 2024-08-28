package com.bortoletti.api.paciente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPaciente(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone) {
}
