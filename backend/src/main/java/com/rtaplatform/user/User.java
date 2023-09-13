package com.rtaplatform.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private Long Id;
    private String firstName;
    private String lastName;
}
