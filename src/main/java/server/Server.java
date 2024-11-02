/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author FATEC ZONA LESTE
 */
import com.mycompany.middleware.Middleware;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa o servidor.
 * Gerencia usuários e interage com a cadeia de middlewares para autenticação.
 */
public class Server {
    private Map<String, String> users = new HashMap<>(); // Mapa que armazena emails e senhas dos usuários
    private Middleware middleware; // Referência à cadeia de middleware

    /**
     * O cliente passa uma cadeia de objetos para o servidor.
     * Isso melhora a flexibilidade e facilita o teste da classe Server.
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware; // Armazena a referência à cadeia de middleware
    }

    /**
     * O servidor recebe email e senha do cliente e envia a solicitação de autorização
     * para a cadeia de middleware.
     */
    public boolean logIn(String email, String password) {
        // Verifica as credenciais através da cadeia de middleware
        if (middleware.check(email, password)) {
            System.out.println("Authorization has been successful!"); // Mensagem de sucesso na autorização

            // Aqui, você pode adicionar lógica adicional para usuários autorizados.

            return true; // Retorna verdadeiro se a autorização for bem-sucedida
        }
        return false; // Retorna falso se a autorização falhar
    }

    // Método para registrar um novo usuário
    public void register(String email, String password) {
        users.put(email, password); // Armazena o email e a senha no mapa de usuários
    }

    // Verifica se o email está registrado
    public boolean hasEmail(String email) {
        return users.containsKey(email); // Retorna verdadeiro se o email existir no mapa
    }

    // Verifica se a senha é válida para o email fornecido
    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password); // Compara a senha armazenada com a fornecida
    }
}

