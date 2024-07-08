package com.viz.to_do_listapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomFloatingActionButton( icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 42.dp, end = 42.dp),
        horizontalArrangement = Arrangement.End,
//                verticalAlignment = Alignment.Bottom
    ) {
        FloatingActionButton(
            onClick = onClick ,
            elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
            containerColor = MaterialTheme.colorScheme.onBackground ,
            modifier = Modifier.scale(1.25f),// .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(50.dp)),
            shape = RoundedCornerShape(50.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background.copy(alpha = 1f),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}