CREATE TABLE leaf_link
(
    id                   uuid           NOT NULL,
    link                 text,
    PRIMARY KEY (id),
    CONSTRAINT fk_leaf_link_to_leaf FOREIGN KEY (id) REFERENCES leaf(id)
);