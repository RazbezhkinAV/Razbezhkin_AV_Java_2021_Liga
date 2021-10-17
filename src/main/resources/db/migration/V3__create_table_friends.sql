create table friends(
    person_id uuid,
    friends_id uuid,
    foreign key (person_id) references person (id),
    foreign  key (friends_id) references person (id),
    primary key(person_id,friends_id)
);