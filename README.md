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
BACKEND:

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
FRONTEND:
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
                    - ...
            - [js](src/main/js)
                - [angular](src/main/js/angular)
                    - ...
                - [react](src/main/js/react)
                    - ...
            - [resources](src/main/resources)
                - [static](src/main/resources/static)
                    - [built](src/main/resources/static/built)
                        - ...
                    - [data](src/main/resources/static/data)
                        - ...
                    - [images](src/main/resources/static/images)
                        - ...
                    - [style](src/main/resources/static/style)
                        - ...
                - [templates](src/main/resources/templates)
                    - [fragments](src/main/resources/templates/fragments)
                        - ...
            - [sh(src/main/sh)
        - [test](test)

This site was built using [GitHub Pages](https://pages.github.com/).
