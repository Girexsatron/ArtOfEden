package com.example.kwiaciarnia1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val koszykViewModel: KoszykViewModel = viewModel()
            var pokazKoszyk by remember { mutableStateOf(false) }
            var wybranyBukiet by remember { mutableStateOf<Bukiet?>(null) }

            when {
                pokazKoszyk -> KoszykScreen(koszykViewModel) { pokazKoszyk = false }
                wybranyBukiet != null -> EdycjaZamowieniaScreen(
                    bukiet = wybranyBukiet!!,
                    koszykViewModel = koszykViewModel,
                    zamknijEkran = { wybranyBukiet = null },
                    otworzKoszyk = {
                        wybranyBukiet = null
                        pokazKoszyk = true // 🔹 Po dodaniu bukietu otwieramy koszyk
                    }
                )
                else -> ListaBukietowScreen(koszykViewModel, { pokazKoszyk = true }) { wybranyBukiet = it }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaBukietowScreen(
    koszykViewModel: KoszykViewModel,
    otworzKoszyk: () -> Unit,
    otworzEdycje: (Bukiet) -> Unit
) {
    val viewModel = BukietyViewModel()
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.art_of_eden_logo),
                        contentDescription = "Art of Eden Logo",
                        modifier = Modifier
                            .height(80.dp)
                            .padding(16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEAEAEA).copy(alpha = 0.5f) // 🔹 Lekko szare tło z 50% przezroczystości
                ),
                actions = {
                    Box(modifier = Modifier.padding(end = 16.dp)) {
                        IconButton(onClick = otworzKoszyk) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color.White, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.koszyk),
                                    contentDescription = "Koszyk",
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }

                        // 🔹 Licznik produktów w koszyku (jeśli coś jest dodane)
                        if (koszykViewModel.koszyk.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(Color.Red, shape = CircleShape)
                                    .align(Alignment.TopEnd),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = koszykViewModel.koszyk.size.toString(),
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            BackgroundImage(listState) // 🔹 Nadal widać tło pod TopAppBar
            BukietyList(viewModel.bukiety, listState, koszykViewModel, otworzEdycje)
        }
    }
}