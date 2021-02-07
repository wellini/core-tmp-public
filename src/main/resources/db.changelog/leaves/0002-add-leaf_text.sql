CREATE TABLE leaf_text
(
    id                   uuid           NOT NULL,
    text                 text,
    PRIMARY KEY (id),
    CONSTRAINT fk_leaf_text_to_leaf FOREIGN KEY (id) REFERENCES leaf(id)
);