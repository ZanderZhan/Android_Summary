package com.example.ipc;
import com.example.ipc.Student;

interface IStudentService {
    void getStudentInfo(int age, in Student student);
}