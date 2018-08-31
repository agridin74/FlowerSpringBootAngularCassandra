
# SpringBoot Rest Angular Cassandra

# $> docker pull datastax/dse-server:latest
# docker run -e DS_LICENSE=accept -p 9042:9042 --name my-dse -d datastax/dse-server -g -s -k
# docker exec -it my-dse bash
#dse@22e59e48a86a:~$ cqlsh
#Connected to Test Cluster at 127.0.0.1:9042.
#cqlsh> create keyspace mysampleapp with replication={'class':'SimpleStrategy','replication_factor':1};
#cqlsh> use mysampleapp ;
#cqlsh:mysampleapp> create table flower(
#               ... id timeuuid primary key,
#               ... name text,
#               ... price float,
#               ... active boolean
#               ... );



