1. [Was ist der Spring Framework?]
* Spring Framework ist ein Open Source Framework, das zur Lösung der Komplexität der Entwicklung von Unternehmensanwendungen entwickelt wurde. 
Einer der Hauptvorteile des Spring-Frameworks ist die layered Architektur, mit der Sie auswählen können, welche Komponenten Sie verwenden. 
Hauptmodul für Spring sind 
    * Spring Core, 
    * Spring AOP und 
    * Spring MVC.

2. [Was sind die Hauptmerkmale von Spring Frameworks?]
* Leichtgewicht:
    * Spring ist leicht in Bezug auf Größe und Transparenz. 
    * Die Grundversion des Spring Framework beträgt etwa 1 MB. Und der processing overhead ist auch sehr gering.
* Inversion der Kontrolle (IOC):
    * Das Grundkonzept der Dependency Injection oder Inversion der Kontrolle besteht darin, dass der Programmierer die Objekte nicht erstellen muss, sondern nur beschreiben, wie er erstellt werden soll.
* Aspektorientiert (AOP):
    * Spring unterstützt eine aspektorientierte Programmierung.
    * Aspektorientierte Programmierung bezieht sich auf das Programmierparadigma, das sekundäre oder unterstützende Funktionen von der Geschäftslogik des Hauptprogramms isoliert. 
    * AOP ist eine vielversprechende Technologie zur Trennung von Crosscutting-Anliegen, die in der objektorientierten Programmierung normalerweise nur schwer zu erreichen ist. 
    * Die Modularität der Anwendung wird auf diese Weise erhöht und die Wartung wird erheblich vereinfacht.
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
    
3. [Erläutern Sie die Hauptmodule des Spring?]
* Spring AOP:
    * Eine der Schlüsselkomponenten von Spring ist das AOP-Framework. 
    * AOP wird im Spring  verwendet: Dec Bereitstellung deklarativer Enterprise-Services, insbesondere als Ersatz für deklarative Services von EJB. Der wichtigste solcher Dienst ist das deklarative Transaktionsmanagement, das auf der Transaktionsabstraktion von Spring basiert.
* Benutzer können benutzerdefinierte Aspekte implementieren und deren Verwendung von OOP mit AOP ergänzen
* Spring ORM/DAO:
    * Das ORM-Paket bezieht sich auf REFERS TO den Datenbankzugriff. Es bietet Integrationsebenen für beliebte objektrelationale Mapping-APIs, einschließlich JDO, Hibernate und iBatis.
    * Die DAO-Unterstützung (Data Access Object) in Spring dient in erster Linie der Standardisierung der Datenzugriffsarbeit mithilfe von Technologien wie JDBC, Hibernate oder JDO.
    * Spring Context: ( JMS? )
    * Dieses Paket baut auf dem Beans-Paket auf, um Unterstützung für Nachrichtenquellen und für das Observer-Entwurfsmuster sowie die Möglichkeit für Anwendungsobjekte hinzuzufügen, Ressourcen über eine konsistente API abzurufen.
* Spring Web / Spring Web MVC:
    * Das Spring-Webmodul ist Teil des Webanwendungs-Entwicklungsstacks von Spring, zu dem Spring MVC gehört.
    * Dies ist das Modul, das die MVC-Implementierungen für die Webanwendungen bereitstellt.
* Spring Core:
    * Das Core-Paket ist die wichtigste Komponente des Spring Frameworks.
    * Diese Komponente stellt die Abhängigkeitseinspritzungsfunktionen bereit. Die BeanFactory stellt ein Factory-Pattern bereit, das die Abhängigkeiten wie Initialisierung, Erstellung und Zugriff der Objekte von Ihrer eigentlichen Programmlogik trennt.

