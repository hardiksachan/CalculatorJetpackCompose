package com.hardiksachan.calculatorjetpackcompose.infrastructure

import com.hardiksachan.calculatorjetpackcompose.domain.IValidator

object ValidatorImpl : IValidator {
    override suspend fun validateExpression(exp: String): Boolean {

        //check for valid starting/ending chars
        if (invalidStart(exp)) return false
        if (invalidEnd(exp)) return false

        //Check for concurrent decimals and operators like "2++2"
        if (hasConcurrentOperators(exp)) return false
        if (hasConcurrentDecimals(exp)) return false
        if (hasTooManyDecimalOperand(exp)) return false


        return true
    }

    private fun hasTooManyDecimalOperand(expression: String): Boolean {
        val operands = expression.split("+", "-", "/", "*")
        operands.forEach { operand: String ->
            val occurrences = operand.count { it == '.' }
            if (occurrences > 1) return true
        }

        return false
    }

    private fun invalidEnd(expression: String): Boolean {
        return when {
            expression.endsWith("+") -> true
            expression.endsWith("-") -> true
            expression.endsWith("*") -> true
            expression.endsWith("/") -> true
            expression.endsWith(".") -> true
            else -> false
        }
    }

    private fun invalidStart(expression: String): Boolean {
        return when {
            expression.startsWith("+") -> true
            expression.startsWith("-") -> true
            expression.startsWith("*") -> true
            expression.startsWith("/") -> true
            expression.startsWith(".") -> true
            else -> false
        }
    }

    private fun hasConcurrentDecimals(expression: String): Boolean {
        expression.indices
            .forEach {
                if (it < expression.lastIndex) {
                    if (isConcurrentDecimal(expression[it], expression[it + 1])) {
                        return true
                    }
                }
            }

        return false
    }

    private fun isConcurrentDecimal(current: Char, next: Char): Boolean {
        if (current.toString() == "." && next.toString() == ".") {
            return true
        }
        return false
    }

    private fun hasConcurrentOperators(expression: String): Boolean {
        expression.indices
            .forEach {
                if (it < expression.lastIndex) {
                    if (isConcurrentOperator(expression[it], expression[it + 1])) {
                        return true
                    }
                }
            }

        return false
    }

    private fun isConcurrentOperator(current: Char, next: Char): Boolean {
        if (isOperator(current) && isOperator(next)) {
            return true
        }
        return false
    }

    private fun isOperator(char: Char): Boolean {
        return when {
            //not sure why I had to toString() but char.equals("+") was not working as expected
            char.toString() == "+" -> true
            char.toString() == "-" -> true
            char.toString() == "*" -> true
            char.toString() == "/" -> true
            else -> false
        }
    }
}