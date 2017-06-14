package br.ufrpe.social_network.dao;
import java.time.LocalDate;
import java.util.*;
import br.ufrpe.social_network.negocio.beans.Person;
import br.ufrpe.social_network.negocio.beans.Post;
public class PostDAO {
    
	private static PostDAO instance;
	
	private ArrayList<Post> post;
    private int proxima;
    
		private PostDAO() {
	        // Construtor privado para implementação de singleton
	    }

	    public static PostDAO getInstance() {
	        if (instance == null) {
	            instance = new PostDAO();
	        }
	        return instance;
	    }
    
	    /**
	     * Construtor público
	     * 
	     * @param tamanho Tamanho inicial do array de Person a ser construído
	     */
	    public PostDAO(int tamanho) 
	    {
	        this.post = new ArrayList <Post>(tamanho);
	        this.proxima = 0;
	    }

	    /**
	     * Cadastra uma nova conta no array de contas.
	     * 
	     * @param c A referência da Person a ser cadastrada
	     */
	    public void cadastrar(Post c) 
	    {
	        this.post.add(this.proxima, c);
	        this.proxima = this.proxima + 1;
	        if (this.proxima == this.post.size()) 
	        {
	            this.duplicaArrayPost();
	        }
	    }

	    /**
	     * Cria o objeto da conta com o número e saldo inicial passados como
	     * parâmetro e cadastra a conta no array de contas. Observe o reuso entre os
	     * métodos sobrecarregados "cadastrar"
	     * 
	     * @param numero Número da conta a ser criada e cadastrada
	     * @param saldoInicial Saldo inicial da conta a ser criada e cadastrada
	     */
	    public void cadastrar(String texto, Person author) {
	    	Post c = new Post( texto,  author);
	        this.cadastrar(c);
	    }

	    /**
	     * Procurar uma conta baseado no número dado como parâmetro
	     * 
	     * @param num O número da conta a ser procurada
	     * @return A conta encontrada ou null se o número de conta passado com
	     *         parâmetro não existir
	     */
	    public Post procurar(String num) {
	        int i = this.procurarIndice(num);
	        Post resultado = null;
	        if (i != this.proxima) {
	            resultado = this.post.get(i);
	        }
	        return resultado;
	    }

	    /**
	     * Método para verificar se existe alguma com o número informado como 
	     * parâmetro no Array de contas.
	     * 
	     * @param numConta O número da conta a ser procurada.
	     * @return true se a conta existe, false caso contrário.
	     */
	    public boolean existe(String numPerson) {
	        boolean existe = false;
	        int indice = this.procurarIndice(numPerson);
	        if (indice != proxima) {
	            existe = true;
	        }
	        return existe;
	    }

	    /**
	     * Removendo a conta cujo número é passado como parâmetro
	     * 
	     * @param num Número da conta a ser removida.
	     */
	    public void remover(String num) {
	        int i = this.procurarIndice(num);
	        if (i != this.proxima) {
	            this.post.add(i,this.post.get(this.proxima - 1));
	            this.post.add(this.proxima - 1, null);
	            this.proxima = this.proxima - 1;
	        } else {
	            // Aqui não dever haver impressão de mensagem para o usuário, já
	            // que essa não é a responsabildiade do Repositório.
	            // Neste caso, o problema é resolvido com o uso de exceções
	        }
	    }

	    /**
	     * Método auxiliar para procurar o índice de uma conta no array.
	     * 
	     * @param num Número da conta da qual deseja-se encontrar o índice no array
	     *            de contas
	     * @return Um inteiro correspondente ao índice da conta encontrada.
	     */
	    private int procurarIndice(String num) {
	        int i = 0;
	        boolean achou = false;
	        while ((!achou) && (i < this.proxima)) {
	            if (num.equals(this.post.get(i).getId())) {
	                achou = true;
	            } else {
	                i = i + 1;
	            }
	        }
	        return i;
	    }

	    private void duplicaArrayPost() {
	        if (this.post != null && this.post.size() > 0) {
	        	ArrayList<Post> arrayDuplicado = new ArrayList<Post>(this.post.size() * 2);
	            for (int i = 0; i < this.post.size(); i++) {
	            	arrayDuplicado.add(i ,this.post.get(i));
	            }
	            this.post = arrayDuplicado;
	        }
	    }
    

    /*
     * Antes de implementar os métodos, pense bem na assinatura dos mesmos
     */
}
