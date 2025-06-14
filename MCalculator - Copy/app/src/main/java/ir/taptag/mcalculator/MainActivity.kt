package ir.taptag.mcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ir.taptag.mcalculator.R


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var n1:Int=0
        var n2:Int=0

        var temp:Long=0
        var op=""

        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        val btnC = findViewById<Button>(R.id.btnC)
        val btnPlus = findViewById<Button>(R.id.btnplus)
        val btnminus = findViewById<Button>(R.id.btnminus)
        val btntimes = findViewById<Button>(R.id.btntimes)
        val btndiv = findViewById<Button>(R.id.btndiv)
        val btnequal = findViewById<Button>(R.id.btnequal)

        val resText = findViewById<TextView>(R.id.restext)


        btn0.setOnClickListener {
            temp=temp*10
            temp=temp+0
            resText.text=temp.toString()
        }
        btn1.setOnClickListener {
            temp=temp*10
            temp=temp+1
            resText.text=temp.toString()
        }
        btn2.setOnClickListener {
            temp=temp*10
            temp=temp+2
            resText.text=temp.toString()
        }
        btn3.setOnClickListener {
            temp=temp*10
            temp=temp+3
            resText.text=temp.toString()
        }
        btn4.setOnClickListener {
            temp=temp*10
            temp=temp+4
            resText.text=temp.toString()
        }
        btn5.setOnClickListener {
            temp=temp*10
            temp=temp+5
            resText.text=temp.toString()
        }
        btn6.setOnClickListener {
            temp=temp*10
            temp=temp+6
            resText.text=temp.toString()
        }
        btn7.setOnClickListener {
            temp=temp*10
            temp=temp+7
            resText.text=temp.toString()
        }
        btn8.setOnClickListener {
            temp=temp*10
            temp=temp+8
            resText.text=temp.toString()
        }
        btn9.setOnClickListener {
            temp=temp*10
            temp=temp+9
            resText.text=temp.toString()
        }

        btnC.setOnClickListener {
            temp=0
            resText.text=""
        }
        btnPlus.setOnClickListener {
            n1=resText.text.toString().toInt()
            op = "+"
            resText.text=""
            temp=0
        }
        btnminus.setOnClickListener {
            n1 = resText.text.toString().toInt()
            op = "-"
            resText.text = ""
            temp = 0
        }
        btntimes.setOnClickListener {
            n1 = resText.text.toString().toInt()
            op = "*"
            resText.text = ""
            temp = 0
        }
        btndiv.setOnClickListener {
            n1 = resText.text.toString().toInt()
            op = "/"
            resText.text = ""
            temp = 0
        }

        btnequal.setOnClickListener {
            n2 = resText.text.toString().toInt()
            if (op == "+") {
                temp = (n1 + n2).toLong()
                resText.text = temp.toString()
            }
            else if (op == "-") {
                temp = (n1 - n2).toLong()
                resText.text = temp.toString()
            }
            else if (op == "*") {
                temp = (n1 * n2).toLong()
                resText.text = temp.toString()
            }
            else if (op == "/") {
                temp = (n1 / n2).toLong()
                resText.text = temp.toString()
            }


        }

    }
}