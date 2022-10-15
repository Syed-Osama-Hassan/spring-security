package com.syed.osama.hassan.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Student {
    private final Integer id;
    private final String name;
}
