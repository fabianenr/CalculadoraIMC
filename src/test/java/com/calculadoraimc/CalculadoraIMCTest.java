package com.calculadoraimc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para as classes do sistema de Calculadora de IMC.
 *
 * <p>Utiliza JUnit Jupiter (JUnit 5) para validar o comportamento correto
 * de todas as classes: Pessoa, Atleta, CalculadoraRecursiva, Historico
 * e EntradaInvalidaException.</p>
 */
@DisplayName("Testes do Sistema de Calculadora de IMC")
class CalculadoraIMCTest {

    // ========================================================================
    // TESTES: CalculadoraRecursiva
    // ========================================================================

    @Nested
    @DisplayName("CalculadoraRecursiva")
    class CalculadoraRecursivaTest {

        @Test
        @DisplayName("Deve retornar 1 quando expoente for 0")
        void deveRetornar1QuandoExpoentForZero() {
            double resultado = CalculadoraRecursiva.potencia(5.0, 0);
            assertEquals(1.0, resultado, 0.0001,
                "Qualquer número elevado a 0 deve ser 1");
        }

        @Test
        @DisplayName("Deve retornar a base quando expoente for 1")
        void deveRetornarBaseQuandoExpoentForUm() {
            double resultado = CalculadoraRecursiva.potencia(3.5, 1);
            assertEquals(3.5, resultado, 0.0001,
                "Qualquer número elevado a 1 deve ser ele mesmo");
        }

        @Test
        @DisplayName("Deve calcular 1.75 ao quadrado corretamente")
        void deveCalcularAlturaAoQuadrado() {
            double resultado = CalculadoraRecursiva.potencia(1.75, 2);
            assertEquals(3.0625, resultado, 0.0001,
                "1.75² deve ser igual a 3.0625");
        }

        @Test
        @DisplayName("Deve calcular 2 elevado a 10 corretamente")
        void deveCalcularDoisElevadoADez() {
            double resultado = CalculadoraRecursiva.potencia(2.0, 10);
            assertEquals(1024.0, resultado, 0.0001,
                "2^10 deve ser 1024");
        }

        @Test
        @DisplayName("Deve lançar exceção para expoente negativo")
        void deveLancarExcecaoParaExpoentNegativo() {
            assertThrows(EntradaInvalidaException.class,
                () -> CalculadoraRecursiva.potencia(2.0, -1),
                "Expoente negativo deve lançar EntradaInvalidaException");
        }
    }

    // ========================================================================
    // TESTES: Pessoa
    // ========================================================================

    @Nested
    @DisplayName("Pessoa")
    class PessoaTest {

        private Pessoa pessoa;

        @BeforeEach
        void setUp() {
            pessoa = new Pessoa("João Silva", 30, 80.0, 1.75);
        }

        @Test
        @DisplayName("Deve criar Pessoa com dados válidos")
        void deveCriarPessoaComDadosValidos() {
            assertNotNull(pessoa, "Pessoa não deve ser nula");
            assertEquals("João Silva", pessoa.getNome());
            assertEquals(30, pessoa.getIdade());
            assertEquals(80.0, pessoa.getPeso(), 0.001);
            assertEquals(1.75, pessoa.getAltura(), 0.001);
            assertTrue(pessoa.isAtivo(), "Pessoa deve iniciar ativa");
        }

        @Test
        @DisplayName("Deve calcular IMC corretamente")
        void deveCalcularIMCCorretamente() {
            double imc = pessoa.calcularIMC(80.0, 1.75);
            // IMC = 80 / (1.75²) = 80 / 3.0625 ≈ 26.12
            assertEquals(26.12, imc, 0.01,
                "IMC de 80kg e 1.75m deve ser aproximadamente 26.12");
        }

        @ParameterizedTest(name = "IMC {0} deve ser classificado como: {1}")
        @CsvSource({
            "17.0, Abaixo do peso",
            "22.0, Peso normal",
            "27.5, Sobrepeso",
            "32.0, Obesidade Grau I",
            "37.0, Obesidade Grau II",
            "42.0, Obesidade Grau III"
        })
        @DisplayName("Deve classificar IMC de pessoa comum corretamente")
        void deveClassificarIMCCorretamente(double imc, String esperado) {
            String resultado = pessoa.classificarIMC(imc);
            assertEquals(esperado, resultado,
                "IMC " + imc + " deveria ser '" + esperado + "'");
        }

        @Test
        @DisplayName("Deve lançar exceção para peso zero")
        void deveLancarExcecaoParaPesoZero() {
            assertThrows(EntradaInvalidaException.class,
                () -> new Pessoa("Maria", 25, 0.0, 1.60),
                "Peso zero deve lançar EntradaInvalidaException");
        }

        @Test
        @DisplayName("Deve lançar exceção para peso negativo")
        void deveLancarExcecaoParaPesoNegativo() {
            assertThrows(EntradaInvalidaException.class,
                () -> new Pessoa("Carlos", 35, -10.0, 1.80),
                "Peso negativo deve lançar EntradaInvalidaException");
        }

        @Test
        @DisplayName("Deve lançar exceção para altura zero")
        void deveLancarExcecaoParaAlturaZero() {
            assertThrows(EntradaInvalidaException.class,
                () -> new Pessoa("Ana", 28, 65.0, 0.0),
                "Altura zero deve lançar EntradaInvalidaException");
        }

        @Test
        @DisplayName("Deve exibir perfil com todas as informações")
        void deveExibirPerfilCompleto() {
            String perfil = pessoa.exibirPerfil();
            assertTrue(perfil.contains("João Silva"), "Perfil deve conter o nome");
            assertTrue(perfil.contains("30"), "Perfil deve conter a idade");
            assertTrue(perfil.contains("PERFIL DA PESSOA"), "Perfil deve ter cabeçalho de PESSOA");
        }
    }

