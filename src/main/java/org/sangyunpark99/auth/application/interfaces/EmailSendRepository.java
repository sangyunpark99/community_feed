package org.sangyunpark99.auth.application.interfaces;

import org.sangyunpark99.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(Email email, String randomToken);
}
