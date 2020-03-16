# ALL THAT'S USEFUL

#1. Chronology
* [https://www.quickprogrammingtips.com/spring-boot/history-of-spring-framework-and-spring-boot.html]
    * [Spring 0.9: Jun 2003]
    * [Spring boot 1.1 (June 2014)]
        improved templating support,
        gemfire support,
        auto configuration for elasticsearch and apache solr.
    * [Spring boot 1.2 (March 2015)]
        upgrade to servlet 3.1/tomcat 8/jetty 9,
        spring 4.1 upgrade,
        support for banner/jms/SpringBootApplication annotation.
    * [Spring boot 1.3 (December 2016)]
        spring 4.2 upgrade,
        new spring-boot-devtools,
        auto configuration for caching technologies(ehcache, hazelcast, redis, guava and infinispan) and fully executable jar support.
    * [Spring boot 1.4 (January 2017)]
        spring 4.3 upgrade,
        couchbase/neo4j support,
        analysis of startup failures and RestTemplateBuilder.
    * [Spring boot 1.5 (February 2017)]
        support for kafka/ldap,
        third party library upgrades,
        deprecation of CRaSH support and actuator loggers endpoint to modify application log levels on the fly.
    * [Spring 1.2.6: Nov 2005]
    * [Spring 2.0: Oct 2006]
    * [Spring 2.5: Nov 2007]
    * [Spring 2.5.6: Nov 2008 springsource...]
    * [Spring 3.0: Dec 2009]
    * [Spring 3.1: Dec 2011 RabbitMQ]
    * [Spring 4.0: Dec 2013, Pivotal...]
    * [Spring Boot 1.0: Avr 2014]
    * [Spring 4.1.3: Dec 2014]
    * [Spring 4.3: Jun 2016]
    * [Spring 5.0 M4: Dec 2016]
    * [Spring Boot 1.5.2: Mar 2017]
    * [Spring 5.0: Jul 2017]
    * [Spring Boot 2.0: Nov 2017]
    
# 2. Was ist der Spring Framework?
* Spring Framework ist ein Open Source Framework, 
    * das zur Lösung der Komplexität der Entwicklung von Unternehmensanwendungen entwickelt wurde. 
* Einer der Hauptvorteile des Spring-Frameworks ist die layered Architektur, mit der Sie auswählen können, welche Komponenten Sie verwenden. 
    * Hauptmodul für Spring sind 
        * Spring Core, 
        * Spring AOP und 
        * Spring MVC.

# 3. Was sind die Hauptmerkmale von Spring Frameworks?
* Leichtgewicht:
    * Spring ist leicht in Bezug auf Größe und Transparenz. 
    * Die Grundversion des Spring Framework beträgt etwa 1 MB. Und der processing overhead ist auch sehr gering.
* Inversion der Kontrolle (IOC):
    * Das Grundkonzept der Dependency Injection oder Inversion der Kontrolle besteht darin, dass der Programmierer die Objekte nicht erstellen muss, sondern nur beschreiben, wie er erstellt werden soll.
* Aspektorientiert (AOP)
    * -> Spring AOP    
* Behälter / Objektcontainer:
    * Spring enthält und verwaltet den Lebenszyklus und die Konfiguration von Anwendungsobjekten.
* MVC Framework:
    * Spring wird mit einem MVC-Webanwendungs-Framework geliefert, das auf der Spring-Funktionalität von Core basiert. 
    * Dieses Framework ist über Strategieschnittstellen in hohem Maße konfigurierbar und unterstützt mehrere Ansichtstechnologien wie JSP, Velocity, Tiles, iText und POI.
* Transaktionsverwaltung:
    * Spring Framework bietet eine generische Abstraktionsebene für das Transaktionsmanagement. Dadurch kann der Entwickler die steckbaren Transaktionsmanager hinzufügen und die Abgrenzung von Transaktionen vereinfachen, ohne sich mit Problemen auf niedriger Ebene zu befassen.
* JDBC-Ausnahmebehandlung:
    * Die JDBC-Abstraktionsschicht von Spring bietet eine sinnvolle Ausnahmehierarchie, die die Fehlerbehandlungsstrategie vereinfacht. 
    * Die Integration mit Hibernate, JDO und iBATIS: 
    * Spring bietet die besten Integrationsservices für Hibernate, JDO und iBATIS

#4. in details    
| item          | Description                                                                                                                                                                                                                                                                                   |
| ------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Spring Core   | Das Core-Paket ist die wichtigste Komponente des Spring Frameworks.                                                                                                                                                                                                                         |
|               | Diese Komponente stellt die Abhängigkeitseinspritzungsfunktionen bereit.                                                                                                                                                                                                                    |
|               | Die BeanFactory stellt ein Factory-Pattern bereit, das die Abhängigkeiten wie Initialisierung, Erstellung und Zugriff der Objekte von Ihrer eigentlichen Programmlogik trennt.                                                                                                              |
|               | Das Core-Paket ist die wichtigste Komponente des Spring Frameworks.                                                                                                                                                                                                                         |
|               | Diese Komponente stellt die Abhängigkeitseinspritzungsfunktionen bereit. Die BeanFactory stellt ein Factory-Pattern bereit, das die Abhängigkeiten wie Initialisierung, Erstellung und Zugriff der Objekte von Ihrer eigentlichen Programmlogik trennt.                                     |
| Spring AOP    | Spring unterstützt eine aspektorientierte Programmierung.                                                                                                                                                                                                                                   |
|               | Aspektorientierte Programmierung bezieht sich auf das Programmierparadigma, das sekundäre oder unterstützende Funktionen von der Geschäftslogik des Hauptprogramms isoliert.                                                                                                                |
|               | AOP ist eine vielversprechende Technologie zur Trennung von Crosscutting-Anliegen, die in der objektorientierten Programmierung normalerweise nur schwer zu erreichen ist.                                                                                                                  |
|               |     Die Modularität der Anwendung wird auf diese Weise erhöht und die Wartung wird erheblich vereinfacht.                                                                                                                                                                                   |
|               |     Eine der Schlüsselkomponenten von Spring ist das AOP-Framework.                                                                                                                                                                                                                         |
|               |     AOP wird im Spring  verwendet: Dec Bereitstellung deklarativer Enterprise-Services, insbesondere als Ersatz für deklarative Services von EJB. Der wichtigste solcher Dienst ist das deklarative Transaktionsmanagement, das auf der Transaktionsabstraktion von Spring basiert.         |
|               |     Benutzer können benutzerdefinierte Aspekte implementieren und deren Verwendung von OOP mit AOP ergänzen                                                                                                                                                                                 |
|               |     Eine der Schlüsselkomponenten von Spring ist das AOP-Framework.                                                                                                                                                                                                                         |
|               |     AOP wird im Spring  verwendet:                                                                                                                                                                                                                                                          |
|               |         Dec Bereitstellung deklarativer Enterprise-Services, insbesondere als Ersatz für deklarative Services von EJB.                                                                                                                                                                      |
|               |         Der wichtigste solcher Dienst ist das deklarative Transaktionsmanagement, das auf der Transaktionsabstraktion von Spring basiert.                                                                                                                                                   |
|               |         Benutzer können benutzerdefinierte Aspekte implementieren und deren Verwendung von OOP mit AOP ergänzen                                                                                                                                                                             |
| Spring MVC    | Spring Web / Spring Web MVC:                                                                                                                                                                                                                                                                |
|               | Das Spring-Webmodul ist Teil des Webanwendungs-Entwicklungsstacks von Spring, zu dem Spring MVC gehört.                                                                                                                                                                                     |
|               | Dies ist das Modul, das die MVC-Implementierungen für die Webanwendungen bereitstellt.                                                                                                                                                                                                      |
|               | Das Spring-Webmodul ist Teil des Webanwendungs-Entwicklungsstacks von Spring, zu dem Spring MVC gehört.                                                                                                                                                                                     |
|               | Dies ist das Modul, das die MVC-Implementierungen für die Webanwendungen bereitstellt.                                                                                                                                                                                                      |
| Spring ORM/DAO| Das ORM-Paket bezieht sich auf REFERS TO den Datenbankzugriff. Es bietet Integrationsebenen für beliebte objektrelationale Mapping-APIs, einschließlich JDO, Hibernate und iBatis.                                                                                                          |
|               | Die DAO-Unterstützung (Data Access Object) in Spring dient in erster Linie der Standardisierung der Datenzugriffsarbeit mithilfe von Technologien wie JDBC, Hibernate oder JDO.                                                                                                             |
|               | Spring Context: ( JMS? )                                                                                                                                                                                                                                                                    |
|               | Dieses Paket baut auf dem Beans-Paket auf, um Unterstützung für Nachrichtenquellen und für das Observer-Entwurfsmuster sowie die Möglichkeit für Anwendungsobjekte hinzuzufügen, Ressourcen über eine konsistente API abzurufen.                                                            |

# 5. Spring Boot
* Everything's here: 
    * [spring-boot/docs](https://docs.spring.io/spring-boot/docs/current/reference/html)
* first of all Spring Boot is not a framework
    * it is a way to ease to create stand-alone application with minimal or zero configurations. 
    * It is approach to develop spring based application with very less configuration. 
    * It provides defaults for code and annotation configuration to quick start new spring projects within no time. 
    * Spring Boot leverages existing spring projects as well as Third party projects to develop production ready applications. 
* Spring Boot follows “Opinionated Defaults Configuration” Approach to reduce Developer effort
* Spring Boot automatically configures required classes depending on the libraries on its classpath. Suppose your application want to interact with DB, if there are Spring Data libraries on class path then it automatically sets up connection to DB along with the Data Source class.
    * Spring Boot reduces lots of development time and increases productivity.
    * Spring Boot follows “Opinionated Defaults Configuration” Approach to reduce Developer effort
    * It avoids writing lots of boilerplate Code, Annotations and XML Configuration.
    * It is very easy to develop Spring Based applications with Java or Groovy.
    * It is very easy to integrate Spring Boot Application with its Spring Ecosystem like 
        * Spring JDBC, 
        * Spring ORM, 
        * Spring Data, 
        * Spring Security etc.
    * It provides 
        * Embedded HTTP servers like Tomcat, Jetty etc. to develop and test our web applications very easily.
        * CLI (Command Line Interface) tool to develop and test Spring Boot (Java or Groovy) Applications from command prompt very easily and quickly.
        * lots of plugins to 
            * work with embedded and in-memory Databases very easily.
            * develop and test Spring Boot Applications very easily using Build Tools like Maven and Gradle
        * a set of Starter Pom’s or gradle build files which one can use to add required dependencies and also facilitate auto configuration.

#4. Microservices
* Microservices are just REST-Services. 
    * Es bedeutet, dass anstatt einer Anwendung es gibt mehrere.

#6. CLEAN CODE
* SOLID
    * Single Change Responsibility
        * a class should have a single reason to change.
            Responsabilité unique (single responsibility principle)
            une classe, une fonction ou une méthode doit avoir une et une seule responsabilité
    * Interface Segregation (intf)
        * An object should only depend on interfaces it requires
            Ségrégation des interfaces (interface segregation principle)
            préférer plusieurs interfaces spécifiques pour chaque client plutôt qu'une seule interface générale
            
    * Open/Closed for modifs
        * software entities should be open for extension but closed for modification.
            Ouvert/fermé (open/closed principle)
            une entité applicative (class, fonction, module ...) doit être ouverte à l'extension, mais fermée à la modification
    * Liskov Substitution (characs)
        * the program’s characteristics should not change
            Substitution de Liskov (Liskov substitution principle)
            une instance de type T doit pouvoir être remplacée par une instance de type G, tel que G sous-type de T, sans que cela ne modifie la cohérence du programme
            
    * Dependency Inversion
        * Use interfaces
            Inversion des dépendances (dependency inversion principle)
            il faut dépendre des abstractions, pas des implémentations
