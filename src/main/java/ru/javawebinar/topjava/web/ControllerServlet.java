package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.service.Logic;
import ru.javawebinar.topjava.service.LogicService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ControllerServlet.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ControllerServlet extends HttpServlet {
    /**
     * field a logic.
     */
    private final Logic logic = LogicService.getInstance();

    @Override
    protected final void doPost(final HttpServletRequest req,
                                final HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final String action = req.getParameter("action");
        this.logic.runAction(action, req, resp);
    }
}
