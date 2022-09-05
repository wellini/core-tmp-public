CREATE TABLE auth_provider
(
    id          uuid         NOT NULL PRIMARY KEY,
    provider_id varchar(255) NOT NULL,
    type        varchar(255) NOT NULL,
    token       varchar(255) NOT NULL,
    user_id     uuid         NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "users" (id)
)