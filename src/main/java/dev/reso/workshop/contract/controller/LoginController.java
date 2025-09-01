package dev.reso.workshop.contract.controller;

import dev.reso.workshop.contract.controller.request.UserRequest;
import dev.reso.workshop.contract.controller.response.UserResponse;
import dev.reso.workshop.contract.security.JwtUtil;
import dev.reso.workshop.contract.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserResponse> insertUser(@Valid @RequestBody UserRequest userRequest){
    UserResponse userResponse = service.insertUser(userRequest);
        return ResponseEntity.status(201).body(userResponse);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserRequest userRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password()));

        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

}