4. [Spring Boot:]
* first of all Spring Boot is not a framework, it is a way to ease to create stand-alone application with minimal or zero configurations. It is approach to develop spring based application with very less configuration. It provides defaults for code and annotation configuration to quick start new spring projects within no time. Spring Boot leverages existing spring projects as well as Third party projects to develop production ready applications. 
It provides a set of Starter Pom’s or gradle build files which one can use to add required dependencies and also facilitate auto configuration.
Spring Boot automatically configures required classes depending on the libraries on its classpath. Suppose your application want to interact with DB, if there are Spring Data libraries on class path then it automatically sets up connection to DB along with the Data Source class.
    • Spring Boot reduces lots of development time and increases productivity.
    • It avoids writing lots of boilerplate Code, Annotations and XML Configuration.
    • Spring Boot follows “Opinionated Defaults Configuration” Approach to reduce Developer effort
    • It is very easy to develop Spring Based applications with Java or Groovy.
    • It is very easy to integrate Spring Boot Application with its Spring Ecosystem like 
        ◦ Spring JDBC, 
        ◦ Spring ORM, 
        ◦ Spring Data, 
        ◦ Spring Security etc.
    • It provides Embedded HTTP servers like Tomcat, Jetty etc. to develop and test our web applications very easily.
    • It provides CLI (Command Line Interface) tool to develop and test Spring Boot (Java or Groovy) Applications from command prompt very easily and quickly.
    • Spring Boot provides lots of plugins to develop and test Spring Boot Applications very easily using Build Tools like Maven and Gradle
    • It provides lots of plugins to work with embedded and in-memory Databases very easily.

5. [Microservices:]
* Microservices are just REST-Services. Es bedeutet, dass anstatt einer Anwendung es gibt mehrere.
* SOLID
    • Single Change Responsibility
        ◦ a class should have a single reason to change.
            Responsabilité unique (single responsibility principle)
            une classe, une fonction ou une méthode doit avoir une et une seule responsabilité
    • Open/Closed for modifs
        ◦ software entities should be open for extension but closed for modification.
            Ouvert/fermé (open/closed principle)
            une entité applicative (class, fonction, module ...) doit être ouverte à l'extension, mais fermée à la modification
    • Liskov Substitution (characs)
        ◦ the program’s characteristics should not change
            Substitution de Liskov (Liskov substitution principle)
            une instance de type T doit pouvoir être remplacée par une instance de type G, tel que G sous-type de T, sans que cela ne modifie la cohérence du programme
    • Interface Segregation (intf)
        ◦ An object should only depend on interfaces it requires
            Ségrégation des interfaces (interface segregation principle)
            préférer plusieurs interfaces spécifiques pour chaque client plutôt qu'une seule interface générale
    • Dependency Inversion
        ◦ Use interfaces
            Inversion des dépendances (dependency inversion principle)
            il faut dépendre des abstractions, pas des implémentations

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

Hauptmodul für Spring sind
* [Spring Core]
        Das Core-Paket ist die wichtigste Komponente des Spring Frameworks.
        Diese Komponente stellt die Abhängigkeitseinspritzungsfunktionen bereit.
        Die BeanFactory stellt ein Factory-Pattern bereit, das die Abhängigkeiten wie Initialisierung, Erstellung und Zugriff der Objekte von Ihrer eigentlichen Programmlogik trennt.
* [Spring AOP]
        Eine der Schlüsselkomponenten von Spring ist das AOP-Framework.
        AOP wird im Spring  verwendet:
            Dec Bereitstellung deklarativer Enterprise-Services, insbesondere als Ersatz für deklarative Services von EJB.
            Der wichtigste solcher Dienst ist das deklarative Transaktionsmanagement, das auf der Transaktionsabstraktion von Spring basiert.
            Benutzer können benutzerdefinierte Aspekte implementieren und deren Verwendung von OOP mit AOP ergänzen
* [Spring MVC]
        Das Spring-Webmodul ist Teil des Webanwendungs-Entwicklungsstacks von Spring, zu dem Spring MVC gehört.
        Dies ist das Modul, das die MVC-Implementierungen für die Webanwendungen bereitstellt.

* [Spring Boot]
        Spring Boot follows “Opinionated Defaults Configuration” Approach to reduce Developer effort
        Spring Boot provides lots of plugins to develop and test Spring Boot Applications very easily using Build Tools like Maven and Gradle
        It provides Embedded HTTP servers like Tomcat, Jetty etc. to develop and test our web applications very easily.
        It provides CLI (Command Line Interface) tool to develop and test Spring Boot (Java or Groovy) Applications from command prompt very easily and quickly.
        It provides lots of plugins to work with embedded and in-memory Databases very easily.

