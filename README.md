# Calculadora de IMC — CLI (Java 17 + Maven)

Aplicação de linha de comando (CLI) para cálculo e classificação do **Índice de Massa Corporal (IMC)**, desenvolvida em **Java 17** com **Maven**, demonstrando todos os principais conceitos de **Programação Orientada a Objetos (POO)**.

---

## Descrição do Projeto

A aplicação permite cadastrar **pessoas comuns** e **atletas**, calcular o IMC de cada um e armazenar o histórico de cálculos durante a sessão. A interface é totalmente baseada em terminal (CLI) com menu interativo via `Scanner`.

---

## Requisitos

| Ferramenta | Versão mínima |
|------------|--------------|
| Java (JDK) | 17           |
| Maven      | 3.8+         |

---

## Estrutura dos Arquivos

```
calculadora-imc/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/calculadoraimc/
    │           ├── CalculadoraIMC.java         # Interface
    │           ├── PessoaBase.java             # Classe abstrata
    │           ├── Pessoa.java                 # extends PessoaBase, implements CalculadoraIMC
    │           ├── Atleta.java                 # extends Pessoa (herança multinível)
    │           ├── Historico.java              # Composição: armazena registros
    │           ├── SistemaIMC.java             # Orquestração + composição com Historico
    │           ├── CalculadoraRecursiva.java   # Recursão para potência
    │           ├── EntradaInvalidaException.java # Exceção personalizada
    │           └── Main.java                   # Ponto de entrada
    └── test/
        └── java/
            └── com/calculadoraimc/
                └── CalculadoraIMCTest.java     # Testes JUnit 5
```

---

## Como Compilar

No diretório raiz do projeto (onde está o `pom.xml`):

```bash
mvn clean compile
```

Para compilar e rodar os testes:

```bash
mvn clean test
```

Para gerar o JAR executável:

```bash
mvn clean package
```

---

## Como Executar

### Opção 1 — Via Maven (recomendado)

```bash
mvn clean compile exec:java -Dexec.mainClass="com.calculadoraimc.Main"
```

### Opção 2 — Via JAR gerado

```bash
mvn clean package
java -jar target/calculadora-imc.jar
```

---

## Exemplo de Uso

```
╔════════════════════════════════════════╗
║      CALCULADORA DE IMC - CLI          ║
║   Sistema de Índice de Massa Corporal  ║
╚════════════════════════════════════════╝
  Bem-vindo(a)! Utilize o menu abaixo.

========================================
                  MENU
========================================
  1 - Cadastrar Pessoa
  2 - Cadastrar Atleta
  3 - Calcular IMC da última pessoa cadastrada
  4 - Exibir Histórico
  0 - Sair
========================================
  [Nenhuma pessoa cadastrada ainda]
========================================
  Digite a opção desejada: 1

--- CADASTRAR PESSOA ---
  Nome: João Silva
  Idade (anos): 30
  Peso (kg, use ponto como decimal, ex: 70.5): 80.0
  Altura (m, use ponto como decimal, ex: 1.75): 1.75

[OK] Pessoa cadastrada com sucesso!
=== PERFIL DA PESSOA ===
Nome   : João Silva
Idade  : 30 anos
Peso   : 80,00 kg
Altura : 1,75 m
Ativo  : Sim

  Digite a opção desejada: 3

--- CALCULAR IMC ---

=== RESULTADO DO CÁLCULO DE IMC ===
Nome          : João Silva
Tipo          : PESSOA
Peso          : 80,00 kg
Altura        : 1,75 m
IMC calculado : 26,12
Classificação : Sobrepeso
===================================

  Digite a opção desejada: 2

--- CADASTRAR ATLETA ---
  Nome: Maria Atleta
  Idade (anos): 25
  Peso (kg): 65.0
  Altura (m): 1.70
  Modalidade esportiva: Natação

[OK] Atleta cadastrado com sucesso!

  Digite a opção desejada: 3

=== RESULTADO DO CÁLCULO DE IMC ===
Nome          : Maria Atleta
Tipo          : ATLETA
Peso          : 65,00 kg
Altura        : 1,70 m
IMC calculado : 22,49
Classificação : Ideal para atleta   ← classificação diferente para atleta!
===================================

  Digite a opção desejada: 4

========================================
          HISTÓRICO DE CÁLCULOS
========================================

[Cálculo #1]
Nome          : João Silva
...
[Cálculo #2]
Nome          : Maria Atleta
...
Total de cálculos realizados: 2

  Digite a opção desejada: 0

========================================
  Obrigado por usar a Calculadora IMC!
  Até logo!
========================================
```

