package dev.reso.workshop.contract.service;

import dev.reso.workshop.contract.controller.request.UserRequest;
import dev.reso.workshop.contract.controller.response.UserResponse;
import dev.reso.workshop.contract.entities.User;
import dev.reso.workshop.contract.exceptions.InvalidInputException;
import dev.reso.workshop.contract.mapper.UserMapper;
import dev.reso.workshop.contract.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse insertUser(UserRequest userRequest){
        if(existsByEmail(userRequest.email())){
            throw new InvalidInputException("Email ja cadastrado: " + userRequest.email());
        }

        User user = UserMapper.toUSer(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = repository.save(user);
        return UserMapper.toUserResponse(user);

    }

    public boolean existsByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

}
