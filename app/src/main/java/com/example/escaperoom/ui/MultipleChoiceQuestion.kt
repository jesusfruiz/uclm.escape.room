package com.example.escaperoom.ui

import android.graphics.Paint.Align
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.escaperoom.R
import com.example.escaperoom.data.EscapeRoomState
import com.example.escaperoom.ui.theme.EscapeRoomTheme
import kotlin.math.log

@Composable
fun MultipleChoiceList(
    @StringRes title: Int,
    @StringRes question: Int,
    choices: List<String>,
    state: EscapeRoomState,
    onButtonClick: () -> Unit
    ) {
    LazyColumn(
        modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)).fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(
                text = stringResource(title),
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.largeFontSize).toSp() },
                lineHeight = with(LocalDensity.current) { dimensionResource(R.dimen.largeFontSize).toSp() * 1.2f },
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(question),
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.middleFontSize).toSp() },
                lineHeight = with(LocalDensity.current) { dimensionResource(R.dimen.middleFontSize).toSp() * 1.2f } // Ajusta según necesites
            )
        }

        itemsIndexed(choices) { index, choice ->
            val isChecked = state.symptonsList.contains(choice)
            if(isChecked) {
                var aux = 0;
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        if(state.symptonsList.contains(choice)) {
                            state.symptonsList.removeAt(state.symptonsList.indexOf(choice))
                        } else {
                            state.symptonsList.add(choice)
                        }
                    },
                )
                Text(
                    text = choice,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.middleFontSize).toSp() },
                    )
            }
        }

        item {
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.large_padding)))
        }

        if(state.errorInSymptons) {
            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(stringResource(R.string.badSelection),
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.middleFontSize).toSp() },
                        )
                    Spacer(modifier = Modifier.size(dimensionResource(R.dimen.large_padding)))
                }

            }
        }

        item {
            Button(
                onClick = onButtonClick,
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
}


/*@Preview(showBackground = true)
@Composable
fun MultiChoiceScreenPreview() {
    EscapeRoomTheme() {
        var choicesList = stringResource(R.string.challenge4Choices).split("\n")
        choicesList = choicesList.shuffled()
        MultipleChoiceList(
            R.string.challenge4QuestionTitle,
            R.string.challenge4Question,
            choicesList,
            state = EscapeRoomState())
    }
}*/