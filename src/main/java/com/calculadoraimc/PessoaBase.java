package com.calculadoraimc;

/**
 * Classe abstrata que representa a base de qualquer pessoa no sistema.
 *
 * <p>Esta classe define os atributos e comportamentos comuns a todas as
 * entidades do tipo "pessoa", funcionando como superclasse na hierarquia
 * de herança do projeto.</p>
 *
 * Conceitos de POO demonstrados:
 * - Abstração: classe abstrata não pode ser instanciada diretamente
 * - Herança: serve como superclasse para Pessoa e, indiretamente, para Atleta
 * - Encapsulamento: atributos protegidos, acessíveis apenas pela hierarquia
 */
public abstract class PessoaBase {

    // Atributos protegidos: acessíveis pelas subclasses, mas não externamente
    // Demonstra encapsulamento com modificador 'protected'
    protected String nome;   // tipo String
    protected int idade;     // tipo int (primitivo)

    /**
     * Construtor da classe abstrata PessoaBase.
     *
     * @param nome  nome da pessoa (String)
     * @param idade idade da pessoa em anos (int)
     */
    public PessoaBase(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    /**
     * Método abstrato que obriga as subclasses a fornecerem
     * sua própria implementação de exibição de perfil.
     *
     * <p>Este método é um exemplo de polimorfismo em tempo de execução:
     * cada subclasse define como seu perfil deve ser exibido.</p>
     *
     * @return String formatada com as informações do perfil
     */
    public abstract String exibirPerfil();

    /**
     * Retorna o nome da pessoa.
     *
     * @return nome como String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a idade da pessoa.
     *
     * @return idade como int
     */
    public int getIdade() {
        return idade;
    }
}
