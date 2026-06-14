# 💰 Finanças API

API REST desenvolvida com Java e Spring Boot para gerenciamento de finanças pessoais.

O sistema permite cadastrar categorias, registrar transações financeiras, consultar resumos e gerar indicadores financeiros através de endpoints REST documentados com Swagger.

---

## 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot 4
* Spring Data JPA
* PostgreSQL
* Flyway
* Bean Validation
* Lombok
* Swagger / OpenAPI
* JUnit 5
* Mockito
* Maven
* Railway

---

## 📋 Funcionalidades

### Categorias

* Criar categoria
* Buscar categoria por ID
* Listar categorias
* Atualizar categoria
* Excluir categoria

### Transações

* Criar transação
* Buscar transação por ID
* Atualizar transação
* Excluir transação
* Listar transações com paginação
* Filtrar transações por período
* Buscar transações por tipo (ENTRADA ou SAIDA)

### Relatórios

* Resumo financeiro
* Dashboard financeiro

---

## 🏗️ Estrutura do Projeto

```text
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── exception
 ├── config
 └── db
      └── migration
```

---

## 🗄️ Banco de Dados

O projeto utiliza PostgreSQL.

As tabelas são controladas através do Flyway.

Migration inicial:

```sql
V1__create_tables.sql
```

Tabelas criadas:

* categorias
* transacoes

---

## ⚙️ Configuração

Defina as variáveis de ambiente:

```properties
DB_URL=jdbc:postgresql://localhost:5432/financas_db
DB_USER=postgres
DB_PASSWORD=sua_senha
```

---

## ▶️ Executando Localmente

Clone o repositório:

```bash
git clone https://github.com/seu-usuario/financas-api.git
```

Entre na pasta:

```bash
cd financas-api
```

Execute:

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

---

## 📚 Documentação da API

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

---

## ☁️ Deploy

Aplicação publicada no Railway.

A API está preparada para utilização com variáveis de ambiente e execução automatizada das migrations do Flyway durante o deploy.

---

## 🧪 Testes

Foram implementados testes unitários utilizando:

* JUnit 5
* Mockito

Cobertura atual:

### TransacaoService

* Criar transação com sucesso
* Criar transação com categoria inexistente
* Buscar transação por ID
* Excluir transação com sucesso
* Excluir transação inexistente

Executar testes:

```bash
mvn test
```

---

## ⭐ Diferenciais Implementados

* Arquitetura em camadas
* DTOs para entrada e saída de dados
* Validação de requisições com Bean Validation
* Tratamento global de exceções
* Paginação de resultados
* Filtros por período
* Dashboard financeiro
* Resumo financeiro
* Documentação Swagger/OpenAPI
* Testes unitários
* Controle de versão do banco com Flyway
* Deploy em nuvem com Railway

---
## 🔮 Próximas Implementações

- Implementação de autenticação e autorização com JWT
- Controle de usuários e perfis
- Dashboard financeiro com gráficos
- Relatórios mensais em PDF
- Testes de integração utilizando Testcontainers
- Monitoramento da aplicação com Spring Boot Actuator

---

## 👨‍💻 Autor

Alan Silva

Desenvolvedor Java Back-End.
