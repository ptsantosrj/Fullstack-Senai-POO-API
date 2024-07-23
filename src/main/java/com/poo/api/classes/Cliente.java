package com.poo.api.classes;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco; // Cliente tem um endereço

    // Construtor vazio
    public Cliente() {
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente: \n" + nome + "\n" +
                "Cpf= " + cpf + "\n" +
                "Telefone= " + telefone + "\n" +
                "Email= " + email + 
                "\n" + endereco;
    }
}