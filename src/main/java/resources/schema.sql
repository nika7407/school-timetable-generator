CREATE TABLE IF NOT EXISTS teachers (
  ID SERIAL PRIMARY KEY,
  first_name VARCHAR(20),
  last_name VARCHAR(50),
  email VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS subjects (
  ID SERIAL PRIMARY KEY,
  name VARCHAR(30) UNIQUE,
  description VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS classrooms (
  ID SERIAL PRIMARY KEY,
  number INTEGER UNIQUE,
  isLab BOOLEAN
);

CREATE TABLE IF NOT EXISTS teacher_subjects (
  teacher_id INTEGER REFERENCES teachers(ID),
  subject_id INTEGER REFERENCES subjects(ID),
  PRIMARY KEY (teacher_id, subject_id)
);

CREATE TABLE IF NOT EXISTS subject_classroom (
  subject_id INTEGER REFERENCES subjects(ID),
  classroom_id INTEGER REFERENCES classrooms(ID),
  PRIMARY KEY (subject_id, classroom_id)
);


CREATE TABLE IF NOT EXISTS timetable (
    ID SERIAL PRIMARY KEY,
    date DATE,
    subject_id INTEGER NOT NULL REFERENCES subjects(ID) ON DELETE CASCADE,
    teacher_id INTEGER NOT NULL REFERENCES teachers(ID) ON DELETE CASCADE,
    classroom_id INTEGER REFERENCES classrooms(ID) ON DELETE SET NULL,
    day_of_week VARCHAR(10) NOT NULL CHECK (day_of_week IN (
        'MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY'
    )),
    period_number INTEGER NOT NULL CHECK (period_number BETWEEN 1 AND 10)
);