//
// Created by fpi on 11.07.19.
//

#ifndef C_ENGTESTER_H
#define C_ENGTESTER_H

#include <string>
#include <iostream>
#include <vector>

#include "../headers/com_core_eng_EngExternal.h"

using namespace std;

namespace website {

    class EngJNIEntry {
    protected:
        jint readInt(JNIEnv *pEnv, jclass cls, string name, jobject data);
        jlong readLong(JNIEnv *pEnv, jclass cls, string name, jobject data);
        jfloat readFloat(JNIEnv *pEnv, jclass cls, string name, jobject data);
        jdouble readDouble(JNIEnv *pEnv, jclass cls, string name, jobject data);
        const char* readString(JNIEnv *pEnv, jclass cls, string name, jobject data);
        void parseIntArray(JNIEnv *pEnv, jclass cls, string name, jobject data);
        const jobjectArray fillStringArray(JNIEnv *pEnv, const vector<string>& v);

    };

    class EngTester: protected EngJNIEntry {
    public:
        static const char* wrapper_in(JNIEnv *pEnv, jobject p1, jobject p2);
        static const jobjectArray wrapper_out(JNIEnv *pEnv, jobject p1);

    protected:
        virtual const char* execute_in(JNIEnv *pEnv, jobject p1, jobject p2);
        virtual const jobjectArray execute_out(JNIEnv *env, jobject p1);

        /*  Java class is:
            static class Data {
                public int field1 = 0;
                public long field2 = 1;
                public float field3 = (float) 2.0;
                public double field4 = 2.0;
                public String field5 = "TEST";
                public int[] field6 = { 0, 1, 2 };
            };
        */
        virtual void read_field1(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void read_field2(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void read_field3(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void read_field4(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void read_field5(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void read_field6(JNIEnv *pEnv, jclass cls, jobject data);

    };

}

#endif //C_ENGTESTER_H
