# Sistema de Biblioteca - Microsserviços

Projeto desenvolvido utilizando arquitetura de microsserviços com Spring Boot.

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Docker
- Docker Compose
- Nginx

## Estrutura do projeto

```
projeto-biblioteca/
│
├── autor-ms/
├── livro-ms/
├── front-ms/
├── nginx/
└── docker-compose.yml
```

## Como executar

Clone o repositório:

```bash
git clone https://github.com/kaique28122004/projeto-biblioteca.git
cd projeto-biblioteca
```

Suba os containers:

```bash
docker compose up -d --build
```

## Acessando a aplicação

Frontend:

```
http://localhost:8080
```

APIs:

Autores

```
http://localhost/api/autores
```

Livros

```
http://localhost/api/livros
```

## Funcionalidades

- Cadastro de autores
- Cadastro de livros
- Listagem de autores
- Listagem de livros
- Atualização de registros
- Exclusão de registros
- Comunicação entre microsserviços
