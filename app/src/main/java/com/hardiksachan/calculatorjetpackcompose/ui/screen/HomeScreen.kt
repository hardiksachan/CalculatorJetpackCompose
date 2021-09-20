package com.hardiksachan.calculatorjetpackcompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hardiksachan.calculatorjetpackcompose.R
import com.hardiksachan.calculatorjetpackcompose.domain.UnicodeSymbols
import com.hardiksachan.calculatorjetpackcompose.ui.theme.Green
import com.hardiksachan.calculatorjetpackcompose.ui.theme.Red

@Composable
fun HomeScreen(
    switchTheme: () -> Unit
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = buildAnnotatedString {
                        "814 ${UnicodeSymbols.multiply} 122".toCharArray().forEach {
                            withStyle(
                                style = SpanStyle(
                                    color =
                                    if (UnicodeSymbols.isSymbol(it.toString()))
                                        Red
                                    else
                                        MaterialTheme.colors.onPrimary
                                )
                            ) {
                                append(it)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.End
                )
                Text(
                    text = "99,308",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(32.dp))
                Keypad(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            ThemeSelector(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                switchTheme = switchTheme
            )

        }
    }
}

@Composable
fun ThemeSelector(
    modifier: Modifier = Modifier,
    iconSize: Dp = 45.dp,
    switchTheme: () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {
        val isDarkTheme = !MaterialTheme.colors.isLight

        Icon(
            painter = painterResource(
                id =
                if (isDarkTheme)
                    R.drawable.ic_moon
                else
                    R.drawable.ic_sun
            ),
            contentDescription = "theme icon",
            modifier = Modifier
                .size(iconSize)
                .padding(8.dp)
                .clickable {
                    switchTheme()
                },
        )
    }
}


@Composable
fun Keypad(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = CornerSize(32.dp),
            topEnd = CornerSize(32.dp),
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp),
        ),
        color = MaterialTheme.colors.primary,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                SpecialOperatorButton(text = "C")
                NumberButton(text = "7")
                NumberButton(text = "4")
                NumberButton(text = "1")
                NumberButton(text = "00")
            }
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                SpecialOperatorButton(text = UnicodeSymbols.bracketOpen)
                NumberButton(text = "8")
                NumberButton(text = "5")
                NumberButton(text = "2")
                NumberButton(text = "0")
            }
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                SpecialOperatorButton(text = UnicodeSymbols.bracketClose)
                NumberButton(text = "9")
                NumberButton(text = "6")
                NumberButton(text = "3")
                NumberButton(text = ".")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                OperatorButton(text = UnicodeSymbols.divide)
                OperatorButton(text = UnicodeSymbols.multiply)
                OperatorButton(text = UnicodeSymbols.minus)
                OperatorButton(text = UnicodeSymbols.plus)
                OperatorButton(text = UnicodeSymbols.equals)
            }
        }
    }
}

@Composable
fun NumberButton(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(16.dp),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun OperatorButton(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(16.dp),
        color = Red
    )
}

@Composable
fun SpecialOperatorButton(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(16.dp),
        color = Green
    )
}