### Guides
The following guides illustrates how to use certain features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

NOTES:

* [1 - for all properties:]
    https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
    ----------------------------------------
    * [CORE PROPERTIES]
    ----------------------------------------
    * LOGGING -> logging.
    * AOP -> spring.aop.
    * IDENTITY (ContextIdApplicationContextInitializer) -> spring.autoconfigure.
    * ADMIN (SpringApplicationAdminJmxAutoConfiguration)
    * AUTO-CONFIGURATION
    * BANNER -> spring.banner.
    * SPRING CORE
    * SPRING CACHE (CacheProperties) -> spring.cache.
    * SPRING CONFIG - using environment property only (ConfigFileApplicationListener), spring.config.
    * HAZELCAST (HazelcastProperties): https://hazelcast.org, memory layer, spring.hazelcast.
    * PROJECT INFORMATION (ProjectInfoProperties)
    * JMX -> spring.jmx.
    * Email (MailProperties)
    * APPLICATION SETTINGS (SpringApplication), spring.application.
    * FILE ENCODING (FileEncodingApplicationListener)
    * INTERNATIONALIZATION (MessageSourceProperties)
    * OUTPUT -> spring.output.
    * PID FILE (ApplicationPidFileWriter), spring.pid.
    * PROFILES -> spring.profiles.
    * QUARTZ SCHEDULER (QuartzProperties): https://www.baeldung.com/spring-quartz-schedule, scheduler, spring.quartz.
    * REACTOR (ReactorCoreProperties): https://projectreactor.io/, multithreading?, spring.reactor.
    * SENDGRID (SendGridAutoConfiguration), spring.sendgrid.
    * TASK EXECUTION  (TaskExecutionProperties), spring.task.execution.
    * TASK SCHEDULING  (TaskSchedulingProperties), spring.task.scheduling.
    ----------------------------------------
    * [WEB PROPERTIES]
    ----------------------------------------
    * EMBEDDED SERVER CONFIGURATION (ServerProperties)
    * FREEMARKER (FreeMarkerProperties): https://freemarker.apache.org/docs/index.html, GUI template engine, spring.freemarker.
    * GROOVY TEMPLATES (GroovyTemplateProperties), spring.groovy.
    * SPRING HATEOAS (HateoasProperties): https://spring.io/projects/spring-hateoas, "Hypermedia As The Engine Of Application State", Designmodell für REST-Schnittstellen, spring.hateoas.
    * HTTP (HttpProperties)
    * MULTIPART (MultipartProperties)
    * JACKSON (JacksonProperties), spring.jackson.
    * GSON (GsonProperties), spring.gson.
    * JERSEY (JerseyProperties): https://jersey.github.io/, REST framework, spring.jersey.
    * SPRING LDAP (LdapProperties), spring.ldap.
    * EMBEDDED LDAP (EmbeddedLdapProperties)
    * MUSTACHE TEMPLATES (MustacheAutoConfiguration), spring.mustache.
    * SPRING MVC (WebMvcProperties), spring.mvc.
    * SPRING RESOURCES HANDLING (ResourceProperties)
    * SPRING SESSION (SessionProperties)
    * SPRING SESSION HAZELCAST (HazelcastSessionProperties)
    * SPRING SESSION JDBC (JdbcSessionProperties)
    * SPRING SESSION MONGODB (MongoSessionProperties)
    * SPRING SESSION REDIS (RedisSessionProperties): https://redis.io/, memory stowage
    * THYMELEAF (ThymeleafAutoConfiguration)
    * SPRING WEBFLUX (WebFluxProperties): https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux, scheduler
    * SPRING WEB SERVICES (WebServicesProperties)
    ----------------------------------------
    * [SECURITY PROPERTIES]
    ----------------------------------------
    * SECURITY (SecurityProperties)
    * SECURITY OAUTH2 CLIENT (OAuth2ClientProperties)
    * SECURITY OAUTH2 RESOURCE SERVER (OAuth2ResourceServerProperties)
    ----------------------------------------
    * [DATA PROPERTIES]
    ----------------------------------------
    * FLYWAY (FlywayProperties): https://flywaydb.org/documentation/plugins/springboot, database migration
    * LIQUIBASE (LiquibaseProperties): https://www.liquibase.org/, DB from XML, YAML, JSON
    * COUCHBASE (CouchbaseProperties): https://info.couchbase.com, nosql
    * DAO (PersistenceExceptionTranslationAutoConfiguration)
    * CASSANDRA (CassandraProperties): https://aiven.io/, cloud db
    * DATA COUCHBASE (CouchbaseDataProperties)
    * ELASTICSEARCH (ElasticsearchProperties): https://www.elastic.co/fr/, spring.elasticsearch.
    * DATA JDBC
    * DATA LDAP
    * MONGODB (MongoProperties)
    * DATA REDIS
    * NEO4J (Neo4jProperties): https://neo4j.com, graph analytics
    * DATA REST (RepositoryRestProperties)
    * SOLR (SolrProperties): https://lucene.apache.org/solr/, search platform
    * DATA WEB (SpringDataWebProperties)
    * DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
    * JEST (Elasticsearch HTTP client) (JestProperties)
    * Elasticsearch REST clients (RestClientProperties)
    * H2 Web Console (H2ConsoleProperties): http://www.h2database.com/html/quickstart.html, db engine
    * InfluxDB (InfluxDbProperties): https://www.influxdata.com/, db engine
    * JOOQ (JooqProperties): https://www.jooq.org/, Java code from DB
    * JDBC (JdbcProperties)
    * JPA (JpaBaseConfiguration, HibernateJpaAutoConfiguration)
    * JTA (JtaAutoConfiguration): https://www.baeldung.com/jee-jta, managing transactions
    * ATOMIKOS (AtomikosProperties): https://www.atomikos.com/, managing transactions
    * BITRONIX: https://github.com/bitronix/btm, managing transactions
    * EMBEDDED MONGODB (EmbeddedMongoProperties)
    * REDIS (RedisProperties)
    * TRANSACTION (TransactionProperties)
    ----------------------------------------
    * [INTEGRATION PROPERTIES]
    ----------------------------------------
    * ACTIVEMQ (ActiveMQProperties): https://activemq.apache.org/, messaging
    * ARTEMIS (ArtemisProperties): https://activemq.apache.org/components/artemis/, messaging
    * SPRING BATCH (BatchProperties)
    * SPRING INTEGRATION (IntegrationProperties)
    * JMS (JmsProperties)
    * APACHE KAFKA (KafkaProperties): https://kafka.apache.org/, "distributed streaming platform"?
    * RABBIT (RabbitProperties): https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html, messaging
    ----------------------------------------
    * [ACTUATOR PROPERTIES]
    ----------------------------------------
    * MANAGEMENT HTTP SERVER (ManagementServerProperties)
    * CLOUDFOUNDRY
    * ENDPOINTS GENERAL CONFIGURATION
    * ENDPOINTS JMX CONFIGURATION (JmxEndpointProperties)
    * ENDPOINTS WEB CONFIGURATION (WebEndpointProperties)
    * ENDPOINTS CORS CONFIGURATION (CorsEndpointProperties)
    * AUDIT EVENTS ENDPOINT (AuditEventsEndpoint)
    * BEANS ENDPOINT (BeansEndpoint)
    * CACHES ENDPOINT (CachesEndpoint)
    * CONDITIONS REPORT ENDPOINT (ConditionsReportEndpoint)
    * CONFIGURATION PROPERTIES REPORT ENDPOINT (ConfigurationPropertiesReportEndpoint, ConfigurationPropertiesReportEndpointProperties)
    * ENVIRONMENT ENDPOINT (EnvironmentEndpoint, EnvironmentEndpointProperties)
    * FLYWAY ENDPOINT (FlywayEndpoint)
    * HEALTH ENDPOINT (HealthEndpoint, HealthEndpointProperties)
    * HEAP DUMP ENDPOINT (HeapDumpWebEndpoint)
    * HTTP TRACE ENDPOINT (HttpTraceEndpoint)
    * INFO ENDPOINT (InfoEndpoint)
    * INTEGRATION GRAPH ENDPOINT (IntegrationGraphEndpoint)
    * JOLOKIA ENDPOINT (JolokiaProperties): https://jolokia.org/reference/html/introduction.html
    * LIQUIBASE ENDPOINT (LiquibaseEndpoint)
    * LOG FILE ENDPOINT (LogFileWebEndpoint, LogFileWebEndpointProperties)
    * LOGGERS ENDPOINT (LoggersEndpoint)
    * REQUEST MAPPING ENDPOINT  (MappingsEndpoint)
    * METRICS ENDPOINT (MetricsEndpoint)
    * PROMETHEUS ENDPOINT (PrometheusScrapeEndpoint): https://docs.spring.io/spring-metrics/docs/current/public/prometheus
    * SCHEDULED TASKS ENDPOINT (ScheduledTasksEndpoint)
    * SESSIONS ENDPOINT (SessionsEndpoint)
    * SHUTDOWN ENDPOINT (ShutdownEndpoint)
    * THREAD DUMP ENDPOINT (ThreadDumpEndpoint)
    * HEALTH INDICATORS
    * HTTP TRACING (HttpTraceProperties)
    * INFO CONTRIBUTORS (InfoContributorProperties)
    * METRICS
    ----------------------------------------
    * [DEVTOOLS PROPERTIES]
    ----------------------------------------
    * DEVTOOLS (DevToolsProperties)
    * REMOTE DEVTOOLS (RemoteDevToolsProperties)
    ----------------------------------------
    * [TESTING PROPERTIES]
    ----------------------------------------
