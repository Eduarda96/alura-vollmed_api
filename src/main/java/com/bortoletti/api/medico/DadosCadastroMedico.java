package com.bortoletti.api.medico;

import com.bortoletti.api.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade,
                                  DadosEndereco endereco) {
}
