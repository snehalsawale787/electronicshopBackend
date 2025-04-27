package com.commerce.electronicshop.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {


    @Id
    private String categoryId;

@NotBlank
@Min(value = 4,message = "Title must be of minimum 4 characters")
private String title;

@NotBlank(message = "Description required !!")
    private String description;

@NotBlank(message = "Cover image is required")
    private String coverImage;

}
