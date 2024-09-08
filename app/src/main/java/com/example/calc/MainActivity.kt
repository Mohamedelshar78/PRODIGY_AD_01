package com.example.calc

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var history: TextView
    lateinit var result: TextView  // For displaying the result

    // Initialize buttons
    lateinit var buttonAc: Button
    lateinit var buttonMod: Button
    lateinit var buttonMultiplication: Button
    lateinit var buttonDivision: Button
    lateinit var buttonMinus: Button
    lateinit var buttonEqual: Button
    lateinit var buttonDelete: Button
    lateinit var buttonDot: Button
    lateinit var buttonAdd: Button
    lateinit var buttonNumber00: Button
    lateinit var buttonNumber0: Button
    lateinit var buttonNumber1: Button
    lateinit var buttonNumber2: Button
    lateinit var buttonNumber3: Button
    lateinit var buttonNumber4: Button
    lateinit var buttonNumber5: Button
    lateinit var buttonNumber6: Button
    lateinit var buttonNumber7: Button
    lateinit var buttonNumber8: Button
    lateinit var buttonNumber9: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeview()

        // Set number and operator button listeners
        buttonNumber00.setOnClickListener { appendVal("00",false) }
        buttonNumber0.setOnClickListener { appendVal("0", false) }
        buttonNumber1.setOnClickListener { appendVal("1", false) }
        buttonNumber2.setOnClickListener { appendVal("2", false) }
        buttonNumber3.setOnClickListener { appendVal("3", false) }
        buttonNumber4.setOnClickListener { appendVal("4", false) }
        buttonNumber5.setOnClickListener { appendVal("5", false) }
        buttonNumber6.setOnClickListener { appendVal("6", false) }
        buttonNumber7.setOnClickListener { appendVal("7", false) }
        buttonNumber8.setOnClickListener { appendVal("8", false) }
        buttonNumber9.setOnClickListener { appendVal("9", false) }
        buttonDot.setOnClickListener { appendVal(".", false) }
        buttonAc.setOnClickListener { appendVal("", true) }
        buttonDivision.setOnClickListener { appendVal(" ÷ ", false) }
        buttonMultiplication.setOnClickListener { appendVal(" * ", false) }
        buttonMinus.setOnClickListener { appendVal(" - ", false) }
        buttonAdd.setOnClickListener { appendVal(" + ", false) }
        buttonMod.setOnClickListener { appendVal(" % ", false) }

        

        buttonDelete.setOnClickListener {
            val expression = result.text.toString()
            if (expression.isNotEmpty()) {
                result.setText(expression.substring(0, expression.length - 1))
            }
        }

        buttonEqual.setOnClickListener {
            try {
                val expressionText = result.text.toString().replace("÷","/")  // Get the expression
                val expression = ExpressionBuilder(expressionText).build()  // Build the expression
                val evaluatedResult = expression.evaluate()  // Evaluate the expression
                val longResult = evaluatedResult.toLong()

                // Display the result in the result field
                if (evaluatedResult == longResult.toDouble()) {
                    result.setText(longResult.toString())
                } else {
                    result.setText(evaluatedResult.toString())
                }

                // Update the history with the full expression and result only after result is calculated
                history.append( "${expressionText.replace("/","÷")}\n" )// Display the full expression and result

            } catch (e: Exception) {
                Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }
    }

    // Append value to result or clear the field
    private fun appendVal(string: String, isClear: Boolean) {
        if (isClear) {
            result.setText("")
            history.text = ""
        } else {
            result.append(string)  // Append to result field
        }
    }

    // Initialize all views
    private fun initializeview() {
        history = findViewById(R.id.historyTextView)
        result = findViewById(R.id.resultTextView)  // If using EditText, this should be an EditText view

        buttonAc = findViewById(R.id.button_clererAll)
        buttonMod = findViewById(R.id.button_mod)
        buttonDelete = findViewById(R.id.button_delete)
        buttonDivision = findViewById(R.id.button_division)
        buttonMultiplication = findViewById(R.id.button_multiplication)
        buttonMinus = findViewById(R.id.button_minus)
        buttonEqual = findViewById(R.id.button_equal)
        buttonAdd = findViewById(R.id.button_add)
        buttonDot = findViewById(R.id.button_dot)

        buttonNumber0 = findViewById(R.id.button_0)
        buttonNumber00 = findViewById(R.id.button_00)
        buttonNumber1 = findViewById(R.id.button_1)
        buttonNumber2 = findViewById(R.id.button_2)
        buttonNumber3 = findViewById(R.id.button_3)
        buttonNumber4 = findViewById(R.id.button_4)
        buttonNumber5 = findViewById(R.id.button_5)
        buttonNumber6 = findViewById(R.id.button_6)
        buttonNumber7 = findViewById(R.id.button_7)
        buttonNumber8 = findViewById(R.id.button_8)
        buttonNumber9 = findViewById(R.id.button_9)
    }
}
