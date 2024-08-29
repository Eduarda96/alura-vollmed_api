package com.bortoletti.api.medico;

public record DadosMedicoAtivado(Long id, String nome, String email, boolean ativo) {

    public DadosMedicoAtivado (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.isAtivo());
    }
}
