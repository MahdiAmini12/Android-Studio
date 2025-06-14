package com.slowtech.taptag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.slowtech.taptag.DatabaseHelper


class SetupDatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)

        // اضافه کردن دانشجوها
        dbHelper.insertStudent("محمد مهدی رسول امینی", "1363426370", "4001830235", "مهندسی کامپیوتر")
        dbHelper.insertStudent("سینا مختاروند", "1376377276", "4001830281", "مهندسی کامپیوتر")
        dbHelper.insertStudent("علی زمانی", "1398765432", "4001830456", "مهندسی برق")
        dbHelper.insertStudent("زهرا احمدی", "1389756310", "4001830598", "مهندسی شیمی")
        dbHelper.insertStudent("سارا شریفی", "1378654987", "4001830789", "ریاضیات")
        dbHelper.insertStudent("مجید اکبری", "1368745621", "4001830965", "مهندسی مکانیک")

        // اضافه کردن اساتید
        dbHelper.insertProfessor("دکتر رضا حسینی", "1234567890", "EMP1001", "مهندسی کامپیوتر")
        dbHelper.insertProfessor("دکتر نسرین کریمی", "0987654321", "EMP1002", "ریاضیات")

        // اضافه کردن کارمند
        dbHelper.insertStaff("حسن جعفری", "1472583690", "STF001")
    }
}
