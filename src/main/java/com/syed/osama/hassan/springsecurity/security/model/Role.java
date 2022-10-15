package com.syed.osama.hassan.springsecurity.security.model;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;

import java.util.Set;

import static com.syed.osama.hassan.springsecurity.security.model.Permission.*;

@AllArgsConstructor
public enum Role {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_WRITE, COURSE_READ, STUDENT_WRITE, STUDENT_READ));

    private final Set<Permission> permissions;
}
