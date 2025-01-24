package org.sangyunpark99.user.application.interfaces;

import org.sangyunpark99.user.domain.User;

public interface UserRepository {

    User save(User user);

    User findById(Long id);
}
