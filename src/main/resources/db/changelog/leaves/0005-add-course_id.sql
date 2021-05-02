ALTER TABLE leaf ADD COLUMN course_id uuid NOT NULL;
ALTER TABLE leaf ADD CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course (id);