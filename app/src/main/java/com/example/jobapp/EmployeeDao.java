package com.example.jobapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EmployeeDao {

    @Insert
    void registerEmployee(EmployeeEntity employeeEntity);

    @Update
    void updateEmployee(EmployeeEntity employeeEntity);

    @Delete
    void deleteEmployee(EmployeeEntity employeeEntity);

    @Query("SELECT * FROM Employee WHERE employeeId = :employeeId AND password = :password")
    EmployeeEntity getEmployee(String employeeId, String password);

}

