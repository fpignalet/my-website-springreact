PATH_JDK=/usr/lib/jvm/jdk-11.0.3
PATH_JSRC=../java/com/core/ext
PATH_CSRC=../c
PATH_TARGET=../../../target
PATH_LIBS=$(PATH_TARGET)/libs

NAME=$(PATH_LIBS)/libextfacade.so
OBJECTS=$(PATH_LIBS)/extfacade.o $(PATH_LIBS)/exttester.o
JNIHEADER=$(PATH_CSRC)/headers/com_core_ext_ExtFacade.h

all: $(PATH_LIBS) $(NAME)

$(PATH_LIBS):
	mkdir $(PATH_TARGET)
	mkdir $(PATH_LIBS)

$(NAME): $(OBJECTS)
	g++ -shared -static-libstdc++ -o $(NAME) $(OBJECTS)

$(PATH_LIBS)/extfacade.o: $(JNIHEADER) $(PATH_CSRC)/impl/ExtFacade.cpp
	g++ -c -Wall -Werror -fpic -I${PATH_JDK}/include -I${PATH_JDK}/include/linux -I./ -o ${PATH_LIBS}/extfacade.o ${PATH_CSRC}/impl/ExtFacade.cpp

$(PATH_LIBS)/exttester.o: $(JNIHEADER) $(PATH_CSRC)/impl/ExtTester.cpp
	g++ -c -Wall -Werror -fpic -I${PATH_JDK}/include -I${PATH_JDK}/include/linux -I./ -o ${PATH_LIBS}/exttester.o ${PATH_CSRC}/impl/ExtTester.cpp

$(JNIHEADER):
	javac -h ${PATH_CSRC}/headers ${PATH_JSRC}/ExtFacade.java
	javap -s ${PATH_JSRC}/*.class > ExtSignatures.txt

clean:
	rm ${PATH_LIBS}/*.o
	rm ${PATH_JSRC}/*.class
