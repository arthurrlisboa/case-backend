
---
# Loan Simulation API - Case Backend

Este projeto é uma **API de simulação de empréstimos** construída com **Java 23**, **Spring Boot** e **Maven**. Ele permite realizar simulações de empréstimos para um ou vários clientes.

## Tecnologias utilizadas

- **Java 23**
- **Spring Boot**
- **Maven**
- **Swagger** 
- **OpenAPI Generator**

## Como Subir a Aplicação

### 1. Clonar o Repositório

Clone este repositório para o seu ambiente local:

```bash
git clone https://github.com/arthurrlisboa/case-backend.git
cd loan-simulation-api
```

### 2. Certificar-se de Ter o Java 23 Instalado

Verifique se o **Java 23** está instalado em sua máquina:

```bash
java -version
```

Se não tiver o **Java 23** instalado, você pode [baixar aqui](https://adoptium.net/) ou usar um gerenciador de versões como o **SDKMAN**.

### 3. Compilar e Executar a Aplicação

Após garantir que o Java 23 está configurado corretamente, compile e execute a aplicação com Maven:

```bash
mvn clean install
mvn package
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

### 1. **Simulação de Empréstimo para um Cliente**

- **URL**: `/loan/simulation`
- **Método**: `POST`
- **Descrição**: Realiza a simulação de empréstimo para um único cliente.

#### Exemplo de Requisição (Body):
```json
{
  "userEmail": "user@exemplo.com",
  "loanAmount": 10000.00,
  "clientBirthDate": "20/10/1999",
  "paymentTermMonths": 10
}
```

#### Exemplo de Resposta (200 OK):
```json
{
  "userEmail": "user@exemplo.com",
  "totalPaymentAmount": "R$ 12.950,46",
  "monthlyInstallmentAmount": "R$ 1.295,05",
  "totalInterestPaid": "R$ 2.950,46"
}
```

### 2. **Simulação de Empréstimos para uma Lista de Clientes**

- **URL**: `/loan/simulation/list`
- **Método**: `POST`
- **Descrição**: Realiza simulações de empréstimos para uma lista de clientes.

#### Exemplo de Requisição (Body):
```json
[
  {
    "userEmail": "user1@test.com",
    "loanAmount": 5000.00,
    "clientBirthDate": "15/05/1985",
    "paymentTermMonths": 12
  },
  {
    "userEmail": "user2@test.com",
    "loanAmount": 15000.00,
    "clientBirthDate": "25/12/1990",
    "paymentTermMonths": 24
  }
]
```

#### Exemplo de Resposta (200 OK):
```json
[
  {
    "loanSimulationResponse": {
      "userEmail": "user1@test.com",
      "totalPaymentAmount": "R$ 6.027,73",
      "monthlyInstallmentAmount": "R$ 502,32",
      "totalInterestPaid": "R$ 1.027,73"
    },
    "success": true,
    "errorMessage": null
  },
  {
    "loanSimulationResponse": {
      "userEmail": "user2@test.com",
      "totalPaymentAmount": "R$ 21.257,07",
      "monthlyInstallmentAmount": "R$ 885,72",
      "totalInterestPaid": "R$ 6.257,07"
    },
    "success": true,
    "errorMessage": null
  }
]
```

## Estrutura e decisões de arquitetura

O projeto segue o padrão de Arquitetura Limpa (Clean Architecture) para a implementação deste desafio, com o objetivo de
garantir testabilidade, flexibilidade no código e manutenibilidade. Esse modelo foi escolhido para permitir que, em um 
cenário de aplicação real, a estrutura do sistema possa ser facilmente adaptada e expandida sem comprometer a lógica de 
negócios, facilitando a manutenção ao longo do tempo.

A arquitetura foi estruturada em três camadas principais: controller (responsável pela apresentação e interação com o
cliente), application (responsável pelos casos de uso e lógica de serviços) e domain (que contém as entidades centrais 
e as regras de negócio).

Optou-se por não incluir uma camada de infraestrutura, pois o sistema não depende de frameworks externos nem de integrações
com outras APIs ou bancos de dados, simplificando a arquitetura e o processo de desenvolvimento.

Para a implementação dos casos de uso, foi adotado o padrão de workflow, em que cada funcionalidade é composta por uma 
sequência de etapas chamadas activities. Nesse modelo, um workflow pode depender de outros workflows ou activities, 
mas uma activity nunca depende de workflows ou de outras activities. Esse padrão garante maior modularidade e 
flexibilidade, permitindo que as atividades sejam reutilizadas de forma eficiente.

Todas as estruturas de workflow e activities são implementadas como Components do Spring, o que facilita a reutilização 
e mantém um baixo acoplamento entre os diferentes elementos do sistema.

Além disso, a adoção desse padrão também se justifica pela facilidade de documentação das funcionalidades. 
O código segue um estilo descritivo e imperativo, com uma clara separação de responsabilidades, o que facilita o 
entendimento e a manutenção do sistema. Em um cenário de manutenção, as correções costumam ser localizadas e de baixo
impacto, tornando o processo de atualização do sistema mais eficiente.

---
