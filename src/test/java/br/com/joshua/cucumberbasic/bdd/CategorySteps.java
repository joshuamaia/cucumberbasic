package br.com.joshua.cucumberbasic.bdd;

import br.com.joshua.cucumberbasic.domain.Category;
import br.com.joshua.cucumberbasic.service.CategoryService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.assertj.core.api.Assertions;

import java.util.Map;

public class CategorySteps {

    private final CategoryService manager;

    public CategorySteps(CategoryService manager) {
        this.manager = manager;
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
}