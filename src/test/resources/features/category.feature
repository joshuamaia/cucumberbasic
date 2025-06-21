# language: pt
Funcionalidade: Cadastro de categorias

  Cenário: Cadastrando três categorias de uma vez
    Dado que não existem categorias cadastradas
    Quando eu cadastro as seguintes categorias
      | nome        | descrição             |
      | Eletrônicos | Tudo sobre tecnologia |
      | Roupas      | Vestuário em geral    |
      | Livros      | Diversos tipos de livro |
    Então a lista de categorias deve conter 3 categorias

  Cenário: Testar API REST
    Dado que não existem categorias cadastradas
    Quando eu envio as seguintes categorias pela API
      | nome   | descrição           |
      | Teste1 | Categoria de teste  |
      | Teste2 | Outra categoria     |
    Então a API deve retornar 2 categorias

  Cenário: Cadastro inválido de categoria
    Quando eu tento cadastrar uma categoria inválida
    Então o sistema deve rejeitar com status 400
