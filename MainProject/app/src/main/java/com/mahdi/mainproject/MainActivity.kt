package com.example.dateconverter

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mahdi.mainproject.R
import java.util.*

class MainActivity : AppCompatActivity() {

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

                // تبدیل میلادی به شمسی
                val jalaliDate = gregorianToJalali(selectedYear, selectedMonth + 1, selectedDay)

                // تبدیل میلادی به قمری
                val hijriDate = gregorianToHijri(selectedYear, selectedMonth + 1, selectedDay)

                tvConvertedDate.text = "شمسی: $jalaliDate\nقمری: $hijriDate"

            }, year, month, day)

            datePicker.show()
        }
    }

    // ✅ تابع تبدیل میلادی به شمسی
    private fun gregorianToJalali(gy: Int, gm: Int, gd: Int): String {
        val gDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        val jDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)

        var gy2 = if (gm > 2) gy + 1 else gy
        var days = 355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100) + ((gy2 + 399) / 400)

        for (i in 0 until gm - 1) days += gDaysInMonth[i]
        days += gd

        var jy = -1595 + (33 * (days / 12053))
        days %= 12053
        jy += 4 * (days / 1461)
        days %= 1461
        if (days > 365) {
            jy += (days - 1) / 365
            days = (days - 1) % 365
        }

        var jm = 0
        for (i in 0..11) {
            if (days < jDaysInMonth[i]) {
                jm = i + 1
                break
            }
            days -= jDaysInMonth[i]
        }

        return "$jy-$jm-${days + 1}"
    }

    // ✅ تابع تبدیل میلادی به قمری
    private fun gregorianToHijri(gy: Int, gm: Int, gd: Int): String {
        val jd = (1461 * (gy + 4800 + (gm - 14) / 12)) / 4 +
                (367 * (gm - 2 - 12 * ((gm - 14) / 12))) / 12 -
                (3 * ((gy + 4900 + (gm - 14) / 12) / 100)) / 4 +
                gd - 32075

        val l = jd - 1948440 + 10632
        val n = ((l - 1) / 10631).toInt()
        val l2 = l - (10631 * n + 354)
        var j = ((10985 - l2) / 5316).toInt() * ((50 * l2) / 17719).toInt() +
                ((l2 / 5670).toInt() * ((43 * l2) / 15238).toInt())

        j = j + 29
        val jm = ((24 * j) / 709).toInt()
        val jd2 = j - ((709 * jm) / 24).toInt()
        val jy = 30 * n + j / 30

        return "$jy-$jm-$jd2"
    }
}
