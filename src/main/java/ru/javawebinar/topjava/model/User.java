package ru.javawebinar.topjava.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
import java.util.StringJoiner;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/14/2020
 */
public class User extends AbstractNamedEntity {

    private final Set<Role> roles;
    private String email;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private int caloriesPerDay = DEFAULT_CALORIES_PER_DAY;

    public User(Integer id, String name, String email,
                String password, Role role, Role... roles) {
        this(id, name, email, password, DEFAULT_CALORIES_PER_DAY,
                true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password,
                int caloriesPerDay, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                User.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("name='" + this.name + "'")
                .add("email='" + this.email + "'")
                .add("enabled=" + this.enabled)
                .add("registered=" + this.registered)
                .add("roles=" + this.roles)
                .add("caloriesPerDay=" + this.caloriesPerDay)
                .toString();
    }
}
