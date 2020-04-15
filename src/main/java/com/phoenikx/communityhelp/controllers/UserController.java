package com.phoenikx.communityhelp.controllers;

import com.phoenikx.communityhelp.businessobjects.UserBO;
import com.phoenikx.communityhelp.config.UserContextStore;
import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.models.User;
import com.phoenikx.communityhelp.reqresps.UserUpdateRequest;
import com.phoenikx.communityhelp.services.apis.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserContextStore userContextStore;

    @PutMapping
    public UserBO updateUser(@RequestBody UserUpdateRequest request) {
        Optional<User> userOptional = userService.updateUser(userContextStore.getUserId(),
                request.getHomeLocation(), request.getName());
        if (!userOptional.isPresent()) {
            throw new InvalidRequestException("No such user found.");
        }
        return UserBO.fromUser(userOptional.get());
    }

    @GetMapping("me")
    public UserBO getLoggedInUser() {
        Optional<User> userOptional = userService.findByUserId(userContextStore.getUserId());
        if (!userOptional.isPresent()) {
            throw new InvalidRequestException("No such user found.");
        }
        return UserBO.fromUser(userOptional.get());
    }

}
