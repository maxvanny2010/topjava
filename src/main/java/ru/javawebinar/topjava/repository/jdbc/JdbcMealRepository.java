package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepository implements MealRepository {
    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    public JdbcMealRepository(final JdbcTemplate jdbcTemplate,
                              final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Meal save(final Meal meal, final int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("dateTime", meal.getDateTime())
                .addValue("calories", meal.getCalories())
                .addValue("description", meal.getDescription())
                .addValue("user_id", userId);

        if (meal.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            meal.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE meals SET datetime=:dateTime, calories=:calories, description=:description"
                        + " WHERE id=:id AND user_id=:user_id", map) == 0) {
            return null;
        }
        return meal;
    }

    @Override
    public final boolean delete(final int id, final int userId) {
        return jdbcTemplate.update("DELETE FROM meals "
                + " WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public final Meal get(final int id, final int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals"
                + " WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public final List<Meal> getAll(final int userId) {
        return jdbcTemplate.query("SELECT * FROM meals "
                + " WHERE user_id=? ORDER BY datetime DESC", ROW_MAPPER, userId);
    }

    @Override
    public final List<Meal> getBetweenHalfOpen(final LocalDateTime startDate,
                                               final LocalDateTime endDate,
                                               final int userId) {
        return jdbcTemplate.query("SELECT * FROM meals"
                        + " WHERE user_id=? AND (datetime >=? AND datetime<=?)"
                        + "ORDER BY datetime DESC",
                ROW_MAPPER, userId, startDate, endDate);
    }
}
