package com.emilioheinz.mytrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(view: View) {
        val id = view.id

        if(id == R.id.buttonCalculate) {
            handleCalculateClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener(this)
    }

    private fun handleCalculateClick() {
        if(isValid()) {
            try {
                val distance = editDistance.text.toString().toFloat()
                val price = editPrice.text.toString().toFloat()
                val autonomy = editAutonomy.text.toString().toFloat()

                val result = ((distance * price) / autonomy)

                textResult.setText("Total: R$ $result")
            } catch (nfe: NumberFormatException) {
                Toast.makeText(this, R.string.invalid_values, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, R.string.invalid_values, Toast.LENGTH_LONG).show()
        }
    }

    private fun isValid(): Boolean {
        val isDistanceValid = editDistance.text.toString() != ""
        val isPriceValid = editPrice.text.toString() != ""
        val isAutonomyValid = editAutonomy.text.toString() != ""  && editAutonomy.text.toString() != "0"

        return isDistanceValid && isPriceValid && isAutonomyValid
    }
}
