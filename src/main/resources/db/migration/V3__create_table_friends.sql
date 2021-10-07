create table friends(
    person_id integer,
    friends_id integer,
    foreign key (person_id) references person (id) on delete cascade,
    foreign  key (friends_id) references person (id),
    primary key(person_id,friends_id)
);