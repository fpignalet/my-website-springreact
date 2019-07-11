//
// Created by fpi on 11.07.19.
//

#include <algorithm>

#include "../headers/com_core_eng_EngExternal.h"
#include "../headers/EngTester.h"

using namespace std;
using namespace website;

static const char* sTypeInt = "I";
static const char* sTypeLong = "L";
static const char* sTypeFloat = "F";
static const char* sTypeDouble = "D";
static const char* sTypeString = "Ljava/lang/String;";
static const char* sTypeArrayInt = "[I";

static const char *sResult = (char*)"RESULT FROM CPP";

static const char *sReplies[] = {
    "test string 1 from library",
    "test string 2 from library",
    "test string 3 from library",
    "test string 4 from library",
    "test string 5 from library"
};

const char* EngTester::wrapper_in(JNIEnv *pEnv, jobject p1, jobject p2) {
    EngTester tester;
    return(tester.execute_in(pEnv, p1, p2));
}

const jobjectArray EngTester::wrapper_out(JNIEnv *pEnv, jobject p1) {
    EngTester tester;
    return(tester.execute_out(pEnv, p1));
}

const char* EngTester::execute_in(JNIEnv *pEnv, jobject p1, jobject p2){
    const jclass cls = pEnv->GetObjectClass(p2);

    read_field1(pEnv, cls, p2);
    //read_field2(pEnv, cls, p2);
    read_field3(pEnv, cls, p2);
    read_field4(pEnv, cls, p2);
    read_field5(pEnv, cls, p2);
    read_field6(pEnv, cls, p2);

    return(sResult);
}

const jobjectArray EngTester::execute_out(JNIEnv *pEnv, jobject p1){
    const vector<string> v(sReplies, end(sReplies));
    return(fillStringArray(pEnv, v));
}

void EngTester::read_field1(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field1VAL: " << readInt(pEnv, cls, "field1", data) << endl;
}

void EngTester::read_field2(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field2VAL: " << readLong(pEnv, cls, "field2", data) << endl;
}

void EngTester::read_field3(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field3VAL: " << readFloat(pEnv, cls, "field3", data) << endl;
}

void EngTester::read_field4(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field4VAL: " << readDouble(pEnv, cls, "field4", data) << endl;
}

void EngTester::read_field5(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field5VAL: " << readString(pEnv, cls, "field5", data) << endl;
}

void EngTester::read_field6(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field6VAL: "<< endl;
    parseIntArray(pEnv, cls, "field6", data);
}

jint EngJNIEntry::readInt(JNIEnv *pEnv, jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeInt);
    return pEnv->GetIntField(data, fieldId);
}

jlong EngJNIEntry::readLong(JNIEnv *pEnv, jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeLong);
    return pEnv->GetLongField(data, fieldId);
}

jfloat EngJNIEntry::readFloat(JNIEnv *pEnv, jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeFloat);
    return pEnv->GetFloatField(data, fieldId);
}

jdouble EngJNIEntry::readDouble(JNIEnv *pEnv, jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeDouble);
    return pEnv->GetDoubleField(data, fieldId);
}

const char* EngJNIEntry::readString(JNIEnv *pEnv, jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeString);
    const jstring fieldValue = (jstring)pEnv->GetObjectField(data, fieldId);
    return pEnv->GetStringUTFChars(fieldValue, 0);
}

void EngJNIEntry::parseIntArray(JNIEnv *pEnv, jclass cls, string name, jobject data) {
    const jfieldID fieldId = pEnv->GetFieldID(cls, name.c_str(), sTypeArrayInt);
    const jintArray fieldValue = (jintArray)pEnv->GetObjectField(data, fieldId);
    const jsize length = pEnv->GetArrayLength(fieldValue);
    const jint *fieldItems = pEnv->GetIntArrayElements(fieldValue, 0);
    const vector<jint> v(fieldItems, fieldItems + length);
    for_each(v.begin(), v.end(), [](const jint& val) { cout << val << endl; });
}

const jobjectArray EngJNIEntry::fillStringArray(JNIEnv *pEnv, const vector<string>& v) {
    const jobjectArray ret = (jobjectArray)pEnv->NewObjectArray
            (v.size(), pEnv->FindClass(sTypeString), pEnv->NewStringUTF(""));
    int i = 0;
    for_each(v.begin(), v.end(), [pEnv, ret, &i](const string& val) {
        pEnv->SetObjectArrayElement(ret, i, pEnv->NewStringUTF(val.c_str()));
        i++;
    });

    return(ret);
}
