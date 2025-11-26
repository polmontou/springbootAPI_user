package com.example.user_game_api.jpa;

import com.example.user_game_api.dataclass.UserDTO;
import com.example.user_game_api.dataclass.UserLogsParams;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String email;
    private String password;

    public static UserModel fromUserLogsParams(UserLogsParams user) {
        UserModel userModel = new UserModel();
        if (user.getId() != null) {
            userModel.setId(user.getId());
        }
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        return userModel;
    }

    public static UserModel fromUserDTO(UserDTO user) {
        UserModel userModel = new UserModel();
        if (user.getId() != null) {
            userModel.setId(user.getId());
        }
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        return userModel;
    }
}
