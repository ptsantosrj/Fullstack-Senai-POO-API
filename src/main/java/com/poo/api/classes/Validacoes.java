package com.poo.api.classes;

import org.springframework.web.client.RestTemplate;

public class Validacoes {

    public static Endereco consultaViaCEP(String cep) {
        final String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        try {
            Endereco endereco = restTemplate.getForObject(uri, Endereco.class);
            return endereco;
        } catch (Exception e) {
            System.err.println("Erro ao consultar o serviço ViaCEP: " + e.getMessage());
            return null;
        }
    }

    public static boolean validaCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        return true;
    }

    public static boolean validaTelefone(String telefone) {
        // Aceita números com ou sem DDD, com ou sem traços ou espaços
        String regex = "^\\(?\\d{2}\\)?[-\\s]?\\d{4,5}[-\\s]?\\d{4}$";
        return telefone.matches(regex);
    }

    public static boolean validaEmail(String email) {
        // Validação simples de email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

}
