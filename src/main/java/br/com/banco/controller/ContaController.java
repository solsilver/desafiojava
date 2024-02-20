package br.com.banco.controller;

import br.com.banco.model.Conta;
import br.com.banco.model.dto.ContaDTO;
import br.com.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("conta")
public class ContaController {
    @Autowired
    private ContaService contaService;
    @PostMapping
    public ResponseEntity<?> criarConta(@RequestBody Conta conta) {
        Conta contaOutput = contaService.saveConta(conta);
        URI location = UriComponentsBuilder.fromUriString("http://localhost:8080/conta/{id}").
                buildAndExpand(contaOutput.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    @GetMapping
    public ResponseEntity <List<ContaDTO>> buscarContas( ) {

        List<ContaDTO> contas = contaService.getContas();
        return ResponseEntity.ok().body(contas);


    }
    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> buscarContaid( @PathVariable ("id") Long id ) {

        ContaDTO conta = contaService.getContaById(id);
        return ResponseEntity.ok().body(conta);

    }
}
