package org.sangyunpark99.user.repository;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.user.application.interfaces.UserRelationRepository;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.repository.entity.UserEntity;
import org.sangyunpark99.user.repository.entity.UserRelationIdEntity;
import org.sangyunpark99.user.repository.entity.UserRelationshipEntity;
import org.sangyunpark99.user.repository.jpa.JpaUserRelationRepository;
import org.sangyunpark99.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    public void save(User user, User targetUser) {
        UserRelationshipEntity entity = new UserRelationshipEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);

        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser))); // 이때 팔로워 수는 갱신되어 있다.
        // 도메인으로 수정하기 때문에 더티체킹이 되지 않는다. 따라서 saveAll을 해준다.
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationshipEntity entity = jpaUserRelationRepository.findById(new UserRelationIdEntity(user.getId()
                ,targetUser.getId())).orElseThrow(() -> new IllegalArgumentException());

        jpaUserRelationRepository.delete(entity);

        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}