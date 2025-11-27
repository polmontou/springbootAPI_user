package com.example.user_game_api.controller;


import com.example.user_game_api.dataclass.UserDTO;
import com.example.user_game_api.dataclass.UserLogsParams;
import com.example.user_game_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user_api")
@Tag(name = "Users", description = "API de gestion des utilisateurs")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @Operation(summary = "Récupérer tous les utilisateurs",
            description = "Retourne la liste des ID de tous les utilisateurs")
    @ApiResponse(responseCode = "200", description = "Liste des UUID des utilisateurs")
    public List<UUID> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Récupérer un utilisateur par ID",
            description = "Retourne les détails d'un utilisateur si l'ID correspond au header X-UserId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "200", description = "ID non autorisé - retourne null")
    })
    public UserDTO getUserById(
            @Parameter(description = "ID de l'utilisateur à récupérer", required = true)
            @PathVariable UUID userId,
            @Parameter(description = "ID de l'utilisateur connecté pour vérification", required = true)
            @RequestHeader("X-UserId") UUID id) {
        if (userId.equals(id)) {
            return userService.getUserById(id);
        }
        return null;
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel utilisateur",
            description = "Ajoute un utilisateur avec ses identifiants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    public UserDTO addUser(
            @Parameter(description = "Paramètres de connexion du nouvel utilisateur", required = true)
            @RequestBody UserLogsParams user) {
        return userService.addUser(user);
    }

    @PutMapping
    @Operation(summary = "Mettre à jour un utilisateur",
            description = "Modifie les informations d'un utilisateur existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    public UserDTO updateUser(
            @Parameter(description = "Nouvelles informations de l'utilisateur", required = true)
            @RequestBody UserLogsParams user,
            @Parameter(description = "ID de l'utilisateur connecté", required = true)
            @RequestHeader("X-UserId") UUID id) {
        return userService.updateUser(user);
    }

    @DeleteMapping
    @Operation(summary = "Supprimer un utilisateur",
            description = "Supprime un utilisateur si son ID correspond au header X-UserId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "true si supprimé, false sinon")
    })
    public boolean deleteUser(
            @Parameter(description = "Informations de l'utilisateur à supprimer", required = true)
            @RequestBody UserLogsParams user,
            @Parameter(description = "ID de l'utilisateur connecté pour vérification", required = true)
            @RequestHeader("X-UserId") UUID id) {
        if(user.getId().equals(id)) {
            return userService.removeUser(user);
        }
        return false;
    }

    @PostMapping("/check")
    @Operation(summary = "Vérifier les identifiants d'un utilisateur",
            description = "Valide le couple login/mot de passe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "true si identifiants valides, false sinon")
    })
    public boolean checkUser(
            @Parameter(description = "Identifiants de connexion à vérifier", required = true)
            @RequestBody UserLogsParams user) {
        return userService.checkUser(user);
    }
}