package dev.reso.workshop.contract.mapper;

import dev.reso.workshop.contract.controller.request.UserRequest;
import dev.reso.workshop.contract.controller.response.UserResponse;
import dev.reso.workshop.contract.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUSer(UserRequest userRequest){
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
