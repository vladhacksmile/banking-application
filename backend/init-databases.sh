#!/bin/sh
set -e

psql --username postgres --dbname postgres <<-EOSQL
  CREATE DATABASE app;
  CREATE ROLE app WITH ENCRYPTED PASSWORD 'app' LOGIN;
  GRANT ALL PRIVILEGES ON DATABASE app TO app;
EOSQL

psql --username postgres --dbname app <<-EOSQL
  GRANT ALL ON SCHEMA public TO app;
EOSQL