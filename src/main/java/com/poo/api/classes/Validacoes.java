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

        // Verificação dos dígitos verificadores
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        // Primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }
        int mod = sum % 11;
        int expectedDigit1 = (mod < 2) ? 0 : (11 - mod);
        if (digits[9] != expectedDigit1) {
            return false;
        }

        // Segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }
        mod = sum % 11;
        int expectedDigit2 = (mod < 2) ? 0 : (11 - mod);
        if (digits[10] != expectedDigit2) {
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
