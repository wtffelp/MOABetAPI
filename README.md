# MOABetAPI

API REST de uma plataforma de apostas desenvolvida para um servidor de amigos no Discord. Projeto criado com o objetivo de aprender e explorar o framework Javalin na prática.

---

## Stack

- **Java 17**
- **Javalin** — framework web leve e minimalista
- **JDBI3** — acesso ao banco de dados
- **PostgreSQL** — banco de dados relacional
- **BCrypt** — hash de senhas
- **Gson** — serialização/deserialização JSON

---

## Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
Controller → Service → Repository → Database
```

- **Controller** — recebe as requisições HTTP e retorna as respostas
- **Service** — contém as regras de negócio
- **Repository** — responsável pelas queries no banco de dados
- **Model** — representação das entidades

---

## Rotas

### Usuários
```
GET    /usuarios          lista todos os usuários
GET    /usuarios/{id}     busca por ID
GET    /usuarios?email=   busca por email
GET    /usuarios?nome_completo=  busca por nome
POST   /usuarios          cria novo usuário
PUT    /usuarios/{id}     atualiza usuário
DELETE /usuario/{id}      deleta usuário
```

### Apostas
```
GET    /apostas           lista todas as apostas
GET    /apostas/{id}      busca por ID
GET    /apostas?titulo=   busca por título
GET    /apostas?descricao= busca por descrição
POST   /apostas           cria nova aposta
PUT    /apostas/{id}      finaliza aposta (define status e opção vencedora)
DELETE /apostas/{id}      deleta aposta
```

### Opções de Aposta
```
GET    /opcoes            lista todas as opções
GET    /opcoes/{id}       busca por ID
GET    /opcoes?texto=     busca por texto
GET    /opcoes?odd=       busca por odd
POST   /opcoes            cria nova opção com odd
PUT    /opcoes/{id}       atualiza odd de uma opção
DELETE /opcoes/{id}       deleta opção
```

### Palpites
```
GET    /palpites              lista todos os palpites
GET    /palpites/{id}         busca por ID
GET    /palpites?usuario_id=  palpites de um usuário
GET    /palpites?valor=       palpites por valor apostado
POST   /palpites              registra novo palpite
DELETE /palpites/{id}         deleta palpite
```

---

## Segurança

- Senhas armazenadas com hash BCrypt (custo 12)
- Anotação customizada `@Exclude` para ocultar campos sensíveis (como senha) na serialização JSON
- Validação de username duplicado no cadastro

---

## Como rodar localmente

**Pré-requisitos:** Java 17+, Maven, PostgreSQL

1. Clone o repositório
```bash
git clone https://github.com/wtffelp/MOABetAPI.git
```

2. Configure as variáveis de conexão com o banco no arquivo de configuração do projeto

3. Crie o banco de dados no PostgreSQL e rode as migrations

4. Execute o projeto
```bash
mvn compile exec:java
```

A API estará disponível em `http://localhost:7000`

---

## Observações

Este projeto não possui sistema financeiro real — não há depósitos, saques ou movimentação de dinheiro. Foi desenvolvido como projeto de aprendizado do Javalin e para uso informal entre amigos.
