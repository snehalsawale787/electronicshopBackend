package com.commerce.electronicshop.services.impl;

import com.commerce.electronicshop.dtos.PageableResponse;
import com.commerce.electronicshop.dtos.ProductDto;
import com.commerce.electronicshop.entities.Product;
import com.commerce.electronicshop.exceptions.ResourceNotFoundException;
import com.commerce.electronicshop.helper.Helper;
import com.commerce.electronicshop.repositories.ProductRepository;
import com.commerce.electronicshop.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;
    //other dependency

    @Override
    public ProductDto create(ProductDto productDto) {

        Product product = mapper.map(productDto, Product.class);

        //product id

        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);

        //added
        product.setAddedDate(new Date());
        Product saveProduct = productRepository.save(product);
        return mapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        //fetch the product of given id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given id"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductImageName(productDto.getProductImageName());
        
        //save the entity
        Product updatedProduct = productRepository.save(product);
        return mapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given id"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto get(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given id"));

        return mapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle,pageable);
        return Helper.getPageableResponse(page,ProductDto.class);
    }
}
