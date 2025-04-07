# E-commerce em Java

Bem-vindo ao projeto de E-commerce em Java! Este é um sistema completo de comércio eletrônico desenvolvido para gerenciamento de produtos, usuários, pedidos e pagamento online. 

## 🚀 Funcionalidades

- **Gestão de usuários**: Cadastro, login e gerenciamento de perfis (administradores e clientes).
- **Gestão de produtos**: Adição, edição, exclusão e visualização de produtos por administradores.
- **Carrinho de compras**: Adicionar, remover e atualizar itens no carrinho.
- **Sistema de pedidos**: Checkout de pedidos, histórico de compras e acompanhamento.
- **Painel administrativo**: Relatórios de vendas, gerenciamento de categorias e usuários.
- **Filtros de busca e categorias**: Pesquisa avançada de produtos.

## 🛠️ Tecnologias Utilizadas

- **Backend**:
  - Java 17
  - Spring Boot (MVC, Data JPA)
  - Hibernate (ORM)
  - MySQL (Banco de dados relacional)
  - Maven (Gerenciador de dependências)
  
- **Frontend**:
  - HTML, CSS, JavaScript

- **Outros**:
  - API RESTful

## 📦 Configuração do Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:
- **JDK 17** ou superior
- **Maven**
- **MySQL**
- **Docker** (opcional para ambiente de produção)

### Instalação

1. Clone o repositório

2. Configure o Banco de dados


Crie um banco de dados no MySQL:
 CREATE DATABASE (Nome do seu Banco);

Atualize o arquivo application.properties com as credenciais do banco:
 spring.datasource.url=jdbc:mysql://localhost:3306/(Nome do seu Banco)
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

Execute a aplicação:

 mvn spring-boot:run

# API Endpoints Documentation

Este documento descreve os principais endpoints da API, organizados por funcionalidades e tipos de usuário (Cliente e Administrador).

--- # API Endpoints Documentação

## Cliente

| Funcionalidade               | Método  | Endpoint                                   | Body                                                                                                   |
|------------------------------|---------|-------------------------------------------|--------------------------------------------------------------------------------------------------------|
| Consulta pesquisa            | `GET`   | `/livro/consulta/pesquisa`                | `{ "termoDePesquisa": "vid" }`                                                                         |
| Consulta com parâmetro       | `GET`   | `/livro/consulta/parametros`              | `{ "edicao": 4 }`                                                                                     |
| Excluir item do carrinho     | `DELETE`| `/pedidos/itens/produto/5`                | -                                                                                                      |
| Atualizar itens no carrinho  | `PUT`   | `/pedidos/itens/produto/5`                | `{ "quantidade": 6 }`                                                                                 |
| Criar pedido                 | `POST`  | `/pedidos/1/1`                            | `{ "id": 1, "pedidoRealizado": "2024-10-15", "codigoPedido": "b1089fab-ff04-4efd-8092-ab0732d452ca", "statusPedido": "AGUARDANDO_PAGAMENTO" }` |
| Listar pedidos por cliente   | `GET`   | `/pedidos/1`                              | -                                                                                                      |
| Listar pedidos pagos         | `GET`   | `/pedidos/pagos/1`                        | -                                                                                                      |
| Listar livros ativos         | `GET`   | `/livro`                                  | -                                                                                                      |
| Ativar livro                 | `POST`  | `/livro/ativar`                           | `{ "idLivro": "1", "justificativa": "Esta versão está fora do padrão da livraria", "categoria": "PETICAO_PUBLICA" }` |

---

## Administrador

| Funcionalidade                 | Método  | Endpoint                                   | Body                                                                                                   |
|--------------------------------|---------|-------------------------------------------|--------------------------------------------------------------------------------------------------------|
| Listar todos os pedidos pagos  | `GET`   | `/administrador/listar-pedidos`           | -                                                                                                      |
| Aceitar devolução              | `POST`  | `/administrador/aceitar`                  | `{ "codigoPedido": "69319b5c-3406-42fd-b8a9-f96b7b002e48", "esperandoDevolucaoOuRecebido": "RECEBIDO", "produtoVoltaParaEstoque": "SIM", "estadoProduto": "NOVO" }` |
| Novo estoque                   | `POST`  | `/estoque`                                | `{ "idLivro": 16, "quantidade": 100, "valorCusto": "35.44", "dataEntrada": "1990-01-01", "fornecedor": "Casa da letra", "estadoProduto": "NOVO" }` |
| Criar cupom                    | `POST`  | `/administrador/cupons`                   | `{ "idCliente": 1, "tipoCupom": "TROCA", "valor": 200 }`                                               |
| Cadastrar livro                | `POST`  | `/livro`                                  | `{ "idPrecificacao": 1, "data": "2024-01-30", "preco": "55.70", "titulo": "Psicologia Financeira", "isbn": "978-3-01-144444-1", "autor": [ { "nome": "Morgan Housel" } ], "categoria": [ { "nome": "autoajuda" } ] }` |
| Atualizar livro                | `PUT`   | `/livro`                                  | `{ "id": 6, "autor": { "nome": "Morgan Housel" } }`                                                     |

---

Para mais informações sobre os endpoints, revise as tabelas acima ou entre em contato com o responsável pela API.

Frontend: http://localhost:8080
Swagger API Docs (se configurado): http://localhost:8080/swagger-ui.html
🌐 Contribuição
Contribuições são bem-vindas! Siga os passos abaixo para contribuir:
Faça um fork do repositório.
Crie uma nova branch:
 git checkout -b minha-nova-feature

Commit suas alterações:
 git commit -m "Minha nova feature"

Envie para o repositório remoto:
 git push origin minha-nova-feature

Desenvolvido com ❤️ por Kleberson dos santos silva.

### Adaptação
- Inclua qualquer configuração adicional necessária, como variáveis de ambiente para integração com APIs externas.
