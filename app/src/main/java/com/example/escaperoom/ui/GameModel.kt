package com.example.escaperoom.ui

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.escaperoom.EscapeRoomScreen
import com.example.escaperoom.R
import com.example.escaperoom.data.EscapeRoomState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.Normalizer

private const val PASSWORD1 = "Afrontamiento18"
private const val PASSWORD2 = "Comunicacion10"

class GameModel: ViewModel() {
    private val _uiState = MutableStateFlow(EscapeRoomState())
    val uiState: StateFlow<EscapeRoomState> = _uiState.asStateFlow()

    fun setPassword1(password1: String) {
        _uiState.update { currentState->
            currentState.copy(
                inputPass1 = password1,
            )
        }
    }

    fun setPassword2(password2: String) {
        _uiState.update { currentState->
            currentState.copy(
                inputPass2 = password2,
            )
        }
    }

    fun checkPassword1() {
        val passIsCorrect: Boolean = checkPassword(_uiState.value.inputPass1, PASSWORD1)

        if(passIsCorrect) {
            _uiState.update { currentState->
                currentState.copy(
                    inputPass1 = "",
                    pass1Correct = true,
                    errorInPass1 = false
                )
            }
        } else {
            _uiState.update { currentState->
                currentState.copy(
                    inputPass1 = "",
                    errorInPass1 = true
                )
            }
        }
    }

    fun checkPassword2() {
        val passIsCorrect: Boolean = checkPassword(_uiState.value.inputPass2, PASSWORD2)

        if(passIsCorrect) {
            _uiState.update { currentState->
                currentState.copy(
                    inputPass2 = "",
                    pass2Correct = true,
                    errorInPass2 = false
                )
            }
        } else {
            _uiState.update { currentState->
                currentState.copy(
                    inputPass2 = "",
                    errorInPass2 = true,
                )
            }
        }
    }

    private fun checkPassword(inText: String, password: String): Boolean {
        val textNormalized = Normalizer.normalize(inText, Normalizer.Form.NFD)
        val inputProcessed = textNormalized.replace(Regex("\\p{M}"), "")
        return inputProcessed.equals(password, ignoreCase = true)
    }

    fun resetState() {
        _uiState.value = EscapeRoomState()
    }
}