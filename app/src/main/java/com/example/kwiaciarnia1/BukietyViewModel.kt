package com.example.kwiaciarnia1

import androidx.lifecycle.ViewModel

class BukietyViewModel : ViewModel() {
    val bukiety = listOf(
        // 🔹 Polskie bukiety
        Bukiet("Bukiet Róż", "99 zł", R.drawable.bukiet_roze),
        Bukiet("Bukiet Tulipanów", "79 zł", R.drawable.bukiet_tulipany),
        Bukiet("Bukiet Słoneczników", "89 zł", R.drawable.bukiet_sloneczniki),
        Bukiet("Bukiet Lilii", "119 zł", R.drawable.bukiet_lilie),
        Bukiet("Bukiet Mieszany", "95 zł", R.drawable.bukiet_mieszane),

        // 🔹 Angielskie bukiety
        Bukiet("Red Roses", "99 zł", R.drawable.bouquet_red_roses),
        Bukiet("Pink Tulips", "79 zł", R.drawable.bouquet_pink_tulips),
        Bukiet("White Lilies", "89 zł", R.drawable.bouquet_white_lilies),
        Bukiet("Yellow Sunflowers", "119 zł", R.drawable.bouquet_yellow_sunflowers),
        Bukiet("Mixed Wildflowers", "95 zł", R.drawable.bouquet_mixed_wildflowers),
        Bukiet("Purple Orchids", "129 zł", R.drawable.bouquet_purple_orchids),
        Bukiet("Soft Pink Peonies", "109 zł", R.drawable.bouquet_pink_peonies),
        Bukiet("Red and White Carnations", "85 zł", R.drawable.bouquet_red_white_carnations),
        Bukiet("Blue Hydrangeas", "99 zł", R.drawable.bouquet_blue_hydrangeas)
    )
}