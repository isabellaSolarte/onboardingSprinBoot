package com.onboarding.onboarding.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.onboarding.onboarding.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    
}
