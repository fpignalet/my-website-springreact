//
// Created by fpi on 11.07.19.
//

#include <vector>
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

const char* EngTester::wrapper(JNIEnv *pEnv, jobject p1, jobject p2) {
    EngTester tester;
    return tester.execute(pEnv, p1, p2);
}

/*
static class Data {
    public int field1 = 0;
    public long field2 = 1;
    public float field3 = (float) 2.0;
    public double field4 = 2.0;
    public String field5 = "TEST";
    public int[] field6 = { 0, 1, 2 };
};
*/
const char* EngTester::execute(JNIEnv *pEnv, jobject p1, jobject p2){
    const jclass cls = pEnv->GetObjectClass(p2);

    readField1(pEnv, cls, p2);
    //readField2(pEnv, cls, p2);
    readField3(pEnv, cls, p2);
    readField4(pEnv, cls, p2);
    readField5(pEnv, cls, p2);
    readField6(pEnv, cls, p2);

    return sResult;
}

void EngTester::readField1(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field1VAL: " << readInt(pEnv, cls, "field1", data) << endl;
}

void EngTester::readField2(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field2VAL: " << readLong(pEnv, cls, "field2", data) << endl;
}

void EngTester::readField3(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field3VAL: " << readFloat(pEnv, cls, "field3", data) << endl;
}

void EngTester::readField4(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field4VAL: " << readDouble(pEnv, cls, "field4", data) << endl;
}

void EngTester::readField5(JNIEnv *pEnv, jclass cls, jobject data) {
    cout << "field5VAL: " << readString(pEnv, cls, "field5", data) << endl;
}

void EngTester::readField6(JNIEnv *pEnv, jclass cls, jobject data) {
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
