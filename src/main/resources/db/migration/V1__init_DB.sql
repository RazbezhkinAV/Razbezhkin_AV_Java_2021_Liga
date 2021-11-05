create table ticket
(
    id           uuid primary key,
    person_id    uuid,
    time         varchar,
    ticket_free  boolean,
    visit_person boolean,
    confirm      boolean
);
create table role
(
    id   uuid primary key,
    name varchar
);
create table person
(
    id           uuid primary key,
    login        varchar,
    password     varchar,
    name         varchar,
    surname      varchar,
    phone_number varchar,
    email        varchar,
    ticket_id    uuid,
    role_id      uuid,
    foreign key (ticket_id) references ticket (id),
    foreign key (role_id) references role (id)
);




