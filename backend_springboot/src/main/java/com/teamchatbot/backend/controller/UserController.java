package com.teamchatbot.backend.controller;

import com.teamchatbot.backend.dto.CreateUserRequest;
import com.teamchatbot.backend.dto.TransferUserRequest;
import com.teamchatbot.backend.entity.User;
import com.teamchatbot.backend.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/transfer")
    public User transferUser(
            @PathVariable Long id,
            @RequestBody TransferUserRequest request) {

        return userService.transferUser(id, request.getTeamId());
    }

}
