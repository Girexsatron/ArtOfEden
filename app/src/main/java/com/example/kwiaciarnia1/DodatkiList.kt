package com.example.kwiaciarnia1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun DodatkiList(
    dodatki: List<Pair<String, Int>>, // 🔹 Lista dodatków (nazwa + cena)
    wybraneDodatki: Set<String>,
    onDodatekChange: (String) -> Unit // 🔹 Callback do obsługi wyboru dodatków
) {
    LazyColumn(modifier = Modifier.fillMaxHeight(0.5f)) { // 🔹 Przewijana lista
        items(dodatki) { (nazwa, cena) ->
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Checkbox(
                    checked = nazwa in wybraneDodatki,
                    onCheckedChange = { onDodatekChange(nazwa) }
                )
                Column {
                    Text(text = nazwa, fontSize = 18.sp)
                    Text(
                        text = "+ $cena zł",
                        fontSize = 14.sp, // 🔹 Mniejsza czcionka dla ceny
                        color = Color.Gray
                    )
                }
            }
        }
    }
}