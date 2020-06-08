package ru.javawebinar.topjava.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logic.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public interface Logic {
    /**
     * Method to run action.
     *
     * @param action a action
     * @param req    a request
     * @param resp   a response
     * @throws ServletException servlet exception
     * @throws IOException      io exception
     */
    void runAction(String action,
                   HttpServletRequest req,
                   HttpServletResponse resp)
            throws ServletException, IOException;
}
