package br.com.joshua.cucumberbasic.controller;

import br.com.joshua.cucumberbasic.domain.Category;
import br.com.joshua.cucumberbasic.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Valid Category category) {
        Category categoryResponse = service.add(category);
        return ResponseEntity.created(URI.create("/categories")).body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(service.all());
    }

}
