package org.sangyunpark99.auth;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.sangyunpark99.auth.domain.Email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }


    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com", "abcdefg", "뭐하세요.com"})
    void givenInvalidEmail_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@ab.com", "a@naver.com", "natty@naver.com", "test@test.com"})
    void givenEmailValidWhenCreateThenReturnEmail(String email){
        //given

        //when
        Email emailValue = Email.createEmail(email);

        //then
        assertEquals(email, emailValue.getEmailText());
    }
}
