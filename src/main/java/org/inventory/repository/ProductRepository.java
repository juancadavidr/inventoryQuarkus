package org.inventory.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@ApplicationScoped
public interface ProductRepository extends JpaRepository<Product, Integer> {
}