package com.tugas.asibu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //remove app windows
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //animation transition to next layout
        Handler().postDelayed({
            val intent = Intent(this, RandomQuotes::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            finish()
        }, 3000)

    }
}