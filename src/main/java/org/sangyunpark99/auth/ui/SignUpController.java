package org.sangyunpark99.auth.ui;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.auth.application.AuthService;
import org.sangyunpark99.auth.application.EmailService;
import org.sangyunpark99.auth.application.dto.CreateUserAuthRequestDto;
import org.sangyunpark99.auth.application.dto.SendEmailRequestDto;
import org.sangyunpark99.common.ui.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailService.sendEmail(dto);
        return Response.ok(null);
    }

    @GetMapping("/verify-token")
    public Response<Void> verifyEmail(String email, String token) {
        emailService.verifyEmail(email, token);
        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<Long> register(@RequestBody CreateUserAuthRequestDto dto) {
        return Response.ok(authService.registerUser(dto));
    }
}
