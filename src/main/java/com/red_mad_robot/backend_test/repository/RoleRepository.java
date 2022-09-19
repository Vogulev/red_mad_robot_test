package com.red_mad_robot.backend_test.repository;

import com.red_mad_robot.backend_test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
