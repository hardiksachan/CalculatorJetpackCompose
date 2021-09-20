package com.hardiksachan.calculatorjetpackcompose.ui.calculator

sealed class CalculatorEvent {
    object OnStop : CalculatorEvent()
    object Evaluate : CalculatorEvent()
    object Delete : CalculatorEvent()
    object DeleteAll : CalculatorEvent()
    data class Input(val input: String) : CalculatorEvent()
}