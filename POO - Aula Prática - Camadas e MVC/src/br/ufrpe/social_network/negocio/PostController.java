package br.ufrpe.social_network.negocio;

import br.ufrpe.social_network.dao.PostDAO;

public class PostController {
    
    private PostDAO postsRepository;
    private static PostController instance;
    // TODO implementar corpo dos métodos CRUD deste controlador

    
	
	private PostController() {
        // Construtor privado para implementação de singleton
    }

    public static PostController getInstance() {
        if (instance == null) {
            instance = new PostController();
        }
        return instance;
    }
    
    // TODO Implementar método que lista todos os posts de uma determinada pessoa
    
    // TODO DESAFIO Implementar método que busque todos comentários que uma dada pessoa realizou em posts de terceiros
    // Para implementar este método, você deve fazer uma buscar em todos os 
    // comentários de todos os posts, verificando quem realizou aqueele comentário.
    // Lembre-se que é possível realizar comentários de comentários e busca 
    // precisaria ser feita em profundidade
}
