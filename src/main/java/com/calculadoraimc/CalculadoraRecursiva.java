package com.calculadoraimc;

/**
 * Fornece operações matemáticas implementadas de forma recursiva.
 *
 * <p>Esta classe utilitária implementa o cálculo de potência sem usar
 * {@code Math.pow()}, utilizando exclusivamente recursão. O método é
 * estático pois não depende de estado de instância.</p>
 *
 * Conceitos de POO demonstrados:
 * - Recursão: método que chama a si mesmo com um problema menor
 * - Método estático: acessível sem instanciar a classe
 * - Encapsulamento: lógica interna isolada nesta classe utilitária
 */
public class CalculadoraRecursiva {

    /**
     * Construtor privado para impedir instanciação desta classe utilitária.
     * Como todos os métodos são estáticos, não faz sentido criar objetos.
     */
    private CalculadoraRecursiva() {
        // Classe utilitária: não deve ser instanciada
    }

    /**
     * Calcula a potência de um número de forma recursiva.
     *
     * <p>Implementação sem uso de {@code Math.pow()}.
     * A recursão funciona da seguinte forma:</p>
     * <ul>
     *   <li><strong>Caso base:</strong> expoente == 0 → retorna 1 (qualquer número elevado a 0 é 1)</li>
     *   <li><strong>Caso base:</strong> expoente == 1 → retorna a própria base</li>
     *   <li><strong>Passo recursivo:</strong> base * potencia(base, expoente - 1)</li>
     * </ul>
     *
     * <p>Exemplo de execução para potencia(1.75, 2):
     * <pre>
     *   potencia(1.75, 2)
     *   → 1.75 * potencia(1.75, 1)
     *   → 1.75 * (1.75 * potencia(1.75, 0))
     *   → 1.75 * (1.75 * 1)
     *   → 1.75 * 1.75
     *   → 3.0625
     * </pre>
     * </p>
     *
     * @param base     o número-base (double)
     * @param expoente o expoente inteiro não-negativo (int)
     * @return o resultado de base elevado ao expoente
     * @throws EntradaInvalidaException se o expoente for negativo
     */
    public static double potencia(double base, int expoente) {
        // Validação: expoente negativo não é suportado nesta implementação
        if (expoente < 0) {
            throw new EntradaInvalidaException(
                "Expoente inválido: " + expoente + ". Use apenas expoentes não-negativos."
            );
        }

        // CASO BASE: qualquer número elevado a 0 é igual a 1
        // Operador relacional: ==
        if (expoente == 0) {
            return 1.0;
        }

        // CASO BASE ADICIONAL: qualquer número elevado a 1 é ele mesmo
        // Evita uma chamada recursiva desnecessária
        if (expoente == 1) {
            return base;
        }

        // PASSO RECURSIVO: base * potencia(base, expoente - 1)
        // Operador aritmético: multiplicação e subtração
        // A cada chamada, o expoente decresce em 1 até atingir o caso base
        return base * potencia(base, expoente - 1);
    }
}
