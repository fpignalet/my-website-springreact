# my-website-springreact
Porting my-website-raw to Spring / React

## This software 
is developped on Linux Ubuntu Bionic, with Jetbrains IntelliJIdea & CLion.
it is still a draft. 
My current professional website is 
- http://www.pignalet.de
- https://github.com/fpignalet/my-website-raw

------------------------------------------------------------------
Also...
What is important here?
- everything's begin with files
    - [pom.xml](pom.xml)
        - it prepares spring application material, getting maven plugins through dependencies to allow extensive use of spring packages
        - files called by [frontend-maven-plugin](https://github.com/eirslett/frontend-maven-plugin)
            ```
                <execution>
                    <id>install node and npm</id>
                    <goals>
                        <goal>install-node-and-npm</goal>
                    </goals>
                    <configuration>
                        <nodeVersion>v10.11.0</nodeVersion>
                        <npmVersion>6.4.1</npmVersion>
                    </configuration>
                </execution>
                <execution>
                    <id>npm install</id>
                    <goals>
                        <goal>npm</goal>
                    </goals>
                    <configuration>
                        <arguments>install</arguments>
                    </configuration>
                </execution>
                <execution>
                    <id>webpack build</id>
                    <goals>
                        <goal>webpack</goal>
                    </goals>
                </execution>
            ```
            - [package.json](package.json)
                - it prepares frontend resources, same as above with npm packages
            - [webpack.config.js](webpack.config.js)
                - it compiles / transpiles the content of js directory to get production source code which will be used in GUI html fragments

    - [Dockerfile](Dockerfile)
        - prepares the final deployment as a container
    
    - Using the above material, here's what it's possible todo:
    
        * (preparing environment) INSTALL mysql:        
            ```
            - sudo apt-get install mysql-server
            - sudo ufw allow mysql
            - systemctl start mysql
            - systemctl enable mysql
            - mysql -u root -p 
            - {password choose during installation}
            - ... {sql commands}
            ```
          
        * (preparing environment) INSTALL nodejs / npm:        
            ```
            - curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -
            - sudo apt install nodejs
            ```
          
        * (preparing environment) INSTALL redis:        
            ```
            - sudo apt-get install redis-server
            - sudo systemctl enable redis-server.service
            - ...
            ```
        
        * GENERATE AND RUN APPLICATION:        
            Open the project / import the pom in IntelliJIdea, 
            ```
            - use Maven target to compile the application
                - compile, package or install. this will generate the content of the target folder.
            - then 
                - add "-Djava.library.path=./target/libs" to IntelliJ Run/Debug VMOptions configurations
                - use Run/Debug configurations
            ```
        
            Otherwise, open a terminal and execute:
            ```ruby
            mvn clean compile
            mvn package
            mvn install
            or mvn install -Dmaven.test.skip=true
            then:
            mvn spring-boot:run -Drun.jvmArguments="-D$LD_LIBRARY_PATH:./target/libs"
            or:
            sudo docker build -t my-website-springreact .
            sudo docker run --network="host" -t my-website-springreact
            ```
            
        * GENERATE AND RUN EXTERNAL FRONTEND:
            open a terminal and execute:
            ```ruby
            cd external-react-frontend
            npm install
            npm run-script build
            then:
            DEBUG=canvas-game-bootstrap:* npm run-script start
            or:
            npm run-script start
            ```
        
        * DEPLOY THE APPLICATION ON LOCALHOST DEFAULT PORT 80:
            open a terminal and execute:
            ```
            a2enmod proxy
            a2enmod proxy_http   
            
            cd /etc/apache2/sites-enabled
            sudo nano 000-default.conf
            
            Edit file:
            <VIRTUALHOST *:80>
            
                # ...
            
                ProxyPreserveHost On
                ProxyPass / http://localhost:8080/
            </VIRTUALHOST>
            ```
        
- the Spring app tree is
    - [external-react-frontend](external-react-frontend)
        - a standard NodeJS / Javascript / React application which uses 
            - [axios](https://github.com/axios/axios) to connect to backend
            - [React JS](https://reactjs.org/) to display GUI
        - see [README.md](external-react-frontend/README.md)
    - [src](src)
        - [main](src/main)
            - [C](src/main/c)
                - [Makefile](src/main/c/Makefile)
                - [impl](src/main/c/impl)
                    - contains:
                        - the ExtFacade which implements the entry functions required by JNI. 
                        - The JNI interface generation is done when generating application. The [pom.xml](pom.xml) file refers to an [exec-maven-plugin](https://www.mojohaus.org/exec-maven-plugin/) which is configured to use [native.sh](src/main/sh/native.sh).
                        - Theses functions respects [ExtFacade](src/main/java/com/core/ext/ExtFacade.java) interface
            - [java](src/main/java)
                - [com](src/main/java/com)
                    - spring boot app implementation
                        - [core](src/main/java/com/core)
                            - [async](src/main/java/com/core/async)
                            - [ctrl](src/main/java/com/core/ctrl)
                            - [data](src/main/java/com/core/data)
                            - [ejb](src/main/java/com/core/ejb)
                            - [eng](src/main/java/com/core/eng)
                            - [ext](src/main/java/com/core/ext)
                            - [utils](src/main/java/com/core/utils)
            - [js](src/main/js)
                - [angular](src/main/js/angular)
                    - frontend angular applications
                - [jquery](src/main/js/jquery)
                    - frontend scripts using jQuery
                - [react](src/main/js/react)
                    - frontend react components
            - [resources](src/main/resources)
                - [static](src/main/resources/static)
                    - [built](src/main/resources/static/built)
                        - frontend transpiled javascript modules
                        - these transpiled files are generated using [webpack.config.js](webpack.config.js) from the javascripts sources in [js](src/main/js)
                    - [data](src/main/resources/static/data)
                        - static resources. Mostly JSON files
                    - [images](src/main/resources/static/images)
                        - static resources. For example images referenced in CV and BLOG pages
                    - [style](src/main/resources/static/style)
                        - all the useful stylesheets
                        - this web app uses [Spectre css](https://picturepan2.github.io/spectre/index.html)
                - [templates](src/main/resources/templates)
                    - this web app uses thymeleaf as frontend template engine
                    - all the "frontend_..." html pages and the fragments below:
                        - can refer to data provided by model through this kind of notation [${name_of_entity}]
                        - the model needs to update the same [name_of_entity] on his side. In this application all the @Service components can perform
                            ```
                            model.addAttribute({name_of_entity}, {value}});
                            ```
                        - the available [name_of_entity] are listed in [com.core.eng.EEngModelItems](src/main/java/com/core/eng/EEngModelItems.java)
                    - [fragments](src/main/resources/templates/fragments)
                        - here are the thymeleaf fragments referenced in html pages above
                - [application.properties](src/main/resources/application.properties)
                    - contains the settings for spring boot app
                    - [HELP.md](HELP.md) can be useful to improve this file
                - [log4j.properties](src/main/resources/log4j.properties)
                    - standard log4j settings file 
                - [schema.sql](src/main/resources/database.sql)
                    - will automaticaly be loaded during app startup
                        ```
                        ...
                        2019-07-31 09:40:00.329  INFO 1838 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate Core {5.3.7.Final}
                        2019-07-31 09:40:00.330  INFO 1838 --- [  restartedMain] org.hibernate.cfg.Environment            : HHH000206: hibernate.properties not found
                        2019-07-31 09:40:00.556  INFO 1838 --- [  restartedMain] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
                        2019-07-31 09:40:00.747  INFO 1838 --- [  restartedMain] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL5Dialect
                        Hibernate: drop table if exists contact
                        Hibernate: drop table if exists hibernate_sequence
                        Hibernate: drop table if exists histcontener
                        Hibernate: drop table if exists histcontent
                        Hibernate: drop table if exists histitem
                        Hibernate: drop table if exists histsubs
                        Hibernate: drop table if exists histtext
                        Hibernate: drop table if exists itemtest
                        Hibernate: drop table if exists token
                        Hibernate: create table contact (id integer not null, emailadresse VARCHAR(256) not null, enabled BOOLEAN not null, nachname VARCHAR(256) not null, vorname VARCHAR(256) not null, primary key (id)) engine=MyISAM
                        Hibernate: create table hibernate_sequence (next_val bigint) engine=MyISAM
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: insert into hibernate_sequence values ( 1 )
                        Hibernate: create table histcontener (id int(11) not null, contenerdate VARCHAR(256), contenername VARCHAR(256), contenerphoto VARCHAR(256), contenersubname VARCHAR(256), contenertype VARCHAR(256), primary key (id)) engine=MyISAM
                        Hibernate: create table histcontent (id int(11) not null, entrydesc VARCHAR(256), parent_id int(11), primary key (id)) engine=MyISAM
                        Hibernate: create table histitem (id int(11) not null, histdesc VARCHAR(256), histduration VARCHAR(256), histextra VARCHAR(256), histkind VARCHAR(256), histtitle VARCHAR(256), parent_id int(11), primary key (id)) engine=MyISAM
                        Hibernate: create table histsubs (id int(11) not null, parent_id int(11), primary key (id)) engine=MyISAM
                        Hibernate: create table histtext (id int(11) not null, data TEXT, parent_id int(11), primary key (id)) engine=MyISAM
                        Hibernate: create table itemtest (id integer not null, name varchar(255) not null, primary key (id)) engine=MyISAM
                        Hibernate: create table token (token_id bigint not null, confirmation_token varchar(255), created_date datetime, id integer not null, primary key (token_id)) engine=MyISAM
                        Hibernate: alter table itemtest add constraint UK_cbrfajejalq125npr9yqpx7my unique (name)
                        Hibernate: alter table histcontent add constraint FKdunpf5vhq8mt2ctgaj33x43wa foreign key (parent_id) references histitem (id)
                        Hibernate: alter table histitem add constraint FKpusvc572gghesffw06snbxgb0 foreign key (parent_id) references histcontener (id)
                        Hibernate: alter table histsubs add constraint FKs8r6ercsim61i4gfxhpcj8e53 foreign key (parent_id) references histcontent (id)
                        Hibernate: alter table histtext add constraint FK9h6coernr6uch7geaoio4ytpc foreign key (parent_id) references histsubs (id)
                        Hibernate: alter table token add constraint FKnbp7thph8wigye94do4ylhk5x foreign key (id) references contact (id)
                        2019-07-31 09:40:01.910  INFO 1838 --- [  restartedMain] o.h.t.schema.internal.SchemaCreatorImpl  : HHH000476: Executing import script 'org.hibernate.tool.schema.internal.exec.ScriptSourceInputNonExistentImpl@688c5f85'
                        ...
                        ```
            - [sh](src/main/sh)
                - [native.sh](src/main/sh/native.sh)
                    - called by build plugin [exec-maven-plugin](https://www.mojohaus.org/exec-maven-plugin/) in [pom.xml](pom.xml)
                        ```
                        <execution>
                            <id>JNI compilation</id>
                            <phase>generate-sources</phase>
                            <goals>
                                <goal>exec</goal>
                            </goals>
                            <configuration>
                                <executable>src/main/sh/native.sh</executable>
                            </configuration>
                        </execution>
                        ```
                    - this is a wrapper to prepare a target/libs directory, in case it doesn't exists, before running [Makefile](src/main/c/Makefile) 
        - [test](src/test)
            - spring boot app tests implementation
    - [target](target)
        - contains the result of mvn compile, package or install

This site was built using [GitHub Pages](https://pages.github.com/).
