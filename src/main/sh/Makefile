all: target/libs/libengexternal.so

target/libs/libengexternal.so: target/libs/engexternal.o target/libs/engtester.o
	g++ -shared -static-libstdc++ -o target/libs/libengexternal.so target/libs/engexternal.o target/libs/engtester.o

target/libs/engexternal.o: src/main/c/impl/EngExternal.cpp
	g++ -c -o target/libs/engexternal.o -Wall -Werror -fpic -I/usr/lib/jvm/jdk-11.0.3/include -I/usr/lib/jvm/jdk-11.0.3/include/linux -I./ src/main/c/impl/EngExternal.cpp

target/libs/engtester.o: src/main/c/impl/EngTester.cpp
	g++ -c -o target/libs/engtester.o -Wall -Werror -fpic -I/usr/lib/jvm/jdk-11.0.3/include -I/usr/lib/jvm/jdk-11.0.3/include/linux -I./ src/main/c/impl/EngTester.cpp

clean:
	rm src/main/java/com/core/eng/EngExternal*.class
