# GerenciadorclienteAPI

GerenciadorclienteAPI é uma aplicação construída com Java e Spring Boot 3 que permite o cadastro de clientes e integração com a API do *ViaCEP* para preenchimento automático de endereços, a API também conta com permissionamento, onde, o usuário admin tem liberdade a qualquer recurso da aplicação e o user tem permissão somente para visualização dos dados, mais especificamente aos métodos GET.

## Funcionalidades

- Cadastro de usuários
    - Permite a criação de novos usuários no sistema, armazenando informações como username, password e se é um adm ou não.
- Cadastro de clientes
    - Permite a criação de novos clientes no sistema, armazenando informações como cpf, nome, e-mails, telefones e endereço.
- Consulta de endereço através da API do ViaCEP
    - Realiza a busca de endereços através da integração com a API do ViaCEP, permitindo a obtenção automática de dados de endereço com base no CEP fornecido.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security
- H2 Database (para desenvolvimento)
- Maven
- Lombok
- Model Mapper
- API ViaCEP

## Requisitos

- Java 17
- Maven

## Endpoints

### Cadastro de Cliente

- **POST** `/clientes`

    ```json
    {
        "nome": "Mariana Souza",
        "cpf": "98765432100",
        "emails": [
            {"email": "mariana.souza@email.com"},
            {"email": "mariana.souza@gmail.com"}
        ],
        "telefones": [
            {
                "numero": "11987654321",
                "tipo": "residencial"
            },
            {
                "numero": "11987654322",
                "tipo": "celular"
            }
        ],
        "endereco": {
            "cep": "01001000"
        }
    }
    ```

### Listar Clientes

- **GET** `/clientes`

### Response

- *200 OK*

### Consultar Cliente por ID

- **GET** `/clientes/{clienteId}`

### Path Parameter

- *clienteId*: ID do cliente.

### Response

- *200 OK*
- *404 Not Found*: Cliente não encontrado.

### Atualizar Cliente

- **PUT** `/clientes/{clienteId}`

### Path Parameter

- *clienteId*: ID do cliente a ser deletado.

### Response

- *200 OK*
- *404 Not Found*: Cliente não encontrado.

    ```json
    {
        "nome": "Mariana Souza",
        "cpf": "98765432100",
        "emails": [
            {"email": "mariana.souza@email.com"},
            {"email": "mariana.souza@gmail.com"}
        ],
        "telefones": [
            {
                "id": 1,
                "numero": "11987654321",
                "tipo": "residencial"
            },
            {
                "id": 2,
                "numero": "11987654322",
                "tipo": "celular"
            }
        ],
        "endereco": {
            "cep": "01001000",
            "logradouro": "Praça da Sé",
            "complemento": "Apto 101",
            "bairro": "Sé",
            "cidade": "São Paulo",
            "uf": "SP"
        }
    }
    ```

## Deletar Cliente
**DELETE** /clientes/{clienteId}*

### Path Parameter

- *clienteId*: ID do cliente a ser deletado.

### Response

- *204 NO CONTENT*
- *404 Not Found*: Cliente não encontrado.

## Exemplo de Uso

Para cadastrar um cliente, envie uma requisição POST para `http://localhost:8080/clientes` com o corpo da requisição conforme o exemplo acima. A API do ViaCEP será chamada automaticamente para preencher os dados de endereço do cliente.

## Contribuição

1. Fork este repositório
2. Crie uma branch com sua feature: `git checkout -b minha-feature`
3. Commit suas mudanças: `git commit -m 'Adiciona minha feature'`
4. Envie para o branch original: `git push origin minha-feature`
5. Crie um pull request
