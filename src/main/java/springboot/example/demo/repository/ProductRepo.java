package springboot.example.demo.repository;

import springboot.example.demo.entity.Product;

import java.util.List;

public interface ProductRepo {

    List<Product> findAll();
    void add(Product product);
}
