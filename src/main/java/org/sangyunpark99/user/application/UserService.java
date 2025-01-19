package org.sangyunpark99.user.application;

import org.sangyunpark99.user.application.dto.CreateUserRequestDto;
import org.sangyunpark99.user.application.interfaces.UserRepository;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);

        return userRepository.save(user);
    }

    public User getUser(final Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
