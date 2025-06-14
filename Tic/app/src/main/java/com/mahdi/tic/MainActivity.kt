package com.mahdi.dateconverter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mahdi.tic.R
import java.util.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPickDate = findViewById<Button>(R.id.btnPickDate)
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val tvConvertedDate = findViewById<TextView>(R.id.tvConvertedDate)

        btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val pickedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                tvSelectedDate.text = "تاریخ انتخابی: $pickedDate"

                // تبدیل تاریخ میلادی به قمری
                val islamicCalendar = Calendar.getInstance()
                islamicCalendar.set(selectedYear, selectedMonth, selectedDay)
                val hijriYear = islamicCalendar.get(Calendar.YEAR) - 580
                val hijriMonth = islamicCalendar.get(Calendar.MONTH) + 1
                val hijriDay = islamicCalendar.get(Calendar.DAY_OF_MONTH)
                val hijriDate = "$hijriYear-$hijriMonth-$hijriDay"

                tvConvertedDate.text = "تاریخ قمری: $hijriDate"

            }, year, month, day)

            datePicker.show()
        }
    }
}
