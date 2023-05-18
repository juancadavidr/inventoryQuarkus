package org.inventory.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.inventory.entity.BuyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

@ApplicationScoped
public interface BuyDetailsRepository extends JpaRepository<BuyDetail, Integer> {
}