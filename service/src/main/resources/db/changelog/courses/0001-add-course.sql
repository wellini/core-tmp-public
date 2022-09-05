CREATE TABLE course (
    id              uuid            NOT NULL PRIMARY KEY,
    title           varchar(255)    NOT NULL,
    author_id       uuid            NOT NULL,
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES "user" (id)
)