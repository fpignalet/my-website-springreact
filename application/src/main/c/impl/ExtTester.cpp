//
// Created by fpi on 11.07.19.
//

#include <stdio.h>     // Standard input/output Definitionen
#include <string.h>    // String Funktionen Definitionen
#include <unistd.h>    // UNIX Standard Funktionen Definitionen
#include <fcntl.h>     // File control Definitionen
#include <errno.h>     // Error number definitionen
#include <termios.h>   // POSIX terminal control Definitionen
#include <inttypes.h>  // Spezielle int-Datentypen
#include <sys/ioctl.h>

#include <algorithm>
#include <cstring>
#include <iostream>

#include "../headers/com_core_ext_ExtFacade.h"
#include "../headers/ExtTester.h"

using namespace std;
using namespace website;

const char* ExtJNIEntry::sTypeInt = "I";
const char* ExtJNIEntry::sTypeLong = "L";
const char* ExtJNIEntry::sTypeFloat = "F";
const char* ExtJNIEntry::sTypeDouble = "D";
const char* ExtJNIEntry::sTypeString = "Ljava/lang/String;";
const char* ExtJNIEntry::sTypeArrayInt = "[I";

const char *ExtTester::sHeader = (char*)"library received Data.";
const char *ExtTester::sResult = (char*)"RESULT FROM LIBRARY";
const char *ExtTester::sError = (char*)"ERROR FROM LIBRARY";
const char *ExtTester::sReplies[] = {
    "test string 1 from library",
    "test string 2 from library",
    "test string 3 from library",
    "test string 4 from library",
    "test string 5 from library"
};

const char* ExtTester::wrapper_DataIn(JNIEnv *pEnv, jobject p1, jobject p2) {
    try {
        /// no memory allocation. object created on stack
        ExtTester tester(pEnv);
        return(tester.execute_DataIn(p1, p2));
    }
    catch (...){
        return sError;
    }
}

const jobjectArray ExtTester::wrapper_ArrayStringOut(JNIEnv *pEnv, jobject p1) {
    try {
        /// no memory allocation. object created on stack
        ExtTester tester(pEnv);
        return(tester.execute_ArrayStringOut(p1));
    }
    catch (...){
        return NULL;
    }
}

const void ExtTester::wrapper_SerialPortOps(JNIEnv *pEnv, jobject p1, jobject p2) {
    try {
        /// no memory allocation. object created on stack
        ExtTester tester(pEnv);
        tester.execute_SerialPortOps(p1, p2);
    }
    catch (...){
//        return sError;
    }
}

ExtTester::ExtTester(JNIEnv *pEnv): ExtJNIEntry(pEnv){
}

ExtTester::~ExtTester(){
}

const char* ExtTester::execute_DataIn(jobject p1, jobject p2){
    const jclass cls = getEnv()->GetObjectClass(p2);

    /* here I receive this:
    public class com.core.ext.ExtFacade$Data {
      public int field1;
        descriptor: I
      public long field2;
        descriptor: J
      public float field3;
        descriptor: F
      public double field4;
        descriptor: D
      public java.lang.String field5;
        descriptor: Ljava/lang/String;
      public int[] field6;
        descriptor: [I
      public com.core.ext.ExtFacade$Data();
        descriptor: ()V
    }
     */
    read_field1(cls, p2);
    //read_field2(cls, p2);
    read_field3(cls, p2);
    read_field4(cls, p2);
    read_field5(cls, p2);
    read_field6(cls, p2);

    return(ExtTester::sResult);
}

const jobjectArray ExtTester::execute_ArrayStringOut(jobject p1){
    const vector<string> v(sReplies, end(sReplies));
    return(fillStringArray(v));
}