    // ========================================================================
    // TESTES: Atleta
    // ========================================================================

    @Nested
    @DisplayName("Atleta")
    class AtletaTest {

        private Atleta atleta;

        @BeforeEach
        void setUp() {
            atleta = new Atleta("Pedro Oliveira", 25, 90.0, 1.85, "Natação");
        }

        @Test
        @DisplayName("Deve criar Atleta com dados válidos incluindo modalidade")
        void deveCriarAtletaComDadosValidos() {
            assertNotNull(atleta);
            assertEquals("Pedro Oliveira", atleta.getNome());
            assertEquals(25, atleta.getIdade());
            assertEquals(90.0, atleta.getPeso(), 0.001);
            assertEquals(1.85, atleta.getAltura(), 0.001);
            assertEquals("Natação", atleta.getModalidade());
        }

        @Test
        @DisplayName("Atleta deve herdar de Pessoa (herança multinível)")
        void atletaDeveHerdarDePessoa() {
            assertTrue(atleta instanceof Pessoa,
                "Atleta deve ser instância de Pessoa (herança)");
            assertTrue(atleta instanceof PessoaBase,
                "Atleta deve ser instância de PessoaBase (herança multinível)");
            assertTrue(atleta instanceof CalculadoraIMC,
                "Atleta deve implementar CalculadoraIMC");
        }

        @ParameterizedTest(name = "IMC {0} para atleta deve ser: {1}")
        @CsvSource({
            "19.0, Abaixo do ideal para atleta",
            "23.0, Ideal para atleta",
            "27.0, Acima do ideal para atleta"
        })
        @DisplayName("Deve classificar IMC de atleta com faixas específicas")
        void deveClassificarIMCDeAtletaCorretamente(double imc, String esperado) {
            String resultado = atleta.classificarIMC(imc);
            assertEquals(esperado, resultado,
                "IMC " + imc + " para atleta deveria ser '" + esperado + "'");
        }

        @Test
        @DisplayName("Classificação de atleta deve diferir da classificação de pessoa")
        void classificacaoAtletaDeveDiferirDePessoa() {
            // IMC 25.0: pessoa → "Sobrepeso", atleta → "Ideal para atleta"
            Pessoa pessoa = new Pessoa("Teste", 30, 70.0, 1.70);
            String classAtleta = atleta.classificarIMC(25.0);
            String classPessoa = pessoa.classificarIMC(25.0);

            assertNotEquals(classAtleta, classPessoa,
                "Classificação de atleta e pessoa devem diferir para IMC 25.0 (polimorfismo)");
        }

        @Test
        @DisplayName("Deve exibir perfil com modalidade esportiva")
        void deveExibirPerfilComModalidade() {
            String perfil = atleta.exibirPerfil();
            assertTrue(perfil.contains("Natação"), "Perfil do atleta deve conter modalidade");
            assertTrue(perfil.contains("PERFIL DO ATLETA"), "Perfil deve ter cabeçalho de ATLETA");
        }
    }

    // ========================================================================
    // TESTES: Historico
    // ========================================================================

    @Nested
    @DisplayName("Historico")
    class HistoricoTest {

        private Historico historico;

        @BeforeEach
        void setUp() {
            historico = new Historico();
        }

        @Test
        @DisplayName("Deve iniciar vazio")
        void deveIniciarVazio() {
            assertTrue(historico.isEmpty(), "Histórico deve iniciar vazio");
            assertEquals(0, historico.getTotalRegistros(), "Total de registros deve ser 0");
        }

        @Test
        @DisplayName("Deve adicionar registros corretamente")
        void deveAdicionarRegistros() {
            historico.adicionar("Cálculo 1: João, IMC 25.5");
            historico.adicionar("Cálculo 2: Maria, IMC 22.0");

            assertFalse(historico.isEmpty(), "Histórico não deve estar vazio após adições");
            assertEquals(2, historico.getTotalRegistros(), "Deve ter 2 registros");
        }

        @Test
        @DisplayName("Deve ignorar registros nulos ou vazios")
        void deveIgnorarRegistrosNulosOuVazios() {
            historico.adicionar(null);
            historico.adicionar("");
            historico.adicionar("   ");

            assertTrue(historico.isEmpty(),
                "Histórico deve permanecer vazio ao adicionar registros inválidos");
        }
    }

    // ========================================================================
    // TESTES: EntradaInvalidaException
    // ========================================================================

    @Nested
    @DisplayName("EntradaInvalidaException")
    class EntradaInvalidaExceptionTest {

        @Test
        @DisplayName("Deve herdar de RuntimeException")
        void deveHerdarDeRuntimeException() {
            EntradaInvalidaException ex = new EntradaInvalidaException("teste");
            assertTrue(ex instanceof RuntimeException,
                "EntradaInvalidaException deve ser RuntimeException");
        }

        @Test
        @DisplayName("Deve armazenar a mensagem corretamente")
        void deveArmazenarMensagem() {
            String mensagem = "Peso inválido: -5.0";
            EntradaInvalidaException ex = new EntradaInvalidaException(mensagem);
            assertEquals(mensagem, ex.getMessage());
        }

        @Test
        @DisplayName("Deve aceitar construtor com causa")
        void deveAceitarConstrutoComCausa() {
            NumberFormatException causa = new NumberFormatException("abc");
            EntradaInvalidaException ex = new EntradaInvalidaException("Erro de parse", causa);

            assertEquals("Erro de parse", ex.getMessage());
            assertEquals(causa, ex.getCause());
        }
    }
}
