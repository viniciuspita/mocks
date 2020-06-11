package br.com.wirecard.mocks.service;

import br.com.wirecard.mocks.model.Email;
import br.com.wirecard.mocks.model.EnderecoDTO;
import br.com.wirecard.mocks.model.PessoaDTO;
import br.com.wirecard.mocks.model.db.Pessoa;
import br.com.wirecard.mocks.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EmailService emailService;

    public Pessoa criaPessoa(PessoaDTO pessoaDTO) {
        EnderecoDTO enderecoDTO = enderecoService.buscaEnderecoPorCep(pessoaDTO.getCep());
        String endereco = enderecoDTO.getLogradouro() + ", " + enderecoDTO.getBairro() + " - " + enderecoDTO.getUf();
        return pessoaRepository.save(new Pessoa(
                pessoaDTO.getNome(),
                pessoaDTO.getEmail(),
                pessoaDTO.getSenha(),
                endereco)
        );
    }

    public void alteraSenha(Integer id, String novaSenha) {
        Pessoa pessoa = pessoaRepository.findOne(id);
        pessoa.setSenha(novaSenha);

        pessoaRepository.save(pessoa);

        Email email = new Email();
        email.setDestinatario(pessoa.getEmail());
        email.setAssunto("Alteração de senha");
        email.setMensagem("Sua senha foi alterada com sucesso");

        emailService.enviar(email);
    }
}