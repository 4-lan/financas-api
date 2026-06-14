# Finanças API

API REST desenvolvida com Spring Boot para controle financeiro pessoal, permitindo o gerenciamento de categorias e transações financeiras, além de fornecer resumos e indicadores financeiros.

🚀 Produção:
https://financas-api-production-cac9.up.railway.app

📖 Swagger:
https://financas-api-production-cac9.up.railway.app/swagger-ui/index.html

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Swagger / OpenAPI
* Bean Validation
* Lombok
* Railway (Deploy)

## Funcionalidades

### Categorias

* Criar categoria
* Listar categorias
* Buscar categoria por ID
* Atualizar categoria
* Excluir categoria

### Transações

* Criar transação
* Listar transações com paginação
* Buscar transação por ID
* Buscar transações por tipo
* Filtrar transações por período
* Atualizar transação
* Excluir transação

### Dashboard Financeiro

* Total de entradas
* Total de saídas
* Saldo atual
* Quantidade de entradas
* Quantidade de saídas

### Resumo Financeiro

* Total de receitas
* Total de despesas
* Saldo consolidado

## Diferenciais do Projeto

- Deploy em produção no Railway
- Banco PostgreSQL hospedado na nuvem
- Documentação automática com Swagger/OpenAPI
- Tratamento global de exceções
- Validação de dados com Bean Validation
- Paginação de resultados
- Logs de aplicação com SLF4J
- API pública acessível pela internet

## Validações

A API utiliza Bean Validation para garantir a integridade dos dados:

* Campos obrigatórios
* Valores positivos
* Datas não futuras
* Limites de tamanho para textos
* Tratamento de erros padronizado

## Documentação Swagger

A documentação interativa está disponível em:

https://financas-api-production-cac9.up.railway.app/swagger-ui/index.html

## API em Produção

URL base:

https://financas-api-production-cac9.up.railway.app

## Exemplos

### Criar Categoria

POST /categorias

```json
{
  "nome": "Salário"
}
```

### Criar Transação

POST /transacoes

```json
{
  "tipo": "ENTRADA",
  "valor": 5000.00,
  "data": "2026-06-12",
  "descricao": "Salário mensal",
  "idCategoria": "UUID_DA_CATEGORIA"
}
```

## Como Executar Localmente

### Clonar o projeto

```bash
git clone https://github.com/4-lan/financas-api.git
```

### Configurar variáveis de ambiente

```yaml
DB_URL=jdbc:postgresql://localhost:5432/financas_db
DB_USER=postgres
DB_PASSWORD=sua_senha
```

### Executar a aplicação

```bash
./mvnw spring-boot:run
```

## Autor

Alan Silva

GitHub:
https://github.com/4-lan
