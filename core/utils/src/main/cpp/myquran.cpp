#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("myquran");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("myquran")
//      }
//    }
//extern "C"
//JNIEXPORT jstring JNICALL
//Java_com_oratakashi_myquran_utility_Credentials_getBaseUrl(JNIEnv *env, jobject thiz) {
//    return env->NewStringUTF("https://api.oratakashi.com/myquran/");
//}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_oratakashi_myquran_utils_Credentials_getBaseUrl(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("https://api.oratakashi.com/myquran/");
}