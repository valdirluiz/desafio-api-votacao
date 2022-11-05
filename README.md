# Api de votação

Este projeto consiste em uma API de votação, tendo como principais funcinalidades:

- Cadastro e consulta de pautas
- Criação e consulta de sessões
- Votação em pautas
- Apuração por pautas

## Arquitetura

A arquitetura escolhida para desenvolver o sistema foi a hexagonal, com algumas adaptações para evitar o over engineering. As camadas da aplicação são:

- configs: Responsável pelas configurações do projeto.
- datasources: Responsável por acessar dados, seja eles de bancos ou outros sistemas.
- entities: Entidades do domínio, com uma adaptação buscando evitar over engineerin as entidades JPA também ficam nessa camada.
- exceptions: Mapeamento dos erros da aplicação.
- interactors: Camada de usecases, aqui fica toda a orquestração da api.
- repositorios: Interfaces que são utilizadas pelo usecsae e implementadas pelo datasource, dessa forma a camada de interactos nao precisa conhecer como é feita a implementação do acesso a dados.
- transportlayers: Camada com api rest, ou possiveis listeners de filas por exemplo.


## Documentação de API

Para documentar a API foi utilizada a ferramenta swagger, ela pode ser acessada da seguinte forma:

http://localhost:5000/api-votacao/swagger-ui/

