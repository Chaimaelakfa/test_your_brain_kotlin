package com.example.test_your_brain_personnel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        val splashImageView = findViewById<ImageView>(R.id.splash)
        val splashAnimation = AnimationUtils.loadAnimation(this, R.anim.rotation)
        splashImageView.startAnimation(splashAnimation)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this,WelcomeScreen::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}