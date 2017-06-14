package br.ufrpe.social_network.negocio;

public class Fachada {
    
    private static Fachada instance;
    // TODO Implementar Fachada que usa métodos do controlador de pessoas e posts
    // Todos os métodos devem ser do tipo delegate e podem ser gerados 
    // automaticamente pelo Eclipse se não houver conflito de nomes
    
	private Fachada() {
        // Construtor privado para implementação de singleton
    }

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }
    
}
