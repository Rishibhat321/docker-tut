package com.user.crud.service.impl;

import com.user.crud.dto.UserDto;
import com.user.crud.entity.User;
import com.user.crud.exceptionHandling.ResourceNotFoundException;
import com.user.crud.repository.UserRepository;
import com.user.crud.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        // convert the dto object to entity
        User theUser = convertToEntity(userDto);
        User savedUser = userRepository.save(theUser);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
/*
        return userRepository.findById(userId)
                .orElseThrow(() ->  new RuntimeException("User not found with id: " + userId));
 */

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));

        return convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, Long theId) {

        UserDto userDto1 = getUserById(theId);
        User newUser = convertToEntity(userDto1);

        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setAddress(userDto.getAddress());

        User updatedUser = userRepository.save(newUser);
        return convertToDto(updatedUser);

    }

    @Override
    @Transactional
    public void deleteUserById(Long theId) {

        User user = userRepository.findById(theId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + theId));

        userRepository.deleteById(theId);
    }


}
