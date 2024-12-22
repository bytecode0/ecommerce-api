package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.CategoryService;
import com.jtspringproject.JtSpringProject.services.ProductService;

import io.jsonwebtoken.lang.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
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
    public ResponseEntity<GenericResponse<ProductList>> getProductsByCategories() {
        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.getAllProducts();

        ProductList wishlist = new ProductList(
            categories,
            products
        );
        GenericResponse<ProductList> response = new GenericResponse<ProductList>();
        response.setResult(wishlist);
        response.setStatus(true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<Product>>> searchProducts(
            @RequestParam(value = "categories_id", required = false) Integer categoriesId,
            @RequestParam(value = "sort", required = false, defaultValue = "0") Integer sort,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {

        // Fetch the category if `categories_id` is provided
        List<Product> filteredProducts;
        if (categoriesId != null) {
            filteredProducts = productService.getProductsByCategory(categoriesId);
        } else {
            filteredProducts = productService.getAllProducts();
        }

        // Apply sorting
        switch (sort) {
            case 1:
                filteredProducts.sort(Comparator.comparing(Product::getPrice)); // Sort by price ascending
                break;
            case 2:
                filteredProducts.sort(Comparator.comparing(Product::getPrice).reversed()); // Sort by price descending
                break;
            case 3:
                filteredProducts.sort(Comparator.comparing(Product::getName)); // Sort by name ascending
                break;
            case 4:
                filteredProducts.sort(Comparator.comparing(Product::getName).reversed()); // Sort by name descending
                break;
            default:
                // Default sort logic, if any, can go here
                break;
        }

        // Pagination logic
        int pageSize = 10; // Define the number of products per page
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, filteredProducts.size());
        List<Product> paginatedProducts;

        if (startIndex < filteredProducts.size()) {
            paginatedProducts = filteredProducts.subList(startIndex, endIndex);
        } else {
            paginatedProducts = Collections.emptyList();
        }

        // Create response
        GenericResponse<List<Product>> response = new GenericResponse<>();
        response.setResult(paginatedProducts);
        response.setStatus(true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/wishlist")
    public ResponseEntity<GenericResponse<ProductList>> getWishlist() {
        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.getAllProducts();

        ProductList wishlist = new ProductList(
            categories,
            products
        );
        GenericResponse<ProductList> response = new GenericResponse<ProductList>();
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

    public static class ProductList {
        private List<Category> categories;
        private List<Product> products;

        public ProductList(List<Category> categories, List<Product> products) {
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
