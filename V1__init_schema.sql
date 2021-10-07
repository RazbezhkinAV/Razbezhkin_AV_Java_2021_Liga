create table school(
    id integer not null primary key,
    name varchar(255),
    address varchar (255)
);

create table teacher(
    id integer not null primary key,
    name varchar(255),
    age integer,
    sex varchar(10),
    school_id integer,
    foreign key (school_id) references school(id)
);

create table lesson(
    id integer not null primary key,
    name varchar(255)
);

create table student(
    id integer not null primary key,
    name varchar(255),
    age integer  not null,
    sex varchar(10),
    school_id integer,
    foreign key (school_id) references school(id)
);

create table teacher_lesson(
  teacher_id  integer not null,
  lesson_id integer not null,
  foreign key (teacher_id) references teacher(id),
  foreign key (lesson_id) references lesson(id),
  primary key (teacher_id,lesson_id)
);

create table student_lesson(
  student_id  integer not null,
  lesson_id integer not null,
  foreign key (student_id) references student(id),
  foreign key (lesson_id) references lesson(id),
  primary key (student_id,lesson_id)
)