---

## Conceitos de POO Utilizados

### 1. Interface
`CalculadoraIMC` define o contrato com `calcularIMC()` e `classificarIMC()`. Implementada por `Pessoa` (e herdada por `Atleta`).

### 2. Classe Abstrata
`PessoaBase` declara `exibirPerfil()` como método abstrato, forçando subclasses a fornecer implementação própria.

### 3. Herança Simples
`Pessoa` herda de `PessoaBase`, reaproveitando `nome`, `idade`, `getNome()` e `getIdade()`.

### 4. Herança Multinível
`PessoaBase → Pessoa → Atleta`. Atleta herda de Pessoa que herda de PessoaBase.

### 5. Polimorfismo
`Atleta` sobrescreve `classificarIMC()` com `@Override`, usando faixas específicas para atletas. Uma referência `Pessoa` apontando para `Atleta` executa o método correto em runtime.

### 6. Encapsulamento
Atributos privados em `Pessoa` (`peso`, `altura`, `ativo`) acessíveis apenas por getters/setters. O setter `setPeso()` valida e lança exceção se o valor for inválido.

### 7. Composição
`SistemaIMC` possui um `Historico` interno (relação "tem um"), sem herdar dele. Garante baixo acoplamento e responsabilidade única.

### 8. Recursão
`CalculadoraRecursiva.potencia(base, expoente)` calcula `altura²` sem usar `Math.pow()`, com caso base `expoente == 0 → 1`.

### 9. Exceção Personalizada
`EntradaInvalidaException extends RuntimeException` encapsula erros de domínio: peso inválido, altura inválida, opção de menu inexistente, entrada não numérica.

### 10. Tipos de Dados
- `int` → idade, contador de histórico
- `double` → peso, altura, IMC
- `String` → nome, modalidade, classificação
- `boolean` → flag `ativo`, flag `rodando`

### 11. Operadores
- **Aritméticos**: `/` no cálculo do IMC, `*` e `-` na recursão
- **Relacionais**: `<`, `>=`, `<=`, `==`, `!=` nas classificações e validações
- **Lógicos**: `&&` (AND), `||` (OR) nas faixas de IMC e validações

### 12. Coleções
`List<String>` com `ArrayList` no `Historico` para armazenar registros de cálculo.

---

## Tabelas de Classificação de IMC

### Pessoa Comum

| IMC              | Classificação        |
|------------------|----------------------|
| < 18.5           | Abaixo do peso       |
| 18.5 – 24.9      | Peso normal          |
| 25.0 – 29.9      | Sobrepeso            |
| 30.0 – 34.9      | Obesidade Grau I     |
| 35.0 – 39.9      | Obesidade Grau II    |
| ≥ 40.0           | Obesidade Grau III   |

### Atleta

| IMC              | Classificação                   |
|------------------|---------------------------------|
| < 20.0           | Abaixo do ideal para atleta     |
| 20.0 – 26.99     | Ideal para atleta               |
| ≥ 27.0           | Acima do ideal para atleta      |

---

## Dependências

| Dependência            | Versão  | Escopo |
|------------------------|---------|--------|
| junit-jupiter-api      | 5.10.1  | test   |
| junit-jupiter-engine   | 5.10.1  | test   |
| junit-jupiter-params   | 5.10.1  | test   |

---

## Autor

Projeto desenvolvido como exercício completo de **Programação Orientada a Objetos em Java 17**.
