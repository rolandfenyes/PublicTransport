package aut.bme.hu.publictransport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val KEY_TRANSPORT_TYPE = "KEY_TRANSPORT_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val transportType = intent.getIntExtra(KEY_TRANSPORT_TYPE, -1);
        tvTicketType.text = getTypeString(transportType)

    }

    private fun getTypeString(transportType: Int): String {
        return when(transportType) {
            ListActivity.TYPE_BUS -> "Bus pass"
            ListActivity.TYPE_BIKE -> "Bike pass"
            ListActivity.TYPE_TRAIN -> "Train pass"
            else -> "Unknown pass"
        }
    }
}
