package com.example.jobapp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Employee")
public class EmployeeEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name="employeeId")
    String empId;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "employeeName")
    String empName;


}