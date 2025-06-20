package br.com.joshua.cucumberbasic.service.impl;

import br.com.joshua.cucumberbasic.domain.Category;
import br.com.joshua.cucumberbasic.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final List<Category> categorias = new ArrayList<>();

    public void clear() {
        categorias.clear();
    }

    public void add(Category c) {
        categorias.add(c);
    }

    public List<Category> all() {
        return categorias;
    }
}
