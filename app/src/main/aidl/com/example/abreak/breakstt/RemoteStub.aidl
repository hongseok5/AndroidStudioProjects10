// RemoteStub.aidl
package com.example.abreak.breakstt;

// Declare any non-default types here with import statements

interface RemoteStub {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String getServerPackageName();
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


}
