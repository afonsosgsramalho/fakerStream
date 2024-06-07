# Fake Streaming Data Pipeline

The project is a streaming data pipeline based on generation of fake data.

## Architecture



The diagram shown above provides a detailed explanation into the pipeline's architecture.

All applications are containerized into **Docker** containers.

**Data ingestion layer** - a containerized **Python** application produces fake data and ingests messages into a Kafka broker.

**Message broker layer** - messages from fakerProducer are consumed by **Kafka** broker, which is located in kafka-service pod and has **Kafdrop** service as a sidecar ambassador container for Kafka.

**Stream processing layer** - a **Spark** cluster is deployed. then a **Scala** application called **StreamProcessor** is submitted into Spark cluster manager, that delegates a worker for it. This application connects to Kafka broker to retrieve messages, transform them using Spark Structured Streaming, and loads into Cassandra tables.

**Serving database layer** - a **Cassandra** database stores & persists data from Spark jobs. Upon launching, the **cassandra-setup.cql** script runs to create keyspace & tables.

**Visualization layer** - **Grafana** connects to Cassandra database using HadesArchitect-Cassandra-Plugin and serves visualized data to users as in example of Dashboard. This Dashboard only exists as a way to connect to test the connection to grafana (it only displays a table with the data).
