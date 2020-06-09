package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.service.Logic;
import ru.javawebinar.topjava.service.LogicService;

import javax.servlet.ServletConfig;
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
 * @since 6/9/2020
 */
public class UserServlet extends HttpServlet {
    /**
     * field a logic.
     */
    private Logic logic;

    @Override
    public final void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.logic = LogicService.getInstance();
    }

    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {
        this.logic.runAction("users", req, resp);
    }
}
