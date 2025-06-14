package ir.taptag.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var n1:Int=0
        var n2:Int=0

        var temp:Long=0


        btn_4.setOnClickListener{
            temp=temp*10
            temp=temp+4
            restext.text=temp.toString()
        }



    }
}
