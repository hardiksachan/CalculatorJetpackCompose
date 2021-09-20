package com.hardiksachan.calculatorjetpackcompose.ui.calculator

import com.hardiksachan.calculatorjetpackcompose.common.BaseLogic
import com.hardiksachan.calculatorjetpackcompose.common.DispatcherProvider
import com.hardiksachan.calculatorjetpackcompose.domain.ICalculator
import com.hardiksachan.calculatorjetpackcompose.domain.ResultWrapper
import com.hardiksachan.calculatorjetpackcompose.domain.UnicodeSymbols
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CalculatorLogic(
    private val container: CalculatorContainer,
    private val viewModel: CalculatorViewModel,
    private val calculatorFacade: ICalculator,
    dispatcher: DispatcherProvider
) : BaseLogic<CalculatorEvent>(dispatcher), CoroutineScope {

    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    private var isLastNumeric = false
    private var isDotIllegal = false


    override fun onEvent(event: CalculatorEvent) {
        when (event) {
            CalculatorEvent.Delete -> {
                if (viewModel.primaryDisplay.isNotEmpty()) {
                    viewModel.updatePrimaryDisplay(viewModel.primaryDisplay.dropLast(1))
                }
            }
            CalculatorEvent.DeleteAll -> {
                viewModel.updatePrimaryDisplay("")
                viewModel.updateSecondaryDisplay("")
                isLastNumeric = false
                isDotIllegal = false
            }
            CalculatorEvent.Evaluate -> handleEvaluate()
            is CalculatorEvent.Input -> handleInput(event.input)
            CalculatorEvent.OnStop -> {
                jobTracker.cancel()
            }
        }
    }

    private fun handleInput(key: String) {
        if (UnicodeSymbols.isSymbol(key)) {
            if (isLastNumeric) {
                viewModel.updatePrimaryDisplay("${viewModel.primaryDisplay}$key")
                isLastNumeric = false
                isDotIllegal = false    // Reset the DOT flag
            }
        } else if (key == ".") {
            if (isLastNumeric && !isDotIllegal) {
                viewModel.updatePrimaryDisplay("${viewModel.primaryDisplay}.")
                isLastNumeric = false
                isDotIllegal = true
            }
        } else {
            viewModel.updatePrimaryDisplay("${viewModel.primaryDisplay}$key")
            isLastNumeric = true
        }
    }

    private fun handleEvaluate() {
        if (!isLastNumeric) return
        launch {
            val exp = viewModel.primaryDisplay.map { char ->
                when (char.toString()) {
                    UnicodeSymbols.plus -> "+"
                    UnicodeSymbols.minus -> "-"
                    UnicodeSymbols.multiply -> "*"
                    UnicodeSymbols.divide -> "/"
                    else -> char.toString()
                }
            }.joinToString(separator = "")
            calculatorFacade.evaluateExpression(exp) {
                when (it) {
                    is ResultWrapper.Failure -> container.showError()
                    is ResultWrapper.Success -> {
                        viewModel.updateSecondaryDisplay(viewModel.primaryDisplay)
                        viewModel.updatePrimaryDisplay(it.result)
                    }
                }
            }
        }
    }
}