package org.sangyunpark99.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.sangyunpark99.auth.domain.Password;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        Password password = Password.createEncryptPassword("password");
        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchDifferentPassword_thenReturnTrue() {
        Password password = Password.createEncryptPassword("password1");
        assertFalse(password.matchPassword("password"));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowError(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(null));
    }

}