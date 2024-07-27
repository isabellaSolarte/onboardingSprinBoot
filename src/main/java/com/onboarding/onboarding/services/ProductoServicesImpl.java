package com.onboarding.onboarding.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboarding.onboarding.models.Product;
import com.onboarding.onboarding.repositories.ProductRepository;
import java.util.List;

@Service
public class ProductoServicesImpl implements IProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) this.productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Integer id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct!=null) {
            existingProduct.setNameProduct(product.getNameProduct());
            existingProduct.setDescriptionProduct(product.getDescriptionProduct());
            existingProduct.setPriceProduct(product.getPriceProduct());
            return productRepository.save(existingProduct);
        }
        return null;
    }
}