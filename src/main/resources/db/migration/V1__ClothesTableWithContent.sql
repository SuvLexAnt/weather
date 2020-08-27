create table if not exists clothes
(
    id serial not null
        constraint clothes_pk
            primary key,
    name varchar(50) not null,
    type varchar(20) not null,
    temperature int,
    snow bool not null,
    rain bool not null,
    image bytea
);

insert into clothes (name, type, temperature, snow, rain)
values
('Baseball cap', 'hat', 30, false, false),
('Umbrella cap', 'hat', 20, false, true),
('Fur hat', 'hat', -15, true, false),

('T-shirt', 'body', 22, false, false),
('Tank top', 'body', 30, false, false),
('Hoodie', 'body', 14, false, false),
('Trench coat', 'body', 8, false, true),
('Oilskin', 'body', -10, true, true),
('Hood', 'body', 20, false, true)