CREATE DATABASE if not exists fpitest;

DROP TABLE if exists fpi_spring1;
DROP TABLE if exists fpi_springcontener;
DROP TABLE if exists fpi_springitem;
DROP TABLE if exists fpi_springcontent;
DROP TABLE if exists fpi_springlist;
DROP TABLE if exists fpi_springsubs;
DROP TABLE if exists fpi_springtext;

CREATE TABLE if not exists  fpi_spring1
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    name text

) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springcontener
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    contenerphoto VARCHAR(256),
    contenerdate VARCHAR(256),
    contenername VARCHAR(256),
    contenersubname VARCHAR(256),

    conteneritems int(11),
    FOREIGN KEY(conteneritems) references fpi_springitem(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springitem
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    histkind VARCHAR(256),
    histdesc VARCHAR(256),
    histduration VARCHAR(256),
    histtitle VARCHAR(256),
    histextra VARCHAR(256),

    contentitems int(11),
    FOREIGN KEY(contentitems) references fpi_springcontent(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springcontent
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    entrydesc VARCHAR(256),

    contentlist int(11),
    FOREIGN KEY(contentlist) references fpi_springsubs(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springsubs
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    listtext int(11),
    FOREIGN KEY(listtext) references fpi_springtext(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  fpi_springtext
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    data TEXT
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;
