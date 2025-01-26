package org.sangyunpark99.user.repository;

import org.sangyunpark99.user.application.interfaces.UserRepository;
import org.sangyunpark99.user.domain.User;

import java.util.HashMap;
import java.util.Map;

public class FakeUserRepository implements UserRepository {

    private Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {

        if(user.getId() != null) {
            store.put(user.getId(), user);
            return user;
        }

        Long id = store.size() + 1L;
        User newUser = new User(id, user.getInfo());
        store.put(id, newUser);

        return newUser;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
