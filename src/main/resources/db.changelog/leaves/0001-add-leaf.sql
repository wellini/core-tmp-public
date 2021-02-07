CREATE TABLE leaf
(
    id          uuid                NOT NULL,
    type        varchar(40)         NOT NULL,
    title       varchar(512)        NOT NULL,
    order_id    integer             NOT NULL,
    PRIMARY KEY (id)
);