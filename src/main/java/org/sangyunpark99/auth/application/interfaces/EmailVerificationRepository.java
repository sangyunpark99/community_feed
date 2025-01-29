package org.sangyunpark99.auth.application.interfaces;

import org.sangyunpark99.auth.domain.Email;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);

    void verifyEmail(Email email, String token);

    boolean isEmailVerified(Email email);
}
