package com.syed.osama.hassan.springsecurity.auth.dao.impl;

import com.syed.osama.hassan.springsecurity.auth.ApplicationUser;
import com.syed.osama.hassan.springsecurity.auth.dao.ApplicationUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.syed.osama.hassan.springsecurity.security.model.Role.*;

@Repository("fake")
@RequiredArgsConstructor
public class ApplicationUserDaoImpl implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> findByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {

        return Arrays.asList(
                new ApplicationUser("admin", passwordEncoder.encode("admin"),
                        ADMIN.getAuthorities(), true, true,
                        true, true),
                new ApplicationUser("manager", passwordEncoder.encode("admin"),
                        MANAGER.getAuthorities(), true, true,
                        true, true),
                new ApplicationUser("student", passwordEncoder.encode("admin"),
                        STUDENT.getAuthorities(), true, true,
                        true, true)
        );
    }

}
