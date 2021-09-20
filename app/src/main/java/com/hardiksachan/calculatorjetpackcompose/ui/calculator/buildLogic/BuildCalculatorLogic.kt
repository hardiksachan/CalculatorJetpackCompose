package com.hardiksachan.calculatorjetpackcompose.ui.calculator.buildLogic

import com.hardiksachan.calculatorjetpackcompose.common.ProductionDispatcherProvider
import com.hardiksachan.calculatorjetpackcompose.domain.CalculatorImpl
import com.hardiksachan.calculatorjetpackcompose.infrastructure.EvaluatorImpl
import com.hardiksachan.calculatorjetpackcompose.infrastructure.ValidatorImpl
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.CalculatorContainer
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.CalculatorLogic
import com.hardiksachan.calculatorjetpackcompose.ui.calculator.CalculatorViewModel

internal fun buildCalculatorLogic(
    container: CalculatorContainer,
    viewModel: CalculatorViewModel
): CalculatorLogic = CalculatorLogic(
    container,
    viewModel,
    CalculatorImpl(
        ValidatorImpl,
        EvaluatorImpl
    ),
    ProductionDispatcherProvider
)

