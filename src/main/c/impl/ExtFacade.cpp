#include "../headers/com_core_ext_ExtFacade.h"
#include "../headers/ExtTester.h"

using namespace website;

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_core_ext_ExtFacade_execute
    (JNIEnv *pEnv, jobject p1, jobject p2){
    return pEnv->NewStringUTF(ExtTester::wrapper_DataIn(pEnv, p1, p2));
}

JNIEXPORT jobjectArray JNICALL Java_com_core_ext_ExtFacade_getData
    (JNIEnv *pEnv, jobject p1){
    return ExtTester::wrapper_ArrayStringOut(pEnv, p1);
}

#ifdef __cplusplus
}
#endif
