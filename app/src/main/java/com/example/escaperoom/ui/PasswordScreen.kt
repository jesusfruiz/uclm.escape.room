package com.example.escaperoom.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.escaperoom.R
import com.example.escaperoom.data.EscapeRoomState
import com.example.escaperoom.ui.theme.EscapeRoomTheme
import com.example.escaperoom.ui.theme.StartScreen

@Composable
fun PasswordScreen(
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    userInput: String,
    errorInPass: Boolean,
    state: EscapeRoomState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(errorInPass) {
            Log.d("Password", "Ha habido un error en la contraseña")
        } else {
            Log.d("Password", "Pues nada")
        }
        if(state.pass1Correct) {
            Log.d("Password", "Contraseña correcta")
        } else {
            Log.d("Password", "Contraseña no correcta")
        }
        OutlinedTextField(
            value = userInput,
            onValueChange = onValueChange,
            placeholder = { Text(stringResource(R.string.passwordText)) },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {onClick()}),
            isError = errorInPass,
            modifier = Modifier.fillMaxWidth().height(dimensionResource(R.dimen.textfield_height)),
        )

        Spacer(Modifier.size(dimensionResource(R.dimen.large_padding)))

        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.textfield_height)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                uclmRed, Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray
            ),
        ) {
            Text(stringResource(R.string.checkPass))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EscapeRoomTheme() {
        PasswordScreen(
            onValueChange = {},
            onClick = {},
            userInput = "",
            errorInPass = false,
            state = EscapeRoomState("", "", false, false),
            modifier = Modifier,
        )
    }
}