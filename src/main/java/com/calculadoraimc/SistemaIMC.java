package com.calculadoraimc;

import java.util.Scanner;

/**
 * Orquestra toda a lógica de negócio do sistema de cálculo de IMC.
 *
 * <p>Esta classe é responsável por:</p>
 * <ul>
 *   <li>Gerenciar o menu interativo via CLI</li>
 *   <li>Cadastrar pessoas e atletas</li>
 *   <li>Executar os cálculos de IMC</li>
 *   <li>Armazenar resultados no histórico via composição</li>
 * </ul>
 *
 * <p><strong>Composição:</strong> SistemaIMC possui um objeto {@link Historico}
 * como atributo privado, instanciado internamente. SistemaIMC NÃO herda de
 * Historico — o relacionamento é "tem um" (composição), não "é um" (herança).</p>
 *
 * Conceitos de POO demonstrados:
 * - Composição: atributo 'historico' instanciado internamente
 * - Polimorfismo: 'ultimaPessoa' pode referenciar Pessoa ou Atleta
 * - Encapsulamento: todos os atributos são privados
 * - Tipos de dados: int, double, String, boolean
 * - Operadores: aritméticos, relacionais e lógicos
 */
public class SistemaIMC {

    // ===== COMPOSIÇÃO: SistemaIMC "tem um" Historico =====
    // O Historico é instanciado internamente e encapsulado
    private final Historico historico;

    // Referência polimórfica: pode apontar para Pessoa ou Atleta
    // (Pessoa é superclasse de Atleta)
    private Pessoa ultimaPessoa;

    // Scanner para leitura de entrada do usuário via terminal
    private final Scanner scanner;

    // Flag de controle do loop principal
    private boolean rodando;

    /**
     * Construtor do SistemaIMC.
     * Inicializa o histórico por composição e o scanner para CLI.
     */
    public SistemaIMC() {
        // Instancia o Historico internamente (composição)
        this.historico = new Historico();
        this.ultimaPessoa = null;
        this.scanner = new Scanner(System.in);
        // Operador de atribuição: sistema inicia em execução
        this.rodando = true;
    }

    /**
     * Inicia o loop principal do sistema.
     *
     * <p>Utiliza loop {@code while} que mantém o sistema rodando
     * até o usuário escolher a opção de sair (0).</p>
     */
    public void iniciar() {
        exibirBoasVindas();

        // Loop principal: continua enquanto 'rodando' for true
        while (rodando) {
            exibirMenu();
            processarOpcao();
        }

        encerrar();
    }

    /**
     * Exibe a mensagem de boas-vindas ao iniciar o sistema.
     */
    private void exibirBoasVindas() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      CALCULADORA DE IMC - CLI          ║");
        System.out.println("║   Sistema de Índice de Massa Corporal  ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("  Bem-vindo(a)! Utilize o menu abaixo.\n");
    }

    /**
     * Exibe o menu principal de opções no terminal.
     */
    private void exibirMenu() {
        System.out.println("========================================");
        System.out.println("                  MENU                  ");
        System.out.println("========================================");
        System.out.println("  1 - Cadastrar Pessoa");
        System.out.println("  2 - Cadastrar Atleta");
        System.out.println("  3 - Calcular IMC da última pessoa cadastrada");
        System.out.println("  4 - Exibir Histórico");
        System.out.println("  0 - Sair");
        System.out.println("========================================");

        // Exibe a última pessoa cadastrada, se houver
        if (ultimaPessoa != null) {
            // Operador ternário + instanceof: verifica tipo do objeto
            String tipo = (ultimaPessoa instanceof Atleta) ? "Atleta" : "Pessoa";
            System.out.println("  [Última cadastrada: " + ultimaPessoa.getNome() + " (" + tipo + ")]");
        } else {
            System.out.println("  [Nenhuma pessoa cadastrada ainda]");
        }

        System.out.println("========================================");
        System.out.print("  Digite a opção desejada: ");
    }

