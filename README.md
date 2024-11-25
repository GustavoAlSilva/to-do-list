#to-do-list-api

Bem-vindo ao repositório to-do-list-api, uma API desenvolvida para a disciplina de Programação para Dispositivos Móveis, da Universidade do Estado de Santa Catarina. A API será consumida por um aplicação mobile de gerenciamento de tarefas.

Para executar a API, basta clonar o repositório e executar o método main do arquivo src/main/java/com/gustavo/todolist/ToDoListApplication.java. A aplicação executará na porta 8080.

O Hibernate está sincronizando as entidades e com as tabelas do banco de dados, então é necessário apenas haver um banco de dados postgres com usuário postgres e senha postgres. Caso deseje utilizar outro nome de banco de dados e/ou outras credenciais, o arquivo target/classes/application.properties pode ser modificado.

Há a pasta `postman`, e ela contém as collections com as possíveis requisições à API.

O primeiro usuário da aplicação deve ser criado através de uma requisição via Postman ou via SQL no banco de dados. Exemplo de SQL:
INSERT INTO user (username, password, email)
VALUES ('Gustavo', '123', 'gustavo@gmail.com');
