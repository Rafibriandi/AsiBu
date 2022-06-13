package com.tugas.asibu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.tugas.asibu.ui.home.HomeActivity

@Suppress("DEPRECATION")
class RandomQuotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_quotes)

        //remove header title
        supportActionBar!!.hide()

     //take quotes from string array and display one  randomly
     val quotes  = resources.getStringArray(R.array.quotes)
     val quote : TextView = findViewById(R.id.quote)
     quote.text = quotes.random().toString()

       //animation transition to next layout
        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            finish()
        }, 3000)

    }
}