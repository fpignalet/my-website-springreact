CREATE DATABASE fpitest;

CREATE TABLE if not exists fpi_areasofexpertise
(
    description text
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_info
(
    raisonsociale text,
    addresse      text,
    mail          text,
    telephone     text
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_mainskills
(
    description text,
    niveau      text
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_testtable1
(
    testtable1field1 int(11) NOT NULL,
    testtable1field2 int(11) NOT NULL,
    testtable1field3 text    NOT NULL
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_yetanothertable
(
    description text
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;
