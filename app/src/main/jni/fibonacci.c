#include "com_fibonaccindk_FibonacciNDK.h"
        jint fibonacciNativoR(jint n) {
    if(n<=0) return 0;
    if(n==1) return 1;
    return fibonacciNativoR(n-1) + fibonacciNativoR(n-2);
}
jint fibonacciNativoI(jint n) {
    jint previous = -1;
    jint result = 1;
    jint i=0;
    jint sum=0;
    for (i = 0; i <= n; i++) {
        sum = result + previous;
        previous = result;
        result = sum;
    }
    return result;
}

JNIEXPORT jlong JNICALL Java_com_fibonaccindk_FibonacciNDK_fibonacciNativoR
        (JNIEnv *env, jclass obj, jint n) {
    return fibonacciNativoR(n);
}
JNIEXPORT jlong JNICALL Java_com_fibonaccindk_FibonacciNDK_fibonacciNativoI
        (JNIEnv *env, jclass obj, jint n) {
    return fibonacciNativoI(n);
}