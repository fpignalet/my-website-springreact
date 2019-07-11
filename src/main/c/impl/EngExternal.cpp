#include "../headers/com_core_eng_EngExternal.h"
#include "../headers/EngTester.h"

using namespace website;

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_core_eng_EngExternal_execute
    (JNIEnv *pEnv, jobject p1, jobject p2){
    return pEnv->NewStringUTF(EngTester::wrapper_in(pEnv, p1, p2));
}

JNIEXPORT jobjectArray JNICALL Java_com_core_eng_EngExternal_getData
    (JNIEnv *pEnv, jobject p1){
    return EngTester::wrapper_out(pEnv, p1);
}

#ifdef __cplusplus
}
#endif
