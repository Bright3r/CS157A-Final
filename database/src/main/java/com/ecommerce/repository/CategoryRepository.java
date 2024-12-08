package com.ecommerce.repository;

import com.ecommerce.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Category getCategoryById(Integer categoryId) {
        String sql = "SELECT * FROM Categories WHERE categoryId = ?";
        return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum) -> {
            Category category = new Category();
            category.setCategoryId(rs.getInt("categoryId"));
            category.setCategoryName(rs.getString("categoryName"));
            return category;
        }, categoryId);
    }

    public void saveCategory(Category category) {
        String sql = "INSERT INTO Categories (categoryId, categoryName) VALUES (?, ?)";
        jdbcTemplate.update(sql, category.getCategoryId(), category.getCategoryName());
    }

    public void updateCategory(Category category) {
        String sql = "UPDATE Categories SET categoryName = ? WHERE categoryId = ?";
        jdbcTemplate.update(sql, category.getCategoryName(), category.getCategoryId());
    }

    public void deleteCategory(Integer categoryId) {
        String sql = "DELETE FROM Categories WHERE categoryId = ?";
        jdbcTemplate.update(sql, categoryId);
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Categories";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Category category = new Category();
            category.setCategoryId(rs.getInt("categoryId"));
            category.setCategoryName(rs.getString("categoryName"));
            return category;
        });
    }
}
