package com.commerce.electronicshop.services;

import com.commerce.electronicshop.dtos.UserDto;
import com.commerce.electronicshop.entities.User;

import java.util.List;

public interface UserService {
    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    void deleteUser(String userId);

    //get all users
    List<UserDto> getAllUser();

    //get single users by Id
    UserDto getUserById(String userId);

    //get single users by email
    UserDto getUserByEmail(String email);

    //search user
    List<UserDto> searchUser(String keyword);

    //other user specific features
}
