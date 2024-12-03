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


Acesse a aplicação:


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


Abra um Pull Request.
📄 Licença
Este projeto está licenciado sob a MIT License.

Desenvolvido com ❤️ por Kleberson dos santos silva.

### Adaptação
- Inclua qualquer configuração adicional necessária, como variáveis de ambiente para integração com APIs externas.

Se precisar de ajuda para implementar alguma funcionalidade específica, é só pedir! 😊



