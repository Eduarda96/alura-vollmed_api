package com.bortoletti.api.paciente;

import com.bortoletti.api.endereco.DadosEndereco;

public record DadosPaciente(String nome, String email, DadosEndereco endereco) {
}
