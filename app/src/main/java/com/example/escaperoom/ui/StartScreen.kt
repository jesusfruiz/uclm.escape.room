package com.example.escaperoom.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.escaperoom.EscapeRoomApp
import com.example.escaperoom.R
import com.example.escaperoom.ui.ImageButton


@Composable
fun StartScreen(
    onDossierButton: () -> Unit,
    onChallenge1Button: () -> Unit,
    onChallenge2Button: () -> Unit,
    modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier,
        ) {

        Row (
            modifier = Modifier.weight(1f),
        ){
            ImageButton(
                onClick = onDossierButton,
                image = R.drawable.expediente,
                description = R.string.dossier,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            ImageButton(
                onClick = onChallenge1Button,
                image = R.drawable.challenge_removebg_preview,
                description = R.string.challenge1,
                modifier = Modifier

            )


            ImageButton(
                onClick = onChallenge2Button,
                image = R.drawable.challenge_removebg_preview,
                description = R.string.challenge2,
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EscapeRoomTheme() {
        StartScreen(
            {}, {}, {}
        )
    }
}