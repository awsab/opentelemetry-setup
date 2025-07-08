package org.me.practise.otel.mapper;


import org.me.practise.otel.dto.UserCreateDto;
import org.me.practise.otel.dto.UserDto;
import org.me.practise.otel.dto.UserUpdateDto;
import org.me.practise.otel.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                      .id(user.getId())
                      .name(user.getName())
                      .email(user.getEmail())
                      .address(user.getAddress())
                      .phone(user.getPhone())
                      .createdAt(user.getCreatedAt())
                      .updatedAt(user.getUpdatedAt())
                      .build();
    }

    public User toEntity(UserCreateDto userCreateDto) {
        return User.builder()
                   .name(userCreateDto.getName())
                   .email(userCreateDto.getEmail())
                   .address(userCreateDto.getAddress())
                   .phone(userCreateDto.getPhone())
                   .build();
    }

    public void updateEntityFromDto(UserUpdateDto userUpdateDto, User user) {
        user.setName(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());
        user.setAddress(userUpdateDto.getAddress());
        user.setPhone(userUpdateDto.getPhone());
    }
}

