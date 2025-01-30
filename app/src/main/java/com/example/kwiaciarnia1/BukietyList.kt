package com.example.kwiaciarnia1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BukietyList(
    bukiety: List<Bukiet>,
    listState: LazyListState,
    koszykViewModel: KoszykViewModel,
    otworzEdycje: (Bukiet) -> Unit // 🔹 Funkcja otwierająca ekran edycji bukietu
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(bukiety) { bukiet ->
            BukietItem(bukiet, otworzEdycje) // 🔹 Po kliknięciu otworzy ekran edycji
        }
    }
}