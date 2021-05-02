CREATE TABLE course_affiliation (
    id              uuid        NOT NULL PRIMARY KEY,
    user_id         uuid        NOT NULL,
    course_id       uuid        NOT NULL,
    type            varchar(255),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE UNIQUE INDEX idx_course_affiliation_course_id_user_id ON course_affiliation (course_id, user_id);