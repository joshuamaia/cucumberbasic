# language: pt

Funcionalidade: Cadastro de categorias

  Cenário: Cadastrar três categorias de uma só vez
  Dado que não existam categorias cadastradas
  Quando eu cadastrar as seguintes categorias
  | name        | description          |
  | Eletrônicos | Tudo de tecnologia   |
  | Roupas      | Vestuário geral      |
  | Livros      | Literatura diversa   |
  Então a lista de categorias deve conter 3 categorias

  Cenário: Testar API REST
    Dado que não existam categorias cadastradas
    Quando eu envio as categorias via API
      | name   | description         |
      | Teste1 | Categoria de teste  |
      | Teste2 | Outra categoria     |
    Então a API deve retornar 2 categorias


  Cenário: Cadastro inválido de categoria
    Quando eu tentar cadastrar uma categoria inválida
    Então o sistema deve rejeitar com erro 400
