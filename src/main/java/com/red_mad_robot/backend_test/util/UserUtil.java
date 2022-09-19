package com.red_mad_robot.backend_test.util;

import com.red_mad_robot.backend_test.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUtil {

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}