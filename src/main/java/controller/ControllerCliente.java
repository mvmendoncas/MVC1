/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.ClienteDAO;
import model.ModelCliente;

/**
 * @author thiag
 */
public class ControllerCliente {

  private ClienteDAO dao = new ClienteDAO();

    // ------------------------------------------
    // Operações de Cadastro
    // ------------------------------------------
    
    public boolean cadastrarCliente(String nome, String cpf, int idade) {
        // 1. Validação básica de negócio (feita pelo Controller)
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty()) {
            System.err.println("Erro: Nome e CPF não podem ser vazios.");
            return false;
        }
        
        // 2. Cria o objeto ModelCliente
        ModelCliente novoCliente = new ModelCliente(nome, cpf, idade);
        
        // 3. Delega a persistência ao DAO
        dao.salvar(novoCliente);
        return true;
    }
    
    // ------------------------------------------
    // Operações de Busca/Visualização
    // ------------------------------------------
    
    public ModelCliente buscarClientePorCpf(String cpf) {
        List<ModelCliente> clientes = dao.listarTodos();
        
        // Busca o cliente na lista
        for (ModelCliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente; // Cliente encontrado
            }
        }
        return null; // Cliente não encontrado
    }
    
    // ------------------------------------------
    // Operações de Atualização
    // ------------------------------------------

    public boolean atualizarCliente(String nome, String cpf, int novaIdade) {
        // 1. Cria um ModelCliente (o CPF é a chave para o DAO saber quem atualizar)
        ModelCliente clienteAtualizado = new ModelCliente(nome, cpf, novaIdade);
        
        // 2. Delega a persistência ao DAO
        return dao.atualizar(clienteAtualizado);
    }

    // ------------------------------------------
    // Operações de Deleção
    // ------------------------------------------
    
    public boolean deletarCliente(String cpf) {
        // 1. Delega a deleção ao DAO
        return dao.deletar(cpf);
    }
}
