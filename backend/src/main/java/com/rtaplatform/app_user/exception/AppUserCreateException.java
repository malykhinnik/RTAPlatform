package com.rtaplatform.app_user.exception;

public class AppUserCreateException extends RuntimeException {
    private static final String USER_EXIST_MESSAGE = "User exist";

    private AppUserCreateException(final String message) {
        super(message);
    }

    public static AppUserCreateException exist() {
        return new AppUserCreateException(USER_EXIST_MESSAGE);
    }
}
