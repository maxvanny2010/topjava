package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO;

@RestController
@RequestMapping(value = MealRestController.REST_MEALS, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    static final String REST_MEALS = "/rest/meals";

    @Override
    @GetMapping
    public final List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public final Meal get(@PathVariable final int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public final void delete(@PathVariable final int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void update(@RequestBody final Meal meal, @PathVariable final int id) {
        super.update(meal, id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody final Meal meals) {
        final String description = meals.getDescription() != null ? meals.getDescription() : "";
        final int calories = meals.getCalories() != 0 ? meals.getCalories() : 1000;
        final Meal created = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), description, calories);
        super.create(created);
        final URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_MEALS + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/filter?{startDate}&{endDate}&{startTime}&{endTime}")
    public final ResponseEntity<List<MealTo>> getAllBetween(
            @PathVariable @DateTimeFormat(iso = ISO.DATE) final LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = ISO.DATE) final LocalDate endDate,
            @PathVariable @DateTimeFormat(iso = ISO.TIME) final LocalTime startTime,
            @PathVariable @DateTimeFormat(iso = ISO.TIME) final LocalTime endTime) {
        final List<MealTo> between = super.getBetween(startDate, startTime, endDate, endTime);
        return between.isEmpty()
                ? new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(between, HttpStatus.OK);
    }
}
