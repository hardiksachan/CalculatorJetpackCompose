package com.hardiksachan.calculatorjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hardiksachan.calculatorjetpackcompose.ui.screen.HomeScreen
import com.hardiksachan.calculatorjetpackcompose.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember {
                mutableStateOf(true)
            }

            AppTheme(darkTheme = darkTheme) {

                HomeScreen {
                    darkTheme = !darkTheme
                }
            }
        }
    }
}