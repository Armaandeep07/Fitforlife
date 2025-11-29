package com.example.fitforlife

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private val splashTime: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val auth = FirebaseAuth.getInstance()
            if (auth.currentUser != null) {
                // User already logged in → go to SessionsListActivity
                startActivity(Intent(this, SessionsListActivity::class.java))
            } else {
                // Not logged in → go to LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, splashTime)
    }
}
