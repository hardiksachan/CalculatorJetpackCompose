package com.hardiksachan.calculatorjetpackcompose.domain

interface ICalculator {
    suspend fun evaluateExpression(
        exp: String,
        callback: (ResultWrapper<Exception, String>) -> Unit
    )
}