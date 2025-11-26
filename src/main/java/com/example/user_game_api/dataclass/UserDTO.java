package com.example.user_game_api.dataclass;

import com.example.user_game_api.jpa.UserModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDTO {
    private UUID id;
    private String email;
    private String password;

    public static UserDTO from(UserModel user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public UserModel toUserModel(UserDTO this) {
        return UserModel.fromUserDTO(this);
    }
}
