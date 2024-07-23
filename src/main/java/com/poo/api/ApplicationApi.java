package com.poo.api;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.poo.api.classes.Cliente;
import com.poo.api.classes.Endereco;
import com.poo.api.classes.Validacoes;

@SpringBootApplication
public class ApplicationApi {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationApi.class, args);
        cadastrarCliente();
    }

    public static void cadastrarCliente() {
        Scanner sc = new Scanner(System.in);

        // Solicita CEP
        System.out.println("Digite o CEP: ");
        String cep = sc.nextLine();

        Endereco endereco = Validacoes.consultaViaCEP(cep);

        if (endereco != null) {
            // Mostra o endereço encontrado e solicita confirmacao
            System.out.println("Endereço encontrado:");
            System.out.println(endereco);

            System.out.println("Confirma o endereço? (s/n)");
            String confirma = sc.nextLine();

            if ("s".equalsIgnoreCase(confirma)) {
                // Inicia a obtencao dos dados do cliente
                System.out.println("Digite o nome do cliente:");
                String nome = sc.nextLine();

                System.out.println("Digite o CPF do cliente:");
                String cpf = sc.nextLine();
                while (!Validacoes.validaCPF(cpf)) {
                    System.out.println("CPF inválido. Digite novamente:");
                    cpf = sc.nextLine();
                }

                System.out.println("Digite o telefone do cliente:");
                String telefone = sc.nextLine();
                while (!Validacoes.validaTelefone(telefone)) {
                    System.out.println("Telefone inválido. Digite novamente:");
                    telefone = sc.nextLine();
                }

                System.out.println("Digite o email do cliente:");
                String email = sc.nextLine();
                while (!Validacoes.validaEmail(email)) {
                    System.out.println("Email inválido. Digite novamente:");
                    email = sc.nextLine();
                }

                // Pede número e complemento
                System.out.println("Digite o número do logradouro: ");
                String numero = sc.nextLine();
                endereco.setNumero(numero);

                System.out.println("Digite o complemento do logradouro: ");
                String complemento = sc.nextLine();
                endereco.setComplemento(complemento);

                // Cria o cliente com o endereço confirmado
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setTelefone(telefone);
                cliente.setEmail(email);
                cliente.setEndereco(endereco);

                // Mostra os dados finais do cliente
                System.out.println("Cadastro do cliente finalizado:");
                System.out.println(cliente);
            } else {
                System.out.println("Operação cancelada pelo usuário.");
            }
        } else {
            System.out.println("CEP não encontrado.");
        }

        sc.close();
    }

    
}