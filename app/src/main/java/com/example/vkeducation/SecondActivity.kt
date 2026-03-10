package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkeducation.ui.theme.VkEducationTheme

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Получаем переданный текст из первой Activity
        val receivedText = intent.getStringExtra("TEXT") ?: "Нет данных"

        setContent {
            VkEducationTheme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    SecondScreen(
                        receivedText = receivedText,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SecondScreen(receivedText: String, modifier: Modifier = Modifier) {
    Text(
        text = receivedText,
        modifier = modifier.padding(16.dp)
    )
}