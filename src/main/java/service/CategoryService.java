package service;

import dao.CategoryDao;
import model.Category;

import java.util.Comparator;
import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public List<Category> findAndSortCategories() {
        categoryDao.findAll().sort(Comparator.comparing(Category::getName));
        return categoryDao.findAll();
    }
}
