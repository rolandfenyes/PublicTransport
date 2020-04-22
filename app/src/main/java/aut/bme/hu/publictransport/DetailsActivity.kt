package aut.bme.hu.publictransport

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_details.*
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val KEY_TRANSPORT_TYPE = "KEY_TRANSPORT_TYPE"
    }

    private val bikeDailyPrice = 700
    private val busDailyPrice = 1000
    private val trainDailyPrice = 1500
    private val boatDailyPrice = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val transportType = intent.getIntExtra(KEY_TRANSPORT_TYPE, -1);
        tvTicketType.text = getTypeString(transportType)

        btnPurchase.setOnClickListener {
            val intent = Intent(this, PassActivity::class.java)
            intent.putExtra(PassActivity.KEY_TYPE_STRING, getTypeString(transportType))
            intent.putExtra(PassActivity.KEY_DATE_STRING, "${getDateString(dpStartDate)} - ${getDateString(dpEndDate)}")
            startActivity(intent)
        }

    }

    private fun getTypeString(transportType: Int): String {
        return when(transportType) {
            ListActivity.TYPE_BUS -> "Bus pass"
            ListActivity.TYPE_BIKE -> "Bike pass"
            ListActivity.TYPE_TRAIN -> "Train pass"
            ListActivity.TYPE_BOAT -> "Boat pass"
            else -> "Unknown pass"
        }
    }

    private fun getDateString(dp: DatePicker): String {
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", dp.year, dp.month+1, dp.dayOfMonth)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculatePrice(startDate: DatePicker, endDate: DatePicker, transportType: Int): Long {
        val date1 = LocalDate.parse(getDateString(startDate), DateTimeFormatter.ISO_DATE)
        val date2 = LocalDate.parse(getDateString(endDate), DateTimeFormatter.ISO_DATE)

        val days = Duration.between(date1, date2).toDays()

        return when(transportType) {
            ListActivity.TYPE_BUS -> days * busDailyPrice
            ListActivity.TYPE_BIKE -> days * bikeDailyPrice
            ListActivity.TYPE_BOAT -> days * boatDailyPrice
            ListActivity.TYPE_TRAIN -> days * trainDailyPrice
            else -> 0
        }
    }

}
