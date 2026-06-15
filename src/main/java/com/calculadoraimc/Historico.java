package com.calculadoraimc;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por armazenar e exibir o histórico de cálculos de IMC.
 *
 * <p>Esta classe é utilizada por {@link SistemaIMC} através de composição,
 * ou seja, {@code SistemaIMC} possui um objeto {@code Historico} como atributo,
 * sem herdar dele. Essa abordagem segue o princípio "favor composição à herança".</p>
 *
 * Conceitos de POO demonstrados:
 * - Composição: utilizada por SistemaIMC (não herança)
 * - Encapsulamento: lista privada, acessada apenas pelos métodos da classe
 * - Coleções: uso de List<String> com ArrayList
 */
public class Historico {

    // Lista privada que armazena os registros de cálculo (encapsulamento)
    // Tipo genérico List<String> para armazenar descrições dos cálculos
    private final List<String> registros;

    /**
     * Construtor padrão. Inicializa a lista de registros como ArrayList vazio.
     */
    public Historico() {
        this.registros = new ArrayList<>();
    }

    /**
     * Adiciona um novo registro ao histórico.
     *
     * <p>Cada registro é uma String que descreve um cálculo de IMC
     * realizado durante a sessão.</p>
     *
     * @param texto descrição do cálculo a ser registrado
     */
    public void adicionar(String texto) {
        // Operador lógico: valida que o texto não é nulo nem vazio
        if (texto != null && !texto.isBlank()) {
            registros.add(texto);
        }
    }

    /**
     * Exibe todos os registros armazenados no histórico.
     *
     * <p>Se não houver registros, exibe uma mensagem informativa.
     * Caso contrário, lista todos com numeração sequencial.</p>
     */
    public void exibir() {
        System.out.println("\n========================================");
        System.out.println("          HISTÓRICO DE CÁLCULOS         ");
        System.out.println("========================================");

        // Operador relacional: verifica se a lista está vazia
        if (registros.isEmpty()) {
            System.out.println("Nenhum cálculo realizado nesta sessão.");
        } else {
            // Operador aritmético: incremento de índice na iteração
            int contador = 1;
            for (String registro : registros) {
                System.out.println("\n[Cálculo #" + contador + "]");
                System.out.println(registro);
                System.out.println("----------------------------------------");
                contador++;
            }
            System.out.println("\nTotal de cálculos realizados: " + registros.size());
        }

        System.out.println("========================================\n");
    }

    /**
     * Retorna o número de registros armazenados no histórico.
     *
     * @return quantidade de registros como int
     */
    public int getTotalRegistros() {
        return registros.size();
    }

    /**
     * Verifica se o histórico está vazio.
     *
     * @return true se não houver registros, false caso contrário
     */
    public boolean isEmpty() {
        // Delega para a lista interna (encapsulamento)
        return registros.isEmpty();
    }
}
