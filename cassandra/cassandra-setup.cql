CREATE KEYSPACE IF NOT EXISTS fakerPerson 
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE fakerPerson;

CREATE TABLE IF NOT EXISTS person(
    uuid uuid,
    name text,
    address text,
    email text,
    phone_number int,
    job text,
    company text,
    birthdate timestamp,
    credit_card_number int,
    username text,
    ingest_timestamp timestamp,
    PRIMARY KEY((email), ingest_timestamp)
)
WITH CLUSTERING ORDER BY (ingest_timestamp DESC);

CREATE INDEX IF NOT EXISTS ON person (uuid);