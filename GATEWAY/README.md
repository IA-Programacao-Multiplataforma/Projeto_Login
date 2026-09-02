
# 🛒 E-Commerce API - Arquitetura de Microsserviços

Este projeto implementa uma arquitetura de microsserviços em Java com Spring Boot, utilizando o padrão API Gateway e segurança via tokens JWT. O sistema simula as operações de um e-commerce, incluindo gestão de usuários, clientes, produtos e o registro de vendas.

---

## ⚙️ Arquitetura

O ecossistema é composto pelos seguintes serviços:

* **API Gateway:** Ponto de entrada único (porta `8080`). Responsável por rotear as requisições e validar os tokens JWT.
* **Microsserviço de Login:** Responsável pela autenticação, autorização e geração de tokens.
* **Microsserviço de Cliente:** Gerencia o CRUD e os perfis dos clientes (com dados de endereço e nascimento).
* **Microsserviço de Produto (e Vendas):** Gerencia o catálogo de itens e o processamento dos carrinhos de compras/vendas.
* **Banco de Dados:** MongoDB (NoSQL) utilizado para persistência flexível dos documentos.

---

## 🚀 Guia de Testes

Para testar o fluxo completo da aplicação, inicie todos os microsserviços e o API Gateway. Todas as requisições abaixo devem ser enviadas para a porta do **Gateway (`8080`)**.

---

### 1. Registrar um Novo Usuário

Cria as credenciais de acesso ao sistema.

* **Método:** `POST`
* **URL:** `http://localhost:8080/usuarios/registro`

**Body (JSON):**

```json
{
  "username": "Ana",
  "password": "123",
  "email": "ana@teste.com",
  "cep": "08031020",
  "roles": [
    "USER"
  ]
}
```

### 2. Autenticação (Login)

Gera o Token JWT necessário para acessar as demais rotas.

* **Método:** `POST`
* **URL:** `http://localhost:8080/autenticacao/login`

**Body (JSON):**

```json
{
  "username": "Ana",
  "password": "123"
}
```

**Atenção:** Copie o token gerado na resposta para utilizar no cabeçalho
`Authorization: Bearer <token>` dos próximos passos.


### 3. Cadastrar Cliente (Autenticado)

Cria o perfil do cliente no banco de dados com seus dados pessoais.

* **Método:** `POST`
* **URL:** `http://localhost:8080/clientes`
* **Headers:** `Authorization: Bearer <seu_token>`

**Body (JSON):**

```json
{
  "nome": "Ana Pereira",
  "dataNascimento": "2000-01-01",
  "endereco": {
    "logradouro": "rua exemplo",
    "numero": "123",
    "cidade": "SP",
    "estado": "SP"
  }
}
```

**Atenção:** Copie o id gerado na resposta, ele será usado para associar as compras a este cliente!


### 4. Consultar Clientes (Autenticado)

Retorna a lista de todos os clientes cadastrados.

* **Método:** `GET`
* **URL:** `http://localhost:8080/clientes`
* **Headers:** `Authorization: Bearer <seu_token>`

### 5. Cadastrar Produto (Autenticado)

Adiciona um novo produto ao catálogo do e-commerce.

* **Método:** `POST`
* **URL:** `http://localhost:8080/produtos`
* **Headers:** `Authorization: Bearer <seu_token>`

**Body (JSON):**

```json
{
  "nome": "Notebook",
  "preco": 3500.00
}
```

### 6. Registrar Venda (Autenticado)

Processa um carrinho de compras, associando os itens ao clienteId. O total de itens é calculado automaticamente pelo sistema.

* **Método:** `POST`
* **URL:** `http://localhost:8080/produtos/vendas`
* **Headers:** `Authorization: Bearer <seu_token>`

**Body (JSON):** (Substitua o clienteId pelo ID gerado no passo 3)

```json
{
  "clienteId": "937a0288-2ba2-4794-b8ec-760c4988cf3f",
  "itens": [
    {
      "produtoNome": "Notebook",
      "quantidade": 1
    }
  ]
}
```
