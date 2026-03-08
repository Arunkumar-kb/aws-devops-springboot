package com.aws.devops.service;

import com.aws.devops.dto.UsersRequest;
import com.aws.devops.dto.UsersResponse;
import com.aws.devops.entities.User;
import com.aws.devops.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsersResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UsersResponse(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    public UsersResponse createUser(UsersRequest request) {
        User user = new User(request.name(), request.email(), passwordEncoder.encode(request.password()), "USER", true);

        User savedUser = userRepository.save(user);

        return new UsersResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole());
    }

}
