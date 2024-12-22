package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.controller.HomeController.FlashSale;
import com.jtspringproject.JtSpringProject.controller.HomeController.HomeResponse;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.CategoryService;
import com.jtspringproject.JtSpringProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public ResponseEntity<GenericResponse<Wishlist>> getProductsByCategories() {
        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.getProducts();

        Wishlist wishlist = new Wishlist(
            categories,
            products
        );
        GenericResponse<Wishlist> response = new GenericResponse<Wishlist>();
        response.setResult(wishlist);
        response.setStatus(true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<GenericResponse<Product>> getProductById(@PathVariable("id") int productId) {
        Product product = productService.getProduct(productId);

        if (product == null) {
            // Retornar un error 404 si el producto no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // O puedes enviar un mensaje en el body si lo deseas
        }

        GenericResponse<Product> response = new GenericResponse<Product>();
        response.setResult(product);
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }

    public static class Wishlist {
        private List<Category> categories;
        private List<Product> products;

        public Wishlist(List<Category> categories, List<Product> products) {
            this.categories = categories;
            this.products = products;
        }

        // Getters y Setters
        public List<Category> getCategories() {
            return categories;
        }
        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }
        public List<Product> getProducts() {
            return products;
        }
        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }
}
