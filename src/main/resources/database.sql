CREATE DATABASE if not exists fpitest;

# DROP TABLE if exists fpi_spring1;
# DROP TABLE if exists fpi_springcontener;

CREATE TABLE if not exists  fpi_spring1
(
    id int(11),
    name text
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springcontener
(
    id int(11),
    toto int(11),
    name text
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;
