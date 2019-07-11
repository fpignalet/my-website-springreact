#include <iostream>
#include <string.h>

#include "../headers/com_core_eng_EngExternal.h"

using namespace std;

static char *sResult = (char*)"RESULT FROM CPP";

class Tester {

    public: char* execute() {
        return sResult;
    }

};

char *execute_wrapper() {
    Tester tester;
    return tester.execute();
}

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_core_eng_EngExternal_execute
    (JNIEnv *env, jobject p1, jobject p2){
    /*
        public int field1 = 0;
        public long field2 = 1;
        public float field3 = (float) 2.0;
        public double field4 = 2.0;
        public String field5 = "TEST";
        public int[] field6 = { 0, 1, 2 };
    */
    jclass cls = env->GetObjectClass(p2);

    jfieldID field1ID = env->GetFieldID(cls, "field1", "I");
    jint field1VAL = env->GetIntField(p2, field1ID);
    std::cout << "field1VAL: " << field1VAL << std::endl;

/*
    jfieldID field2ID = env->GetFieldID(cls, "field2", "L");
    jlong field2VAL = env->GetLongField(p2, field2ID);
    std::cout << field2VAL << std::endl;
*/

    jfieldID field3ID = env->GetFieldID(cls, "field3", "F");
    jfloat field3VAL = env->GetFloatField(p2, field3ID);
    std::cout << "field3VAL: " << field3VAL << std::endl;

    jfieldID field4ID = env->GetFieldID(cls, "field4", "D");
    jdouble field4VAL = env->GetDoubleField(p2, field4ID);
    std::cout << "field4VAL: " << field4VAL << std::endl;

    jfieldID field5ID = env->GetFieldID(cls, "field5", "Ljava/lang/String;");
    jstring field5VAL = (jstring)env->GetObjectField(p2, field5ID);
    const char *nativeString = env->GetStringUTFChars(field5VAL, 0);
    std::cout << "field5VAL: " << nativeString << std::endl;

    jfieldID field6ID = env->GetFieldID(cls, "field6", "[I");
    jobject field6DATA = env->GetObjectField(p2, field6ID);
    jintArray field6VALS = (jintArray)field6DATA;
    jsize length = env->GetArrayLength(field6VALS);
    jint *field6ITEMS = env->GetIntArrayElements(field6VALS, 0);
    for (int i = 0; i < length; i++) {
        int val = field6ITEMS[i];
        std::cout << val << std::endl;
    }

    return env->NewStringUTF(execute_wrapper());
}

#ifdef __cplusplus
}
#endif
