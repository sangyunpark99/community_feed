package org.sangyunpark99.acceptance.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.sangyunpark99.user.application.dto.request.CreateUserRequestDto;
import org.sangyunpark99.user.application.dto.response.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static org.sangyunpark99.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.sangyunpark99.acceptance.steps.UserAcceptanceSteps.followUser;

@Component
@RequiredArgsConstructor
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto); // 1번유저 생성
        createUser(dto); // 2번유저 생성
        createUser(dto); // 3번유저 생성

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
 }
