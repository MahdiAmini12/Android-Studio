package com.mahdi.myapp

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var tvMainText: TextView
    private lateinit var etUserInput: EditText
    private var currentFontSize = 18f
    private var currentTypeface: Typeface = Typeface.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        tvMainText = findViewById(R.id.mainTextView)
        etUserInput = findViewById(R.id.etUserInput)
        val btnOpenMenu = findViewById<Button>(R.id.btnOpenMenu)
        val navView = findViewById<NavigationView>(R.id.navView)

        // دکمه باز کردن منو
        btnOpenMenu.setOnClickListener {
            drawerLayout.openDrawer(navView)
        }

        // وقتی متن داخل EditText تغییر کرد، داخل TextView نمایش داده بشه
        etUserInput.setOnKeyListener { _, _, _ ->
            tvMainText.text = etUserInput.text.toString()
            false
        }

        // دکمه‌های منو برای تغییر متن
        navView.setNavigationItemSelectedListener { menuItem ->
            handleMenuClick(menuItem)
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun handleMenuClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.action_align_left -> tvMainText.gravity = Gravity.RIGHT
            R.id.action_align_center -> tvMainText.gravity = Gravity.CENTER
            R.id.action_align_right -> tvMainText.gravity = Gravity.LEFT
            R.id.action_increase_size -> {
                currentFontSize += 2f
                tvMainText.textSize = currentFontSize
            }
            R.id.action_decrease_size -> {
                if (currentFontSize > 12f) {
                    currentFontSize -= 2f
                    tvMainText.textSize = currentFontSize
                }
            }
            R.id.action_change_font -> changeFont()
        }
    }

    private fun changeFont() {
        val typefaces = listOf(
            Typeface.SANS_SERIF,
            Typeface.SERIF,
            Typeface.MONOSPACE
        )

        currentTypeface = typefaces.random()
        tvMainText.typeface = currentTypeface
    }
}
