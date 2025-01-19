package org.sangyunpark99.user.application.interfaces;

import org.sangyunpark99.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);
}
