package com.rtaplatform.user;

public class AppUserCreateException extends RuntimeException {
    private static final String USER_EXIST_MESSAGE = "User exist";

    private AppUserCreateException(String message) {
        super(message);
    }

    public static AppUserCreateException exist() {
        return new AppUserCreateException(USER_EXIST_MESSAGE);
    }
}
