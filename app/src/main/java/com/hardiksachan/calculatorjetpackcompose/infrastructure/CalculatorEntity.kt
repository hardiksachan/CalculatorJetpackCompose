package com.hardiksachan.calculatorjetpackcompose.infrastructure

sealed class CalculatorEntity {
    data class Operator(val operatorValue: String): CalculatorEntity() {
        val priority: Int = checkPriority(operatorValue)

        private fun checkPriority(operatorValue: String): Int {
            return when (operatorValue) {
                "*", "/" -> 2
                "-", "+" -> 1
                else -> throw IllegalAccessException("Illegal Operator")
            }
        }
    }

    data class Operand(val value: Float): CalculatorEntity()
}