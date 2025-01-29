package org.sangyunpark99.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.sangyunpark99.auth.application.dto.CreateUserAuthRequestDto;
import org.sangyunpark99.user.application.dto.request.CreateUserRequestDto;
import org.sangyunpark99.user.application.dto.response.FollowUserRequestDto;
import org.springframework.http.MediaType;

public class UserAcceptanceSteps {

    private static final String CREATE_USER = "/user";
    private static final String FOLLOW_USER = "/relation/follow";

    public static ExtractableResponse<Response> createUser(CreateUserRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(CREATE_USER)
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> followUser(FollowUserRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(FOLLOW_USER)
                .then()
                .extract();
    }

    public static Integer registerUser(CreateUserAuthRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/register")
                .then()
                .extract()
                .jsonPath().get("code");
    }
}
