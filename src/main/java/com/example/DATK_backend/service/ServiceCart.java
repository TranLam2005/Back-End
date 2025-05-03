package com.example.DATK_backend.service;

import com.example.DATK_backend.entity.Cart;
import com.example.DATK_backend.repository.RepositoryCart;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Transactional
public class ServiceCart {
    private final RepositoryCart repositoryCart;
    public ServiceCart(RepositoryCart repositoryCart) {
        this.repositoryCart = repositoryCart;
    }

    public void addCart(Integer userId, Integer productId, Integer quantity) {
        Cart cartCheck = repositoryCart.findCartByUserIdAndProductId(userId, productId);
        if (cartCheck != null) {
            cartCheck.setQuantity(cartCheck.getQuantity() + quantity);
            repositoryCart.save(cartCheck);
        }
        else {
            repositoryCart.insertCart(userId, productId, quantity);
        }
    }

    public List<Cart> getCartByMaUser(Integer userId) {
        return repositoryCart.getCartByMaUser(userId);
    }

    public void deleteCartByMaSanPham (Integer productId) {
        repositoryCart.deleteCartByMaSanPham(productId);
    }
}