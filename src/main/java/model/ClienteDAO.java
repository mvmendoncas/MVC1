/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDAO {
    // Arquivo onde os dados serão salvos. Ele será criado na raiz do projeto.
    private static final String NOME_ARQUIVO = "clientes_db.txt";
    
    // Método auxiliar para converter uma linha de texto em um objeto ModelCliente
    private ModelCliente parseCliente(String linha) {
        String[] partes = linha.split(";");
        if (partes.length == 3) {
            try {
                String nome = partes[0];
                String cpf = partes[1];
                int idade = Integer.parseInt(partes[2]);
                return new ModelCliente(nome, cpf, idade);
            } catch (NumberFormatException e) {
                // Ignora linhas com idade inválida, mas avisa no console
                System.err.println("Linha ignorada por formato de idade inválido: " + linha);
            }
        }
        return null;
    }

    /**
     * Lê todos os clientes do arquivo
     * @return Lista de ModelCliente
     */
    public List<ModelCliente> listarTodos() {
        List<ModelCliente> clientes = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);
        
        if (!arquivo.exists() || arquivo.length() == 0) {
            return clientes; // Retorna lista vazia se o arquivo não existe ou está vazio
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                
                ModelCliente cliente = parseCliente(linha);
                if (cliente != null) {
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de clientes: " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Salva um novo cliente (adiciona no final do arquivo)
     */
    public void salvar(ModelCliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            writer.write(cliente.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar cliente no arquivo: " + e.getMessage());
        }
    }
    
    /**
     * Atualiza um cliente existente (busca pelo CPF e reescreve o arquivo)
     */
    public boolean atualizar(ModelCliente clienteAtualizado) {
        // 1. Lê todos os clientes
        List<ModelCliente> clientes = listarTodos();
        boolean encontrado = false;
        
        // 2. Procura e atualiza na lista da memória
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(clienteAtualizado.getCpf())) {
                clientes.set(i, clienteAtualizado); // Substitui o cliente na lista
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            // 3. Reescreve TODO o arquivo com a nova lista
            reescreverArquivo(clientes);
            return true;
        }
        return false;
    }

    /**
     * Deleta um cliente (busca pelo CPF e reescreve o arquivo)
     */
    public boolean deletar(String cpf) {
        // 1. Lê todos os clientes
        List<ModelCliente> clientes = listarTodos();
        
        // 2. Cria uma nova lista sem o cliente a ser deletado (filtro)
        List<ModelCliente> clientesRestantes = clientes.stream()
            .filter(c -> !c.getCpf().equals(cpf))
            .collect(Collectors.toList());
        
        // Verifica se houve alguma mudança (se o cliente foi encontrado e removido)
        if (clientesRestantes.size() < clientes.size()) {
            // 3. Reescreve TODO o arquivo com a nova lista (sem o deletado)
            reescreverArquivo(clientesRestantes);
            return true;
        }
        return false;
    }
    
    // Método privado auxiliar para reescrever o arquivo
    private void reescreverArquivo(List<ModelCliente> clientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, false))) {
            // Usa 'false' para sobrescrever o arquivo existente
            for (ModelCliente cliente : clientes) {
                writer.write(cliente.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo: " + e.getMessage());
        }
    }
}