package com.onboarding.onboarding.services;
import java.util.List;
import com.onboarding.onboarding.models.Product;

public interface IProductServices {

    public List<Product> findAll();
    public Product findById(Integer id);
    public Product save(Product product);
    public void delete(Integer id);
    public Product update(Integer id, Product product);
} 