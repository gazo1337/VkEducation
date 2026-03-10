package com.example.vkeducation

import android.content.Intent
import android.net.Uri
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

    fun isValidPhoneNumber(input: String): Boolean {
        val cleaned = input.replace(Regex("[\\s\\-()]"), "")
        return cleaned.matches(Regex("^\\+?[1-9][0-9]{6,14}$"))
    }

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(text.isNotBlank() && isValidPhoneNumber(text)){
                    val uri = Uri.parse("tel:$text")
                    val secondIntent = Intent(Intent.ACTION_DIAL, uri)
                    context.startActivity(secondIntent)
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Позвонить другу! (он ждёт хехехе)")
        }
    }
}