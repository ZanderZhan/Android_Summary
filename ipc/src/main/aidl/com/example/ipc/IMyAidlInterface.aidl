// IMyAidlInterface.aidl
package com.example.ipc;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

    void say(String word);

    int tell(String word, int age);

}