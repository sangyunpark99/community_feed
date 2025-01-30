package org.sangyunpark99.common.principle;

import lombok.Getter;
import org.sangyunpark99.auth.domain.UserRole;

@Getter
public class UserPrincipal {

    private Long userId;
    private UserRole userRole;

    public UserPrincipal(Long userId, String userRole) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(userRole);
    }
}
