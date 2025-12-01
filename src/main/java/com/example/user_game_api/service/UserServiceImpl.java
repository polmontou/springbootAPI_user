package com.example.user_game_api.service;

import com.example.user_game_api.dataclass.UserDTO;
import com.example.user_game_api.dataclass.UserLogsParams;
import com.example.user_game_api.jpa.UserModel;
import com.example.user_game_api.jpa.UserRepository;
import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UUID> getAllUsers() {
        Iterable<UserModel> users = userRepository.findAll();
        List<UUID> userIds = new ArrayList<>();

        for (UserModel user : users) {
            userIds.add(user.getId());
        }
        return userIds;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return UserDTO.from(userRepository.findById(id).get());
    }

    @Override
    public UserDTO addUser(UserLogsParams user) {
        UserModel userModel = user.toUserModel();

        return UserDTO.from(userRepository.save(userModel));
    }

    @Override
    public UserDTO updateUser(UserLogsParams user) {
        if (user.getId() == null) {
            return null;
        }
        UserModel userModel = user.toUserModel();

        return UserDTO.from(userRepository.save(userModel));
    }

    @Override
    public boolean removeUser(UserLogsParams user) {
        if (user.getId() == null) {
            return false;
        }

        UUID idToRemove = user.getId();

        userRepository.deleteById(idToRemove);
        return (!userRepository.existsById(idToRemove));
    }

    @Override
    public UUID checkUser(UserLogsParams user) {
        String select = "SELECT u FROM UserModel u WHERE u.email=:email and u.password=:password";
        TypedQuery<UserModel> query = entityManager.createQuery(select, UserModel.class);
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());

        List<UserModel> userModel = query.getResultList();

        if  (userModel.isEmpty()) {
            return null;
        }
        return userModel.getFirst().getId();
    }
}
