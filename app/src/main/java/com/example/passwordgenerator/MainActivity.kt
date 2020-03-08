package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val allChars = ('!'..'~')
    private val charSet : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generate.setOnClickListener {
            shuffleLetters()
        }

    }

    private fun shuffleLetters() {
        if (passwordLength.text.toString() != "") {
            val limiter = passwordLength.text.toString().toInt()

            val passText = if(!punctuation.isChecked)
                charSet.joinToString(separator = "")
            else
                allChars.joinToString(separator = "")

            password.text = passText

        } else {
            val toast = Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_SHORT)
            toast.show()
        }



    }
}
