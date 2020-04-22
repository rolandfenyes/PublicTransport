package aut.bme.hu.publictransport

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_details.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val KEY_TRANSPORT_TYPE = "KEY_TRANSPORT_TYPE"
    }

    private val bikeDailyPrice = 700
    private val busDailyPrice = 1000
    private val trainDailyPrice = 1500
    private val boatDailyPrice = 2500

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val transportType = intent.getIntExtra(KEY_TRANSPORT_TYPE, -1);
        tvTicketType.text = getTypeString(transportType)
        //tvPrice.text = calculatePrice(transportType).toString()

        btnPurchase.setOnClickListener {
            val intent = Intent(this, PassActivity::class.java)
            intent.putExtra(PassActivity.KEY_TYPE_STRING, getTypeString(transportType))
            intent.putExtra(PassActivity.KEY_DATE_STRING, "${getDateString(dpStartDate)} - ${getDateString(dpEndDate)}")
            startActivity(intent)
        }

        btnCalculate.setOnClickListener {
            tvPrice.text = calculatePrice(transportType).toString()
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
        return String.format(Locale.getDefault(), "%04d.%02d.%02d", dp.year, dp.month+1, dp.dayOfMonth)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculatePrice(transportType: Int): Long {
        val discountType = rgPriceCategory.checkedRadioButtonId
        val radioButton: RadioButton
        radioButton = findViewById(discountType)

        val discount = getDiscount(radioButton)


        val start = LocalDate.of(dpStartDate.year, dpStartDate.month, dpStartDate.dayOfMonth)
        val end = LocalDate.of(dpEndDate.year, dpEndDate.month, dpEndDate.dayOfMonth)

        var days = ChronoUnit.DAYS.between(start, end)
        days += 1

        return when(transportType) {
            ListActivity.TYPE_BUS -> days * busDailyPrice * discount / 100
            ListActivity.TYPE_BIKE -> days * bikeDailyPrice * discount / 100
            ListActivity.TYPE_BOAT -> days * boatDailyPrice * discount / 100
            ListActivity.TYPE_TRAIN -> days * trainDailyPrice * discount / 100
            else -> 0
        }
    }

    private fun getDiscount(radioButton: RadioButton): Long {
        return when(radioButton.text) {
            "Full price" ->  100
            "Senior" -> 10
            "Student" -> 50
            "Public servant" -> 50
            else -> 0
        }
    }

}
