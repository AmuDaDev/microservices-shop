package com.amuk3lani.shop.product.repository;

import com.amuk3lani.shop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
