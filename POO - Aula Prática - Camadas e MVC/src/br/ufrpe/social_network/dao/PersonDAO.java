package br.ufrpe.social_network.dao;

import java.time.LocalDate;

import br.ufrpe.social_network.negocio.Fachada;
import br.ufrpe.social_network.negocio.beans.Person;

public class PersonDAO {
	
	private static PersonDAO instance;
	private Person[] person;
	private int proxima;
	
	private PersonDAO() {
        // Construtor privado para implementação de singleton
    }

    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }
    
    /**
     * Construtor público
     * 
     * @param tamanho Tamanho inicial do array de Person a ser construído
     */
    public PersonDAO(int tamanho) {
        this.person = new Person[tamanho];
        this.proxima = 0;
    }

    /**
     * Cadastra uma nova conta no array de contas.
     * 
     * @param c A referência da Person a ser cadastrada
     */
    public void cadastrar(Person c) {
        this.person[this.proxima] = c;
        this.proxima = this.proxima + 1;
        if (this.proxima == this.person.length) {
            this.duplicaArrayPerson();
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
    public void cadastrar(String name, String country, LocalDate birthDate) {
    	Person c = new Person( name,  country,  birthDate);
        this.cadastrar(c);
    }

    /**
     * Procurar uma conta baseado no número dado como parâmetro
     * 
     * @param num O número da conta a ser procurada
     * @return A conta encontrada ou null se o número de conta passado com
     *         parâmetro não existir
     */
    public Person procurar(String num) {
        int i = this.procurarIndice(num);
        Person resultado = null;
        if (i != this.proxima) {
            resultado = this.person[i];
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
            this.person[i] = this.person[this.proxima - 1];
            this.person[this.proxima - 1] = null;
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
            if (num.equals(this.person[i].getId())) {
                achou = true;
            } else {
                i = i + 1;
            }
        }
        return i;
    }

    private void duplicaArrayPerson() {
        if (this.person != null && this.person.length > 0) {
        	Person[] arrayDuplicado = new Person[this.person.length * 2];
            for (int i = 0; i < this.person.length; i++) {
                arrayDuplicado[i] = this.person[i];
            }
            this.person = arrayDuplicado;
        }
    }
    
    /*
     * Antes de implementar os métodos, pense bem na assinatura dos mesmos
     */
    
}