const void ExtTester::execute_SerialPortOps(jobject p1, jobject p2) {
    const jclass cls = getEnv()->GetObjectClass(p2);

    /* here I receive this:
    public class com.core.ext.ExtFacade$Com {
      final com.core.ext.ExtFacade$Com$Way way;
        descriptor: Lcom/core/ext/ExtFacade$Com$Way;
      final java.lang.String portName;
        descriptor: Ljava/lang/String;
      public java.lang.String bufferIn;
        descriptor: Ljava/lang/String;
      public java.lang.String bufferOut;
        descriptor: Ljava/lang/String;
      public com.core.ext.ExtFacade$Com(java.lang.String, com.core.ext.ExtFacade$Com$Way);
        descriptor: (Ljava/lang/String;Lcom/core/ext/ExtFacade$Com$Way;)V
    }
    */

    const jstring portName = read_portName(cls, p2);
    const char *nportName = getEnv()->GetStringUTFChars(portName, 0);
    cout << sHeader << "field portName" << ": " << nportName << endl;

    /* UNDER CONSTRUCTION/INVESTIGATION... next CallIntMethod doesn't work
    const jstring bufferIn = read_bufferIn(cls, p2);
    const char *nbufferIn = getEnv()->GetStringUTFChars(bufferIn, 0);
    cout << sHeader << "field bufferIn" << ": " << nbufferIn << endl;

    const jobject comWay = read_comWay(cls, p2);
    cout << sHeader << "found enum ExtFacade$Com$Way..." << endl;
    const jmethodID method = getEnv()->GetMethodID(getEnv()->FindClass("com/core/ext/ExtFacade$Com$Way"), "ordinal", "()I");
    cout << sHeader << "found method ordinal..." << endl;
    const JINT comValue = getEnv()->CallIntMethod(comWay, method);
    cout << sHeader << "field comWay" << ": " << comValue << endl;

    ExtSerial serial((char*)nportName);
    const int size = 1024;
    char bufferOut[size];

    switch (comValue) {
        case -1:
            serial.sendData(nbufferIn);
            break;
        case 0:
            serial.readData(bufferOut);
            break;
    }

    getEnv()->ReleaseStringUTFChars(bufferIn, nbufferIn);
    */
    getEnv()->ReleaseStringUTFChars(portName, nportName);
}

void ExtTester::read_field1(jclass cls, jobject data) {
    const string fieldName("field1");
    const JINT result = parseInt(cls, fieldName, data);
    cout << sHeader << fieldName << ": " << result << endl;
}

void ExtTester::read_field2(jclass cls, jobject data) {
    const string fieldName("field2");
    const JLONG result = parseLong(cls, fieldName, data);
    cout << sHeader << fieldName << ": " << result << endl;
}

void ExtTester::read_field3(jclass cls, jobject data) {
    const string fieldName("field3");
    const jfloat result = parseFloat(cls, fieldName, data);
    cout << sHeader << fieldName << ": " << result << endl;
}

void ExtTester::read_field4(jclass cls, jobject data) {
    const string fieldName("field4");
    const jdouble result = parseDouble(cls, fieldName, data);
    cout << sHeader << fieldName << ": " << result << endl;
}

void ExtTester::read_field5(jclass cls, jobject data) {
    const string fieldName("field5");
    const jstring result = parseString(cls, fieldName, data);
    cout << sHeader << fieldName << ": " << result << endl;
}

void ExtTester::read_field6(jclass cls, jobject data) {
    const string fieldName("field6");
    cout << sHeader << fieldName << ": " << endl;
    parseIntArray(cls, "field6", data); ///uses parseIntArray_cbk
}

jstring ExtTester::read_portName(jclass cls, jobject data) {
    const string fieldName("portName");
    const jstring result = parseString(cls, fieldName, data);
    cout << sHeader << fieldName << ": " << result << endl;
    return result;
}

jobject ExtTester::read_comWay(jclass cls, jobject data) {
    const string fieldName("way");
    const jobject result = parseObject(cls, fieldName, data);
    cout << sHeader << fieldName << ": ..." << endl;
    return result;
}

jstring ExtTester::read_bufferIn(jclass cls, jobject data) {
    const string fieldName("bufferIn");
    const jstring result = parseString(cls, fieldName, data);
    cout << sHeader << fieldName << ": " << result << endl;
    return result;
}

void ExtTester::parseIntArray_cbk(const JINT& val) {
    cout << val << endl;
}

void ExtTester::fillStringArray_cbk(jobjectArray ret, int index , const string& val) {
    const jstring c_str = getEnv()->NewStringUTF(val.c_str());
    getEnv()->SetObjectArrayElement(ret, index, c_str);
}

ExtJNIEntry::ExtJNIEntry(JNIEnv *pEnv){
    this->pEnv = pEnv;
}

