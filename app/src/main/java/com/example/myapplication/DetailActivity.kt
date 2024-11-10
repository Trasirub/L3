package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Логування для перевірки отриманих даних
        val countryName = intent.getStringExtra("countryName") ?: "Країна"
        val imageUrl = intent.getStringExtra("imageUrl") ?: ""
        Log.d("DetailActivity", "Отримали countryName: $countryName, imageUrl: $imageUrl")

        setContent {
            CountryDetailScreen(countryName = countryName, imageUrl = imageUrl)
        }
    }
}

@Composable
fun CountryDetailScreen(countryName: String, imageUrl: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = countryName,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (imageUrl.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = countryName,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
        } else {
            Text(text = "Зображення відсутнє", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { (context as? ComponentActivity)?.finish() }) {
            Text("Назад")
        }
    }
}
