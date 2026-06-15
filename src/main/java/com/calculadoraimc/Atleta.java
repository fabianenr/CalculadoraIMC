package com.calculadoraimc;

/**
 * Representa um atleta no sistema de cálculo de IMC.
 *
 * <p>Atleta herda de {@link Pessoa} (herança multinível: PessoaBase → Pessoa → Atleta)
 * e sobrescreve o método {@link #classificarIMC(double)} com faixas específicas
 * para atletas, demonstrando polimorfismo em ação.</p>
 *
 * Conceitos de POO demonstrados:
 * - Herança multinível: PessoaBase → Pessoa → Atleta
 * - Polimorfismo: sobrescrita de classificarIMC() com @Override
 * - Encapsulamento: atributo modalidade privado com getter
 * - Tipos de dados: String para modalidade
 */
public class Atleta extends Pessoa {

    // Atributo exclusivo de Atleta, não existente em Pessoa
    private String modalidade; // tipo String (ex: futebol, natação, atletismo)

    /**
     * Construtor completo da classe Atleta.
     *
     * <p>Chama o construtor de {@link Pessoa} via {@code super()},
     * que por sua vez chama o construtor de {@link PessoaBase},
     * demonstrando a cadeia de herança multinível.</p>
     *
     * @param nome       nome do atleta
     * @param idade      idade em anos
     * @param peso       peso em kg (deve ser > 0)
     * @param altura     altura em metros (deve ser > 0)
     * @param modalidade modalidade esportiva praticada
     * @throws EntradaInvalidaException se peso ou altura forem inválidos
     */
    public Atleta(String nome, int idade, double peso, double altura, String modalidade) {
        // Chama o construtor da superclasse Pessoa
        super(nome, idade, peso, altura);
        this.modalidade = modalidade;
    }

    /**
     * Sobrescreve o método exibirPerfil() de Pessoa/PessoaBase
     * para incluir a modalidade esportiva nas informações.
     *
     * <p>Demonstra polimorfismo: o mesmo método tem comportamento
     * diferente dependendo do tipo do objeto em runtime.</p>
     *
     * @return String com todas as informações do atleta formatadas
     */
    @Override
    public String exibirPerfil() {
        // Operador de concatenação e formatação
        return String.format(
            "=== PERFIL DO ATLETA ===%n" +
            "Nome       : %s%n" +
            "Idade      : %d anos%n" +
            "Peso       : %.2f kg%n" +
            "Altura     : %.2f m%n" +
            "Modalidade : %s%n" +
            "Ativo      : %s",
            getNome(), getIdade(), getPeso(), getAltura(),
            modalidade, (isAtivo() ? "Sim" : "Não")
        );
    }

    /**
     * Sobrescreve a classificação de IMC com faixas específicas para atletas.
     *
     * <p>Atletas possuem composição corporal diferente de pessoas comuns,
     * portanto as faixas de IMC são distintas:</p>
     * <ul>
     *   <li>IMC &lt; 20.0       → Abaixo do ideal para atleta</li>
     *   <li>20.0 a 26.99      → Ideal para atleta</li>
     *   <li>IMC &ge; 27.0      → Acima do ideal para atleta</li>
     * </ul>
     *
     * <p>Este método demonstra polimorfismo: ao chamar {@code classificarIMC()}
     * em um objeto {@code Atleta}, esta versão é executada, não a de {@code Pessoa}.</p>
     *
     * @param imc valor do IMC calculado
     * @return classificação específica para atleta como String
     */
    @Override
    public String classificarIMC(double imc) {
        // Operadores relacionais e lógicos (&&, <, >=)
        if (imc < 20.0) {
            return "Abaixo do ideal para atleta";
        } else if (imc >= 20.0 && imc <= 26.99) {
            return "Ideal para atleta";
        } else {
            // imc >= 27.0
            return "Acima do ideal para atleta";
        }
    }

    /**
     * Retorna a modalidade esportiva do atleta.
     *
     * @return modalidade como String
     */
    public String getModalidade() {
        return modalidade;
    }

    /**
     * Define a modalidade esportiva do atleta.
     *
     * @param modalidade nova modalidade
     */
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
