package com.rootstrap.donations.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rootstrap.donations.utils.PREF_USER_IS_LOGGED
import com.rootstrap.donations.utils.TinyDB

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(this,
                if (!TinyDB(this).getBoolean(PREF_USER_IS_LOGGED, false))
                WelcomeActivity :: class.java else MainActivity :: class.java))
            finish()
        }, 2000)
    }
}