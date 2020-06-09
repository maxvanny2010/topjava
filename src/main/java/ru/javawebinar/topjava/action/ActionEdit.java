package ru.javawebinar.topjava.action;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * ActionEdit.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionEdit extends ActionAbs {
    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            final String id = req.getParameter("id");
            final int parseInt = Integer.parseInt(id);
            final Optional<Meal> meal = getStore().findById(parseInt);
            meal.ifPresent(value -> req.setAttribute("meal", value));
        } catch (RuntimeException e) {
            resp.sendRedirect("/404");
            return;
        }
        final String path = "/WEB-INF/jsp/edit.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
