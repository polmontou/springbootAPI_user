package com.example.user_game_api.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserModel, UUID> {

}
