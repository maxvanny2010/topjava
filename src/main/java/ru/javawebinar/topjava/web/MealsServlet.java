package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.service.Logic;
import ru.javawebinar.topjava.service.LogicService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserServlet.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class MealsServlet extends HttpServlet {
    /**
     * field a logic.
     */
    private final Logic logic = LogicService.getInstance();

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {
        this.logic.runAction("meals", req, resp);
    }
}
