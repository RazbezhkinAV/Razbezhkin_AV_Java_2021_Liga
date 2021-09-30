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
