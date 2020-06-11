package br.com.wirecard.mocks.service;

import br.com.wirecard.mocks.model.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviar(Email email) {
        // logica que envia email
    }
}