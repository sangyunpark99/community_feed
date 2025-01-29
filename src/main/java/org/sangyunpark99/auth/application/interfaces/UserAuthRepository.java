package org.sangyunpark99.auth.application.interfaces;

import org.sangyunpark99.auth.domain.UserAuth;
import org.sangyunpark99.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth userAuth, User user);
}
