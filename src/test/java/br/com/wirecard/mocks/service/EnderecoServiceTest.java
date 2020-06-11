package br.com.wirecard.mocks.service;
import br.com.wirecard.mocks.model.EnderecoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoService enderecoServiceMock;

//    @Before
//    public void setup() {
//        enderecoServiceMock = mock(EnderecoService.class);
//    }

    @Test
    public void testaBuscaComCepDeGuarulhos() {
        String cepDeGuarulhos = "07025160";
        EnderecoDTO enderecoDTODefault = this.constroiEnderecoDefault();
        when(enderecoServiceMock.buscaEnderecoPorCep(cepDeGuarulhos)).thenReturn(enderecoDTODefault);

        EnderecoDTO enderecoDTODeGuarulhos = enderecoServiceMock.buscaEnderecoPorCep(cepDeGuarulhos);

        assertEquals("07025-160", enderecoDTODeGuarulhos.getCep());
        assertEquals("Rua José Momo", enderecoDTODeGuarulhos.getLogradouro());
    }

    @Test
    public void testaMaisDeUmaChamada() {
        String cepDeGuarulhos = "07025160";
        EnderecoDTO enderecoDTODefault = this.constroiEnderecoDefault();
        EnderecoDTO segundoEnderecoDTO = null;
        when(enderecoServiceMock.buscaEnderecoPorCep(cepDeGuarulhos)).thenReturn(enderecoDTODefault).thenReturn(segundoEnderecoDTO);

        EnderecoDTO enderecoDTODeGuarulhos = enderecoServiceMock.buscaEnderecoPorCep(cepDeGuarulhos);

        assertEquals("07025-160", enderecoDTODeGuarulhos.getCep());
        assertEquals("Rua José Momo", enderecoDTODeGuarulhos.getLogradouro());

        EnderecoDTO enderecoDTO2 = enderecoServiceMock.buscaEnderecoPorCep(cepDeGuarulhos);
        assertNull(enderecoDTO2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaBuscaComCepQueLancaException() {
        String cepErrado = "000";
        when(enderecoServiceMock.buscaEnderecoPorCep(cepErrado)).thenThrow(new IllegalArgumentException("Cep errado"));

        enderecoServiceMock.buscaEnderecoPorCep(cepErrado);
    }

    //Matchers
    @Test
    public void testaBuscaComQualquerCep() {
        EnderecoDTO enderecoDTODefault = this.constroiEnderecoDefault();
        when(enderecoServiceMock.buscaEnderecoPorCep(anyString())).thenReturn(enderecoDTODefault);


        String cepQualquer = "07025160";
        EnderecoDTO enderecoDTODeGuarulhos = enderecoServiceMock.buscaEnderecoPorCep(cepQualquer);

        assertEquals("07025-160", enderecoDTODeGuarulhos.getCep());
        assertEquals("Rua José Momo", enderecoDTODeGuarulhos.getLogradouro());
    }

    private EnderecoDTO constroiEnderecoDefault() {
        return new EnderecoDTO(
                "07025-160",
                "Rua José Momo",
                "",
                "Vila Augusta",
                "Guarulhos",
                "SP"
        );
    }
}