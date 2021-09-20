package com.hardiksachan.calculatorjetpackcompose.domain

interface ICalculator {

    suspend fun evaluateExpression(
        exp: String,
        handleResult: (ResultWrapper<Exception, String>) -> Unit
    )

}