package com.example.jobapp


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private var employeeDao: EmployeeDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        employeeDao = EmployeeDatabase.getInstance(this).employeeDao()

        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            val employeeIdEditText = findViewById<EditText>(R.id.employeeId_login)
            val passwordEditText = findViewById<EditText>(R.id.login_password)
            val employeeId = employeeIdEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check if employeeDao is not null before using it
            employeeDao?.let { dao ->
                Thread {
                    val employee = dao.getEmployee(employeeId, password)
                    runOnUiThread {
                        if (employee != null) {
                            // Login successful, navigate to another activity or perform actions
                        } else {
                            // Login failed, show an error message
                            Toast.makeText(
                                this@LoginActivity,
                                "Login failed. Check your credentials.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }.start()
            } ?: run {
                // Handle the case when employeeDao is null (e.g., show an error message)
                Toast.makeText(
                    this@LoginActivity,
                    "Database not initialized. Please try again later.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


