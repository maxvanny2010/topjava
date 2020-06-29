package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Component("JpaMealRepository")
@Repository
@Transactional()
public class JpaMealRepository implements MealRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            final User ref = this.em.getReference(User.class, userId);
            meal.setUser(ref);
            this.em.persist(meal);
            return meal;
        } else {
            return this.em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        final User user = this.em.getReference(User.class, userId);
        return this.em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .setParameter("userId", user.getId())
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        final User user = this.em.getReference(User.class, userId);
        return this.em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id", id)
                .setParameter("userId", user.getId())
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        final User ref = this.em.getReference(User.class, userId);
        return this.em.createNamedQuery(Meal.ALL, Meal.class)
                .setParameter("ref", ref)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        final User ref = this.em.getReference(User.class, userId);
        return this.em.createNamedQuery(Meal.PERIOD, Meal.class)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .setParameter("ref", ref)
                .getResultList();

    }
}
