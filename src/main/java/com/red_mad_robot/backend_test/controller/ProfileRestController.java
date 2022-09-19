package com.red_mad_robot.backend_test.controller;

import com.red_mad_robot.backend_test.AuthorizedUser;
import com.red_mad_robot.backend_test.model.User;
import com.red_mad_robot.backend_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.red_mad_robot.backend_test.util.validation.ValidationUtil.assureIdConsistent;
import static com.red_mad_robot.backend_test.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileRestController {
    static final String REST_URL = "/rest/profile";
    private final UserService service;

    @GetMapping
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        return service.get(authUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        service.delete(authUser.getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        checkNew(user);
        User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @AuthenticationPrincipal AuthorizedUser authUser) {
        assureIdConsistent(user, authUser.getId());
        service.update(user);
    }
}