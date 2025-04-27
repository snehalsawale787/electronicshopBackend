package com.commerce.electronicshop.repositories;

import com.commerce.electronicshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {

}
