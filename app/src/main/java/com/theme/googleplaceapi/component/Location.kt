package com.theme.googleplaceapi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theme.googleplaceapi.R

@Composable
fun Location(locationTitle : String, locationDetails : String, onClick: () -> Unit) {

    Box(modifier = Modifier.fillMaxWidth().clickable{onClick.invoke()}.padding(10.dp)){
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_location),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = locationTitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = colorResource(R.color.black)
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = locationDetails,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = colorResource(R.color.gray)
                    )
                )
            }
        }

    }

}

@Preview
@Composable
private fun Preview() {
    Location(
        locationTitle = "Dhaka medical collage",
        locationDetails = "Dhaka medical collage, Dhaka, Bangladesh Road No 03 Dhaka",
        onClick = {}
    )
}