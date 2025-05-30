package com.commerce.electronicshop.dtos;



import com.commerce.electronicshop.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;

    @Size(min = 3,max = 20,message = "Invalid Name!!")
    private String name;

    @Email(message = "Invalid user email!!")
    @Pattern(regexp ="^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",message = "Invalid user email")
    @NotBlank(message = "Email is required !!")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Size(min=4,max = 6,message = "Invalid Gender")
    private String gender;

    @NotBlank(message = "Write something about yourself !!")
    private String about;

    //@Pattern
    //Custom validator

    @ImageNameValid
    private String imageName;

}
