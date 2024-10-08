package com.bortoletti.api.controller;

import com.bortoletti.api.domain.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico((dados));
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    /*@GetMapping
    public List<DadosListagemMedico> listar(){
       return repository.findAll().stream().map(DadosListagemMedico::new).toList(); //converte lista de medicos para DadosListamMedico
    }*/

    //paginacao
   /* @GetMapping
    public Page<DadosListagemMedico> listar(Pageable pagincao){
        return repository.findAll(pagincao).map(DadosListagemMedico::new);
    }*/

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) { // configurando um default para paginacao
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

   /* @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);

    }*/

    //todo exclusao logica, não apaga do banco de dados para manter o histórico só inativa
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.ativar();
        return ResponseEntity.ok(new DadosMedicoAtivado(medico));
    }

    @GetMapping("/listaInativos")
    public ResponseEntity<Page<DadosListagemMedico>> listarInativos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var medicosInativos = repository.findAllByAtivoFalse(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(medicosInativos);

    }


}
