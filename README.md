# my-website-springreact
Porting my-website-raw to Spring / React

## This software 
is developped on Linux Ubuntu Bionic, with Jetbrains IntelliJIdea.
it is still a draft. 
My current professional website is 
- http://www.pignalet.de
- https://github.com/fpignalet/my-website-raw

------------------------------------------------------------------
Also...
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
    
    - Using the above material, here's what it's possible todo:
    
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
        - a standard NodeJS / Javascript / React application which uses [axios](https://github.com/axios/axios) to connect to backend
    - [src](src)
        - [main](src/main)
            - [C](src/main/c)
                - [impl](src/main/c/impl)
                    - contains:
                        - the ExtFacade which implements the entry functions required by JNI. 
                        - The JNI interface generation is done when generating application. The [pom.xml](pom.xml) file refers to a [exec-maven-plugin]() which is configured to use [native.sh](src/main/sh/native.sh). NOTE: in the future thsi stupid hard-coded shell script will be replaced by a real Makefile
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
                    - frontend angular implementation
                    - 
                - [react](src/main/js/react)
                    - frontend react implementation
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
                    - this web app uses thymeleaf
                    - [fragments](src/main/resources/templates/fragments)
                        - heare are the thymeleaf fragments referenced in html pages above
            - [sh](src/main/sh)
        - [test](src/test)
            - spring boot app tests implementation
    - [target](target)
        - contains the result of mvn compile, package or install

This site was built using [GitHub Pages](https://pages.github.com/).
