package org.me.practise.otel.service;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.me.practise.otel.cleints.feign.PersonServiceClient;
import org.me.practise.otel.cleints.request.PersonResponse;
import org.me.practise.otel.dto.UserCreateDto;
import org.me.practise.otel.dto.UserDto;
import org.me.practise.otel.dto.UserUpdateDto;
import org.me.practise.otel.entity.User;
import org.me.practise.otel.exception.DuplicateResourceException;
import org.me.practise.otel.exception.ResourceNotFoundException;
import org.me.practise.otel.mapper.UserMapper;
import org.me.practise.otel.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PersonServiceClient personServiceClient;

//    @Timed (value = "user.service.create", description = "Time taken to create user")
//    @Retry (name = "userService", fallbackMethod = "createUserFallback")
//    @RateLimiter (name = "userService")
//    @Bulkhead (name = "userService")
    @Transactional
    public UserDto createUser (UserCreateDto userCreateDto) {
        log.info ("Creating user with email: {}", userCreateDto.getEmail ());

        if ( userRepository.existsByEmail (userCreateDto.getEmail ()) ) {
            throw new DuplicateResourceException ("User with email " + userCreateDto.getEmail () + " already exists");
        }

        try {
            User user = userMapper.toEntity (userCreateDto);
            User savedUser = userRepository.save (user);
            log.info ("User created successfully with ID: {}", savedUser.getId ());
            return userMapper.toDto (savedUser);
        } catch (DataIntegrityViolationException e) {
            log.error ("Data integrity violation while creating user", e);
            throw new DuplicateResourceException ("User with email " + userCreateDto.getEmail () + " already exists");
        }
    }

//    @Timed (value = "user.service.getById", description = "Time taken to get user by ID")
//    @Retry (name = "userService", fallbackMethod = "getUserByIdFallback")
//    @RateLimiter (name = "userService")
//    @Bulkhead (name = "userService")
    @Transactional (readOnly = true)
    @WithSpan
    public UserDto getUserById (Long id) {
        log.info ("Fetching user with ID: {}", id);

        User user = userRepository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException ("User not found with ID: " + id));

        log.info ("User found: {}", user.getName ());
        PersonResponse personResponse = personServiceClient.getPersonByPersonId (1L);
        log.info ("Person Service Response : {}", personResponse.toString ());
        return userMapper.toDto (user);
    }

//    @Timed (value = "user.service.getAll", description = "Time taken to get all users")
//    @Retry (name = "userService", fallbackMethod = "getAllUsersFallback")
//    @RateLimiter (name = "userService")
//    @Bulkhead (name = "userService")
    @Transactional (readOnly = true)
    public List<UserDto> getAllUsers () {
        log.info ("Fetching all users");

        List<User> users = userRepository.findAll ();
        log.info ("Found {} users", users.size ());

        return users.stream ()
                .map (userMapper::toDto)
                .collect (Collectors.toList ());
    }

//    @Timed (value = "user.service.update", description = "Time taken to update user")
//    @Retry (name = "userService", fallbackMethod = "updateUserFallback")
//    @RateLimiter (name = "userService")
//    @Bulkhead (name = "userService")
    @Transactional
    public UserDto updateUser (Long id, UserUpdateDto userUpdateDto) {
        log.info ("Updating user with ID: {}", id);

        User existingUser = userRepository.findById (id)
                .orElseThrow (() -> new ResourceNotFoundException ("User not found with ID: " + id));

        // Check if email is being changed and if it already exists
        if ( !existingUser.getEmail ().equals (userUpdateDto.getEmail ()) &&
                userRepository.existsByEmail (userUpdateDto.getEmail ()) ) {
            throw new DuplicateResourceException ("User with email " + userUpdateDto.getEmail () + " already exists");
        }

        userMapper.updateEntityFromDto (userUpdateDto, existingUser);
        User updatedUser = userRepository.save (existingUser);

        log.info ("User updated successfully with ID: {}", updatedUser.getId ());
        return userMapper.toDto (updatedUser);
    }

//    @Timed (value = "user.service.delete", description = "Time taken to delete user")
//    @Retry (name = "userService", fallbackMethod = "deleteUserFallback")
//    @RateLimiter (name = "userService")
//    @Bulkhead (name = "userService")
    @Transactional
    public void deleteUser (Long id) {
        log.info ("Deleting user with ID: {}", id);

        if ( !userRepository.existsById (id) ) {
            throw new ResourceNotFoundException ("User not found with ID: " + id);
        }

        userRepository.deleteById (id);
        log.info ("User deleted successfully with ID: {}", id);
    }

//    @Timed (value = "user.service.searchByName", description = "Time taken to search users by name")
//    @Retry (name = "userService", fallbackMethod = "searchUsersByNameFallback")
//    @RateLimiter (name = "userService")
//    @Bulkhead (name = "userService")
    @Transactional (readOnly = true)
    public List<UserDto> searchUsersByName (String name) {
        log.info ("Searching users by name: {}", name);

        List<User> users = userRepository.findByNameContaining (name);
        log.info ("Found {} users matching name: {}", users.size (), name);

        return users.stream ()
                .map (userMapper::toDto)
                .collect (Collectors.toList ());
    }

    // Fallback methods for Resilience4j
    public UserDto createUserFallback (UserCreateDto userCreateDto, Exception ex) {
        log.error ("Fallback method called for createUser", ex);
        throw new RuntimeException ("Service temporarily unavailable. Please try again later.");
    }

    public UserDto getUserByIdFallback (Long id, Exception ex) {
        log.error ("Fallback method called for getUserById", ex);
        throw new RuntimeException ("Service temporarily unavailable. Please try again later.");
    }

    public List<UserDto> getAllUsersFallback (Exception ex) {
        log.error ("Fallback method called for getAllUsers", ex);
        throw new RuntimeException ("Service temporarily unavailable. Please try again later.");
    }

    public UserDto updateUserFallback (Long id, UserUpdateDto userUpdateDto, Exception ex) {
        log.error ("Fallback method called for updateUser", ex);
        throw new RuntimeException ("Service temporarily unavailable. Please try again later.");
    }

    public void deleteUserFallback (Long id, Exception ex) {
        log.error ("Fallback method called for deleteUser", ex);
        throw new RuntimeException ("Service temporarily unavailable. Please try again later.");
    }

    public List<UserDto> searchUsersByNameFallback (String name, Exception ex) {
        log.error ("Fallback method called for searchUsersByName", ex);
        throw new RuntimeException ("Service temporarily unavailable. Please try again later.");
    }
}

