package com.example.user_game_api.dataclass;

import com.example.user_game_api.jpa.UserModel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserLogsParams {
    private UUID id;
    private String email;
    private String password;

    public UserModel toUserModel(UserLogsParams this) {
        return UserModel.fromUserLogsParams(this);
    }
}
