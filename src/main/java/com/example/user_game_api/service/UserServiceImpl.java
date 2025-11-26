package com.example.user_game_api.service;

import com.example.user_game_api.dataclass.UserDTO;
import com.example.user_game_api.dataclass.UserLogsParams;
import com.example.user_game_api.jpa.UserModel;
import com.example.user_game_api.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO addUser(UserLogsParams user) {
        UserModel userModel = user.toUserModel();

        return UserDTO.from(userRepository.save(userModel));
    }

    @Override
    public UserDTO updateUser(UserLogsParams user) {
        if (user.getId() != null) {
            UserModel userModel = user.toUserModel();

            return UserDTO.from(userRepository.save(userModel));
        }
        return null;
    }

    @Override
    public boolean removeUser(UserLogsParams user) {
        UUID idToRemove = user.getId();

        userRepository.deleteById(idToRemove);
        return (!userRepository.existsById(idToRemove));
    }

    @Override
    public boolean checkUser(UserLogsParams user) {
        UserModel userModel = userRepository.findById(user.getId()).get();

        return userModel.getEmail().equals(user.getEmail()) && userModel.getPassword().equals(user.getPassword());
    }
}
