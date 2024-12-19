package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.dao.CartProductDao;
import com.jtspringproject.JtSpringProject.models.CartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartProductDao cartProductDao;

    public void addProductToCart(CartProduct cartProduct) {
        cartProductDao.addCartProduct(cartProduct);
    }

    public List<CartProduct> getCartProducts() {
        return this.cartProductDao.getCartProducts();
    }

    public Optional<CartProduct> getCartProductById(int productId) {
        return cartProductDao.getCartProducts()
                .stream()
                .filter( cartProduct -> cartProduct.getProduct().getId() == productId).findFirst();
    }

    public void updateCart(CartProduct cartProduct) {
        cartProductDao.updateCartProduct(cartProduct);
    }

    public void deleteCart(CartProduct cartProduct) {
        cartProductDao.deleteCartProduct(cartProduct);
    }


}
