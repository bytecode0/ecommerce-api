package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.CategoryService;
import com.jtspringproject.JtSpringProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public ProductRestController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryNode> getProductsByCategories() {
        // Obtener todas las categorías
        List<Category> categories = categoryService.getCategories();

        // Obtener todos los productos
        List<Product> products = productService.getProducts();

        // Agrupar productos por categoría
        Map<Integer, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(product -> product.getCategory().getId()));

        // Crear lista de nodos con categorías y sus productos
        return categories.stream()
                .map(category -> new CategoryNode(
                        category.getId(),
                        category.getName(),
                        productsByCategory.getOrDefault(category.getId(), List.of())
                ))
                .collect(Collectors.toList());
    }

    public static class CategoryNode {
        private int id;
        private String name;
        private List<Product> products;

        public CategoryNode(int id, String name, List<Product> products) {
            this.id = id;
            this.name = name;
            this.products = products;
        }

        // Getters y Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }
}
