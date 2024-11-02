/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

/**
 *
 * @author FATEC ZONA LESTE
 */
import server.Server;

/**
 * ConcreteHandler. Checks whether a user with the given credentials exists.
 * Esta classe concreta do middleware verifica se um usuário com as credenciais fornecidas existe no servidor.
 */
public class UserExistsMiddleware extends Middleware {
    private Server server; // Referência ao servidor que contém os dados dos usuários

    // Construtor que inicializa o middleware com uma instância do servidor
    public UserExistsMiddleware(Server server) {
        this.server = server; // Armazena a referência ao servidor
    }

    // Método que realiza a verificação das credenciais do usuário
    public boolean check(String email, String password) {
        // Verifica se o email está registrado no servidor
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!"); // Mensagem de erro se o email não existir
            return false; // Retorna falso, interrompendo a cadeia
        }
        
        // Verifica se a senha é válida para o email fornecido
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!"); // Mensagem de erro se a senha estiver errada
            return false; // Retorna falso, interrompendo a cadeia
        }
        
        // Se as verificações passarem, chama o próximo middleware na cadeia
        return checkNext(email, password);
    }
}

