package com.example.escaperoom.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.util.Log
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.escaperoom.R


val uclmRed: Color = Color(0xFFB40A31)

@Composable
fun ImageButton(
    onClick: () -> Unit,
    @DrawableRes image: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = uclmRed,
            contentColor = Color.White),
        modifier = modifier
            .clickable(onClick = onClick)
            .size(width = dimensionResource(R.dimen.button_width),
                height = dimensionResource(R.dimen.button_height))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.small_padding)))
            Text(
                text = stringResource(description),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
fun openUrlWithApp(context: Context, url: String, packageName: String) {
    Log.d("CodeSpace", "Se intenta abrir la app")
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage(packageName) // Especifica el paquete de la app
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


