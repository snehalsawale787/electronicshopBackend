package com.commerce.electronicshop.services;

import com.commerce.electronicshop.dtos.CategoryDto;
import com.commerce.electronicshop.dtos.PageableResponse;

import java.awt.print.Pageable;

public interface CategoryService {

    //create
CategoryDto create(CategoryDto categoryDto);

    //update
CategoryDto update(CategoryDto categoryDto,String categoryId);

    //delete
void delete(String categoryId);

    //get all
PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);

    //get single category detail
CategoryDto get(String categoryId);
    //search
}
