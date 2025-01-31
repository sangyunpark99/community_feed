package org.sangyunpark99.auth.domain;

public class Password {

    private final String encryptedPassword;
    private static final String PASSWORD_NOT_NULL_OR_EMPTY = "패스워드는 빈값이 될 수 없습니다.";

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static Password createPassword(String password) {
        isEmptyOrNullPassword(password);
        return new Password(password);
    }

    public static Password createEncryptPassword(String password) {
        isEmptyOrNullPassword(password);
        return new Password(SHA256.encrypt(password));
    }

    public static void isEmptyOrNullPassword(String password) {
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException(PASSWORD_NOT_NULL_OR_EMPTY);
        }
    }

    public boolean matchPassword(String password) {
        return encryptedPassword.equals(password);
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
