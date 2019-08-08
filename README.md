Porting my-website-raw to Spring / React

## This software 
is developped on Linux Ubuntu Bionic, with Jetbrains IntelliJIdea & CLion.
it is still a draft project in constant evolution
My current professional website is 
- http://www.pignalet.de
- https://github.com/fpignalet/my-website-raw

Porting my-website-raw to Spring / React. Please read:
* [main application README](client1/README.md)
* [module README](client2/README.md)
* [external-react-frontend README](external-react-frontend/README.md)
* [server README](server/README.md)

# Once upon a time...
- In the past we did monolitic "Java Rich-Client" development using 
    - JDK for engine / business logic / backend
    - And especially awt / Swing / swt for GUI
    - some specific additional packages / drivers, for example to access database     
- Then we used Servlet technology: our application was only backend and answered request returning http pages
- Then we ran our Java source code using an application server 

- it was possible to generate an application with an Ant script
- it was possible to run application through an Applet
- it was possible to...

- Now there is Maven...

- So now, let's call everything by it's ****ing name... :)
    - MICROSERVICES aren't SO MAGIC and COMPLICATED
    - MICROSERVICES are just "normal" application 
        - sometimes using the same data source
        - sometimes binded together through different technologies
            - REST api
            - Message queue
            - any kind of shared repository
            - ...
            
            
This documentation was built using [GitHub Pages](https://pages.github.com/).
