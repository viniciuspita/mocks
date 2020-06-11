package br.com.wirecard.mocks.service;

import br.com.wirecard.mocks.model.Email;
import br.com.wirecard.mocks.model.EnderecoDTO;
import br.com.wirecard.mocks.model.PessoaDTO;
import br.com.wirecard.mocks.model.db.Pessoa;
import br.com.wirecard.mocks.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private EmailService emailService;

    @Test
    public void testaSaveNoBanco() {
        PessoaDTO pessoaDTO = this.constroiPessoaDTODefault();
        EnderecoDTO enderecoDTO = this.constroiEnderecoDTODefault();
        when(enderecoService.buscaEnderecoPorCep(pessoaDTO.getCep())).thenReturn(enderecoDTO);

        pessoaService.criaPessoa(pessoaDTO);

        Mockito.verify(pessoaRepository).save(isA(Pessoa.class));
    }

    @Test
    public void testaAlteracaoDeSenhaEEnvioDeEmail() {
        Pessoa pessoa = constroiPessoaDefault();
        when(pessoaRepository.findOne(1)).thenReturn(pessoa);

        pessoaService.alteraSenha(1, "novaSenha");

        verify(pessoaRepository).save(pessoa);
        verify(emailService).enviar(notNull(Email.class));
    }

    @Test
    public void testaAlteracaoDeSenhaEEnvioDeEmailNaOrdem() {
        Pessoa pessoa = constroiPessoaDefault();
        when(pessoaRepository.findOne(1)).thenReturn(pessoa);

        pessoaService.alteraSenha(1, "novaSenha");

        InOrder inOrder = inOrder(pessoaRepository, emailService);

        inOrder.verify(pessoaRepository).save(pessoa);
        inOrder.verify(emailService).enviar(notNull(Email.class));
    }

    @Test
    public void testaEnvioDeEmailComDestinatarioCorreto() {
        Pessoa pessoa = constroiPessoaDefault();
        when(pessoaRepository.findOne(1)).thenReturn(pessoa);

        pessoaService.alteraSenha(1, "novaSenha");

        ArgumentCaptor<Email> emailCaptor = ArgumentCaptor.forClass(Email.class);

        verify(emailService).enviar(emailCaptor.capture());

        Email email = emailCaptor.getValue();
        assertEquals(pessoa.getEmail(), email.getDestinatario());
        assertEquals("Alteração de senha", email.getAssunto());
        assertEquals("Sua senha foi alterada com sucesso", email.getMensagem());
    }

    private PessoaDTO constroiPessoaDTODefault() {
        return new PessoaDTO(
                "Vinicius",
                "vinicius.pita@wirecard.com",
                "root",
                "07025160"
        );
    }

    private EnderecoDTO constroiEnderecoDTODefault() {
        return new EnderecoDTO(
                "07025-160",
                "Rua José Momo",
                "",
                "Vila Augusta",
                "Guarulhos",
                "SP"
        );
    }

    private Pessoa constroiPessoaDefault() {
        return new Pessoa(
                1,
                "Vinicius",
                "vinicius.pita@wirecard.com",
                "root",
                "Rua José Momo, Vila Augusta - SP"
        );
    }
}