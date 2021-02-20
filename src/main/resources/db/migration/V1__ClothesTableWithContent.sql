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
('Beanie hat', 'hat', -1, true, false),
('Wool cap', 'hat', -10, true, false),
('Fur hat', 'hat', -20, true, false),
('Groovy with earflaps', 'hat', -40, true, false),

('Tank top', 'body', 30, false, false),
('T-shirt', 'body', 22, false, false),
('Hood', 'body', 20, false, true),
('Hoodie', 'body', 14, false, false),
('Vest', 'body', 8, false, true),
('Trench coat', 'body', 5, false, true),
('Winter coat', 'body', -5, false, true),
('Oilskin', 'body', -10, true, true),
('Puffer jacket', 'body', -30, true, true),
('Fur coat', 'body', -40, true, true)
