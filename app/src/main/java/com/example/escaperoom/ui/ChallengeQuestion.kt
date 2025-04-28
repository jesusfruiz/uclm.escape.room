package com.example.escaperoom.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.escaperoom.R
import com.example.escaperoom.data.EscapeRoomState
import com.example.escaperoom.ui.theme.EscapeRoomTheme

@Composable
fun ChallengeScreen(
    vararg questions: Question
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.medium_padding)).fillMaxHeight().verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.Center
    ){
    for (q in questions) {

            Text(
                text = q.title,
                fontWeight = FontWeight.Bold,
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.largeFontSize).toSp() },
            )
            Text(
                text = q.content,
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.middleFontSize).toSp() },
                lineHeight = with(LocalDensity.current) { dimensionResource(R.dimen.largeFontSize).toSp() * 1.2f } // Ajusta según necesites
            )
            Text(
                text = q.choices,
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.middleFontSize).toSp() },
                lineHeight = with(LocalDensity.current) { dimensionResource(R.dimen.middleFontSize).toSp() * 1.2f } // Ajusta según necesites
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.large_padding)))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ChalengeScreenPreview() {
    EscapeRoomTheme() {
        var question1 = Question(
            stringResource(R.string.issue1QuestionTitle),
            stringResource(R.string.issue1Question),
            buildAnnotatedString {  stringResource(R.string.issue1Choices) }
        )

        var question2 = Question(
            stringResource(R.string.issue2QuestionTitle),
            stringResource(R.string.issue2Question),
            buildAnnotatedString { stringResource(R.string.issue2Choices) }
        )

        ChallengeScreen(
            question1,
            question2
        )
    }
}