# GerenciadorclienteAPI

GerenciadorclienteAPI é uma aplicação construída com Java e Spring Boot 3 que permite o cadastro de clientes e integração com a API do ViaCEP para preenchimento automático de endereços, a API também conta com permissionamento, onde, o uduário admin tem liberdade a qualquer recurso da aplicação e o user tem permissão somente para visualização dos dados, mais especificamente aos métodos GET.

## Funcionalidades

- Cadastro de usuários
- Cadastro de clientes
- Consulta de endereço através da API do ViaCEP

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

## Configuração e Execução

1. Clone o repositório:

    ```sh
    git clone https://github.com/seu-usuario/gerenciadorclientes.git
    ```

2. Navegue até o diretório do projeto:

    ```sh
    cd gerenciadorclientes
    ```

3. Compile e instale as dependências:

4. Execute a aplicação:

5. A aplicação estará disponível em `http://localhost:8080`.

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

### Consultar Cliente por ID

- **GET** `/clientes/{clienteId}`

### Atualizar Cliente

- **PUT** `/clientes/{clienteId}`

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

### Deletar Cliente

- **DELETE** `/clientes/{clienteId}`

## Exemplo de Uso

Para cadastrar um cliente, envie uma requisição POST para `http://localhost:8080/clientes` com o corpo da requisição conforme o exemplo acima. A API do ViaCEP será chamada automaticamente para preencher os dados de endereço do cliente.

## Contribuição

1. Fork este repositório
2. Crie uma branch com sua feature: `git checkout -b minha-feature`
3. Commit suas mudanças: `git commit -m 'Adiciona minha feature'`
4. Envie para o branch original: `git push origin minha-feature`
5. Crie um pull request
