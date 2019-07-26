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

const void execute_SerialPortOps(jobject p1, jobject p2) {
    ExtSerial serial("");

    serial.serial_write((unsigned char *) "INIT \r");

    const int size = 1024;
    unsigned char rsp[size];
    serial.serial_read(rsp, size);
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

ExtSerial::ExtSerial(const char* strPortName){
    serial = serial_open(strPortName);
}

int ExtSerial::serial_open(const char* strPortName) {
    return open( strPortName, O_RDWR| O_NOCTTY );
}

void ExtSerial::serial_configure() {
    struct termios tty;
    struct termios tty_old;
    memset (&tty, 0, sizeof tty);

    /* Error Handling */
    if (tcgetattr (serial, &tty ) != 0 ) {
        std::cout << "Error " << errno << " from tcgetattr: " << strerror(errno) << std::endl;
    }

    /* Save old tty parameters */
    tty_old = tty;
    /* Set Baud Rate */
    cfsetospeed (&tty, (speed_t)B9600);
    cfsetispeed (&tty, (speed_t)B9600);
    /* Setting other Port Stuff */
    tty.c_cflag     &=  ~PARENB;            // Make 8n1
    tty.c_cflag     &=  ~CSTOPB;
    tty.c_cflag     &=  ~CSIZE;
    tty.c_cflag     |=  CS8;
    tty.c_cflag     &=  ~CRTSCTS;           // no flow control
    tty.c_cc[VMIN]   =  1;                  // read doesn't block
    tty.c_cc[VTIME]  =  5;                  // 0.5 seconds read timeout
    tty.c_cflag     |=  CREAD | CLOCAL;     // turn on READ & ignore ctrl lines
    /* Make raw */
    cfmakeraw(&tty);

    /* Flush Port, then applies attributes */
    tcflush(serial, TCIFLUSH );

    /* Error Handling */
    if (tcsetattr (serial, TCSANOW, &tty ) != 0) {
        std::cout << "Error " << errno << " from tcsetattr: " << strerror(errno) << std::endl;
    }
}

void ExtSerial::serial_write(unsigned char cmd[]) {
    int n_written = 0,
        spot = 0;

    do {
        n_written = write(serial, &cmd[spot], 1 );
        spot += n_written;
    } while (cmd[spot-1] != '\r' && n_written > 0);
}

void ExtSerial::serial_read(unsigned char rsp[], int bytes2Read) {
    int n_read = 0,
        spot = 0;

    char buf = '\0';
    /* Whole response*/
    memset(rsp, '\0', bytes2Read);

    do {
        n_read = read(serial, &buf, 1 );
        sprintf(reinterpret_cast<char *>(&rsp[spot]), "%c", buf );
        spot += n_read;
    } while( buf != '\r' && n_read > 0);

    if (n_read < 0) {
        std::cout << "Error reading: " << strerror(errno) << std::endl;
    }
    else if (n_read == 0) {
        std::cout << "Read nothing!" << std::endl;
    }
    else {
        std::cout << "Response: " << rsp << std::endl;
    }
}

ExtSerial2::ExtSerial2() {

    // Set up initial values
    dev = reinterpret_cast<char *>((unsigned char *) "/dev/ttyS0");
    baud = 9600;
    dataBits = 8;
    bufferSize = 1000;
    parity = PARITY_8N1;
    bufferIndex = 0;
    blocking = 0;
}

ExtSerial2::~ExtSerial2() {
    // Kills the port
    closePort();
}

int ExtSerial2::initPort() {

    // Try to open serial port with r/w access
    printf("SERIAL: Opening %s at %i baud...", dev, baud);
    fd = open(dev, O_RDWR | O_NOCTTY | O_NDELAY);

    // As long as port is actually open...
    if(fd != -1) {

        // Share the good news
        printf("OK\n");

        // Display blocking info
        if (blocking == 1) printf("SERIAL: Blocking enabled\n");
        else printf("SERIAL: Blocking disabled\n");

        // Configure port settings
        fcntl(fd, F_SETFL, FNDELAY);

        // Save current port settings so we don't corrupt anything on exit
        struct termios options;
        tcgetattr(fd, &options);

        // Convert integer baud to Baud type
        // Default to 9600 baud if none specified
        switch (baud) {
            case 4800:
                cfsetispeed(&options, B4800);
                cfsetospeed(&options, B4800);
                break;
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
            default:
                cfsetispeed(&options, B9600);
                cfsetospeed(&options, B9600);
                break;
        }

        // Set options for proper port settings
        //	ie. 8 Data bits, No parity, 1 stop bit
        options.c_cflag |= (CLOCAL | CREAD);

        switch (parity) {
            case PARITY_7E1:
                // Parity, odd, 1 stop bit (7E1)
                options.c_cflag |= PARENB;
                options.c_cflag &= ~PARODD;
                options.c_cflag &= ~CSTOPB;
                printf("SERIAL: Parity set to 7E1\n");
                break;
            case PARITY_8N1:
                // No parity, 1 stop bit (8N1)
                options.c_cflag |= CS8;
                printf("SERIAL: Parity set to 8N1\n");
                break;
            default:
                // No parity, 1 stop bit (8N1)
                options.c_cflag |= CS8;
                printf("SERIAL: No parity set\n");
                break;
        }

        // Set number of data bits.  Default is 8.
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

        // Turn off hardware flow control
        options.c_cflag &= ~CRTSCTS;
        options.c_lflag &= ~(ICANON | ECHO | ECHOE | ISIG);

        // Write our changes to the port configuration
        tcsetattr(fd, TCSANOW, &options);
    }

        // There was a problem, let's tell the user what it was
    else {
        printf("FAIL\n");
        perror("Error opening port:");
        closePort();
        exit(1);
    }

    // Send back the public port file descriptor
    return fd;
}

void ExtSerial2::flushPort() {

    // If the port is actually open, flush it
    if (fd != -1) ioctl(fd, TCFLSH, 2);
}

int ExtSerial2::getData(char* data) {

    // If the port is actually open, read the data
    if (fd != -1) {
        // Grab the data and return the nubmer of bytes actually read
        return read(fd, data, sizeof(data));
    }
        // Port is closed!
    else return -1;
}

int ExtSerial2::sendData(char* data) {

    // If the port is actually open, send the data
    if (fd != -1) {
        // Send the data and return the nubmer of bytes actually written
        //printf("Writing %s...\n", data);
        return write(fd, data, sizeof(data));
    }
        // Port is closed!
    else return -1;
}

char ExtSerial2::getChar() {
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
                // Send back the firt byte in the buffer
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
                // Send back the firt byte in the buffer
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
                // Port is closed!
            else return -1;
            // Blocking variable is messed up
        default:
            printf("SERIAL: Error with blocking setting!\n");
            return -1;
    }
    // Should never get here
    return -1;
}

int ExtSerial2::sendChar(char data) {
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

void ExtSerial2::closePort() {

    // If the port is actually open, close it
    if (fd != -1) {
        close(fd);
        printf("SERIAL: Device %s is now closed.\n", dev);
    }
}
