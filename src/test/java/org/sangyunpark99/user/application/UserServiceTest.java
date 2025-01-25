package org.sangyunpark99.user.application;

import org.junit.jupiter.api.Test;
import org.sangyunpark99.fake.FakeObjectFactory;
import org.sangyunpark99.user.application.dto.request.CreateUserRequestDto;
import org.sangyunpark99.user.domain.User;
import org.sangyunpark99.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals("test", userInfo.getName());
    }
}
