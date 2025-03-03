package com.example.escaperoom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.escaperoom.ui.Challenge1Screen
import com.example.escaperoom.ui.ChallengeScreen
import com.example.escaperoom.ui.GameModel
import com.example.escaperoom.ui.PasswordScreen
import com.example.escaperoom.ui.YouTubePlayer
import com.example.escaperoom.ui.openUrlWithApp
import com.example.escaperoom.ui.theme.StartScreen


enum class EscapeRoomScreen() {
    Start,
    ChallengePass1,
    ChallengePass2,
    Challenge1,
    Challenge2,
    VideoPlayer,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EscapeRoomBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier,
        title = {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(R.drawable.logomarca_uclmcei_2021),
                    contentDescription = null,
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.weight(1f).heightIn(max=80.dp)
                        //.size(width = 120.dp, height = 80.dp).weight(2f)
                )

                //Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(R.drawable.fac_salud_removebg_preview),
                    contentDescription = null,
                    alignment = Alignment.CenterEnd,
                    modifier = Modifier.weight(1f).heightIn(max=80.dp)
                )
            }
        }
    )
}


@Composable
fun EscapeRoomApp(
    viewModel: GameModel = GameModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            EscapeRoomBar(
                modifier = Modifier.padding(dimensionResource(R.dimen.small_padding))
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController =navController,
            startDestination = EscapeRoomScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = EscapeRoomScreen.Start.name) {
                StartScreen(
                    onDossierButton = {navController.navigate(EscapeRoomScreen.VideoPlayer.name)},
                    onChallenge1Button = {navController.navigate(EscapeRoomScreen.ChallengePass1.name)},
                    onChallenge2Button = {navController.navigate(EscapeRoomScreen.ChallengePass2.name)},
                    modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.small_padding)))
            }

            composable(route = EscapeRoomScreen.ChallengePass1.name) {
//                PasswordScreen(
//                    onValueChange = {
//                        viewModel.setPassword1(it)
//                    },
//                    onClick = {
//                        viewModel.checkPassword1()
//                    },
//                    userInput = uiState.inputPass1,
//                    errorInPass = uiState.errorInPass1,
//                    state = uiState,
//                    modifier = Modifier.fillMaxSize(),
//                )
                var context = LocalContext.current
                var url = stringResource(R.string.urlCube)
                var codeSpaceApp = stringResource(R.string.cospacesApp)
                Challenge1Screen(
                    onPassUpdate = {
                        viewModel.setPassword1(it)
                    },
                    onPassButtonPressed = {
                        viewModel.checkPassword1()
                    },
                    onCubeButtonPressed = {
                        openUrlWithApp(
                            context = context,
                            url = url,
                            packageName = codeSpaceApp,
                        )
                    },
                    userInput = uiState.inputPass1,
                    errorInPass = uiState.errorInPass1,
                    state = uiState,
                    modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.medium_padding)),
                )

                if(uiState.pass1Correct) {
                    viewModel.resetState()
                    navController.navigate(EscapeRoomScreen.Challenge1.name)
                }
            }

            composable(route = EscapeRoomScreen.ChallengePass2.name) {
                PasswordScreen(
                    onValueChange = {
                        viewModel.setPassword2(it)
                    },
                    onClick = {
                        viewModel.checkPassword2()
                        if(uiState.pass2Correct) {
                            navController.navigate(EscapeRoomScreen.Challenge2.name)
                        }
                    },
                    userInput = uiState.inputPass2,
                    errorInPass = uiState.errorInPass2,
                    state = uiState,
                    modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.medium_padding)),
                )

                if(uiState.pass2Correct) {
                    viewModel.resetState()
                    navController.navigate(EscapeRoomScreen.Challenge2.name)
                }
            }

            composable(route = EscapeRoomScreen.Challenge1.name) {
                ChallengeScreen()
            }

            composable(route = EscapeRoomScreen.Challenge2.name) {
                ChallengeScreen()
            }

            composable(route = EscapeRoomScreen.VideoPlayer.name) {
                YouTubePlayer(
                    videoId = stringResource(R.string.urlVideo),
                    modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.small_padding))
                )
            }
        }
    }
}