//
// Created by fpi on 11.07.19.
//

#include <algorithm>

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
