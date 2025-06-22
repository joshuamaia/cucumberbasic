# language: pt
Funcionalidade: Cadastro de categorias

  Cenário: Cadastrando três categorias de uma vez
    Dado que não existem categorias cadastradas
    Quando eu cadastro as seguintes categorias
      | name        | description             | price |
      | Eletrônicos | Tudo sobre tecnologia | 12.7 |
      | Roupas      | Vestuário em geral    | 435.8 |
      | Livros      | Diversos tipos de livro | 54.1 |
    Então a lista de categorias deve conter 3 categorias

  Cenário: Testar API REST
    Dado que não existem categorias cadastradas
    Quando eu envio as seguintes categorias pela API
      | name        | description    | price |
      | Teste1 | Categoria de teste  |  12.7 |
      | Teste2 | Outra categoria     |  54.1 |
    Então a API deve retornar 2 categorias

  Cenário: Cadastro inválido de categoria
    Quando eu tento cadastrar uma categoria inválida
    Então o sistema deve rejeitar com status 400

  Cenário: Falha ao tentar cadastrar categorias com preço negativo
    Dado que deve limpar categorias cadastradas
    Quando quando eu tento cadastrar categoria com preço -12.9
    Então  a requisição deve falhar com erro 400