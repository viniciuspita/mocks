package br.com.wirecard.mocks.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDTO {

    private String nome;
    private String email;
    private String senha;
    private String cep;

    public PessoaDTO() {
    }

    public PessoaDTO(String nome, String email, String senha, String cep) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "PessoaDTO{" +
                "nome: '" + nome + '\'' +
                "email: '" + email + '\'' +
                ", cep: '" + cep + '\'' +
                '}';
    }
}