package com.commerce.electronicshop.services.impl;

import com.commerce.electronicshop.dtos.UserDto;
import com.commerce.electronicshop.entities.User;
import com.commerce.electronicshop.repositories.UserRepository;
import com.commerce.electronicshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {


      //generate unique id in string format
        String userId= UUID.randomUUID().toString();
        userDto.setUserId(userId);
        //dto->entity
        User user= dtoToEntity(userDto);
        User savedUser=userRepository.save(user);
        //entity->dto
        UserDto newDto=entityToDto(savedUser);
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        //email.update
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        //save data
          User updatedUser      = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);

        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with given id");
        //delete user
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id "));

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
    User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with given emailid and password"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword)
    {
        List<User> users = userRepository.findByNameContaining(keyword);
       Stream<UserDto> dtoList = users.stream().map(user -> entityToDto(user));
        return dtoList;
    }
    private UserDto entityToDto(User savedUser)
    {
//        UserDto userDto=UserDto.builder()
//        .userId(savedUser.getUserId())
//        .name(savedUser.getName())
//        .email(savedUser.getEmail())
//        .password(savedUser.getPassword())
//        .about(savedUser.getAbout())
//        .gender(savedUser.getGender())
//        .imageName(savedUser.getImageName())
//        .build();

        return mapper.map(savedUser,UserDto.class);
    }
    private User dtoToEntity(UserDto userDto)
    {
//        User user=User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
      return mapper.map(userDto,User.class);
    }
}


