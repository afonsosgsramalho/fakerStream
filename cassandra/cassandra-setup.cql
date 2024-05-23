CREATE KEYSPACE IF NOT EXISTS fakerPerson 
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE fakerPerson;

CREATE TABLE IF NOT EXISTS person(
    uuid uuid,
    id text,
    name text,
    author text,
    body text,
    subreddit text,
    upvotes int,
    downvotes int,
    over_18 boolean,
    permalink text,
    api_timestamp timestamp,
    ingest_timestamp timestamp,
    sentiment_score float,
    PRIMARY KEY((subreddit), api_timestamp)
)
WITH CLUSTERING ORDER BY (api_timestamp DESC);

CREATE INDEX IF NOT EXISTS ON person (uuid);