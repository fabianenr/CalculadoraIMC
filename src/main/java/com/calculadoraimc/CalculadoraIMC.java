package com.calculadoraimc;

/**
 * Interface que define o contrato para cálculo e classificação de IMC.
 *
 * <p>Esta interface é implementada por qualquer classe que precise
 * calcular e classificar o Índice de Massa Corporal (IMC).
 * O uso de interface garante o princípio de abstração e polimorfismo da POO.</p>
 *
 * Conceitos de POO demonstrados:
 * - Abstração: define o "o quê" sem o "como"
 * - Polimorfismo: diferentes classes implementam os métodos de formas distintas
 */
public interface CalculadoraIMC {

    /**
     * Calcula o Índice de Massa Corporal (IMC).
     *
     * <p>Fórmula: IMC = peso / (altura²)</p>
     *
     * @param peso   o peso da pessoa em quilogramas (kg)
     * @param altura a altura da pessoa em metros (m)
     * @return o valor do IMC calculado como double
     */
    double calcularIMC(double peso, double altura);

    /**
     * Classifica o IMC de acordo com as faixas estabelecidas.
     *
     * <p>A classificação pode variar conforme a implementação:
     * pessoas comuns e atletas possuem faixas diferentes.</p>
     *
     * @param imc o valor do IMC previamente calculado
     * @return uma String descrevendo a classificação do IMC
     */
    String classificarIMC(double imc);
}