ExtJNIEntry::~ExtJNIEntry(){
}

JNIEnv* ExtJNIEntry::getEnv(){
    return this->pEnv;
}

JINT ExtJNIEntry::parseInt(jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeInt);
    return pEnv->GetIntField(data, fieldId);
}

JLONG ExtJNIEntry::parseLong(jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeLong);
    return pEnv->GetLongField(data, fieldId);
}

jfloat ExtJNIEntry::parseFloat(jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeFloat);
    return pEnv->GetFloatField(data, fieldId);
}

jdouble ExtJNIEntry::parseDouble(jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeDouble);
    return pEnv->GetDoubleField(data, fieldId);
}

const jstring ExtJNIEntry::parseString(jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeString);
    const jobject tmpValue = pEnv->GetObjectField(data, fieldId);
    return reinterpret_cast<jstring >(tmpValue);
}

const jobject ExtJNIEntry::parseObject(jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeString);
    return pEnv->GetObjectField(data, fieldId);
}

void ExtJNIEntry::parseIntArray(jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeArrayInt);
    const jobject tmpValue = pEnv->GetObjectField(data, fieldId);
    const jintArray fieldValue = reinterpret_cast<jintArray>(tmpValue);

    const jsize length = pEnv->GetArrayLength(fieldValue);
    const JINT *fieldItems = pEnv->GetIntArrayElements(fieldValue, 0);
    const vector<jint> v(fieldItems, fieldItems + length);

    ExtJNIEntry *pLocal = this;
    for_each(v.begin(), v.end(), [pLocal](const JINT& val) {
        pLocal->parseIntArray_cbk(val);
    });
}

const jobjectArray ExtJNIEntry::fillStringArray(const vector<string>& v) {
    const jclass cls = pEnv->FindClass(sTypeString);
    const jstring str = pEnv->NewStringUTF("");
    const jobjectArray ret = pEnv->NewObjectArray(v.size(), cls, str);

    int i = 0;
    ExtJNIEntry *pLocal = this;
    for_each(v.begin(), v.end(), [pLocal, ret, &i](const string& val) {
        pLocal->fillStringArray_cbk(ret, i, val);
        i++;
    });

    return(ret);
}

ExtSerial::ExtSerial(const char* psPortName) {
    dev = (char*)psPortName;

    baud = 9600;
    dataBits = 8;
    bufferSize = 1000;
    parity = PARITY_8N1;
    bufferIndex = 0;
    blocking = 0;
}

ExtSerial::~ExtSerial() {
    closePort();
}

int ExtSerial::initPort() {

    printf("SERIAL: Opening %s at %i baud...", dev, baud);
    fd = open(dev, O_RDWR | O_NOCTTY | O_NDELAY);
    if(-1 != fd) {
        if (blocking == 1) printf("SERIAL: Blocking enabled\n");
        else printf("SERIAL: Blocking disabled\n");

        fcntl(fd, F_SETFL, FNDELAY);

        struct termios options;
        tcgetattr(fd, &options);

        switch (baud) {
            case 4800:
                cfsetispeed(&options, B4800);
                cfsetospeed(&options, B4800);
                break;
            default:
            case 9600:
                cfsetispeed(&options, B9600);
                cfsetospeed(&options, B9600);
                break;
            case 38400:
                cfsetispeed(&options, B38400);
                cfsetospeed(&options, B38400);
                break;
            case 57600:
                cfsetispeed(&options, B57600);
                cfsetospeed(&options, B57600);
                break;
            case 115200:
                cfsetispeed(&options, B115200);
                cfsetospeed(&options, B115200);
                break;
        }

        options.c_cflag |= (CLOCAL | CREAD);

        switch (parity) {
            case PARITY_7E1:
                options.c_cflag |= PARENB;
                options.c_cflag &= ~PARODD;
                options.c_cflag &= ~CSTOPB;
                printf("SERIAL: Parity set to 7E1\n");
                break;
            case PARITY_8N1:
                options.c_cflag |= CS8;
                printf("SERIAL: Parity set to 8N1\n");
                break;
            default:
                options.c_cflag |= CS8;
                printf("SERIAL: No parity set\n");
                break;
        }

        switch (dataBits) {
            case 7:
                options.c_cflag |= CS7;
                printf("SERIAL: Databits set to 7\n");
                break;
            case 8:
                options.c_cflag |= CS8;
                printf("SERIAL: Databits set to 8\n");
                break;
            default:
                options.c_cflag |= CS8;
                printf("SERIAL: Databits not set!\n");
                break;
        }

        options.c_cflag &= ~CRTSCTS;
        options.c_lflag &= ~(ICANON | ECHO | ECHOE | ISIG);

        tcsetattr(fd, TCSANOW, &options);
    }
    else {
        printf("FAIL\n");
        perror("Error opening port:");
        closePort();
        exit(1);
    }

    return fd;
}

