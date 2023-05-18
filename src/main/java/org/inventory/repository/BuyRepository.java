package org.inventory.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.inventory.entity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

@ApplicationScoped
public interface BuyRepository extends JpaRepository<Buy, Integer> {
}