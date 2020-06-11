package br.com.wirecard.mocks.controller;

import br.com.wirecard.mocks.model.EnderecoDTO;
import br.com.wirecard.mocks.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public EnderecoDTO buscaEnderecoPorCep(@PathVariable String cep) {
        System.out.println("Entrou no controller");
        EnderecoDTO enderecoDTO = enderecoService.buscaEnderecoPorCep(cep);
        System.out.println("Retorno: " + enderecoDTO);
        return enderecoDTO;
    }
}