package com.example.vkeducation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.vkeducation.ui.theme.VkEducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstActivity(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FirstActivity(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val firstIntent = Intent(context, SecondActivity::class.java)
                firstIntent.putExtra("TEXT", text)
                context.startActivity(firstIntent)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Открыть вторую Activity")
        }
    }
}