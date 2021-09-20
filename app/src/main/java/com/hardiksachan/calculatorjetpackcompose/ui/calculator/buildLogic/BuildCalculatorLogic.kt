package com.hardiksachan.calculatorjetpackcompose.ui.calculator.buildLogic

import android.content.Context
import com.hardiksachan.calculatorjetpackcompose.common.ProductionDispatcherProvider
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.CalculatorContainer
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.CalculatorLogic
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.CalculatorViewModel

internal fun buildCalculatorLogic(
    container: CalculatorContainer,
    viewModel: CalculatorViewModel,
    context: Context
): CalculatorLogic = CalculatorLogic(
    container,
    viewModel,
    TODO(),
    ProductionDispatcherProvider
)

