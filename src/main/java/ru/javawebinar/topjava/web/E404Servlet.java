package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.service.Logic;
import ru.javawebinar.topjava.service.LogicService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * E404Servlet.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/8/2020
 */
public class E404Servlet extends HttpServlet {
    /**
     * field a logic.
     */
    private final Logic logic = LogicService.getInstance();

    @Override
    public final void doGet(final HttpServletRequest req,
                            final HttpServletResponse resp)
            throws ServletException, IOException {
        this.logic.runAction("404", req, resp);
    }
}
