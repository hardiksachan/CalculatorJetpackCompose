package com.hardiksachan.calculatorjetpackcompose.infrastructure

import com.hardiksachan.calculatorjetpackcompose.domain.IEvaluator
import com.hardiksachan.calculatorjetpackcompose.domain.ResultWrapper
import com.hardiksachan.calculatorjetpackcompose.infrastructure.CalculatorEntity.Operand
import com.hardiksachan.calculatorjetpackcompose.infrastructure.CalculatorEntity.Operator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object EvaluatorImpl : IEvaluator {

    override suspend fun evaluateExpression(
        exp: String
    ): ResultWrapper<Exception, String> = withContext(Dispatchers.IO) {
        ResultWrapper.build {
            val parsedExpression: List<CalculatorEntity> = parse(exp)
            val postfixExpression: List<CalculatorEntity> = toPostfix(parsedExpression)
            val answer: Float = solvePostfix(postfixExpression)

            answer.toString()
        }
    }

    private fun solvePostfix(expression: List<CalculatorEntity>): Float {
        val stack = mutableListOf<Operand>()

        expression.forEach {
            when (it) {
                is Operand -> stack.add(it)
                is Operator -> {
                    val num2 = stack.removeLast().value
                    val num1 = stack.removeLast().value
                    stack.add(
                        Operand(
                            when (it.operatorValue) {
                                "+" -> num1 + num2
                                "-" -> num1 - num2
                                "*" -> num1 * num2
                                "/" -> num1 / num2
                                else -> throw IllegalStateException()
                            }
                        )
                    )
                }
            }
        }

        return stack.last().value
    }

    private fun toPostfix(expression: List<CalculatorEntity>): List<CalculatorEntity> {
        val result = mutableListOf<CalculatorEntity>()

        val stack = mutableListOf<Operator>()

        expression.forEach {
            when (it) {
                is Operand -> result.add(it)
                is Operator -> {
                    if (stack.isEmpty() || stack.last().priority < it.priority) stack.add(it)
                    else {
                        while (stack.last().priority >= it.priority) {
                            result.add(stack.removeLast())

                            if (stack.isEmpty()) break
                        }
                        stack.add(it)
                    }
                }
            }
        }

        while (stack.isNotEmpty()) {
            result.add(stack.removeLast())
        }

        return result
    }

    private fun parse(exp: String): List<CalculatorEntity> {
        var exp: String = exp
        listOf("+", "-", "*", "/").forEach {
            exp = exp.replace(it, "@$it@")
        }

        return exp.split("@").filter { it.isNotEmpty() }.map {
            when (it) {
                "+", "-", "*", "/" -> Operator(it)
                else -> Operand(it.trim().toFloat())
            }
        }
    }
}