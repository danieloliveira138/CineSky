package com.danieloliveira138.cinesky.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.danieloliveira138.cinesky.ui.main_screen.MainActivity
import com.danieloliveira138.cinesky.utils.isNetworkAvailable
import com.danieloliveira138.cinesky.R
import com.danieloliveira138.cinesky.ui.dialogs.DialogAlert

class SplashActivity: AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val splashDelay: Long = 500L

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing){
            if (isNetworkAvailable(this)){
                setToMainActivity()
            } else {
                noConnectionAlert()
            }
        }
    }

    private fun setToMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave)
        setContentView(R.layout.splash_activity)

        mDelayHandler = Handler()
        mDelayHandler?.postDelayed(mRunnable, splashDelay)

    }

    override fun onDestroy() {

        if (mDelayHandler != null){
            mDelayHandler?.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

    private fun noConnectionAlert(){

        val dialogAlert = DialogAlert(
                                {setToMainActivity()},
                                "Ops!!!",
                                "Seu celular esta sem sinal, verifique sua conexão."
                            )

        dialogAlert.show(supportFragmentManager, "dialog_no_connection")
    }

}