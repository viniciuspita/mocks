package br.com.wirecard.mocks.controller;

import br.com.wirecard.mocks.model.PessoaDTO;
import br.com.wirecard.mocks.model.db.Pessoa;
import br.com.wirecard.mocks.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public Pessoa criaPessoa(@RequestBody PessoaDTO pessoaDTO) {
        return pessoaService.criaPessoa(pessoaDTO);
    }

    @PutMapping("/{id}")
    public void alteraSenha(@PathVariable Integer id, @RequestBody String senha) {
        pessoaService.alteraSenha(id, senha);
    }
}