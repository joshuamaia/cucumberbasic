package br.com.joshua.cucumberbasic.bdd;

import br.com.joshua.cucumberbasic.domain.Category;
import br.com.joshua.cucumberbasic.service.CategoryService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class CategorySteps {

    private final CategoryService manager;
    private final TestRestTemplate restTemplate;
    private ResponseEntity<Void> response;

    public CategorySteps(CategoryService manager, TestRestTemplate restTemplate) {
        this.manager = manager;
        this.restTemplate = restTemplate;
    }

    @Dado("que não existem categorias cadastradas")
    public void limparCategorias() {
        manager.clear();
    }

    @Quando("eu cadastro as seguintes categorias")
    public void cadastrarCategorias(DataTable table) {
        for (Map<String, String> row : table.asMaps()) {
            Category c = new Category(row.get("nome"), row.get("descrição"));
            manager.add(c);
        }
    }

    @Entao("a lista de categorias deve conter {int} categorias")
    public void verificarQuantidadeCategorias(int expectedCount) {
        Assertions.assertThat(manager.all())
                .hasSize(expectedCount);
    }

    @Quando("eu envio as seguintes categorias pela API")
    public void enviarCategoriasPelaApi(DataTable table) {
        for (Map<String, String> row : table.asMaps()) {
            Category category = new Category(row.get("nome"), row.get("descrição"));
            restTemplate.postForEntity("/categories", category, Void.class);
        }
    }

    @Entao("a API deve retornar {int} categorias")
    public void verificarQuantidadeCategoriasNaApi(int total) {
        ResponseEntity<Category[]> response = restTemplate.getForEntity("/categories", Category[].class);
        Assertions.assertThat(response.getBody()).hasSize(total);
    }

    @Quando("eu tento cadastrar uma categoria inválida")
    public void cadastrarCategoriaInvalida() {
        Category category = new Category("", "sem nome");
        response = restTemplate.postForEntity("/categories", category, Void.class);
    }

    @Entao("o sistema deve rejeitar com status {int}")
    public void verificarStatusErro(int status) {
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(status);
    }
}
