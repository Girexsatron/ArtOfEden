package com.example.kwiaciarnia1

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class KoszykViewModel : ViewModel() {
    val koszyk = mutableStateListOf<Bukiet>()

    fun dodajDoKoszyka(bukiet: Bukiet) {
        koszyk.add(bukiet)
    }

    fun usunZKoszyka(bukiet: Bukiet) { // 🔹 Nowa funkcja usuwania
        koszyk.remove(bukiet)
    }
}