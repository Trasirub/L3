package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryListScreen()
        }
    }
}

@Composable
fun CountryListScreen() {
    val countries = listOf(
        "Україна" to "https://static.ukrinform.com/photos/2022_04/thumb_files/630_360_1649691691-907.png",
        "Франція" to "https://travel-tours.com.ua/wp-content/uploads/2016/02/france-vidpochynok-1.jpg",
        "Італія" to "https://comments.ua/img/publications/VOWv9ELm9RFBiFZUcSrJbuqzg6MJVxhl.jpg",
        "Німеччина" to "https://tut-cikavo.com/images/1_new/reichstag-pix.jpg",
        "Іспанія" to "https://img.eurointegration.com.ua/images/doc/f/5/f568825-705.jpg"
    )
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(countries.size) { index ->
            val (countryName, imageUrl) = countries[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Log.d("MainActivity", "Натиснули на $countryName")
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra("countryName", countryName)
                            putExtra("imageUrl", imageUrl)
                        }
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = countryName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B)
                    )
                }
            }
        }
    }
}
