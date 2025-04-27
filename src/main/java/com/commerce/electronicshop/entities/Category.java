package com.commerce.electronicshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @Column(name = "id")
    private String categoryId;

    @Column(name="category_title",length = 60,nullable = false)
    private String title;

    @Column(name = "category_desc",length = 500)
    private String description;
    private String coverImage;

    //other attributes if you have.....
}
