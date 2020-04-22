package aut.bme.hu.publictransport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

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

        btnLogin.setOnClickListener() {
            if (etEmail.text.isEmpty()) {
                etEmail.requestFocus()
                etEmail.error = "Email cannot be empty!"
            }
            else if (etPassword.text.isEmpty()) {
                etPassword.requestFocus()
                etPassword.error = "Password cannot be empty!"
            }
            else {
                startActivity(Intent(this, ListActivity::class.java))
            }
        }
    }
}
