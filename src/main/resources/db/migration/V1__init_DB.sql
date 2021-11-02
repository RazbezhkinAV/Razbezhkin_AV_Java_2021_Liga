create table ticket
(
    id           uuid primary key,
    person_id    uuid,
    time         varchar(255),
    ticket_free  boolean,
    visit_person boolean,
    confirm      boolean
);
create table role
(
    id   uuid primary key,
    name varchar(255)
);
create table person
(
    id           uuid primary key,
    login        varchar(50),
    password     varchar(200),
    name         varchar(255),
    surname      varchar(255),
    phone_number varchar(12),
    email        varchar(255),
    ticket_id    uuid,
    role_id      uuid,
    foreign key (ticket_id) references ticket (id),
    foreign key (role_id) references role (id)
);




