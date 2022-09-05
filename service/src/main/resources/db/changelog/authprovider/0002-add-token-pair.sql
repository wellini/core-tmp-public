ALTER TABLE auth_providers RENAME COLUMN provider_id TO external_id;
ALTER TABLE auth_providers RENAME COLUMN token TO external_refresh_token;
ALTER TABLE auth_providers ADD COLUMN external_access_token varchar(255);