    /**
     * Lê a opção do usuário e redireciona para o método adequado.
     *
     * <p>Utiliza {@code switch} para desvio de fluxo e
     * {@code try/catch} para tratar entradas inválidas.</p>
     */
    private void processarOpcao() {
        try {
            // Leitura da opção como String, depois convertida para int
            String entrada = scanner.nextLine().trim();

            // Valida se a entrada não está vazia
            if (entrada.isEmpty()) {
                throw new EntradaInvalidaException("Nenhuma opção foi digitada.");
            }

            int opcao;
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                // Relança como nossa exceção personalizada
                throw new EntradaInvalidaException(
                    "Opção inválida: '" + entrada + "'. Digite apenas números inteiros.",
                    e
                );
            }

            // Switch para desvio de fluxo conforme opção escolhida
            switch (opcao) {
                case 1 -> cadastrarPessoa();
                case 2 -> cadastrarAtleta();
                case 3 -> calcularIMCUltimaCadastrada();
                case 4 -> historico.exibir();
                case 0 -> rodando = false; // Encerra o loop principal
                default -> throw new EntradaInvalidaException(
                    "Opção '" + opcao + "' não existe. Escolha entre 0 e 4."
                );
            }

        } catch (EntradaInvalidaException e) {
            // Captura e exibe mensagem de erro amigável
            System.out.println("\n[ERRO] " + e.getMessage());
            System.out.println("Por favor, tente novamente.\n");
        }
    }

    /**
     * Realiza o cadastro de uma pessoa comum via entrada do usuário.
     *
     * <p>Solicita nome, idade, peso e altura, valida as entradas
     * e cria um objeto {@link Pessoa}.</p>
     */
    private void cadastrarPessoa() {
        System.out.println("\n--- CADASTRAR PESSOA ---");

        try {
            String nome = lerNome();
            int idade = lerIdade();
            double peso = lerPeso();
            double altura = lerAltura();

            // Cria objeto Pessoa com os dados coletados
            ultimaPessoa = new Pessoa(nome, idade, peso, altura);

            System.out.println("\n[OK] Pessoa cadastrada com sucesso!");
            System.out.println(ultimaPessoa.exibirPerfil());
            System.out.println();

        } catch (EntradaInvalidaException e) {
            System.out.println("\n[ERRO no cadastro] " + e.getMessage());
        }
    }

    /**
     * Realiza o cadastro de um atleta via entrada do usuário.
     *
     * <p>Solicita nome, idade, peso, altura e modalidade esportiva,
     * e cria um objeto {@link Atleta}.</p>
     */
    private void cadastrarAtleta() {
        System.out.println("\n--- CADASTRAR ATLETA ---");

        try {
            String nome = lerNome();
            int idade = lerIdade();
            double peso = lerPeso();
            double altura = lerAltura();
            String modalidade = lerModalidade();

            // Cria objeto Atleta (subclasse de Pessoa) com os dados coletados
            ultimaPessoa = new Atleta(nome, idade, peso, altura, modalidade);

            System.out.println("\n[OK] Atleta cadastrado com sucesso!");
            System.out.println(ultimaPessoa.exibirPerfil());
            System.out.println();

        } catch (EntradaInvalidaException e) {
            System.out.println("\n[ERRO no cadastro] " + e.getMessage());
        }
    }

    /**
     * Calcula e exibe o IMC da última pessoa cadastrada.
     *
     * <p>Demonstra polimorfismo: se {@code ultimaPessoa} for um {@code Atleta},
     * o método {@code classificarIMC()} de {@code Atleta} é chamado automaticamente.</p>
     */
    private void calcularIMCUltimaCadastrada() {
        System.out.println("\n--- CALCULAR IMC ---");

        // Operador relacional: verifica se há pessoa cadastrada
        if (ultimaPessoa == null) {
            System.out.println("[AVISO] Nenhuma pessoa cadastrada. Use as opções 1 ou 2 primeiro.\n");
            return;
        }

        try {
            double peso = ultimaPessoa.getPeso();
            double altura = ultimaPessoa.getAltura();

            // Calcula o IMC usando a implementação de Pessoa (que usa CalculadoraRecursiva)
            double imc = ultimaPessoa.calcularIMC(peso, altura);

            // POLIMORFISMO: o método classificarIMC() chamado aqui
            // usa a implementação de Atleta se o objeto for Atleta,
            // ou a de Pessoa se for uma Pessoa comum
            String classificacao = ultimaPessoa.classificarIMC(imc);

            // Determina o tipo real do objeto (instanceof = operador de tipo)
            String tipo = (ultimaPessoa instanceof Atleta) ? "ATLETA" : "PESSOA";

            // Monta o resultado formatado
            String resultado = String.format(
                "Nome          : %s%n" +
                "Tipo          : %s%n" +
                "Peso          : %.2f kg%n" +
                "Altura        : %.2f m%n" +
                "IMC calculado : %.2f%n" +
                "Classificação : %s",
                ultimaPessoa.getNome(), tipo, peso, altura, imc, classificacao
            );

            // Exibe no terminal
            System.out.println("\n=== RESULTADO DO CÁLCULO DE IMC ===");
            System.out.println(resultado);
            System.out.println("===================================\n");

            // Adiciona ao histórico via composição (SistemaIMC usa Historico)
            historico.adicionar(resultado);

        } catch (EntradaInvalidaException e) {
            System.out.println("\n[ERRO no cálculo] " + e.getMessage());
        }
    }

    // ===== MÉTODOS AUXILIARES DE LEITURA DE DADOS =====

    /**
     * Lê e valida o nome da pessoa/atleta.
     *
     * @return nome não-vazio como String
     * @throws EntradaInvalidaException se o nome for vazio ou nulo
     */
    private String lerNome() {
        System.out.print("  Nome: ");
        String nome = scanner.nextLine().trim();

        // Operador lógico: || (OR) — verifica nulo OU vazio
        if (nome == null || nome.isEmpty()) {
            throw new EntradaInvalidaException("Nome não pode ser vazio.");
        }

        return nome;
    }

    /**
     * Lê e valida a idade da pessoa/atleta.
     *
     * @return idade como int (deve ser > 0 e <= 150)
     * @throws EntradaInvalidaException se a entrada não for numérica ou for inválida
     */
    private int lerIdade() {
        System.out.print("  Idade (anos): ");
        String entrada = scanner.nextLine().trim();

        int idade;
        try {
            idade = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(
                "Idade inválida: '" + entrada + "'. Digite apenas números inteiros.", e
            );
        }

        // Operadores relacionais e lógicos: valida intervalo
        if (idade <= 0 || idade > 150) {
            throw new EntradaInvalidaException(
                "Idade inválida: " + idade + ". A idade deve estar entre 1 e 150 anos."
            );
        }

        return idade;
    }

    /**
     * Lê e valida o peso da pessoa/atleta.
     *
     * @return peso como double (deve ser > 0)
     * @throws EntradaInvalidaException se a entrada não for numérica ou for inválida
     */
    private double lerPeso() {
        System.out.print("  Peso (kg, use ponto como decimal, ex: 70.5): ");
        String entrada = scanner.nextLine().trim().replace(",", ".");

        double peso;
        try {
            peso = Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(
                "Peso inválido: '" + entrada + "'. Digite um número válido (ex: 70.5).", e
            );
        }

        // A validação de peso <= 0 também ocorre no setter de Pessoa
        if (peso <= 0) {
            throw new EntradaInvalidaException(
                "Peso inválido: " + peso + ". O peso deve ser maior que zero."
            );
        }

        return peso;
    }

    /**
     * Lê e valida a altura da pessoa/atleta.
     *
     * @return altura como double em metros (deve ser > 0 e <= 3.0)
     * @throws EntradaInvalidaException se a entrada não for numérica ou for inválida
     */
    private double lerAltura() {
        System.out.print("  Altura (m, use ponto como decimal, ex: 1.75): ");
        String entrada = scanner.nextLine().trim().replace(",", ".");

        double altura;
        try {
            altura = Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(
                "Altura inválida: '" + entrada + "'. Digite um número válido (ex: 1.75).", e
            );
        }

        // Operadores relacionais e lógicos: &&
        if (altura <= 0 || altura > 3.0) {
            throw new EntradaInvalidaException(
                "Altura inválida: " + altura + ". A altura deve estar entre 0.01 e 3.00 metros."
            );
        }

        return altura;
    }

    /**
     * Lê e valida a modalidade esportiva do atleta.
     *
     * @return modalidade como String não-vazia
     * @throws EntradaInvalidaException se a modalidade for vazia
     */
    private String lerModalidade() {
        System.out.print("  Modalidade esportiva: ");
        String modalidade = scanner.nextLine().trim();

        if (modalidade.isEmpty()) {
            throw new EntradaInvalidaException("Modalidade esportiva não pode ser vazia.");
        }

        return modalidade;
    }

    /**
     * Exibe mensagem de encerramento e fecha o Scanner.
     */
    private void encerrar() {
        System.out.println("\n========================================");
        System.out.println("  Obrigado por usar a Calculadora IMC!  ");
        System.out.println("  Até logo!                             ");
        System.out.println("========================================\n");

        // Exibe um resumo rápido do histórico ao sair
        if (!historico.isEmpty()) {
            System.out.println("  Cálculos realizados nesta sessão: " + historico.getTotalRegistros());
        }

        // Fecha o Scanner para liberar recursos (boa prática)
        scanner.close();
    }
}
