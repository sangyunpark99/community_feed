package org.sangyunpark99.acceptance.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sangyunpark99.acceptance.steps.LoginAcceptanceSteps;
import org.sangyunpark99.acceptance.utils.AcceptanceTestTemplate;
import org.sangyunpark99.auth.application.dto.LoginRequestDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoginAcceptanceTest extends AcceptanceTestTemplate  {

    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "password";
    private static final String WRONG_PASSWORD = "wrong password";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(EMAIL);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        //given
        LoginRequestDto dto = new LoginRequestDto(EMAIL, PASSWORD, "token");

        //when
        String token = LoginAcceptanceSteps.requestLoginGetToken(dto);

        //then
        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero() {
        //given
        LoginRequestDto dto = new LoginRequestDto(EMAIL, WRONG_PASSWORD, "token");

        //when
        Integer code = LoginAcceptanceSteps.requestLoginGetResponseCode(dto);

        //then
        assertEquals(400, code);
    }
}