void ExtSerial::flushPort() {
    if (fd != -1) ioctl(fd, TCFLSH, 2);
}

int ExtSerial::getData(char* data) {
    if (fd != -1) {
        return read(fd, data, sizeof(data));
    }
    else return -1;
}

int ExtSerial::sendData(char* data) {
    if (fd != -1) {
        return write(fd, data, sizeof(data));
    }
    else return -1;
}

char ExtSerial::getChar() {
    int delay = 0;
    switch (blocking) {

        // Non-blocking mode enable, so use the buffer
        case 0:
            if (bufferIndex > 0) {
                // Yes, so grab the byte we need and shift the buffer left
                char c = buffer[1];
                for (int j = 1; j < (bufferIndex + 1); j++) {
                    buffer[j] = buffer[j + 1];
                }
                bufferIndex--;
                // Send back the first byte in the buffer
                return c;
            }
            // If the port is actually open, grab a byte
            if (fd != -1) {
                // Return the byte received if it's there
                int n = read(fd, &temp[0], 1);
                if (n > -1) {
                    // Just 1 byte, so return it right away
                    if (n == 1) return temp[0];
                        // More than 1 byte there, so buffer them and return the first
                    else {
                        for (int i = 0; i < (n - 1); i++) {
                            bufferIndex++;
                            buffer[bufferIndex] = temp[i + 1];
                        }
                        return temp[0];
                    }
                }
                else return -1;
            }
            break;

        // Blocking mode enabled, so wait until we get a byte
        case 1:
            if (bufferIndex > 0) {
                // Yes, so grab the byte we need and shift the buffer left
                char c = buffer[1];
                for (int j = 1; j < (bufferIndex + 1); j++) {
                    buffer[j] = buffer[j + 1];
                }
                bufferIndex--;
                // Send back the first byte in the buffer
                return c;
            }
            // If the port is actually open, grab a byte
            if (fd != -1) {
                // Return the byte received if it's there
                int n = -1;
                while (n < 1) {
                    n = read(fd, &temp[0], 1);
                    if (n == -1) usleep(10000);	// wait 10ms
                    delay++;
                    if (delay == 5) return -1;
                }
                if (n > -1) {
                    // Just 1 byte, so return it right away
                    if (n == 1) return temp[0];
                    // More than 1 byte there, so buffer them and return the first
                    else {
                        for (int i = 0; i < (n - 1); i++) {
                            bufferIndex++;
                            buffer[bufferIndex] = temp[i + 1];
                        }
                        return temp[0];
                    }
                }
                else return -1;
            }
            else return -1;

        // Blocking variable is messed up
        default:
            printf("SERIAL: Error with blocking setting!\n");
            return -1;

    }

    // Should never get here
    return -1;
}

int ExtSerial::sendChar(char data) {
    switch (blocking) {

        // Non-blocking mode
        case 0:
            // If the port is actually open, send a byte
            if (fd != -1) {
                // Send the data and return number of bytes actually written
                write(fd, &data, 1);
                return 1;
            }
            else return -1;

        // Blocking mode
        case 1:
            // If the port is actually open, send a byte
            if (fd != -1) {
                // Send the data and return number of bytes actually written
                write(fd, &data, 1);
                return 1;
            }
            else return -1;

        // Blocking variable is messed up
        default:
            printf("SERIAL: Error with blocking setting!\n");
            return -1;
    }
}

void ExtSerial::closePort() {
    if (fd != -1) {
        close(fd);
        printf("SERIAL: Device %s is now closed.\n", dev);
    }
}
