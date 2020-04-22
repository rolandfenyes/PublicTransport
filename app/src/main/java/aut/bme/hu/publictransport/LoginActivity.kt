package aut.bme.hu.publictransport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        setTheme(R.style.AppTheme)
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
