package com.user.crud.service;

import com.user.crud.dto.UserDto;
import com.user.crud.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

   UserDto updateUser(UserDto userDto, Long theId);

   void deleteUserById(Long theId);

}
