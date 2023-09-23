package com.example.jobapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private var employeeDao: EmployeeDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        employeeDao = EmployeeDatabase.getInstance(this).employeeDao()

        val registerButton = findViewById<Button>(R.id.btn_register)
        registerButton.setOnClickListener {
            val nameEditText = findViewById<EditText>(R.id.name_register)
            val employeeIdEditText = findViewById<EditText>(R.id.employeeId_register)
            val passwordEditText = findViewById<EditText>(R.id.register_password)

            val name = nameEditText.text.toString()
            val employeeId = employeeIdEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check if employeeDao is not null before using it
            employeeDao?.let { dao ->
                Thread {
                    val existingEmployee = dao.getEmployee(employeeId, password)

                    runOnUiThread {
                        if (existingEmployee != null) {
                            // An employee with the same ID and password already exists.
                            Toast.makeText(
                                this@RegisterActivity,
                                "Employee with the same ID and password already exists.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val newEmployee = EmployeeEntity()
                            newEmployee.empId = employeeId
                            newEmployee.password = password
                            newEmployee.empName = name

// Register the employee
                            dao.registerEmployee(newEmployee)

                            // Registration successful, you can show a success message or navigate to login
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registration successful.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }.start()
            } ?: run {
                // Handle the case when employeeDao is null (e.g., show an error message)
                Toast.makeText(
                    this@RegisterActivity,
                    "Database not initialized. Please try again later.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
