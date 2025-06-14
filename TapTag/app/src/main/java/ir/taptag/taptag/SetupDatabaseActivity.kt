package ir.taptag.taptag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SetupDatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = DatabaseHelper(this)

        dbHelper.insertStudent("محمد مهدی رسول امینی", "1363426370", "4001830235", "مهندسی کامپیوتر")
        dbHelper.insertStudent("سینا مختاروند", "1376377276", "4001830281", "مهندسی کامپیوتر")
        dbHelper.insertStudent("سینا مختاروند", "1376377276", "4001830281", "مهندسی کامپیوتر")

        // می‌توانید دانشجوهای بیشتری اضافه کنید...
    }
}
