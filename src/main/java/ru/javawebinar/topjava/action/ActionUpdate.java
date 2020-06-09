package ru.javawebinar.topjava.action;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * ActionUpdate.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionUpdate extends ActionAbs {
    /**
     * field a logger.
     */
    private static final Logger LOG = getLogger(ActionUpdate.class);

    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws IOException {
        final String id = req.getParameter("id");
        final String dates = req.getParameter("dates");
        final String desc = req.getParameter("desc").trim();
        final String calories = req.getParameter("calories");
        final int idx = Integer.parseInt(id);
        final Optional<Meal> byId = this.getStore().findById(idx);
        if (!byId.isPresent()) {
            resp.sendRedirect("/404");
            return;
        }
        final Meal meals = byId.get();
        try {
            final LocalDateTime dateTime = LocalDateTime.parse(dates);
            meals.setDateTime(dateTime);
            meals.setDescription(desc);
            final int calorie = Integer.parseInt(calories);
            meals.setCalories(calorie);
            final Meal update = this.getStore().update(meals);
            LOG.info("обновлён:" + update);
            resp.sendRedirect("/meals");
        } catch (final Exception e) {
            resp.sendRedirect("/404");
        }
    }
}
