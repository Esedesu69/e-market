package com.Abdu.shop.repositories;

import com.Abdu.account.models.AccountEntity;
import com.Abdu.shop.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findBySessionToken(String  sessionToken);
    ShoppingCart findByUser(AccountEntity user);
}