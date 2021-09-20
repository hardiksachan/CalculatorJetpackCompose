package com.hardiksachan.calculatorjetpackcompose.ui.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
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
    onEventHandler: (CalculatorEvent) -> Unit,
    viewModel: CalculatorViewModel,
    switchThemeHandler: () -> Unit
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

                var primaryDisplay by remember {
                    mutableStateOf("")
                }

                var secondaryDisplay by remember {
                    mutableStateOf("")
                }

                viewModel.subPrimaryDisplay = {
                    primaryDisplay = it
                }

                viewModel.subSecondaryDisplay = {
                    secondaryDisplay = it
                }

                Text(
                    text = buildDisplayString(message = secondaryDisplay),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.End
                )
                Text(
                    text = buildDisplayString(message = primaryDisplay),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(32.dp))
                Keypad(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onEventHandler = onEventHandler
                )
            }

            ThemeSelector(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                switchTheme = switchThemeHandler
            )

        }
    }
}

@Composable
fun buildDisplayString(message: String) = buildAnnotatedString {
    message.toCharArray().forEach {
        val isOperator = UnicodeSymbols.isSymbol(it.toString())
        withStyle(
            style = SpanStyle(
                color =
                if (isOperator)
                    Red
                else
                    MaterialTheme.colors.onPrimary
            )
        ) {
            if (isOperator)
                append(" $it ")
            else
                append(it)
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
    modifier: Modifier = Modifier,
    onEventHandler: (CalculatorEvent) -> Unit,
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
                SpecialOperatorButton(text = "C",
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onEventHandler(CalculatorEvent.Delete)
                            },
                            onLongPress = {
                                onEventHandler(CalculatorEvent.DeleteAll)
                            }
                        )
                    })
                NumberButton(
                    text = "7",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("7"))
                    }
                )
                NumberButton(text = "4",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("4"))
                    })
                NumberButton(text = "1",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("1"))
                    })
                NumberButton(text = "00",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("00"))
                    })
            }
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                SpecialOperatorButton(
                    text = UnicodeSymbols.bracketOpen,
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input(UnicodeSymbols.bracketOpen))
                    }
                )
                NumberButton(text = "8",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("8"))
                    })
                NumberButton(text = "5",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("5"))
                    })
                NumberButton(text = "2",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("2"))
                    })
                NumberButton(text = "0",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("0"))
                    })
            }
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                SpecialOperatorButton(
                    text = UnicodeSymbols.bracketClose,
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input(UnicodeSymbols.bracketClose))
                    }
                )
                NumberButton(text = "9",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("9"))
                    })
                NumberButton(text = "6",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("6"))
                    })
                NumberButton(text = "3",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("3"))
                    })
                NumberButton(text = ".",
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input("."))
                    })
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                OperatorButton(text = UnicodeSymbols.divide,
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input(UnicodeSymbols.divide))
                    })
                OperatorButton(text = UnicodeSymbols.multiply,
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input(UnicodeSymbols.multiply))
                    })
                OperatorButton(text = UnicodeSymbols.minus,
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input(UnicodeSymbols.minus))
                    })
                OperatorButton(text = UnicodeSymbols.plus,
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Input(UnicodeSymbols.plus))
                    })
                OperatorButton(text = UnicodeSymbols.equals,
                    modifier = Modifier.clickable {
                        onEventHandler(CalculatorEvent.Evaluate)
                    })
            }
        }
    }
}

@Composable
fun NumberButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        modifier = modifier
            .padding(16.dp),
        textAlign = TextAlign.Justify,
    )
}

@Composable
fun OperatorButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5,
        modifier = modifier
            .padding(16.dp),
        color = Red
    )
}

@Composable
fun SpecialOperatorButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        modifier = modifier
            .padding(16.dp),
        color = Green
    )
}