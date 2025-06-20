package br.com.joshua.cucumberbasic.service;

import br.com.joshua.cucumberbasic.domain.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {

    public void clear();

    public void add(Category c);

    public List<Category> all();
}
