package com.example.kwiaciarnia1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdycjaZamowieniaScreen(
    bukiet: Bukiet,
    koszykViewModel: KoszykViewModel,
    zamknijEkran: () -> Unit,
    otworzKoszyk: () -> Unit
) {
    var iloscKwiatow by remember { mutableStateOf(5) } // 🔹 Standardowa ilość kwiatów
    val cenaKwiatu = 10 // 🔹 Cena za pojedynczy kwiat (przykładowa wartość)

    // 🔹 Lista dodatków z różnymi cenami
    val dodatki = listOf(
        Pair("Kokarda", 5),
        Pair("Brokat", 8),
        Pair("Liście dekoracyjne", 6),
        Pair("Małe serduszko", 7),
        Pair("Elegancki wazon", 15),
        Pair("Złota tasiemka", 10),
        Pair("Srebrne ozdoby", 12),
        Pair("Pastelowe wstążki", 6)
    )

    var wybraneDodatki by remember { mutableStateOf(setOf<String>()) }

    // 🔹 Obliczanie całkowitej ceny bukietu
    val cenaCalkowita by derivedStateOf {
        (iloscKwiatow * cenaKwiatu) +
                (wybraneDodatki.sumOf { nazwa -> dodatki.find { it.first == nazwa }?.second ?: 0 })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edytuj zamówienie", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = zamknijEkran) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_revert),
                            contentDescription = "Wróć"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    koszykViewModel.dodajDoKoszyka(bukiet.copy(cena = "$cenaCalkowita zł"))
                    otworzKoszyk()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Dodaj do koszyka")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = bukiet.obrazek),
                contentDescription = bukiet.nazwa,
                modifier = Modifier.size(200.dp).padding(16.dp)
            )

            Text(text = bukiet.nazwa, fontSize = 24.sp, color = Color.Black)

            // 🔹 Wybór ilości kwiatów
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(onClick = { if (iloscKwiatow > 1) iloscKwiatow-- }) {
                    Text("-")
                }
                Text(
                    text = "$iloscKwiatow kwiatów",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Button(onClick = { iloscKwiatow++ }) {
                    Text("+")
                }
            }

            // 🔹 Przewijana lista dodatków
            Text(text = "Dodatki", fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .padding(horizontal = 16.dp)
            ) {
                items(dodatki) { (nazwa, cena) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Checkbox(
                            checked = nazwa in wybraneDodatki,
                            onCheckedChange = {
                                wybraneDodatki = if (nazwa in wybraneDodatki) {
                                    wybraneDodatki - nazwa
                                } else {
                                    wybraneDodatki + nazwa
                                }
                            }
                        )
                        Column {
                            Text(text = nazwa, fontSize = 18.sp)
                            Text(
                                text = "+ $cena zł",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // 🔹 Podsumowanie ceny
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Cena: $cenaCalkowita",
                    fontSize = 22.sp,
                    color = Color.Black
                )
                Text(
                    text = " zł",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}