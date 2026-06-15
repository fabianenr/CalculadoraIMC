package com.calculadoraimc;

/**
 * Exceção personalizada para representar entradas inválidas no sistema.
 *
 * <p>Esta exceção herda de {@link RuntimeException} (unchecked exception),
 * sendo lançada em situações como:</p>
 * <ul>
 *   <li>Peso menor ou igual a zero</li>
 *   <li>Altura menor ou igual a zero</li>
 *   <li>Entrada não numérica onde número é esperado</li>
 *   <li>Opção de menu fora do intervalo válido</li>
 * </ul>
 *
 * Conceitos de POO demonstrados:
 * - Herança: herda de RuntimeException (hierarquia de exceções)
 * - Encapsulamento: encapsula a mensagem de erro contextual
 * - Exceção personalizada: tipo específico para domínio da aplicação
 */
public class EntradaInvalidaException extends RuntimeException {

    /**
     * Número de versão para serialização.
     * Boa prática para classes que implementam Serializable (herdado de Throwable).
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constrói uma nova exceção com a mensagem de erro especificada.
     *
     * <p>Chama o construtor de {@link RuntimeException} via {@code super(mensagem)},
     * demonstrando o uso de {@code super} em hierarquias de herança.</p>
     *
     * @param mensagem descrição detalhada do erro que causou a exceção
     */
    public EntradaInvalidaException(String mensagem) {
        super(mensagem);
    }

    /**
     * Constrói uma nova exceção com mensagem e causa original.
     *
     * <p>Útil para encadear exceções (exception chaining), preservando
     * o stack trace da exceção original.</p>
     *
     * @param mensagem descrição detalhada do erro
     * @param causa    a exceção original que gerou este problema
     */
    public EntradaInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
