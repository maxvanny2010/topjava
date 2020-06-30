package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        final User ref = this.em.getReference(User.class, userId);
        if (meal.isNew()) {
            meal.setUser(ref);
            this.em.persist(meal);
            return meal;
        }
        final Meal existMeal = this.get(meal.getId(), userId);
        if (Objects.nonNull(existMeal)) {
            return this.em.merge(meal);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return this.em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        final boolean query = Objects.nonNull(meal) && meal.getUser().getId() == userId;
        return query ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        final User ref = this.em.getReference(User.class, userId);
        return this.em.createNamedQuery(Meal.ALL, Meal.class)
                .setParameter("userId", ref.getId())
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        final User ref = this.em.getReference(User.class, userId);
        return this.em.createNamedQuery(Meal.PERIOD, Meal.class)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .setParameter("userId", ref.getId())
                .getResultList();

    }
}
