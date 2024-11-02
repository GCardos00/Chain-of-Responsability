/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.middleware;

/**
 * Classe abstrata que representa um middleware.
 * Esta classe serve como base para a criação de cadeias de middleware.
 * Cada middleware pode processar uma requisição e passar o controle
 * para o próximo middleware na cadeia.
 */
public abstract class Middleware {
    private Middleware next; // Referência ao próximo middleware na cadeia

    /**
     * Método estático que constrói uma cadeia de objetos middleware.
     * Recebe o primeiro middleware e um número variável de outros middlewares,
     * conectando-os em sequência.
     */
    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first; // Inicializa a cabeça da cadeia com o primeiro middleware
        for (Middleware nextInChain: chain) { // Itera sobre os middlewares adicionais
            head.next = nextInChain; // Define o próximo middleware na cadeia
            head = nextInChain; // Move a cabeça para o próximo middleware
        }
        return first; // Retorna o primeiro middleware da cadeia
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses.
     * Cada middleware deve fornecer sua própria lógica de verificação
     * (ex: autenticação, validação, throttling).
     */
    public abstract boolean check(String email, String password);

    /**
     * Método protegido que executa o check no próximo objeto da cadeia.
     * Se não houver próximo middleware, retorna true, indicando que
     * todas as verificações anteriores foram bem-sucedidas.
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) { // Se não houver próximo middleware
            return true; // Retorna verdadeiro, finalizando a cadeia com sucesso
        }
        return next.check(email, password); // Chama o método check do próximo middleware
    }
}
