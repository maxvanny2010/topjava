package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE from Meal m where m.id=?1 AND m.user.id=?2")
    int delete(int id, int userId);

    @Query("SELECT m FROM Meal m where  m.id=?1 AND m.user.id=?2")
    Meal findMealByIdAndUserId(int id, int userId);

    @Query("SELECT m FROM Meal m "
            + " WHERE m.user.id=?3 AND m.dateTime >= ?1 "
            + " AND m.dateTime < ?2 ORDER BY m.dateTime DESC")
    List<Meal> findMealsBy(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=?1")
    List<Meal> findAllByUserId(int userId, Sort sort);
}
