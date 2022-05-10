CREATE OR REPLACE VIEW course_with_author_fullname_view AS
SELECT c.id, c.title, c.author_id, c.cover_theme, c.cover_url, u.fullname
FROM course c left join users u on u.id = c.author_id
