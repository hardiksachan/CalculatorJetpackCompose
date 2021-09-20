package com.hardiksachan.calculatorjetpackcompose.domain

class CalculatorImpl(
    val validator: IValidator,
    val evaluator: IEvaluator
) : ICalculator {

    override suspend fun evaluateExpression(
        exp: String,
        callback: (ResultWrapper<Exception, String>) -> Unit
    ) {
        if (validator.validateExpression(exp)) callback.invoke(evaluator.evaluateExpression(exp))
        else callback.invoke(ResultWrapper.build { throw Exception() })
    }

}