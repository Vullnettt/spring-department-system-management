package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String saveProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        product.setSkuCode(UUID.randomUUID().toString());
        productRepository.save(product);
        log.info("Save product: {}", product.getName());
        return product.getSkuCode();
    }
    public List<ProductResponse> getAllProducts(){
       List<Product> products = productRepository.findAll();
        log.info("Get all products");
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .skuCode(product.getSkuCode())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public ProductResponse getProductBySkuCode(String skuCode){
        Product product = productRepository.findBySkuCode(skuCode);
        log.info("Get product by sku-code");
            return ProductResponse.builder()
                    .skuCode(product.getSkuCode())
                    .name(product.getName())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build();

        }

        public void removeQuantity(String skuCode){
        ProductRequest productRequest = new ProductRequest();
            Product product = productRepository.findBySkuCode(skuCode);

            Product.builder()
                    .name(productRequest.getName())
                    .price(productRequest.getPrice())
                    .quantity(productRequest.getQuantity())
                    .build();

            for(int i = 1; i >= 1; i--) {
                product.setQuantity(product.getQuantity() - i);
                log.info("Remove a quantity...");
                productRepository.save(product);
            }
        }
}
