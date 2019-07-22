CREATE DATABASE if not exists fpitest;

DROP TABLE if exists dbhist_sub_data;
DROP TABLE if exists springtest;
DROP TABLE if exists contact;
DROP TABLE if exists token;
DROP TABLE if exists histcontener;
DROP TABLE if exists histitem;
DROP TABLE if exists histcontent;
DROP TABLE if exists histlist;
DROP TABLE if exists histsubs;
DROP TABLE if exists histtext;

CREATE TABLE if not exists  springtest
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    name text

) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  contact
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    vorname VARCHAR(256),
    nachname VARCHAR(256),
    emailadresse VARCHAR(256),
    enabled BOOLEAN

) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  token
(
    token_id int(11) NOT NULL auto_increment PRIMARY KEY,

    confirmation_token VARCHAR(256),

    user int(11),
    FOREIGN KEY(user) references contact(id)

) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  histcontener
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    contenerphoto VARCHAR(256),
    contenerdate VARCHAR(256),
    contenername VARCHAR(256),
    contenersubname VARCHAR(256),

    conteneritems int(11),
    FOREIGN KEY(conteneritems) references histitem(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  histitem
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    histkind VARCHAR(256),
    histdesc VARCHAR(256),
    histduration VARCHAR(256),
    histtitle VARCHAR(256),
    histextra VARCHAR(256),

    contentitems int(11),
    FOREIGN KEY(contentitems) references histcontent(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  histcontent
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    entrydesc VARCHAR(256),

    contentlist int(11),
    FOREIGN KEY(contentlist) references histsubs(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  histsubs
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    listtext int(11),
    FOREIGN KEY(listtext) references histtext(id)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;

CREATE TABLE if not exists  histtext
(
    id int(11) NOT NULL auto_increment PRIMARY KEY,

    data TEXT
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1;
