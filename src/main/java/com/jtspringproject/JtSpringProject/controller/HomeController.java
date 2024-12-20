package com.jtspringproject.JtSpringProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.CategoryService;
import com.jtspringproject.JtSpringProject.services.ProductService;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public HomeResponse getProductsByCategories() {
        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.getProducts();

        return new HomeResponse(
            categories,
            products
        );
    }

    public static class HomeResponse {
        private List<Category> categories;
        private List<Product> newest_product;
        
        public HomeResponse(List<Category> categories, List<Product> newest_product) {
            this.categories = categories;
            this.newest_product = newest_product;
        }

        // Getters y Setters
        public List<Category> getCategories() {
            return categories;
        }
        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }
        public List<Product> getNewest_product() {
            return newest_product;
        }
        public void setNewest_product(List<Product> newest_product) {
            this.newest_product = newest_product;
        }
    }

    @GetMapping
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Welcome to the API</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            text-align: center;
                            background-color: #f9f9f9;
                            margin: 0;
                            padding: 0;
                        }
                        header {
                            padding: 50px;
                            background-color: #4CAF50;
                            color: white;
                        }
                        img {
                            max-width: 100%;
                            height: auto;
                            margin: 20px 0;
                        }
                        .description {
                            font-size: 18px;
                            margin: 20px;
                            color: #333;
                        }
                    </style>
                </head>
                <body>
                    <header>
                        <h1>Welcome to the API</h1>
                    </header>
                    <div class="description">
                        <p>Explore the power of our API to transform your applications.
                        This is a simple demo to show how a REST API can serve a web page.</p>
                    </div>
                    <img src="https://i.imgur.com/ctpOniL.png" alt="Welcome Image">
                </body>
                </html>
                """;
    }
}

