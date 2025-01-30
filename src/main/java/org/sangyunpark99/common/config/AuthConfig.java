package org.sangyunpark99.common.config;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.auth.domain.TokenProvider;
import org.sangyunpark99.common.principle.AuthPrincipalArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;

    @Override
    public void addArgumentResolvers(List argumentsResolvers) {
        argumentsResolvers.add(new AuthPrincipalArgumentResolver(tokenProvider));
    }
}
