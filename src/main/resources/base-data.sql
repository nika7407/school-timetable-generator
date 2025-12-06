
INSERT INTO subject (name, description) VALUES
('mathematics', 'mathematics'),
('physics', 'physics'),
('chemistry', 'chemistry'),
('biology', 'biology'),
('history', 'history'),
('geography', 'geography'),
('english', 'english'),
('computer science', 'computer science'),
('PE', 'physical education');

INSERT INTO classroom (number) VALUES
(101),
(102),
(103),
(201),
(202),
(203);

INSERT INTO teacher (first_name, last_name, email, subject_id) VALUES
('teacher_pe', 'last_name_pe', 'teacher_pe@mail.com', 9),
('teacher_math', 'last_name_math', 'teacher_math@mail.com', 1),
('teacher_physics', 'last_name_physics', 'teacher_physics@mail.com', 2),
('teacher_cs', 'last_name_cs', 'teacher_cs@mail.com', 8),
('teacher_chemistry', 'last_name_chemistry', 'teacher_chemistry@mail.com', 3),
('teacher_biology', 'last_name_biology', 'teacher_biology@mail.com', 4),
('teacher_history', 'last_name_history', 'teacher_history@mail.com', 5),
('teacher_geography', 'last_name_geography', 'teacher_geography@mail.com', 6),
('teacher_english', 'last_name_english', 'teacher_english@mail.com', 7);