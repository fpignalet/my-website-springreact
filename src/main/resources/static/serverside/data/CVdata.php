<?php
/**
 * Created by PhpStorm.
 * User: franc
 * Date: 12.10.2018
 * Time: 16:08
 */

//-----------------------------------------------------------------------------------------------------------
/// @brief
$data_CVloadsleep = 100000;

//-----------------------------------------------------------------------------------------------------------
/// @brief
$data_CVtitle =
[
    "moi_photo" =>
        "images/P_20160208_183649_BF.jpg",
    "moi_name" =>
        "Francois Pignalet"
];

/// @brief fills clientside/cards/CVcardpres.html
$data_CVinfo =
[
    "info_raisonsociale" =>
        [ "Software architecture, development and integration with 25+ years expertise"  ],
    "info_adresse" =>
        [ "Curd-Jürgens-Str 18, 81739 München" ],
    "info_email" =>
        [ "mailto:francois.pignalet@gmail.com", "francois.pignalet@gmail.com", ],
    "info_phonenum" =>
        [ "00491704868403" ],
    "info_geburstag" =>
        [ "06/07/1967" ]
];

/// @brief fills clientside/cards/CVcardskills.html
$data_CVexperience =
[
    "exp_title" =>
        "Areas of expertise",
    "exp_content" =>
        [
            "Full stack developer",
            "AGILE software development",
            "Maintainable and documented code writing... Clean Code!",
            "Application architecture (Object Oriented & procedural) from specification",
            "Heavily threaded architectures specialist",
            "Complex refactoring and debugging"
        ]
];

/// @brief fills clientside/cards/CVcardskills.html
$data_CVskillshead =
[
    "mskills_title" =>
        "Main skills"
];
/// @brief fills clientside/cards/CVcardskills.html
$data_CVskillsentries =
[
    [
        "mskills_f1desc" =>
            [ "Java,  C,  C++,  Javascript, SW architecture, OOP, REST, Eclipse, CUnit, JUnit, XML, JSON, Ant, AGILE, JIRA, SVN, V Cycle  ==" ],
        "mskills_f1level" =>
            [ "100%" ],
        "mskills_f1text" =>
            [ "CONFIRMED" ]
    ],
    [
        "mskills_f2desc" =>
            [ "Python,  Php,  Angular,  React,  Spring,  jQuery,  SQL,  Linux,  Jenkins, DOORS, MFC,  Win32,  Asm 68K,  Github, Continuous Integration,  Rational Unified Process,  Design Patterns ==" ],
        "mskills_f2level" =>
            [ "75%" ],
        "mskills_f2text" =>
            [ "AVERAGE" ]
    ],
    [
        "mskills_f3desc" =>
            [ "C# / .NET,  J2EE,  OSGi,  WebLogic,  CORBA,  Android,  Django,  Asm x86 / PowerPC / ARM  ==" ],
        "mskills_f3level" =>
            [ "50%" ],
        "mskills_f3text" =>
            [ "LOW" ]
    ]
];

/// @brief fills clientside/cards/CVcardlangs.html
$data_CVlanghead =
[
    "lang_title" =>
        "Languages"
];
/// @brief fills clientside/cards/CVcardlangs.html
$data_CVlangentries =
[
    [
        "lang_f1desc" =>
            [ "French" ],
        "lang_f1level" =>
            [ "100%" ],
        "lang_f1text" =>
            [ "100% (MOTHER TONGUE)" ]
    ],
    [
        "lang_f2desc" =>
            [ "English" ],
        "lang_f2level" =>
            [ "85%" ],
        "lang_f2text" =>
            [ "C1" ]
    ],
    [
        "lang_f3desc" =>
            [ "German" ],
        "lang_f3level" =>
            [ "65%" ],
        "lang_f3text" =>
            [ "B2" ]
    ]
];

// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot0 =
[
    "boulotentry0date" =>
        [ "2018/11 - until now" ],
    "boulotentry0boite" =>
        [
            "http://www.pignalet.de",
            [
                "Freelance"
            ]
        ],
    "boulotentry0desc" =>
        [ null ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot01 =
[
    "boulotentry01item" =>
        [
            "Open Source project:",
            "Website pro",
            "(whole time)"
        ],
    "boulotentry01title" =>
        [ "Full-Stack developer" ],
    "boulotentry01content" =>
        [
            [ "Description", "Web development",
                [
                    [ "http://www.pignalet.de", "This website" ],
                    [ "https://github.com/fpignalet/my-website-raw", "Source code" ],
                ]
            ],
            [ "Environment",
                "Windows 10, Linus Debian 9, JetBrains (IntelliJIDEA, PhpStorm, PyCharm, Datagrid), React, Angular, Spring, Django, Microservices, Apache2, WAMP Server, Tomcat, MySQL, MariaDB, Jira, Jenkins, Github" ],
            [ "Keypoints",
                "Java, Javascript, Php, Python, SQL" ],
            [ "Methodology",
                [
                    "AGILE software development",
                    "continuous integration",
                    "Extreme programming"
                ]
            ]
        ]
];

// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot1 =
[
    "boulotentry1date" =>
        [ "2015/10 - 2018/10" ],
    "boulotentry1boite" =>
        [
            "https://www.hensoldt.net/solutions/sea/identification-iff/",
            [
                "HENSOLDT GMBH Ottobrunn (former Airbus Defence)"
            ]
        ],
    "boulotentry1desc" =>
        [ null ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot11 =
[
    "boulotentry11item" =>
        [
            "Products / team:",
            "IFF",
            "(until the end)"
        ],
    "boulotentry11title" =>
        [ "Software engineer / Full-Stack developer" ],
    "boulotentry11content" =>
        [
            [ "Description", "IFF interrogators, transponders and related tools, based on these products",
                [
                    [ "https://www.hensoldt.net/solutions/sea/identification-iff/military-mode-5-and-mode-s-interrogator-mssr-2000-i/", "MSSR" ],
                    [ "https://www.hensoldt.net/solutions/sea/identification-iff/ltr-400-mode-siff-lightweight-transponder/", "LTR" ]
                ]
            ],
            [ "Environment",
                "Windows 7, Linux Debian 9, IoT, Eclipse, Swing, Ant, CUnit, JUnit, React / Node, jQuery, AJAX, XML, JSON, 
                Microservices, GNU Autotools, Docker, JIRA, Jenkins, DOORS, SVN, various com links & specific HW",
                [
                    "UDP/TCP IP",
                    "RS-XXX",
                    "CAN bus",
                    [ "https://en.wikipedia.org/wiki/Automatic_dependent_surveillance_%E2%80%93_broadcast", "ADS-B" ],
                    [ "https://de.moxa.com/product/Ethernet_Remote_IO.htm", "REST API for Network Controller" ],
                    [ "https://de.moxa.com/product/IC_specialized_by_market.htm", "[Moxa] Embedded computer for military applications" ],
                    [ "http://www.rugged.com/a172-rugged-compact-pc", "[Aitech] Embedded computer for military applications" ]
                ]
            ],
            [ "Keypoints",
                "C, C++, Java, Javascript, Php, Asm x86" ],
            [ "Methodology",
                [
                    "AGILE software development",
                    "continuous integration"
                ]
            ]
        ]
];

// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot2 =
[
    "boulotentry2date" =>
        [ "2000/06 - 2015/09" ],
    "boulotentry2boite" =>
        [
            "https://www.hensoldt.net/solutions/sea/identification-iff/",
            [
                "Airbus Defence (former Cassidian / EADS Defence & Security)",
                "[mission for ALTEN until 2002/02]"
            ]
        ],
    "boulotentry2desc" =>
        [ null ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot21 =
[
    "boulotentry21item" =>
        [
            "Products / team:",
            "IFF",
            "(until the end)"
        ],
    "boulotentry21title" =>
        [ "Software engineer" ],
    "boulotentry21content" =>
        [
            [ "Description", "IFF interrogators, transponders and related tools",
                [
                    [ "https://www.hensoldt.net/solutions/sea/identification-iff/short-range-identification-friend-foe-interrogator-shorad-iff-msr-1000/", "MSR1000" ],
                    [ "https://www.hensoldt.net/solutions/sea/identification-iff/iff-mode-4mode-5-crypto-test-bench-srpm-ng/", "SRPM" ],
                    [ "https://www.hensoldt.net/solutions/sea/identification-iff/military-mode-5-and-mode-s-interrogator-mssr-2000-i/", "... and other projects related with" ],
                ]
            ],
            [ "Environment",
                "Windows 7, Eclipse, Swing, Ant, AspectJ, Angular, AJAX, JUnit, JIRA, SVN, DOORS, various com links & specific HW",
                [
                    "UDP/TCP IP",
                    "RS-XXX",
                    [ "https://en.wikipedia.org/wiki/Automatic_dependent_surveillance_%E2%80%93_broadcast", "ADS-B" ],
                    [ "https://www.kontron.de/industries/defense", "[Kontron] Embedded computer for military applications" ]
                ]
            ],
            [ "Keypoints",
                "C, Java, Javascript, JSON, XML, Asm x86, Asm ARM STR7" ],
            [ "Methodology",
                "AGILE software development" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot22 =
[
    "boulotentry22item" =>
        [
            "Project:",
            "CLA2000",
            "(1 year + 3 months)"
        ],
    "boulotentry22title" =>
        [ "Software engineer" ],
    "boulotentry22content" =>
        [
            [ "Description", "Military air control in SCCOA program",
                [
                    [ "https://www.nao.org.uk/defencevfm/wp-content/uploads/sites/16/2013/03/france_sccoa_mar_2004_1.pdf", "SCCOA" ],
                ]
            ],
            [ "Environment",
                "Linux, Eclipse, Hibernate, Ant, POJO, DOORS" ],
            [ "Keypoints",
                "Java, XML" ],
            [ "Methodology",
                "company internal process (~V cycle)" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot23 =
[
    "boulotentry23item" =>
        [
            "Project:",
            "LMT / GCTAM",
            "(2 years + 4 months)"
        ],
    "boulotentry23title" =>
        [ "Software engineer" ],
    "boulotentry23content" =>
        [
            [ "Description",
                [
                    "Supply flow handling in a military system",
                    "Pure Java rich client based on a multithreaded 3 tier architecture"
                ]
            ],
            [ "Environment",
                "Windows 7, Eclipse, Swing, Java Beans, Ant" ],
            [ "Keypoints",
                "Java" ],
            [ "Methodology",
                "Company internal process (~V cycle)" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot24 =
[
    "boulotentry24item" =>
        [
            "Project:",
            "MOIE Sic Terre",
            "(2 years + 7 months)"
        ],
    "boulotentry24title" =>
        [ "Software architect / Team leader (~6 developers)" ],
    "boulotentry24content" =>
        [
            [ "Description",
                [
                    "Message handling in a military weapon system communication infrastructure",
                    "Huge team ~100 people (70 SW developers)"
                ]
            ],
            [ "Environment", "
                Windows XP, Entreprise Architect, Eclipse, Swt, OSGI, J2EE, EJB, POJO, JAXB, Spring, SVN" ],
            [ "Keypoints",
                "Java, Python, Ant, XML, XSLT" ],
            [ "Methodology",
                "MDA Approach, Design Patterns" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot25 =
[
    "boulotentry25item" =>
        [
            "Project:",
            "Euromale",
            "(7 months)"
        ],
    "boulotentry25title" =>
        [ "Software architect / Team leader (~3 developers)" ],
    "boulotentry25content" =>
        [
            [ "Description",
                [
                    "Drone video data real-time acquisition and handling",
                    "Multithreaded 3 tier architecture with extensive use of design patterns: 
                    Creational (Abstract Factory / Builder / Factory method / Object pool / Singleton), 
                    Structural (Bridge / Facade), 
                    Behavioral (Command / Iterator / State), 
                    Concurrency (Scheduler) "
                ]
            ],
            [ "Environment", "
                Windows XP, Eclipse, Web Services, BEA WebLogic, PostgreSQL, SVN" ],
            [ "Keypoints",
                "Java, C++, .NET (C# / J#), SQL" ],
            [ "Methodology",
                "MDA Approach, Design Patterns" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot26 =
[
    "boulotentry26item" =>
        [
            "Project:",
            "Helios 2",
            "(4 years + 5 months)",
            "[mission for ALTEN/EADS FLEXIMAGE]"
        ],
    "boulotentry26title" =>
        [ "Software architect / Team leader (~6 developers)" ],
    "boulotentry26content" =>
        [
            [ "Description",
                [
                    "Geographic information with image management (2D & 3D) for mission preparation and intelligence",
                    "Heavily multithreaded 3 tier architecture (specific Parallel handling layer / error management layer)",
                    "Specific deployment platform (initially based on DOS/batch tools, then Emac/lisp tools))"
                ]
            ],
            [ "Environment",
                "Windows NT, Unix/CDE, Microsoft MFC, CORBA, ActiveX components, ORACLE DB, Rational suite (ClearCase), Bounds Checker" ],
            [ "Keypoints",
                "C++, SQL" ],
            [ "Methodology", "Rational Unified Process, Design Patterns" ]
        ],
    "boulotentry27title" =>
        [ "Trainer in ALTEN Learning School (~15 attendees)" ],
    "boulotentry27content" =>
        [
            [ "Description",
                "Sessions about coding and unit testing best practises (~3 hours)" ]
        ]
];

// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot3 =
[
    "boulotentry3date" =>
        [ "1999/10 - 2000/05" ],
    "boulotentry3boite" =>
        [
            "https://www.thalesgroup.com/fr/global/activities/transportation/urban-mobility/billettique-et-systemes-de-paiements",
            //          "https://www.thalesgroup.com/sites/default/files/database/d7/asset/document/transcity_ds400_bd_en.pdf"
            [
                "THALES group (former ALCATEL CGA Transport)",
                "[mission for ALTEN]"
            ]
        ],
    "boulotentry3desc" =>
        [ null, ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot31 =
[
    "boulotentry31item" =>
        [
            "Project:",
            "Wayfarer",
            "(7 month)"
        ],
    "boulotentry31title" =>
        [ "Software architect / Team leader (~4 developers)" ],
    "boulotentry31content" =>
        [
            [ "Description",
                [
                    "Real time embedded software inside bus ticketing console",
                    "Virtual machine for 386EX microcontroller emulation"
                ]
            ],
            [ "Environment",
                "Windows NT, Visual Studio, Microsoft MFC, Wayfarer BUS Ticketing Machine, 386EX microcontroller" ],
            [ "Keypoints",
                "C, C++, Asm x86" ],
            [ "Methodology", [
                "V-Model",
                "static & dynamic modeling (UML Diagrams)",
            ]]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot32 =
[
    "boulotentry32item" =>
        [
            "Project:",
            "GART",
            "(1 month)"
        ],
    "boulotentry32title" =>
        [
            "Software developer"
        ],
    "boulotentry32content" =>
        [
            [ "Description", "Contactless smartcard demonstration application" ],
            [ "Environment",
                "Windows NT, Visual Studio, Microsoft MFC, Serial RS-232 communication, Contactless smartcard antenna" ],
            [ "Keypoints",
                "C++" ]
        ]
];

// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot4 =
[
    "boulotentry4date" =>
        [
            "1998/07 - 1999/09"
        ],
    "boulotentry4boite" =>
        [
            "https://www.zodiacaerospace.com/en/products-services/aerosystems/data-systems/telemetry-ground-segment/equipment",
            [
                "ZODIAC (former INTERTECHNIQUE IN-SNEC)",
                "[mission for ALTEN]"
            ]
        ],
    "boulotentry4desc" =>
        [ null ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot41 =
[
    "boulotentry41item" =>
        [
            "Project:",
            "EUMETSAT",
            "(1 year + 3 months)"
        ],
    "boulotentry41title" =>
        [ "Software developer" ],
    "boulotentry41content" =>
        [
            [ "Description",
                [
                    "Regulation and transmission for Meteosat 2nd generationsatellite",
                    "Heavily multithreaded data flow handling",
                    "Real time data treatment",
                    "Data encoding (Reed-Solomon, Pseudo-Randomization, Convolutional)"
                ]
            ],
            [ "Environment",
                "Windows NT, Microsoft MFC, Windows DDK, DSP 56301, OOP, Design Patterns" ],
            [ "Keypoints",
                "C++, C, Asm DSP 56301" ],
            [ "Methodology", [
                "V-Model",
                "static & dynamic modeling (UML Diagrams)",
            ]]
        ]
];

// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot5 =
[
    "boulotentry5date" =>
        [ "1996/01 - 1998/06" ],
    "boulotentry5boite" =>
        [
            null,
            [
                "ATELIER (Groupe Ka)"
            ]
        ],
    "boulotentry5desc" =>
        [ null ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot51 =
[
    "boulotentry51item" =>
        [
            "Project:",
            "EDU",
            "(9 months)"
        ],
    "boulotentry51title" =>
        [ "Software developer" ],
    "boulotentry51content" =>
        [
            [ "Description",
                [
                    "Essential Disk Utilities: VFAT disk tools for Psion organizer (series 3 & 5): ",
                    "Defragmentation / Scandisk / Smart Format / Disk Editor"
                ]
            ],
            [ "Environment",
                "Windows NT, Psion EPOC(16/32), OOP" ],
            [ "Keypoints",
                "C, C++" ],
            [ "Methodology",
                "Static model, Booch diagrams" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot52 =
[
    "boulotentry52item" =>
        [
            "Project:",
            "PsiTools",
            "(1 year)"
        ],
    "boulotentry52title" =>
        [ "Software developer" ],
    "boulotentry52content" =>
        [
            [ "Description", "Save and restore utilities for Psion organizer (series 3 & 5)" ],
            [ "Environment",
                "Mac OS (6/7), Psion EPOC(16/32), Visual C++, Metrowerks CodeWarrior, OOP, Design Patterns" ],
            [ "Keypoints",
                "C, C++" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot53 =
[
    "boulotentry53item" =>
        [
            "Project:",
            "Velvet",
            "(9 months)"
        ],
    "boulotentry53title" =>
        [ "Software developer" ],
    "boulotentry53content" =>
        [
            [ "Description",
                [
                    "Faxing (classe 1 & 2) software",
                    "Device to device file transferts (X, Y and Z modem)",
                    "Asynchronous parts under interrupt (no real multitasking available)"
                ]
            ],
            [ "Environment",
                "Mac OS (6/7)" ],
            [ "Keypoints",
                "C, C++" ]
        ]
];
// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot6 =
[
    "boulotentry6date" =>
        [ "1992/04 - 1995/12" ],
    "boulotentry6boite" =>
        [
            "https://de.4d.com/",
            [
                "4D - France & USA (former ACI)"
            ]
        ],
    "boulotentry6desc" =>
        [ null ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot61 =
[
    "boulotentry61item" =>
        [
            "Product: ",
            "4th Dimension",
            "(1 year, with 6 month in USA)"
        ],
    "boulotentry61title" =>
        [ "Software developer" ],
    "boulotentry61content" =>
        [
            [ "Description",
                [
                    "Tools for 4th dimension (RDBMS IDE):",
                    [
                        "Platform Independant Extension Kit: tools and API for writing 4D plugins",
                        "4D plugins. For example : smartcard handling",
                        "4D Server connection API: samples & tests applications for writing 4D native clients"
                    ]
                ]
            ],
            [ "Environment",
                "Windows (3.11/WG/NT), Mac OS (6/7), MPW, Think C, Think Pascal, STL, MacAPP, OOP, MacsBug" ],
            [ "Keypoints",
                "C, C++, Pascal, Object Pascal, Asm 68K, Asm x86" ]
        ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot62 =
[
    "boulotentry62item" =>
        [
            "Products: ",
            "4th Dimension / Object Master",
            "(2 years + 8 month)"
        ],
    "boulotentry62title" =>
        [ "IT Technician / Software developer" ],
    "boulotentry62content" =>
        [
            [ "Description",
                [
                    "4th dimension (RDBMS IDE) hotline",
                    "Object Master (C/C++/Pascal/Modula2 IDE) hotline",
                    [
                        "Development problems handling",
                        "Samples and tools writing"
                    ]
                ]
            ],
            [ "Environment",
                "Windows (3.11/WG/NT), Mac OS (6/7), MPW, MacsBug" ],
            [ "Keypoints",
                "4th Dimension, C, C++, Pascal, Modula2" ]
        ]
];

// BOULOT ENTRY -----------------------------------------------------------------
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVboulot7 =
[
    "boulotentry7date" =>
        [ "1986/10 - 1992/03" ],
    "boulotentry7boite" =>
        [
            null,
            [
                "Centre d’Essai des Propulseurs de Saclay (DGA), GEET, LTC..."
            ]
        ],
    "boulotentry7desc" =>
        [ null ]
];
/// @brief fills clientside/cards/CVgridhist.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVboulot71 =
[
    "boulotentry71item" =>
        [
            "General: ",
            "",
            "(5 years + 5 month)"
        ],
    "boulotentry71title" =>
        [ "Software developer" ],
    "boulotentry71content" =>
        [
            ["Description",
                "Database development. EDM."],
            ["Environment",
                "Dbase2, MS-DOS, Mac OS (6)"],
            ["Keypoints",
                "4th Dimension, DOS cmds"]
        ]
];

// BILDUNG -----------------------------------------------------------------
/// @brief fills clientside/cards/CVcardbildung.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVbildung1 =
[
    "edu_f1date" =>
        [ "During last years..." ],
    "edu_f1boite" =>
        null,
    "edu_f1desc" =>
        null
];
/// @brief fills clientside/cards/CVcardbildung.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVbildung11 =
[
    "edu_f11item" =>
        [ "2017" ],
    "edu_f11title" =>
        [ "Training: aerospace systems SW development" ],
    "edu_f11content" =>
        [
            [
                "",
                "DO-278 Software Standards",
                [
                    [ "https://en.wikipedia.org/wiki/DO-178C", "Wikipedia..." ]
                ]
            ]
        ]
];
/// @brief fills clientside/cards/CVcardbildung.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVbildung12 =
[
    "edu_f12item" =>
        [ "2004" ],
    "edu_f12title" =>
        [ "Training: application server" ],
    "edu_f12content" =>
        [
            [
                "",
                "BEA WebLogic Integration",
                [
                    [ "https://docs.oracle.com/cd/E13222_01/wls/docs90/index.php", "BEA WebLogic" ]
                ]
            ]
        ]
];
/// @brief fills clientside/cards/CVcardbildung.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVbildung13 =
[
    "edu_f13item" =>
        [ "1985" ],
    "edu_f13title" =>
        [ "Diploma graduation" ],
    "edu_f13content" =>
        [
            [
                "",
                "Brevet de Formation Technique Aéronautique",
                [
                    [ "https://www.aerocampus-aquitaine.com/en/home/", "Aerocampus" ]
                ]
            ]
        ]
];

// LOISIRS -----------------------------------------------------------------
/// @brief fills clientside/cards/CVcardhobbyg.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYENTRY
$data_CVhobby1 =
[
    "loisirs_f1date" =>
        [ "Until now..." ],
    "loisirs_f1boite" =>
        null,
    "loisirs_f1desc" =>
        null,
];
/// @brief fills clientside/cards/CVcardhobbyg.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVhobby11 =
[
    "loisirs_f11item" =>
        [ "Activity" ],
    "loisirs_f11title" =>
        [ "Sport" ],
    "loisirs_f11content" =>
        [
            [ "Martial arts", "Aikido, Boxing" ],
            [ "Running",
                [
                    [ "https://www.abavent.de/anmeldeservice/muenchenmarathon2018/ergebnisse#1_CF28C6", "2018/10/09: München marathon" ],
                    "2018: B2Run",
                    "2017: B2Run",
                    "2016: München Stadtlauf, Marathon, B2Run",
                    "2004 - 2006: Paris semi marathon & marathon, 5K / 10K / 12K races",
                ]
            ],
            [ "Triathlon",
                [
                    [ "https://www.regensburger-triathlon.de/triathlon", "2018/08/05: Regensburg, Olympische Distanz" ],
                    [ "https://www.zeitgemaess.info/results.php?accesscode=201808051&konkurrenz=900002", "Ergebnisse" ]
                ],
                [
                    [ "http://www.tegernsee-triathlon.de/", "2018/07/01: Tegernsee, Olympische Distanz" ],
                    [ "https://my1.raceresult.com/91482/results?lang=de#1_0FC775", "Ergebnisse" ]
                ]
            ]
        ]
];
/// @brief fills clientside/cards/CVcardhobbyg.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVhobby12 =
[
    "loisirs_f12item" =>
        [ "Activity" ],
    "loisirs_f12title" =>
        [ "Music" ],
    "loisirs_f12content" =>
        [
            [ "Guitar", "classical & metal" ],
            [ "Bass guitar", "\"DFE\" (studies end cycle) 1st prize in 2011",
                [
                    [ "http://www.darizmusic.com/", "Francis Darizcuren School" ]
                ]
            ]
        ]
];
/// @brief fills clientside/cards/CVcardhobbyg.html
/// @brief handled by CLISIDE_CVCREATE::addHISTORYSUBENTRY
$data_CVhobby13 =
[
    "loisirs_f13item" =>
        [ "Interests" ],
    "loisirs_f13title" =>
        [ "Various" ],
    "loisirs_f13content" =>
        [
            [ "", "Travelling" ],
            [ "", "Gastronomic restaurants" ],
            [ "", "Classical & metal music" ],
            [ "", "Boardgaming & Role Playing games (oldschool way)" ],
            [ "", "To be continued..." ]
        ]
];
/// @brief
$data_CVmap =
[

    "data_CVtitle" => $data_CVtitle,
    "data_CVinfo" => $data_CVinfo,
    "data_CVexperience" => $data_CVexperience,
    "data_CVskillshead" => $data_CVskillshead,
    "data_CVskillsentries" => $data_CVskillsentries,
    "data_CVlanghead" => $data_CVlanghead,
    "data_CVlangentries" => $data_CVlangentries,

    "data_CVboulot0" => $data_CVboulot0,
    "data_CVboulot01" => $data_CVboulot01,

    "data_CVboulot1" => $data_CVboulot1,
    "data_CVboulot11" => $data_CVboulot11,

    "data_CVboulot2" => $data_CVboulot2,
    "data_CVboulot21" => $data_CVboulot21,
    "data_CVboulot22" => $data_CVboulot22,
    "data_CVboulot23" => $data_CVboulot23,
    "data_CVboulot24" => $data_CVboulot24,
    "data_CVboulot25" => $data_CVboulot25,
    "data_CVboulot26" => $data_CVboulot26,

    "data_CVboulot3" => $data_CVboulot3,
    "data_CVboulot31" => $data_CVboulot31,
    "data_CVboulot32" => $data_CVboulot32,

    "data_CVboulot4" => $data_CVboulot4,
    "data_CVboulot41" => $data_CVboulot41,

    "data_CVboulot5" => $data_CVboulot5,
    "data_CVboulot51" => $data_CVboulot51,
    "data_CVboulot52" => $data_CVboulot52,
    "data_CVboulot53" => $data_CVboulot53,

    "data_CVboulot6" => $data_CVboulot6,
    "data_CVboulot61" => $data_CVboulot61,
    "data_CVboulot62" => $data_CVboulot62,

    "data_CVboulot7" => $data_CVboulot7,
    "data_CVboulot71" => $data_CVboulot71,

    "data_CVbildung1" => $data_CVbildung1,
    "data_CVbildung11" => $data_CVbildung11,
    "data_CVbildung12" => $data_CVbildung12,
    "data_CVbildung13" => $data_CVbildung13,

    "data_CVhobby1" => $data_CVhobby1,
    "data_CVhobby11" => $data_CVhobby11,
    "data_CVhobby12" => $data_CVhobby12,
    "data_CVhobby13" => $data_CVhobby13

];