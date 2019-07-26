//
// Created by fpi on 11.07.19.
//

#ifndef C_EXTTESTER_H
#define C_EXTTESTER_H

#include <string>
#include <iostream>
#include <vector>

#include "../headers/com_core_ext_ExtFacade.h"

using namespace std;

//@brief just to avoid parsing errors everywhere in editor. don't know why there is issues with these 2 JNI types...
typedef jint JINT;
//@brief just to avoid parsing errors everywhere in editor. don't know why there is issues with these 2 JNI types...
typedef jlong JLONG;

///@namespace website which delimits classes accessible from Java layer
namespace website {

    ///@class base class which contains parsing material to access and format data exchanged between Java and native code
    class ExtJNIEntry {
    protected:
        ///@brief constructor. stores the JNI env pointer.
        ///@param pEnv is the pointer on JNI environment
        ExtJNIEntry(JNIEnv *pEnv);
        ///@brief destructor
        virtual ~ExtJNIEntry();

        ///@brief retrieve a int value from incoming data
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param name contains the name of the field to be found in data
        ///@param data contains the data passed by the calling Java object
        ///@return an int value
        JINT parseInt(jclass cls, string name, jobject data);

        ///@brief retrieve a long value from incoming data
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param name contains the name of the field to be found in data
        ///@param data contains the data passed by the calling Java object
        ///@return a long value
        JLONG parseLong(jclass cls, string name, jobject data);

        ///@brief retrieve a float value from incoming data
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param name contains the name of the field to be found in data
        ///@param data contains the data passed by the calling Java object
        ///@return a float value
        jfloat parseFloat(jclass cls, string name, jobject data);

        ///@brief retrieve a double value from incoming data
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param name contains the name of the field to be found in data
        ///@param data contains the data passed by the calling Java object
        ///@return a double value
        jdouble parseDouble(jclass cls, string name, jobject data);

        ///@brief retrieve a string value from incoming data
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param name contains the name of the field to be found in data
        ///@param data contains the data passed by the calling Java object
        ///@return a string value
        const jstring parseString(jclass cls, string name, jobject data);

        ///@brief retrieve an array of int from incoming data
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param name contains the name of the field to be found in data
        ///@param data contains the data passed by the calling Java object
        ///@note uses parseIntArray_cbk
        void parseIntArray(jclass cls, string name, jobject data);

        ///@brief prepares an array of string to be returned
        ///@param v is a vector containing a list of string
        ///@note uses fillStringArray_cbk
        const jobjectArray fillStringArray(const vector<string>& v);

        ///@brief accessor on JNI environment pointer
        JNIEnv* getEnv();

        ///@brief callback called for each item when parsing an array of int
        ///@param val contains the int value
        virtual void parseIntArray_cbk(const JINT& val) = 0;
        ///@brief callback called for each item when parsing an array of string
        ///@param ret contains the array to be filled
        ///@param index contains the index to be used
        ///@param val contains the string value
        virtual void fillStringArray_cbk(jobjectArray ret, int index , const string& val) = 0;

    private:
        ///@brief the Java/JNI type identifier for int
        static const char* sTypeInt;
        ///@brief the Java/JNI type identifier for long
        static const char* sTypeLong;
        ///@brief the Java/JNI type identifier for float
        static const char* sTypeFloat;
        ///@brief the Java/JNI type identifier for double
        static const char* sTypeDouble;
        ///@brief the Java/JNI type identifier for string
        static const char* sTypeString;
        ///@brief the Java/JNI type identifier for array of int
        static const char* sTypeArrayInt;

        ///@brief contains the JNI environment pointer
        JNIEnv *pEnv;

    };

    ///@class
    class ExtTester: protected ExtJNIEntry {
    public:
        ///@brief
        ///@param pEnv
        ///@param p1
        ///@param p2
        static const char* wrapper_DataIn(JNIEnv *pEnv, jobject p1, jobject p2);

        ///@brief
        ///@param pEnv
        ///@param p1
        static const jobjectArray wrapper_ArrayStringOut(JNIEnv *pEnv, jobject p1);

