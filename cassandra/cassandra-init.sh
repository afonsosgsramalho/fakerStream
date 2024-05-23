# chmod +x cassandra-setup.cql

# echo "Executing CQL script..."
# cqlsh -u cassandra -p cassandra -f cassandra-setup.cql
# echo "CQL script executed."

#!/usr/bin/env bash

until printf "" 2>>/dev/null >>/dev/tcp/cassandra/9042; do
    sleep 5;
    echo "Waiting for cassandra...";
done

echo "Creating keyspace"
cqlsh cassandra -u cassandra -p cassandra -e "CREATE KEYSPACE IF NOT EXISTS spring_cassandra WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};"