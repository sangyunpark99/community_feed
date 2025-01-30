package org.sangyunpark99.auth;

import org.junit.jupiter.api.Test;
import org.sangyunpark99.auth.domain.TokenProvider;

import static org.junit.jupiter.api.Assertions.*;

class TokenProviderTest {

    private final String secretKey =
            "asjfsdlfkdsfsjlfdsfsflskdfsdjfsldkfjlsjfdlfskdfjdlsfjksdfldsjflkdsfjlsdjfslkdjflsdjfldksfjldsjfldsjflsdkjfkldsjlfsdjlfksdjlfksdjfldsjlfsdjflkdsjflfjsldkfjsdklfj";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() throws Exception{
        //given
        Long userId = 1L;
        String role = "ADMIN";

        //when
        String token = tokenProvider.createToken(userId,role);

        //then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }

    @Test
    void givenInValidToken_whenGetUserId_thenThrowError() {
        // given
        String invalidToken = "invalid token";

        // when, then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }
}
