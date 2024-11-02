/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

/**
 *
 * @author FATEC ZONA LESTE
 */
public class RoleCheckMiddleware extends Middleware {
    // Método que verifica o papel do usuário com base no email fornecido
    public boolean check(String email, String password) {
        // Verifica se o email é o do administrador
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!"); // Mensagem de saudação para o administrador
            return true; // Retorna verdadeiro, permitindo o prosseguimento
        }
        // Para qualquer outro email, trata como um usuário regular
        System.out.println("Hello, user!"); // Mensagem de saudação para usuários regulares
        // Chama o próximo middleware na cadeia
        return checkNext(email, password);
    }
}

