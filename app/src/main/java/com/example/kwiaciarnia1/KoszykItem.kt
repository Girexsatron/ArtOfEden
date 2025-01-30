package com.example.kwiaciarnia1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun KoszykItem(bukiet: Bukiet, koszykViewModel: KoszykViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = bukiet.obrazek),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp).padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = bukiet.nazwa, fontSize = 20.sp, color = Color.Black)
                Text(text = bukiet.cena, fontSize = 16.sp, color = Color.Gray)
            }

            // 🔹 Przycisk "Usuń"
            IconButton(onClick = { koszykViewModel.usunZKoszyka(bukiet) }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_delete),
                    contentDescription = "Usuń z koszyka",
                    tint = Color.Red
                )
            }
        }
    }
}