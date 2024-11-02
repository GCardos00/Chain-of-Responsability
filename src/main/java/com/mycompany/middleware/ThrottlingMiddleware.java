/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

/**
 *
 * @author FATEC ZONA LESTE
 */
public class ThrottlingMiddleware extends Middleware {
    private int requestPerMinute; // Limite de requisições por minuto
    private int request; // Contador de requisições atuais
    private long currentTime; // Marca temporal do início do intervalo de 1 minuto

    // Construtor que inicializa o limite de requisições por minuto
    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute; // Define o limite de requisições
        this.currentTime = System.currentTimeMillis(); // Captura o tempo atual
    }

    /**
     * O método check é responsável por verificar se a requisição pode ser processada.
     * Ele pode ser chamado no início ou no final do processamento da cadeia de middleware,
     * oferecendo flexibilidade na ordem das verificações.
     */
    public boolean check(String email, String password) {
        // Verifica se já passou 1 minuto desde a última contagem
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0; // Reinicia o contador de requisições
            currentTime = System.currentTimeMillis(); // Atualiza a marca temporal
        }

        request++; // Incrementa o contador de requisições atuais
        
        // Verifica se o número de requisições excedeu o limite
        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!"); // Informa que o limite foi ultrapassado
            Thread.currentThread().stop(); // Para a thread atual (não recomendado em produção)
        }
        
        // Chama o próximo middleware na cadeia
        return checkNext(email, password);
    }
}
