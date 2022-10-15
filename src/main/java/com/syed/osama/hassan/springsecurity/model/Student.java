package com.syed.osama.hassan.springsecurity.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class Student {
    private final Integer id;
    private final String name;
}
