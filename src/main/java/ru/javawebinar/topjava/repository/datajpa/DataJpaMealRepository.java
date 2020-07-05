package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_TIME = Sort.by(Sort.Direction.DESC, "dateTime");
    @PersistenceContext
    private EntityManager em;

    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(this.em.getReference(User.class, userId));
        if (meal.isNew()) {
            return this.crudRepository.save(meal);
        } else if (this.get(meal.id(), userId) == null) {
            return null;
        }
        return this.crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return this.crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return this.crudRepository.findMealByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return this.crudRepository.findAllByUserId(userId, SORT_TIME);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return this.crudRepository.findMealsBy(startDateTime, endDateTime, userId);
    }
}
