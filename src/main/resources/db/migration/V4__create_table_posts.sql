create table posts(
    id uuid not null primary key,
    content varchar(2048),
    person_id uuid,
    foreign key (person_id) references person (id)
);