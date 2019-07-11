#include <iostream>
#include <string.h>

#include "../headers/com_core_eng_EngExternal.h"

using namespace std;

static char *sResult = (char*)"RESULT FROM CPP";

class Tester {

    public: static char *wrapper(JNIEnv *pEnv, jobject p1, jobject p2) {
        Tester tester;
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
    public: char* execute(JNIEnv *pEnv, jobject p1, jobject p2){
        const jclass cls = pEnv->GetObjectClass(p2);

        readField1(pEnv, cls, p2);
        //readField2(pEnv, cls, p2);
        readField3(pEnv, cls, p2);
        readField4(pEnv, cls, p2);
        readField5(pEnv, cls, p2);
        readField6(pEnv, cls, p2);

        return sResult;
    }

    protected: void readField1(JNIEnv *pEnv, jclass cls, jobject data) {
        const jfieldID field1ID = pEnv->GetFieldID(cls, "field1", "I");
        const jint field1VAL = pEnv->GetIntField(data, field1ID);
        std::cout << "field1VAL: " << field1VAL << std::endl;
    }

    protected: void readField2(JNIEnv *pEnv, jclass cls, jobject data) {
        const jfieldID field2ID = pEnv->GetFieldID(cls, "field2", "L");
        const jlong field2VAL = pEnv->GetLongField(data, field2ID);
        std::cout << field2VAL << std::endl;
    }

    protected: void readField3(JNIEnv *pEnv, jclass cls, jobject data) {
        const jfieldID field3ID = pEnv->GetFieldID(cls, "field3", "F");
        const jfloat field3VAL = pEnv->GetFloatField(data, field3ID);
        std::cout << "field3VAL: " << field3VAL << std::endl;
    }

    protected: void readField4(JNIEnv *pEnv, jclass cls, jobject data) {
        jfieldID field4ID = pEnv->GetFieldID(cls, "field4", "D");
        jdouble field4VAL = pEnv->GetDoubleField(data, field4ID);
        std::cout << "field4VAL: " << field4VAL << std::endl;
    }

    protected: void readField5(JNIEnv *pEnv, jclass cls, jobject data) {
        const jfieldID field5ID = pEnv->GetFieldID(cls, "field5", "Ljava/lang/String;");
        const jstring field5VAL = (jstring)pEnv->GetObjectField(data, field5ID);
        const char *nativeString = pEnv->GetStringUTFChars(field5VAL, 0);
        std::cout << "field5VAL: " << nativeString << std::endl;
    }

    protected: void readField6(JNIEnv *pEnv, jclass cls, jobject data) {
        const jfieldID field6ID = pEnv->GetFieldID(cls, "field6", "[I");
        const jobject field6DATA = pEnv->GetObjectField(data, field6ID);
        const jintArray field6VALS = (jintArray)field6DATA;
        const jsize length = pEnv->GetArrayLength(field6VALS);
        const jint *field6ITEMS = pEnv->GetIntArrayElements(field6VALS, 0);
        for (int i = 0; i < length; i++) {
            const int val = field6ITEMS[i];
            std::cout << val << std::endl;
        }
    }

};

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_core_eng_EngExternal_execute
    (JNIEnv *pEnv, jobject p1, jobject p2){
    return pEnv->NewStringUTF(Tester::wrapper(pEnv, p1, p2));
}

#ifdef __cplusplus
}
#endif
