FROM grafana/grafana:latest

COPY ./dashboards /var/lib/grafana/dashboards
COPY ./provisioning /etc/grafana/provisioning
COPY ./grafana.ini /etc/grafana/grafana.ini

ENV GF_LOG_LEVEL=info 
COPY ./plugins/hadesarchitect-cassandra-datasource /var/lib/grafana/plugins/hadesarchitect-cassandra-datasource
