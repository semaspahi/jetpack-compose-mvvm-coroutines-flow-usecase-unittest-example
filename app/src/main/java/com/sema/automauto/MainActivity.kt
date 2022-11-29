package com.sema.automauto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sema.automauto.ui.navigation.NavGraph
import com.sema.automauto.ui.theme.AutoMautoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoMautoAppMain()
        }
    }
}

@Composable
fun AutoMautoAppMain() {
    AutoMautoAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavGraph()
        }
    }
}