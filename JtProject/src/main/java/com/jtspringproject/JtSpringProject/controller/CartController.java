package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.CartProduct;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.CartService;
import com.jtspringproject.JtSpringProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/cart/products")
public class CartController {

    private CartService cartService;

    private ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getCartProducts() {
        List<CartProduct> carts = cartService.getCartProducts();
        return ResponseEntity.ok(carts);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddToCartRequest addToCartRequest) {
        Product product = productService.getProduct(addToCartRequest.getProductId());
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The product Id: " + addToCartRequest.getProductId() + " looks like incorrect or it does not exits.");
        }

        if (addToCartRequest.getCount() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The count to add should be greater than zero.");
        }

        CartProduct newCartProduct = new CartProduct();
        newCartProduct.setCount(addToCartRequest.getCount());
        newCartProduct.setProduct(product);

        cartService.addProductToCart(newCartProduct);
        return ResponseEntity.ok("Product has been added to the cart.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody DeleteCartProductRequest deleteCartProductRequest) {
        if (deleteCartProductRequest == null || deleteCartProductRequest.getProductId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid product ID provided.");
        }

        Optional<CartProduct> cartProductOptional = cartService.getCartProductById(deleteCartProductRequest.getProductId());

        if (cartProductOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found in the cart.");
        }

        cartService.deleteCart(cartProductOptional.get());
        return ResponseEntity.ok("Product removed from the cart.");
    }

}
