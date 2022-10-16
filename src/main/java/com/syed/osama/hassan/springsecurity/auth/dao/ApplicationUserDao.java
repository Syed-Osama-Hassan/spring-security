package com.syed.osama.hassan.springsecurity.auth.dao;

import com.syed.osama.hassan.springsecurity.auth.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> findByUsername(String username);

}
