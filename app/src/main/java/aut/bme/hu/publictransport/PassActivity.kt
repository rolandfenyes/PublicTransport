package aut.bme.hu.publictransport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PassActivity : AppCompatActivity() {
    companion object {
        const val KEY_TYPE_STRING = "KEY_TYPE_STRING"
        const val KEY_DATE_STRING = "KEY_DATE_STRING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)
    }
}
