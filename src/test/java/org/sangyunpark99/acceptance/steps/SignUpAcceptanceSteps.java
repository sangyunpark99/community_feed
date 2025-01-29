package org.sangyunpark99.acceptance.steps;

import io.restassured.RestAssured;
import org.sangyunpark99.auth.application.dto.SendEmailRequestDto;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/send-verification-email")
                .then()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer requestVerifyEmail(String email, String token) {
        return RestAssured
                .given()
                .queryParam("email", email)
                .queryParam("token", token)
                .when()
                .get("/signup/verify-token")
                .then()
                .extract()
                .jsonPath().get("code");
    }
}
