package com.tugas.asibu

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView

@Suppress("DEPRECATION")
class RandomQuotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_quotes)

        supportActionBar!!.hide()
     val quotes  = resources.getStringArray(R.array.quotes)
     val quote : TextView = findViewById(R.id.quote)
     quote.text = quotes.random().toString()


        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            finish()
        }, 3000)

    }
}