## Objetivo

Atender a um desafio de codificar uma API simples baseada na API pública do [StarWars](https://swapi.co/).

## Instruções gerais

Ready? Go! Aqui teremos um teste para você mostrar um pouco do que sabe!
Dando continuidade ao nosso processo, temos um desafio para te propor! \o/

Nossos associados são aficionados por Star Wars e com isso, queremos criar um jogo com algumas informações da franquia.

Para possibilitar a equipe de front criar essa aplicação, queremos desenvolver uma API que contenha os dados dos planetas, que podem ser obtidas pela API pública do Star Wars (https://swapi.co/)

### Requisitos:

* A API deve ser REST
* Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, que foram inseridos manualmente pela funcionalidade de adicionar planetas:
  * Nome
  * Clima
  * Terreno
  * Para cada planeta também devemos ter a quantidade de aparições em filmes que deve ser obtida pela api do Star Wars na inserção do planeta.

### Funcionalidades desejadas:

* Adicionar um planeta (com nome, clima e terreno)
* Listar planetas do banco de dados
* Listar planetas da API do Star Wars
* Buscar por nome no banco de dados
* Buscar por ID no banco de dados
* Remover planeta

**Linguagens que usamos:** Java, Kotlin
**Bancos que usamos:** DynamoDB, Cassandra, MySQL, Oracle
**Diferencial:** Utilizar framework reativo (Ex.: Spring WebFlux, VertX)

Em média, as pessoas demoram 2 dias para resolver e enviar.
Antes de começar, estabeleça o seu prazo de entrega e informe a gente, blz?
Fique à vontade pra tirar qualquer dúvida que possa surgir!

**E lembre-se! Um bom software é um software bem testado**

## Executando o projeto

**1. Banco de dados MySQL**
O projeto está considerando que exista um banco de dados MySQL rodando localmente. Para isso, basta subir um container docker via `docker-compose` provido na pasta `docker` na raiz do projeto.
```
cd docker
docker-compose up -d
```

**2. Testes de Integração**
Existem testes de integração utilizando `cucumber`. Para executá-los, basta executar o seguinte comando `maven`:
```
mvn package -P integration-tests
```

**3. Executando a aplicação**
A aplicação foi escrita utilizando o framework `spring-boot`. Para executá-la, executar o seguinte comando `maven`:
```
mvn spring-boot:run -f pom.xml
```
Dessa forma a aplicação poderá ser acessada na porta 8080:
* [Documentação API: http://localhost:8080/swagger-ui.htm](http://localhost:8080/swagger-ui.htm)