package com.calculadoraimc;

/**
 * Ponto de entrada principal da aplicação Calculadora de IMC.
 *
 * <p>A classe {@code Main} instancia e inicia o {@link SistemaIMC},
 * que orquestra toda a lógica da aplicação CLI.
 * O bloco {@code try/catch} garante que exceções inesperadas sejam
 * tratadas de forma amigável ao usuário.</p>
 *
 * Conceitos de POO demonstrados:
 * - Ponto de entrada padrão Java: método main static
 * - Tratamento de exceções: try/catch com Exception e EntradaInvalidaException
 * - Delegação: Main delega toda a responsabilidade ao SistemaIMC
 */
public class Main {

    /**
     * Método principal da aplicação.
     *
     * <p>Cria uma instância de {@link SistemaIMC} e inicia o loop
     * interativo de menu. Captura e exibe qualquer exceção não tratada
     * que possa surgir durante a execução.</p>
     *
     * @param args argumentos de linha de comando (não utilizados nesta aplicação)
     */
    public static void main(String[] args) {
        try {
            // Instancia o sistema principal e inicia a execução
            SistemaIMC sistema = new SistemaIMC();
            sistema.iniciar();

        } catch (EntradaInvalidaException e) {
            // Captura exceção personalizada de entrada inválida
            System.err.println("[ERRO DE ENTRADA] " + e.getMessage());
            System.exit(1);

        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada
            System.err.println("[ERRO INESPERADO] " + e.getMessage());
            System.err.println("Por favor, reinicie a aplicação.");
            e.printStackTrace();
            System.exit(2);
        }
    }
}
