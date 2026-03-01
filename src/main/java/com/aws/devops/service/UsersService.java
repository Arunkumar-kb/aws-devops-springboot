package com.aws.devops.service;

import com.aws.devops.dto.UsersRequest;
import com.aws.devops.dto.UsersResponse;
import com.aws.devops.entities.Users;
import com.aws.devops.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<UsersResponse> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(user -> new UsersResponse(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    public UsersResponse createUser(UsersRequest request) {
        Users user = new Users(request.name(), request.email(), request.password(), "USER", true);

        Users savedUser = usersRepository.save(user);

        return new UsersResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole());
    }

}
