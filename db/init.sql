CREATE DATABASE roadmaps_db;
CREATE ROLE roadmaps WITH LOGIN roadmaps PASSWORD 'roadmaps';
CREATE SCHEMA roadmaps AUTHORIZATION roadmaps;
grant create on schema roadmaps to roadmaps;
grant usage on schema roadmaps to roadmaps;
SET search_path TO roadmaps,public;
