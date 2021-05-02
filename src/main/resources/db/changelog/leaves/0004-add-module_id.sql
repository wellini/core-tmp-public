ALTER TABLE leaf ADD COLUMN module_id uuid NOT NULL;
ALTER TABLE leaf ADD CONSTRAINT fk_module_id FOREIGN KEY (module_id) REFERENCES module (id);