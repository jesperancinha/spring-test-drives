create table if not exists car
(
    id                SERIAL PRIMARY KEY,
    model             varchar(255),
    brand             varchar(255),
    car_year          int,
    movie_appearances varchar(255)
);