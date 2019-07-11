#!/usr/bin/env bash
mkdir target/libs
javac -h src/main/c/headers src/main/java/com/core/eng/EngExternal.java
g++ -c -o target/libs/engexternal.o -Wall -Werror -fpic -I/usr/lib/jvm/jdk-11.0.3/include -I/usr/lib/jvm/jdk-11.0.3/include/linux src/main/c/impl/EngExternal.cpp
g++ -shared -static-libstdc++ -o target/libs/libengexternal.so target/libs/engexternal.o
rm src/main/java/com/core/eng/EngExternal*.class
