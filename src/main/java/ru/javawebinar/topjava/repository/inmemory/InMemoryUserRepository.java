package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, User> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public final boolean delete(int id) {
        LOG.info("delete {}", id);
        return this.repository.remove(id) != null;
    }

    @Override
    public final User save(User user) {
        LOG.info("save {}", user);
        if (user.isNew()) {
            user.setId(this.counter.getAndIncrement());
            this.repository.put(user.getId(), user);
            return user;
        }
        return this.repository.computeIfPresent(
                user.getId(), (id, oldUser) -> user);
    }

    @Override
    public final User get(int id) {
        LOG.info("get {}", id);
        return this.repository.get(id);
    }

    @Override
    public final List<User> getAll() {
        final List<User> list = new ArrayList<>(this.repository.values());
        list.sort(this::compareByName);
        LOG.info("getAll {}", list);
        return list;
    }

    @Override
    public final User getByEmail(String email) {
        LOG.info("getByEmail {}", email);
        return this.repository.values()
                .stream()
                .filter(user -> Objects.equals(email, user.getEmail()))
                .findFirst()
                .orElse(null);
    }

    private int compareByName(final User o1, final User o2) {
        final String left = o1.getName();
        final String right = o2.getName();
        final int compare = left.compareTo(right);
        if (compare == 0) {
            int min = Math.min(left.length(), right.length());
            return IntStream.range(0, min)
                    .map(z -> Character.compare(left.charAt(z), right.charAt(z)))
                    .filter(z -> z != 0)
                    .findFirst()
                    .orElse(Integer.compare(left.length(), right.length()));
        }
        return compare;
    }

}
