package aut.bme.hu.publictransport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListActivity : AppCompatActivity() {
    companion object {
        const val TYPE_BUS = 1
        const val TYPE_TRAIN = 2
        const val TYPE_BIKE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}
