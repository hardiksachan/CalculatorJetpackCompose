package com.hardiksachan.calculatorjetpackcompose.domain


interface IEvaluator {
    suspend fun evaluateExpression(exp: String): ResultWrapper<Exception, String>
}