CREATE DATABASE if not exists fpitest;

DROP TABLE if exists fpi_spring1;
DROP TABLE if exists fpi_springcontener;
DROP TABLE if exists fpi_springitem;
DROP TABLE if exists fpi_springcontent;
DROP TABLE if exists fpi_springlist;
DROP TABLE if exists fpi_springsubs;

CREATE TABLE if not exists  fpi_spring1
(
    id int(11),
    name text
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springcontener
(
    id int(11),
    contenerphoto VARCHAR(256),
    contenerdate VARCHAR(256),
    contenername VARCHAR(256),
    contenersubname VARCHAR(256)
#     conteneritems, refd by parentid in springitem
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springitem
(
    id int(11),
    parentid int(11),
    histkind VARCHAR(256),
    histdesc VARCHAR(256),
    histduration VARCHAR(256),
    histtitle VARCHAR(256),
    histextra VARCHAR(256)
#     contentitems, refd by parentid in springcontent
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springcontent
(
    id int(11),
    parentid int(11),
    entrydesc VARCHAR(256)
#     contentlist, refd by parentid in fpi_springsubs
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springsubs
(
    id int(11),
    parentid int(11),
    data VARCHAR(256)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;
