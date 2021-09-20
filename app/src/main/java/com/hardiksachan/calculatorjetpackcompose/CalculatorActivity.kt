package com.hardiksachan.calculatorjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.*
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.buildLogic.buildCalculatorLogic
import com.hardiksachan.calculatorjetpackcompose.ui.theme.AppTheme

class CalculatorActivity : ComponentActivity(), CalculatorContainer {

    private lateinit var logic: CalculatorLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = CalculatorViewModel()

        setContent {
            var darkTheme by remember {
                mutableStateOf(true)
            }

            AppTheme(darkTheme = darkTheme) {
                HomeScreen(
                    onEventHandler = logic::onEvent,
                    viewModel = viewModel,
                ) {
                    darkTheme = !darkTheme
                }
            }
        }

        logic = buildCalculatorLogic(this, viewModel)
    }

    override fun onStop() {
        super.onStop()
        logic.onEvent(CalculatorEvent.OnStop)
    }

    override fun showError() {
        Toast.makeText(
            this,
            "Error Occurred",
            Toast.LENGTH_SHORT
        ).show()
    }
}