package com.example.escaperoom.data

data class EscapeRoomState(
    val inputPass1: String = "",
    val inputPass2: String = "",
    val pass1Correct: Boolean = false,
    val pass2Correct: Boolean = false,
    val errorInPass1: Boolean = false,
    val errorInPass2: Boolean = false,

    )
