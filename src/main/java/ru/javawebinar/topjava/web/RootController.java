package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    private final UserService userService;
    private final MealService mealService;

    public RootController(final UserService userService, final MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    // обычный вход при старте спринга.
    // ПРИ-> @GetMapping("/")
    // при слеше("/") без index(любое слово), не могу ни зайти со старта, ни потом перейти на root
    // пишет нет маппинга на GET "/topjava/"
    // картинки настроек и вид ошибки приложил в слаке. название GetMapping
    @GetMapping("/index")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:meals";
    }

    @GetMapping("/meals")
    public String getMeals(Model model) {
        model.addAttribute("meals",
                MealsUtil.getTos(mealService.getAll(SecurityUtil.authUserId()),
                        SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }
}
