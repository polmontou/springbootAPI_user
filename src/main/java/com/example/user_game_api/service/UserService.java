package com.example.user_game_api.service;

import com.example.user_game_api.dataclass.UserDTO;
import com.example.user_game_api.dataclass.UserLogsParams;


public interface UserService {
    UserDTO addUser(UserLogsParams user);
    UserDTO updateUser(UserLogsParams user);
    boolean removeUser(UserLogsParams user);
    boolean checkUser(UserLogsParams user);
}
