package com.example.sendemail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sendemail.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var bin: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)

        myFun()
    }

    private fun myFun() {
        bin.btnSend.setOnClickListener {
            val email = bin.etEmail.text.toString().trim()
            val title = bin.etTitle.text.toString().trim()
            val message = bin.etDescription.text.toString().trim()
            when {
                email.isEmpty() -> {
                    bin.etEmail.error = getString(R.string.en_email)
                }
                title.isEmpty() -> {
                    bin.etTitle.error = getString(R.string.en_title)
                }
                message.isEmpty() -> {
                    bin.etDescription.error = getString(R.string.en_des)
                }
                else -> {
                    sendEmail(email,title,message)
                }
            }
        }
    }

    private fun sendEmail(email: String, title: String, message: String) {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("mailto:")
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, email)
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Email Client"))
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}