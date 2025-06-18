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
