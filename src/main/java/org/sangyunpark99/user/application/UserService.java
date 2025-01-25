package org.sangyunpark99.user.application;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.user.application.dto.request.CreateUserRequestDto;
import org.sangyunpark99.user.application.dto.response.GetUserResponseDto;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.domain.UserInfo;
import org.sangyunpark99.user.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepository;

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);

        return userRepository.save(user);
    }

    public User getUser(final Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user= getUser(id);

        return new GetUserResponseDto(user);
    }
}
