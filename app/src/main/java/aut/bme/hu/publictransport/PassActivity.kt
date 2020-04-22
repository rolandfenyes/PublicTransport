package aut.bme.hu.publictransport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pass.*

class PassActivity : AppCompatActivity() {
    companion object {
        const val KEY_TYPE_STRING = "KEY_TYPE_STRING"
        const val KEY_DATE_STRING = "KEY_DATE_STRING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)

        tvTicketType.text = intent.getStringExtra(KEY_TYPE_STRING)
        tvDate.text = intent.getStringExtra(KEY_DATE_STRING)
    }
}
