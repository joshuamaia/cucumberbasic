package br.com.joshua.cucumberbasic.bdd;

import br.com.joshua.cucumberbasic.domain.Category;
import br.com.joshua.cucumberbasic.service.CategoryService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Given("there are no categories registered")
    public void clearCategories() {
        manager.clear();
    }

    @When("I register the following categories")
    public void registerCategories(DataTable table) {
        for (Map<String, String> row : table.asMaps()) {
            Category c = new Category(row.get("name"), row.get("description"));
            manager.add(c);
        }
    }

    @Then("the category list should contain {int} categories")
    public void checkCategoryCount(int expectedCount) {
        Assertions.assertThat(manager.all())
                .hasSize(expectedCount);
    }

    @When("I send the following categories via API")
    public void sendCategoriesViaApi(DataTable table) {
        for (Map<String, String> row : table.asMaps()) {
            Category category = new Category(row.get("name"), row.get("description"));
            restTemplate.postForEntity("/categories", category, Void.class);
        }
    }

    @Then("the API should return {int} categories")
    public void checkApiCategoryCount(int total) {
        ResponseEntity<Category[]> response = restTemplate.getForEntity("/categories", Category[].class);
        Assertions.assertThat(response.getBody()).hasSize(total);
    }

    @When("I try to register an invalid category")
    public void registerInvalidCategory() {
        Category category = new Category("", "no name");
        response = restTemplate.postForEntity("/categories", category, Void.class);
    }

    @Then("the system should reject with status {int}")
    public void checkErrorStatus(int status) {
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(status);
    }
}
