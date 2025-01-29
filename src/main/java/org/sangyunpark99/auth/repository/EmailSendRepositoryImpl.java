package org.sangyunpark99.auth.repository;

import org.sangyunpark99.auth.application.interfaces.EmailSendRepository;
import org.sangyunpark99.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {

    }
}
