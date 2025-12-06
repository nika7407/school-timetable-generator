create table classroom (
id BIGSERIAL PRIMARY KEY,
number int not null
)

CREATE TABLE subject (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);


CREATE TABLE teacher (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    subject_id BIGINT NOT NULL REFERENCES subject(id) ON DELETE CASCADE
);


CREATE TYPE day_of_week AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
);


CREATE TABLE timetable (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    period_number INT NOT NULL,
    day_of_week day_of_week NOT NULL,

    subject_id BIGINT NOT NULL REFERENCES subject(id) ON DELETE CASCADE,
    teacher_id BIGINT NOT NULL REFERENCES teacher(id) ON DELETE CASCADE,
    classroom_id BIGINT NOT NULL REFERENCES classroom(id) ON DELETE CASCADE
);

