package com.mahdi.shamsi

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mahdi.shamsi.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPickDate = findViewById<Button>(R.id.btnPickDate)
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val tvJalaliDate = findViewById<TextView>(R.id.tvJalaliDate)
        val tvHijriDate = findViewById<TextView>(R.id.tvHijriDate)

        btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val pickedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                tvSelectedDate.text = "📅 تاریخ انتخابی: $pickedDate"

                // تبدیل تاریخ‌ها
                val jalaliDate = gregorianToJalali(selectedYear, selectedMonth + 1, selectedDay)
                val hijriDate = gregorianToHijri(selectedYear, selectedMonth + 1, selectedDay)

                tvJalaliDate.text = "📆 شمسی: $jalaliDate"
                tvHijriDate.text = "🌙 قمری: $hijriDate"

            }, year, month, day)

            datePicker.show()
        }
    }

    // تابع تبدیل میلادی به شمسی
    private fun gregorianToJalali(gy: Int, gm: Int, gd: Int): String {
        val gDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        val jDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)

        var gy2 = if (gm > 2) gy + 1 else gy
        var days =
            355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100) + ((gy2 + 399) / 400)

        for (i in 0 until (gm - 1)) days += gDaysInMonth[i]
        days += gd

        var jy = -1595 + (33 * (days / 12053))
        var daysRemaining = days % 12053
        jy += 4 * (daysRemaining / 1461)
        daysRemaining %= 1461

        if (daysRemaining > 365) {
            jy += (daysRemaining - 1) / 365
            daysRemaining = (daysRemaining - 1) % 365
        }

        var jm = 0
        while (jm < 11 && daysRemaining >= jDaysInMonth[jm]) {
            daysRemaining -= jDaysInMonth[jm]
            jm++
        }

        return "$jy/${jm + 1}/${daysRemaining + 1}"
    }

    // تابع تبدیل میلادی به قمری
    private fun gregorianToHijri(year: Int, month: Int, day: Int): String {
        val jd = (1461 * (year + 4800 + (month - 14) / 12)) / 4 +
                (367 * (month - 2 - 12 * ((month - 14) / 12))) / 12 -
                (3 * ((year + 4900 + (month - 14) / 12) / 100)) / 4 +
                day - 32075

        val l = jd - 1948440 + 10632
        val n = (l - 1) / 10631
        var i = l - 10631 * n + 354
        var j = ((10985 - i) / 5316) * ((50 * i) / 17719) + (i / 5670) * ((43 * i) / 15238)
        i -= ((30 - j) / 15) * ((17719 * j) / 50) - ((j / 16) * ((15238 * j) / 43))
        val m = ((i - 1) / 29) + 1
        val d = (i - 29 * (m - 1))

        return "${n * 30 + j + 1}/$m/$d"
    }
}
