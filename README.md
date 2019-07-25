# my-website-springreact
Porting my-website-raw to Spring / React

## This software 
is developped on Linux Ubuntu Bionic, with Jetbrains IntelliJIdea.
it is still a draft. 
My current professional website is 
- http://www.pignalet.de
- https://github.com/fpignalet/my-website-raw

Also...

------------------------------------------------------------------
BACKEND / EMBEDDED THYMELEAF FRONTEND:

Open the project / import the pom in IntelliJIdea, 
- use Maven target to compile the application
- then 
    - add "-Djava.library.path=./target/libs" to IntelliJ Run/Debug VMOptions configurations
    - use Run/Debug configurations

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

------------------------------------------------------------------
EXTERNAL FRONTEND:

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

------------------------------------------------------------------
DEPLOY THE APPLICATION ON LOCALHOST DEFAULT PORT 80:

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

------------------------------------------------------------------
NOTES:

What is important here?
- everything's begin with files
    - [pom.xml](pom.xml)
        - it prepares spring application material, getting maven dependencies to allow extensive use of spring packages
    - [package.json](package.json)
        - it prepares frontend resources, same as above with npm packages
    - [webpack.config.js](webpack.config.js)
        - it compiles / transpiles the content of js directory to get production source code which will be used in GUI html fragments
    - [Dockerfile](Dockerfile)
        - prepares the final deployment as a container
    
- the Spring app tree is
    - [src](src)
        - [main](src/main)
            - [C](src/main/c)
            - [java](src/main/java)
                - [com](src/main/java/com)
                    - spring boot app implementation
            - [js](src/main/js)
                - [angular](src/main/js/angular)
                    - frontend angular implementation
                - [react](src/main/js/react)
                    - frontend react implementation
            - [resources](src/main/resources)
                - [static](src/main/resources/static)
                    - [built](src/main/resources/static/built)
                        - frontend transpiled javascript modules
                    - [data](src/main/resources/static/data)
                        - static resources
                    - [images](src/main/resources/static/images)
                        - static resources
                    - [style](src/main/resources/static/style)
                        - css...
                - [templates](src/main/resources/templates)
                    - [fragments](src/main/resources/templates/fragments)
                        - thymeleaf fragments
            - [sh](src/main/sh)
        - [test](test)
            - spring boot app tests implementation

This site was built using [GitHub Pages](https://pages.github.com/).
