package com.example.kwiaciarnia1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KoszykScreen(koszykViewModel: KoszykViewModel, zamknijKoszyk: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Twój koszyk", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = zamknijKoszyk) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_revert),
                            contentDescription = "Wróć"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (koszykViewModel.koszyk.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Koszyk jest pusty", fontSize = 20.sp)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(koszykViewModel.koszyk) { bukiet ->
                        KoszykItem(bukiet, koszykViewModel) // 🔹 Teraz przekazujemy koszykViewModel do KoszykItem()
                    }
                }

                Button(
                    onClick = { /* Tu dodamy płatność */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Przejdź do płatności")
                }
            }
        }
    }
}