CREATE DATABASE roadmaps_db;
CREATE ROLE roadmaps WITH LOGIN roadmaps PASSWORD 'roadmaps';
CREATE SCHEMA core AUTHORIZATION roadmaps;
grant create on schema core to roadmaps;
grant usage on schema core to roadmaps;
SET search_path TO roadmaps,public;
