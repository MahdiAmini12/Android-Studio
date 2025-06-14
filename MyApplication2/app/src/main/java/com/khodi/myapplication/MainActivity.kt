package com.khodi.myapplication
import java.util.Scanner



fun main() {
    val scanner = Scanner(System.`in`)

    println("Enter first number:")
    val num1 = scanner.nextDouble()

    println("Enter second number:")
    val num2 = scanner.nextDouble()

    println("Enter an operator (+, -, *, /):")
    val operator = scanner.next().single()

    val result = when (operator) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> {
            if (num2 == 0.0) {
                println("Cannot divide by zero.")
                return
            } else {
                num1 / num2
            }
        }
        else -> {
            println("Invalid operator.")
            return
        }
    }

    println("$num1 $operator $num2 = $result")
}

