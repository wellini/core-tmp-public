CREATE SEQUENCE leaves_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NO CYCLE;

CREATE SEQUENCE modules_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NO CYCLE;

CREATE SEQUENCE courses_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NO CYCLE;

CREATE SEQUENCE users_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NO CYCLE;

CREATE SEQUENCE auth_providers_seq
    MINVALUE 1
    MAXVALUE 9223372036854775807
    NO CYCLE;

DROP VIEW course_with_author_fullname_view;

ALTER TABLE leaves ADD COLUMN id_seq bigint;
ALTER TABLE modules ADD COLUMN id_seq bigint;
ALTER TABLE courses ADD COLUMN id_seq bigint;
ALTER TABLE users ADD COLUMN id_seq bigint;
ALTER TABLE auth_providers ADD COLUMN id_seq bigint;
ALTER TABLE leaves_link ADD COLUMN id_seq bigint;
ALTER TABLE leaves_text ADD COLUMN id_seq bigint;
ALTER TABLE course_affiliations DROP CONSTRAINT fk_user_id;
ALTER TABLE course_affiliations DROP CONSTRAINT fk_course_id;

ALTER TABLE leaves ADD COLUMN module_id_seq bigint;
ALTER TABLE modules ADD COLUMN course_id_seq bigint;
ALTER TABLE courses ADD COLUMN author_id_seq bigint;
ALTER TABLE auth_providers ADD COLUMN user_id_seq bigint;
ALTER TABLE course_affiliations ADD COLUMN user_id_seq bigint;
ALTER TABLE course_affiliations ADD COLUMN course_id_seq bigint;

UPDATE leaves SET id_seq = nextval('leaves_seq');
UPDATE modules SET id_seq = nextval('modules_seq');
UPDATE courses SET id_seq = nextval('courses_seq');
UPDATE auth_providers SET id_seq = nextval('auth_providers_seq');
UPDATE users SET id_seq = nextval('users_seq');

ALTER TABLE leaves_text DROP CONSTRAINT fk_leaf_text_to_leaf;
ALTER TABLE leaves_link DROP CONSTRAINT fk_leaf_link_to_leaf;
ALTER TABLE leaves DROP CONSTRAINT fk_module_id;
ALTER TABLE modules DROP CONSTRAINT fk_course_id;
ALTER TABLE courses DROP CONSTRAINT fk_author_id;
ALTER TABLE auth_providers DROP CONSTRAINT fk_user_id;

UPDATE leaves_text lt SET id_seq = l.id_seq FROM leaves l WHERE l.id = lt.id;
UPDATE leaves_link ll SET id_seq = l.id_seq FROM leaves l WHERE l.id = ll.id;
UPDATE leaves l SET module_id_seq = m.id_seq FROM modules m WHERE l.module_id = m.id;
UPDATE modules m SET course_id_seq = c.id_seq FROM courses c WHERE c.id = m.course_id;
UPDATE courses c SET author_id_seq = u.id_seq FROM users u WHERE u.id = c.author_id;
UPDATE auth_providers ap SET user_id_seq = u.id_seq FROM users u WHERE u.id = ap.user_id;
UPDATE course_affiliations ca SET user_id_seq = u.id_seq FROM users u WHERE u.id = ca.user_id;
UPDATE course_affiliations ca SET course_id_seq = c.id_seq FROM courses c WHERE c.id = ca.course_id;

ALTER TABLE leaves_text DROP CONSTRAINT leaf_text_pkey;
ALTER TABLE leaves_text DROP COLUMN id;
ALTER TABLE leaves_text RENAME COLUMN id_seq TO id;
ALTER TABLE leaves_text ADD PRIMARY KEY (id);

ALTER TABLE leaves_link DROP CONSTRAINT leaf_link_pkey;
ALTER TABLE leaves_link DROP COLUMN id;
ALTER TABLE leaves_link RENAME COLUMN id_seq TO id;
ALTER TABLE leaves_link ADD PRIMARY KEY (id);

ALTER TABLE leaves DROP CONSTRAINT leaf_pkey;
ALTER TABLE leaves DROP COLUMN id;
ALTER TABLE leaves RENAME COLUMN id_seq TO id;
ALTER TABLE leaves ADD PRIMARY KEY (id);
ALTER TABLE leaves DROP COLUMN module_id;
ALTER TABLE leaves RENAME COLUMN module_id_seq TO module_id;

ALTER TABLE modules DROP CONSTRAINT module_pkey;
ALTER TABLE modules DROP COLUMN id;
ALTER TABLE modules RENAME COLUMN id_seq TO id;
ALTER TABLE modules ADD PRIMARY KEY (id);
ALTER TABLE modules DROP COLUMN course_id;
ALTER TABLE modules RENAME COLUMN course_id_seq TO course_id;

ALTER TABLE courses DROP CONSTRAINT course_pkey;
ALTER TABLE courses DROP COLUMN id;
ALTER TABLE courses RENAME COLUMN id_seq TO id;
ALTER TABLE courses ADD PRIMARY KEY (id);
ALTER TABLE courses DROP COLUMN author_id;
ALTER TABLE courses RENAME COLUMN author_id_seq TO author_id;

ALTER TABLE auth_providers DROP CONSTRAINT auth_provider_pkey;
ALTER TABLE auth_providers DROP COLUMN id;
ALTER TABLE auth_providers RENAME COLUMN id_seq TO id;
ALTER TABLE auth_providers ADD PRIMARY KEY (id);
ALTER TABLE auth_providers DROP COLUMN user_id;
ALTER TABLE auth_providers RENAME COLUMN user_id_seq TO user_id;

ALTER TABLE course_affiliations DROP CONSTRAINT course_affiliation_pkey;
ALTER TABLE course_affiliations DROP COLUMN user_id;
ALTER TABLE course_affiliations DROP COLUMN course_id;
ALTER TABLE course_affiliations RENAME COLUMN user_id_seq TO user_id;
ALTER TABLE course_affiliations RENAME COLUMN course_id_seq TO course_id;
ALTER TABLE course_affiliations ADD PRIMARY KEY (course_id, user_id);

ALTER TABLE users DROP CONSTRAINT user_pkey;
ALTER TABLE users DROP COLUMN id;
ALTER TABLE users RENAME COLUMN id_seq TO id;
ALTER TABLE users ADD PRIMARY KEY (id);

ALTER TABLE leaves_text
    ADD CONSTRAINT fk_text_leves_to_leaf
    FOREIGN KEY (id)
    REFERENCES leaves (id);

ALTER TABLE leaves_link
    ADD CONSTRAINT fk_link_leaves_to_leaf
    FOREIGN KEY (id)
    REFERENCES leaves (id);

ALTER TABLE leaves
    ADD CONSTRAINT fk_leaves_to_modules
    FOREIGN KEY (module_id)
    REFERENCES modules (id);

ALTER TABLE modules
    ADD CONSTRAINT fk_modules_to_courses
    FOREIGN KEY (course_id)
    REFERENCES courses (id);

ALTER TABLE courses
    ADD CONSTRAINT fk_courses_to_authors
    FOREIGN KEY (author_id)
    REFERENCES users (id);

ALTER TABLE auth_providers
    ADD CONSTRAINT fk_auth_providers_to_users
    FOREIGN KEY (user_id)
    REFERENCES users (id);

ALTER TABLE course_affiliations
    ADD CONSTRAINT fk_course_affiliations_to_users
    FOREIGN KEY (user_id)
    REFERENCES users (id);

ALTER TABLE course_affiliations
    ADD CONSTRAINT fk_course_affiliations_to_courses
    FOREIGN KEY (course_id)
    REFERENCES courses (id);