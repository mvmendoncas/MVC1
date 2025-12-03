/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.ModelCliente;

/**
 * @author thiag
 */
public class ControllerCliente extends ModelCliente {

    public ControllerCliente(String nome, String cpf, int idade) {
        super(nome, cpf, idade);
    }

    @Override
    public void cadastrar() {
        // Implement the logic to register the client
    }

    @Override
    public void visualizar() {
        // Implement the logic to view the client's data
    }

    @Override
    public void deletar() {
        // Implement the logic to delete the client
    }

    @Override
    public void atualizar() {
        // Implement the logic to update the client's data
    }
}
