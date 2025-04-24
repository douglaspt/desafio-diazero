# Desafio Diazero

Este projeto é uma API desenvolvida com **Spring Boot 3.3.4** e **Java 17**, com gerenciamento de dependências feito via **Maven**. O objetivo é demonstrar habilidades em desenvolvimento backend utilizando práticas modernas e frameworks consolidados no ecossistema Java.

## 🛠️ Tecnologias Utilizadas

- **Java 17**: versão LTS com suporte a melhorias de performance e sintaxe.
- **Spring Boot 3.3.4**: framework para construção de aplicações robustas e escaláveis com mínima configuração.
- **Maven**: gerenciador de dependências e build.
- **Spring Web**: módulo para criação de APIs RESTful.
- **Spring Data JPA**: abstração de acesso a dados com integração com bancos relacionais.
- **H2 Database**: banco de dados em memória para testes e desenvolvimento.

## ▶️ Como compilar e executar o projeto

### Pré-requisitos

- Java 17 instalado
- Maven 3.8+ instalado

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/douglaspt/desafio-diazero.git
   cd desafio-diazero
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em:  
   [http://localhost:8080](http://localhost:8080)

5. A documentação Swagger estará disponível em:  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 📚 Frameworks e bibliotecas utilizadas

- **Spring Boot** foi escolhido por simplificar a configuração e acelerar o desenvolvimento de aplicações Java, com foco em produtividade.
- **Spring Web** facilita a criação de endpoints REST de forma declarativa e testável.
- **Spring Data JPA** reduz significativamente o boilerplate de acesso a dados, permitindo maior foco na lógica de negócio.
- **H2 Database** permite testes rápidos sem necessidade de instalação de bancos externos.
- **Maven** oferece controle eficiente de dependências e automatiza o processo de build/testes.

Essas escolhas foram feitas para garantir um desenvolvimento ágil, com código limpo, testável e alinhado às boas práticas modernas do ecossistema Java.

## 📝 Notas Adicionais

- O projeto está configurado para utilizar banco de dados em memória (H2). Para ambientes de produção, recomenda-se configurar um banco relacional como PostgreSQL ou MySQL.
- É possível acessar o console do H2 em [http://localhost:8080/h2-console](http://localhost:8080/h2-console) com as seguintes credenciais:
  - JDBC URL: `jdbc:h2:mem:db_diazero`
  - Usuário: `diazero`
  - Senha: `diazero`

## 📦 Estrutura do Projeto

```
src
└── main
    ├── java
    │   └── com.dpt.desafiodiazero
    │       ├── configuration
    │       ├── controller
    │       ├── dtos
    │       ├── model
    │       ├── repository
    │       └── service
    └── resources
        ├── application.properties
```


