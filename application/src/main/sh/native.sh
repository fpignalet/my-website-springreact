#!/usr/bin/env bash

mkdir target/libs
javac -h src/main/c/headers src/main/java/com/core/ext/ExtFacade.java
g++ -c -o target/libs/extfacade.o -Wall -Werror -fpic -I/usr/lib/jvm/jdk-11.0.3/include -I/usr/lib/jvm/jdk-11.0.3/include/linux -I./ src/main/c/impl/ExtFacade.cpp
g++ -c -o target/libs/exttester.o -Wall -Werror -fpic -I/usr/lib/jvm/jdk-11.0.3/include -I/usr/lib/jvm/jdk-11.0.3/include/linux -I./ src/main/c/impl/ExtTester.cpp
g++ -shared -static-libstdc++ -o target/libs/libextfacade.so target/libs/extfacade.o target/libs/exttester.o
rm src/main/java/com/core/ext/ExtFacade*.class
#make
