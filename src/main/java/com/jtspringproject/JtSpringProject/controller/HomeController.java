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
    public GenericResponse<HomeResponse> getProductsByCategories() {
        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.getProducts();

        GenericResponse<HomeResponse> response = new GenericResponse<HomeResponse>();
        response.setResult(new HomeResponse(
            categories,
            new FlashSale("", products),
            products,
            products
        ));
        response.setStatus(true);
        response.setAlert(new JAlertResponse("Welcome to VespaShop",  "The solution for your problems"));

        return response;
    }

    public static class HomeResponse {
        private List<Category> categories;
        private FlashSale flash_sale;
        private List<Product> most_sale;
        private List<Product> newest_product;
        
        public HomeResponse(List<Category> categories, FlashSale flash_sale, List<Product> most_sale, List<Product> newest_product) {
            this.categories = categories;
            this.flash_sale = flash_sale;
            this.most_sale = most_sale;
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
        public List<Product> getMost_sale() {
            return most_sale;
        }
        public void setMost_sale(List<Product> most_sale) {
            this.most_sale = most_sale;
        }
        public FlashSale getFlash_sale() {
            return flash_sale;
        }

        public void setFlash_sale(FlashSale flash_sale) {
            this.flash_sale = flash_sale;
        }
    }

    public static class FlashSale {
        String expiredAt = "";
        private List<Product> products;

        public FlashSale(String expiredAt, List<Product> products) {
            this.expiredAt = expiredAt;
            this.products = products;
        }

        public String getExpiredAt() {
            return expiredAt;
        }

        public void setExpiredAt(String expiredAt) {
            this.expiredAt = expiredAt;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
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