        ///@brief
        ///@param pEnv
        ///@param p1
        static const void wrapper_SerialPortOps(JNIEnv *pEnv, jobject p1, jobject p2);

    protected:
        ///@brief constructor
        ///@param pEnv is the pointer on JNI environment
        ExtTester(JNIEnv *pEnv);
        ///@brief destructor
        virtual ~ExtTester();

        ///@brief a method which instantiates an EngTester
        ///@param p1 is the first parameter passed through JNI inteface. Normally the instance of the Java calling object.
        ///@param p2 is the second parameter passed through JNI inteface. Normally the instance of the data passed by Java calling object.
        ///@return a string containing a debug message
        const char* execute_DataIn(jobject p1, jobject p2);

        ///@brief
        ///@param p1 is the first parameter passed through JNI inteface. normally the instance of the Java calling object.
        ///@return an array of string
        const jobjectArray execute_ArrayStringOut(jobject p1);

        ///@brief a method which ...
        ///@param p1 is the first parameter passed through JNI inteface. Normally the instance of the Java calling object.
        ///@param p2 is the second parameter passed through JNI inteface. Normally the instance of the data passed by Java calling object.
        ///@return a string containing a debug message
        const void execute_SerialPortOps(jobject p1, jobject p2);

        /*  Java test class is:
            static class Data {
                public int field1 = 0;
                public long field2 = 1;
                public float field3 = (float) 2.0;
                public double field4 = 2.0;
                public String field5 = "TEST";
                public int[] field6 = { 0, 1, 2 };
            };
        */
        ///@brief retrieves the content of Data.field1
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param data is the instance of the data passed by Java calling object
        void read_field1(jclass cls, jobject data);

        ///@brief retrieves the content of Data.field2
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param data is the instance of the data passed by Java calling object
        void read_field2(jclass cls, jobject data);

        ///@brief retrieves the content of Data.field3
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param data is the instance of the data passed by Java calling object
        void read_field3(jclass cls, jobject data);

        ///@brief retrieves the content of Data.field4
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param data is the instance of the data passed by Java calling object
        void read_field4(jclass cls, jobject data);

        ///@brief retrieves the content of Data.field5
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param data is the instance of the data passed by Java calling object
        void read_field5(jclass cls, jobject data);

        ///@brief retrieves the content of Data.field6
        ///@param cls contains the name of the data passed by the calling Java object
        ///@param data is the instance of the data passed by Java calling object
        void read_field6(jclass cls, jobject data);

        ///@brief overriding callback
        void parseIntArray_cbk(const JINT& val);
        ///@brief overriding callback
        void fillStringArray_cbk(jobjectArray ret, int index , const string& val);

    private:
        ///@brief a header message for debug
        static const char *sHeader;
        ///@brief a debug message to be returned
        static const char *sResult;
        ///@brief an error message to be returned
        static const char *sError;
        ///@brief a test array to be returned
        static const char *sReplies[];

    };

    ///@class
    class ExtSerial {
    public:
        ExtSerial(const char* strPortName);

        ///@brief
        void serial_write(unsigned char cmd[]);

        ///@brief
        void serial_read(unsigned char rsp[], int bytes2Read);

    protected:
        ///@brief
        int serial_open(const char* strPortName);

        ///@brief
        void serial_configure();

    private:
        int serial;

    };

    ///@brief code ripped from https://forum.arduino.cc/index.php?topic=52584.0
    class ExtSerial2 {
    public:
        ExtSerial2();
        ~ExtSerial2();

        int initPort();
        void flushPort();
        int getData(char* data);
        int sendData(char* data);
        char getChar();
        int sendChar(char data);
        void closePort();

    protected:
        enum PARITY {
            PARITY_7E1,
            PARITY_8N1,
            DEFAULT
        };

    private:
        char* dev;

        int fd;

        int baud;
        int dataBits;
        PARITY parity;
        int bufferIndex;
        int blocking;

        int bufferSize;
        int buffer[2000];
        int temp[2000];
    };
}

#endif //C_EXTTESTER_H
