//
// Created by fpi on 11.07.19.
//

#ifndef C_ENGTESTER_H
#define C_ENGTESTER_H

#include <string>
#include <iostream>

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

    };

    class EngTester: protected EngJNIEntry {
    public:
        static const char* wrapper(JNIEnv *pEnv, jobject p1, jobject p2);

    protected:
        virtual const char* execute(JNIEnv *pEnv, jobject p1, jobject p2);

        virtual void readField1(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void readField2(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void readField3(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void readField4(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void readField5(JNIEnv *pEnv, jclass cls, jobject data);
        virtual void readField6(JNIEnv *pEnv, jclass cls, jobject data);

    };

}

#endif //C_ENGTESTER_H
