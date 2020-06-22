CREATE TABLE IF NOT EXISTS meals
(
    id          serial primary key not null,
    datetime    timestamp          NOT NULL,
    description varchar(2000)      NOT NULL,
    calories    integer            NOT NULL,
    user_id     int4               NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
