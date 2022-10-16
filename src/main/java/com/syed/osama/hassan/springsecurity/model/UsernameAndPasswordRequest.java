package com.syed.osama.hassan.springsecurity.model;

import lombok.Data;

@Data
public class UsernameAndPasswordRequest {
    private String username;
    private String password;
}
