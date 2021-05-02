CREATE TABLE module (
    id          uuid            NOT NULL PRIMARY KEY,
    title       varchar(255)    NOT NULL,
    order_id    integer         NOT NULL,
    course_id   uuid            NOT NULL,
    CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course (id)
)