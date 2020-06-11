package br.com.wirecard.mocks.service;

import br.com.wirecard.mocks.model.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    @Value("${viacep.url}")
    private String viaCepUrl;

    @Autowired
    private RestTemplate restTemplate;

    public EnderecoDTO buscaEnderecoPorCep(String cep) {
        String url = viaCepUrl.replace("{cep}", cep);
        ResponseEntity<EnderecoDTO> response = restTemplate.getForEntity(url, EnderecoDTO.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        }
        return null;
    }

    
}