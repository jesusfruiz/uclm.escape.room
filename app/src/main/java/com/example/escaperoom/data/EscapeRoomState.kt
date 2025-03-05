package com.example.escaperoom.data

import androidx.compose.runtime.mutableStateListOf

data class EscapeRoomState(
    val inputPass1: String = "",
    val inputPass2: String = "",
    val inputPass3: String = "",
    val pass1Correct: Boolean = false,
    val pass2Correct: Boolean = false,
    val pass3Correct: Boolean = false,
    val errorInPass1: Boolean = false,
    val errorInPass2: Boolean = false,
    val errorInPass3: Boolean = false,
    val symptonsList: MutableList<String> = mutableStateListOf(),
    val symptonsAreCorrect: Boolean = false,
    val errorInSymptons: Boolean = false,
    )
