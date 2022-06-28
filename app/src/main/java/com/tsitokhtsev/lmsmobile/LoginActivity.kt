package com.tsitokhtsev.lmsmobile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.tsitokhtsev.lmsmobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val username = binding.loginUsernameField.editText?.text?.trim().toString()
            val password = binding.loginPasswordField.editText?.text?.trim().toString()

            if (username.isBlank()) {
                binding.loginUsernameField.error = "ველი ცარიელია"
            }

            if (password.isBlank()) {
                binding.loginPasswordField.error = "ველი ცარიელია"
            }

            if (username.isBlank() || password.isBlank()) {
                return@setOnClickListener
            }

            val studentData = getStudentData(username, password)

            if (studentData.length == 5667) {
                val text = "არასწორი მონაცემები..."
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("studentData", studentData)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getStudentData(username: String, password: String): String {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        val python = Python.getInstance()
        val loginFile = python.getModule("fetch_data")

        return loginFile.callAttr("login", username, password).toString()
    }
}