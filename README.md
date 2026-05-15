![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen?style=for-the-badge&logo=springboot)
![JWT](https://img.shields.io/badge/JWT-Authentication-red?style=for-the-badge&logo=jsonwebtokens)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue?style=for-the-badge&logo=docker)
![Gradle](https://img.shields.io/badge/Gradle-Build_Tool-black?style=for-the-badge&logo=gradle)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-CI/CD-blue?style=for-the-badge&logo=githubactions)

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:134E5E,50:71B280,100:2C5364&height=220&section=header&text=Agendador%20de%20Tarefas&fontSize=40&fontColor=ffffff&animation=fadeIn" />
</p>

<p align="center">
  <b>API REST para gerenciamento e agendamento de tarefas utilizando Spring Boot e JWT.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/github/stars/ViniciusS4ntos/agendador-tarefas-api?style=social" />
  <img src="https://img.shields.io/github/forks/ViniciusS4ntos/agendador-tarefas-api?style=social" />
  <img src="https://img.shields.io/github/issues/ViniciusS4ntos/agendador-tarefas-api" />
</p>

---

# Agendador de Tarefas API

**Agendador de Tarefas API** é uma API REST desenvolvida com **Java + Spring Boot** para gerenciamento de tarefas e notificações.

O projeto possui autenticação utilizando **JWT**, integração entre microsserviços através de **Feign Client** e estrutura organizada em camadas.

A aplicação também conta com suporte para execução via **Docker** e pipeline CI/CD utilizando **GitHub Actions**.

---

# Tecnologias Utilizadas

- Java 17  
- Spring Boot 3  
- Spring Security  
- JWT (JSON Web Token)  
- OpenFeign  
- PostgreSQL  
- Docker  
- Gradle  
- GitHub Actions  
- Lombok  

---

# Funcionalidades

- Cadastro de tarefas  
- Atualização de tarefas  
- Exclusão de tarefas  
- Busca de tarefas  
- Autenticação via JWT  
- Integração entre microsserviços  
- Controle de notificações  
- Tratamento global de exceções  
- Containerização com Docker  
- Pipeline CI/CD  

---

# Pré-requisitos

- Java 17 instalado  
- Docker instalado  
- PostgreSQL configurado  

---

# Rodando o Projeto

## 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/agendador-tarefas-api.git
cd agendador-tarefas-api
```

---

## 2. Configure o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agendador
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 3. Execute os containers

```bash
docker-compose up --build
```

---

## 4. Rodar manualmente

### Linux/Mac

```bash
./gradlew bootRun
```

### Windows

```bash
gradlew.bat bootRun
```

---

# Autenticação JWT

A API utiliza autenticação baseada em JWT.

Fluxo:

1. Usuário realiza login  
2. A API gera um token JWT  
3. O cliente envia o token no header Authorization  
4. As rotas protegidas validam o token  

Exemplo:

```http
Authorization: Bearer SEU_TOKEN
```

---

# Endpoints Principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/tarefas` | Cria uma tarefa |
| GET | `/tarefas` | Lista todas as tarefas |
| GET | `/tarefas/{id}` | Busca tarefa por ID |
| PUT | `/tarefas/{id}` | Atualiza uma tarefa |
| DELETE | `/tarefas/{id}` | Remove uma tarefa |

---

# Estrutura do Projeto

```text
Directory structure:
└── viniciuss4ntos-agendador-tarefas-api/
    ├── Dockerfile
    ├── gradlew
    ├── gradlew.bat
    ├── gradle/
    │   └── wrapper/
    │       └── gradle-wrapper.properties
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/
    │       │       └── vinicius/
    │       │           └── agendador_tarefas_api/
    │       │               ├── AgendadorTarefasApiApplication.java
    │       │               ├── business/
    │       │               │   ├── TarefasService.java
    │       │               │   ├── dto/
    │       │               │   │   ├── TarefasDTORecord.java
    │       │               │   │   └── UsuarioDTO.java
    │       │               │   └── mapper/
    │       │               │       ├── TarefasConverter.java
    │       │               │       └── TarefaUpdateConverter.java
    │       │               ├── controller/
    │       │               │   └── TarefasController.java
    │       │               └── infrastructure/
    │       │                   ├── client/
    │       │                   │   └── UsuarioClient.java
    │       │                   ├── entity/
    │       │                   │   └── TarefasEntity.java
    │       │                   ├── enums/
    │       │                   │   └── StatusNotificacaoEnum.java
    │       │                   ├── exceptions/
    │       │                   │   ├── GlobalExceptionHandler.java
    │       │                   │   ├── ResourceNotFoundException.java
    │       │                   │   └── UnathorizedException.java
    │       │                   ├── repository/
    │       │                   │   └── TarefasRepository.java
    │       │                   └── security/
    │       │                       ├── JwtRequestFilter.java
    │       │                       ├── JwtUtil.java
    │       │                       ├── SecurityConfig.java
    │       │                       └── UserDetailsServiceImpl.java
    │       └── resources/
    │           └── application.properties
    └── .github/
        └── workflows/
            └── gradle.yml

```

---

# Segurança

O projeto possui:

- Spring Security  
- JWT Authentication  
- Filtro JWT  
- Rotas protegidas  
- Controle de autenticação  
- Tratamento global de exceções  

---

# Docker

## Subir containers

```bash
docker-compose up -d
```

## Derrubar containers

```bash
docker-compose down
```

---

# CI/CD

O projeto possui pipeline automatizada utilizando GitHub Actions.

Funcionalidades:

- Build automático  
- Execução de testes  
- Integração contínua  

---

# Autor

Desenvolvido por Edson Vinicius.
