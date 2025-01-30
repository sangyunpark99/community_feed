package org.sangyunpark99.acceptance.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.sangyunpark99.auth.application.dto.CreateUserAuthRequestDto;
import org.sangyunpark99.auth.application.dto.SendEmailRequestDto;
import org.sangyunpark99.user.application.dto.response.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.sangyunpark99.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.sangyunpark99.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.sangyunpark99.acceptance.steps.UserAcceptanceSteps.*;

@Component
@RequiredArgsConstructor
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {

        // user 1, 2, 3
        for(int i = 1; i <= 3; i++) {
            createUser("user" + i + "@test.com");
        }

        followUser(new FollowUserRequestDto(1L, 2L)); // 1번유저 2번유저 팔로우
        followUser(new FollowUserRequestDto(1L, 3L)); // 1번유저 3번유저 팔로우
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?",
                String.class)
                .setParameter(1,email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT isVerified FROM EmailVerificationEntity where email = :email",
                Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Long getUser(String email) {
        return (Long) entityManager.createNativeQuery("SELECT user_id FROM community_user_auth WHERE email = ?",
                        Long.class)
                .setParameter(1,email)
                .getSingleResult();
    }

    public void createUser(String email) {
        // 이메일로 먼저 점검 후, 회원가입
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email,token);
        registerUser(new CreateUserAuthRequestDto(email, "password", "USER", "name",""));
    }
 }
