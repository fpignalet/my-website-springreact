#include "../headers/com_core_eng_EngExternal.h"
#include "../headers/EngTester.h"

using namespace website;

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_core_eng_EngExternal_execute
    (JNIEnv *pEnv, jobject p1, jobject p2){
    return pEnv->NewStringUTF(EngTester::wrapper(pEnv, p1, p2));
}

#ifdef __cplusplus
}
#endif
