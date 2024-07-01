@file:Suppress("UNUSED_PARAMETER")

package com.cusufcan.basiccalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cusufcan.basiccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var numberButtons = ArrayList<Button>()

    private var input = ""
    private var output = ""

    private var number1: Double? = 0.0
    private var number2: Double? = 0.0

    private var operation = Operations.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNumberButtons()
    }

    private fun initNumberButtons() {
        numberButtons.add(binding.button0)
        numberButtons.add(binding.button1)
        numberButtons.add(binding.button2)
        numberButtons.add(binding.button3)
        numberButtons.add(binding.button4)
        numberButtons.add(binding.button5)
        numberButtons.add(binding.button6)
        numberButtons.add(binding.button7)
        numberButtons.add(binding.button8)
        numberButtons.add(binding.button9)

        for (button in numberButtons) {
            button.setOnClickListener {
                input += button.text
                output += button.text
                binding.inputTextView.text = output
            }
        }
    }

    fun plus(view: View) {
        number1 = input.toDoubleOrNull()
        input = ""
        operation = Operations.PLUS

        output += " + "
        binding.inputTextView.text = output
        changeOperationsEnabled(false)
    }

    fun minus(view: View) {
        number1 = input.toDoubleOrNull()
        input = ""
        operation = Operations.MINUS

        output += " - "
        binding.inputTextView.text = output
        changeOperationsEnabled(false)
    }

    fun multiply(view: View) {
        number1 = input.toDoubleOrNull()
        input = ""
        operation = Operations.MULTIPLY

        output += " * "
        binding.inputTextView.text = output
        changeOperationsEnabled(false)
    }

    fun divorce(view: View) {
        number1 = input.toDoubleOrNull()
        input = ""
        operation = Operations.DIVORCE

        output += " / "
        binding.inputTextView.text = output
        changeOperationsEnabled(false)
    }

    fun clear(view: View) {
        input = ""
        output = ""

        number1 = 0.0
        number2 = 0.0

        binding.inputTextView.text = input

        changeOperationsEnabled(true)

        operation = Operations.NONE
    }

    fun complete(view: View) {
        if (operation == Operations.NONE) return
        number2 = input.toDoubleOrNull()
        if (number2 == null) return

        input = ""
        output = when (operation) {
            Operations.PLUS -> (number1!! + number2!!).toString()
            Operations.MINUS -> (number1!! - number2!!).toString()
            Operations.DIVORCE -> (number1!! / number2!!).toString()
            Operations.MULTIPLY -> (number1!! * number2!!).toString()
            Operations.NONE -> return
        }

        binding.outputTextView.text = output
        clear(view)
    }

    fun dot(view: View) {
        input += binding.buttonDot.text
        output += binding.buttonDot.text
        binding.inputTextView.text = output
    }

    private fun changeOperationsEnabled(enable: Boolean) {
        binding.buttonPlus.isEnabled = enable
        binding.buttonMinus.isEnabled = enable
        binding.buttonMultiply.isEnabled = enable
        binding.buttonDivorce.isEnabled = enable
    }
}