package com.hardiksachan.calculatorjetpackcompose.domain

interface IValidator {
    suspend fun validateExpression(exp: String): Boolean
}