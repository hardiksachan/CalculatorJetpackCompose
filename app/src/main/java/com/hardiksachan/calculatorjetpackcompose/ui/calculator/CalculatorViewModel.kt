package com.hardiksachan.calculatorjetpackcompose.ui.calculator

class CalculatorViewModel {
    internal var subPrimaryDisplay: ((String) -> Unit)? = null
    internal var subSecondaryDisplay: ((String) -> Unit)? = null

    internal var primaryDisplay = ""
    internal var secondaryDisplay = ""

    internal fun updatePrimaryDisplay(message: String) {
        primaryDisplay = message
        subPrimaryDisplay?.invoke(primaryDisplay)
    }

    internal fun updateSecondaryDisplay(message: String) {
        secondaryDisplay = message
        subSecondaryDisplay?.invoke(secondaryDisplay)
    }
}