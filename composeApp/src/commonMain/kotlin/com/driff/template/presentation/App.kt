package com.driff.template.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.driff.template.presentation.messages.MessagesScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        MessagesScreen()
    }
}