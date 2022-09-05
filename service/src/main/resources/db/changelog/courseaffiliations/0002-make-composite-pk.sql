ALTER TABLE course_affiliation DROP CONSTRAINT course_affiliation_pkey;
ALTER TABLE course_affiliation DROP COLUMN id;
ALTER TABLE course_affiliation ADD PRIMARY KEY (course_id, user_id);