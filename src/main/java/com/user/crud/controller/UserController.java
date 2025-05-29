package com.user.crud.controller;

import com.user.crud.dto.UserDto;
import com.user.crud.entity.User;
import com.user.crud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/say")
    public String hello() {
        return "hello";
    }

    // Create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
  //      return ResponseEntity.ok(createdUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }


    @GetMapping("/users/{theUserId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long theUserId) {
        UserDto userdto = userService.getUserById(theUserId);
        return ResponseEntity.ok(userdto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> theUsers = userService.getAllUsers();
   //     return ResponseEntity.ok(theUsers);
        return new ResponseEntity<>(theUsers, HttpStatus.OK);
    }

    // update
    @PutMapping("/users/{theId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long theId) {
        UserDto newUser = userService.updateUser(userDto, theId);
        return ResponseEntity.ok(newUser);
    }

    // delete
    @DeleteMapping("/users/{theId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long theId) {
        userService.deleteUserById(theId);
        return ResponseEntity.ok("User deleted with id: " + theId);
    }

}
