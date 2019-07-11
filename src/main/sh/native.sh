#!/usr/bin/env bash

mkdir target/libs
javac -h src/main/c/headers src/main/java/com/core/eng/EngExternal.java
g++ -c -o target/libs/engexternal.o -Wall -Werror -fpic -I/usr/lib/jvm/jdk-11.0.3/include -I/usr/lib/jvm/jdk-11.0.3/include/linux -I./ src/main/c/impl/EngExternal.cpp
g++ -c -o target/libs/engtester.o -Wall -Werror -fpic -I/usr/lib/jvm/jdk-11.0.3/include -I/usr/lib/jvm/jdk-11.0.3/include/linux -I./ src/main/c/impl/EngTester.cpp
g++ -shared -static-libstdc++ -o target/libs/libengexternal.so target/libs/engexternal.o target/libs/engtester.o
rm src/main/java/com/core/eng/EngExternal*.class
#make