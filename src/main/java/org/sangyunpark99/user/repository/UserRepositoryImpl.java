package org.sangyunpark99.user.repository;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.user.application.interfaces.UserRepository;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.repository.entity.UserEntity;
import org.sangyunpark99.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    @Transactional
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return entity.toUser();
    }
}
