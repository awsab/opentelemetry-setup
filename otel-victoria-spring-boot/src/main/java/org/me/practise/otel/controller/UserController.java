package org.me.practise.otel.controller;

import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.me.practise.otel.dto.UserCreateDto;
import org.me.practise.otel.dto.UserDto;
import org.me.practise.otel.dto.UserUpdateDto;
import org.me.practise.otel.service.CustomUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final CustomUserService userService;

    @PostMapping
    @Timed(value = "user.controller.create", description = "Time taken to create user endpoint")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        log.info("Creating user with email: {}", userCreateDto.getEmail());
        UserDto createdUser = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    @Timed(value = "user.controller.getById", description = "Time taken to get user by ID endpoint")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        log.info("Fetching user with ID: {}", id);
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @Timed(value = "user.controller.getAll", description = "Time taken to get all users endpoint")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Fetching all users");
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @Timed(value = "user.controller.update", description = "Time taken to update user endpoint")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        log.info("Updating user with ID: {}", id);
        UserDto updatedUser = userService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "user.controller.delete", description = "Time taken to delete user endpoint")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Timed(value = "user.controller.searchByName", description = "Time taken to search users by name endpoint")
    public ResponseEntity<List<UserDto>> searchUsersByName(@RequestParam String name) {
        log.info("Searching users by name: {}", name);
        List<UserDto> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users);
    }
}

