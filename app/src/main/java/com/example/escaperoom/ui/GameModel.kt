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
private const val PASSWORD3 = "Depresion5"

private val SYMPTONS = listOf(
    "Depresión del sistema nervioso central",
    "Hipoventilación",
    "Somnolencia",
    "Broncoconstricción"
)

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

    fun setPassword3(password3: String) {
        _uiState.update { currentState->
            currentState.copy(
                inputPass3 = password3,
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

    fun checkPassword3() {
        val passIsCorrect: Boolean = checkPassword(_uiState.value.inputPass3, PASSWORD3)

        if(passIsCorrect) {
            _uiState.update { currentState->
                currentState.copy(
                    inputPass3 = "",
                    pass3Correct = true,
                    errorInPass3 = false
                )
            }
        } else {
            _uiState.update { currentState->
                currentState.copy(
                    inputPass3 = "",
                    errorInPass3 = true
                )
            }
        }
    }

    fun switchErrorInSymptons() {
        _uiState.update { currentState->
            currentState.copy(
                errorInSymptons = true,
            )
        }
    }

    fun checkSymptons() {
        var symptonsCorrect = 0
        for (sympton in _uiState.value.symptonsList) {
            if(SYMPTONS.contains(sympton)) {
                symptonsCorrect++
            } else {
                _uiState.update { currentState->
                    currentState.copy(
                        symptonsAreCorrect = false
                    )
                }
            }
        }

        if (symptonsCorrect == SYMPTONS.size) {
            _uiState.update { currentState->
                currentState.copy(
                    symptonsAreCorrect = true
                )
            }
        }
    }

    fun onToggleSympton(choice: String) {
        if(_uiState.value.symptonsList.contains(choice)) {
            _uiState.value.symptonsList.removeAt(
                _uiState.value.symptonsList.indexOf(choice)
            )
        } else {
            _uiState.value.symptonsList.add(choice)
        }
    }

    fun symptonToggled(choice: String): Boolean {
        return _uiState.value.symptonsList.contains(choice)
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