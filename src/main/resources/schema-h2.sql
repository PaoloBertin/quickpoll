DROP TABLE IF EXISTS poll_option;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS polls;

CREATE TABLE IF NOT EXISTS options(
    id BIGINT NOT NULL AUTO_INCREMENT,
    value VARCHAR(255),

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS votes(
    id BIGINT NOT NULL AUTO_INCREMENT,
    option_id BIGINT,

    PRIMARY KEY(id),
    CONSTRAINT vote_fk_01 FOREIGN KEY (option_id) REFERENCES options(id)
);

CREATE TABLE IF NOT EXISTS polls(
    id BIGINT NOT NULL AUTO_INCREMENT,
    question VARCHAR(255),

    PRIMARY KEY(id)
);

CREATE TABLE poll_option(
    poll_id BIGINT NOT NULL ,
    option_id BIGINT NOT NULL,

    CONSTRAINT poll_option_uk_01 UNIQUE (option_id),
    CONSTRAINT poll_option_fk_01 FOREIGN KEY (option_id) REFERENCES options(id),
    CONSTRAINT poll_option_fk_02 FOREIGN KEY (poll_id) REFERENCES polls(id)
)
