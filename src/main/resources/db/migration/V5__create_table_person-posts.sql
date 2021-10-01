create table person_posts(
    person_id integer,
    posts_id integer,
    foreign key (person_id) references person (id) on delete cascade,
    foreign key(posts_id) references posts(id)
);