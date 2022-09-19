package com.red_mad_robot.backend_test;

import com.red_mad_robot.backend_test.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        user.setPassword(null);
        setUser(user);
    }

    public int getId() {
        return user.id();
    }
}