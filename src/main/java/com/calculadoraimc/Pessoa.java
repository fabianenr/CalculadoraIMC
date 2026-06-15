package com.calculadoraimc;

/**
 * Representa uma pessoa comum no sistema de cálculo de IMC.
 *
 * <p>Esta classe herda de {@link PessoaBase} e implementa {@link CalculadoraIMC},
 * encapsulando dados de peso, altura e status ativo da pessoa,
 * e fornecendo os métodos de cálculo e classificação de IMC para o público geral.</p>
 *
 * Conceitos de POO demonstrados:
 * - Herança: extends PessoaBase (herança simples)
 * - Implementação de interface: implements CalculadoraIMC
 * - Encapsulamento: atributos private com getters/setters validados
 * - Exceção personalizada: setter valida e lança EntradaInvalidaException
 * - Uso de tipos: double, boolean, String, int
 */
public class Pessoa extends PessoaBase implements CalculadoraIMC {

    // Atributos privados: só acessíveis via getters/setters (encapsulamento total)
    private double peso;    // tipo double (peso em kg)
    private double altura;  // tipo double (altura em metros)
    private boolean ativo;  // tipo boolean (indica se o cadastro está ativo)

    /**
     * Construtor completo da classe Pessoa.
     *
     * <p>Chama o construtor da superclasse {@link PessoaBase} via {@code super()},
     * demonstrando o reuso de código através da herança.</p>
     *
     * @param nome   nome da pessoa
     * @param idade  idade da pessoa em anos
     * @param peso   peso da pessoa em kg (deve ser > 0)
     * @param altura altura da pessoa em metros (deve ser > 0)
     * @throws EntradaInvalidaException se peso ou altura forem inválidos
     */
    public Pessoa(String nome, int idade, double peso, double altura) {
        // Chama o construtor da classe pai (PessoaBase)
        super(nome, idade);

        // Utiliza o setter com validação para garantir integridade do dado
        setPeso(peso);
        setAltura(altura);

        // Operador de atribuição: cadastro inicia como ativo
        this.ativo = true;
    }

    /**
     * Implementa o método abstrato de PessoaBase.
     * Exibe o perfil completo da pessoa com seus dados principais.
     *
     * @return String com todas as informações da pessoa formatadas
     */
    @Override
    public String exibirPerfil() {
        // Operadores: concatenação de String + operador relacional (ativo ? "Sim" : "Não")
        return String.format(
            "=== PERFIL DA PESSOA ===%n" +
            "Nome   : %s%n" +
            "Idade  : %d anos%n" +
            "Peso   : %.2f kg%n" +
            "Altura : %.2f m%n" +
            "Ativo  : %s",
            nome, idade, peso, altura, (ativo ? "Sim" : "Não")
        );
    }

    /**
     * Calcula o IMC utilizando a classe {@link CalculadoraRecursiva}
     * para elevar a altura ao quadrado (sem Math.pow).
     *
     * <p>Fórmula: IMC = peso / altura²</p>
     *
     * @param peso   peso em kg
     * @param altura altura em metros
     * @return valor do IMC calculado
     * @throws EntradaInvalidaException se altura for inválida (≤ 0)
     */
    @Override
    public double calcularIMC(double peso, double altura) {
        // Operador relacional: verifica se altura é inválida
        if (altura <= 0) {
            throw new EntradaInvalidaException("Altura inválida para cálculo do IMC: " + altura);
        }

        // Utiliza recursão para calcular altura² (requisito do projeto)
        double alturaAoQuadrado = CalculadoraRecursiva.potencia(altura, 2);

        // Operador aritmético: divisão
        return peso / alturaAoQuadrado;
    }

    /**
     * Classifica o IMC de acordo com as faixas para pessoa comum (OMS).
     *
     * <p>Tabela de classificação:
     * <ul>
     *   <li>IMC &lt; 18.5 → Abaixo do peso</li>
     *   <li>18.5 a 24.9  → Peso normal</li>
     *   <li>25.0 a 29.9  → Sobrepeso</li>
     *   <li>30.0 a 34.9  → Obesidade Grau I</li>
     *   <li>35.0 a 39.9  → Obesidade Grau II</li>
     *   <li>≥ 40.0       → Obesidade Grau III</li>
     * </ul>
     * </p>
     *
     * @param imc valor do IMC calculado
     * @return classificação como String
     */
    @Override
    public String classificarIMC(double imc) {
        // Demonstração de operadores relacionais e lógicos (&&, <, >=)
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc >= 18.5 && imc <= 24.9) {
            return "Peso normal";
        } else if (imc >= 25.0 && imc <= 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30.0 && imc <= 34.9) {
            return "Obesidade Grau I";
        } else if (imc >= 35.0 && imc <= 39.9) {
            return "Obesidade Grau II";
        } else {
            // imc >= 40.0
            return "Obesidade Grau III";
        }
    }

    // ===== GETTERS =====

    /**
     * Retorna o peso da pessoa.
     *
     * @return peso em kg como double
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Retorna a altura da pessoa.
     *
     * @return altura em metros como double
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Retorna se o cadastro da pessoa está ativo.
     *
     * @return true se ativo, false caso contrário
     */
    public boolean isAtivo() {
        return ativo;
    }

    // ===== SETTERS COM VALIDAÇÃO =====

    /**
     * Define o peso da pessoa com validação.
     *
     * <p>Demonstra o uso de exceção personalizada para rejeitar
     * valores inválidos e proteger a integridade dos dados.</p>
     *
     * @param peso novo peso em kg
     * @throws EntradaInvalidaException se peso for menor ou igual a zero
     */
    public void setPeso(double peso) {
        // Operador relacional: valida peso
        if (peso <= 0) {
            throw new EntradaInvalidaException(
                "Peso inválido: " + peso + ". O peso deve ser maior que zero."
            );
        }
        this.peso = peso;
    }

    /**
     * Define a altura da pessoa com validação.
     *
     * @param altura nova altura em metros
     * @throws EntradaInvalidaException se altura for menor ou igual a zero
     */
    public void setAltura(double altura) {
        // Operador relacional: valida altura
        if (altura <= 0) {
            throw new EntradaInvalidaException(
                "Altura inválida: " + altura + ". A altura deve ser maior que zero."
            );
        }
        this.altura = altura;
    }

    /**
     * Define o status ativo do cadastro.
     *
     * @param ativo true para ativo, false para inativo
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
