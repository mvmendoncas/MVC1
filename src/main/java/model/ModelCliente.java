/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thiag
 */
public class ModelCliente {
    private String nome;
    private String cpf;
    private int idade;

    // Construtor completo
    public ModelCliente(String nome, String cpf, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    // Método para formatar o objeto como uma linha para o arquivo
    public String toFileString() {
        return nome + ";" + cpf + ";" + idade;
    }

    // Getters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public int getIdade() { return idade; }

    // Setters (para atualização)
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setIdade(int idade) { this.idade = idade; }
}