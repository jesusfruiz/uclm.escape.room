package com.example.escaperoom.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.escaperoom.R
import com.example.escaperoom.data.EscapeRoomState
import com.example.escaperoom.ui.theme.EscapeRoomTheme

@Composable
fun Challenge1Screen(
    modifier: Modifier = Modifier,
    onPassUpdate: (String) -> Unit,
    onPassButtonPressed: () -> Unit,
    onCubeButtonPressed: () -> Unit,
    userInput: String,
    errorInPass: Boolean,
    state: EscapeRoomState,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {

        ImageButton(
            onClick = onCubeButtonPressed,
            image = R.drawable.cube_10019,
            description = R.string.cubeIconText,
            modifier = Modifier
        )

        PasswordScreen(
            onValueChange = onPassUpdate,
            onClick = onPassButtonPressed,
            userInput = userInput,
            errorInPass = errorInPass,
            state = state,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.large_padding))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Chalenge1Preview() {
    EscapeRoomTheme() {
        Challenge1Screen(
            onPassUpdate = {},
            onPassButtonPressed = {},
            onCubeButtonPressed = {},
            userInput = "",
            errorInPass = false,
            state = EscapeRoomState("", "", false, false),
            modifier = Modifier,
        )
    }
}