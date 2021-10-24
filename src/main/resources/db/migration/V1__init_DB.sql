create table ticket
(
    id           serial primary key,
    person_id    uuid,
    time         varchar(255),
    ticket_free  boolean,
    visit_person boolean,
    confirm      boolean
);
create table role
(
    id   serial primary key,
    name varchar(255)
);
create table person
(
    id           uuid         not null primary key,
    login        varchar(50)  not null unique,
    password     varchar(200) not null,
    name         varchar(255) not null,
    surname      varchar(255),
    phone_number varchar(12)  not null unique,
    email        varchar(255),
    ticket_id    integer,
    role_id      integer,
    foreign key (ticket_id) references ticket (id),
    foreign key (role_id) references role (id)
);




