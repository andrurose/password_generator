package com.example.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val symbolSet : List<Char> = ('!'..'/') + (':'..'@') + ('['..'`') + ('{'..'~')
    private val charSet : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val allChars = charSet + symbolSet

    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?

        generate.setOnClickListener {
            shuffleLetters()
        }
        password.setOnClickListener {
            copyText()
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
                    randCharSet += allChars.random().toString()
                }
            }
            passwordLength.text.clear()
        } else {
            val toast = Toast.makeText(applicationContext, "Please enter a number", Toast.LENGTH_SHORT)
            toast.show()
        }
        password.text = randCharSet
    }

    private fun copyText() {
        myClip = ClipData.newPlainText("text", password.text)
        myClipboard?.primaryClip = myClip

        Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }

    override fun onResume() {
        super.onResume()
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }
}
