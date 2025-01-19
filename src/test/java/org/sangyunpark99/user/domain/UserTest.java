package org.sangyunpark99.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private final UserInfo userInfo = new UserInfo("test","");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    @DisplayName("아이디가 서로 다른 두 유저가 주어진 경우, 비교 테스트 시, false가 리턴됩니다.")
    void givenTwodUser_whenEqual_ReturnFalse() throws Exception{
        //when
        boolean isSame = user1.equals(user2);

        //then
        assertFalse(isSame);
    }

    @Test
    @DisplayName("아이디가 서로 같은 두 유저가 주어진 경우, 비교 테스트 시, true가 리턴됩니다.")
    void givenTwoSameIdUser_whenEqual_ReturnTrue() throws Exception{
        //given
        User sameUser = new User(1L,userInfo);

        //when
        boolean isSame = user1.equals(sameUser);

        //then
        assertTrue(isSame);
    }

    @Test
    @DisplayName("아이디가 서로 다른 두 유저가 주어진 경우, 해시 코드 비교 테스트 시, false가 리턴됩니다.")
    void givenTwoUser_whenHashCode_thenReturnFalse() throws Exception{
        //when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        //then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("아이디가 서로 같은 두 유저가 주어진 경우, 해시 코드 비교 테스트 시, false가 return 됩니다.")
    void givenTwoUser_whenHashCode_thenReturnTrue() throws Exception{
        //given
        User sameUser = new User(1L, userInfo);

        //when
        int hashCode1 = user1.hashCode();
        int hashCode2 = sameUser.hashCode();

        //then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("두 유저가 주어진 경우, 1번 유저가 2번유저를 팔로우시, 1번유저의 팔로우 수와 2번 유저의 팔로워 수가 일치합니다.")
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() throws Exception{
        //when
        user1.follow(user2);

        //then
        assertEquals(1,user1.getFollowingCount());
        assertEquals(0,user1.getFollowerCount());
        assertEquals(0,user2.getFollowingCount());
        assertEquals(1,user2.getFollowerCount());
    }

    @Test
    @DisplayName("두 유저가 주어진 경우, 1번 유저가 2번유저를 팔로우시, 1번유저의 팔로우 수와 2번 유저의 팔로워 수가 일치합니다.")
    void givenTwoUserUser1FollowUser2_whenUser1UnfollowUser2_thenDecreaseUserCount() throws Exception{
        //given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0,user1.getFollowingCount());
        assertEquals(0,user1.getFollowerCount());
        assertEquals(0,user2.getFollowingCount());
        assertEquals(0,user2.getFollowerCount());
    }
}