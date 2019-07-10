# Getting Started

### Guides
The following guides illustrates how to use certain features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

NOTES:

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
