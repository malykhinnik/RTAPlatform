package com.rtaplatform.user;

public class UserCreateException extends RuntimeException {
    private static final String USER_EXIST_MESSAGE = "User exist";

    private UserCreateException(String message) {
        super(message);
    }

    public static UserCreateException exist() {
        return new UserCreateException(USER_EXIST_MESSAGE);
    }
}
