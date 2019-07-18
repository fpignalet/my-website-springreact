# my-website-springreact
Porting my-website-raw to Spring / React

this software is developped on Linux Ubuntu Bionic, with Jetbrains IntelliJIdea.

it is still a draft. My current professional website is 
- http://www.pignalet.de
- https://github.com/fpignalet/my-website-raw

Also...

Open the project / import the pom in IntelliJIdea, then use Maven targets to build / run the application
Otherwise, open a terminal and execute:
- mvn clean compile
- mvn package
- mvn install
- mvn spring-boot:run -Drun.jvmArguments="-D$LD_LIBRARY_PATH:./target/libs"
- cd external-react-frontend
- npm install
- npm run-script build
- DEBUG=canvas-game-bootstrap:* npm run-script start
- or npm run-script start

What is important with this source code?
- everything's begin with POM & package.json & webpack.config.js files
- POM prepares spring application material
- package.json prepares frontend resources
- webpack.config.js prepares frontend compiled stuff
    it compiles / transpiles the content of js directory to get production source code which will be used in GUI html fragments
- the Spring app tree is
    - src
        - main
            - C
            - java
                - com ...
            - js
                - angular
                - react
            - resources
                - static
                    - built
                    - data
                    - images
                    - style
                - templates
                    - fragments
            - sh
        - test
