package com.rootstrap.donations.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity :: class.java))
            finish()
        }, 2000)
    }
}