package com.example.user_game_api.service;

import com.example.user_game_api.dataclass.UserDTO;
import com.example.user_game_api.dataclass.UserLogsParams;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDTO getUserById(UUID id);
    UserDTO addUser(UserLogsParams user);
    UserDTO updateUser(UserLogsParams user);
    List<UUID> getAllUsers();
    boolean removeUser(UserLogsParams user);
    boolean checkUser(UserLogsParams user);
}
