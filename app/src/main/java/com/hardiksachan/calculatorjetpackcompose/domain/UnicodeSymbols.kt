package com.hardiksachan.calculatorjetpackcompose.domain

object UnicodeSymbols {
    const val plus: String = "+"
    const val minus: String = "‒"
    const val multiply: String = "×"
    const val divide: String = "÷"
    const val equals: String = "="
    const val bracketOpen: String = "("
    const val bracketClose: String = ")"

    fun isSymbol(s: String) =
        s == plus || s == minus || s == multiply || s == divide || s == bracketClose || s == bracketOpen
}