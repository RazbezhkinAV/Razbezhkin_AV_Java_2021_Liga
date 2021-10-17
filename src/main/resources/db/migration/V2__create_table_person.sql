create table person(
    id uuid not null primary key,
    name varchar(40),
    surname varchar(40),
    school_id uuid,
    foreign key (school_id) references school (id) on delete set null
);

INSERT INTO person (id,name,surname,school_id)
VALUES ('b64a7d0e-2f2e-11ec-8d3d-0242ac130003','Sasha','Petrov','c9f466df-49d9-4542-9f8c-642bed0e8ef1');

INSERT INTO person (id,name,surname,school_id)
VALUES ('b64a7f52-2f2e-11ec-8d3d-0242ac130003','Masha','Ivanova','c9f466df-49d9-4542-9f8c-642bed0e8ef1');

INSERT INTO person (id,name,surname,school_id)
VALUES ('b64a85b0-2f2e-11ec-8d3d-0242ac130003','Dasha','Sidorov','c9f466df-49d9-4542-9f8c-642bed0e8ef1');

INSERT INTO person (id,name,surname,school_id)
VALUES ('b64a86dc-2f2e-11ec-8d3d-0242ac130003','Petya','Vasechkin','c9f466df-49d9-4542-9f8c-642bed0e8ef1');