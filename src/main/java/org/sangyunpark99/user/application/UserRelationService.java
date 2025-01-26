package org.sangyunpark99.user.application;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.user.application.dto.response.FollowUserRequestDto;
import org.sangyunpark99.user.application.interfaces.UserRelationRepository;
import org.sangyunpark99.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    @Transactional
    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.follow(targetUser); // 업데이트

        userRelationRepository.save(user, targetUser);
    }

    public void unfollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }
}
