package org.sangyunpark99.auth.domain;

import java.util.regex.Pattern;

public class Email { // 유효성 확인 클래스

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;

    private Email(String email) {
        this.emailText = email;
    }

    public static Email createEmail(String email) {
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is not valid");
        }

        if(isNotValidEmailString(email)) {
            throw new IllegalArgumentException("email is not valid");
        }

        return new Email(email);
    }

    private static boolean isNotValidEmailString(String email) {
        return !pattern.matcher(email).matches();
    }

    public String getEmailText() {
        return this.emailText;
    }
}