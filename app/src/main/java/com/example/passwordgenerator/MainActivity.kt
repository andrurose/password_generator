package com.example.passwordgenerator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Node
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val symbolSet : List<Char> = ('!'..'/') + (':'..'@') + ('['..'`') + ('{'..'~')
    private val charSet : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generate.setOnClickListener {
            shuffleLetters()
        }
    }

    private fun shuffleLetters() {
        var randCharSet = ""
        if (passwordLength.text.toString() != "") {
            val limiter = passwordLength.text.toString().toInt()

            if(!punctuation.isChecked) {
                for (i in 1..limiter) {
                    randCharSet += charSet.random().toString()
                }
            } else {
                for (i in 1..limiter) {
                    randCharSet += charSet.random().toString() + symbolSet.random().toString()
                }
            }

            passwordLength.text.clear()
        } else {
            val toast = Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_SHORT)
            toast.show()
        }
        password.text = randCharSet
    }
}
