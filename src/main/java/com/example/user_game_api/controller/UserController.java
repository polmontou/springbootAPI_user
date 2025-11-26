package com.example.user_game_api.controller;


import com.example.user_game_api.dataclass.UserDTO;
import com.example.user_game_api.dataclass.UserLogsParams;
import com.example.user_game_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO addUser(@RequestBody UserLogsParams user) {
        return userService.addUser(user);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserLogsParams user) {
        return userService.updateUser(user);
    }

    @DeleteMapping
    public boolean deleteUser(@RequestBody UserLogsParams user) {
        return userService.removeUser(user);
    }

    @PostMapping("/check")
    public boolean checkUser(@RequestBody UserLogsParams user) {
        return userService.checkUser(user);
    }
}