* [2 - EXAMPLE: to use JSP pages]
    * spring.mvc.view.prefix: /WEB-INF/jsp/
    * spring.mvc.view.suffix: .jsp
* [3 - annotations]
    for all annotations:
    https://springframework.guru/spring-framework-annotations/
    * [Core Spring Framework Annotations]
        @Required       @Autowired  @Qualifier      @Configuration
        @ComponentScan  @Bean       @Lazy           @Value
    * [Spring Framework Stereotype Annotations]
        @Component  @Controller     @Service    @Repository
    * [Spring Framework Testing Annotations]
        @BootstrapWith          @ContextConfiguration   @WebAppConfiguration    @Timed
        @Repeat                 @Commit                 @RollBack               @DirtiesContext
        @BeforeTransaction      @AfterTransaction       @Sql                    @SqlConfig
        @SqlGroup               @SpringBootTest         @DataJpaTest            @DataMongoTest
        @WebMVCTest             @AutoConfigureMockMVC   @MockBean               @JsonTest
        @TestPropertySource
    * [Spring Framework DataAccess Annotations]
        @Transactional

    * [Spring Boot Annotations]
        @EnableAutoConfiguration    @SpringBootApplication

    * [Spring MVC and REST Annotations]
        @Controller         @RequestMapping     @CookieValue        @CrossOrigin
        @GetMapping         @PostMapping        @PutMapping         @PatchMapping
        @DeleteMapping      @ExceptionHandler   @InitBinder         @Mappings and @Mapping
        @MatrixVariable     @PathVariable       @RequestAttribute   @RequestBody
        @RequestHeader      @RequestParam       @RequestPart        @ResponseBody
        @ResponseStatus     @ControllerAdvice   @RestController     @RestControllerAdvice
        @SessionAttribute   @SessionAttributes

    * [Spring Cloud Annotations]
        @EnableConfigServer     @EnableEurekaServer     @EnableDiscoveryClient  @EnableCircuitBreaker
        @HystrixCommand

    * [Cache-Based Annotations]
        @Cacheable  @CachePut   @CacheEvict @CacheConfig

    * [Task Execution and Scheduling Annotations]
        @Scheduled  @Async

https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
