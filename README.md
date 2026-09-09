# 📝 Task Management API

API RESTful robusta, escalável e de alta performance desenvolvida em **Java 21** e **Spring Boot 3**, com persistência em **PostgreSQL** e tratamento global de exceções.

---

## 🎯 Sobre o Projeto

A **Task Management API** foi projetada seguindo as melhores práticas de arquitetura de software para o ecossistema Java enterprise. O projeto implementa um CRUD completo de gerenciamento de tarefas com separação rigorosa de responsabilidades entre camadas, DTOs para transferência segura de dados, validação de requisições e manipulação centralizada de erros com respostas HTTP semânticas.

---

## 🛠️ Tecnologias e Ferramentas

- **Linguagem:** Java 21 (LTS)
- **Framework Principal:** Spring Boot 3.x
- **Camada Web:** Spring Web (Spring MVC)
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL
- **Validação:** Jakarta Bean Validation (`@Valid`, `@NotBlank`, etc.)
- **Gerenciador de Dependências:** Maven
- **Controle de Versão:** Git & GitHub

---

## 🏗️ Arquitetura do Sistema

A aplicação adota uma **Arquitetura em 3 Camadas (3-Tier Architecture)** limpa e desacoplada:

```
[ Cliente HTTP / Postman / Frontend ]
               │
               ▼
   [ TaskController ]  <── @RestController & Mapeamento HTTP
               │
               ▼
     [ TaskService ]   <── Regras de Negócio & Lançamento de Exceções
               │
               ▼
   [ TaskRepository ]  <── @Repository (Spring Data JPA)
               │
               ▼
     [ PostgreSQL ]    <── Banco de Dados Relacional
```

### Principais Decisões Arquiteturais

1. **Injeção de Dependências por Construtor:** Promove imutabilidade (`private final`) e facilita testes unitários, evitando injeção por campo (`@Autowired`).
2. **Tratamento Global de Exceções:** Uso de `@ControllerAdvice` (`GlobalExceptionHandler`) para capturar exceções customizadas (`ResourceNotFoundException`) e padronizar respostas de erro via `ErrorResponse` DTO.
3. **Status HTTP Semânticos:** Retornos rigorosos conforme os padrões REST (`200 OK`, `201 Created`, `204 No Content`, `400 Bad Request`, `404 Not Found`).
4. **Isolamento de Entidades via DTOs:** Utilização de DTOs (`TaskUpdateRequest`) para receber payloads de requisição sem expor diretamente a entidade do banco de dados.

---

## 🔌 Endpoints da API

| Método | Endpoint | Descrição | Status de Sucesso | Status de Erro |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/tasks` | Lista todas as tarefas cadastradas | `200 OK` | - |
| `GET` | `/tasks/{id}` | Busca uma tarefa específica por ID | `200 OK` | `404 Not Found` |
| `POST` | `/tasks` | Cria uma nova tarefa | `201 Created` | `400 Bad Request` |
| `PUT` | `/tasks/{id}` | Atualiza uma tarefa existente por ID | `200 OK` | `400 Bad Request` / `404 Not Found` |
| `DELETE` | `/tasks/{id}` | Remove uma tarefa existente por ID | `204 No Content` | `404 Not Found` |

---

## 📄 Formato das Respostas de Erro

Quando um recurso não é encontrado ou ocorre uma falha na requisição, a API responde com um JSON padronizado (`ErrorResponse`):

```json
{
  "status": 404,
  "dateTime": "2026-09-09T14:20:00",
  "message": "Task not found with ID: 99"
}
```

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos

- **Java 21** instalado
- **PostgreSQL** instalado e em execução
- **Maven** (ou o executável `./mvnw` do próprio projeto)

### 1. Configurar o Banco de Dados

Crie um banco de dados no PostgreSQL chamado `task_api`:

```sql
CREATE DATABASE task_api;
```

### 2. Configurar as Propriedades da Aplicação

Edite o arquivo `src/main/resources/application.properties` com as credenciais do seu banco de dados local:

```properties
spring.application.name=task-api

# Conexão com o PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/task_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configurações do Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Executar a Aplicação

No terminal, navegue até a pasta raiz do projeto e rode:

```bash
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080/tasks`

---

## 📦 Histórico de Commits e Evolução

O projeto foi construído incrementalmente através de **commits atômicos e modulares**:

- `feat(exception): add ResourceNotFoundException and ErrorResponse DTO`
- `feat(exception): handle ResourceNotFoundException globally with 404 status`
- `refactor(service): throw ResourceNotFoundException in find and update operations`
- `refactor(controller): simplify task GET and PUT endpoints by removing redundant null-checks`
- `refactor(task): implementa exclusao segura com orElseThrow e status 204`

---

## ✒️ Autor

Desenvolvido com foco na evolução para **Desenvolvedor Java Full Stack**.
