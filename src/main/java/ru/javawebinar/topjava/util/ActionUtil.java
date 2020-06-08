package ru.javawebinar.topjava.util;

import java.util.Arrays;
import java.util.List;

/**
 * ActionUtil.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public final class ActionUtil {
    /**
     * Constructor.
     */
    private ActionUtil() {
    }

    /**
     * Method to get.
     *
     * @return the first row a table of user store
     */
    public static List<String> firstRow() {
        return Arrays.asList("датa/время", "описание", "калории",
                "редактор", "удалить");
    }

}
