package com.example.metro.Avtivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.metro.R

class SplashActivity : AppCompatActivity() {
    private lateinit var imageS: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        imageS = findViewById(R.id.imageS)
        imageS.alpha = 0f
        imageS.animate().setDuration(2000).alpha(1f).withEndAction {
            Intent(this, MainActivity::class.java).also {
                startActivity(it) }
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}