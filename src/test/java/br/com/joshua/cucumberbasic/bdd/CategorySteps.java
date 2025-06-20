package br.com.joshua.cucumberbasic.bdd;

import br.com.joshua.cucumberbasic.domain.Category;
import br.com.joshua.cucumberbasic.service.CategoryService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class CategorySteps {

    private final CategoryService manager;

    private final TestRestTemplate restTemplate;

    private ResponseEntity<Void> resposta;

    public CategorySteps(CategoryService manager, TestRestTemplate restTemplate) {
        this.manager = manager;
        this.restTemplate = restTemplate;
    }

    @Dado("que não existam categorias cadastradas")
    public void resetarCategorias() {
        manager.clear();
    }

    @Quando("eu cadastrar as seguintes categorias")
    public void cadastrarCategorias(DataTable table) {
        for (Map<String, String> linha : table.asMaps()) {
            Category c = new Category(linha.get("name"), linha.get("description"));
            manager.add(c);
        }
    }

    @Então("a lista de categorias deve conter {int} categorias")
    public void verificarQuantidade(int qtdEsperada) {
        Assertions.assertThat(manager.all())
                .hasSize(qtdEsperada);
    }

    @Quando("eu envio as categorias via API")
    public void enviarCategoriasViaAPI(DataTable table) {
        for (Map<String, String> linha : table.asMaps()) {
            String name = linha.get("name");
            String description = linha.get("description");
            Category categoria = new Category(name, description);
            restTemplate.postForEntity("/categories", categoria, Void.class);
        }
    }

    @Então("a API deve retornar {int} categorias")
    public void verificarCategoriasNaApi(int total) {
        ResponseEntity<Category[]> resposta = restTemplate.getForEntity("/categories", Category[].class);
        Assertions.assertThat(resposta.getBody()).hasSize(total);
    }

    @Quando("eu tentar cadastrar uma categoria inválida")
    public void cadastrarCategoriaInvalida() {
        Category categoria = new Category("", "sem nome");
        resposta = restTemplate.postForEntity("/categories", categoria, Void.class);
    }

    @Então("o sistema deve rejeitar com erro {int}")
    public void erroEsperado(int status) {
        Assertions.assertThat(resposta.getStatusCodeValue()).isEqualTo(status);
    }
}