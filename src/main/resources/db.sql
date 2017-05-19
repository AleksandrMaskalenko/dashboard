CREATE DATABASE IF NOT EXISTS dashboard;

USE dashboard;

DROP TABLE IF EXISTS `event`;
DROP TABLE IF EXISTS `test_case`;

CREATE TABLE test_case (

  uuid VARCHAR(255)             NOT NULL PRIMARY KEY,
  requirement VARCHAR(255)      NOT NULL,
  component VARCHAR(255)        NOT NULL,
  title VARCHAR(255)            NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE event (
  uuid VARCHAR(255)             NOT NULL PRIMARY KEY,
  time VARCHAR(255)             NOT NULL,
  event VARCHAR(255)            NOT NULL,
  test_case VARCHAR(255)        NOT NULL,
  dateTime DATETIME,

  FOREIGN KEY (test_case) REFERENCES test_case(uuid)
)
  ENGINE = InnoDB;