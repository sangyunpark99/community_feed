package org.sangyunpark99.auth.repository;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.auth.application.interfaces.UserAuthRepository;
import org.sangyunpark99.auth.domain.UserAuth;
import org.sangyunpark99.auth.repository.entity.UserAuthEntity;
import org.sangyunpark99.auth.repository.jpa.JpaUserAuthRepository;
import org.sangyunpark99.user.application.interfaces.UserRepository;
import org.sangyunpark99.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(UserAuth auth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(auth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);

        return userAuthEntity.toUserAuth();
    }

    @Override
    @Transactional
    public UserAuth loginUser(String email, String password) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        userAuthEntity.updateLastLoginAt();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if(!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("올바르지 않은 비밀번호 입니다.");
        }

        return userAuth;
    }
}
