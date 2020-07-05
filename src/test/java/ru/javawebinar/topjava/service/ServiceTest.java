package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * ServiceTest.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/5/2020
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class ServiceTest {
    private static final Logger log = getLogger("result");
    private static final StringBuilder results = new StringBuilder();
    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(),
                    TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + "ms\n");
        }
    };

    @Autowired
    private CacheManager cacheManager;

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

    @Before
    public void setUp() {
        Objects.requireNonNull(this.cacheManager.getCache("users")).clear();
    }

    public abstract void create();

    public abstract void get();

    public abstract void update();

    public abstract void delete();

    public abstract void deletedNotFound();

}
