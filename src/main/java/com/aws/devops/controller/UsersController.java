package com.aws.devops.controller;

import com.aws.devops.dto.UsersRequest;
import com.aws.devops.dto.UsersResponse;
import com.aws.devops.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<UsersResponse> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping
    public UsersResponse createUser(@RequestBody UsersRequest request) {
        return usersService.createUser(request);
    }

}
