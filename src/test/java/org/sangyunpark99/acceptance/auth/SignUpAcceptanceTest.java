package org.sangyunpark99.acceptance.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sangyunpark99.acceptance.utils.AcceptanceTestTemplate;
import org.sangyunpark99.auth.application.dto.CreateUserAuthRequestDto;
import org.sangyunpark99.auth.application.dto.SendEmailRequestDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.sangyunpark99.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.sangyunpark99.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.sangyunpark99.acceptance.steps.UserAcceptanceSteps.registerUser;

public class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";

    @BeforeEach
    public void setUp() {
        this.cleanUp(); // 데이터가 매번 지워지는 부분만 수행
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved() {
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        //when
        Integer code = requestSendEmail(dto);

        //then
        String token = getEmailToken(email);
        assertNotNull(token);
        assertEquals(0,code);
    }

    @Test
    void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved() {
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto("abcd");

        //when
        Integer code = requestSendEmail(dto);

        //then
        assertEquals(400,code);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified(){
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail(email, "wrong token");

        //then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(400,code);
        assertFalse(isEmailVerified);
    }

    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError() { // 2번 요청 - 중복 처리
        //given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // when
        Integer code = requestVerifyEmail(email, token);

        // then
        assertEquals(400, code); // 2번 하게 되면 에러가 발생한다.
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail("wrong email", "token");

        assertEquals(400,code);
    }

    @Test
    void givenVerifiedEmail_whenRegister_thenUserRegistered() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        //when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "password", "USER", "name",
                "profileImageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(0, code);
        Long userId = getUserId(email);
        assertEquals(1L, userId);
    }

    @Test
    void givenUnverifiedEmail_whenRegister_thenThrowError() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "password", "USER", "name",
                "profileImageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(400, code);
    }
}