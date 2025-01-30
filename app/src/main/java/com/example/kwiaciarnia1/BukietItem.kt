package com.example.kwiaciarnia1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BukietItem(bukiet: Bukiet, otworzEdycje: (Bukiet) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { otworzEdycje(bukiet) }, // 🔹 Otwieramy ekran edycji po kliknięciu
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = bukiet.obrazek),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp).padding(end = 16.dp)
            )

            Column {
                Text(text = bukiet.nazwa, fontSize = 20.sp, color = Color.Black)
            }
        }
    }
}